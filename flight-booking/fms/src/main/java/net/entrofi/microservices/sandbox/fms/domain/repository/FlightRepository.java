
package net.entrofi.microservices.sandbox.fms.domain.repository;


import net.entrofi.microservices.sandbox.fms.domain.entity.Flight;
import net.entrofi.microservices.sandbox.fms.domain.entity.FlightId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FlightRepository extends JpaRepository<Flight, FlightId> {
}
