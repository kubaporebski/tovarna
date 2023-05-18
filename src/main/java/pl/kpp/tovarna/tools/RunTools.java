package pl.kpp.tovarna.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kpp.tovarna.data.entity.Product;
import pl.kpp.tovarna.data.entity.Requirement;
import pl.kpp.tovarna.data.repo.ProductRepository;
import pl.kpp.tovarna.data.repo.QueueRepository;
import pl.kpp.tovarna.data.repo.RequirementRepository;

import java.util.List;

@Component
public class RunTools {

    private final static Logger logger = Loggers.forEnclosingClass();

    private final ProductRepository productRepository;
    private final RequirementRepository requirementRepository;
    private final QueueRepository queueRepository;

    @Autowired
    public RunTools(ProductRepository productRepository,
                    RequirementRepository requirementRepository,
                    QueueRepository queueRepository) {

        this.productRepository = productRepository;
        this.requirementRepository = requirementRepository;
        this.queueRepository = queueRepository;

    }

    public void prepareSampleDatabase() {

        logger.info("Clearing the database");

        queueRepository.deleteAll();
        requirementRepository.deleteAll();
        productRepository.deleteAll();

        logger.info("Adding some sample data... ");
        var products = List.of(
                prod("Coal", "Energy basic source"), prod("Iron ore", "Source of metals"),
                prod("Wood", "For good ol' building"), prod("Plastic", "Universal"),
                prod("Coal plant", "For powering of factories"),
                prod("Cooldroid factory", "Factory of smartphones"),
                prod("Coolphone", "A smartphone")
        );
        var requirements = List.of(
                req(find(products, "Coal plant"), find(products, "Coal"))
        );

        productRepository.saveAll(products);
        requirementRepository.saveAll(requirements);
    }

    private Product find(List<Product> products, String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow();
    }

    private static Product prod(String name, String description) {
        return new Product(name, description);
    }

    private static Requirement req(Product produced, Product required) {
        return new Requirement(produced, required);
    }

}
