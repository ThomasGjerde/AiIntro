package gui;

import java.awt.Color;
import java.util.ArrayList;

import model.NavNode;
import model.Node;
import model.Point;
import navigation.Board;


public class BoardGraphics extends Graphics{

	Board board;
	ArrayList<Node> openList = new ArrayList<Node>();
	ArrayList<Node> closedList = new ArrayList<Node>();
	public BoardGraphics(Board board){
		super(board.size.x,board.size.y);
		//System.out.println("Board: " + board.size.x + " BoardY: " +board.size.y);
		this.board = board;
		
	}
	public void setLists(ArrayList<Node> openList, ArrayList<Node> closedList){
		this.openList = new ArrayList<Node>(openList);
		this.closedList = new ArrayList<Node>(closedList);
		grid.clearAllText();
	}
	public void setBoard(Board board){
		grid.clearColor(Color.RED); //Delete old tail
        for(int i = 0; i < board.size.x; i++)
        {
        	for(int j = board.size.y; j > 0; j--)
        	{
        		NavNode tempNode = board.boardArray[i][board.size.y -j];
        		if(board.boardArray[i][board.size.y - j].status == Node.Status.Obstacle){
        			grid.setCellColorWithoutRepaint(i, j -1, Color.BLACK);
        		}
        		else if(board.boardArray[i][board.size.y - j].status == Node.Status.Visiting){
        			grid.setCellColorWithoutRepaint(i, j -1, Color.RED);
        		}else if(board.boardArray[i][board.size.y - j].type.equals(NavNode.type_start)){
        			grid.setCellColorWithoutRepaint(i, j -1, Color.WHITE);
        		}else if(tempNode.type.equals(NavNode.type_forest)){
        			grid.setCellColorWithoutRepaint(i, j -1, new Color(51,153,0));
        		}else if(tempNode.type.equals(NavNode.type_grass)){
        			grid.setCellColorWithoutRepaint(i, j -1, Color.GREEN);
        		}else if(tempNode.type.equals(NavNode.type_mountain)){
        			grid.setCellColorWithoutRepaint(i, j -1, Color.GRAY);
        		}else if(tempNode.type.equals(NavNode.type_water)){
        			grid.setCellColorWithoutRepaint(i, j -1, Color.BLUE);
        		}else if(tempNode.type.equals(NavNode.type_road)){
        			grid.setCellColorWithoutRepaint(i, j -1, new Color(102, 51, 0));
        		}
        	}
        }
        for(int i = 0; i < openList.size(); i++){
        	grid.addText(new GridText(((NavNode)openList.get(i)).pos,"*"));
        }
        for(int i = 0; i < closedList.size(); i++){
        	grid.addText(new GridText(((NavNode)closedList.get(i)).pos, "x"));
        }
        grid.repaint();
	}
}
