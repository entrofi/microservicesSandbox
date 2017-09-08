package net.entrofi.microservices.sandbox.flightbooking.web.controller;

import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebBookingInfo;
import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebBookingRecord;
import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebFlight;
import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebFlightSearchQuery;
import net.entrofi.microservices.sandbox.flightbooking.web.env.model.BookingBookingRequest;
import net.entrofi.microservices.sandbox.flightbooking.web.env.model.BookingFlight;
import net.entrofi.microservices.sandbox.flightbooking.web.env.service.BookingConsumer;
import net.entrofi.microservices.sandbox.flightbooking.web.env.service.FlightSearchConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/flights")
public class WebFlightController {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebFlightController.class);

    @Autowired
    private FlightSearchConsumer flightSearchConsumer;

    @Autowired
    private BookingConsumer bookingConsumer;

    @PostMapping(value = "/search")
    public String search(@ModelAttribute("flightSearchQuery") WebFlightSearchQuery webFlightSearchQuery, Model model) {
        LOGGER.info("Searching for flights with " + webFlightSearchQuery);
        List<WebFlight> flights = flightSearchConsumer.findFlights(webFlightSearchQuery);
        model.addAttribute("flightList", flights);
        model.addAttribute("flightSearchQuery", webFlightSearchQuery);
        return "search-flight";
    }

    @GetMapping(value = "/book/{id}")
    public String book(@PathVariable String id, Model model) {
        WebFlight flight = flightSearchConsumer.getFlightDetail(id);
        model.addAttribute("bookingInfo", new WebBookingInfo(flight));
        return "book";
    }


    @PostMapping(value = "/book")
    public String bookFlight(@ModelAttribute WebBookingInfo bookingInfo, Model model) {
        WebBookingRecord bookingRecord = bookingConsumer.book(bookingInfo);
        model.addAttribute("bookingRecord", bookingRecord);
        return "booking-result";
    }


}
