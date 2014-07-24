package com.androidgame.model;

import com.androidgame.model.enums.Actions;

/**
 * Class contains methods to move a Tetris piece: move left, move right, rotate.
 * 
 * @author kenny
 */
public class TetrisPieceController {
	private TetrisPiece currentPiece;
	private GridManager gridManager;

	private final int DROP_ONE_DOWN = 1;
	private final int MOVE_ONE_LEFT = -1;
	private final int MOVE_ONE_RIGHT = 1;

	public TetrisPieceController(TetrisPiece piece) {
		this.gridManager = new GridManager();
		this.currentPiece = piece;
	}

	public TetrisPieceController(TetrisPiece piece, GridManager gridManager) {
		this.currentPiece = piece;
		this.gridManager = gridManager;
	}

	public void setCurrentTetrisPiece(TetrisPiece currentPiece) {
		this.currentPiece = currentPiece;
	}

	public void moveTetrisPieceLeft() {
		// move the piece to the left on the grid unless it is touching the
		// grid's left corner
		if (!gridManager.hasCollidedLeft(currentPiece)) {
			// clear previous piece
			gridManager.updateGrid(currentPiece, false);

			// move piece to the left
			shiftPiece(MOVE_ONE_LEFT);

			// update grid
			gridManager.updateGrid(currentPiece, true);
		}
	}

	public void moveTetrisPieceRight() {
		// move the piece to the right on the grid unless it is touching the
		// grid's right corner
		if (!gridManager.hasCollidedRight(currentPiece)) {
			// clear previous piece
			gridManager.updateGrid(currentPiece, false);

			// move piece to the right
			shiftPiece(MOVE_ONE_RIGHT);

			// update tetris piece
			gridManager.updateGrid(currentPiece, true);
		}
	}

	public void dropTetrisPiece() {
		// drop the piece one row down on the grid unless it is at the bottom
		if (!gridManager.hasCollidedBelow(currentPiece)) {
			// clear previous piece
			gridManager.updateGrid(currentPiece, false);

			// move piece down
			dropPiece(DROP_ONE_DOWN);

			// update tetris piece
			gridManager.updateGrid(currentPiece, true);
		}
	}

	/*
	 * shift piece left (negative) or right (positive). How much shifted depends
	 * on value
	 */
	private void shiftPiece(int value) {
		// no change in row, just column
		currentPiece.update(0,value);
	}

	/*
	 * drop piece down depending on value
	 */
	private void dropPiece(int value) {
		// no change in column, just row
		currentPiece.update(value, 0);
	}

	/*
	 * rotate piece clock-wise
	 */
	public void rotateTetrisPiece() {
		gridManager.updateGrid(currentPiece, false);
		
		currentPiece.rotate();
		
		// update tetris piece
		gridManager.updateGrid(currentPiece, true);
	}
}
