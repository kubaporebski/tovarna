package pl.kpp.tovarna.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.kpp.tovarna.TovarnaApplication;
import pl.kpp.tovarna.data.DataFacade;
import pl.kpp.tovarna.data.classes.BuildState;
import pl.kpp.tovarna.data.entity.Queue;
import pl.kpp.tovarna.data.repo.QueueRepository;
import pl.kpp.tovarna.data.repo.RequirementRepository;
import pl.kpp.tovarna.process.BuildManager;
import pl.kpp.tovarna.tools.Loggers;

import javax.xml.crypto.Data;

@Service
public class BuildService {

    private final static Logger logger = Loggers.forEnclosingClass();

    private final DataFacade dataFacade;
    private final BuildManager buildManager;

    @Autowired
    public BuildService(DataFacade dataFacade, BuildManager buildManager) {
        this.dataFacade = dataFacade;
        this.buildManager = buildManager;
    }

    @Scheduled(fixedDelay = 2500)
    public void runBuildQueue() {
        logger.info("Runnin' buildin' queue... ");
        dataFacade.getQueueRepository()
                .findByState(BuildState.NEW)
                .forEach(this::runBuildQueueItem);
    }

    private void runBuildQueueItem(Queue queueItem) {
        logger.info("Found new element in the queue: " + queueItem);
        buildManager.build(queueItem);
    }

}
