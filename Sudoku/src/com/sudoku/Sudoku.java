package com.sudoku;

public class Sudoku {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sudoku sudoku = new Sudoku();
	}
	
	public Sudoku() {
		
		int[][] field = {{6, 3, 9 ,0 ,0 ,8 ,0 ,4 ,1},
		                 {0, 0, 0 ,0 ,2 ,0 ,5 ,0 ,0},
						 {0, 0, 0 ,0 ,0 ,0 ,0 ,7 ,9},
						 {0, 9, 0 ,6 ,0 ,0 ,0 ,3 ,0},
						 {0, 2, 5 ,0 ,0 ,7 ,0 ,0 ,4},
						 {0, 4, 0 ,0 ,9 ,0 ,0 ,0 ,2},
						 {0, 7, 1 ,0 ,3 ,0 ,0 ,0 ,8},
						 {9, 0, 0 ,0 ,0 ,4 ,1 ,6 ,0},
						 {0, 0, 0 ,0 ,8 ,0 ,9 ,0 ,0},};
		field = this.solve(field);
		if(field == null)
			System.out.println("No possibility");
		else 
		printField(field);
		
	}
	
	private void printField(int[][] pField) {
		System.out.println("------------------------");
		for(int j=0; j<9; j++) {
			System.out.print("| ");
			for(int i=0; i<9; i++) {
				System.out.print(pField[j][i]+" ");
				if((i+1)%3 == 0)
					System.out.print("| ");
			}
			
			System.out.println("");
			if((j+1)%3 == 0)
				System.out.println("------------------------");
		}
	}
	
	private int[][] solve(int[][] table) {
		for(int j=0; j<9; j++) {
			for(int i=0; i<9; i++) {
				int currentNumber = table[i][j];
				if(currentNumber==0) {
					for(int a=1; a<=9; a++) {
						table[i][j]= a;
						if(this.isCorrect(table)) {
							int[][] tempTable = this.solve(table);
							if(tempTable != null){
								return tempTable;
							}
						}
						table[i][j]= 0;
					}
					return null;
				}
			}
		}
		return table;
			
	}
	
	private boolean isCorrect(int[][] table) {
		//Lines
		
		for(int j=0; j<9; j++) {
			boolean[] line = new boolean[9];
			for(int i=0; i<9; i++) {
				int currentNumber = table[i][j];
				if(currentNumber != 0) {
					if(!line[currentNumber-1])
						line[currentNumber-1] = true;
					else
						return false;
				}
			}
			
		}
		
		//Columns
		for(int i=0; i<9; i++) {
			boolean[] column = new boolean[9];
			for(int j=0; j<9; j++) {
				int currentNumber = table[i][j];
				if(currentNumber != 0) {
					if(!column[currentNumber-1])
						column[currentNumber-1] = true;
					else
						return false;
				}
			}
			
		}
		
		//Fields
		for(int f=0; f<9; f++) {
			int rowStart, columnStart;
			
			
			if(f<3) {
				rowStart = f*3;
				columnStart = 0;
			}else if(f>=3 && f<6) {
				rowStart = (f-3)*3;
				columnStart = 3;
			}else {
				rowStart = (f-6)*3;
				columnStart = 6;
			}
			boolean[] field = new boolean[9];
			for(int j=rowStart; j<rowStart+3; j++) {					
				for(int i=columnStart; i<columnStart+3; i++) {
					int currentNumber = table[i][j];
					if(currentNumber != 0) {
						if(!field[currentNumber-1])
							field[currentNumber-1] = true;
						else
							return false;
					
					}
				}
			}
		}	
		
		
		return true;
	}

}
