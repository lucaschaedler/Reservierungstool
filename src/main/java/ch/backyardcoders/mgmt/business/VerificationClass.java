package ch.backyardcoders.mgmt.business;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.backyardcoders.mgmt.logger.LoggerClass;
import ch.backyardcoders.mgmt.persistence.AccountRequest;
import ch.backyardcoders.mgmt.persistence.AccountRequestRepository;
import ch.backyardcoders.mgmt.persistence.Reservation;
import ch.backyardcoders.mgmt.persistence.ReservationRepository;
import ch.backyardcoders.mgmt.persistence.User;
import ch.backyardcoders.mgmt.persistence.UserRepository;

@Service
public class VerificationClass {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountRequestRepository accountRequestrepository;

	@Autowired
	ReservationRepository reservationRepository;

	LoggerClass logger = new LoggerClass();

	public boolean validateAdmin(String email) {
		User u = userRepository.findUserByUserEmail(email);
		if (u == null) {
			return true;
		} else
			return false;
	}

	public boolean validateEmail(AccountRequest accountRequest) {

		String tempEmail = accountRequest.getAccountRequestEmail();
		AccountRequest x = accountRequestrepository.findByAccountRequestEmail(tempEmail);
		User u = userRepository.findUserByUserEmail(tempEmail);

		if (x == null && u == null) {
			logger.getLogger().info(this.getClass().getName() + "||UserEmail are unique||");
			return true;
		} else {
			return false;
		}
	}

	public User VerifyLogin(String tempEmail, String tempPassword) {
		User x = userRepository.findByUserEmailAndUserPassword(tempEmail, tempPassword);
		if (x != null) {
			logger.getLogger().info(this.getClass().getName() + "||User found||");
			return x;
		} else
			logger.getLogger().info(this.getClass().getName() + "||User not found||");
		return null;

	}

	public boolean validateReservation(Reservation r) {
		LocalDateTime tempDate = r.getBookingDate();
//		if(reservationRepository.findByBookingDate(tempDate)){ // todo: + and Court
//			logger.getLogger().info(this.getClass().getName() + "||Booking Slot is empty -- Reservation created||");
//			return true;
//		}
//		logger.getLogger().info(this.getClass().getName() + "||Booking Slot is full -- Reservation failed||");
		return true;
	}

}
