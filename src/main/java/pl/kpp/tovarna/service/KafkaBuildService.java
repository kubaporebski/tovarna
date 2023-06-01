package pl.kpp.tovarna.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.kpp.tovarna.AppConfig;

@Service
@Profile(AppConfig.Profiles.KAFFKA)
public class KafkaBuildService implements IBuildService {

    @Override
    public void runBuildQueue() {

    }
}
