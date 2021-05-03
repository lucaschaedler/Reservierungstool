package ch.backyardcoders.mgmt.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AccountRequest {

	@Id @GeneratedValue
	private Integer accountRequestId;
	
	private String accountRequestEmail;
	private String accountRequestMobile;
	private String accountRequestName;
	private String accountRequestPassword;

	
	public Integer getAccountRequestId() {
		return accountRequestId;
	}
	public void setAccountRequestId(Integer accountRequestId) {
		this.accountRequestId = accountRequestId;
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
