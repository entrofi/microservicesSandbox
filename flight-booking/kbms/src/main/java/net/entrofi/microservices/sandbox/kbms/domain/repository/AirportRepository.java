package net.entrofi.microservices.sandbox.kbms.domain.repository;


import net.entrofi.microservices.sandbox.kbms.domain.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AirportRepository extends JpaRepository<Airport, Long> {


    Airport findByCode(@Param("code") String code);

}
