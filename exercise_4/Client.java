package exercise_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

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

	public void communicate()  {

		String line = "";
		String response = "";
		String temp;
		boolean running = true;
		while (running) {
			try {
				response = socketIn.readLine();
				System.out.println(response);
				if(response.indexOf("win")!=-1 ||response.indexOf("lost")!=-1 ||response.indexOf("tie")!=-1 ) {
					running=false;
				}
				
				
				if(response.indexOf("what row")!=-1 ||response.indexOf("what column")!=-1 || response.indexOf("enter")!=-1)
					{
					line=stdIn.readLine();
					socketOut.println(line);}
			
					
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

	public static void main(String[] args) throws IOException  {
		Client aClient = new Client("localhost", 9090);
		aClient.communicate();
	}


}
