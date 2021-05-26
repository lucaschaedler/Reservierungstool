package ch.backyardcoders.mgmt.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ch.backyardcoders.mgmt.persistence.AccountRequest;
import ch.backyardcoders.mgmt.persistence.AccountRequestRepository;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(VerificationClass.class.getSimpleName());

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
			LOGGER.info("Useremail is unique");
			return true;
		} else {
			return false;
		}
	}

	public User VerifyLogin(String tempEmail, String tempPassword) {
		User x = userRepository.findByUserEmailAndUserPassword(tempEmail, tempPassword);
		if (x != null) {
			LOGGER.info("User found");
			return x;
		} else {
			LOGGER.info("User not found");
			return null;
		}

	}

}
