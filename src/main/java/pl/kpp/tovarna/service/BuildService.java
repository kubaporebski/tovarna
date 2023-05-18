package pl.kpp.tovarna.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.kpp.tovarna.data.classes.BuildState;
import pl.kpp.tovarna.data.repo.QueueRepository;
import pl.kpp.tovarna.data.repo.RequirementRepository;
import pl.kpp.tovarna.tools.Loggers;

@Service
public class BuildService {

    private final static Logger logger = Loggers.forEnclosingClass();

    private final RequirementRepository requirementRepository;
    private final QueueRepository queueRepository;

    @Autowired
    public BuildService(RequirementRepository requirementRepository, QueueRepository queueRepository) {
        this.requirementRepository = requirementRepository;
        this.queueRepository = queueRepository;
    }

    @Scheduled(fixedDelay = 2500)
    public void runBuildQueue() {
        logger.info("Runnin' buildin' queue... ");

        queueRepository.findByState(BuildState.NEW).forEach(q -> {
            logger.info("Found new element in the queue: " + q);

            q.setState(BuildState.IN_PROGRESS);
            queueRepository.save(q);

            var reqs = requirementRepository.findByProduced(q.getBuilt());

            reqs.stream().forEach(req -> {
                // req.getRequired()
            });
            logger.info("Requirements are: " + reqs);

        });


    }

}
