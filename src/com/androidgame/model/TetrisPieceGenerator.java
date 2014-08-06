package com.androidgame.model;

import java.util.Random;

import com.androidgame.model.enums.TetrisPieceName;
import com.androidgame.model.tetrispiece.*;

/**
 * Classes generates random Tetris pieces.
 * Note: Should be a Singleton object.
 * @author kenny
 */
public class TetrisPieceGenerator {
	
	public TetrisPiece getRandomPiece() {
		TetrisPieceName [] names = TetrisPieceName.values();
		
		// simple random generator
		Random r = new Random();
		int selection = r.nextInt(names.length - 6);
		
		return getPiece(names[selection]);
	}
	
	public TetrisPiece getPiece(TetrisPieceName pieceName) {
		switch(pieceName) {

		case SINGLE_PIECE:
			return new Single_Piece();
		case L_PIECE:
			return new L_Piece();
		case REV_L_PIECE:
			return new Rev_L_Piece();
		case STRAIGHT_PIECE:
			return new Straight_Piece();
		case S_PIECE:
			return new S_Piece();
		case REV_S_PIECE:
			return new Rev_S_Piece();
		case T_PIECE:
			return null;
		case BOX_PIECE:
			return new Box_Piece();
		default:
			return new Single_Piece();
		}
	}
}
