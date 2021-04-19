package ch.mgmt.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Integer>{

}
