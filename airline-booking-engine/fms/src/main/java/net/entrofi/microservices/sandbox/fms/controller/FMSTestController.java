package net.entrofi.microservices.sandbox.fms.controller;


import net.entrofi.microservices.sandbox.fms.app.FMSInitializerService;
import net.entrofi.microservices.sandbox.fms.domain.entity.Flight;
import net.entrofi.microservices.sandbox.fms.env.model.FlightCreationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fms-test")
public class FMSTestController {


    @Autowired
    private FMSInitializerService fmsInitializerService;

    @GetMapping("create")
    public List<Flight> createFlights(@RequestParam(name = "count", defaultValue = "10")int count) {
        return fmsInitializerService.createFlights(count);
    }

    @PostMapping("create")
    public Flight createFlight(@RequestBody FlightCreationModel flightCreationModel) {
        return fmsInitializerService.createFlightFromToOn(flightCreationModel.getOriginAirport(),
                    flightCreationModel.getDestinationAirport(),
                    flightCreationModel.getDate());
    }
}
