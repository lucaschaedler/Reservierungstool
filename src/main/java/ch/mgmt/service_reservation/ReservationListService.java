package ch.mgmt.service_reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
import ch.mgmt.persistence.Reservation;
import ch.mgmt.persistence.ReservationRepository;



@RestController
public class ReservationListService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private VerificationClass verificationClass;
	
	LoggerClass logger = new LoggerClass();
	
	@GetMapping(path = "api/reservations", produces = " apllication/json")
	public List<Reservation> getReservations(@RequestParam(required = false) String filter){//filter kann man ev ins gui einbauen
		logger.getLogger().info(this.getClass().getName() + "||All reservation has been displayed||");
		return reservationRepository.findAll();
	}


}
