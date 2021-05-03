package ch.backyardcoders;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ch.backyardcoders.mgmt.logger.LoggerClass;
import ch.backyardcoders.mgmt.persistence.Authorization;
import ch.backyardcoders.mgmt.persistence.ReservationRepository;
import ch.backyardcoders.mgmt.persistence.User;
import ch.backyardcoders.mgmt.persistence.UserRepository;

@SpringBootApplication
public class BackyardcodersSpringReactApplication {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	LoggerClass logger = new LoggerClass();

	public static void main(String[] args) {
		SpringApplication.run(BackyardcodersSpringReactApplication.class, args);
	}

	@PostConstruct
	public void createHardCodedData() {
		logger.getLogger().info(this.getClass().getName() + "||Application has successfully started||");

		
		//test Reservation erstellt
//		Reservation r = new Reservation();
//		r.setCourt(1);
//		r.setDate(2020, 12, 30);
//		r.setStartTime(15, 30);
//		r.setEndTime(16, 30);
//		r.setPlayerNames("homoQ");
//		
//		//Test User erstellt
//		User u = new User();
//		u.setUserEmail("roberto@hotmail.com");
//		u.setUserMobile("0695554323");
//		u.setUserName("roberto");
//		u.setUserPassword("baba123");
//		u.setAuthorization(Authorization.administrator);
//
//		reservationRepository.save(r);
//		userRepository.save(u);

	}
}
