package com.androidgame.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Represents the game's UI.
 * @author kenny
 */
public class TetrisView extends View {

	//public final int getViewHeight = getHeight();
	//public final int getViewWidth = getWidth();
	
	// define the dimensions of the grid. for now it will be a 10 columns by 18 rows grid.
	public static final int GRID_COLUMNS = 10;
	public static final int GRID_ROWS = 18;
	
	private TetrisGridBox [][] tetrisGrid;
	private Paint defaultGridPaint;
	
	public TetrisView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// two for boundaries?
		tetrisGrid = new TetrisGridBox[GRID_ROWS][GRID_COLUMNS];
		
		//defaultGridPaint = new Paint();
		//defaultGridPaint.setStrokeWidth(2);
		//defaultGridPaint.setStyle(Style.STROKE);
	}

	private void initGridView(int viewWidth, int viewHeight) {
		// for now see what getHeight and getWidth returns
		/*
		Log.d("ViewDimensions", "View Height: " + getMeasuredHeight());
		Log.d("ViewDimensions", "View Width: " + getMeasuredWidth());
		Toast t = Toast.makeText(this.getContext(), "Test", Toast.LENGTH_LONG);
		t.show();
		*/
		int roundedViewWidth = viewWidth - (viewWidth % (GRID_COLUMNS));
		int roundedViewHeight = viewHeight - (viewHeight % (GRID_ROWS));
		
		//Log.d("ViewDimensions", "viewWidth % GRID_COLUMNS " + (viewWidth % GRID_COLUMNS));
		//Log.d("ViewDimensions", "viewHeight % GRID_ROWS: " + (viewHeight % GRID_ROWS));
		
		//Log.d("ViewDimensions", "roundedViewWidth: " + roundedViewWidth);
		//Log.d("ViewDimensions", "roundedViewHeight: " + roundedViewHeight);
		
		int boxWidth = roundedViewWidth / (GRID_COLUMNS);
		int boxHeight = roundedViewHeight / (GRID_ROWS);
		

		//Log.d("ViewDimensions", "roundedBoxWidth: " + boxWidth);
		//Log.d("ViewDimensions", "roundedBoxHeight: " + boxHeight);
		
		for (int row = 0; row < tetrisGrid.length; row++) {
			for (int column = 0; column < tetrisGrid[0].length; column++) {
				// create Rect object with calculated boxWidth and boxHeight
				Rect rect = new Rect((column * boxWidth),(row * boxHeight),
						            (column + 1) * boxWidth,(row + 1) * boxHeight);
				Paint defaultGridPaint = new Paint();
				defaultGridPaint.setStrokeWidth(2);
				defaultGridPaint.setStyle(Style.STROKE);
				
				// create an TetrisGridBox object
				TetrisGridBox gridbox = new TetrisGridBox(rect, defaultGridPaint);
				tetrisGrid[row][column] = gridbox;
			}
		}
	}
	
	// test method
	public void updateGridPosition(int row, int column, int color, boolean filled) {
		Paint paint;
		
		if (row < tetrisGrid.length && column < tetrisGrid[0].length) {
			 paint = tetrisGrid[row][column].getPaint();
			 
			 // update box's paint color
			 paint.setColor(color);	 
			 if (filled)
				 paint.setStyle(Style.FILL);
			 else
				 paint.setStyle(Style.STROKE);
			 
			 // invalidate view
			 //invalidate();
			 
			 //Toast toast = Toast.makeText(this.getContext(), "Works?", Toast.LENGTH_SHORT);
			 //toast.show(); 
		}
	}
	
	
	public void updateGridPosition(ArrayList<Integer> rows, ArrayList<Integer> columns, int color, boolean filled) {
		// assume rows and columns are equal for now
		for (int i = 0; i < rows.size(); i++) {
			int row = rows.get(i);
			int column = columns.get(i);
			
			updateGridPosition(row - 1, column - 1, color, filled);
		}
		
		invalidate();
	}
	
	// test method - updates view by a single row with columns list
	public void updateGridPosition(int row, int [] columns, int color, boolean filled) {
		for (int column = 0; column < columns.length; column++) {
			updateGridPosition(row, column, color, filled);
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		initGridView(w, h);
		
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// Draw the grid.
		for (int row = 0; row < tetrisGrid.length; row++) {
			for (int column = 0; column < tetrisGrid[0].length; column++) {
				// create Rect object with calculated boxWidth and boxHeight
				Rect box = tetrisGrid[row][column].getRect();
				Paint paint = tetrisGrid[row][column].getPaint();
				
				canvas.drawRect(box, paint);
			}
		}
	}
}
