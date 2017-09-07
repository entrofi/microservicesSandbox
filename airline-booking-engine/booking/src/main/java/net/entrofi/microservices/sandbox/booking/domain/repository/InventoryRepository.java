package net.entrofi.microservices.sandbox.booking.domain.repository;

import net.entrofi.microservices.sandbox.booking.domain.model.Flight;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@RepositoryRestResource
public interface InventoryRepository extends JpaRepository<Inventory, Flight> {

    @RestResource(path = "flight", rel = "flight")
    Inventory findByFlight_FlightNumberAndFlight_originAndFlight_destinationAndFlight_date(@Param("flightNumber")
                                                                                                   String flightNumber,
                                                                                          @Param("origin")
                                                                                           String origin,
                                                                                           @Param("destination")
                                                                                           String destination,
                                                                                           @Param("date")
                                                                                           @DateTimeFormat(iso =
                                                                                                   DateTimeFormat
                                                                                                           .ISO
                                                                                                           .DATE_TIME)
                                                                                           Date date);
}
