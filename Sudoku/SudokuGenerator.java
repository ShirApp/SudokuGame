package Sudoku;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SudokuGenerator {
	
	//method for creation of a new array (initialization to 0)
	public static int[][] create(){
		int [][] arr = new int[9][9];
		for (int i =0;i<9;i++)
			for (int j=0;j<9;j++){
				arr[i][j] = 0;
			}
		return arr;
	}
	
	//method for writing to txt file an array
	public static void write(int[][] temp){
		try
	      {
	         PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
	         for (int i=0;i<9;i++)
	         {
	        	String s="";
	        	for (int j=0;j<9;j++) {
	        		s+=temp[i][j]+" ";
	        	}
	            pw.println(s);
	         }
	         pw.close();
	      } catch (IOException e)
	         {
	            System.out.println("The following error occurred " + e);
	         }
	}
	
}

