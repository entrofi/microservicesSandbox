package net.entrofi.microservices.sandbox.booking.domain.repository;

import net.entrofi.microservices.sandbox.booking.domain.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
