
package net.entrofi.microservices.sandbox.booking.domain.repository;

import net.entrofi.microservices.sandbox.booking.domain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory, Long>{
}
