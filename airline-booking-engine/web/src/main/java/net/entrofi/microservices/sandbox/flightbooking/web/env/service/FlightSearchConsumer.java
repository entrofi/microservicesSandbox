package net.entrofi.microservices.sandbox.flightbooking.web.env.service;


import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebAirport;
import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebFlight;
import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebFlightSearchQuery;
import net.entrofi.microservices.sandbox.flightbooking.web.env.model.FlightSearchFlight;
import net.entrofi.microservices.sandbox.flightbooking.web.env.model.FlightSearchFlightQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightSearchConsumer {


    private static final String FLIGHT_SEARCH_URL = "http://FLIGHT-SEARCH";

    @Autowired
    private RestTemplate restTemplate;


    public List<WebFlight> findFlights(WebFlightSearchQuery webFlightSearchFlightQuery) {
        FlightSearchFlightQuery flightQuery = toFlightSearchFlightQuery(webFlightSearchFlightQuery);
        HttpEntity<FlightSearchFlightQuery> requestEntity = new HttpEntity<>(flightQuery);
        ResponseEntity<List<FlightSearchFlight>> responseEntity = restTemplate.exchange(FLIGHT_SEARCH_URL + "/flights/search",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<FlightSearchFlight>>() {});
        return responseEntity.getBody()
                             .stream().map(flightSearchFlight -> toWebFlight(flightSearchFlight))
                             .collect(Collectors.toList());
    }

    public WebFlight getFlightDetail(String id) {
        FlightSearchFlight result =
                restTemplate.getForObject(FLIGHT_SEARCH_URL + "/flights/" + id, FlightSearchFlight.class);
        return toWebFlight(result);
    }

    private WebFlight toWebFlight(FlightSearchFlight flightSearchFlight) {
        WebFlight webFlight = new WebFlight();
        webFlight.setAvailableSeats(flightSearchFlight.getAvailableSeats());
        webFlight.setDate(flightSearchFlight.getDate());
        webFlight.setFare(flightSearchFlight.getFare());

        WebAirport.NameCodeParamWrapper destinationAirportParam = new WebAirport.NameCodeParamWrapper(flightSearchFlight
                .getDestination().getName(), flightSearchFlight.getDestination().getCode());
        WebAirport.NameCodeParamWrapper destinationDivParam = new WebAirport.NameCodeParamWrapper(flightSearchFlight
                .getDestination().getDivisionName(), null);
        webFlight.setDestination(new WebAirport(destinationAirportParam, null, destinationDivParam));

        WebAirport.NameCodeParamWrapper originAirportParam = new WebAirport.NameCodeParamWrapper(flightSearchFlight
                .getOrigin().getName(), flightSearchFlight.getOrigin().getCode());
        WebAirport.NameCodeParamWrapper originDivParam = new WebAirport.NameCodeParamWrapper(flightSearchFlight
                .getOrigin().getDivisionName(), null);
        webFlight.setOrigin(new WebAirport(originAirportParam, null, originDivParam));

        webFlight.setFlightNumber(flightSearchFlight.getFlightNumber());
        webFlight.setAvailableSeats(flightSearchFlight.getAvailableSeats());

        webFlight.setId(flightSearchFlight.getId());

        return webFlight;
    }

    private FlightSearchFlightQuery toFlightSearchFlightQuery(WebFlightSearchQuery webFlightSearchFlightQuery) {
        FlightSearchFlightQuery flightQuery = new FlightSearchFlightQuery();
        flightQuery.setDate(webFlightSearchFlightQuery.getDate());
        flightQuery.setOriginDivision(webFlightSearchFlightQuery.getOrigin());
        flightQuery.setDestinationDivision(webFlightSearchFlightQuery.getDestination());
        return flightQuery;
    }


}
