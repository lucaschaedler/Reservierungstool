package ch.mgmt.service_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
import ch.mgmt.persistence.UserRepository;



@RestController
public class UserListService {
	
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private VerificationClass verificationClass;
	
	LoggerClass logger = new LoggerClass();

}
