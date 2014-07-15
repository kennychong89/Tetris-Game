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
		if (!gridManager.hasCollidedLeft(currentPiece.getPieceBlocks())) {
			// clear previous piece
			gridManager.updateGrid(currentPiece.getPieceBlocks(), false);

			// move piece to the left
			shiftPiece(MOVE_ONE_LEFT);

			// update grid
			gridManager.updateGrid(currentPiece.getPieceBlocks(), true);
		}
	}

	public void moveTetrisPieceRight() {
		// move the piece to the right on the grid unless it is touching the
		// grid's right corner
		if (!gridManager.hasCollidedRight(currentPiece.getPieceBlocks())) {
			// clear previous piece
			gridManager.updateGrid(currentPiece.getPieceBlocks(), false);

			// move piece to the right
			shiftPiece(MOVE_ONE_RIGHT);

			// update tetris piece
			gridManager.updateGrid(currentPiece.getPieceBlocks(), true);
		}
	}

	public void dropTetrisPiece() {
		// drop the piece one row down on the grid unless it is at the bottom
		if (!gridManager.hasCollidedBelow(currentPiece.getPieceBlocks())) {
			// clear previous piece
			gridManager.updateGrid(currentPiece.getPieceBlocks(), false);

			// move piece down
			dropPiece(DROP_ONE_DOWN);

			// update tetris piece
			gridManager.updateGrid(currentPiece.getPieceBlocks(), true);
		}
	}

	/*
	 * shift piece left (negative) or right (positive). How much shifted depends
	 * on value
	 */
	private void shiftPiece(int value) {
		Block[] blocks = currentPiece.getPieceBlocks();

		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];

			if (block != null) {
				int previousColumn = block.getColumn();

				block.setColumn(previousColumn + value);
			}
		}
	}

	/*
	 * drop piece down depending on value
	 */
	private void dropPiece(int value) {
		Block[] blocks = currentPiece.getPieceBlocks();

		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];

			if (block != null) {
				int previousRow = block.getRow();

				block.setRow(previousRow + value);
			}
		}
	}

	/*
	 * rotate piece clock-wise
	 */
	public void rotateTetrisPiece() {

	}
}
