package exercise_5;

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
	private GUI gui;
	private GUIActions action;
	int row=-1,col=-1;
	char mark;

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

	public void communicate() throws InterruptedException  {

		String line = "";
		String response = "";
		String temp;
		boolean running = true;
		while (running) {
			try {
				response = socketIn.readLine();
				System.out.println(response);
				gui.setText(response);
				if(response.equals("X") || response.equals("O") )
					{gui.getCharField().setText(response);
					gui.setMark(response.charAt(0));
					}
				if(response.indexOf("update")!=-1) {
					while(true) {
					try {
					
					response = socketIn.readLine();
					System.err.println(response);
					 row=Integer.parseInt(response);
					 response = socketIn.readLine();
					 System.err.println(response);
					 col=Integer.parseInt(response);
					 response = socketIn.readLine();
					 System.err.println(response);
					char mark=response.charAt(0);
					System.err.println((row==0)+"  "+col+ " "+mark);
					if(row==0) {
						if(col==0)
							action.row0col0.setText(mark+"");
						if (col==1)
							action.row0col1.setText(mark+"");
						if(col==2)
							action.row0col2.setText(mark+"");
						break;
					}
					if(row==1) {
						if(col==0)
							action.row1col0.setText(mark+"");
							if (col==1)
								action.row1col1.setText(mark+"");
							if(col==2)
								action.row1col2.setText(mark+"");
							break;
					}
					if(row==2) {
						if(col==0)
							action.row2col0.setText(mark+"");
					   if (col==1)
						   action.row2col1.setText(mark+"");
					   if(col==2)
						   action.row2col2.setText(mark+"");
					   break;
					}
					} catch (Exception e) {Thread.sleep(00);}	
					
					
					
				}}
				if(response.indexOf("enter")!=-1  )
					{
					
					while(true) {
					if(action.getName()==null)
					 Thread.sleep(500);
					line=action.getName();
					if(action.getName()!=null) {
					line=action.getName();
					System.err.println(line);
					socketOut.println(line);
					break;}}
						}
				if(response.indexOf("box")>-1)
				{		action.setEnable(true);
						boolean check;
						while (true) {
							check=action.isClicked();
							if(!check) {
								Thread.sleep(500);
							}
							if(check) {
							row=action.getRow();
							col=action.getCol();
							socketOut.println(row);
							socketOut.println(col);
							System.err.println(action.isClicked());
							System.err.println(row+"  "+col);
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

	public static void main(String[] args) throws IOException  {
		Client aClient = new Client("localhost", 9090);
		aClient.gui=new GUI();
		aClient.action=aClient.gui.getAction();
		try {
			aClient.communicate();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
