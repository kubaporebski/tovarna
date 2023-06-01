package pl.kpp.tovarna.process;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import pl.kpp.tovarna.data.DataFacade;
import pl.kpp.tovarna.data.classes.BuildState;
import pl.kpp.tovarna.data.entity.Queue;
import pl.kpp.tovarna.data.repo.QueueRepository;
import pl.kpp.tovarna.tools.Loggers;

@Component
public class BuildManager {

    private static final Logger logger = Loggers.forEnclosingClass();

    private final DataFacade dataFacade;

    public BuildManager(DataFacade dataFacade) {
        this.dataFacade = dataFacade;
    }

    public void build(Queue queueItem) {
        queueItem.setState(BuildState.IN_PROGRESS);
        dataFacade.getQueueRepository().save(queueItem);

        var reqs = dataFacade.getRequirementRepository().findByProduced(queueItem.getBuilt());

        reqs.stream().forEach(req -> {
            // req.getRequired()
        });
        logger.info("Requirements are: " + reqs);

        if (reqs.size() == 0) {
            // put it inventory
        } else {
            //
        }
    }
}
