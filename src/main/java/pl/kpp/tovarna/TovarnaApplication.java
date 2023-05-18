package pl.kpp.tovarna;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.kpp.tovarna.tools.Loggers;
import pl.kpp.tovarna.tools.RunTools;

@SpringBootApplication
public class TovarnaApplication implements ApplicationRunner {

	private final static Logger logger = Loggers.forEnclosingClass();

	@Autowired
	RunTools runTools;

	public static void main(String[] args) {
		SpringApplication.run(TovarnaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		runTools.prepareSampleDatabase();

		logger.info("TOVARNA is UP and RUNNING!!!");

	}
}
