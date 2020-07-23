package Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI implements ActionListener, KeyListener{

	private JButton set = new JButton("set");
	private JButton clear = new JButton("clear");
	private JLabel CorrOrWrong = new JLabel("---");
	private static int[][] sudoku;
	private static JTextField[][] index = new JTextField[9][9];
	private JPanel panel = new JPanel();
		
	public GUI (){
		JFrame frame = new JFrame("Sudoku");
		frame.setSize(500,500);
		set.addActionListener(this);
		clear.addActionListener(this);
		JPanel board = new JPanel();
		JPanel panel = new JPanel();
		board.setLayout(new GridLayout (9,9));
		for (int i =0;i<9;i++) //creating the 81 textfields of board and adding a key listener
			for (int j=0;j<9;j++){
				index[i][j]=new JTextField(1);
				index[i][j].addKeyListener((KeyListener) this);
				board.add(index[i][j]);
			}
		panel.add(set);
		panel.add(clear);
		frame.getContentPane().add(board);
		frame.getContentPane().add(panel,"South");
		frame.getContentPane().add(CorrOrWrong, "North");
	    frame.setVisible(true);
	    
		sudoku = SudokuGenerator.create();
	}
	
	//The method implements the press action of "set" and "clear" buttons
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == set){
			for (int i =0;i<9;i++)
				for (int j=0;j<9;j++){
					if (sudoku[i][j]!=0)
						index[i][j].setEditable(false);
				}
		}
		if (e.getSource() == clear) {
			for (int i =0;i<9;i++)
				for (int j=0;j<9;j++){
					index[i][j].setText("");
					index[i][j].setEditable(true);
				}
			sudoku = SudokuGenerator.create();
		}
	}
	
	//The method implements the interaction of the user with the textfield board
	//It occurs just when ENTER press and act to enter a number to the board (before or after "set") if the number fits. 
	public void keyReleased(KeyEvent e)
	{
	    int key=e.getKeyCode();
	    if(key==KeyEvent.VK_ENTER) { //check that it is an ENTER action
			for (int i =0;i<9;i++) {
				for (int j=0;j<9;j++){	    	
					if(e.getSource()==index[i][j]){ //find the relevant text field
						sudoku[i][j]=Integer.parseInt(index[i][j].getText());
						SudokuGenerator.write(sudoku);
						if(sudoku[i][j]<1 || sudoku[i][j]>9) { //case number is not in range
							message("NOT IN RANGE EROR! Please enter a number from 1 to 9");
							index[i][j].setText("");
							sudoku[i][j] = 0;
						}else if(!testSudoku()) { //case number is a wrong answer
							message("Wrong input! Try another");
							index[i][j].setText("");
							sudoku[i][j] = 0;
						}else { //case write answer
							CorrOrWrong.setText("OK");
							index[i][j].setText(""+index[i][j].getText());
						}
					}
				}
			}
		}
	}
	
	//creating a message dialog
	public void message(String s){
		JOptionPane.showMessageDialog(null,
			    s,
			    "Inane error",
			    JOptionPane.ERROR_MESSAGE);
	}

	//a method for using the methods of the checker
	public boolean testSudoku() {
		return SudokuChecker.main(null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
