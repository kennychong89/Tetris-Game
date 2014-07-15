package com.androidgame.model;

import com.androidgame.model.enums.Actions;
import com.androidgame.model.enums.TetrisPieceName;

/**
 * Represents a single Tetris piece.
 * @author kenny
 */
public class TetrisPiece {
	// holds blocks to represent the tetris piece
	private TetrisBlock[] piece;
	
	// starting coordinates 
	private int startingRow = 0;
	private int startingColumn = 0;
	
	private TetrisPieceName pieceName;
	private final int PIECE_SIZE = 4;
	
	// constructor
	public TetrisPiece(int startingRow, int startingColumn) {
		this.startingRow = startingRow;
		this.startingColumn = startingColumn;
		
		// assume most Tetris piece size is four
		piece = new TetrisBlock[PIECE_SIZE];
		
		pieceName = TetrisPieceName.SINGLE_PIECE;
		
		// initialize a one block piece.
		createPiece();
	}
	
	// constructor
	public TetrisPiece(TetrisPieceName pieceName, int startingRow, int startingColumn) {
		this(startingRow, startingColumn);
		
		this.pieceName = pieceName;
	}
	
	/**
	 * Test case
	 * @param column
	 * @param row
	 */
	public void updatePiece(Actions action) {
		switch (action) {
			case LEFT:
				// shift all columns by -1.
				shiftPiece(-1);
				break;
			case RIGHT:
				// shift all columns by +1.
				shiftPiece(1);
				break;
			case ROTATE:
				break;
			case DROP:
				// drop piece by -1.
				dropPiece(-1);
				break;
		}
		//piecePositions[0].setRow(row);
		//piecePositions[0].setColumn(column);
	}
	
	public TetrisBlock[] getPieceBlocks() {
		return createTetrisPieceCopy();
	}
	
	private TetrisBlock[] createTetrisPieceCopy() {
		TetrisBlock[] copy = new TetrisBlock[piece.length];
		
		// returns copy of blocks.
		for (int i = 0; i < piece.length; i++) {
			TetrisBlock block = piece[i];
			int row = block.getRow();
			int column = block.getColumn();
			
			copy[i] = new TetrisBlock(row, column); 
		}
		
		return copy;
	}
	
	private void createPiece() {
		switch (pieceName) {
			case L_PIECE:
				break;
			case REV_L_PIECE:
				break;
			case SINGLE_PIECE:
				// We will only use a single 1x1 piece
				piece[0] = new TetrisBlock(startingRow, startingColumn);
				break;
		}
	}

	/*
	 * shift piece left (negative) or right (positive). How much shifted depends on value.
	 */
	private void shiftPiece(int value) {
		for (int i = 0; i < piece.length; i++) { 
			TetrisBlock block = piece[i];
			
			if (block != null) {
				int previousColumn = block.getColumn();
				
				block.setColumn(previousColumn + value);
			}
		}
	}
	
	/*
	 * drop piece down depending on value.
	 */
	private void dropPiece(int value) {
		for (int i = 0; i < piece.length; i++) {
			TetrisBlock block = piece[i];
			
			if (block != null) {
				int previousRow = block.getRow();
				
				block.setRow(previousRow + value); 
			}
		}
	}
}
