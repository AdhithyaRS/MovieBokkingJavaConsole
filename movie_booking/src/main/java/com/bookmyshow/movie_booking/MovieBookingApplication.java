package com.bookmyshow.movie_booking;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bookmyshow.movie_booking.controller.AuthController;
import com.bookmyshow.movie_booking.controller.UserController;
import com.bookmyshow.movie_booking.model.User;
import com.bookmyshow.movie_booking.model.User.UserType;

public class MovieBookingApplication 
{
	private static final HashMap<String, StringBuilder> logFiles = new HashMap<>();

	public static void main( String[] args )
    {
		try {
			loadLogFilesFromJson();
			LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fileName = "console_logs_" + currentDate.format(formatter) + ".txt";
            String folderPath = "C:\\Users\\toadh\\LocalDB\\ConsoleLogs";
            Files.createDirectories(Paths.get(folderPath));
            String filePath = folderPath + "\\" + fileName;
            FileOutputStream fileOut = new FileOutputStream(filePath,true);
            CustomOutputStream customOut = new CustomOutputStream(System.out, fileOut, fileName);
            System.setOut(new PrintStream(customOut));
            Scanner sc = new Scanner(System.in);
	        String terminate = "no";
	        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	            saveLogFilesToJson();
	        }));
	        while(terminate.equals("no")) {
	        	System.out.println( "Book My Show Application !!\n1) User\n2) Admin" );
	            int[] option= new int[2];
	            option[0]= sc.nextInt();
	            sc.nextLine();
	            UserType checkUser= option[0] == 1 ? User.UserType.USER : User.UserType.ADMIN;
	            if(option[0]==1 || option[0]==2) {
	            	System.out.print("\033[H\033[2J");
	            	AuthController  authController= new AuthController();
	            	User user =null;
	            	while(user==null) {
	            		System.out.println( "\n1) Sign In\n2) Sign Up\n3) Exit" );
	                	option[1]=sc.nextInt();
	                	sc.nextLine();
	                	if(option[1]==1) {
	                		System.out.println("Redirecting to Sign In Page");
	                		try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                		user = authController.signIn(sc);
	                		
	                	}else if(option[1]==2) {
	                		System.out.println("Redirecting to Sign Up Page");
	                		try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                		user = authController.signUp(checkUser,sc);
	                	}else if(option[1]==3) {
	                		break;
	                	}else {
	                		System.out.println("Enter a valid option!!");
	                	}
	                	try {
	                		
	                		if(user!=null) {
	                			
	                			if(checkUser != user.getUserType()) {
	                				System.out.println("You dont have "+checkUser+" rights!! Please login as "+ user.getUserType());
	                				user=null;
	                				option[1]=3;
	                				System.out.println("Press enter to continue");
	                				sc.nextLine();
	                				break;
	                			}else {
	                				System.out.println("Signed In successfully as "+ user.getUserType() +"!! \nRedirecting to Home page");
	                			}
	                		}else System.out.println("Try Again!!");
	                        Thread.sleep(5000);
	                        System.out.println("\033[H\033[2J");
	                    } catch (InterruptedException e) {
	                        
	                        e.printStackTrace();
	                    }
	            	}
	            	if(option[1]!=3 && user.getUserType()==UserType.USER) {
	            		UserController userController = new UserController();
	            		String exit= "no";
	            		while(exit.equals("no")) {
	            			System.out.println("1) View all movies\n2) View all released movies\n3) View all upcoming movies\n4) View movies by name\n5) Book a movie\n6) View My old bookings\n7) View my upcoming bookings\n8) Reset password\n9) Sign Out");
	            			int userOption=sc.nextInt();
	            			sc.nextLine();
	                		if(userOption==1) {
	                			userController.viewAllMovies(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==2) {
	                			userController.viewReleasedMovies(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==3) {
	                			userController.viewUpcomingMovies(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==4) {
	                			userController.searchByName(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==5) {
	                			userController.bookMovie(user.getLoginId(),sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==6) {
	                			userController.viewOldBookings(user.getLoginId(),sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==7) {
	                			userController.viewUpcomingBookings(user.getLoginId(),sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==8) {
	                			authController.passwordReset(user.getLoginId(), sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==9) {
	                			exit="yes";
	                		}else {
	                			System.out.println("Please enter a valid option");
	                			System.out.print("\033[H\033[2J");
	                		}
	            		}
	            	}else if(option[1]!=3 && user.getUserType()==UserType.ADMIN) {
	            		UserController userController = new UserController();
	            		String exit= "no";
	            		while(exit.equals("no")) {
	            			System.out.println("1) Add/Update a movie\n2) Remove a movie\n3) Ticket Status\n4) View all upcoming tickets\n5) View all expired tickets\n6) Reset Data\n7) Backup\n8) View Logs\n9) Reset Password\n10) Sign Out\n11) Terminate Application");
	            			int userOption=sc.nextInt();
	            			sc.nextLine();
	                		if(userOption==1) {
	                			userController.add(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==2) {
	                			userController.remove(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==3) {
	                			userController.ticketStatus(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==4) {
	                			userController.viewUpcomingTickets(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==5) {
	                			userController.ViewExpiredTickets(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==6) {
	                			System.out.println("\nAre you sure want to reset data?y/n");
	                			String opinion=sc.nextLine();
	                			if(opinion.equals("y")) userController.reset(sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==7) {
	                			userController.backup();
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==8) {
	                			customOut.setWriteToLogFile(false);
	                			userController.logs(sc);
	                			customOut.setWriteToLogFile(true);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==9) {
	                			authController.passwordReset(user.getLoginId() ,sc);
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==10) {
	                			exit="yes";
	                			System.out.print("\033[H\033[2J");
	                		}else if(userOption==11) {
	                			terminate="yes";
	                			exit="yes";
	                			System.out.print("\033[H\033[2J");
	                		}else {
	                			System.out.println("Please enter a valid option");
	                			System.out.print("\033[H\033[2J");
	                		}
	            		}
	            		
	            	}
	            	
	            }else {
	            	System.out.println("Enter a valid option!!");
	            	try {
	            		System.out.println("Try Again!!");
	                    Thread.sleep(5000);
	                    System.out.print("\033[H\033[2J");
	                } catch (InterruptedException e) {
	                    
	                    e.printStackTrace();
	                }
	            }
	            System.out.print("\033[H\033[2J");
	        } 
	        sc.close();
	        customOut.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private static void loadLogFilesFromJson() {
	    try {
	        Gson gson = new Gson();
	        String jsonFilePath = "C:\\Users\\toadh\\LocalDB\\ConsoleLogs\\log_files.json";
	        Type type = new TypeToken<HashMap<String, String>>() {}.getType();

	        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
	        HashMap<String, String> logDataMap = gson.fromJson(jsonContent, type);

	        for (String logFileName : logDataMap.keySet()) {
	            String logData = logDataMap.get(logFileName);
	            logFiles.put(logFileName, new StringBuilder(logData));
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void saveLogFilesToJson() {
        try {
            Gson gson = new Gson();
            String jsonFilePath = "C:\\Users\\toadh\\LocalDB\\ConsoleLogs\\log_files.json";
            Type type = new TypeToken<HashMap<String, String>>(){}.getType();

            
            HashMap<String, String> logMessages = new HashMap<>();
            for (String logFileName : logFiles.keySet()) {
                StringBuilder logData = logFiles.get(logFileName);
                logMessages.put(logFileName, logData.toString());
            }

            String json = gson.toJson(logMessages, type);
            Files.write(Paths.get(jsonFilePath), json.getBytes());
            System.out.println("Log data saved to JSON file: " + jsonFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private static class CustomOutputStream extends OutputStream {
	    private final PrintStream consoleOut;
	    private final OutputStream fileOut;
	    private final StringBuilder logData;
	    private final String logFileName;
	    private final InputStream inputStream;
	    private boolean writeToLogFile = true;

	    public CustomOutputStream(PrintStream consoleOut, OutputStream fileOut, String logFileName) {
	        this.consoleOut = consoleOut;
	        this.fileOut = fileOut;
	        this.logData = new StringBuilder();
	        this.logFileName = logFileName;
	        this.inputStream = new CustomInputStream(System.in);
	    }

	    @Override
	    public void write(int b) throws IOException {
	        // Write to both console and file
	        consoleOut.write(b);
	        if (writeToLogFile) {
	        	logData.append((char) b);
		        //System.out.println("write 1: "+logData);
		        fileOut.write(b); // Write to the log file
	        }
	    }

	    @Override
	    public void write(byte[] b) throws IOException {
	        // Write to both console and file
	        consoleOut.write(b);
	        for (byte value : b) {
	            logData.append((char) value); // Capture log messages as strings
	            
	        }
	        fileOut.write(b); // Write to the log file
	        //System.out.println("write 2: "+logData);
	    }

	    @Override
	    public void write(byte[] b, int off, int len) throws IOException {
	        // Write to both console and file
	        consoleOut.write(b, off, len);
	        for (int i = off; i < off + len; i++) {
	            logData.append((char) b[i]);
	            
	        }
	        fileOut.write(b, off, len);
	    }
	    
	    public void setWriteToLogFile(boolean writeToLogFile) {
	        this.writeToLogFile = writeToLogFile;
	    }
	    
	    @Override
	    public void close() throws IOException {
	    	//System.out.println("write 3: "+logFiles.get(logFileName));
	        super.close();
	        fileOut.close();
	        logFiles.put(logFileName, logData);
	        ((CustomInputStream) inputStream).close();
	    }
	    private class CustomInputStream extends InputStream {
	        private final InputStream originalInputStream;

	        public CustomInputStream(InputStream originalInputStream) {
	            this.originalInputStream = originalInputStream;
	        }

	        @Override
	        public int read() throws IOException {
	            int data = originalInputStream.read();
	            logData.append((char) data);
	            return data;
	        }

	        @Override
	        public int read(byte[] b) throws IOException {
	            int bytesRead = originalInputStream.read(b);
	            if (bytesRead > 0) {
	                logData.append(new String(b, 0, bytesRead));
	            }
	            return bytesRead;
	        }

	        @Override
	        public int read(byte[] b, int off, int len) throws IOException {
	            int bytesRead = originalInputStream.read(b, off, len);
	            if (bytesRead > 0) {
	                logData.append(new String(b, off, bytesRead));
	            }
	            return bytesRead;
	        }

	        @Override
	        public void close() throws IOException {
	            originalInputStream.close();
	        }
	    }
	}
}