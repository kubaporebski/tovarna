package pl.kpp.tovarna;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AppConfig {

    public static class Profiles {
        public final static String PERIODIC = "periodic";

        public final static String KAFFKA = "kaffka";
    }
}
