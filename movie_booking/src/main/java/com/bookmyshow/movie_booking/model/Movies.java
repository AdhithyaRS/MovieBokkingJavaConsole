package com.bookmyshow.movie_booking.model;

import java.util.HashMap;

public class Movies {
	private String name;
    private HashMap<String, Availability> theatre;
    private HashMap<String, boolean[][]> seatDisplay;
    private HashMap<String,int[]> seatAvailability;
    private Status status;
    

	public enum Status {
        RELEASED,
        UPCOMING
    }
    
	public enum Availability {
        AVAILABLE,
        SOLD_OUT,
        BOOKING_FAST
    }
	
    public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, Availability> getTheatre() {
		return theatre;
	}
	public void setTheatre(HashMap<String, Availability> theatre) {
		this.theatre = theatre;
	}

	public HashMap<String, boolean[][]> getSeatDisplay() {
		return seatDisplay;
	}
	public void setSeatDisplay(HashMap<String, boolean[][]> seatDisplay) {
		this.seatDisplay = seatDisplay;
	}
	public HashMap<String, int[]> getSeatAvailability() {
		return seatAvailability;
	}
	public void setSeatAvailability(HashMap<String, int[]> seatAvailability) {
		this.seatAvailability = seatAvailability;
	}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    
	    if (status == Status.RELEASED) {
	        sb.append("Movie Status: ").append(status).append("\n");
	        for (String theatreName : theatre.keySet()) {
	            sb.append("Theatre name: ").append(theatreName).append("\n");
	            sb.append("Theatre booking status: ").append(theatre.get(theatreName)).append("\n");
	        }
	    } else {
	        sb.append("Movie Status: ").append(status).append("\n");
	    }
	    
	    return sb.toString();
	}


    

}
