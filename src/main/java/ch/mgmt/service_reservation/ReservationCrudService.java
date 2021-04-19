package ch.mgmt.service_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
import ch.mgmt.persistence.ReservationRepository;


@RestController
public class ReservationCrudService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private VerificationClass verificationClass;
	
	LoggerClass logger = new LoggerClass();

}
