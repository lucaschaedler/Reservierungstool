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
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
import ch.mgmt.messages.MessageLogin;
import ch.mgmt.messages.MessageModifyUser;
import ch.mgmt.messages.MessageNewUser;
import ch.mgmt.persistence.User;
import ch.mgmt.persistence.UserRepository;

@RestController
@RequestMapping("api/")
public class UserCrudService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerificationClass verificationClass;

	LoggerClass logger = new LoggerClass();

	// Wird nur von ITVerantwortlichen aufgerufen idem er die AccountAnfrage
	// bestätigt --> wandelt dann anfrage in User um
	@PostMapping(path = "createUser", produces = "application/json")
	public boolean createUser(@RequestBody MessageNewUser m) {

		// verifizieren das alle angaben korrekt sind

		User u = new User();
		u.setUserName(m.getUserName());
		u.setUserPassword(m.getUserPassword());
		u.setUserMobile(m.getUserMobile());
		u.setUserEmail(m.getUserEmail());

		userRepository.save(u);
		logger.getLogger().info(this.getClass().getName() + "||User created||");
		return true;

	}

	// kann nur vom Admin und ITVerantwortlichen aufgerufen werden über UserListe
	// --> löscht angewählten account
	@DeleteMapping(path = "user/delete/{uderid}", produces = "apllication/json")
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
	public List<User> getUsers() {
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
