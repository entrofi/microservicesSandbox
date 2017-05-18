package net.entrofi.microservices.sandbox.booking.domain.repository;

import net.entrofi.microservices.sandbox.booking.domain.model.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO add javadoc
 * Created on 09/05/2017.
 */
public interface BookingRecordRepository extends JpaRepository<BookingRecord, Long> {
}
