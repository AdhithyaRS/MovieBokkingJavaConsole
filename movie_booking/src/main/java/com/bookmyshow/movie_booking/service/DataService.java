package com.bookmyshow.movie_booking.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.bookmyshow.movie_booking.model.Movies;
import com.bookmyshow.movie_booking.model.Tickets;
import com.bookmyshow.movie_booking.model.User;
import com.bookmyshow.movie_booking.model.User.Validitity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("unused")
public class DataService {
	Gson gson = new Gson();
	private String userFilePath = "C:/Users/toadh/LocalDB/users.json";
	private String movieFilePath = "C:/Users/toadh/LocalDB/movies.json";
	private String ticketFilePath = "C:/Users/toadh/LocalDB/tickets.json";
	private String singleTicketFilePath = "C:/Users/toadh/LocalDB/SingleTicket.json";
	private String backupUserFilePath = "C:/Users/toadh/LocalDB/BackUp/users.json";
	private String backupMovieFilePath = "C:/Users/toadh/LocalDB/BackUp/movies.json";
	private String backupTicketFilePath = "C:/Users/toadh/LocalDB/BackUp/tickets.json";
	private String logsFilePath = "C:/Users/toadh/LocalDB/ConsoleLogs/log_files.json";
	
	public HashMap<String, User> getUsers(){
		try (FileReader fileReader = new FileReader(userFilePath)) {
            Type type = new TypeToken<HashMap<String, User>>() {}.getType();
            return gson.fromJson(fileReader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public void saveUsers(HashMap<String, User> updatedUsersMap){
		try (FileWriter fileWriter = new FileWriter(userFilePath)) {
            Type type = new TypeToken<HashMap<String, User>>() {}.getType();
            gson.toJson(updatedUsersMap, type, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	// Load the JSON file into a HashMap
	public HashMap<String, String> loadLogFilesFromJson() {
		try (FileReader fileReader = new FileReader(logsFilePath)) {
	        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
	        return gson.fromJson(fileReader, type);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public HashMap<Validitity, ArrayList<Tickets>> loadTicketsFilesFromJson() {
		// TODO Auto-generated method stub
		try (FileReader fileReader = new FileReader(ticketFilePath)) {
	        Type type = new TypeToken<HashMap<Validitity, ArrayList<Tickets>>>() {}.getType();
	        return gson.fromJson(fileReader, type);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public void saveTickets(HashMap<Validitity, ArrayList<Tickets>> updatedtickets) {
		// TODO Auto-generated method stub
		try (FileWriter fileWriter = new FileWriter(ticketFilePath)) {
            Type type = new TypeToken<HashMap<Validitity, ArrayList<Tickets>>>() {}.getType();
            gson.toJson(updatedtickets, type, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public HashMap<Validitity, HashMap<String, Tickets>> loadSingleTicketFilesFromJson() {
		// TODO Auto-generated method stub
		try (FileReader fileReader = new FileReader(singleTicketFilePath)) {
	        Type type = new TypeToken<HashMap<Validitity, HashMap<String, Tickets>>>() {}.getType();
	        return gson.fromJson(fileReader, type);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public void saveSingleTicket(HashMap<Validitity, HashMap<String, Tickets>> updatedSingleTicket) {
		// TODO Auto-generated method stub
		try (FileWriter fileWriter = new FileWriter(singleTicketFilePath)) {
            Type type = new TypeToken<HashMap<Validitity, HashMap<String, Tickets>>>() {}.getType();
            gson.toJson(updatedSingleTicket, type, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public HashMap<String, Movies> loadMoviesFilesFromJson() {
		// TODO Auto-generated method stub
		try (FileReader fileReader = new FileReader(movieFilePath)) {
	        Type type = new TypeToken<HashMap<String, Movies>>() {}.getType();
	        return gson.fromJson(fileReader, type);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public void saveMovie(HashMap<String, Movies> updatedMovies) {
		// TODO Auto-generated method stub
		try (FileWriter fileWriter = new FileWriter(movieFilePath)) {
            Type type = new TypeToken<HashMap<String, Movies>>() {}.getType();
            gson.toJson(updatedMovies, type, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}



}
