package service_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import Logger.LoggerClass;
import business.VerificationClass;
import persistence.UserRepository;

@RestController
public class UserListService {
	
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private VerificationClass verificationClass;
	
	LoggerClass logger = new LoggerClass();

}
