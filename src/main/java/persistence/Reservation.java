package persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reservation {
	
	@Id @GeneratedValue
	private Integer reservationId;
	
	private int court;
	private String date; //date wird noch geändert
	private String startTime;
	private String endTime;
	private String playerNames;

	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getCourt() {
		return court;
	}
	public void setCourt(int court) {
		this.court = court;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(String playerNames) {
		this.playerNames = playerNames;
	}
	
	


}
