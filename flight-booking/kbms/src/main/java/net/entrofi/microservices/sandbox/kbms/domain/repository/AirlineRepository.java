
package net.entrofi.microservices.sandbox.kbms.domain.repository;


import net.entrofi.microservices.sandbox.kbms.domain.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AirlineRepository extends JpaRepository<Airline, Long>{
}
