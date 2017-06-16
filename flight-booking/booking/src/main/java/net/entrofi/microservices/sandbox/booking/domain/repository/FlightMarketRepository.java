package net.entrofi.microservices.sandbox.booking.domain.repository;


import net.entrofi.microservices.sandbox.booking.domain.model.FlightMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightMarketRepository extends JpaRepository<FlightMarket, FlightMarket.FlightMarketId> {

    @Query("select fm from FlightMarket fm where fm.directional = :#{#flightMarketId.directional} " +
            "and ( (fm.airportI = :#{#flightMarketId.airportI}  and fm.airportII = :#{#flightMarketId.airportII})  " +
            "      or (fm.airportI = :#{#flightMarketId.airportI} and fm.airportII = :#{#flightMarketId.airportII}) )")
    FlightMarket findOneByFlightMarketId(@Param("flightMarketId")FlightMarket.FlightMarketId flightMarketId);


}
