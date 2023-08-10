package com.bookmyshow.movie_booking.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import com.bookmyshow.movie_booking.model.Movies;
import com.bookmyshow.movie_booking.model.Movies.Availability;
import com.bookmyshow.movie_booking.model.Movies.Status;
import com.bookmyshow.movie_booking.model.Tickets;
import com.bookmyshow.movie_booking.model.User;
import com.bookmyshow.movie_booking.model.User.Validitity;

public class UserService {
	private DataService dataService = new DataService();

	public void addMovie(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<String, Movies> movies = dataService.loadMoviesFilesFromJson();
		System.out.println("1) Update movie status\n2) Add a new movie");
		int option = sc.nextInt();
		sc.nextLine();
		if(option==1) {
			System.out.println("Please view the upcoming movies");
			for(String movieName: movies.keySet()) {
				System.out.print(movies.get(movieName).getStatus()==Status.UPCOMING?movieName+"\n":"");
			}
			System.out.println("Please enter a movie name to change the status");
			Movies movie= getMovieDetails(movies.get(sc.nextLine()),sc);
			movies.put(movie.getName(), movie);
			dataService.saveMovie(movies);
			System.out.println("Movie details updated successfully!!\nRedirecting to home page");
		}else {
			Movies movie = new Movies();
			System.out.println("Please enter a movie name");
			movie.setName(sc.nextLine());
			System.out.println("Enter the movie status: RELEASED/UPCOMING?");
			movie.setStatus(sc.nextLine().equals("RELEASED")?Status.RELEASED:Status.UPCOMING);
			if(movie.getStatus()==Status.UPCOMING) {
				movies.put(movie.getName(), movie);
				dataService.saveMovie(movies);
				System.out.println("Movie details added successfully!!\nRedirecting to home page");
				return;
			}else {
				movie = getMovieDetails(movie,sc);
				movies.put(movie.getName(), movie);
				dataService.saveMovie(movies);
				System.out.println("Movie details added successfully!!\nRedirecting to home page");
			}
		}
		System.out.println("press enter to go back to main menu");
		sc.nextLine();
	}

	private Movies getMovieDetails(Movies movie, Scanner sc) {
		// TODO Auto-generated method stub
		movie.setStatus(Status.RELEASED);
		HashMap<String, Availability> theatres = new HashMap<>();
		HashMap<String, boolean[][]> seatDisplay = new HashMap<>();
		HashMap<String,int[]> seatAvailability = new HashMap<>();
		String add="y";
		while(add.equals("y")) {
			System.out.println("Please enter theatre name");
			String theatre = sc.nextLine();
			theatres.put(theatre, Availability.AVAILABLE);
			System.out.println("Enter no of rows");
			int rows = sc.nextInt();
			System.out.println("Enter no of columns");
			int columns = sc.nextInt();
			sc.nextLine();
			boolean[][] display = new boolean[rows][columns];
			seatDisplay.put(theatre, display);
			int[] available = new int[2];
			available[0]= rows*columns;
			seatAvailability.put(theatre, available);
			System.out.println("Want to add another theatre? y/n");
			add=sc.nextLine();
		}
		movie.setTheatre(theatres);
		movie.setSeatAvailability(seatAvailability);
		movie.setSeatDisplay(seatDisplay);
		return movie;
		
	}

	public void removeMovie(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<String, Movies> movies = dataService.loadMoviesFilesFromJson();
		System.out.println("Please view the movies list");
		for(String movieName: movies.keySet()) {
			System.out.println(movieName);
		}
		System.out.println("Please enter a movie name to remove");
		movies.remove(sc.nextLine());
		dataService.saveMovie(movies);
	}
	
	public void getStatus(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<Validitity, HashMap<String, Tickets>> logs = dataService.loadSingleTicketFilesFromJson();
		int option = 1;
		while(option==1) {
			System.out.println("Please enter ticket ID");
			String id= sc.nextLine();
			if(logs.get(Validitity.VALID).containsKey(id)) {
				System.out.println(logs.get(Validitity.VALID).get(id).toString());
			}else if(logs.get(Validitity.EXPIRED).containsKey(id)){
				System.out.println(logs.get(Validitity.EXPIRED).get(id).toString());
			}else {
				System.out.println("Please enter a valid ticket");
			}
			System.out.println("1) Retry/check another ticket\n2) Go to main menu");
			option=sc.nextInt();
			sc.nextLine();
		}
	}

	public void viewUpcoming(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<Validitity, ArrayList<Tickets>> logs = dataService.loadTicketsFilesFromJson();
		if(logs==null) {
			System.out.println("There is no upcoming Tickets to display\nPress enter to return to main menu");
			sc.nextLine();
			return;
		}
		ArrayList<Tickets> tickets= logs.get(Validitity.VALID);
		if(tickets==null) {
			System.out.println("There is no upcoming Tickets to display\nPress enter to return to main menu");
			sc.nextLine();
			return;
		}
		for(Tickets ticket: tickets) {
			System.out.println("->\n"+ticket.toString());
		}
		System.out.println("\n->Please press enter to go back");
		sc.nextLine();
		
	}

	public void viewExpired(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<Validitity, ArrayList<Tickets>> logs = dataService.loadTicketsFilesFromJson();
		if(logs==null) {
			System.out.println("There is no expired Tickets to display\nPress enter to return to main menu");
			sc.nextLine();
			return;
		}
		ArrayList<Tickets> tickets= logs.get(Validitity.EXPIRED);
		if(tickets==null) {
			System.out.println("There is no expired Tickets to display\nPress enter to return to main menu");
			sc.nextLine();
			return;
		}
		for(Tickets ticket: tickets) {
			System.out.println("->\n"+ticket.toString());
		}
		System.out.println("\n->Please press enter to go back");
		sc.nextLine();
		
	}

	public void resetData(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("This option yet to be implemented");
	}

	public void backupData() {
		// TODO Auto-generated method stub
		System.out.println("This option yet to be implemented");
	}

	public void viewLogs(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<String, String> logs = dataService.loadLogFilesFromJson();
		String date=null;
		
		if(logs==null) {
			System.out.println("There are no logs yet, Please restart the application!!");
			return;
		}
		String stop="n";
		while(stop.equals("n")) {
			
			System.out.println("1) Please enter a date in format :\"yyyy-MM-dd\"\n2) To go back to main menu");
			int option = sc.nextInt();
			sc.nextLine();
			if(option==1) {
				date=sc.nextLine();
				date="console_logs_"+date+".txt";
				if(logs.containsKey(date)) {
					System.out.println("--------------->START");
					System.out.println(logs.get(date));
					System.out.println("--------------->END");
					
				}else System.out.println("No logs available in the mentioned date or check the format!!");
				System.out.println("1) continue logs menu\n2) Go to main menu");
				option= sc.nextInt();
				sc.nextLine();
				if(option==2) return;
			}else return;
			
		}		
	}

	public void getAllMovies(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<String, Movies> movies = dataService.loadMoviesFilesFromJson();
		for(String movieName: movies.keySet()) {
			System.out.println(movieName+" : "+movies.get(movieName).getStatus());
		}
		System.out.println("Press enter to return to main menu");
		sc.nextLine();
	}

	public void getReleased(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<String, Movies> movies = dataService.loadMoviesFilesFromJson();
		for(String movieName: movies.keySet()) {
			if(movies.get(movieName).getStatus()== Status.RELEASED) System.out.println(movieName);
		}
		System.out.println("Press enter to return to main menu");
		sc.nextLine();
	}

	public void getUpcoming(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<String, Movies> movies = dataService.loadMoviesFilesFromJson();
		for(String movieName: movies.keySet()) {
			if(movies.get(movieName).getStatus()== Status.UPCOMING) System.out.println(movieName);
		}
		System.out.println("Press enter to return to main menu");
		sc.nextLine();
	}

	public void searchMovies(Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<String, Movies> movies = dataService.loadMoviesFilesFromJson();
		System.out.println("Please select type of search\n1) Starts with\n2) Ends with\n3) Random");
		int option = sc.nextInt();
		sc.nextLine();
		System.out.println("Please enter a movie name or a keyword");
		String keyword = sc.nextLine();
		//System.out.println(keyword);
		if(option==1) {
			
			for(String movieName: movies.keySet()) {
				
				if(movieName.startsWith(keyword)) {
					
					System.out.println("->"+movieName+" :\n"+movies.get(movieName).toString());
				}
			}
		}else if(option==2) {
			
			for(String movieName: movies.keySet()) {
				
				if(movieName.endsWith(keyword)) {
					System.out.println("->"+movieName+" :\n"+movies.get(movieName).toString());
				}
			}
		}else if(option==3) {
			
			for(String movieName: movies.keySet()) {
				
				if(movieName.contains(keyword)) {
					System.out.println("->"+movieName+" :\n"+movies.get(movieName).toString());
				}
			}
		}
		System.out.println("Press enter to return to main menu");
		sc.nextLine();
	}

	public void book(String LoginId,Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<Validitity, ArrayList<Tickets>> ticketsLogs = dataService.loadTicketsFilesFromJson();
		HashMap<Validitity, HashMap<String, Tickets>> SingleTicketLogs = dataService.loadSingleTicketFilesFromJson();
		HashMap<String, Tickets> allTickets = SingleTicketLogs.get(Validitity.EXPIRED);
		allTickets.putAll(SingleTicketLogs.get(Validitity.VALID));
		String reset="y";
		HashMap<String, User> usersMap = dataService.getUsers();
		User user = usersMap.get(LoginId);
		HashMap<String, Movies> movies = dataService.loadMoviesFilesFromJson();
		Movies movie;
		HashMap<String, Availability> theatreStatus;
	    HashMap<String, boolean[][]> seatDisplay;
	    HashMap<String,int[]> seatAvailability;
		while(reset.equals("y")) {
			System.out.println("\033[H\033[2J");
			System.out.println("PLease view the released movies");
			for(String movieName: movies.keySet()) {
				if(movies.get(movieName).getStatus()== Status.RELEASED) {
					getMovieDetails(movies.get(movieName));
				}
			}			
			System.out.println("Please enter a movie name to book");
			String userMovie = sc.nextLine();
			movie = movies.get(userMovie);
			if(movie==null) {
				System.out.println("The entered movie name is not present or retype the movie name properly");
				System.out.println("PLease press enter to retry again!!");
				sc.nextLine();
				continue;
			}
			System.out.println("Please enter a theatre name to book");
			String userTheatre = sc.nextLine();
			theatreStatus = movie.getTheatre();
			if(!theatreStatus.containsKey(userTheatre)) {
				System.out.println("The movie is not running on the selected theatre or please enter the correct name!!\nPress enter to retry again");
				sc.nextLine();
				continue;
			}
			seatDisplay = movie.getSeatDisplay();
			boolean[][] seats = seatDisplay.get(userTheatre); 
			seatAvailability = movie.getSeatAvailability();
			int[] available = seatAvailability.get(userTheatre);
			int free= available[0]-available[1];
			System.out.println("Enter the number of tickets to book");
			int userTickets = sc.nextInt();
			sc.nextLine();
			if(userTickets>free) {
				System.out.println("The is only "+free+" tickets available in the selected theatre\nTry booking another theatre which has required numbers\nPress enter to try again");
				sc.nextLine();
				continue;
			}
			System.out.println("Please view the "+userTheatre+" layout displayed to select the available seats numbers");
			
			displayTheatre(seats, userTheatre);
			String seatNumber="";
			for(int i=0;i<userTickets;i++) {
				System.out.println("Please enter the row of "+ (i+1) +" ticket");
				int row= sc.nextInt();
				System.out.println("Please enter the column of "+ (i+1) +" ticket");
				int column= sc.nextInt();
				if(row<=seats[0].length && column<=seats[1].length && row>0 && column>0 && !seats[row-1][column-1] ) {
					seats[row-1][column-1]=true;
					if(i<userTickets-1) {
						seatNumber=seatNumber+((char)('A'+(row-1)))+column+",";
					}else {
						seatNumber=seatNumber+((char)('A'+(row-1)))+column;
					}
				}else {
					System.out.println("The selected seat is not available!!\n Try again");
					i--;
				}
			}
			reset="n";
			System.out.println("Your booking is processing!! Please wait");
			String ticketID=getTicketId(allTickets);
			seatDisplay.put(userTheatre, seats);
			available[1]=available[1]+userTickets;
			seatAvailability.put(userTheatre, available);
			if(available[1]> available[0]/2) {
				if(available[0]==available[1]) {
					theatreStatus.put(userTheatre, Availability.SOLD_OUT);
				}
				theatreStatus.put(userTheatre, Availability.BOOKING_FAST);
			}
			movie.setSeatAvailability(seatAvailability);
			movie.setSeatDisplay(seatDisplay);
			movie.setTheatre(theatreStatus);
			movies.put(userMovie, movie);
			dataService.saveMovie(movies);
			Tickets newTicket = new Tickets();
			newTicket.setBookedBy(LoginId);
			newTicket.setMovie(userMovie);
			newTicket.setSeatNo(seatNumber);
			newTicket.setTicketID(ticketID);
			newTicket.setTheatre(userTheatre);
			newTicket.setTicketCount(userTickets);
			newTicket.setValiditity(Validitity.VALID);
			ticketsLogs.get(Validitity.VALID).add(newTicket);
			SingleTicketLogs.get(Validitity.VALID).put(ticketID, newTicket);
			dataService.saveSingleTicket(SingleTicketLogs);
			dataService.saveTickets(ticketsLogs);
			System.out.println("Your booking processed successfully!! Please view the ticket details");
			System.out.println(newTicket.toString());
			if (user.getMyBookings() == null) {
			    user.setMyBookings(new HashMap<>());
			}

			if (!user.getMyBookings().containsKey(Validitity.VALID)) {
			    user.getMyBookings().put(Validitity.VALID, new ArrayList<>());
			}
			user.getMyBookings().get(Validitity.VALID).add(newTicket);
			usersMap.put(LoginId, user);
			dataService.saveUsers(usersMap);
			System.out.println("Press enter to go to main menu");
			sc.nextLine();
			sc.nextLine();
		}
	}
	
	

	private String getTicketId(HashMap<String, Tickets> allTickets) {
		// TODO Auto-generated method stub
		StringBuilder ticketId = new StringBuilder("T");
		Random random = new Random();
		while(ticketId.toString().length()!=16) {
			for (int i = 0; i < 15; i++) {
	            int digit = random.nextInt(10);
	            ticketId.append(digit);
	        }
			if(allTickets.containsKey(ticketId.toString())) {
				ticketId.setLength(0);
				ticketId.append('T');
			}
		}

        return ticketId.toString();
	}


	private void displayTheatre(boolean[][] seats, String userTheatre) {
	    System.out.println("O: Available seats");
	    System.out.println("*: Occupied seat\n ");

	    int maxSeatNumWidth = String.valueOf(seats[0].length).length();
	    int maxRowLabelWidth = String.valueOf(seats.length).length();
	    System.out.print("  ");
	    for (int seatNum = 1; seatNum <= seats[0].length; seatNum++) {
	        int padding = maxSeatNumWidth - String.valueOf(seatNum).length();
	        String seatNumStr = " ".repeat(padding) + seatNum;
	        System.out.print(seatNumStr + " ");
	    }
	    System.out.println();

	    for (int i = 0; i < seats.length; i++) {
	        int paddingRowLabel = maxRowLabelWidth - String.valueOf(i + 1).length();
	        String rowLabel = "".repeat(paddingRowLabel) + (i + 1) + "  ";
	        System.out.print(rowLabel);
	        
	        for (int j = 0; j < seats[i].length; j++) {
	            int paddingSeat = maxSeatNumWidth - 1;
	            String seatDisplay = !seats[i][j] ? "O" : "*";
	            String paddedSeatDisplay = " ".repeat(paddingSeat) + seatDisplay;
	            System.out.print(paddedSeatDisplay + " ");
	        }
	        System.out.println();
	    }
	}


	private void getMovieDetails(Movies movie) {
		// TODO Auto-generated method stub
		HashMap<String, Availability> theatreStatus = movie.getTheatre();
	    HashMap<String,int[]> seatAvailability = movie.getSeatAvailability();
		System.out.println("->Movie name: "+ movie.getName());
		System.out.println("Theatres: ");
		for(String theatre: theatreStatus.keySet()) {
			int[] seat = seatAvailability.get(theatre);
			int available = seat[0]-seat[1];
			System.out.println("Theatre name: \""+theatre+"\" Theatre status: "+theatreStatus.get(theatre)+" Seats available: "+available);
		}
	}

	public void getMyOldBookings(String LoginId,Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<String, User> usersMap = dataService.getUsers();
		HashMap<Validitity, ArrayList<Tickets>> bookings = usersMap.get(LoginId).getMyBookings();
		
		if(bookings==null) {
			System.out.println("There is no past booking to display\nPress enter to return to main menu");
			sc.nextLine();
			return;
		}
		ArrayList<Tickets> tickets = bookings.get(Validitity.EXPIRED);
		if(tickets==null) {
			System.out.println("There is no past booking to display\nPress enter to return to main menu");
			sc.nextLine();
			return;
		}
		for(Tickets ticket : tickets) {
			System.out.println(ticket.toString());
		}
		System.out.println("\n->Please press enter to go back");
		sc.nextLine();
	}

	public void getMyUpcomingBookings(String LoginId,Scanner sc) {
		// TODO Auto-generated method stub
		HashMap<String, User> usersMap = dataService.getUsers();
		HashMap<Validitity, ArrayList<Tickets>> bookings = usersMap.get(LoginId).getMyBookings();
		if(bookings==null) {
			System.out.println("There is no upcoming booking to display\nPress enter to return to main menu");
			sc.nextLine();
			return;
		}
		ArrayList<Tickets> tickets = bookings.get(Validitity.VALID);
		if(tickets==null) {
			System.out.println("There is no Upcoming booking to display\nPress enter to return to main menu");
			sc.nextLine();
			return;
		}
		for(Tickets ticket : tickets) {
			System.out.println(ticket.toString());
		}
		System.out.println("\n->Please press enter to go back");
		sc.nextLine();
	}


}
