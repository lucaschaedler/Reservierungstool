package ch.backyardcoders.mgmt.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.backyardcoders.mgmt.logger.LoggerClass;
import ch.backyardcoders.mgmt.persistence.AccountRequest;
import ch.backyardcoders.mgmt.persistence.AccountRequestRepository;
import ch.backyardcoders.mgmt.persistence.User;
import ch.backyardcoders.mgmt.persistence.UserRepository;

@Service
public class VerificationClass {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRequestRepository accountRequestrepository;

	LoggerClass logger = new LoggerClass();

	public boolean validateAccountRequest(AccountRequest accountRequest) {

		String tempEmail = accountRequest.getAccountRequestEmail();//funktioniert gibt e mail zurück
		AccountRequest x = accountRequestrepository.findByAccountRequestEmail(tempEmail);
		System.out.println(x);
		if (x == null) {
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

}
