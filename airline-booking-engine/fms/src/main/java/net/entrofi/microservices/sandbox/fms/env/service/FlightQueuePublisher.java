package net.entrofi.microservices.sandbox.fms.env.service;


import net.entrofi.microservices.sandbox.fms.domain.entity.Flight;
import net.entrofi.microservices.sandbox.fms.env.model.OutboundFlightMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightQueuePublisher {


    private static final Logger LOGGER = LoggerFactory.getLogger(FlightQueuePublisher.class);

    @Autowired
    private FanoutExchange flightExchange;

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void send(Flight flight) {
        LOGGER.info("Sending flight to the queue..." + flight.getId().toString());
        OutboundFlightMessage flightMessage = new OutboundFlightMessage(flight.getId().getFlightNumber(),
                flight.getId().getOriginDate(),
                flight.getAircraft().getCapacity(),
                flight.getId().getDepartureAirportCode(),
                flight.getId().getArrivalAirportCode());
        rabbitMessagingTemplate.convertAndSend(flightExchange.getName(), "", flightMessage);
    }
}
