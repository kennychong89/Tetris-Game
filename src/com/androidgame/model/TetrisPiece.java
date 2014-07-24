package com.androidgame.model;

import java.util.ArrayList;

import com.androidgame.model.enums.TetrisPieceName;

/**
 * Represents a single Tetris piece.
 * @author kenny
 */
public class TetrisPiece {
	// holds blocks to represent the tetris piece
	private Block[][] piece;
	
	// starting coordinates 
	private int startingRow = 0;
	private int startingColumn = 0;
	
	private TetrisPieceName pieceName;
	private final int PIECE_SIZE = 4;
	
	// constructor
	public TetrisPiece(int startingRow, int startingColumn) {
		this.startingRow = startingRow;
		this.startingColumn = startingColumn;

		pieceName = TetrisPieceName.SINGLE_PIECE;
		
		// initialize a one block piece.
		createPiece();
	}
	
	// constructor
	public TetrisPiece(TetrisPieceName pieceName, int startingRow, int startingColumn) {
		this.startingRow = startingRow;
		this.startingColumn = startingColumn;
		
		this.pieceName = pieceName;
		
		createPiece();
	}
	
	public String getTetrisPieceName() {
		return pieceName.name();
	}
	
	public ArrayList<Integer> getPieceRows() {
		ArrayList<Integer> blockRows = new ArrayList<Integer>();
		
		for (int row = 0; row < piece.length; row++) {
			for (int column = 0; column < piece[0].length; column++) {
				Block block = piece[row][column];
				
				if (block != null) {
					int blockRow = block.getRow();
					
					blockRows.add(blockRow);
				}
					
			}
		}
		
		return blockRows;
	}
	
	public ArrayList<Integer> getPieceColums() {
		ArrayList<Integer> blockColumns = new ArrayList<Integer>();
		
		for (int row = 0; row < piece.length; row++) {
			for (int column = 0; column < piece[0].length; column++) {
				Block block = piece[row][column];
				
				if (block != null) {
					int blockColumn = block.getColumn();
					
					blockColumns.add(blockColumn);
				}
					
			}
		}
		
		return blockColumns;
	}
	
	public ArrayList<Block> getEntirePiece() {
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		for (int row = 0; row < piece.length; row++) {
			for (int column = 0; column < piece[0].length; column++) {
				Block block = piece[row][column];
				
				if (block != null) {
					Block copy = createBlockCopy(block);
					blocks.add(copy);
				}
			}
		}
		
		return blocks;
	}
	public ArrayList<Block> getLeftSideOfPiece() {
		ArrayList<Block> leftBlocks = new ArrayList<Block>();
		
		int column = 0;
		
		for (int row = 0; row < piece.length; row++) {
			Block block = piece[row][column];
			
			if (block != null) {
				Block copy = createBlockCopy(block);
				leftBlocks.add(copy);
			}
		}
		
		return leftBlocks;
	}
	
	public ArrayList<Block> getRightSideOfPiece() {
		ArrayList<Block> rightBlocks = new ArrayList<Block>();
		
		int column = piece[0].length - 1;
		
		for (int row = 0; row < piece.length; row++) {
			Block block = piece[row][column];
			
			if (block != null) {
				Block copy = createBlockCopy(block);
				rightBlocks.add(copy);
			}
		}
		
		return rightBlocks;
	}
	
	public ArrayList<Block> getBottomSideOfPiece() {
		ArrayList<Block> bottomBlocks = new ArrayList<Block>();
		
		int row = piece.length - 1;
		
		for (int column = 0; column < piece[0].length; column++) {
			Block block = piece[row][column];
			
			if (block != null) {
				Block copy = createBlockCopy(block);
				bottomBlocks.add(copy);
			}
		}
		
		return bottomBlocks;
	}

	public ArrayList<Block> getTopSideOfPiece() {
		ArrayList<Block> topBlocks = new ArrayList<Block>();
		
		int row = 0;
		
		for (int column = 0; column < piece[0].length; column++) {
			Block block = piece[row][column];

			if (block != null) {
				Block copy = createBlockCopy(block);
				topBlocks.add(copy);
			}
		}
		
		return topBlocks;
	}

	public void update(int rowChange, int columnChange) {
		for (int row = 0; row < piece.length; row++) {
			for (int column = 0; column < piece[0].length; column++) {
				Block block = piece[row][column];
				if (block != null) {
					int currentRow = block.getRow();
					int currentColumn = block.getColumn(); 
				
					block.setRow(currentRow + rowChange);
					block.setColumn(currentColumn + columnChange);
				}
			}
		}
	}
	
	public void rotate() {
		int oldColumnLength = piece[0].length;
		int oldRowLength = piece.length;
		
		Block [][] newPiece = new Block[oldColumnLength][oldRowLength];
		
		for (int row = 0; row < piece.length; row++) {
			int newRow = piece[0].length - 1;
			int newColumn = row;
			for (int column = 0; column < piece[0].length; column++) {
				//newPiece[newRow][newColumn] = piece[row][column];
				
				Block pieceBlock = piece[row][column];
				
				if (pieceBlock != null) {
					// this is confusing.
					newPiece[newRow][newColumn] =  createBlock(pieceBlock, row, column, newRow, newColumn);
				}
				newRow--;
			}
		}
		
		piece = newPiece;
	}
	
	private Block createBlock(Block currBlock, int currPieceRow, int currPieceColumn, int rRow, int rColumn) {
		int blockCol = currBlock.getColumn();
		int blockRow = currBlock.getRow();
		
		int diffCol = blockCol - currPieceColumn;
		int diffRow = blockRow - currPieceRow;
		
		Block block = new Block(rRow + diffRow, rColumn + diffCol);
		
		return block;
	}
	
	private Block createBlockCopy(Block block) {
		int row = block.getRow();
		int column = block.getColumn();
		
		return new Block(row, column);
	}
	
	private void createPiece() {
		switch (pieceName) {
			case BOX_PIECE:
				piece = new Block[2][2];
				piece[0][0] = new Block(startingRow, startingColumn);
				piece[1][0] = new Block(startingRow + 1, startingColumn);
				piece[0][1] = new Block(startingRow, startingColumn + 1);
				piece[1][1] = new Block(startingRow + 1, startingColumn + 1);
				
				break;
			case L_PIECE:
				piece = new Block[3][2];
				// test
				piece[0][0] = new Block(startingRow, startingColumn);
				piece[1][0] = new Block(startingRow + 1, startingColumn);
				piece[2][0] = new Block(startingRow + 2, startingColumn);
				piece[2][1] = new Block(startingRow + 2, startingColumn + 1);
				break;
			case REV_L_PIECE:
				piece = new Block[3][2];
				// test
				piece[0][1] = new Block(startingRow, startingColumn + 1);
				piece[1][1] = new Block(startingRow + 1, startingColumn + 1);
				piece[2][1] = new Block(startingRow + 2, startingColumn + 1);
				piece[2][0] = new Block(startingRow + 2, startingColumn);
				break;
			case SINGLE_PIECE:
				piece = new Block[1][1];
				// since we have single piece, block size is one.
				piece[0][0] = new Block(startingRow, startingColumn);
				break;
			case STRAIGHT_PIECE:
				piece = new Block[4][1];
				
				piece[0][0] = new Block(startingRow, startingColumn);
				piece[1][0] = new Block(startingRow + 1, startingColumn);
				piece[2][0] = new Block(startingRow + 2, startingColumn);
				piece[3][0] = new Block(startingRow + 3, startingColumn);
				
				break;
			case S_PIECE:
				piece = new Block[3][2];
				
				piece[0][0] = new Block(startingRow, startingColumn);
				piece[1][0] = new Block(startingRow + 1, startingColumn);
				piece[1][1] = new Block(startingRow + 1, startingColumn + 1);
				piece[2][1] = new Block(startingRow + 2, startingColumn + 1);
				
				break;
			case REV_S_PIECE:
				piece = new Block[3][2];
				
				piece[0][1] = new Block(startingRow, startingColumn + 1);
				piece[1][1] = new Block(startingRow + 1, startingColumn + 1);
				piece[1][0] = new Block(startingRow + 1, startingColumn);
				piece[2][0] = new Block(startingRow + 2, startingColumn);
				
				break;
			case T_PIECE:
				piece = new Block[2][3];
				
				piece[0][1] = new Block(startingRow, startingColumn + 1);
				piece[1][0] = new Block(startingRow + 1, startingColumn + 0);
				piece[1][1] = new Block(startingRow + 1, startingColumn + 1);
				piece[1][2] = new Block(startingRow + 1, startingColumn + 2);
				
				break;
		}
	}
}
