package com.androidgame.model;

/**
 * Class will maintain updates to the Tetris grid, including checking for
 * collisions, adding and removing blocks from the grid.
 * 
 * @author kenny
 */
public class GridManager {
	public Grid tetrisGrid;

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

	public void updateGrid(Block[] blocks, boolean fill) {
		// we're filling
		if (fill)
			fillMultipleGridLocations(blocks);
		else
			unFillMultipleGridLocations(blocks);
	}

	public void updateGrid(Block block, boolean fill) {
		if (fill)
			fillSingleGridLocation(block);
		else
			unFillSingleGridLocation(block);
	}

	public boolean hasCollidedTop(Block[] blocks) {
		return false;
	}

	public boolean hasCollidedBelow(Block[] blocks) {
		return hasCollided(DROP_ONE, 0, blocks);
	}

	public boolean hasCollidedLeft(Block[] blocks) {
		return hasCollided(0, MOVE_ONE_LEFT, blocks);
	}

	public boolean hasCollidedRight(Block[] blocks) {
		return hasCollided(0, MOVE_ONE_RIGHT, blocks);
	}

	// TODO
	public boolean hasCollidedBelow(Block block) {
		return false;
	}

	// TODO
	public boolean hasCollidedTop(Block block) {
		return false;
	}

	// TODO
	public boolean hasCollidedLeft(Block block) {
		return false;
	}

	// TODO
	public boolean hasCollidedRight(Block block) {
		return false;
	}

	private boolean hasCollided(int rowChange, int columnChange,
			Block[] blocks) {
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];

			if (block != null) {
				int blockRow = block.getRow();
				int blockColumn = block.getColumn();

				if (isOccupied(blockRow + rowChange, blockColumn + columnChange))
					return true;
			}
		}
		return false;
	}

	// TODO
	private boolean hasCollided(int rowChange, int columnChange, Block block) {
		return false;
	}

	private void fillSingleGridLocation(Block block) {
		if (block != null) {
			int blockRow = block.getRow();
			int blockColumn = block.getColumn();

			tetrisGrid.occupyPosition(blockRow, blockColumn);
		}
	}

	private void unFillSingleGridLocation(Block block) {
		if (block != null) {
			int blockRow = block.getRow();
			int blockColumn = block.getColumn();

			tetrisGrid.unoccupyPosition(blockRow, blockColumn);
		}
	}

	private void fillMultipleGridLocations(Block[] blocks) {
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
			fillSingleGridLocation(block);
		}
	}

	private void unFillMultipleGridLocations(Block[] blocks) {
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
			unFillSingleGridLocation(block);
		}
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
