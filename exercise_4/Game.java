package exercise_4;

import java.io.*;

/**
 * This class is invoked by {@link Server} by thread execution, its the entry
 * point to the tic tac toe game
 */
public class Game implements Constants, Runnable {
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private Board theBoard;
	private Referee theRef;
	Player xPlayer, oPlayer;
	private Game game2;
	String nameX, nameO;
	char mark;

	/**
	 * Constructor for the class where class object of type Board is created
	 */
	public Game(PrintWriter socketOut, BufferedReader socketIn, char mark) {
		this.socketIn = socketIn;
		this.socketOut = socketOut;
		theBoard = new Board(socketOut);
		this.mark = mark;
	}

	/**
	 * This method assigns the local variable {@link #theRef} to the passed @param
	 * then invokes {@link Referee#runTheGame()}
	 * 
	 * @param r its an object of type Referee
	 * @throws IOException
	 */

	/**
	 * overriding run method Note: each player has their own socketIn/socketOut and
	 * their own board
	 * 
	 */
	@Override
	public void run() {
		// first client player
		if (mark == 'X') {
			socketOut.println("\nPlease enter the name of the \'X\' player: ");
			try {
				nameX = socketIn.readLine();

			} catch (IOException e) {
				e.printStackTrace();
			}
			xPlayer = new Player(nameX, LETTER_X, socketOut, socketIn);
			xPlayer.setBoard(theBoard);
			socketOut.println("waiting for O Player");
			while (oPlayer == null) {
				oPlayer = game2.oPlayer;
			}
		}
		// second client player
		else if (mark == 'O') {
			socketOut.println("\nPlease enter the name of the \'O\' player: ");
			try {
				nameO = socketIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			oPlayer = new Player(nameO, LETTER_O, socketOut, socketIn);
			oPlayer.setBoard(theBoard);
			socketOut.println("waitng for xPlayer");
			while (xPlayer == null) {

				xPlayer = game2.xPlayer;
			}
		}
		socketOut.println("setting ref and board!!");
		// once both clients name provided, then create ref for each client and rungame
		theRef = new Referee();
		theRef.setBoard(theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);

        try {
        	theRef.runTheGame();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void setOtherGame(Game game2) {
     this.game2=game2;		
	}
	

}
