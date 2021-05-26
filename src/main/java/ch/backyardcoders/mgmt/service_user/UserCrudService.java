package ch.backyardcoders.mgmt.service_user;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ch.backyardcoders.mgmt.business.VerificationClass;
import ch.backyardcoders.mgmt.messages.MessageLogin;
import ch.backyardcoders.mgmt.messages.MessageModifyUser;
import ch.backyardcoders.mgmt.persistence.AccountRequest;
import ch.backyardcoders.mgmt.persistence.AccountRequestRepository;
import ch.backyardcoders.mgmt.persistence.User;
import ch.backyardcoders.mgmt.persistence.UserRepository;
import ch.backyardcoders.mgmt.security.PasswordHash;

@RestController
@RequestMapping("api/")
public class UserCrudService {

	@Autowired
	private PasswordHash passwordHash;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerificationClass verificationClass;

	@Autowired
	private AccountRequestRepository accountRequestRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserCrudService.class.getSimpleName());

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
			LOGGER.info("User created");
			accountRequestRepository.deleteById(reqid);
			LOGGER.info("Accountrequest deleted");
			return true;
		} else {
			LOGGER.info("Accountrequest not found");
			return false;
		}

	}

	@DeleteMapping(path = "user/delete/{userid}", produces = "application/json")
	public boolean deleteUser(@PathVariable int userid) {
		if (userRepository.existsById(userid)) {
			userRepository.deleteById(userid);
			LOGGER.info("User has been deleted");
			return true;
		} else {
			LOGGER.info("User not found");
			return false;
		}
	}

	@GetMapping("users")
	public List<User> getUsers() {
		List<User> list = this.userRepository.findAll();
		LOGGER.info("List of user found");
		return list;
	}

	@PutMapping(path = "user/modify/{userid}", produces = "application/json") // für user zugreifbar
	public boolean modifyUser(@PathVariable int userid, @RequestBody MessageModifyUser m) {
		if (userRepository.existsById(userid)) {
			User u = userRepository.getOne(userid);
			if(m.getUserName()!="") {
			u.setUserName(m.getUserName());}
			if(m.getUserPassword()!="") {
			u.setUserPassword(passwordHash.hashPassword(m.getUserPassword()));}
			if(m.getUserMobile()!="") {
			u.setUserMobile(m.getUserMobile());}
			if(m.getUserEmail()!="") {
			u.setUserEmail(m.getUserEmail());}
			userRepository.save(u);
			LOGGER.info("User has been updated");
			return true;

		} else {
			LOGGER.info("User has not found");
			return false;

		}

	}

	@PostMapping(path = "login", produces = "application/json")
	public int login(@RequestBody MessageLogin m) {
		String tempEmail = m.getUserEmail();
		String tempPassword = passwordHash.hashPassword(m.getUserPassword());

		User u = verificationClass.VerifyLogin(tempEmail, tempPassword);
		if (u != null) {
			LOGGER.info("Login Successfull");
			return u.getUserId();
		} else {
			LOGGER.info("Login Unsuccessfull");
			return -1;
		}

	}
	
	@GetMapping("user/{userid}")
	public User getUser(@PathVariable int userid) {
		User u = this.userRepository.findById(userid).get();
		LOGGER.info("User found");
		return u;
	}

}
