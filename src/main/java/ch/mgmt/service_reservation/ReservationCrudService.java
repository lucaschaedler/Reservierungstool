package ch.mgmt.service_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
import ch.mgmt.persistence.Reservation;
import ch.mgmt.persistence.ReservationRepository;

@RestController
public class ReservationCrudService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private VerificationClass verificationClass;

	LoggerClass logger = new LoggerClass();

	@GetMapping(path = "/api/reservation/{reservationid}", produces = "application/json")
	public Reservation getReservation(@PathVariable int reservationid) {
		logger.getLogger().info(this.getClass().getName() + "||Reservation found by ID||");
		return reservationRepository.findById(reservationid).get();
	}

	@DeleteMapping(path = "/api/reservation/{reservationid}", produces = "application/json")
	public boolean deleteReservation(@PathVariable int reservationid) {

		if (reservationRepository.existsById(reservationid)) {
			reservationRepository.deleteById(reservationid);
			logger.getLogger().info(this.getClass().getName() + "||Reservation has been deleted||");
			return true;
		} else
			logger.getLogger().info(this.getClass().getName() + "||Reservation to be deleted not found||");
		return false;

	}
}
