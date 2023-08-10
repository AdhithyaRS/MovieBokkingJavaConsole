package com.bookmyshow.movie_booking.service;

import java.io.Console;
import java.util.HashMap;
import java.util.Scanner;

import com.bookmyshow.movie_booking.model.User;

public class PasswordResetService {
	
	private DataService dataService = new DataService();

	public User resetPassword(String loginId, Scanner sc) {
		
		System.out.print("\033[H\033[2J");
		Console console = System.console();
		if (console == null) {
            System.out.println("Console not available. Exiting...");
            System.exit(1);
        }
        HashMap<String, User> usersMap = dataService.getUsers();
        User user = usersMap.get(loginId);
        char[] passwordChars = console.readPassword("Please enter a new password: ");
		String newPassword = new String(passwordChars);
		char[] passwordChars1 = console.readPassword("Retype new password: ");
		String retypeNewPassword = new String(passwordChars1);
        if(newPassword.equals(retypeNewPassword)) {
        	user.setPassword(newPassword);
        	usersMap.put(loginId, user);
        	dataService.saveUsers(usersMap);
        	System.out.println("Password reset successfull!!");
        }else {
        	System.out.println("The entered password did not match Please Try again!!");
        	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	resetPassword(loginId, sc);
        }
        
		return null;
		
	}

}
