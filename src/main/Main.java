package main;
import gui.BoardGraphics;

import java.io.IOException;

import navigation.Board;
import navigation.GridBestFirstSearch;


public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			Board board = new Board("/home/board-2-1.txt");
			GridBestFirstSearch gbfs = new GridBestFirstSearch(board.boardArray[board.startPos.x][board.startPos.y],board.endPos, board);
			gbfs.search();
			//BoardGraphics graphics = new BoardGraphics(board);
			//graphics.setBoard(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
