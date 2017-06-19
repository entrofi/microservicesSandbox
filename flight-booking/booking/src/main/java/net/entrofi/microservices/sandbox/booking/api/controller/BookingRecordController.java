package net.entrofi.microservices.sandbox.booking.api.controller;

import net.entrofi.microservices.sandbox.booking.api.resource.BookingRequest;
import net.entrofi.microservices.sandbox.booking.domain.model.BookingRecord;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import net.entrofi.microservices.sandbox.booking.domain.model.PassengerParam;
import net.entrofi.microservices.sandbox.booking.domain.service.BookingRecordService;
import net.entrofi.microservices.sandbox.booking.domain.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/bookingrecords")
@ExposesResourceFor(BookingRecord.class)
public class BookingRecordController {


    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BookingRecordService bookingRecordService;

    public HttpEntity<BookingRecord> book(@RequestBody BookingRequest bookingRequest) {
        Inventory inventory = inventoryService.findByIdString(bookingRequest.getInventoryId());
        PassengerParam passenger = new PassengerParam(bookingRequest.getPassengerId(),
                    bookingRequest.getPassengerName(), bookingRequest.getPassengerSurname());
        BookingRecord bookingRecord = bookingRecordService.book(inventory, passenger,
                    bookingRequest.getFare(), bookingRequest.getBookingDate());


    }


}
