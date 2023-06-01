package pl.kpp.tovarna.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kpp.tovarna.data.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
