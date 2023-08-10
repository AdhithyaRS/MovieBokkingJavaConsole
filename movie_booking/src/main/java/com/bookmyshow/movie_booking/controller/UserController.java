package com.bookmyshow.movie_booking.controller;


import java.util.Scanner;

import com.bookmyshow.movie_booking.service.UserService;


public class UserController {
	private UserService userService = new UserService();
	
	



	public void add(Scanner sc) {
		// TODO Auto-generated method stub
		userService.addMovie(sc);
		
	}



	public void remove(Scanner sc) {
		// TODO Auto-generated method stub
		userService.removeMovie(sc);
		
	}



	public void viewUpcomingTickets(Scanner sc) {
		// TODO Auto-generated method stub
		userService.viewUpcoming(sc);
	}



	public void ViewExpiredTickets(Scanner sc) {
		// TODO Auto-generated method stub
		userService.viewExpired(sc);
	}



	public void reset(Scanner sc) {
		// TODO Auto-generated method stub
		userService.resetData(sc);
	}



	public void backup() {
		// TODO Auto-generated method stub
		userService.backupData();
	}



	public void logs(Scanner sc) {
		// TODO Auto-generated method stub
		userService.viewLogs(sc);
	}



	public void ticketStatus(Scanner sc) {
		// TODO Auto-generated method stub
		userService.getStatus(sc);
		
	}



	public void viewAllMovies(Scanner sc) {
		// TODO Auto-generated method stub
		userService.getAllMovies(sc);
	}



	public void viewReleasedMovies(Scanner sc) {
		// TODO Auto-generated method stub
		userService.getReleased(sc);
	}



	public void viewUpcomingMovies(Scanner sc) {
		// TODO Auto-generated method stub
		userService.getUpcoming(sc);
	}



	public void searchByName(Scanner sc) {
		// TODO Auto-generated method stub
		userService.searchMovies(sc);
	}



	public void bookMovie(String LoginId,Scanner sc) {
		// TODO Auto-generated method stub
		userService.book(LoginId,sc);
	}



	public void viewOldBookings(String LoginId,Scanner sc) {
		// TODO Auto-generated method stub
		userService.getMyOldBookings(LoginId,sc);
	}



	public void viewUpcomingBookings(String LoginId,Scanner sc) {
		// TODO Auto-generated method stub
		userService.getMyUpcomingBookings(LoginId,sc);
	}
	
	
}
