// GameEngine.java - Sudoku Game Engine Class
// Author: Chris Wilcox
// Date: 10/15/2016
// Email: wilcox@cs.colostate.edu

//Edited By: Daniel Lund
//Date: 12/2/2016

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine implements GameInterface {

	private final int width = 9; // Puzzle width
	private final int height = 9; // Puzzle height
	private int puzzle[][]; // Puzzle data
	private int constraints[][]; // Puzzle constraints
	private ArrayList<Move> history; // Puzzle history

	public GameEngine(){
		setupBlank();
	}

    /**
     * Method to setup blank board to allow for user interaction before loading puzzle
     *
     */
	private void setupBlank() {
		puzzle = new int[9][9];
		constraints = new int[9][9];
		history = new ArrayList<>();
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				puzzle[i][j] = 0;
			}
		}
		updateConstraints();
	}

	// Return puzzle data
	public int[][] getData() {
		return puzzle;
	}

	// Return puzzle constraints
	public int[][] getConstraints() {
		return constraints;
	}

	// Return puzzle constraints
	public ArrayList<Move> getSolution() {
		return history;
	}

	// Load puzzle from file
	public void load(String filename) {

		// STUDENT CODE HERE - read puzzle from file
		puzzle = new int[width][height];
		constraints = new int[width][height];
		history = new ArrayList<Move>();
		try {
			Scanner read = new Scanner(new File(filename));
			for (int i = 0; i < puzzle.length; i++){
				for (int j = 0; j < puzzle[i].length; j++){
					puzzle[i][j] = read.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		// Update constraints
		updateConstraints();
	}

	// Save game
	public void save(String filename) {

		// STUDENT CODE HERE - write puzzle to file
		try {
			PrintWriter write = new PrintWriter(filename);
			int space = 0;
			for (int i = 0; i < puzzle.length; i++){
				for (int j = 0; j < puzzle[i].length; j++){
					write.print(puzzle[i][j]);
					write.print(" ");
					space++;
					if(space == 3){
						write.print(" ");
						space = 0;
					}
				}
				write.print("\n");
			}
			write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Step game
	public eStatus step() {

		// STUDENT CODE HERE - find next move
		for(int row = 0; row < puzzle.length; row++){
			for(int col = 0; col < puzzle[row].length; col++){
				if(numberOfZeros(constraints[row][col]) == 1){
					Move move = new Move();
					move.row = row;
					move.column = col;
					int value = positionOfZero(constraints[row][col])+1;
					move.value = value;
					puzzle[row][col] = value;
					history.add(move);
					updateConstraints();
					if(isSolved()){
						return eStatus.eSolved;
					}
					return eStatus.eSuccess;
				}
			}
		}
		// Update constraints
		//TODO: Hard puzzle

		updateConstraints();
		
		return eStatus.eFailure;
	}


	// Update constraints for all squares
	private void updateConstraints() {

		int constraint = 0;
		for (int row = 0; row < puzzle.length; row++) {
			for (int col = 0; col < puzzle[row].length; col++) {
				if (puzzle[row][col] == 0) {
					for (int currentCol = 0; currentCol < 9; currentCol++) {
						if (puzzle[row][currentCol] != 0) {
							constraint |= 1 << (puzzle[row][currentCol] - 1);
						}
						// columns
						for (int currentRow = 0; currentRow < 9; currentRow++) {
							if (puzzle[currentRow][col] != 0) {
								constraint |= 1 << (puzzle[currentRow][col] - 1);
							}
						}

						// 3x3 squares
						int shortRow = (row / 3) * 3; // 0,3,6
						int shortCol = (col / 3) * 3; // 0,3,6

						constraint |= checkSquare(shortRow, shortRow + 3, shortCol, shortCol + 3);
						constraints[row][col] = constraint;
					}
					constraint = 0;
				}
				else{
                    //TODO: implement constraints for check method
					constraints[row][col] = 511;
				}
			}
		}
	}

	private int checkSquare(int startRow, int endRow, int startCol, int endCol){
		int constraint = 0;
		for(int i = startRow; i < endRow; i++){
			for(int j = startCol; j < endCol; j++){
				if(puzzle[i][j] != 0) {
					constraint |= 1 << (puzzle[i][j] - 1);
				}
			}
		}
		return constraint;
	}


	// Check if puzzle is solved
	public boolean isSolved() {

		// STUDENT CODE HERE - is puzzle solved?
		for(int i = 0; i < puzzle.length; i++){
			for(int j = 0; j < puzzle[i].length; j++){
				if(puzzle[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}

	// Number of zeros in integer
	private int numberOfZeros(int constraint) {

		// STUDENT CODE HERE - how many zeros in constraint?
		int totalZeros = 0;
		for(int i = 0; i < puzzle.length; i++){
			if(((1 << i) & constraint) == 0){
				totalZeros++;
			}
		}
		return totalZeros;
	}

	// Position of zeros in integer
	private int positionOfZero(int constraint) {

		// STUDENT CODE HERE - position of zero in constraint?
		for(int i = 0; i < puzzle.length; i++){
			if(((1 << i) & constraint) == 0){
				return i;
			}
		}
		return -1;
	}

	public void incrementTile(int row, int col){
        //TODO: Add move to history

		if(puzzle[row][col] == 9){
			puzzle[row][col] = 0;
		}else{
			puzzle[row][col]++;
		}
		updateConstraints();
	}

	public void setTileValue(int row, int col, int value){
        //TODO: Add move to history

		puzzle[row][col] = value;
		updateConstraints();
	}

	public int getConstraint(int row, int col){
        return constraints[row][col];
	}

}
