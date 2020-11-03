package exercise_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * This class sets the player x and o, prompts the user of each player to enter a place 
 * on the grid to place their mark, then checks if there is any wiinners 
 * @author zchem
 *
 */
public class Player {
private String name;
private Board board;
private Player opponent;
private PrintWriter socketOut;
private BufferedReader socketIn;
private char mark;
int row=-1,col=-1;
int turn;
/**
 * Constructor that sets the mark and name of the player
 * @param name
 * @param mark
 */
public Player(String name, char mark,PrintWriter socketOut,BufferedReader socketIn) {
this.mark=mark;
this.name=name;
this.socketIn=socketIn;
this.socketOut=socketOut;

}
/**
 * This method invokes {@link #makeMove()} for current player as long
 * as the board is not full nor there is any winners, otherwise stop announce winner
 * if there is any.
 * @throws IOException 
 * @throws NumberFormatException 
 */
public synchronized void play() throws NumberFormatException, IOException {
	
		while(opponent.turn==0) {
			
		makeMove();
		board.display();
			
		if(board.xWins()) {
			if(mark=='X')
				{socketOut.println("Game over!! you win");
			    opponent.socketOut.print("Game over!! you lost");
			    break;}
			else
				{socketOut.println("Game over!! You win");
				opponent.socketOut.println("Game over!! you lost");
				
			break;
		}}
		else if (board.oWins()) {
			if(mark=='O')
			{socketOut.println("Game over!! you win");
		    opponent.socketOut.print("Game over!! you lost");}
			else
			{socketOut.println("Game over!! you lost");
		    opponent.socketOut.print("Game over!! you win");
			break;}
			
		}
		else if(board.isFull()) {
			socketOut.println("It is a tie");
			opponent.socketOut.println("It is a tie");
			break;
		}
		else {
		turn=0;
		socketOut.println("waiting for "+opponent.name+" to make a move");
		opponent.setTurn(1);
		opponent.play();}
		
	}
	}
	

public void setTurn(int i) {
this.turn=i;	
}
/**
 * This method is used to prompt user to where he/she wants to place the mark.
 * Also, checks if a mark already exists, and if the users col,row location is out of bounds
 * @throws IOException 
 * @throws NumberFormatException 
 */
public synchronized void  makeMove() throws NumberFormatException, IOException {
	boolean check=true;
	while(check) {
	socketOut.println(name+", what row should your next "+mark+" be placed in ?");
	row=Integer.parseInt(socketIn.readLine());
	socketOut.println(name+", what column should your next "+mark+" be placed in ?");
	col =Integer.parseInt(socketIn.readLine());
	if((row>=0 && row<=2)&&((col>=0 && col<=2))) {
	if (board.getMark(row, col)==' ' ) {
		 {
			board.addMark(row, col, mark);
			opponent.board.addMark(row, col, mark);
			check=false;
		}
		
	
	}
	else {
		socketOut.println("There is a mark there, choose another location");
		
	}}}
}
/**
 * Setter for {@link #opponent}
 * @param opponent
 */
public void setOpponent (Player opponent) {
	this.opponent=opponent;
}
/**
 * Setter for {@link #board}
 * @param theBoard
 */
public void setBoard(Board theBoard) {
	this.board=theBoard;
}
public int getRow() {
	return row;
}
public void setRow(int row) {
	this.row = row;
}
public int getCol() {
	return col;
}
public void setCol(int col) {
	this.col = col;
}
public String getName() {
	// TODO Auto-generated method stub
	return name;
}

}
