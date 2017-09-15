package net.entrofi.microservices.sandbox.flightbooking.web.env.service;

import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.PassengerInfo;
import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebBookingInfo;
import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebBookingRecord;
import net.entrofi.microservices.sandbox.flightbooking.web.domain.model.WebFlight;
import net.entrofi.microservices.sandbox.flightbooking.web.env.model.BookingBookingRecord;
import net.entrofi.microservices.sandbox.flightbooking.web.env.model.BookingBookingRequest;
import net.entrofi.microservices.sandbox.flightbooking.web.env.model.BookingFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Hasan COMAK
 */
@Service
public class BookingConsumer {

    @Value("${net.entrofi.microservices.sandbox.booking.endpoint}")
    private String bookingEndPoint = "http://BOOKING";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FlightSearchConsumer flightSearchConsumer;

    public WebBookingRecord book(WebBookingInfo bookingInfo) {
        WebFlight flight = flightSearchConsumer.getFlightDetail(bookingInfo.getFlight().getId());
        BookingBookingRequest bookingRequest = getBookingBookingRequest(bookingInfo, flight);
        HttpEntity<BookingBookingRequest> requestEntity = new HttpEntity<>(bookingRequest);
        ResponseEntity<BookingBookingRecord> responseEntity = restTemplate.exchange(bookingEndPoint +
                        "/bookingRecords/book",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<BookingBookingRecord>() { });
        BookingBookingRecord bookingRecord = responseEntity.getBody();
        return getWebBookingRecord(flight, bookingRecord);
    }

    private BookingBookingRequest getBookingBookingRequest(WebBookingInfo bookingInfo, WebFlight flight) {
        BookingFlight bookingFlight = new BookingFlight(flight.getOrigin().getCode(),
                flight.getDestination().getCode(),
                flight.getFlightNumber(),
                getUTCDate(flight.getDate()));
        return new BookingBookingRequest(bookingFlight, Calendar.getInstance().getTime(),
                bookingInfo.getPassengers().get(0), flight.getFare());
    }

    private WebBookingRecord getWebBookingRecord(WebFlight flight, BookingBookingRecord bookingRecord) {
        WebBookingRecord webBookingRecord = new WebBookingRecord();
        webBookingRecord.setFlight(flight);
        webBookingRecord.setPassenger(new PassengerInfo());
        webBookingRecord.getPassenger().setName(bookingRecord.getName());
        webBookingRecord.getPassenger().setSurname(bookingRecord.getSurname());
        webBookingRecord.getPassenger().setPassengerId(bookingRecord.getPassengerId());
        webBookingRecord.setPnr(bookingRecord.getPnr());
        webBookingRecord.setFare(bookingRecord.getFare());
        return webBookingRecord;
    }

    private Date getUTCDate(Date date) {
        return new Date(date.getTime() + TimeZone.getDefault().getOffset(date.getTime()));
    }

}
