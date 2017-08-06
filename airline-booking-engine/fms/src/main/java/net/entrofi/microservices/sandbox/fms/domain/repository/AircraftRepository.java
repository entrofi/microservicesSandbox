package net.entrofi.microservices.sandbox.fms.domain.repository;


import net.entrofi.microservices.sandbox.fms.domain.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
