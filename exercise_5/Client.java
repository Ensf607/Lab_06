package exercise_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Implementation of the client class. This class communicates with the server
 * via sockets and invokes the GUI to display, also communicates with
 * {@link GUIActions} if there are any actions occurred in the GUI
 *
 */
public class Client {

	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader socketIn;
	private GUI gui;
	private GUIActions action;
	int row = -1, col = -1;
	char mark;

	/**
	 * Ctor to initialize @param
	 * 
	 * @param serverName
	 * @param portNumber
	 */
	public Client(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	/**
	 * This method is responsible with communicating with Server and GUI
	 * 
	 * @throws InterruptedException
	 */
	public void communicate() throws InterruptedException {

		String line = "";
		String response = "";
		boolean running = true;
		while (running) {
			try {
				response = socketIn.readLine();
				gui.setText(response);
				// sets the CLients markers
				if (response.equals("X") || response.equals("O")) {
					gui.getCharField().setText(response);
					gui.setMark(response.charAt(0));
				}
				// if server msg contain update then update the GUI's board
				if (response.indexOf("update") != -1) {
					while (true) {
						try {

							response = socketIn.readLine();
							row = Integer.parseInt(response);
							response = socketIn.readLine();
							col = Integer.parseInt(response);
							response = socketIn.readLine();
							char mark = response.charAt(0);
							if (row == 0) {
								if (col == 0)
									action.row0col0.setText(mark + "");
								if (col == 1)
									action.row0col1.setText(mark + "");
								if (col == 2)
									action.row0col2.setText(mark + "");
								break;
							}
							if (row == 1) {
								if (col == 0)
									action.row1col0.setText(mark + "");
								if (col == 1)
									action.row1col1.setText(mark + "");
								if (col == 2)
									action.row1col2.setText(mark + "");
								break;
							}
							if (row == 2) {
								if (col == 0)
									action.row2col0.setText(mark + "");
								if (col == 1)
									action.row2col1.setText(mark + "");
								if (col == 2)
									action.row2col2.setText(mark + "");
								break;
							}
						} catch (Exception e) {
							Thread.sleep(500);
						}

					}
				}
				// if server msg contain enter get clients name from gui
				if (response.indexOf("enter") != -1) {

					while (true) {
						if (action.getName() == null)
							Thread.sleep(500);
						line = action.getName();
						if (action.getName() != null) {
							line = action.getName();
							socketOut.println(line);
							break;
						}
					}
				}
				// if server msg contain box then get the coordinates of the button that the
				// client used tp put his/her mark
				if (response.indexOf("box") > -1) {
					action.setEnable(true);
					boolean check;
					while (true) {
						check = action.isClicked();
						if (!check) {
							Thread.sleep(500);
						}
						if (check) {
							row = action.getRow();
							col = action.getCol();
							socketOut.println(row);
							socketOut.println(col);
							action.setClicked(false);
							break;
						}
					}
					action.setEnable(false);

				}

			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}

		}
		try {
			gui.exit();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}

	}

	public static void main(String[] args) throws IOException {
		Client aClient = new Client("localhost", 9090);
		aClient.gui = new GUI();
		aClient.action = aClient.gui.getAction();
		try {
			aClient.communicate();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
