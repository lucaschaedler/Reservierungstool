package ch.mgmt.business;

import org.springframework.stereotype.Service;

import ch.mgmt.logger.LoggerClass;
import ch.mgmt.persistence.AccountRequest;



@Service
public class VerificationClass {
	
	LoggerClass logger = new LoggerClass();

	public boolean validateAccountRequest(AccountRequest accountRequest) {
		
		//process --> verify accountrequest!
		
		logger.getLogger().info(this.getClass().getName() + "||AccountRequest verified||");
		return true;
	}
	
	
	//do business stuff
	//logger.getLoogger().info(this.getClass().getName() + " ||infos");

}
