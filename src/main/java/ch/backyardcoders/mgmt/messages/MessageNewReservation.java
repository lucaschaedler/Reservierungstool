package ch.backyardcoders.mgmt.messages;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MessageNewReservation {
	
	//private int court;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime bookingDate;
	
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	private String playerNames;
	private int userIdReservation;
	
	
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
