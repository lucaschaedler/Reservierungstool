package ch.backyardcoders.mgmt.persistence;




import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;


@Entity
public class Reservation {

	@Id
	private int reservationId;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime bookingDate;
	private int court = 1;
	private String playerNames;
	private int userIdReservation;
	private String btnId;
	
	public String getBtnId() {
		return btnId;
	}
	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}
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
	public int getUserIdReservation() {
		return userIdReservation;
	}
	public void setUserIdReservation(int userIdReservation) {
		this.userIdReservation = userIdReservation;
	}

}