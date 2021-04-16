package persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reservation {
	
	@Id @GeneratedValue
	private int reservationId;
	
	private int court;
	private String date; //date wird evtl. noch geändert
	private String startTime;
	private String endTime;
	private String playerNames;
	
	
	private int getReservationId() {
		return reservationId;
	}
	private void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	private int getCourt() {
		return court;
	}
	private void setCourt(int court) {
		this.court = court;
	}
	private String getDate() {
		return date;
	}
	private void setDate(String date) {
		this.date = date;
	}
	private String getStartTime() {
		return startTime;
	}
	private void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	private String getEndTime() {
		return endTime;
	}
	private void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	private String getPlayerNames() {
		return playerNames;
	}
	private void setPlayerNames(String playerNames) {
		this.playerNames = playerNames;
	}

}
