package com.androidgame.model.enums;

public enum TetrisPieceName {
	SINGLE_PIECE("Single Block"),L_PIECE("L-Shape"), 
	REV_L_PIECE("Reversed L-Shape"), STRAIGHT_PIECE("Straight Line-Shape"), 
	S_PIECE("S-Shape"), REV_S_PIECE("Reverse S-Shape"), 
	T_PIECE("T-Shape"), BOX_PIECE("BOX-SHAPE");
	
	private String s;
	
	TetrisPieceName(String s) {
		this.s = s;
	}
}
