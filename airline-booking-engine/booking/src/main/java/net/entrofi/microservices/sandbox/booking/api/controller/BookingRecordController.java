package net.entrofi.microservices.sandbox.booking.api.controller;

import net.entrofi.microservices.sandbox.booking.api.resource.BookingRequest;
import net.entrofi.microservices.sandbox.booking.domain.model.BookingRecord;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import net.entrofi.microservices.sandbox.booking.domain.model.PassengerParam;
import net.entrofi.microservices.sandbox.booking.domain.service.BookingRecordService;
import net.entrofi.microservices.sandbox.booking.domain.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.hateoas.mvc.BasicLinkBuilder.linkToCurrentMapping;


@RepositoryRestController
@ExposesResourceFor(BookingRecord.class)
public class BookingRecordController implements RepositoryRestResourceExtensionController{

    public static final String ROOT_REQUEST_MAPPING = "bookingRecords";

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BookingRecordService bookingRecordService;

    @PostMapping(name = "/book")
    public HttpEntity<Resource<BookingRecord>> book(@RequestBody BookingRequest bookingRequest) {
        Inventory inventory = inventoryService.findByIdString(bookingRequest.getInventoryId());
        PassengerParam passenger = new PassengerParam(bookingRequest.getPassengerId(),
                    bookingRequest.getPassengerName(), bookingRequest.getPassengerSurname());
        BookingRecord bookingRecord = bookingRecordService.book(inventory, passenger,
                bookingRequest.getFare(), bookingRequest.getBookingDate());

        Resource<BookingRecord> bookingRecordResource = new Resource<BookingRecord>(bookingRecord,
                getBaseLink().slash(bookingRecord.getId()).withSelfRel());

        return ResponseEntity.ok(bookingRecordResource);
    }

    @Override
    public LinkBuilder getBaseLink() {
        return linkToCurrentMapping().slash(ROOT_REQUEST_MAPPING);
    }

}
