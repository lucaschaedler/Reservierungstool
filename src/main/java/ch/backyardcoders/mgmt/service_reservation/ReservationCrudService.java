package ch.backyardcoders.mgmt.service_reservation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ch.backyardcoders.mgmt.messages.MessageModifyReservation;
import ch.backyardcoders.mgmt.messages.MessageNewReservation;
import ch.backyardcoders.mgmt.persistence.Reservation;
import ch.backyardcoders.mgmt.persistence.ReservationRepository;
import ch.backyardcoders.mgmt.service_user.UserCrudService;

@RestController
@RequestMapping("api/")
public class ReservationCrudService {

	@Autowired
	private ReservationRepository reservationRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationCrudService.class.getSimpleName());

	@GetMapping(path = "reservation/{reservationid}", produces = "application/json")
	public Reservation getReservation(@PathVariable int reservationid) {
		Reservation r = reservationRepository.findById(reservationid).get();
		LOGGER.info("Reservation found by ID");
		return r;
	}

	@DeleteMapping(path = "reservation/{reservationid}", produces = "application/json")
	public boolean deleteReservation(@PathVariable int reservationid) {

		if (reservationRepository.existsById(reservationid)) {
			reservationRepository.deleteById(reservationid);
			LOGGER.info("Reservation deleted");
			return true;
		} else
			LOGGER.info("Reservation not found");
		return false;

	}

	@PostMapping(path = "reservation", produces = "application/json")
	public boolean createReservation(@RequestBody MessageNewReservation m) {
		Reservation r = new Reservation();
		r.setReservationId(m.getReservationId());
		r.setBookingDate(m.getBookingDate());
		r.setPlayerNames(m.getPlayerNames());
		r.setUserIdReservation(m.getUserIdReservation());
		r.setBtnId(m.getBtnId());
		reservationRepository.save(r);
		LOGGER.info("Reservation created");
		return true;
	}

	@PutMapping(path = "reservation/modify/{reservationid}", produces = "application/json")
	public boolean updateReservation(@PathVariable int reservationid, @RequestBody MessageModifyReservation m) {
		Reservation r = reservationRepository.getOne(reservationid);
		if (reservationRepository.existsById(reservationid)) {
			r.setPlayerNames(m.getPlayerNames());
			reservationRepository.save(r);
			LOGGER.info("Reservation updated");
			return true;
		} else {
			LOGGER.info("Reservation not found");
			return false;
		}

	}

	@GetMapping(path = "reservations", produces = " application/json")
	public List<Reservation> getReservations() {
		List<Reservation> list = reservationRepository.findAll();
		LOGGER.info("Reservations found");
		return list;
	}
}
