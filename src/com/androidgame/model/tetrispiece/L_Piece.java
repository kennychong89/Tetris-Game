package com.androidgame.model.tetrispiece;

public class L_Piece extends TetrisPiece {

	private Block[] piece;
	
	public L_Piece() {
		initPiece();
		setPieceName(L_PIECE_NAME);
		setBlock(piece);
	}

	@Override
	public void initPiece() {
		piece = new Block[4];
		
		int startingRow = 1;
		int startingColumn = 1;
		
		piece[0] = new Block(startingRow, startingColumn);
		piece[1] = new Block(startingRow + 1, startingColumn);
		piece[2] = new Block(startingRow + 2, startingColumn);
		piece[3] = new Block(startingRow + 2, startingColumn + 1);
	}
}
