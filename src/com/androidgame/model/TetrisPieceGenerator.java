package com.androidgame.model;

/**
 * Classes generates random Tetris pieces.
 * Note: Should be a Singleton object.
 * @author kenny
 */
public class TetrisPieceGenerator {
	private TetrisPiece currentPiece;
	
	public TetrisPieceGenerator() {
		currentPiece = new TetrisPiece();
	}
	
	public void createNextPiece() {
		currentPiece = new TetrisPiece();
	}
	
	public TetrisPiece getCurrentPiece() {
		return currentPiece;
	}
}
