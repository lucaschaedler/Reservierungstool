package ch.mgmt.service_user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.mgmt.business.VerificationClass;
import ch.mgmt.logger.LoggerClass;
import ch.mgmt.persistence.User;
import ch.mgmt.persistence.UserRepository;



@RestController
@RequestMapping("api/")
public class UserCrudService {
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private VerificationClass verificationClass;
	
	LoggerClass logger = new LoggerClass();
	
	@GetMapping("users")
	public List<User> getUsers(){
		return this.userrepository.findAll();
	}
	

}
