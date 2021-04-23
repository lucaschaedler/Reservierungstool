package ch.mgmt.service_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
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

	@PostMapping(path = "createUser", produces = "application/json") // wird von requestliste direkt aufgerufen
	public int createUser(@RequestBody MessageNewUser m) {

		// verifizieren das alle angaben korrekt sind

		User u = new User();
		u.setUserName(m.getUserName());
		u.setUserPassword(m.getUserPassword());
		u.setUserMobile(m.getUserMobile());
		u.setUserEmail(m.getUserEmail());

		userRepository.save(u);
		logger.getLogger().info(this.getClass().getName() + "||User created||");
		return u.getUserId();

	}

	@DeleteMapping(path = "user/delete/{uderid}", produces = "apllication/json")//nur via userliste admin rechte zugreifbar
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

	@PutMapping(path = "user/{userid}/modify", produces = "application/json")//für user zugreifbar
	public boolean modifyUser(@PathVariable int userid, @RequestBody MessageModifyUser m) {
		if (userRepository.existsById(userid)) {
			User u = userRepository.getOne(userid);
			u.setUserName(m.getUserName());
			u.setUserPassword(m.getPassword());
			u.setUserMobile(m.getMobile());
			u.setUserEmail(m.getEmail());

			logger.getLogger().info(this.getClass().getName() + "||User has been updated||");
			return true;

		} else {
			logger.getLogger().info(this.getClass().getName() + "||User hasnt been found||");
			return false;

		}

	}

}
