package com.bookmyshow.movie_booking.controller;

import java.util.Scanner;

import com.bookmyshow.movie_booking.model.User;
import com.bookmyshow.movie_booking.model.User.UserType;
import com.bookmyshow.movie_booking.service.PasswordResetService;
import com.bookmyshow.movie_booking.service.SignInService;
import com.bookmyshow.movie_booking.service.SignUpService;

public class AuthController {
	private SignInService signInService = new SignInService();
	private SignUpService signUpService = new SignUpService();
	private PasswordResetService passwordResetService = new PasswordResetService();
	
	
	public User signIn(Scanner sc) {
		return signInService.validate(sc);
		
	}
	public User signUp(UserType checkUser, Scanner sc) {
		return signUpService.register(checkUser, sc);
	}
	public User passwordReset(String loginId, Scanner sc) {
		return passwordResetService.resetPassword(loginId, sc);
	}
	
	
}
