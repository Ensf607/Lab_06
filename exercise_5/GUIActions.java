package exercise_5;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * This class handles all the events that happen in the GUI
 *
 */
public class GUIActions implements MouseListener, ActionListener {
	private char mark;
	JButton row0col0, row0col1, row0col2, row1col0, row1col1, row1col2, row2col0, row2col1, row2col2;
	JTextPane textPane;
	JTextField nameField;
	private int row, col;
	private String name;
	boolean enable = false;
	boolean clicked = false;

	/**
	 * setting local variables to passed @param
	 * 
	 * @param nameField
	 * @param mark
	 * @param row0col0
	 * @param row0col1
	 * @param row0col2
	 * @param row1col0
	 * @param row1col1
	 * @param row1col2
	 * @param row2col0
	 * @param row2col1
	 * @param row2col2
	 * @param textPane
	 */
	public GUIActions(JTextField nameField, char mark, JButton row0col0, JButton row0col1, JButton row0col2,
			JButton row1col0, JButton row1col1, JButton row1col2, JButton row2col0, JButton row2col1, JButton row2col2,
			JTextPane textPane) {
		this.nameField = nameField;
		this.mark = mark;
		this.row0col0 = row0col0;
		this.row0col1 = row0col1;
		this.row0col2 = row0col2;
		this.row1col0 = row1col0;
		this.row1col1 = row1col1;
		this.row1col2 = row1col2;
		this.row2col0 = row2col0;
		this.row2col1 = row2col1;
		this.row2col2 = row2col2;
		this.textPane = textPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// mouse events for each button, each event is responsible for assigning value
		// to row and col integers
		if (Character.isLetter(mark) && enable) {
			row = -1;
			col = -1;
			if (e.getSource() == row0col0) {
				if (row0col0.getText() == "") {
					row0col0.setText(mark + "");

					clicked = true;
					row = 0;
					col = 0;
				} else
					textPane.setText("Cant put a mark in an already filled box");
			} else if (e.getSource() == row0col1) {
				if (row0col1.getText() == "") {
					row0col1.setText(mark + "");
					row = 0;
					col = 1;
					clicked = true;
				} else
					textPane.setText("Cant put a mark in an already filled box");

			} else if (e.getSource() == row0col2) {
				if (row0col2.getText() == "") {
					row0col2.setText(mark + "");
					row = 0;
					col = 2;
					clicked = true;
				} else {
					textPane.setText("Cant put a mark in an already filled box");
				}
			} else if (e.getSource() == row1col0) {
				if (row1col0.getText() == "") {
					row1col0.setText(mark + "");
					row = 1;
					col = 0;
					clicked = true;
				} else {
					textPane.setText("Cant put a mark in an already filled box");
				}
			} else if (e.getSource() == row1col1) {
				if (row1col1.getText() == "") {
					row1col1.setText(mark + "");
					row = 1;
					col = 1;
					clicked = true;
				} else {
					textPane.setText("Cant put a mark in an already filled box");
				}
			} else if (e.getSource() == row1col2) {
				if (row1col2.getText() == "") {
					row1col2.setText(mark + "");
					row = 1;
					col = 2;
					clicked = true;
				} else {
					textPane.setText("Cant put a mark in an already filled box");
				}
			} else if (e.getSource() == row2col0) {
				if (row2col0.getText() == "") {
					row2col0.setText(mark + "");
					row = 2;
					col = 0;
					clicked = true;
				} else {
					textPane.setText("Cant put a mark in an already filled box");
				}
			} else if (e.getSource() == row2col1) {
				if (row2col1.getText() == "") {
					row2col1.setText(mark + "");
					row = 2;
					col = 1;
					clicked = true;
				} else {
					textPane.setText("Cant put a mark in an already filled box");
				}
			} else if (e.getSource() == row2col2) {
				if (row2col2.getText() == "") {
					row2col2.setText(mark + "");
					row = 2;
					col = 2;
					clicked = true;
				} else {
					textPane.setText("Cant put a mark in an already filled box");
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setMark(char mark) {
		this.mark = mark;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == nameField) {

			name = nameField.getText();
			nameField.setEditable(false);
			nameField.setText(name);

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

}
