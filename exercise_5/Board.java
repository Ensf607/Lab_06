package exercise_5;

import java.io.PrintWriter;

/**
 * This class is responsible for the console animation of the x & o marks on the screen, and checking 
 * for winners,losers, and if there is a tie
 * @author zchem
 *
 */

public class Board implements Constants {
	private char theBoard[][];
	private int markCount;
	PrintWriter socketOut;
/**
 * Constructor that sets the board of3x3 grid to {@link Constants#SPACE_CHAR}
 * @param socketOut 
 */
	public Board(PrintWriter socketOut) {
		markCount = 0;
		this.socketOut=socketOut;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	/**
	 * returns the mark at specific row and col intersection
	 * @param row
	 * @param col
	 * @return the char x,o, or space at the designated area
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	/**
	 * checks if the grid is full with marks
	 * @return
	 */
	public boolean isFull() {
		return markCount == 9;
	}
	/**
	 * Checks if x is the winner by invoking {@link #checkWinner(char)}
	 * @return
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}
	/**
	 * Checks if o is the winner by invoking {@link #checkWinner(char)}
	 * @return
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}
	/**
	 * This method displays the grid with header, hyphens, and spaces
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			socketOut.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				socketOut.print("|  " + getMark(row, col) + "  ");
			socketOut.println("|");
			addSpaces();
			addHyphens();
		}
	}
	/**
	 * Adds the mark to the board at specific row, col
	 * @param row
	 * @param col
	 * @param mark
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}
	/**
	 * this method replaces all of the grid with space char
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}
	/**
	 * This method checks if the param meets any of the loops below
	 * if any of loop 
	 * @param mark
	 * @return 1 if @param mark is winner else return false
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;
		//check in the row direction 
		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		//check in the column direction
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}
		//if there is no winners then check the diagonals 
		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	/**
	 * Displays the header for the grid
	 */
	void displayColumnHeaders() {
		socketOut.print("          ");
		for (int j = 0; j < 3; j++)
			socketOut.print("|col " + j);
		socketOut.println();
	}
	/**
	 * Adds hyphens to the grid
	 */
	void addHyphens() {
		socketOut.print("          ");
		for (int j = 0; j < 3; j++)
			socketOut.print("+-----");
		socketOut.println("+");
	}
	/**
	 * add space to the grid
	 */
	void addSpaces() {
		socketOut.print("          ");
		for (int j = 0; j < 3; j++)
			socketOut.print("|     ");
		socketOut.println("|");
	}
}
