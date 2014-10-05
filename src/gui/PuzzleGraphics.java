package gui;

import java.awt.Color;
import java.util.ArrayList;

import model.Point;
import model.PuzzleNode;

public class PuzzleGraphics extends Graphics
{
	PuzzleNode[][] boardArray;
	ArrayList<GridText> extraText = new ArrayList<GridText>();
	public PuzzleGraphics(PuzzleNode[][] board)
	{
		super(board.length,board[0].length);
		grid.setScale(40);
		this.boardArray = board;
		setBoard(board);
	}
	public void setBoard(PuzzleNode[][] board){
		grid.clearAllText();
        for(int i = 0; i < board.length; i++){
        	for(int j = 0; j < board[0].length; j++){
        		if(board[i][j].nodeValue != -1){
        			grid.addText(new GridText(new Point(i,j), Integer.toString(board[i][j].nodeValue)));
        		}
        	}
        }
        for(int i = 0; i < extraText.size(); i++){
        	grid.addText(extraText.get(i));
        }
        grid.repaint();
	}
	public void markCell(Point pos){
		grid.clearColor(Color.RED);
		grid.setCellColorWithoutRepaint(pos.x, pos.y, Color.RED);
		grid.repaint();
	}
	public void addText(GridText text){
		text.position.y = boardArray[0].length + extraText.size();
		extraText.add(text);
	}

}
