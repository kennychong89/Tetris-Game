package com.androidgame.model.tetrispiece_utilities;

import java.util.ArrayList;

import com.androidgame.model.tetrispiece.Block;

public class Blocks_Modifier {
	public static void update(Block [] blocks, int rowChange, int columnChange) {
		
		for (int blockIndex = 0; blockIndex < blocks.length; blockIndex++) {

			Block block = blocks[blockIndex];

			if (block != null) {
				int currentRow = block.getRow();
				int currentColumn = block.getColumn();

				block.setRow(currentRow + rowChange);
				block.setColumn(currentColumn + columnChange);
			}
		}
	}

	public static void rotateCounterClockWise(Block [] blocks) {
		/*
		 * Using matrix rotation for rotating piece:
		 * https://www.youtube.com/watch?v=Atlr5vvdchY
		 */
		
		// rotation matrix (counter clockwise)
		int[][] rotationMatrix = { { 0, -1 }, { 1, 0 } };

		// we need the pivot, assume that pivot is at (0,0) for now.
		Block pivotBlock = blocks[0];

		if (pivotBlock != null) {
			int[] pivotVector = { pivotBlock.getRow(), pivotBlock.getColumn() };

			performTransformation(blocks, pivotVector, rotationMatrix);
		}
	}
	
	public static void rotateClockWise(Block [] blocks) {
		
	}
	
	public static ArrayList<Integer> getRows(Block [] blocks) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (Block block : blocks) {
			int blockRow = block.getRow();
			list.add(blockRow);
		}
		
		return list;
	}
	
	public static ArrayList<Integer> getColumns(Block [] blocks) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (Block block : blocks) {
			int blockColumn = block.getColumn();
			list.add(blockColumn);
		}
		
		return list;
	}
	
	private static void performTransformation(Block[] blocks, int[] pivotVector,
			int[][] rotationMatrix) {
		// query for the other block rows and columns
		for (int index = 1; index < blocks.length; index++) {
			Block rotateBlock = blocks[index];

			if (rotateBlock != null) {

				int[] absolutePositionVector = { rotateBlock.getRow(),
						rotateBlock.getColumn() };

				int[] relativeVector = getRelativeVector(
						absolutePositionVector, pivotVector);

				int[] transformVector = getTransformationVector(relativeVector,
						rotationMatrix);

				// get the position vector
				int[] positionVector = getPositionVector(transformVector,
						pivotVector);

				// update blocks
				rotateBlock.setRow(positionVector[0]);
				rotateBlock.setColumn(positionVector[1]);
			}
		}
	}

	private static int[] getRelativeVector(int[] absolutePositionVector,
			int[] pivotVector) {
		// get the relative vector (absolute vector - pivot)
		int relativeRowVector = absolutePositionVector[0] - pivotVector[0];
		int relativeColumnVector = absolutePositionVector[1] - pivotVector[1];

		int[] relativeVector = { relativeRowVector, relativeColumnVector };

		return relativeVector;
	}

	private static int[] getTransformationVector(int[] relativeVector,
			int[][] rotationMatrix) {
		// perform dot product to get transform vector
		int transformRowVector = (rotationMatrix[0][0] * relativeVector[0])
				+ (rotationMatrix[0][1] * relativeVector[1]);
		int transformColumnVector = (rotationMatrix[1][0] * relativeVector[0])
				+ (rotationMatrix[1][1] * relativeVector[1]);

		int[] transVector = { transformRowVector, transformColumnVector };

		return transVector;
	}

	private static int[] getPositionVector(int[] transformVector, int[] pivot) {
		int positionRowVector = transformVector[0] + pivot[0];
		int positionColumnVector = transformVector[1] + pivot[1];

		int[] positionVector = { positionRowVector, positionColumnVector };

		return positionVector;
	}	
}
