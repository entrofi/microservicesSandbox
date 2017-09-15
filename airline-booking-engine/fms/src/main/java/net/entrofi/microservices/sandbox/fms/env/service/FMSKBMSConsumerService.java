package net.entrofi.microservices.sandbox.fms.env.service;


import net.entrofi.microservices.sandbox.fms.env.model.Airport;
import net.entrofi.microservices.sandbox.fms.env.model.BaseServiceListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FMSKBMSConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FMSKBMSConsumerService.class);

    private static final String KBMS_ENDPOINT = "http://KBMS";

    @Autowired
    private RestTemplate restTemplate;

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
