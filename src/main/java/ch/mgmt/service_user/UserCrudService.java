package ch.mgmt.service_user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
import ch.mgmt.messages.MessageLogin;
import ch.mgmt.messages.MessageModifyUser;
import ch.mgmt.messages.MessageNewUser;
import ch.mgmt.persistence.AccountRequest;
import ch.mgmt.persistence.AccountRequestRepository;
import ch.mgmt.persistence.User;
import ch.mgmt.persistence.UserRepository;

@RestController
@RequestMapping("api/")
public class UserCrudService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerificationClass verificationClass;
	
	@Autowired
	private AccountRequestRepository accountRequestRepository;

	LoggerClass logger = new LoggerClass();

	// Wird nur von ITVerantwortlichen aufgerufen idem er die AccountAnfrage
	// bestätigt --> wandelt dann anfrage in User um
	@PostMapping(path = "createUser/{reqid}", produces = "application/json")
	public boolean createUser(@PathVariable int reqid) {
		if (accountRequestRepository.existsById(reqid)) {
			AccountRequest a = accountRequestRepository.getOne(reqid);
		
		User u = new User();
		u.setUserName(a.getAccountRequestName());
		u.setUserEmail(a.getAccountRequestEmail());
		u.setUserMobile(a.getAccountRequestMobile());
		u.setUserPassword(a.getAccountRequestPassword());
		userRepository.save(u);
		logger.getLogger().info(this.getClass().getName() + "||User has been created||");
		accountRequestRepository.deleteById(reqid);
		logger.getLogger().info(this.getClass().getName() + "||Accountrequest has been deleted||");
		return true;
		}else {
			logger.getLogger().info(this.getClass().getName() + "||Accountrequest not found||");
			return false;
		}
		

		
	}

@DeleteMapping(path = "user/delete/{userid}", produces = "application/json")
	public boolean deleteUser(@PathVariable int userid) {
		if (userRepository.existsById(userid)) {
			userRepository.deleteById(userid);
			logger.getLogger().info(this.getClass().getName() + "||User has been deleted||");
			return true;
		} else {
			logger.getLogger().info(this.getClass().getName() + "||User hasnt been found||");
			return false;
		}
	}

	// Listes alle Users auf, wird für Deletemethode verwendet
	@GetMapping("users")
	public List<User> getUsers(@RequestParam(required = false) String filter) {
		logger.getLogger().info(this.getClass().getName() + "||List of user displayed||");
		return this.userRepository.findAll();
	}

	// Von allen User aufrufbar --> können so UserDaten ändern
	@PutMapping(path = "user/{userid}/modify", produces = "application/json") // für user zugreifbar
	public boolean modifyUser(@PathVariable int userid, @RequestBody MessageModifyUser m) {
		if (userRepository.existsById(userid)) {
			User u = userRepository.getOne(userid);
			// prüft ob der das Messageattribut leer ist oder nicht
			if (m.getUserName() != "") {
				u.setUserName(m.getUserName());
			}
			if (m.getPassword() != "") {
				u.setUserPassword(m.getPassword());
			}
			if (m.getMobile() != "") {
				u.setUserMobile(m.getMobile());
			}
			if (m.getEmail() != "") {
				u.setUserEmail(m.getEmail());
			}

			logger.getLogger().info(this.getClass().getName() + "||User has been updated||");
			return true;

		} else {
			logger.getLogger().info(this.getClass().getName() + "||User hasnt been found||");
			return false;

		}

	}

	// Login --> wird geprüft ob Email und Passswort stimmen
	@PostMapping(path = "login", produces = "application/json")
	public int login(@RequestBody MessageLogin m) {
		String tempEmail = m.getUserEmail();
		String tempPassword = m.getUserPassword();

		User u = verificationClass.VerifyLogin(tempEmail, tempPassword);
		if (u != null) {
			logger.getLogger().info(this.getClass().getName() + "||Login Successfull||");
			return u.getUserId();
		} else {
			return -1;// wenn zahl -1 ist dann ist login fehlgeschlagen
		}

	}

}
