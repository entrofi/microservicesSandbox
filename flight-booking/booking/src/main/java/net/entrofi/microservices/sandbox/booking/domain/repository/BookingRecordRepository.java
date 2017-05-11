/*
 * Copyright 2003-2016 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */
package net.entrofi.microservices.sandbox.booking.domain.repository;

import net.entrofi.microservices.sandbox.booking.domain.model.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO add javadoc
 * Created on 09/05/2017.
 */
public interface BookingRecordRepository extends JpaRepository<BookingRecord, Long> {
}
