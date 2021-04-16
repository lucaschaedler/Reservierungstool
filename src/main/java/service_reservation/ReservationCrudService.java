package service_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import business.VerificationClass;
import persistence.ReservationRepository;

@RestController
public class ReservationCrudService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private VerificationClass verificationClass;

}
