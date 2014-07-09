package com.androidgame.model;

/**
 * Class will maintain updates to the Tetris grid, including adding and removing blocks
 * from the grid.
 * @author kenny
 */
public class GridManager {
	private Grid tetrisGrid;
	
	public GridManager() {
		tetrisGrid = new Grid();
	}
	
	public GridManager(int rows, int columns) {
		tetrisGrid = new Grid(rows, columns);
	}
	
	// this will be used only for testing
	public void fillGridBottom() {
		// getNextPiece();
		
		int bottomGridRow = tetrisGrid.getGridRows() - 1;
		int bottomGridColumn = tetrisGrid.getGridColumns();
		
		for (int i = 0; i < bottomGridColumn; i++) {
				if (i != bottomGridColumn / 2)
					tetrisGrid.occupyPosition(bottomGridRow, i);
		}
		
		// setTetrisPieceToLocation(0, bottomGridColumn / 2);
	}
	
	public int getGridRows() {
		return tetrisGrid.getGridRows();
	}
	
	public int getGridColumns() {
		return tetrisGrid.getGridColumns();
	}
	
	public boolean isOccupied(int row, int column) {
		return !tetrisGrid.isEmpty(row, column);
	}
	
	// test method - clears out the bottom row. 
	// This method does not cascade down the rows above the bottom rows 
	public void clearBottomRow() {
		int bottomGridRow = tetrisGrid.getGridRows() - 1;
		
		tetrisGrid.clearRows(bottomGridRow);
	}
	
	// test method - checks if bottom row contains no empty spaces.
	// Will expand this later to check different rows.
	public boolean isBottomRowFilled() {
		int bottomGridRow = tetrisGrid.getGridRows() - 1;
		int bottomGridColumn = tetrisGrid.getGridColumns();

		for (int i = 0; i < bottomGridColumn; i++) {
			if (tetrisGrid.isEmpty(bottomGridRow, i))
				return false;
		}

		return true;
	}

	// test method - cascades the rest of rows down only if bottom row has been
	// cleared
	public void cascadeRowDown() {

	}
	
	public void fillGridLocation(int pieceRow, int pieceColumn) {
		tetrisGrid.occupyPosition(pieceRow, pieceColumn);
	}
	
	public void unFillGridLocation(int pieceRow, int pieceColumn) {
		tetrisGrid.unoccupyPosition(pieceRow, pieceColumn);
	}
}
