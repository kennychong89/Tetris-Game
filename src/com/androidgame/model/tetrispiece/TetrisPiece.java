package com.androidgame.model.tetrispiece;

import com.androidgame.model.enums.TetrisPieceName;

/**
 * Represents a single Tetris piece.
 * @author kenny
 */
public abstract class TetrisPiece implements TetrisPieceTypes {
	
	// holds blocks to represent the tetris piece
	private Block[] piece;
	
	private String pieceName;
	private boolean rotatable;
	
	public String getPieceName() {
		return pieceName;
	}
	
	public void setPieceName(String name) {
		this.pieceName = name;
	}
	
	public boolean isPieceRotatable() {
		return rotatable;
	}

	public void setPieceRotatable(boolean rotatable) {
		this.rotatable = rotatable;
	}
	
	public Block[] getBlocks() {
		return piece;
	}
	
	public void setBlock(Block[] blocks) {
		this.piece = blocks;
	}

	public abstract void initPiece();
}
