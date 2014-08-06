package com.androidgame.model.tetrisgrid;

import com.androidgame.model.tetrispiece.Block;
import com.androidgame.model.tetrispiece_utilities.Blocks_Modifier;

/**
 * Class will maintain updates to the Tetris grid, including checking for
 * collisions, adding and removing blocks from the grid.
 * 
 * @author kenny
 */
public class GridManager {
	private Grid tetrisGrid;

	// constants
	private final int UP_ONE = -1;
	private final int DROP_ONE = 1;
	private final int MOVE_ONE_LEFT = -1;
	private final int MOVE_ONE_RIGHT = 1;

	public GridManager() {
		tetrisGrid = new Grid();
	}

	/*
	 * WILL NOT BE USED, FOR NOW public GridManager(TetrisPiece piece, int rows,
	 * int columns) { this(piece); tetrisGrid = new Grid(rows, columns); }
	 */
	/*
	// this will be used only for testing
	public void fillGridBottom() {
		// getNextPiece();

		int bottomGridRow = tetrisGrid.getGridRows();
		int bottomGridColumn = tetrisGrid.getGridColumns();

		for (int i = 1; i <= bottomGridColumn; i++) {
			// if (i != bottomGridColumn / 2)
			tetrisGrid.occupyPosition(bottomGridRow, i);
		}

		// setTetrisPieceToLocation(0, bottomGridColumn / 2);
	}

	// test method - clears out the bottom row.
	// This method does not cascade down the rows above the bottom rows
	public void clearBottomRow() {
		int bottomGridRow = tetrisGrid.getGridRows();

		tetrisGrid.clearRows(bottomGridRow);
	}

	// test method - checks if bottom row contains no empty spaces.
	// Will expand this later to check different rows.
	public boolean isBottomRowFilled() {
		int bottomGridRow = tetrisGrid.getGridRows();
		int bottomGridColumn = tetrisGrid.getGridColumns();

		for (int i = 1; i <= bottomGridColumn; i++) {
			if (tetrisGrid.isEmpty(bottomGridRow, i))
				return false;
		}

		return true;
	}
	*/
	
	// test method - cascades the rest of rows down only if bottom row has been
	// cleared
	public void cascadeRowDown() {

	}

	public void updateGrid(Block [] blocks, boolean fill) {	
		// we're filling
		if (fill)
			fillMultipleGridLocations(blocks);
		else
			unFillMultipleGridLocations(blocks);
	}
	
	public boolean hasCollided(Block [] blocks) {
		return hasCollidedBelow(blocks) 
				|| hasCollidedLeft(blocks)
				|| hasCollidedRight(blocks)
				|| hasCollidedTop(blocks);
	}
	
	public boolean hasCollidedTop(Block [] blocks) {
		return hasCollidedRow(blocks, UP_ONE);
	}

	public boolean hasCollidedBelow(Block [] blocks) {
		return hasCollidedRow(blocks, DROP_ONE);
	}
	
	public boolean hasCollidedLeft(Block [] blocks) {
		return hasCollidedColumn(blocks, MOVE_ONE_LEFT);
	}

	public boolean hasCollidedRight(Block [] blocks) {
		return hasCollidedColumn(blocks, MOVE_ONE_RIGHT);
	}

	public void clearGrid() {
		tetrisGrid.resetGrid();
	}

	public boolean isOccupied(int row, int column) {
		return !tetrisGrid.isEmpty(row, column);
	}

	public boolean[][] getGridInfo() {
		return tetrisGrid.getGridDataCopy();
	}
	
	private boolean hasCollidedColumn(Block [] blocks, int columnChange) {
		int leftSide = Blocks_Modifier.getLeftLocationOfBlocks(blocks);
		int rightSide = Blocks_Modifier.getRightLocationOfBlocks(blocks);
		int bottomSide = Blocks_Modifier.getBottomLocationOfBlocks(blocks);
		int topSide = Blocks_Modifier.getTopLocationOfBlocks(blocks);
		
		for (int row = topSide; row <= bottomSide; row++) {
			for (int column = leftSide; column <= rightSide; column++) {
				if (Blocks_Modifier.checkBlockExists(blocks, row, column)) {
					// probe one down to if the cell is occupied
					// but make sure that that cell block is not part of the piece
					if (isOccupied(row, column + columnChange) 
							&& !Blocks_Modifier.checkBlockExists(blocks, row, column + columnChange)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean hasCollidedRow(Block [] blocks, int rowChange) {
		int leftSide = Blocks_Modifier.getLeftLocationOfBlocks(blocks);
		int rightSide = Blocks_Modifier.getRightLocationOfBlocks(blocks);
		int bottomSide = Blocks_Modifier.getBottomLocationOfBlocks(blocks);
		int topSide = Blocks_Modifier.getTopLocationOfBlocks(blocks);
		
		for (int row = topSide; row <= bottomSide; row++) {
			for (int column = leftSide; column <= rightSide; column++) {
				if (Blocks_Modifier.checkBlockExists(blocks, row, column)) {
					// probe one down to if the cell is occupied
					// but make sure that that cell block is not part of the piece
					if (isOccupied(row + rowChange, column) 
							&& !Blocks_Modifier.checkBlockExists(blocks, row + rowChange, column)) {
						return true;
					}
				}
			}
		}	
		return false;
	}

	private void fillMultipleGridLocations(Block [] blocks) {
		
		for (Block block : blocks) {
			if (block != null) {
				tetrisGrid.occupyPosition(block.getRow(), block.getColumn());
			}
		}
	}

	private void unFillMultipleGridLocations(Block [] blocks) {
	
		for (Block block : blocks) {
			if (block != null) {
				tetrisGrid.unoccupyPosition(block.getRow(), block.getColumn());
			}
		}
	}
}
