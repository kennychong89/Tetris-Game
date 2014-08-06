package com.androidgame.model.tetrispiece_utilities;

import com.androidgame.model.tetrispiece.Block;
import com.androidgame.model.tetrispiece.TetrisPiece;

public class TetrisPiece_Displayer {

	// Methods below only used for testing //
	public static void printPieceOnGrid(TetrisPiece piece, int gridWidth, int gridHeight) {
		Block [] blocks = piece.getBlocks();
		
		String[][] grid = new String[gridWidth][gridHeight];

		initGrid(grid);

		for (int i = 0; i < blocks.length; i++) {
			int blockRow = blocks[i].getRow();
			int blockColumn = blocks[i].getColumn();

			grid[blockRow][blockColumn] = "y";
		}

		printGrid(grid);
	}
	
	public static void printPieceData(TetrisPiece piece) {
		Block [] blocks = piece.getBlocks();
		
		for (int i = 0; i < blocks.length; i++) {
			int blockRow = blocks[i].getRow();
			int blockColumn = blocks[i].getColumn();

			System.out.println(blockRow + ", " + blockColumn);
		}
	}
	
	private static void initGrid(String[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = "x";
			}
		}
	}

	private static void printGrid(String[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j]);
			}

			System.out.println();
		}
	}
}
