package ch.backyardcoders.mgmt.persistence;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity
public class Reservation {

	@Id @GeneratedValue
	private int reservationId;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime bookingDate;

	private int court = 1;
	

	
	private String playerNames;
	private int userIdReservation;

	public int getUserIdReservation() {
		return userIdReservation;
	}

	public void setUserIdReservation(int userIdReservation) {
		this.userIdReservation = userIdReservation;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public int getCourt() {
		return court;
	}

	public void setCourt(int court) {
		this.court = court;
	}

	public String getPlayerNames() {
		return playerNames;
	}

	public void setPlayerNames(String playerNames) {
		this.playerNames = playerNames;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}



}
