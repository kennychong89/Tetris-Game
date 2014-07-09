package com.androidgame.model;

/**
 * Class to represent various Tetris pieces.
 * @author kenny
 */
public class Tetriminos {
	private Position[] piecePositions;
	
	// starting coordinates for testing
	public int startingRow = 0;
	public int startingColumn = 0;
	
	public Tetriminos() {
		Position test = new Position(startingRow, startingColumn);
		
		// We will only use a single 1x1 tetris piece for testing
		piecePositions = new Position[1];
		piecePositions[0] = test;
	}
	
	public Tetriminos(int span) {
		piecePositions = new Position[span];
	}
	
	/**
	 * Test case
	 * @param column
	 * @param row
	 */
	public void updatePosition(int row, int column) {
		piecePositions[0].setRow(row);
		piecePositions[0].setColumn(column);
	}
	
	public int getRow() {
		return piecePositions[0].getRow();
	}
	
	public int getColumn() {
		return piecePositions[0].getColumn();
	}
}
