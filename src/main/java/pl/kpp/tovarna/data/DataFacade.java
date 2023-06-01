package pl.kpp.tovarna.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kpp.tovarna.data.repo.InventoryRepository;
import pl.kpp.tovarna.data.repo.ProductRepository;
import pl.kpp.tovarna.data.repo.QueueRepository;
import pl.kpp.tovarna.data.repo.RequirementRepository;
import pl.kpp.tovarna.process.BuildManager;

@Component
public class DataFacade {

    private final QueueRepository queueRepository;
    private final ProductRepository productRepository;
    private final RequirementRepository requirementRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public DataFacade(
            QueueRepository queueRepository,
            ProductRepository productRepository,
            RequirementRepository requirementRepository,
            InventoryRepository inventoryRepository) {
        this.queueRepository = queueRepository;
        this.productRepository = productRepository;
        this.requirementRepository = requirementRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public QueueRepository getQueueRepository() {
        return queueRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public RequirementRepository getRequirementRepository() {
        return requirementRepository;
    }

    public InventoryRepository getInventoryRepository() {
        return inventoryRepository;
    }
}
