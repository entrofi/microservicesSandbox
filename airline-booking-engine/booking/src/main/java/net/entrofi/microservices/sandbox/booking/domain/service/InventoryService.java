package net.entrofi.microservices.sandbox.booking.domain.service;


import net.entrofi.microservices.sandbox.booking.app.helpers.InventoryIdHelper;
import net.entrofi.microservices.sandbox.booking.domain.model.Flight;
import net.entrofi.microservices.sandbox.booking.domain.model.FlightMarket;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import net.entrofi.microservices.sandbox.booking.domain.repository.FlightMarketRepository;
import net.entrofi.microservices.sandbox.booking.domain.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);


    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private FlightMarketRepository flightMarketRepository;


    public Inventory findByIdString(String flightString) {
       Inventory inventory = null;
        try{
           //inventory = inventoryRepository.findOne(InventoryIdHelper.convertFlightFromIATAString(flightString));
       }catch (IllegalArgumentException iae) {
            LOGGER.warn("Invalid flight string repesentation provided for finding flight: " + flightString, iae);
        }
       return inventory;
    }

    public Inventory save(Flight flight, int capacity) {
        FlightMarket flightMarket = findOrCreateFlightMarket(flight, false);
        Inventory inventory = new Inventory();
        inventory.setCapacity(capacity);
        inventory.setFlight(flight);
        inventory.setFlightMarket(flightMarket);
        return inventoryRepository.save(inventory);
    }

    private FlightMarket findOrCreateFlightMarket(Flight flight, boolean directional) {
        FlightMarket.FlightMarketId marketIdProbe = new FlightMarket.FlightMarketId(flight.getOrigin()
                , flight.getDestination(), directional );
        FlightMarket flightMarket = flightMarketRepository.findOneByFlightMarketId(marketIdProbe);
        if(flightMarket == null) {
            flightMarket = new FlightMarket();
            flightMarket.setAirportI(flight.getOrigin());
            flightMarket.setAirportII(flight.getDestination());
            flightMarket.setDirectional(directional);
            flightMarket.setBaseFare(getBaseFare());
            flightMarket = flightMarketRepository.save(flightMarket);
        }
        return flightMarket;
    }


    /**
     * @deprecated
     * @return
     */
    private double getBaseFare(){
        return ThreadLocalRandom.current().nextDouble(60.0, 600);
    }
}
