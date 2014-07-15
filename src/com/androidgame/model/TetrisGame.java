package com.androidgame.model;

import com.androidgame.controller.MainActivity;
import com.androidgame.model.enums.Actions;

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
		if (tetrisRules.hasPieceCollided()) {
			
			if (tetrisRules.isBottomRowFilled()) 
				tetrisRules.clearBottomRow();
			
			tetrisRules.getNextPiece();
			updateUI(tetrisRules.getPieceRows(), tetrisRules.getPieceColumns(), true);
		} else {
			int [] previousPieceRows = tetrisRules.getPieceRows();
			int [] previousPieceColumns = tetrisRules.getPieceColumns();
			
			// for testing only, user drops piece manually.
			if (action != Actions.DROP) {
				// do something with control input
				tetrisRules.performAction(action);
				tetrisRules.dropPieceDown();
			} else
				tetrisRules.dropPieceDown();
			
			// update UI, we want to clear current location of (row, column).
			updateUI(previousPieceRows, previousPieceColumns, false);
			
			// update UI, we want to render new location of (row, column).
			updateUI(tetrisRules.getPieceRows(), tetrisRules.getPieceColumns(), true);
		}
	}
	
	private void updateUI(int [] rows, int [] columns, boolean occupied) {
		
		// assume for now that rows and columns have equal length
		for (int i = 0; i < rows.length; i++) {
			int row = rows[i];
			int column = columns[i];
			
			mainAct.updateView(row, column, occupied);
		}
	}
}
