package com.bookmyshow.movie_booking.service;

import java.io.Console;
import java.util.HashMap;
import java.util.Scanner;
import com.bookmyshow.movie_booking.model.User;

public class SignInService {
	private DataService dataService = new DataService();
	private PasswordResetService passwordResetService= new PasswordResetService();

	
	public User validate(Scanner sc) {
		
		String loginId;
		String password;
		System.out.print("\033[H\033[2J");
		System.out.print("Enter your Login ID: ");
		loginId= sc.nextLine();
		Console console = System.console();
		if (console == null) {
            System.out.println("Console not available. Exiting...");
            System.exit(1);
        }
		char[] passwordChars = console.readPassword("Enter your password: ");
        password = new String(passwordChars);
        HashMap<String, User> usersMap = dataService.getUsers();
        User user = usersMap.get(loginId);
        if (user != null) {
            System.out.println("User found: " + user.getFirstName() + " " + user.getLastName());
            if(user.getPassword().equals(password)) {
            	
            	return user;
            }else {
            	System.out.println("Incorrect Password!!\n1) Try again\n2) Reset Password");
            	int option= sc.nextInt();
            	if(option==1) {
            		
            		return null;
            	}else {
            		
            		System.out.println("Redirecting to password reset page");
            		try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		
            		return passwordResetService.resetPassword(loginId,sc);
            	}
            }
        } else {
            System.out.println("User not found. Please Sign Up");
        }
        
		return null;
	}

}
