package com.androidgame.model.tetrispiece;

public class Box_Piece extends TetrisPiece {
	
	private Block[] piece;
	
	public Box_Piece() {
		initPiece();
		setPieceName(BOX_PIECE_NAME);
		setBlock(piece);
	}
	
	@Override
	public void initPiece() {
		// assume classic tetris pieces have 4 blocks
		// will make it modifiable in the future.
		piece = new Block[4]; 
		
		int startingRow = 1;
		int startingColumn = 1;
		
		piece[0] = new Block(startingRow, startingColumn);
		piece[1] = new Block(startingRow + 1, startingColumn);
		piece[2] = new Block(startingRow, startingColumn + 1);
		piece[3] = new Block(startingRow + 1, startingColumn + 1);
	}
}
