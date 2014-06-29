package com.androidgame.model;

/**
 * Class that will represent the Tetris grid matrix.
 * @author kenny
 */
public class Grid {

	private boolean [][] tetrisGrid;
	
	/**
	 * Initializes grid to default 18 rows by 10 columns
	 */
	public Grid() {
		tetrisGrid = new boolean[18][10];
		resetGrid();
	}
	
	/**
	 * 
	 * @param rows
	 * @param columns
	 */
	public Grid(int rows, int columns) {
		if (rows < 0 || columns < 0)
			throw new IllegalArgumentException("row and columns must be positive");
		
		tetrisGrid = new boolean[rows][columns];
		resetGrid();
	}
	
	/**
	 * Resets the grid.
	 */
	public void resetGrid() {
		for (int row = 0; row < tetrisGrid.length; row++) {
			for (int column = 0; column < tetrisGrid[0].length; column++) {
				tetrisGrid[row][column] = false;
			}
		}
	}
	
	/**
	 * Checks whether row,column position is empty
	 * @param row
	 * @param column
	 * @return True if empty, false otherwise
	 * @throws ArrayOutOfBoundsException 
	 */
	public boolean isEmpty(int row, int column) {
		if (!isValidPosition(row, column))
			return false;
		
		return (tetrisGrid[row][column] == false);
	}
	
	/**
	 * Sets (row, column) to be occupied.
	 * @param row
	 * @param column
	 */
	public void occupyPosition(int row, int column) {
	   if (isValidPosition(row, column))
		   tetrisGrid[row][column] = true;
	}
	
	/**
	 * Sets (row, column) to be empty
	 * @param row
	 * @param column
	 */
	public void unoccupyPosition(int row, int column) {
		if (isValidPosition(row, column))
			tetrisGrid[row][column] = false;
	}
	
	/**
	 * Clears the rows given.
	 * @param rows
	 */
	public void clearRows(int... rows) {
		for (int i = 0; i < rows.length; i++) {
			if (isValidPosition(rows[i], 0)) {
				for (int column = 0; column < getGridColumns(); column++)
					unoccupyPosition(rows[i], column);
			}
		}
	}
	
	
	/**
	 * 
	 * @return How many rows the grid has.
	 */
	public int getGridRows() {
		return tetrisGrid.length;
	}
	
	/**
	 * 
	 * @return How many columns the grid has.
	 */
	public int getGridColumns() {
		return tetrisGrid[0].length;
	}
	
	/**
	 * Determine if row and column is valid
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isValidPosition(int row, int column) {
		return (row < tetrisGrid.length && column < tetrisGrid[0].length)
				&& (row >= 0 && column >= 0);
	}
}