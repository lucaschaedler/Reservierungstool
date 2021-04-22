package ch.mgmt.service_reservation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
import ch.mgmt.messages.MessageNewReservation;
import ch.mgmt.persistence.Reservation;
import ch.mgmt.persistence.ReservationRepository;
import ch.mgmt.persistence.User;
import ch.mgmt.persistence.UserRepository;

@RestController
public class ReservationCrudService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserRepository userRepository;

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

		if (reservationRepository.existsById(reservationid)) {// berechtigung hinzufügen nur eigene löschen
			reservationRepository.deleteById(reservationid);
			logger.getLogger().info(this.getClass().getName() + "||Reservation has been deleted||");
			return true;
		} else
			logger.getLogger().info(this.getClass().getName() + "||Reservation to be deleted not found||");
		return false;

	}

	@PostMapping(path = "api/reservation", produces = "application/json")
	public int createReservation(@RequestBody MessageNewReservation m) {
		Reservation r = new Reservation();
		Optional<User> list = userRepository.findById(4);//id muss ich angepasst werde auf der der eingeloggt ist
		if (list.isPresent()) {
			User user = list.get();

			r.setCourt(m.getCourt());
			r.setPlayerNames(m.getPlayerNames());
			r.setDate(m.getYear(), m.getMonth(), m.getDay());
			r.setStartTime(m.getStartHour(), m.getStartMinute());
			r.setEndTime(m.getStartHour(), m.getEndMinute());
			
			reservationRepository.save(r);

			//user.addReservationToList(r);
			//System.out.println(user.getReservationList());

		}
		return r.getReservationId();
	}
}
