package pl.kpp.tovarna.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kpp.tovarna.data.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
