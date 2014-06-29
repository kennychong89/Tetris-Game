package com.androidgame.view;

import android.graphics.Paint;
import android.graphics.Rect;

/**
 * holds information about each box in the Tetris grid
 * @author kenny
 */
public class TetrisGridBox {
	private Rect rectangle;
	private Paint paint;
	
	public TetrisGridBox() {
		rectangle = new Rect();
		paint = new Paint();
	}
	
	public TetrisGridBox(Rect rectangle, Paint paint) {
		this.rectangle = rectangle;
		this.paint = paint;
	}
	
	public Rect getRect() {
		return rectangle;
	}
	
	public Paint getPaint() {
		return paint;
	}
}
