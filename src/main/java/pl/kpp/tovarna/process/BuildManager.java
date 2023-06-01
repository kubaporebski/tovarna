package pl.kpp.tovarna.process;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kpp.tovarna.data.DataFacade;
import pl.kpp.tovarna.data.classes.BuildState;
import pl.kpp.tovarna.data.entity.Inventory;
import pl.kpp.tovarna.data.entity.Queue;
import pl.kpp.tovarna.data.repo.QueueRepository;
import pl.kpp.tovarna.tools.Loggers;

@Component
public class BuildManager {

    private static final Logger logger = Loggers.forEnclosingClass();

    private final DataFacade dataFacade;

    @Autowired
    public BuildManager(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void build(Queue queueItem) {
        queueItem.setState(BuildState.IN_PROGRESS);
        dataFacade.getQueueRepository().save(queueItem);

        var productToBuild = queueItem.getBuilt();
        var reqs = dataFacade.getRequirementRepository().findByProduced(productToBuild);

        logger.info("Will build: " + productToBuild);

        if (reqs.size() == 0) {
            logger.info("No requirements to build it, so I'm putting it into the inventory right away.");

            // put it into the inventory
            var inventoryItem = new Inventory();
            inventoryItem.setObject(queueItem.getBuilt());
            dataFacade.getInventoryRepository().save(inventoryItem);

            queueItem.setState(BuildState.DONE);
            dataFacade.getQueueRepository().save(queueItem);

        } else {

            logger.info("Required products: " + reqs);
            reqs.forEach(req -> {
                var required = req.getRequired();

                var requiredInInventory = dataFacade.getInventoryRepository().findByObjectName(required.getName());
                if (requiredInInventory.isPresent())
                    return;

                queueItem.setState(BuildState.WAIT_FOR_REQUIREMENT);
                dataFacade.getQueueRepository().save(queueItem);

                var newQueueItem = new Queue();
                newQueueItem.setBuilt(required);
                newQueueItem.setState(BuildState.NEW);
                newQueueItem.setParentQueue(queueItem);
                dataFacade.getQueueRepository().save(newQueueItem);
            });
        }
    }
}
