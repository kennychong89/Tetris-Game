package com.androidgame.model.tetrispiece;

public class Rev_L_Piece extends TetrisPiece {
	private Block[] piece;
	
	public Rev_L_Piece() {
		initPiece();
		setPieceName(REV_L_PIECE_NAME);
		setBlock(piece);
	}

	@Override
	public void initPiece() {
		// TODO Auto-generated method stub
		piece = new Block[4];
		
		int startingRow = 1;
		int startingColumn = 1;
		
		piece[0] = new Block(startingRow, startingColumn + 1);
		piece[1] = new Block(startingRow + 1, startingColumn + 1);
		piece[2] = new Block(startingRow + 2, startingColumn + 1);
		piece[3] = new Block(startingRow + 2, startingColumn);
	}

}
