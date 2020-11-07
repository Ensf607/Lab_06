package exercise_1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The Client class prompts the user to enter a word, passes 
 * that word to the server, and then displays the response.
 *
 */
public class Client {
	/**
	 * For writing to socket.
	 */
	private PrintWriter socketOut;
	
	/**
	 * Socket for connecting to server.
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
	 * @param serverName the internet protocol address the stream socket will be connected to
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
	 * This method is used to get input from the user
	 * and communicate with the server regarding the results.
	 */
	public void communicate()  {

		String line = "";
		String response = "";
		boolean running = true;
		while (running) {
			try {
				System.out.println("please enter a word: ");
				line = stdIn.readLine();
				if (!line.equals("QUIT")){
					System.out.println(line);
					socketOut.println(line);
					response = socketIn.readLine();
					System.out.println(response);	
				}else{
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
		Client aClient = new Client("localhost", 8099);
		aClient.communicate();
	}
}