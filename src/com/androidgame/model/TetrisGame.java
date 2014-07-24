package com.androidgame.model;

import java.util.ArrayList;

import com.androidgame.controller.MainActivity;
import com.androidgame.model.enums.Actions;

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
			
			if (tetrisRules.isBottomRowFilled()) {
				// fix later
				//tetrisRules.clearBottomRow();
			}
				tetrisRules.getNextPiece();
				updateUI(tetrisRules.getPieceRows(), tetrisRules.getPieceColumns(), true);
		} else {
			ArrayList<Integer> previousPieceRows = tetrisRules.getPieceRows();
			ArrayList<Integer> previousPieceColumns = tetrisRules.getPieceColumns();
			
			// for testing only, user drops piece manually.
			if (action != Actions.DROP) {
				// do something with control input
				tetrisRules.performAction(action);
				//tetrisRules.dropPieceDown();
			} else
				tetrisRules.dropPieceDown();
			
			// update UI, we want to clear current location of (row, column).
			updateUI(previousPieceRows, previousPieceColumns, false);
			
			// update UI, we want to render new location of (row, column).
			updateUI(tetrisRules.getPieceRows(), tetrisRules.getPieceColumns(), true);
		}
	}
	
	private void updateUI(ArrayList<Integer> rows, ArrayList<Integer> columns, boolean occupied) {
		
		// assume for now that rows and columns have equal length
		for (int i = 0; i < rows.size(); i++) {
			
			// rows[i] - 1 since tetris piece is working with a grid with left, right, bottom boundaries [1..GRID_ROW - 1], [1..GRID_COLUMN - 1],
			// whereas the UI grid us [0..ROW],[0..COLUMN].
			// fix this in the future.
			int row = rows.get(i) - 1;
			int column = columns.get(i) - 1;
			
			mainAct.updateView(row, column, occupied);
		}
	}
}
