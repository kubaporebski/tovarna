package pl.kpp.tovarna.tools;

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

    private final DataFacade dataFacade;

    private final List<Product> products = new LinkedList<>();

    @Autowired
    public RunTools(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void prepareSampleDatabase() {

        logger.info("Clearing the database");

        dataFacade.getQueueRepository().deleteAll();
        dataFacade.getRequirementRepository().deleteAll();
        dataFacade.getProductRepository().deleteAll();

        logger.info("Adding some sample data... ");
        products.addAll(List.of(
                prod("Coal", "Energy basic source"), prod("Iron ore", "Source of metals"),
                prod("Wood", "For good ol' building"), prod("Plastic", "Universal"),
                prod("Coal plant", "For powering of factories"),
                prod("Cooldroid factory", "Factory of smartphones"),
                prod("Coolphone", "A smartphone")
        ));
        var requirements = List.of(
                req(find(products, "Coal plant"), find(products, "Coal"))
        );

        dataFacade.getProductRepository().saveAll(products);
        dataFacade.getRequirementRepository().saveAll(requirements);
    }

    @Scheduled(fixedRate = 4000)
    public void addItemToQueueFromTimeToTime() {
        logger.info("Let's try to add a new item to the queue");
        if (Math.random() < 0.5)
            return;

        var randomProduct = ListUtils.getRandom(products);
        var newQueueItem = new Queue();
        newQueueItem.setState(BuildState.NEW);
        newQueueItem.setBuilt(randomProduct);

        logger.info("OK, can do. Adding a new item into the queue" + newQueueItem);
        dataFacade.getQueueRepository().save(newQueueItem);
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
