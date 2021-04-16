package service_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import business.VerificationClass;
import persistence.AccountRequestRepository;

@RestController
public class AccountRequestListService {
	
	@Autowired
	private AccountRequestRepository accountRepository;
	
	@Autowired
	private VerificationClass verificationClass;

}
