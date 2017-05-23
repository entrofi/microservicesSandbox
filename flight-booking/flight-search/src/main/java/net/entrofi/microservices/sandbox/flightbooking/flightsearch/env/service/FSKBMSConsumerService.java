package net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Airport;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.KBMSAirport;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.KBMSDivision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.hateoas.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class FSKBMSConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FSKBMSConsumerService.class);
    private static final String KBMS_ENDPOINT = "http://localhost:8070";

    private RestTemplate restTemplate;

    public FSKBMSConsumerService() {
        this.restTemplate = getRestTemplateWithHalMessageConverter();
    }

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
            KBMSDivision kbmsDivision = fetchDivisionByDirectLink(airportResource.getLink("division").getHref());
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

    private static RestTemplate getRestTemplateWithHalMessageConverter() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> existingConverters = restTemplate.getMessageConverters();
        List<HttpMessageConverter<?>> newConverters = new ArrayList<>();
        newConverters.add(getHalMessageConverter());
        newConverters.addAll(existingConverters);
        restTemplate.setMessageConverters(newConverters);
        return restTemplate;
    }

    private static HttpMessageConverter getHalMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jackson2HalModule());
        MappingJackson2HttpMessageConverter halConverter =
                new TypeConstrainedMappingJackson2HttpMessageConverter(ResourceSupport.class);
        halConverter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
        halConverter.setObjectMapper(objectMapper);
        return halConverter;
    }
}
