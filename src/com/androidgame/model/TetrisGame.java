package com.androidgame.model;

import com.androidgame.controller.MainActivity;

import android.app.Activity;

/**
 * Collaborates between TetrisRules and Activity.
 * Extend this in the future.
 * @author kenny
 */
public class TetrisGame {
	private MainActivity mainAct;
	private TetrisRules tetrisRules;
	
	// Update to include whatever activity
	public TetrisGame(MainActivity mainActivity) {
		this.mainAct = mainActivity;
		this.tetrisRules = new TetrisRules();
	}
	
	public void startGame() {
		tetrisRules.startGame();
	}
	
	public void nextGameIteration(Actions action) {
		if (tetrisRules.hasPieceReachedBottomEdge() || tetrisRules.hasCollidedWithAnotherPiece()) {
			
			if (tetrisRules.isBottomRowFilled()) 
				tetrisRules.clearBottomRow();
			
			tetrisRules.getNextPiece();
			updateUI(tetrisRules.getCurrentRow(), tetrisRules.getCurrentColumn(), true);
		} else {
			int previousRow = tetrisRules.getCurrentRow();
			int previousColumn = tetrisRules.getCurrentColumn();
			
			// for testing only, user drops piece manually.
			if (action != Actions.DROP) {
				// do something with control input
				tetrisRules.performAction(action);
				tetrisRules.dropPieceDown();
			} else
				tetrisRules.dropPieceDown();
			
			// update UI, we want to clear current location of (row, column).
			updateUI(previousRow, previousColumn, false);
			
			// update UI, we want to render new location of (row, column).
			updateUI(tetrisRules.getCurrentRow(), tetrisRules.getCurrentColumn(), true);
		}
	}
	
	public void updateUI(int row, int column, boolean occupied) {
		mainAct.updateView(row, column, occupied);
	}
}
