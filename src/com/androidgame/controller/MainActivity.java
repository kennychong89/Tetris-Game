package com.androidgame.controller;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidgame.model.Observer;
import com.androidgame.model.TetrisGame;
import com.androidgame.model.enums.Actions;
import com.androidgame.model.tetrispiece.Block;
import com.androidgame.tetrisyy.R;
import com.androidgame.view.TetrisView;

public class MainActivity extends ActionBarActivity implements OnTouchListener, Observer {
	
	private TetrisGame tetrisGame;
	private TetrisView tetrisView;
	
	// add this later as a possible fragment?
	private Button leftMoveButton;
	private Button rightMoveButton;
	private Button rotateButton;
	private Button dropButton;
	
	//private TetrisMain tetrisMain;
	private TetrisGame game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
	
		/*
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		*/
		
		tetrisView = (TetrisView) findViewById(R.id.tetris_view);
		tetrisView.setOnTouchListener(this);
		
		leftMoveButton = (Button) findViewById(R.id.move_left_button);
		rightMoveButton = (Button) findViewById(R.id.move_right_button);
		dropButton = (Button) findViewById(R.id.drop_down_button);
		
		// This should button should rotate, but for now, it will be used to start game.
		rotateButton = (Button) findViewById(R.id.rotate_button);
		
		leftMoveButton.setOnClickListener(new MoveTetrisPieceLeftButton());
		rightMoveButton.setOnClickListener(new MoveTetrisPieceRightButton());
		dropButton.setOnClickListener(new DropButton());
		rotateButton.setOnClickListener(new RotateButton());
		
		game = new TetrisGame();
		//tetrisMain = new TetrisMain();
		//this.register(tetrisMain);
		//tetrisMain.register(this);
		//game.register(this);
	}
	
	public void startGameLoop() {
		if (!game.hasStarted()) 
			game.startGame();
		else {
			// piece either collided with another piece or has reached bottom
			if (game.hasCollision()) {
				// some check to see if there are any filled rows.
				// then perform a collapse on the grid.
				
				// do next iteration
				game.performNextIteration();
			} else {
				Actions userAction = game.getCurrentAction();
				
				// testing, maybe something
				game.performAction(userAction);
			 
				
			}
		}
		
		//return game.getPieceData();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
	
		}			
		return false;
	}
	
	// test method
	private int getRandomColor() {
		// test with 5 colors.
		Random random = new Random();
		int colorpicker = random.nextInt(5);
		
		switch (colorpicker) {
		case 0:
			return Color.BLUE;
		case 1:
			return Color.GREEN;
		case 2:
			return Color.YELLOW;
		case 3:
			return Color.MAGENTA;
		case 4:
			return Color.RED;
		default:
			return Color.BLACK;
		}
	}
	
	// test method - will update view by using tetrisGame
	public void updateView(int row, int column, boolean filled) {
		if (filled) 
			tetrisView.updateGridPosition(row, column, getRandomColor(), filled);
		else 
			tetrisView.updateGridPosition(row, column, Color.BLACK, filled);
	}
	
	private class MoveTetrisPieceLeftButton implements OnClickListener {
		@Override
		public void onClick(View view) {
			//notifyRecievers(Actions.LEFT);
			//tetrisMain.performAction(Actions.LEFT);
			//game.nextGameIteration(Actions.LEFT);
		}
	}
	
	private class MoveTetrisPieceRightButton implements OnClickListener {
		@Override
		public void onClick(View view) {
			//notifyRecievers(Actions.RIGHT);
			//tetrisMain.performAction(Actions.RIGHT);
			//game.nextGameIteration(Actions.RIGHT);
		}
	}
	
	private class DropButton implements OnClickListener {
		@Override
		public void onClick(View view) {
			//notifyRecievers(Actions.DROP);
			//tetrisMain.performAction(Actions.DROP);
			//game.nextGameIteration(Actions.DROP);
		}
	}
	
	private class RotateButton implements OnClickListener {
		@Override
		public void onClick(View view) {
			//notifyRecievers(Actions.ROTATE);
			//tetrisMain.performAction(Actions.ROTATE);
			//game.nextGameIteration(Actions.ROTATE);
		}
	}

	@Override
	public void update(ArrayList<Integer> rows, ArrayList<Integer> columns,
			boolean filled) {
		if (filled) 
			tetrisView.updateGridPosition(rows, columns, getRandomColor(), filled);
		else 
			tetrisView.updateGridPosition(rows, columns, Color.BLACK, filled);
	}
}
