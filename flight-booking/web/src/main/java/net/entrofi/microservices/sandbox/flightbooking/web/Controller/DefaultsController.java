package net.entrofi.microservices.sandbox.flightbooking.web.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultsController {

    @GetMapping("/")
    public String index() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/flights/search")
    public String searchFlights() {
        return "search-flight";
    }

    @GetMapping("/flights/checkin")
    public String checkin() {
        return "checkin";
    }
}
