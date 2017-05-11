package net.entrofi.microservices.sandbox.fms.domain.repository;


import net.entrofi.microservices.sandbox.fms.domain.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Long> {

    List<Crew> findByRole(String role);

    List<Crew> findByRoleIn(List<String> roles);
}
