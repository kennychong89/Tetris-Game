package com.androidgame.model;

import com.androidgame.model.enums.Actions;
import com.androidgame.model.enums.TetrisPieceName;

/**
 * Represents a single Tetris piece.
 * @author kenny
 */
public class TetrisPiece {
	// holds blocks to represent the tetris piece
	private Block[] piece;
	
	// starting coordinates 
	private int startingRow = 0;
	private int startingColumn = 0;
	
	private TetrisPieceName pieceName;
	private final int PIECE_SIZE = 4;
	
	// constructor
	public TetrisPiece(int startingRow, int startingColumn) {
		this.startingRow = startingRow;
		this.startingColumn = startingColumn;
		
		// since we have single piece, block size is one.
		piece = new Block[1];
		
		pieceName = TetrisPieceName.SINGLE_PIECE;
		
		// initialize a one block piece.
		createPiece();
	}
	
	// constructor
	public TetrisPiece(TetrisPieceName pieceName, int startingRow, int startingColumn) {
		this(startingRow, startingColumn);
		
		this.pieceName = pieceName;
	}
	
	public String getTetrisPieceName() {
		return pieceName.name();
	}
	
	public Block[] getPieceBlocks() {
		return piece;
	}
	
	private Block[] createTetrisPieceCopy() {
		Block[] copy = new Block[piece.length];
		
		// returns copy of blocks.
		for (int i = 0; i < piece.length; i++) {
			Block block = piece[i];
			int row = block.getRow();
			int column = block.getColumn();
			
			copy[i] = new Block(row, column); 
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
				piece[0] = new Block(startingRow, startingColumn);
				break;
		}
	}
}
