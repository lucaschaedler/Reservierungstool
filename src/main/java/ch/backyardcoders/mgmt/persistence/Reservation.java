package ch.backyardcoders.mgmt.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {

	@Id
	private int reservationId;

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

}
