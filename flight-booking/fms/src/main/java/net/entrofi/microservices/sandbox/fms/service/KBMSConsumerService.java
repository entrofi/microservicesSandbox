
package net.entrofi.microservices.sandbox.fms.service;


import net.entrofi.microservices.sandbox.fms.domain.model.Airport;
import net.entrofi.microservices.sandbox.fms.domain.model.BaseServiceListResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Component
public class KBMSConsumerService {

    private static final String KBMS_ENDPOINT = "http://localhost:8070";

    private RestTemplate restTemplate;

    public KBMSConsumerService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Airport> getAirports() {
        final BaseServiceListResponse airportsResponse = restTemplate.getForObject(KBMS_ENDPOINT + "/airports", BaseServiceListResponse.class);

        return airportsResponse.getEmbedded().getList();
    }

}
