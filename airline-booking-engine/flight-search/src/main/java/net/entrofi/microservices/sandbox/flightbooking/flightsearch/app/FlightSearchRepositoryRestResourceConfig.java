package net.entrofi.microservices.sandbox.flightbooking.flightsearch.app;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * @author Hasan COMAK
 */
@Configuration
public class FlightSearchRepositoryRestResourceConfig extends RepositoryRestConfigurerAdapter {


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Flight.class);
    }
}
