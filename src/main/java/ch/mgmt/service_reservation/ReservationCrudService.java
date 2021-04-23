package ch.mgmt.service_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
import ch.mgmt.messages.MessageModifyReservation;
import ch.mgmt.messages.MessageNewReservation;
import ch.mgmt.persistence.Reservation;
import ch.mgmt.persistence.ReservationRepository;
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
			logger.getLogger().info(this.getClass().getName() + "||Reservation to be deleted could not be found||");
		return false;

	}

	@PostMapping(path = "api/reservation", produces = "application/json")
	public int createReservation(@RequestBody MessageNewReservation m) {

//		Optional<User> list = userRepository.findById(4); //id muss angepasst werde auf aktuell eingeloggten user
//		if (list.isPresent()) {
//			User user = list.get();
		Reservation r = new Reservation();
		r.setCourt(m.getCourt());
		r.setPlayerNames(m.getPlayerNames());
		r.setDate(m.getYear(), m.getMonth(), m.getDay());
		r.setStartTime(m.getStartHour(), m.getStartMinute());
		r.setEndTime(m.getStartHour(), m.getEndMinute());

		reservationRepository.save(r);

//			user.addReservationToList(r);
//			System.out.println(user.getReservationList());

//		}
		return r.getReservationId();
	}
	
	@PutMapping(path = "api/reservation/{reservationid}/modify", produces = "application/json")
	public boolean updateReservation(@PathVariable int reservationid, @RequestBody MessageModifyReservation m) {
		Reservation r = reservationRepository.getOne(reservationid);
		if(reservationRepository.existsById(reservationid)) {
			r.setCourt(m.getCourt());
			r.setPlayerNames(m.getPlayerNames());
			r.setDate(m.getYear(), m.getMonth(), m.getDay());
			r.setStartTime(m.getStartHour(), m.getStartMinute());
			r.setEndTime(m.getEndHour(), m.getEndMinute());
			
			//ev eingabe prüfen
			logger.getLogger().info(this.getClass().getName() + "||Reservation has been updated||");
			return true;
		}else {
			logger.getLogger().info(this.getClass().getName() + "||Reservation could not be found||");
			return false;
		}
			
	}
}
