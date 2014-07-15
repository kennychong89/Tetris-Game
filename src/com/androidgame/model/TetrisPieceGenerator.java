package com.androidgame.model;

import java.util.Random;

import com.androidgame.model.enums.TetrisPieceName;

/**
 * Classes generates random Tetris pieces.
 * Note: Should be a Singleton object.
 * @author kenny
 */
public class TetrisPieceGenerator {
	private TetrisPiece currentPiece;
	
	public TetrisPieceGenerator() {
		// piece will initially start at (1,5)
		currentPiece = new TetrisPiece(1,5);
	}
	
	public void createNextPiece() {
		currentPiece = new TetrisPiece(1,5);
	}
	
	public TetrisPiece getCurrentPiece() {
		return currentPiece;
	}
	
	private TetrisPieceName generateRandomPieceName() {
		TetrisPieceName [] names = TetrisPieceName.values();
		
		// simple random generator
		Random r = new Random();
		int selection = r.nextInt(names.length);
		
		return names[selection];
	}
}
