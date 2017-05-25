package net.entrofi.microservices.sandbox.fms.app;


import net.entrofi.microservices.sandbox.fms.app.helpers.FlightIdToStringConverter;
import net.entrofi.microservices.sandbox.fms.app.helpers.StringToFlightIdConverter;
import net.entrofi.microservices.sandbox.fms.domain.entity.Flight;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class FMSRepositoryRestResourceConfig extends RepositoryRestConfigurerAdapter {


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Flight.class);
//        config.exposeIdsFor(Aircraft.class);
    }

    @Override
    public void configureConversionService(ConfigurableConversionService conversionService) {
        conversionService.addConverter(new FlightIdToStringConverter());
        conversionService.addConverter(new StringToFlightIdConverter());
    }

//    @Bean
//    public ConversionService conversionService() {
//        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
//        Set<Converter> converters = new HashSet<>();
//        StringToFlightIdConverter stringToFlightIdConverter = new StringToFlightIdConverter();
//        FlightIdToStringConverter flightIdToStringConverter = new FlightIdToStringConverter();
//        converters.add(stringToFlightIdConverter);
//        converters.add(flightIdToStringConverter);
//        factoryBean.setConverters(converters);
//        return factoryBean.getObject();
//    }

}
