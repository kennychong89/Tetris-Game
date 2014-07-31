package com.androidgame.model.tetrispiece;


public interface TetrisPieceTypes {
	public static final int START_ROW = 0;
	public static final int START_COLUMN = 0;
	
	public static final String SINGLE_PIECE_NAME = "Single Block";
	public static final int SINGLE_PIECE_SIZE = 1;
	public static final int[] SINGLE_PIECE_ROWS = {START_ROW};
	public static final int[] SINGLE_PIECE_COLUMNS = {START_COLUMN};
	
	public static final String L_PIECE_NAME = "L-Shape";
	public static final int L_PIECE_SIZE = 4;
	public static final int[] L_PIECE_ROWS = {START_ROW, START_ROW + 1, START_ROW + 2, START_ROW + 2};
	public static final int[] L_PIECE_COLUMNS = {START_COLUMN, START_COLUMN, START_COLUMN, START_COLUMN + 1};
	
	public static final String REV_L_PIECE_NAME = "Reversed L-Shape";
	public static final int REV_L_PIECE_SIZE = 4;
	public static final int[] REV_L_PIECE_ROWS = {START_ROW, START_ROW + 1, START_ROW + 2, START_ROW + 2};
	public static final int[] REV_L_PIECE_COLUMNS = {START_COLUMN + 1, START_COLUMN + 1, START_COLUMN + 1, START_COLUMN};
	
	public static final String BOX_PIECE_NAME = "Block-Shape";
	public static final int BOX_PIECE_SIZE = 4;
	public static final int[] BOX_PIECE_ROWS = {START_ROW, START_ROW + 1, START_ROW, START_ROW + 1};
	public static final int[] BOX_PIECE_COLUMNS = {START_COLUMN, START_COLUMN, START_COLUMN + 1, START_COLUMN + 1};
	
	public static final String STRAIGHT_PIECE_NAME = "Straight Line-Shape";
	public static final int STRAIGHT_PIECE_SIZE = 4;
	public static final int[] STRAIGHT_PIECE_ROWS = {START_ROW, START_ROW + 1, START_ROW + 2, START_ROW + 3};
	public static final int[] STRAIGHT_PIECE_COLUMNS = {START_COLUMN, START_COLUMN, START_COLUMN, START_COLUMN};
	
	public static final String S_PIECE_NAME = "S-Shape";
	public static final int S_PIECE_SIZE = 4;
	public static final int[] S_PIECE_ROWS = {START_ROW, START_ROW + 1, START_ROW + 1, START_ROW + 2};
	public static final int[] S_PIECE_COLUMNS = {START_COLUMN, START_COLUMN, START_COLUMN + 1, START_COLUMN + 2};
	
	public static final String REV_S_PIECE_NAME = "Reverse S-Shape";
	public static final int REV_S_PIECE_SIZE = 4;
	public static final int[] REV_S_PIECE_ROWS = {START_ROW, START_ROW + 1, START_ROW + 1, START_ROW + 2};
	public static final int[] REV_S_PIECE_COLUMNS = {START_COLUMN + 1, START_COLUMN + 1, START_COLUMN, START_COLUMN};
	
	public static final String T_PIECE_NAME = "T-Shape";
	public static final int T_PIECE_SIZE = 4;
	public static final int[] T_PIECE_ROWS = {START_ROW, START_ROW + 1, START_ROW + 1, START_ROW + 1};
	public static final int[] T_PIECE_COLUMNS = {START_COLUMN + 1, START_COLUMN, START_COLUMN + 1, START_COLUMN + 2};
	
}
