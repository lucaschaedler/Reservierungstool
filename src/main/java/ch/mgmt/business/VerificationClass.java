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
		
		//process --> verify accountrequest!
		
		logger.getLogger().info(this.getClass().getName() + "||AccountRequest verified||");
		return false;
	}

	public boolean VerifyLogin(String tempEmail, String tempPassword) {
	User x = userRepository.findByUserEmailAndUserPassword(tempEmail, tempPassword);
	if(x != null) {
		logger.getLogger().info(this.getClass().getName() + "||User found||");
		return true;
	}else
		return false;
	
	}
	
	
	//do business stuff
	//logger.getLoogger().info(this.getClass().getName() + " ||infos");

}
