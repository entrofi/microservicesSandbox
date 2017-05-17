
package net.entrofi.microservices.sandbox.business.repository;

import net.entrofi.microservices.sandbox.business.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.swing.text.html.Option;
import java.util.Optional;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(@Param("name")String name);

    Optional<Customer> findByEmail(@Param("email") String email);
}
