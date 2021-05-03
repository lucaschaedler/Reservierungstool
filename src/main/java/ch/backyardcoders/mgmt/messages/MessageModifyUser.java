package ch.backyardcoders.mgmt.messages;

public class MessageModifyUser {
	public String email;
	public String mobile;
	public String userName;
	public String password; //ev Typ ändern zu Password oder so
	

	public MessageModifyUser() {
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
