package ch.mgmt.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.mgmt.logger.LoggerClass;
import ch.mgmt.persistence.AccountRequest;
import ch.mgmt.persistence.User;
import ch.mgmt.persistence.UserRepository;

@Service
public class VerificationClass {

	@Autowired
	UserRepository userRepository;

	LoggerClass logger = new LoggerClass();

	public boolean validateAccountRequest(AccountRequest accountRequest) {

		String tempEmail = accountRequest.getAccountRequestEmail();
		User x = userRepository.findByUserEmail(tempEmail);
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
