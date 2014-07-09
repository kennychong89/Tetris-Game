package com.androidgame.model;

/**
 * Classes generates random Tetris pieces.
 * Note: Should be a Singleton object.
 * @author kenny
 */
public class TetrisPieceGenerator {
	private Tetriminos currentPiece;
	
	public TetrisPieceGenerator() {
		currentPiece = new Tetriminos();
	}
	
	public void createNextPiece() {
		currentPiece = new Tetriminos();
	}
	
	public Tetriminos getCurrentPiece() {
		return currentPiece;
	}
}
