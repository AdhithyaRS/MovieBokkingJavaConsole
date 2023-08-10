package com.bookmyshow.movie_booking.model;

import com.bookmyshow.movie_booking.model.User.Validitity;

public class Tickets {
	private String ticketID;
	private String bookedBy;
	private String movie;
	private String theatre;
	private int ticketCount;
	private String seatNo;
	private Validitity validitity; 
	
	public Validitity getValiditity() {
		return validitity;
	}
	public void setValiditity(Validitity validitity) {
		this.validitity = validitity;
	}
	public String getBookedBy() {
		return bookedBy;
	}
	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}
	public String getTicketID() {
		return ticketID;
	}
	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}
	public String getMovie() {
		return movie;
	}
	@Override
	public String toString() {
		return "Tickets [ticketID=" + ticketID + ", bookedBy=" + bookedBy + ", movie=" + movie + ", theatre=" + theatre
				+ ", ticketCount=" + ticketCount + ", seatNo=" + seatNo + ", validitity=" + validitity + "]";
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getTheatre() {
		return theatre;
	}
	public void setTheatre(String theatre) {
		this.theatre = theatre;
	}
	public int getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}



}
