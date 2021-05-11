package ch.backyardcoders.mgmt.persistence;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

	
//	@Query("Select r from reservation r where r.booking_date = :tempDate")
//	boolean findByBookingDate(@Param("tempDate") LocalDateTime tempDate, @Param("otherDate") LocalDateTime otherDate);
	
	//public List<Reservation> findByUserId(int userId);

}
