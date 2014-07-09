package com.androidgame.model;

/**
 * Class contains methods to move a Tetris piece: move left, move right, rotate.
 * @author kenny
 */
public class TetrisPieceController {
	private Tetriminos currentPiece;
	private GridManager gridManager;
	
	public TetrisPieceController(GridManager gridManager) {
		this.gridManager = gridManager;
	}
	
	public TetrisPieceController(Tetriminos currentPiece, GridManager gridManager) {
		this.currentPiece = currentPiece;
		this.gridManager = gridManager;
	}
	
	public void setCurrentTetrisPiece(Tetriminos currentPiece) {
		this.currentPiece = currentPiece;
	}
	
	public void moveTetrisPieceLeft() {
		// retrieve the current location of the piece
		int row = currentPiece.getRow();
		int column = currentPiece.getColumn();
				
		// move the piece to the left on the grid unless it is touching the grid's left corner
		if (!hasReachedLeftEdge() && !hasCollidedLeft()) {
			gridManager.unFillGridLocation(row, column);
			gridManager.fillGridLocation(row, column - 1); 
					
			// update tetris piece
			currentPiece.updatePosition(row, column - 1);
		}
	}
	
	public void moveTetrisPieceRight() {
		// retrieve the current location of the piece
		int row = currentPiece.getRow();
		int column = currentPiece.getColumn();
		
		// move the piece to the right on the grid unless it is touching the grid's right corner
		if (!hasReachedRightEdge() && !hasCollidedRight()) {
			gridManager.unFillGridLocation(row, column);
			gridManager.fillGridLocation(row, column + 1); 
			
			// update tetris piece
			currentPiece.updatePosition(row, column + 1);
		}
	}
	
	public void dropTetrisPiece() {

		// retrieve current location of the piece
		int row = currentPiece.getRow();
		int column = currentPiece.getColumn();
		
		// drop the piece one row down on the grid unless it is at the bottom
		if (!hasReachedBottomEdge() && !hasCollidedBelow()) {
			gridManager.unFillGridLocation(row, column);
			gridManager.fillGridLocation(row + 1, column);
			
			// update tetris piece
			currentPiece.updatePosition(row + 1, column);
		}
	}
	
	public void rotateTetrisPiece() {
		
	}
	
	public int getTetrisPieceCurrentRow() {
		return currentPiece.getRow();
	}
	
	public int getTetrisPieceCurrentColumn() {
		return currentPiece.getColumn();
	}
	
	public void setTetrisPieceToLocation(int row, int column) {
		if (currentPiece != null)
			currentPiece.updatePosition(row, column);
	}
	
	public boolean hasCollided(int pieceRow, int pieceColumn) {
		// will change later. used only for testing.
		return gridManager.isOccupied(pieceRow, pieceColumn);
	}
	
	public boolean hasCollidedBelow() {
		return hasCollided(currentPiece.getRow() + 1, currentPiece.getColumn());
	}
	
	public boolean hasCollidedLeft() {
		return hasCollided(currentPiece.getRow(), currentPiece.getColumn() - 1);
	}
	
	public boolean hasCollidedRight() {
		return hasCollided(currentPiece.getRow(), currentPiece.getColumn() + 1);
	}
	
	public boolean hasReachedLeftEdge() {
		return currentPiece.getColumn() == 0;
	}
	
	public boolean hasReachedRightEdge() {
		return currentPiece.getColumn()  == (gridManager.getGridColumns() - 1);
	}
	
	public boolean hasReachedBottomEdge() {
		return currentPiece.getRow() == (gridManager.getGridRows() - 1);
	}
}
