package net.entrofi.microservices.sandbox.booking.app;


import net.entrofi.microservices.sandbox.booking.app.helpers.InventoryIdToStringConverter;
import net.entrofi.microservices.sandbox.booking.app.helpers.StringToInventoryIdConverter;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


@Configuration
public class BookingRepositoryRestResourceConfig extends RepositoryRestConfigurerAdapter{

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Inventory.class);
//        config.exposeIdsFor(Aircraft.class);
    }

    @Override
    public void configureConversionService(ConfigurableConversionService conversionService) {
        conversionService.addConverter(new InventoryIdToStringConverter());
        conversionService.addConverter(new StringToInventoryIdConverter());
    }
}
