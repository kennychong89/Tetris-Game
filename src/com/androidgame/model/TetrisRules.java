package com.androidgame.model;

import java.util.ArrayList;

import com.androidgame.model.enums.Actions;
import com.androidgame.model.tetrisgrid.GridManager;
import com.androidgame.model.tetrispiece.Block;
import com.androidgame.model.tetrispiece.TetrisPiece;
import com.androidgame.model.tetrispiece_utilities.Blocks_Modifier;

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
	private TetrisPiece_Controller pieceController;
	private TetrisPiece piece;
	
	//private boolean hasStarted;
	
	public TetrisRules() {
		gridManager = new GridManager();
		pieceGenerator = new TetrisPieceGenerator();
		
		piece = pieceGenerator.getRandomPiece();
		pieceController = new TetrisPiece_Controller(piece, gridManager);
	}
	
	// Test method - initially fills the bottom row down with tetris pieces
	// except for position (17,5). Used to test methods GridManager.isRowCompleted(),
	// GridManager.clearRow(), GridManager.cascadeDown().
	public void startGame() {
		// testing
		//gridManager.fillGridBottom();
		//pieceController.setTetrisPieceToLocation(0, 5);
	}
	
	public boolean isGameOver() {
		return false;
	}
	
	public ArrayList<Integer> getPieceRows() {
		return Blocks_Modifier.getRows(piece.getBlocks());
	}
	
	public ArrayList<Integer> getPieceColumns() {
		return Blocks_Modifier.getColumns(piece.getBlocks());
	}
	
	public boolean hasPieceCollided() {
		return gridManager.hasCollidedBelow(piece.getBlocks());
	}
	
	public void getNextPiece() {
		piece = pieceGenerator.getRandomPiece();
		pieceController.setCurrentTetrisPiece(piece);
	}
	
	// extend later
	public boolean isBottomRowFilled() {
		return false;
		//return gridManager.isBottomRowFilled();
	}
	
	public void clearBottomRow() {
		//gridManager.clearBottomRow();
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
