package exercise_1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server class reads a string message from socket, examines 
 * it to determine whether or not it is a palindrome and sends an 
 * appropriate message back to the client.
 * 
 * @author Ziad Chemali and Lotfi Hasni
 * @version 1.0
 * @since October 30, 2020
 *
 */
public class Server {

	/**
	 * Socket for communicating with client.
	 */
	private Socket aSocket;
	
	/**
	 * Server socket used to listen for requests.
	 */
	private ServerSocket serverSocket;
	
	/**
	 * Stream for writing to socket.
	 */
	private PrintWriter socketOut;
	
	/**
	 * Stream for reading from socket.
	 */
	private BufferedReader socketIn;

	/**
	 * Server constructor. Creates ServerSocket object
	 * with port number 8099.
	 */
	public Server() {
		try {
			serverSocket = new ServerSocket(8099);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if the given word is in fact a palindrome.
	 * @param word the word to be checked
	 * @return true if the word is a palindrome, false otherwise
	 */
	private boolean checkPalindrome(String word) {
		StringBuilder str = new StringBuilder(word);
		
		String reversed = str.reverse().toString();
		
		if(word.equals(reversed)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Manages the communication with the client-side
	 * depending on the nature of the entered string.
	 */
	public void palindrome() {
		String line = null;
		while(true) {
			try {
				line = socketIn.readLine();
				if(line == null) {
					return;
				}
				if(checkPalindrome(line) == true) {
					socketOut.println(line + " is a Palindrome.");
				}
				else {
					socketOut.println(line + " is not a Palindrome.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Creates server, establishes connection and runs program
	 * @param args command line arguments (none for this program)
	 * @throws IOException
	 */
	public static void main(String [] args) throws IOException {
		
		Server myServer = new Server(); 
		System.out.println("Server is now running.");
		
		//Establishing the connection
		
		try {
			myServer.aSocket = myServer.serverSocket.accept();
			myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
			myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(), true);
			myServer.palindrome();
			
			myServer.socketIn.close();
			myServer.socketOut.close();
			System.out.println("Good Bye!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
