package ch.mgmt.messages;

import ch.mgmt.persistence.Authorization;

public class MessageNewAccountRequest {
	public String accountRequestEmail;
	public String accountRequestMobile;
	public String accountRequestName;
	public String accountRequestPassword;
	public Authorization authorization = Authorization.user;//ist immer User, andere berechtigungen sind hart codiert
	
	public MessageNewAccountRequest(){
		
	}
	
	public String getAccountRequestEmail() {
		return accountRequestEmail;
	}
	public void setAccountRequestEmail(String accountRequestEmail) {
		this.accountRequestEmail = accountRequestEmail;
	}
	public String getAccountRequestMobile() {
		return accountRequestMobile;
	}
	public void setAccountRequestMobile(String accountRequestMobile) {
		this.accountRequestMobile = accountRequestMobile;
	}
	public String getAccountRequestName() {
		return accountRequestName;
	}
	public void setAccountRequestName(String accountRequestName) {
		this.accountRequestName = accountRequestName;
	}
	public String getAccountRequestPassword() {
		return accountRequestPassword;
	}
	public void setAccountRequestPassword(String accountRequestPassword) {
		this.accountRequestPassword = accountRequestPassword;
	}


}
