package net.entrofi.microservices.sandbox.kbms.domain.repository;

import net.entrofi.microservices.sandbox.kbms.domain.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RegionRepository extends JpaRepository<Region, Long> {
}
