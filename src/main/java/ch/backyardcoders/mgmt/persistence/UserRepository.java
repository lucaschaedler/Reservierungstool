package ch.backyardcoders.mgmt.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	User findByUserEmailAndUserPassword(String tempEmail, String tempPassword);
	User findUserByUserEmail(String tempEmail);

	
		


}
