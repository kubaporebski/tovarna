package pl.kpp.tovarna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BuildService {

    private final static Logger logger = LoggerFactory.getLogger(BuildService.class);

    @Scheduled(fixedDelay = 1000)
    public void runBuildQueue() {
        logger.info("Runnin' buildin' queue... ");
    }

}
