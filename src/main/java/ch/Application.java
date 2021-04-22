package ch;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.mgmt.logger.LoggerClass;
import ch.mgmt.persistence.Reservation;
import ch.mgmt.persistence.ReservationRepository;
import ch.mgmt.persistence.User;
import ch.mgmt.persistence.UserRepository;


;

@SpringBootApplication(scanBasePackages={
"ch.mgmt", "ch.application"})
public class Application {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	LoggerClass logger = new LoggerClass();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void createHardCodedData() {
		logger.getLogger().info(this.getClass().getName() + "||Application has successfully started||");

		Reservation r = new Reservation();
		r.setCourt(1);
		r.setDate(2020, 12, 30);
		r.setStartTime(15, 30);
		r.setEndTime(16, 30);
		r.setPlayerNames("homoQ");
		//r.setUserId(12);
		
		User u = new User();
		u.setUserEmail("wehfgirw");
		u.setUserMobile("efgfr");
		u.setUserName("hans");
		u.setUserPassword("homo");


		reservationRepository.save(r);
		userRepository.save(u);

	}
}