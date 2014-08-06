package com.androidgame.model;

import com.androidgame.model.enums.Actions;
import com.androidgame.model.tetrisgrid.GridManager;
import com.androidgame.model.tetrispiece.Block;
import com.androidgame.model.tetrispiece.TetrisPiece;

/**
 * Collaborates between TetrisRules and Activity.
 * Extend this in the future.
 * @author kenny
 */
public class TetrisGame {
	private GridManager gridManager;
	private TetrisPieceGenerator pieceGenerator;
	private TetrisPiece_Controller pieceController;
	private TetrisPiece currentPiece;
	private boolean hasStarted;
	private Actions currentAction;
	
	public TetrisGame() {
		gridManager = new GridManager();
		pieceGenerator = new TetrisPieceGenerator();
		
		currentPiece = pieceGenerator.getRandomPiece();
		pieceController = new TetrisPiece_Controller(currentPiece, gridManager);
		
		hasStarted = false;
		// no action yet.
		currentAction = null;
	}
	
	public boolean[][] startGameLoop() {
		if (!hasStarted())
			startGame();
		else {
			// piece either collided with another piece or has reached bottom
			if (hasCollision()) {
				// some check to see if there are any filled rows.
				// then perform a collapse on the grid
				
				// do next iteration.
				performNextIteration();
			} else {
				// retrieve action.
				Actions userAction = getCurrentAction();
				
				// perform the action
				performAction(userAction);
			}
		}
	
		return gridManager.getGridInfo();
	}
	
	public boolean hasStarted() {
		return hasStarted;
	}
	
	public void setStart(boolean val) {
		hasStarted = val;
	}
	
	public boolean hasPerformedAction() {
		return currentAction != null;
	}
	
	public Actions getCurrentAction() {
		return currentAction;
	}
	
	public void startGame() {
		hasStarted = true;
		
		// reset grid
		gridManager.clearGrid();
		
		// generate random piece
		currentPiece = pieceGenerator.getRandomPiece();
		
		// set pieceController to currentPiece 
		pieceController.setCurrentTetrisPiece(currentPiece);
		
		pieceController.placeCurrentTetrisPieceAt(2, 2);
		
		// other stuff like clearing levels and score.
	}

	// this should be an interface
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
	
	// get copy of data of grid for reference.
	public boolean[][] getPieceData() {
		return gridManager.getGridInfo();
	}
	
	public boolean hasCollision() {
		return hasCollided();
	}
	
	public boolean checkPieceHasReachedBottom() {
		return hasCollided();
	}
	
	public void performNextIteration() {
		currentPiece = pieceGenerator.getRandomPiece();
		pieceController.setCurrentTetrisPiece(currentPiece);
	}
	
	private boolean hasCollided() {
		return gridManager.hasCollidedBelow(currentPiece.getBlocks());
	}
}
