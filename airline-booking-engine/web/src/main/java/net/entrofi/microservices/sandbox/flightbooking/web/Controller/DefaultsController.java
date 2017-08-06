package net.entrofi.microservices.sandbox.flightbooking.web.controller;


import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebFlightSearchQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultsController.class);

    @GetMapping("/")
    public String index() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/flights/search")
    public String searchFlights(Model model) {
        LOGGER.info("Rendering flight search page");
        WebFlightSearchQuery webFlightSearchQuery = new WebFlightSearchQuery();
        model.addAttribute("flightSearchQuery", webFlightSearchQuery);
        return "search-flight";
    }

    @GetMapping("/flights/checkin")
    public String checkin() {
        return "checkin";
    }
}
