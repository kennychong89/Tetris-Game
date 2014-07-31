package com.androidgame.model.tetrispiece;

public class T_Piece extends TetrisPiece {
	private Block [] piece;
	
	public T_Piece() {
		initPiece();
		setPieceName(T_PIECE_NAME);
		setBlock(piece);
	}
	
	@Override
	public void initPiece() {
		piece = new Block[4];
		
		int startingRow = 1;
		int startingColumn = 1;
		
		piece[0] = new Block(startingRow, startingColumn + 1);
		piece[1] = new Block(startingRow + 1, startingColumn + 0);
		piece[2] = new Block(startingRow + 1, startingColumn + 1);
		piece[3] = new Block(startingRow + 1, startingColumn + 2);
	}
}
