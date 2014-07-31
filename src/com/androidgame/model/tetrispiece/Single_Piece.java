package com.androidgame.model.tetrispiece;

public class Single_Piece extends TetrisPiece {
	private Block[] piece;
	
	public Single_Piece() {
		initPiece();
		setPieceName(SINGLE_PIECE_NAME);
		setBlock(piece);
	}
	
	@Override
	public void initPiece() {
		piece = new Block[1];
		
		int startingRow = 1;
		int startingColumn = 1;
		
		piece[0] = new Block(startingRow, startingColumn);
	}
}
