package main;
import gui.BoardGraphics;
import gui.PuzzleGraphics;

import java.io.IOException;

import slidingblock.PuzzleBoard;
import slidingblock.SlidingBlockAStar;

import model.PuzzleStateNode;
import model.PuzzleStateNode.Direction;
import navigation.Board;
import navigation.GridBestFirstSearch;


public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		try
		{
			PuzzleBoard pb = new PuzzleBoard("boards/puzzleBoard.txt");
			PuzzleStateNode psn = pb.generateInitialStateNode();
			SlidingBlockAStar sbas = new SlidingBlockAStar(psn);
			sbas.search();
			//PuzzleStateNode newState = new PuzzleStateNode(psn.board, psn.currentPosition, Direction.UP);
			//newState.printNode();
			//PuzzleGraphics pg = new PuzzleGraphics(newState.board);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			Board board = new Board("boards/board-1-1.txt");
			GridBestFirstSearch gbfs = new GridBestFirstSearch(board.boardArray[board.startPos.x][board.startPos.y],board.endPos, board);
			gbfs.search();
			//BoardGraphics graphics = new BoardGraphics(board);
			//graphics.setBoard(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
	}

}
