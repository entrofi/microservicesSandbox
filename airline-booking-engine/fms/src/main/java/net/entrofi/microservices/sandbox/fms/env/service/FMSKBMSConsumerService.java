package net.entrofi.microservices.sandbox.fms.env.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.entrofi.microservices.sandbox.fms.env.model.Airport;
import net.entrofi.microservices.sandbox.fms.env.model.BaseServiceListResponse;
import net.entrofi.microservices.sandbox.fms.env.model.KBMSAirport;
import net.entrofi.microservices.sandbox.fms.env.model.KBMSDivision;
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
public class FMSKBMSConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FMSKBMSConsumerService.class);

    private static final String KBMS_ENDPOINT = "http://localhost:8070";

    private RestTemplate restTemplate;

    public FMSKBMSConsumerService() {
        this.restTemplate = getRestTemplateWithHalMessageConverter();
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

    public List<Airport> getAirports() {
        //TODO change this call to exchange with parameterizedtyperef...
        final BaseServiceListResponse airportsResponse = restTemplate.getForObject(KBMS_ENDPOINT + "/airports",
                BaseServiceListResponse.class);

        return airportsResponse.getEmbedded().getList();
    }

    public Airport findAirportByCode(String code) {
        final Airport kbmsAirport = restTemplate.getForObject(KBMS_ENDPOINT + "/airports/search/query?code="
                                +code, Airport.class);
        return kbmsAirport;
    }


}
