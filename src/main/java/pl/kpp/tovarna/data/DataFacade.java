package pl.kpp.tovarna.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kpp.tovarna.data.repo.ProductRepository;
import pl.kpp.tovarna.data.repo.QueueRepository;
import pl.kpp.tovarna.data.repo.RequirementRepository;
import pl.kpp.tovarna.process.BuildManager;

@Component
public class DataFacade {

    private final QueueRepository queueRepository;
    private final ProductRepository productRepository;
    private final RequirementRepository requirementRepository;

    @Autowired
    public DataFacade(
            QueueRepository queueRepository,
            ProductRepository productRepository,
            RequirementRepository requirementRepository) {
        this.queueRepository = queueRepository;
        this.productRepository = productRepository;
        this.requirementRepository = requirementRepository;
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
}
