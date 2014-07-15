package com.androidgame.model;

/**
 * Class contains methods to move a Tetris piece: move left, move right, rotate.
 * @author kenny
 */
public class TetrisPieceController {
	private TetrisPiece currentPiece;
	private GridManager gridManager;
	
	public TetrisPieceController(GridManager gridManager) {
		this.gridManager = gridManager;
	}
	
	public TetrisPieceController(TetrisPiece currentPiece, GridManager gridManager) {
		this.currentPiece = currentPiece;
		this.gridManager = gridManager;
	}
	
	public void setCurrentTetrisPiece(TetrisPiece currentPiece) {
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
	

}
