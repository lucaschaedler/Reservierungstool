package ch.backyardcoders.mgmt.messages;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MessageNewReservation {
	
	private int reservationId;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime bookingDate;
	//private int court = 1;
	private String playerNames;
	private int userIdReservation;

	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(String playerNames) {
		this.playerNames = playerNames;
	}
	public int getUserIdReservation() {
		return userIdReservation;
	}
	public void setUserIdReservation(int userIdReservation) {
		this.userIdReservation = userIdReservation;
	}

}
