package ch.backyardcoders.mgmt.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Integer>{
	
	AccountRequest findByAccountRequestEmail(String tempEmail);

}
