package persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id @GeneratedValue
	private int userId;
	
	private String email;
	private String mobile;
	private String password;
	private String userName;

	
	private int getUserId() {
		return userId;
	}
	private void setUserId(int userId) {
		this.userId = userId;
	}
	private String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	private String getMobile() {
		return mobile;
	}
	private void setMobile(String mobile) {
		this.mobile = mobile;
	}
	private String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	private String getUserName() {
		return userName;
	}
	private void setUserName(String userName) {
		this.userName = userName;
	}
	
}
