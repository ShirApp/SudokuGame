package Sudoku;

import java.io.*;

public class SudokuChecker{
	private static int [] temp = new int [9];
	private static int [][]sudoku = new int [9][9];
	
	//Main Check function                                                                
	private static int Check (int[] y) {
	  int lineartemp;
	  for (int i=0; i<9; i++) {
	    lineartemp = y[i];
	    for (int j=i+1; j<9; j++) { //searches for duplications
	    	if(y[i] != 0)
	    		if (y[j] ==  lineartemp)
	    			return 0;
	    }
	  }
	  return 1;
	}
	
	//implementing check for rows
	private static int RowCheck ( int[][] x) {
	  for (int i=0; i<9; i++) {
	    for (int j= 0; j<9; j++)
	      temp[j] = x[i][j];     
	    if (Check(temp) == 0) 
	    	return 0;
	  }
	  return 1;

	}
	
	//implementing check for columns
	private static int ColumnCheck ( int[][] x) {
	  for (int i=0; i<9; i++) {
	    for (int j= 0; j<9; j++)
	      temp[j] = x[j][i];  
	    if (Check(temp) == 0)
	      return 0;
	  }
	  return 1;
	}                        
	
	//implementing check in subsquares
	private static int SquaresCheck (int[][] x) {
	
	//column and row loops set the starting index for each subsquare
	  int i,j,column,row,k;
	        
	  for (column=0; column <= 6; column += 3) {
	    for (row=0; row <= 6; row += 3) {
	      for (k=0, i=0; i<3; i++) {
	        for (j=0; j<3; j++)
	          temp[k++] = x[row+i][column+j];
	      }
	      if (Check(temp) ==0)
	        return 0;                
	    }
	  }       
	  return 1;
	}
	
	//method for reading the file includes the array of textfields
	public static int[][] readIn(){
			int[][] array = new int [9][9];
			int x=0;
			try {
		         BufferedReader br = new BufferedReader(new FileReader("output.txt"));
		         String s;
		         while ((s = br.readLine())!= null)
		         {
		            String[] parts = s.split(" ");
		            for (int y=0;y<9;y++) {
		            	array[x][y]=Integer.parseInt(parts[y]);
		            }
		            x++;
		         }
		         br.close();
		      } catch (IOException e) 
				{
		    	  System.out.println("ERROR");
				}
			return array;
	}
	
	public static boolean main(String [] args) {
	  sudoku = SudokuChecker.readIn();     
	  
	  //passed
	  if (RowCheck(sudoku) == 0 || ColumnCheck(sudoku)==0 || SquaresCheck(sudoku)==0) 
		  return false;
	  
	  //failed
	  return true;
	}
}

