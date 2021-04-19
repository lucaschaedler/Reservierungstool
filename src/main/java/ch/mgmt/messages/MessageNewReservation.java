package ch.mgmt.messages;

public class MessageNewReservation {
	
	public int court;
	public String date; //date wird noch geändert
	public String startTime;
	public String endTime;
	public String playerNames;
	
	MessageNewReservation(){
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
