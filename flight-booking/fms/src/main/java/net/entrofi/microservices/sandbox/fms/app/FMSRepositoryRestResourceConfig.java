package net.entrofi.microservices.sandbox.fms.app;


import net.entrofi.microservices.sandbox.fms.domain.entity.Aircraft;
import net.entrofi.microservices.sandbox.fms.domain.entity.Flight;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class FMSRepositoryRestResourceConfig extends RepositoryRestConfigurerAdapter {


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Flight.class);
        config.exposeIdsFor(Aircraft.class);
    }



}
