package pl.kpp.tovarna.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.kpp.tovarna.AppConfig;
import pl.kpp.tovarna.data.DataFacade;
import pl.kpp.tovarna.data.entity.Queue;
import pl.kpp.tovarna.process.BuildManager;
import pl.kpp.tovarna.tools.Loggers;
import pl.kpp.tovarna.tools.RunTools;

@Service
@Profile(AppConfig.Profiles.PERIODIC)
public class PeriodicBuildService implements IBuildService {

    private final static Logger logger = Loggers.forEnclosingClass();

    private final DataFacade dataFacade;
    private final BuildManager buildManager;

    @Autowired
    public PeriodicBuildService(DataFacade dataFacade, BuildManager buildManager) {
        this.dataFacade = dataFacade;
        this.buildManager = buildManager;
    }

    @Scheduled(fixedDelay = 2500)
    public void runBuildQueue() {

        if (!RunTools.isReady)
            return;

        logger.info("Runnin' buildin' queue... ");
        dataFacade.getQueueRepository()
                .lookForReadyToProcess()
                .forEach(this::runBuildQueueItem);
    }

    private void runBuildQueueItem(Queue queueItem) {
        logger.info("Found new element in the queue: " + queueItem);
        buildManager.build(queueItem);
    }

}
