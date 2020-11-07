package exercise_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The Client class connects to a date-time server in order to allow the 
 * user to receive date or time information depending on their selection.
 * @author Ziad Chemali and Lotfi Hasni
 * @version 1.0
 * @since October 30, 2020
 *
 */
public class Client {
	
	/**
	 * For writing to socket.
	 */
	private PrintWriter socketOut;
	
	/**
	 * For communicating with server.
	 */
	private Socket palinSocket;
	
	/**
	 * For reading user input.
	 */
	private BufferedReader stdIn;
	
	/**
	 * For reading from socket.
	 */
	private BufferedReader socketIn;

	/**
	 * Client object constructor.
	 * @param serverName the Internet protocol address the stream socket will be connected to
	 * @param portNumber the number of the specified port
	 */
	public Client(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
					palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	/**
	 * This method is used to get the date or time selection from the
	 * user, communicate this choice to the server, and then display
	 * the response for the user.
	 */
	public void communicate()  {

		String line = "";
		String response = "";
		boolean running = true;
		while (running) {
			try {
				System.out.println("Please select an option (DATE/TIME) ");
				line = stdIn.readLine();
				if (!line.equals("QUIT")){
					socketOut.println(line);
					response = socketIn.readLine();
					System.out.println(response);
				}else{
					socketOut.println(line);
					running = false;
				}
				
			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}

	}

	/**
	 * Creates a new Client object and starts communication.
	 * @param args command line arguments (none for this program)
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException  {
		Client aClient = new Client("localhost", 9090);
		aClient.communicate();
	}
}