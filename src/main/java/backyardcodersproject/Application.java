package backyardcodersproject;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Logger.LoggerClass;

@SpringBootApplication
public class Application {

	LoggerClass logger = new LoggerClass();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void createHardCodedData() {
		logger.getLogger().info(this.getClass().getName() + "||Application has successfully started||");

	}
}