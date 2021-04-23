package ch.mgmt.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User {
	
	@Id @GeneratedValue
	private Integer userId;
	
	private String userEmail;
	private String userMobile;
	private String userPassword;
	private String userName;
	private Authorization authorization = Authorization.user;
	
//	public User() {
//		this.authorization = Authorization.user;
//	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> userReservationList = new ArrayList();
	
	public List<Reservation> getReservationList() {
		return userReservationList;
	}

	public void setReservationList(List<Reservation> reservationList) {
		this.userReservationList = userReservationList;
	}
	
	public void addReservationToList(Reservation r) {
		userReservationList.add(r);
	}

	
	public Authorization getAuthorization() {
		return authorization;
	}
	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User actuel() {
		
		return null;
	}
	
	
	
	


	
}
