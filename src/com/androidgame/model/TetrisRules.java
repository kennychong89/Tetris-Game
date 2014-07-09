package com.androidgame.model;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Color;

/**
 * Class will represent simplified rules for Tetris. Basically the rules are: 
 * 
 * a) A Tetris piece will be randomly generated from the start (at the moment there
 * will only be a single type of Tetris piece, a 1x1 block).
 * 
 * b) The Tetris piece will drop according to the gravity rule.
 * 
 * c) The gravity is basic: after one second has passed, the Tetris piece will drop down.
 * 
 * d) The Tetris piece will continue to drop down until it hits the bottom of the grid or if
 * it has collided with another piece.
 * 
 * c) After the Tetris piece stops dropping, a new Tetris piece will generate from the start
 * and will repeat b).
 * 
 * d) The game is over if the accumulation of Tetris pieces hits the ceiling of the grid.
 * @author kenny
 */
public class TetrisRules {
	// not sure if needed 
	public static final int GRAVITY = 1000; // 1000 ms or 1 second
	private GridManager gridManager;
	private TetrisPieceGenerator pieceGenerator;
	private TetrisPieceController pieceController;
	//private boolean hasStarted;
	
	public TetrisRules() {
		gridManager = new GridManager();
		pieceGenerator = new TetrisPieceGenerator();
		pieceController = new TetrisPieceController(pieceGenerator.getCurrentPiece(), gridManager);
	}
	
	// Test method - initially fills the bottom row down with tetris pieces
	// except for position (17,5). Used to test methods GridManager.isRowCompleted(),
	// GridManager.clearRow(), GridManager.cascadeDown().
	public void startGame() {
		// testing
		//gridManager.fillGridBottom();
		pieceController.setTetrisPieceToLocation(0, 5);
	}
	
	public void pauseGame() {
		
	}
	
	public void resetGame() {
		
	}
	
	public void endGame() {
		
	}
	
	public boolean isGameOver() {
		return false;
	}
	
	public int getCurrentRow() {
		return pieceController.getTetrisPieceCurrentRow();
	}
	
	public int getCurrentColumn() {
		return pieceController.getTetrisPieceCurrentColumn();
	}
	
	public boolean hasPieceReachedBottomEdge() {
		return pieceController.hasReachedBottomEdge();
	}
	
	public boolean hasCollidedWithAnotherPiece() {
		// checking if dropping piece collided with piece below it.
		int tetrisRow = pieceController.getTetrisPieceCurrentRow() + 1;
		int tetrisColumn = pieceController.getTetrisPieceCurrentColumn();

		return pieceController.hasCollided(tetrisRow, tetrisColumn);
	}
	
	public void getNextPiece() {
		pieceGenerator.createNextPiece();
		pieceController.setCurrentTetrisPiece(pieceGenerator.getCurrentPiece());
		pieceController.setTetrisPieceToLocation(0, 5);
	}
	
	// extend later
	public boolean isBottomRowFilled() {
		return gridManager.isBottomRowFilled();
	}
	
	public void clearBottomRow() {
		gridManager.clearBottomRow();
		gridManager.cascadeRowDown();
	}
	
	public void performAction(Actions action) {
		switch(action) {
			case LEFT:
				pieceController.moveTetrisPieceLeft();
				break;
			case RIGHT:
				pieceController.moveTetrisPieceRight();
				break;
			case DROP:
				pieceController.dropTetrisPiece();
				break;
			case ROTATE:
				pieceController.rotateTetrisPiece();
				break;
		}
	}
	
	public void dropPieceDown() {
		pieceController.dropTetrisPiece();
	}
}
