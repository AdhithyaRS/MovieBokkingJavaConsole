package com.bookmyshow.movie_booking.service;

import java.io.Console;
import java.util.HashMap;
import java.util.Scanner;

import com.bookmyshow.movie_booking.model.User;
import com.bookmyshow.movie_booking.model.User.UserType;

public class SignUpService {
	private DataService dataService = new DataService();
	private User user = new User();
	
	
	public String checkByID(Scanner sc) {
		
		String loginID= sc.nextLine();
		//System.out.println("test");
		HashMap<String, User> usersMap = dataService.getUsers();
		if(usersMap.containsKey(loginID)) {
			System.out.print("Email ID already present. \nRedirect to login? y/n: ");
			loginID=sc.nextLine();
			if(loginID.equals("y")) {
				return "y";
			}
		}
		
		
		if(loginID.equals("n")) {
			System.out.println("Please enter email ID again");
			checkByID(sc);
		}
		System.out.println("test1");
		return loginID;
		
	}
	
	public String passwordMatch(Scanner sc) {
		Console console = System.console();
		if (console == null) {
            System.out.println("Console not available. Exiting...");
            System.exit(1);
        }
		char[] passwordChars = console.readPassword("Enter your password: ");
		String password = new String(passwordChars);
		char[] passwordChars1 = console.readPassword("Confirm password: ");
		String confirmPassword = new String(passwordChars1);
		if(password.equals(confirmPassword)) {
			
			return password;
		}else {
			System.out.println("Password did not match, Please try again");
			passwordMatch(sc);
		}
		
		return password;
	}
	public User register(UserType checkUser,Scanner sc) {
		
		user= new User();
		System.out.print("Please enter your First Name: ");
		user.setFirstName(sc.nextLine());
		System.out.print("Please enter your Last Name: ");
		user.setLastName(sc.nextLine());
		System.out.print("Please enter your LoginID(your E-mail ID): ");
		user.setLoginId(checkByID(sc));
		if(user.getLoginId().equals("y")) {
			
			return null;
		}
		System.out.println("test2");
		user.setPassword(passwordMatch(sc));
		HashMap<String, User> usersMap = dataService.getUsers();
		user.setUserType(checkUser);
		System.out.print("Please enter your contact no.: ");
		user.setContactNumber(sc.nextLine());
		usersMap.put(user.getLoginId(), user);
		dataService.saveUsers(usersMap);
		System.out.println("User registered successfully!! Please login to continue");
		return null;
	}

}
