package net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.service;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Airport;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.KBMSAirport;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.KBMSDivision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class FSKBMSConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FSKBMSConsumerService.class);
    private static final String KBMS_ENDPOINT = "http://KBMS";

    @Autowired
    private RestTemplate restTemplate;

    public Airport findAirportByCode(String airportCode) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("code", airportCode);
        ResponseEntity<Resource<KBMSAirport>> responseEntity =
                restTemplate.exchange(KBMS_ENDPOINT + "/airports/search/query?code={code}", HttpMethod.GET, null,
                        new ParameterizedTypeReference<Resource<KBMSAirport>>() {
                        }, queryParams);

        KBMSAirport kbmsAirport;
        Airport airport = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Resource<KBMSAirport> airportResource = responseEntity.getBody();
            kbmsAirport = airportResource.getContent();
            KBMSDivision kbmsDivision = fetchDivisionByDirectLink(fixIncorrectLinkFromSpringHateoas(KBMS_ENDPOINT,
                    airportResource.getLink("division")));
            airport = new Airport();
            airport.setCode(kbmsAirport.getCode());
            airport.setName(kbmsAirport.getName());
            airport.setDivisionName(kbmsDivision.getName());

        } else {
            LOGGER.warn(responseEntity.getStatusCode() + "; unable to get airport response");
        }

        return airport;
    }


    private KBMSDivision fetchDivisionByDirectLink(String resourceUri) {

        ResponseEntity<Resource<KBMSDivision>> responseEntity = restTemplate.exchange(resourceUri, HttpMethod
                .GET, null, new ParameterizedTypeReference<Resource<KBMSDivision>>() {});
        KBMSDivision kbmsDivision = null;
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            kbmsDivision = responseEntity.getBody().getContent();
        } else {
            LOGGER.warn(responseEntity.getStatusCode() + "; Unable to get division response using " + resourceUri);
        }
        return kbmsDivision;
    }

    /**
     * Workaround for spring hateoas's inconvenience for link generation which is burning CoC. Spring HATEOAS does not
     * allow hostname configuration for link generation. Therefore when the endpoints are behind a LB links are
     * generated with incorrect domain section.
     * @param hostAlias alias to replace for
     * @param link actual link return from spring-hateoas
     * @return reformatted and  correct string representation of the link
     * @deprecated Remove this method after adding API gateway.
     */
    private String fixIncorrectLinkFromSpringHateoas(String hostAlias, Link link) {
        final String domainPattern = "^[a-z][a-z0-9+\\-.]*://([a-z0-9\\-._~%!$&'()*+,;=]+@)?([a-z0-9\\-"
                + "._~%]+|\\[[a-z0-9\\-._~%!$&'()*+,;=:]+\\])(:[0-9]+)?";
        Matcher matcher = Pattern.compile(domainPattern).matcher(link.getHref());
        if(matcher.lookingAt()) {
            return matcher.replaceFirst(hostAlias);
        }
        return link.getHref();
    }

}
