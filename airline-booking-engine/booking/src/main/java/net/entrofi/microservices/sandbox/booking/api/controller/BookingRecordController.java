package net.entrofi.microservices.sandbox.booking.api.controller;

import net.entrofi.microservices.sandbox.booking.api.resource.BookingRequest;
import net.entrofi.microservices.sandbox.booking.app.helpers.InventoryIdHelper;
import net.entrofi.microservices.sandbox.booking.domain.model.BookingRecord;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import net.entrofi.microservices.sandbox.booking.domain.model.PassengerParam;
import net.entrofi.microservices.sandbox.booking.domain.service.BookingRecordService;
import net.entrofi.microservices.sandbox.booking.domain.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.BasicLinkBuilder.linkToCurrentMapping;


@RestController
@RequestMapping("/bookingRecords")
public class BookingRecordController implements RepositoryRestResourceExtensionController{

    public static final String ROOT_REQUEST_MAPPING = "bookingRecords";

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BookingRecordService bookingRecordService;

    @PostMapping(name = "/book")
    public HttpEntity<Resource<BookingRecord>> book(@RequestBody BookingRequest bookingRequest) {
        final String flightId = InventoryIdHelper.convertFlightToString(bookingRequest.getFlight());
        Inventory inventory = inventoryService.findByIdString(flightId);

        PassengerParam passenger = new PassengerParam(bookingRequest.getPassengerId(),
                bookingRequest.getPassengerName(),
                bookingRequest.getPassengerSurname(),
                bookingRequest.getEmail(),
                bookingRequest.getPhoneNumber());

        BookingRecord bookingRecord = bookingRecordService.book(inventory, passenger,
                bookingRequest.getFare(), bookingRequest.getBookingDate());

        Resource<BookingRecord> bookingRecordResource = new Resource<>(bookingRecord,
                getBaseLink().slash(bookingRecord.getId()).withSelfRel(),
                getBaseLink().slash(bookingRecord.getId()).slash("inventory").withRel("inventory"));

        return ResponseEntity.ok(bookingRecordResource);
    }

    @Override
    public LinkBuilder getBaseLink() {
        return linkToCurrentMapping().slash(ROOT_REQUEST_MAPPING);
    }

}
