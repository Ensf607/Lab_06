package exercise_5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {

	private JFrame frame;
	private JTextField nameField;
	private JTextField charField;
	private JTextPane textPane;
	private GUIActions action;
	private JButton row0col0,row0col1,row0col2,row1col0,row1col1,row1col2,row2col0,row2col1,row2col2;
	
	private char mark;


	public GUI() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel msgPanel = new JPanel();
		GridBagConstraints gbc_msgPanel = new GridBagConstraints();
		gbc_msgPanel.gridheight = 3;
		gbc_msgPanel.fill = GridBagConstraints.BOTH;
		gbc_msgPanel.gridx = 9;
		gbc_msgPanel.gridy = 0;
		frame.getContentPane().add(msgPanel, gbc_msgPanel);
		msgPanel.setSize(200, 500);
		msgPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel msgLabel = new JLabel("Message Window");
		msgLabel.setHorizontalAlignment(SwingConstants.LEFT);
		msgLabel.setBackground(new Color(0, 0, 0));
		msgLabel.setForeground(Color.BLACK);
		msgLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		msgPanel.add(msgLabel, BorderLayout.NORTH);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		msgPanel.add(textPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.gridwidth = 8;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		
		 row0col0 = new JButton("");
	
		row0col0.setBounds(22, 44, 60, 60);
		row0col0.setFont(new Font("Tahoma", Font.BOLD, 19));
		panel.add(row0col0);
		
	     row0col1 = new JButton("");
		row0col1.setBounds(94, 44, 60, 60);
		row0col1.setFont(new Font("Tahoma", Font.BOLD, 19));
		panel.add(row0col1);
		
		 row0col2 = new JButton("");
		row0col2.setBounds(168, 44, 60, 60);
		row0col2.setFont(new Font("Tahoma", Font.BOLD, 19));
		panel.add(row0col2);
		
		 row1col0 = new JButton("");
		row1col0.setBounds(22, 117, 60, 60);
		row1col0.setFont(new Font("Tahoma", Font.BOLD, 19));
		panel.add(row1col0);
		
		 row1col1 = new JButton("");
		row1col1.setBounds(94, 117, 60, 60);
		row1col1.setFont(new Font("Tahoma", Font.BOLD, 19));
		panel.add(row1col1);
		
		row1col2 = new JButton("");
		row1col2.setBounds(168, 117, 60, 60);
		row1col2.setFont(new Font("Tahoma", Font.BOLD, 19));
	
		panel.add(row1col2);
		
		 row2col0 = new JButton("");
		row2col0.setBounds(22, 190, 60, 60);
		row2col0.setFont(new Font("Tahoma", Font.BOLD, 19));
		panel.add(row2col0);
		
		 row2col1 = new JButton("");
		row2col1.setBounds(94, 190, 60, 60);
		row2col1.setFont(new Font("Tahoma", Font.BOLD, 19));
		panel.add(row2col1);
		
		 row2col2 = new JButton("");
		
		row2col2.setBounds(168, 190, 60, 60);
		row2col2.setFont(new Font("Tahoma", Font.BOLD, 19));
		panel.add(row2col2);
		
		JLabel playerName = new JLabel("Name");
		playerName.setBounds(65, 303, 56, 16);
		panel.add(playerName);
		
		nameField = new JTextField();
		nameField.setBounds(133, 300, 116, 22);
		panel.add(nameField);
		nameField.setColumns(10);
		
		JLabel charType = new JLabel("Player");
		charType.setBounds(65, 268, 56, 16);
		panel.add(charType);
		
		charField = new JTextField();
		charField.setColumns(10);
		charField.setBounds(133, 265, 33, 22);
		panel.add(charField);
		action=new GUIActions(nameField,mark, row0col0, row0col1, row0col2, row1col0, row1col1, row1col2, row2col0, row2col1, row2col2, textPane);
		row0col0.addMouseListener(action);
		row0col1.addMouseListener(action);
		row0col2.addMouseListener(action);
		row1col0.addMouseListener(action);
		row1col1.addMouseListener(action);
		row1col2.addMouseListener(action);
		row2col0.addMouseListener(action);
		row2col1.addMouseListener(action);
		row2col2.addMouseListener(action);
		nameField.addActionListener(action);
//		enable(true);
		frame.setVisible(true);
		
	}

	public char getMark() {
		return mark;
	}

	public void setMark(char mark) {
		this.mark = mark;
		action.setMark(mark);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getCharField() {
		return charField;
	}

	public JTextPane getTextPane() {
		return textPane;
	}
	public void exit() {
		frame.setVisible(false);
	}

	public GUIActions getAction() {
		// TODO Auto-generated method stub
		return action;
	}

	public void setText(String response) {
		textPane.setText(response);
	}
	public void enable(boolean check) {
		row0col0.setEnabled(check);
		row0col1.setEnabled(check);
		row0col2.setEnabled(check);
		row1col0.setEnabled(check);
		row1col1.setEnabled(check);
		row1col2.setEnabled(check);
		row2col0.setEnabled(check);
		row2col1.setEnabled(check);
		row2col2.setEnabled(check);
		
		
	}
	
}
