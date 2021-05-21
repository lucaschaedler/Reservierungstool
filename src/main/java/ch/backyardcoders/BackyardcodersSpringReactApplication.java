package ch.backyardcoders;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.backyardcoders.mgmt.business.VerificationClass;
import ch.backyardcoders.mgmt.logger.LoggerClass;
import ch.backyardcoders.mgmt.persistence.Authorization;
import ch.backyardcoders.mgmt.persistence.User;
import ch.backyardcoders.mgmt.persistence.UserRepository;

@SpringBootApplication
public class BackyardcodersSpringReactApplication {
	
	@Autowired
	private VerificationClass verificationClass;
	
	@Autowired
	private UserRepository userRepository;
	

	LoggerClass logger = new LoggerClass();

	public static void main(String[] args) {
		SpringApplication.run(BackyardcodersSpringReactApplication.class, args);
	}

	@PostConstruct
	public void createHardCodedData() {
		logger.getLogger().info(this.getClass().getName() + "||Application has successfully started||");

		if(verificationClass.validateAdmin("a@admin.ch")) {
		User u = new User();
		u.setUserEmail("a@admin.ch");
		u.setUserMobile("0795555555");
		u.setUserName("Admin");
		u.setUserPassword("0192023a7bbd73250516f069df18b500");//passwort ist admin123
		u.setAuthorization(Authorization.administrator);

		userRepository.save(u);
		logger.getLogger().info(this.getClass().getName() + "||Admin erstellt||");
		}else {
			logger.getLogger().info(this.getClass().getName() + "||Admin schon vorhanden||");
		}

	}
}
