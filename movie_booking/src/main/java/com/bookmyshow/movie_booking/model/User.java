package com.bookmyshow.movie_booking.model;

import java.util.ArrayList;
import java.util.HashMap;



public class User {
    private String firstName;
    private String lastName;
    private String loginId;
    private String password;
    private String contactNumber;
    private UserType userType;
    private HashMap<Validitity, ArrayList<Tickets>> myBookings;
	public enum UserType {
        USER, 
        ADMIN
    }
	
	public enum Validitity {
        VALID,
        EXPIRED
    }
	
	public HashMap<Validitity, ArrayList<Tickets>> getMyBookings() {
		return myBookings;
	}
	public void setMyBookings(HashMap<Validitity, ArrayList<Tickets>> myBookings) {
		this.myBookings = myBookings;
	}
	
    public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getLoginId() {
		return loginId;
	}



	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getContactNumber() {
		return contactNumber;
	}



	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}



	public UserType getUserType() {
		return userType;
	}



	public void setUserType(UserType userType) {
		this.userType = userType;
	}




}
