package com.androidgame.model.tetrisgrid;

import java.util.ArrayList;

import com.androidgame.model.tetrispiece.Block;
import com.androidgame.model.tetrispiece.TetrisPiece;

/**
 * Class will maintain updates to the Tetris grid, including checking for
 * collisions, adding and removing blocks from the grid.
 * 
 * @author kenny
 */
public class GridManager {
	private Grid tetrisGrid;

	// constants
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

	public boolean hasCollidedTop(int pieceTopSide) {
		return false;
	}

	public boolean hasCollidedBelow(Block [] blocks) {
		int leftSide = getLeftLocationOfBlocks(blocks);
		int rightSide = getRightLocationOfBlocks(blocks);
		int bottomSide = getBottomLocationOfBlocks(blocks);
		
		for (int i = leftSide; i <= rightSide; i++) {
			if (isOccupied(bottomSide + DROP_ONE, i))
				return true;
		}
		
		return false;
	}

	public boolean hasCollidedLeft(Block [] blocks) {
		int leftSide = getLeftLocationOfBlocks(blocks);
		int topSide = getTopLocationOfBlocks(blocks);
		int bottomSide = getBottomLocationOfBlocks(blocks);
		
		for (int i = topSide; i <= bottomSide; i++) {
			if (isOccupied(i, leftSide + MOVE_ONE_LEFT))
				return true;
		}
		
		return false;
	}

	public boolean hasCollidedRight(Block [] blocks) {
		int rightSide = getRightLocationOfBlocks(blocks);
		int topSide = getTopLocationOfBlocks(blocks);
		int bottomSide = getBottomLocationOfBlocks(blocks);
		
		for (int i = topSide; i <= bottomSide; i++) {
			if (isOccupied(i, rightSide + MOVE_ONE_RIGHT))
				return true;
		}
		
		return false;
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


	private int getBottomLocationOfBlocks(Block[] blocks) {
		// perform some checks.
		int row = blocks[0].getRow();

		for (int i = 1; i < blocks.length; i++) {
			int currBlockRow = blocks[i].getRow();

			if (currBlockRow > row)
				row = currBlockRow;
		}
		return row;
	}
		
	private int getTopLocationOfBlocks(Block[] blocks) {
		int row = blocks[0].getRow();

		for (int i = 1; i < blocks.length; i++) {
			int currBlockRow = blocks[i].getRow();

			if (currBlockRow < row)
				row = currBlockRow;
		}
		return row;
	}
		
	private int getLeftLocationOfBlocks(Block[] blocks) {
		int column = blocks[0].getColumn();

		for (int i = 1; i < blocks.length; i++) {
			int currBlockColumn = blocks[i].getColumn();

			if (currBlockColumn < column)
				column = currBlockColumn;
		}
		return column;
	}
		
	private int getRightLocationOfBlocks(Block[] blocks) {
		int column = blocks[0].getColumn();

		for (int i = 1; i < blocks.length; i++) {
			int currBlockColumn = blocks[i].getColumn();

			if (currBlockColumn > column)
				column = currBlockColumn;
		}
		return column;
	}
	
	// collision stuff
	/*
	 * public int getTetrisPieceCurrentColumn() { return
	 * currentPiece.getColumn(); }
	 */

	/*
	 * public void setTetrisPieceToLocation(int row, int column) { if
	 * (currentPiece != null) currentPiece.updatePosition(row, column); }
	 */

	/*
	 * NOT USED SINCE GRID HAS BEEN UPDATED WITH BOUNDARIES public boolean
	 * hasReachedLeftEdge() { return currentPiece.getColumn() == 0; }
	 * 
	 * public boolean hasReachedRightEdge() { return currentPiece.getColumn() ==
	 * (gridManager.getGridColumns() - 1); }
	 * 
	 * public boolean hasReachedBottomEdge() { return currentPiece.getRow() ==
	 * (gridManager.getGridRows() - 1); }
	 */
}
