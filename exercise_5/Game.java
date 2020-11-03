package exercise_5;

import java.io.*;



/**
 *  This is the main class where the tic tac toe game starts.
 *  The overall purpose of this exercise is to be able to code classes from reading UML diagram.
 * @author zchem
 *
 */
public class Game implements Constants,Runnable {
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private Board theBoard;
	private Referee theRef;
	Player xPlayer,oPlayer;
	private Game game2;
	String nameX,nameO;
	char mark;
	/**
	 * Constructor for the class where class object of type Board is created
	 */
	public Game(PrintWriter socketOut,BufferedReader socketIn,char mark) {
		this.socketIn=socketIn;
		this.socketOut=socketOut;
		theBoard  = new Board(socketOut);
		this.mark=mark;
	}
    /**
     * This method assigns the local variable {@link #theRef} to the passed @param then invokes
     *  {@link Referee#runTheGame()}
     * @param r its an object of type Referee
     * @throws IOException
     * @throws InterruptedException 
     * @throws NumberFormatException 
     */
    public void appointReferee(Referee r) throws IOException, NumberFormatException, InterruptedException {
        theRef = r;
    	theRef.runTheGame();
    }
	/**
	 * This is the main function.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// creating local class objects 
		
	}
	@Override
	public void run() {
		Referee theRef;
		
		if(mark=='X') {
		socketOut.println("\nPlease enter the name of the \'X\' player: ");
		try {
			nameX= socketIn.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xPlayer = new Player(nameX, LETTER_X,socketOut,socketIn);
		xPlayer.setBoard(theBoard);
		game2.setXPlayer(xPlayer);
		socketOut.println("waiting for O Player");
		while(oPlayer==null) {
			oPlayer=game2.oPlayer;
			}
		socketOut.println(xPlayer.getName()+"  "+oPlayer.getName());
		oPlayer.setBoard(theBoard);
		}
		
		else if( mark=='O') {
			socketOut.println("\nPlease enter the name of the \'O\' player: ");
			try {
				nameO= socketIn.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oPlayer = new Player(nameO, LETTER_O,socketOut,socketIn);
			oPlayer.setBoard(theBoard);
			game2.setOPlayer(oPlayer);
			socketOut.println("waitng for xPlayer");
			while(xPlayer==null) {
					
				xPlayer=game2.xPlayer;
			}
			xPlayer.setBoard(theBoard);
			socketOut.println(xPlayer.getName()+"  "+oPlayer.getName());
			}
		
		theRef = new Referee();
		theRef.setBoard(theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
		xPlayer.setTurn(1);
		oPlayer.setTurn(0);
        
		//start the game
        try {
			try {
				appointReferee(theRef);
			} catch (NumberFormatException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	private void setOPlayer(Player oPlayer2) {
		// TODO Auto-generated method stub
		
	}
	private void setXPlayer(Player xPlayer2) {
		// TODO Auto-generated method stub
		
	}
	public void setOtherGame(Game game2) {
     this.game2=game2;		
	}
	

}
