package com.androidgame.model;

import java.util.Timer;
import java.util.TimerTask;

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
	public static final int GRAVITY = 1000; // 1000 ms or 1 second
	
	private Grid tetrisGrid;
	private Tetriminos currentPiece;

	//private boolean hasStarted;
	
	public TetrisRules() {
		tetrisGrid = new Grid();
		currentPiece = null;
	}
	
	public TetrisRules(Grid tetrisGrid) {
		this.tetrisGrid = tetrisGrid;
		currentPiece = null;
	}
	
	public void startGame() {
		getNextPiece();
		setTetrisPieceToLocation(0, 0);
	}
	
	// Test method - initially fills the bottom row down with tetris pieces
	// except for position (17,5). Used to test methods isRowCompleted(),
	// clearRow(), cascadeDown().
	public void startGameWithBottomFilled() {
		getNextPiece();
		
		int bottomGridRow = tetrisGrid.getGridRows() - 1;
		int bottomGridColumn = tetrisGrid.getGridColumns();
		
		for (int i = 0; i < bottomGridColumn; i++) {
				if (i != bottomGridColumn / 2)
					tetrisGrid.occupyPosition(bottomGridRow, i);
		}
		
		setTetrisPieceToLocation(0, bottomGridColumn / 2);
	}
	
	public int getRows() {
		return tetrisGrid.getGridRows();
	}
	
	public int getGridColumns() {
		return tetrisGrid.getGridColumns();
	}
	
	public boolean isColumnFilled(int row, int column) {
		return !tetrisGrid.isEmpty(row, column);
	}
	
	public void clearBottonRow() {
		int bottomGridRow = tetrisGrid.getGridRows() - 1;
		
		tetrisGrid.clearRows(bottomGridRow);
	}
	/*
	public void startGame() {
		// generate game piece
		currentPiece = getNextPiece();
		
		// place piece into top middle position of the grid.
		currentPiece.updatePosition(tetrisGrid.getGridColumns() / 2, 0);
		
		// update tetris grid
		tetrisGrid.occupyPosition(currentPiece.getRow(), currentPiece.getColumn());
		
		// drop the piece downward the grid determined by the gravity time
		// create a new timer
		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// check for game over condition
				if (isGameOver()) {
					// end the game
					endGame();
				} else {
					// check if dropping the piece down will cause a collision
					if (willCollide(currentPiece.getRow() + 1, currentPiece.getColumn())) {
						// if piece will collide, place the piece to position before collision
						// set the grid position to occupy and move to the next piece
						tetrisGrid.occupyPosition(currentPiece.getRow(), currentPiece.getColumn());
						
						currentPiece = getNextPiece();
						currentPiece.updatePosition(tetrisGrid.getGridColumns() / 2, 0);
						
					} else {
						// if piece has no collision, continue dropping down
						// (will change depending on the piece but for now assume 1x1 piece will just on top of the piece 
						// it was going to collide with)
						currentPiece.updatePosition(currentPiece.getColumn(), currentPiece.getRow() + 1);
					}	
						
					// check if the piece has reached the bottom of the grid 
					    // if yes, we stop and and retrieve the next piece
						// if no, move the piece down the grid rows
				}
			}
		}, 0, GRAVITY);
	}	
	*/
	
	public void pauseGame() {
		
	}
	
	public void resetGame() {
		
	}
	
	public void endGame() {
		
	}
	
	public boolean isGameOver() {
		return false;
	}
	
	// test method - checks if bottom row contains no empty spaces.
	// Will expand this later to check different rows.
	public boolean isBottomRowFilled() {
		int bottomGridRow = tetrisGrid.getGridRows() - 1;
		int bottomGridColumn = tetrisGrid.getGridColumns();
		
		for (int i = 0; i < bottomGridColumn; i++) {
			if (tetrisGrid.isEmpty(bottomGridRow, i))
				return false;
		}
			
		return true;
	}
	
	// test method - clears out the bottom row. 
	// This method does not cascade down the rows above the bottom rows 
	public void clearBottomRow() {
		int bottomGridRow = tetrisGrid.getGridRows() - 1;
		
		tetrisGrid.clearRows(bottomGridRow);
	}
	
	
	// test method - cascades the rest of rows down only if bottom row has been cleared
	public void cascadeRowDown() {
		
	}
	
	public boolean willCollide(int row, int column) {
		// will change later. used only for testing.
		return !tetrisGrid.isEmpty(row, column);
	}
	
	public void getNextPiece() {
		currentPiece = new Tetriminos();
	}
	
	// test method
	public void setTetrisPieceToLocation(int row, int column) {
		if (currentPiece != null)
			currentPiece.updatePosition(column, row);
	}
	
	// test method
	public void dropTetrisPiece() {

		// retrieve current location of the piece
		int row = currentPiece.getRow();
		int column = currentPiece.getColumn();
		
		// drop the piece one row down on the grid unless it is at the bottom
		if (!hasReachedBottomEdge() && !hasCollidedBelow()) {
			tetrisGrid.unoccupyPosition(row, column);
			tetrisGrid.occupyPosition(row + 1, column);
			
			// update tetris piece
			currentPiece.updatePosition(column, row + 1);
		}
	}
	
	// test method
	public void moveTetrisPieceLeft() {
		// retrieve the current location of the piece
		int row = currentPiece.getRow();
		int column = currentPiece.getColumn();
		
		// move the piece to the left on the grid unless it is touching the grid's left corner
		if (!hasReachedLeftEdge() && !hasCollidedLeft()) {
			tetrisGrid.unoccupyPosition(row, column);
			tetrisGrid.occupyPosition(row, column - 1); 
			
			// update tetris piece
			currentPiece.updatePosition(column - 1, row);
		}
	}
	
	// test method
	public void moveTetrisPieceRight() {
		// retrieve the current location of the piece
		int row = currentPiece.getRow();
		int column = currentPiece.getColumn();
		
		// move the piece to the right on the grid unless it is touching the grid's right corner
		if (!hasReachedRightEdge() && !hasCollidedRight()) {
			tetrisGrid.unoccupyPosition(row, column);
			tetrisGrid.occupyPosition(row, column + 1);
			
			// update tetris piece
			currentPiece.updatePosition(column + 1, row);
		}
	}
	public int getTetrisPieceCurrentRow() {
			return currentPiece.getRow();
	}
	
	public int getTetrisPieceCurrentColumn() {
			return currentPiece.getColumn();
	}
	
	public boolean hasReachedBottomEdge() {
		return currentPiece.getRow() == (tetrisGrid.getGridRows() - 1);
	}
	
	public boolean hasReachedLeftEdge() {
		return currentPiece.getColumn() == 0;
	}
	
	public boolean hasReachedRightEdge() {
		return currentPiece.getColumn() == (tetrisGrid.getGridColumns() - 1);
	}
	
	// update for row, column argument.
	public boolean hasCollided(int row, int column) {
		return !tetrisGrid.isEmpty(row, column);
	}
	
	public boolean hasCollidedBelow() {
		return hasCollided(currentPiece.getRow() + 1, currentPiece.getColumn());
	}
	
	public boolean hasCollidedLeft() {
		return hasCollided(currentPiece.getRow(), currentPiece.getColumn() - 1);
	}
	
	public boolean hasCollidedRight() {
		return hasCollided(currentPiece.getRow(), currentPiece.getColumn() + 1);
	}
}
