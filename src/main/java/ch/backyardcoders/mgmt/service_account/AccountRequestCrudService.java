package ch.backyardcoders.mgmt.service_account;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ch.backyardcoders.mgmt.business.VerificationClass;
import ch.backyardcoders.mgmt.logger.LoggerClass;
import ch.backyardcoders.mgmt.messages.MessageNewAccountRequest;
import ch.backyardcoders.mgmt.persistence.AccountRequest;
import ch.backyardcoders.mgmt.persistence.AccountRequestRepository;
import ch.backyardcoders.mgmt.security.PasswordHash;

@RestController
public class AccountRequestCrudService {

	@Autowired
	private PasswordHash passwordHash;
	
	@Autowired
	private AccountRequestRepository accountRepository;

	@Autowired
	private VerificationClass verificationClass;

	LoggerClass logger = new LoggerClass();

	@PostMapping(path = "/api/account_request", produces = "application/json")
	public boolean createAccountRequest(@RequestBody MessageNewAccountRequest m) {

		AccountRequest a = new AccountRequest();
		a.setAccountRequestEmail(m.getAccountRequestEmail());
		a.setAccountRequestMobile(m.getAccountRequestMobile());
		a.setAccountRequestName(m.getAccountRequestName());
		
		a.setAccountRequestPassword(passwordHash.hashPassword(m.getAccountRequestPassword()));
		
		if (verificationClass.validateAccountRequest(a)) {// ob email schon vergeben ist noch vom user überprüfen auch noch
			accountRepository.save(a);
			logger.getLogger().info(this.getClass().getName() + "||AccountRequest created||");
			return true;
		} else {
			logger.getLogger().info(this.getClass().getName() + "||AccountRequest failed||");
			return false;// sagen das email schon vorhanden ist
		}
	}

	@GetMapping(path = "/api/accountRequests", produces = "application/json")
	public List<AccountRequest> getAccountRequests(@RequestParam(required = false) String filter) {

		// verify user --> verificationClass

		return accountRepository.findAll();

	}

	@DeleteMapping(path = "/api/deleteAccountRequest/{accountrequestid}", produces = "application/json")
	public boolean deleteAccountRequest(@PathVariable int accountrequestid) {
		if (accountRepository.existsById(accountrequestid)) {
			accountRepository.deleteById(accountrequestid);
			logger.getLogger().info(this.getClass().getName() + "||Accountrequest has been deleted||");
			return true;
		} else {
			logger.getLogger().info(this.getClass().getName() + "||Accountrequest to be deleted could not be found||");
			return false;
		}

	}
}
