package net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.service;

import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.model.Flight;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.domain.repository.FlightRepository;
import net.entrofi.microservices.sandbox.flightbooking.flightsearch.env.model.FlightQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;


@Component
public class FlightServiceImpl implements FlightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> findAvailableFlights(FlightQuery flightQuery) {
        List<Date> startEnd = getDayStartToEnd(flightQuery.getDate());
        List<Flight> flights = flightRepository.findByOrigin_DivisionNameLikeAndDestination_DivisionNameLikeAndDateBetween(
                flightQuery.getOriginDivision(),
                flightQuery.getDestinationDivision(),
                startEnd.get(0), startEnd.get(1));
        LOGGER.info("Found " + flights.size() + " for query: " + flightQuery.toString());
        return flights.stream().filter(flight -> flight.getAvailableSeats() > 0).collect(Collectors.toList());
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void updateInventory(String flightNumber, Date flightDate, int availableSeats, Double fare)
            throws NoSuchFlightException  {
        Flight flight = flightRepository.findByDateAndFlightNumber(flightDate, flightNumber);
        if(flight == null ) {
            LOGGER.info("Unable to find flight with flight number: " + flightNumber + " on " + flightDate);
            throw new NoSuchFlightException();
        } else {
            LOGGER.info("Updating available seats to " + availableSeats + " for flight " + flight.toString());
            flight.setAvailableSeats(availableSeats);
            flight.setFare(fare);
            flightRepository.save(flight);
        }
    }


    private List<Date> getDayStartToEnd(final Date referenceDate) {
        if(referenceDate == null) {
            return Collections.EMPTY_LIST;
        }
        List<Date> dates = new ArrayList<>(2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(referenceDate.getTime());



        dates.add(0, getDateForHourAndMins(referenceDate, 0, 0));
        dates.add(1, getDateForHourAndMins(referenceDate, 23, 59));

        return dates;
    }

    private Date getDateForHourAndMins(final Date referenceDate, final int hours, final int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(referenceDate.getTime());

        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        final Date date = new Date(calendar.getTimeInMillis());
        return  date;
    }
}
