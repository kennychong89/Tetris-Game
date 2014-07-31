package com.androidgame.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.androidgame.controller.ActionReciever;
import com.androidgame.model.enums.Actions;

public class TetrisMain implements UIRegister, ActionReciever {
	private ArrayList<TetrisUI_Listener> observers;
	private ArrayList<Integer> rows;
	private ArrayList<Integer> columns;
	private boolean filled;
	
	private TetrisGame game;
	
	public TetrisMain() {
		observers = new ArrayList<TetrisUI_Listener>();
		rows = new ArrayList<Integer>();
		columns = new ArrayList<Integer>();
		filled = false;
		game = new TetrisGame(this);
	}
	
	@Override
	public void register(TetrisUI_Listener registerObs) {
		observers.add(registerObs);
	}

	@Override
	public void unregister(TetrisUI_Listener deleteObs) {
		int observerIndex = observers.indexOf(deleteObs);
		observers.remove(observerIndex);
	}

	@Override
	public void notifyListeners() {
		for (TetrisUI_Listener obs: observers) {
			// assume for now that row and column arrays have same length. check later.
			Iterator<Integer> rowIter = rows.iterator();
			Iterator<Integer> columnIter = columns.iterator();
			
			while(rowIter.hasNext() && columnIter.hasNext()) {
				int row = rowIter.next();
				int column = columnIter.next();
				
				// fix here later.
				obs.updateUI(row - 1, column - 1, filled);
			}
		}
	}

	public void update(ArrayList<Integer> rows, ArrayList<Integer> columns, boolean filled) {
		this.rows = rows;
		this.columns = columns;
		this.filled = filled;
		notifyListeners();
	}

	@Override
	public void performAction(Actions action) {
		game.nextGameIteration(action);
	}
}
