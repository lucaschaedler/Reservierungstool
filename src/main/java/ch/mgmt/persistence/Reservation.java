package ch.mgmt.persistence;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue
	private Integer reservationId;

	private int court;
	private LocalDate date; // date wird noch geändert
	private LocalTime startTime;
	private LocalTime endTime;
	private String playerNames;
	
	@OneToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStartTime(int hour, int minute) {
		this.startTime = LocalTime.of(hour, minute);
	}

	public void setEndTime(int hour, int minute) {
		this.endTime = LocalTime.of(hour, minute);
	}

	public void setDate(int year, int month, int day) {
		this.date = LocalDate.of(year, month, day);

	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public LocalTime getStartTime() {
		return this.startTime;
	}
	
	public LocalTime getEndtime() {
		return this.endTime;
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

}
