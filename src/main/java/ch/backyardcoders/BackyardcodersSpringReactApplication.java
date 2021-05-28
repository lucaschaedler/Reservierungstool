package ch.backyardcoders;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ch.backyardcoders.mgmt.business.VerificationClass;
import ch.backyardcoders.mgmt.persistence.Authorization;
import ch.backyardcoders.mgmt.persistence.User;
import ch.backyardcoders.mgmt.persistence.UserRepository;

@SpringBootApplication
public class BackyardcodersSpringReactApplication {

	@Autowired
	private VerificationClass verificationClass;

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BackyardcodersSpringReactApplication.class.getSimpleName());

	public static void main(String[] args) {
		SpringApplication.run(BackyardcodersSpringReactApplication.class, args);

	}

	@PostConstruct
	public void createHardCodedData() {
		LOGGER.info("Application Successfully Started");

		// erstellt administrator, falls noch keiner existiert
		if (verificationClass.validateAdmin("a@admin.ch")) {
			User u = new User();
			u.setUserEmail("a@admin.ch");
			u.setUserMobile("0795555555");
			u.setUserName("Admin");
			u.setUserPassword("0192023a7bbd73250516f069df18b500"); // passwort: "admin123"
			u.setAuthorization(Authorization.administrator);

			userRepository.save(u);
			LOGGER.info("Admin created");
		} else {
			LOGGER.info("Admin already available");
		}

	}
}
