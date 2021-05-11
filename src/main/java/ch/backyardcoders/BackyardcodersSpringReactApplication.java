package ch.backyardcoders;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.backyardcoders.mgmt.logger.LoggerClass;
import ch.backyardcoders.mgmt.persistence.Authorization;
import ch.backyardcoders.mgmt.persistence.Reservation;
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
//		r.setCourt(2);
//		r.setDate(2021, 5, 7);
//		r.setStartTime(11, 0);
//		r.setEndTime(12, 0);
//		r.setPlayerNames("thomas");
//		
//		//Test User erstellt
		User u = new User();
		u.setUserEmail("baba123");
		u.setUserMobile("0695554323");
		u.setUserName("roberto");
		u.setUserPassword("d855ac619827b58d3ba90e74f326c4c2");
		u.setAuthorization(Authorization.administrator);

//		reservationRepository.save(r);
		userRepository.save(u);

	}
}
