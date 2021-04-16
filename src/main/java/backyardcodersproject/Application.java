package backyardcodersproject;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Logger.LoggerClass;
import persistence.ReservationRepository;

@SpringBootApplication
public class Application {
	
	@Autowired
	private ReservationRepository reservationRepository;

	LoggerClass logger = new LoggerClass();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void createHardCodedData() {
		logger.getLogger().info(this.getClass().getName() + "||Application has successfully started||");
		
	//	Reservation r = new Reservation();
//		r.setCourt(1);
	//	r.setDate("12");
	//	r.setEndTime("13");
	//	r.setStartTime("20");
	//	r.setPlayerNames("homoQ");
		
	//	reservationRepository.save(r);
				

	}
}