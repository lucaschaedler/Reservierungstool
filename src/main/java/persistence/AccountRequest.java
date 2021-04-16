package persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AccountRequest {

	@Id @GeneratedValue
	private int accountRequestId;
	
	private String accountRequestEmail;
	private String accountRequestMobile;
	private String accountRequestName;
	private String accountRequestPassword;
	

	private int getAccountRequestId() {
		return accountRequestId;
	}
	private void setAccountRequestId(int accountRequestId) {
		this.accountRequestId = accountRequestId;
	}
	private String getAccountRequestEmail() {
		return accountRequestEmail;
	}
	private void setAccountRequestEmail(String accountRequestEmail) {
		this.accountRequestEmail = accountRequestEmail;
	}
	private String getAccountRequestMobile() {
		return accountRequestMobile;
	}
	private void setAccountRequestMobile(String accountRequestMobile) {
		this.accountRequestMobile = accountRequestMobile;
	}
	private String getAccountRequestName() {
		return accountRequestName;
	}
	private void setAccountRequestName(String accountRequestName) {
		this.accountRequestName = accountRequestName;
	}
	private String getAccountRequestPassword() {
		return accountRequestPassword;
	}
	private void setAccountRequestPassword(String accountRequestPassword) {
		this.accountRequestPassword = accountRequestPassword;
	}
	
}
