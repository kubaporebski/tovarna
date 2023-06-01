package pl.kpp.tovarna.tools;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.kpp.tovarna.data.DataFacade;
import pl.kpp.tovarna.data.classes.BuildState;
import pl.kpp.tovarna.data.entity.Product;
import pl.kpp.tovarna.data.entity.Queue;
import pl.kpp.tovarna.data.entity.Requirement;
import pl.kpp.tovarna.data.repo.ProductRepository;
import pl.kpp.tovarna.data.repo.QueueRepository;
import pl.kpp.tovarna.data.repo.RequirementRepository;

import java.util.LinkedList;
import java.util.List;

@Component
public class RunTools {

    private final static Logger logger = Loggers.forEnclosingClass();

    public static boolean isReady;

    private final DataFacade dataFacade;

    private final List<Product> products = new LinkedList<>();

    private final List<Requirement> requirements = new LinkedList<>();

    private final List<Queue> queues = new LinkedList<>();

    @Autowired
    public RunTools(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    @Transactional
    public void prepareSampleDatabase() {

        logger.info("Clearing the database");

        dataFacade.getRequirementRepository().deleteAllUnrestricted();
        dataFacade.getInventoryRepository().deleteAllUnrestricted();
        dataFacade.getQueueRepository().deleteAllUnrestricted();
        dataFacade.getProductRepository().deleteAllUnrestricted();

        logger.info("Adding some sample data... ");
        products.addAll(List.of(
                prod("Coal", "Energy basic source"),
                prod("Iron ore", "Source of metals"),
                prod("Wood", "For good ol' building"),
                prod("Plastic", "Just a simple raw material"),
                prod("Coal plant", "For powering of factories"),
                prod("Cooldroid factory", "Factory of smartphones"),
                prod("Coolphone", "A smartphone")
        ));
        requirements.addAll(List.of(
                req(find(products, "Coal plant"), find(products, "Coal")),
                req(find(products, "Cooldroid factory"), find(products, "Coal plant")),
                req(find(products, "Coolphone"), find(products, "Cooldroid factory")),
                req(find(products, "Coolphone"), find(products, "Plastic"))
        ));
        queues.addAll(List.of(
                qu(find(products, "Coolphone"))
        ));

        dataFacade.getProductRepository().saveAll(products);
        dataFacade.getRequirementRepository().saveAll(requirements);
        dataFacade.getQueueRepository().saveAll(queues);

        isReady = true;
    }

    // TODO @Scheduled(fixedRate = 5000)
    public void addItemToQueueFromTimeToTime() {
        /*
        logger.info("Let's try to add a new item to the queue");
        if (Math.random() < 0.5)
            return;

        var randomProduct = ListUtils.getRandom(products);
        var newQueueItem = new Queue();
        newQueueItem.setState(BuildState.NEW);
        newQueueItem.setBuilt(randomProduct);

        logger.info("OK, can do. Adding a new item into the queue" + newQueueItem);
        dataFacade.getQueueRepository().save(newQueueItem);
        */
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

    private static Queue qu(Product productToBuild) {
        return new Queue(productToBuild, BuildState.NEW);
    }

}
