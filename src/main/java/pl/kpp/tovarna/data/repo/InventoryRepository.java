package pl.kpp.tovarna.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.kpp.tovarna.data.entity.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Optional<Inventory> findByObjectName(String name);

    @Modifying
    @Query("Delete from Inventory i")
    void deleteAllUnrestricted();
}
