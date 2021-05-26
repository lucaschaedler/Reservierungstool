package ch.backyardcoders.mgmt.service_account;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ch.backyardcoders.mgmt.business.VerificationClass;
import ch.backyardcoders.mgmt.messages.MessageNewAccountRequest;
import ch.backyardcoders.mgmt.persistence.AccountRequest;
import ch.backyardcoders.mgmt.persistence.AccountRequestRepository;
import ch.backyardcoders.mgmt.security.PasswordHash;

@RestController
@RequestMapping("api/")
public class AccountRequestCrudService {

	@Autowired
	private PasswordHash passwordHash;
	
	@Autowired
	private AccountRequestRepository accountRepository;

	@Autowired
	private VerificationClass verificationClass;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRequestCrudService.class.getSimpleName());

	@PostMapping(path = "account_request", produces = "application/json")
	public boolean createAccountRequest(@RequestBody MessageNewAccountRequest m) {

		AccountRequest a = new AccountRequest();
		a.setAccountRequestEmail(m.getAccountRequestEmail());
		a.setAccountRequestMobile(m.getAccountRequestMobile());
		a.setAccountRequestName(m.getAccountRequestName());
		a.setAccountRequestPassword(passwordHash.hashPassword(m.getAccountRequestPassword()));
		
		if (verificationClass.validateEmail(a)) {
			accountRepository.save(a);
			LOGGER.info("Accountrequest created");
			return true;
		} else {
			LOGGER.info("Accountrequest failed");
			return false;
		}
	}

	@GetMapping(path = "accountRequests", produces = "application/json")
	public List<AccountRequest> getAccountRequests() {
		List<AccountRequest> list = accountRepository.findAll();
		LOGGER.info("Accountrequests found");
		return list;

	}

	@DeleteMapping(path = "deleteAccountRequest/{accountrequestid}", produces = "application/json")
	public boolean deleteAccountRequest(@PathVariable int accountrequestid) {
		if (accountRepository.existsById(accountrequestid)) {
			accountRepository.deleteById(accountrequestid);
			LOGGER.info("Accountrequest deleted");
			return true;
		} else {
			LOGGER.info("Accountrequest not found");
			return false;
		}

	}
}
