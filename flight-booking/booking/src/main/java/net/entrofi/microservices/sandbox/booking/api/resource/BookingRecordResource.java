package net.entrofi.microservices.sandbox.booking.api.resource;


import net.entrofi.microservices.sandbox.booking.domain.model.BookingRecord;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

public class BookingRecordResource extends ResourceSupport {

    private Long id;

    private String origin;

    private String destination;

    private String flightNumber;

    private Date date;

    private Date bookingDate;

    private Double fare;

    private String passengerId;

    private String name;

    private String surname;


    public BookingRecordResource map(BookingRecord bookingRecord) {
      ImplicitResourceMapper<BookingRecordResource, BookingRecord> mapper = (BookingRecord record) -> {
          ModelMapper modelMapper = new ModelMapper();
          return modelMapper.map(record, BookingRecordResource.class);
        };
      return mapper.map(bookingRecord);
    }

    public BookingRecord reverseMap() {
        ImplicitResourceMapper<BookingRecord, BookingRecordResource> mapper = (BookingRecordResource resoure) -> {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(resoure, BookingRecord.class);
        };
        return mapper.map(this);
    }
}
