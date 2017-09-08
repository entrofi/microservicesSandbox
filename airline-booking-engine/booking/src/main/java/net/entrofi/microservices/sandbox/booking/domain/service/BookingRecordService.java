package net.entrofi.microservices.sandbox.booking.domain.service;


import net.entrofi.microservices.sandbox.booking.app.helpers.PnrGenerator;
import net.entrofi.microservices.sandbox.booking.domain.model.BookingRecord;
import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import net.entrofi.microservices.sandbox.booking.domain.model.PassengerParam;
import net.entrofi.microservices.sandbox.booking.domain.repository.BookingRecordRepository;
import net.entrofi.microservices.sandbox.booking.env.model.OutboundInventoryUpdateMessage;
import net.entrofi.microservices.sandbox.booking.env.service.InventoryUpdatePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingRecordService.class);

    @Autowired
    private InventoryUpdatePublisher inventoryUpdatePublisher;

    @Autowired
    private BookingRecordRepository bookingRecordRepository;


    public BookingRecord book(Inventory inventory, final PassengerParam passenger,
                              final Double fare, final Date bookingDate) {
        BookingRecord bookingRecord = new BookingRecord();
        bookingRecord.setName(passenger.getName());
        bookingRecord.setSurname(passenger.getSurname());
        bookingRecord.setEmail(passenger.getEmail());
        bookingRecord.setPhoneNumber(passenger.getPhoneNumber());
        bookingRecord.setPassengerId(passenger.getPassengerId());
        bookingRecord.setBookingDate(bookingDate);
        bookingRecord.setFare(fare);
        bookingRecord.setInventory(inventory);
        bookingRecord.setPnr(PnrGenerator.generate(inventory.getFlight(), passenger));
        inventory.getBookings().add(bookingRecord);
        BookingRecord createdBooking = bookingRecordRepository.saveAndFlush(bookingRecord);
        LOGGER.info("Created boooking:" + createdBooking);
        inventoryUpdatePublisher.publishInventoryUpdate(inventory, OutboundInventoryUpdateMessage.UpdateType.BOOKING);
        return createdBooking;
    }


}
