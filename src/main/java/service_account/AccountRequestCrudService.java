package service_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import Logger.LoggerClass;
import business.VerificationClass;
import persistence.AccountRequestRepository;

@RestController
public class AccountRequestCrudService {
	
	@Autowired
	private AccountRequestRepository accountRepository;
	
	@Autowired
	private VerificationClass verificationClass;
	
	LoggerClass logger = new LoggerClass();
	
	//do stuff

}
