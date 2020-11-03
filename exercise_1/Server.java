package exercise_1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {


	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;

	public Server() {
		try {
			serverSocket = new ServerSocket(8099);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
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
