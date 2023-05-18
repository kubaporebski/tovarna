package pl.kpp.tovarna.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kpp.tovarna.data.entity.Product;
import pl.kpp.tovarna.data.entity.Requirement;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

    List<Requirement> findByProduced(Product product);
}
