package messages;

import persistence.Reservation;

public class MessageModifyReservation {
	
	public int court;
	public String startTime; //ev Datentypändern
	public String endTime;
	public String date;
	public String playerNames;
	
	MessageModifyReservation(){
		
	}
	// Erstellen eine ArtikelMessage basierend auf einem ArticleEntity, wird eventuell benötigt
	public void MessageModifyreservation(Reservation r) {
		court = r.getCourt();
		startTime = r.getStartTime();
		endTime = r.getEndTime();
		date = r.getDate();
		playerNames = r.getPlayerNames();
	}

	public int getCourt() {
		return court;
	}

	public void setCourt(int court) {
		this.court = court;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPlayerNames() {
		return playerNames;
	}

	public void setPlayerNames(String playerNames) {
		this.playerNames = playerNames;
	}

}
