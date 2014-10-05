package slidingblock;

import java.util.ArrayList;

import gui.PuzzleGraphics;
import model.Node;
import model.Point;
import model.PuzzleNode;
import model.PuzzleStateNode;
import model.PuzzleStateNode.Direction;
import search.AStar;

public class SlidingBlockAStar extends AStar
{
	PuzzleGraphics graphics;
	ArrayList<PuzzleNode[][]> expandedNodes;
	public SlidingBlockAStar(Node startNode)
	{
		super(startNode);
		expandedNodes = new ArrayList<PuzzleNode[][]>();
		expandNode((PuzzleStateNode)startNode);
		graphics = new PuzzleGraphics(((PuzzleStateNode)startNode).board);
		//graphics.setBoard(((PuzzleStateNode)startNode).board);
	}

	@Override
	protected boolean checkVictory()
	{
		PuzzleStateNode tempNode = (PuzzleStateNode)currentNode;
		if(tempNode.getBlocksOutOfPlace() > 0){
			return false;
		}
		return true;
	}

	@Override
	protected void processCurrentNode()
	{
		PuzzleStateNode tempNode = (PuzzleStateNode)currentNode;
		expandNode(tempNode);
	}
	private void expandNode(PuzzleStateNode node){
		for(Direction direction : Direction.values()){
			PuzzleStateNode newNode = new PuzzleStateNode(node.board,new Point(node.currentPosition.x, node.currentPosition.y),direction);
			if(!newNode.invalid){
				for(int i = 0; i < expandedNodes.size(); i++){
					if(equalBoards(newNode.board, expandedNodes.get(i))){
						return;
					}
				}
				node.addChild(newNode);
				expandedNodes.add(newNode.board);
			}
		}
	}
	private boolean equalBoards(PuzzleNode[][] board1, PuzzleNode[][] board2){
		for(int i = 0; i < board1[0].length; i++){
			for(int j = 0; j < board1.length; j++){
				if(board1[i][j].nodeValue != board2[i][j].nodeValue){
					return false;
				}
			}
		}
		return false; //Deactivated check
	}

	@Override
	protected void setHeuristic(Node node)
	{
		node.heuristic = ((PuzzleStateNode)node).getBlocksOutOfPlace() + ((PuzzleStateNode)node).getCostFromStart();
		System.out.println("H: " + node.heuristic);
	}

	@Override
	protected void updateGui()
	{
		try
		{
			Thread.sleep(10);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PuzzleStateNode tempNode = (PuzzleStateNode)currentNode;
		graphics.setBoard(tempNode.board);
	}

	@Override
	protected void runVictory()
	{
		System.out.println("Correct path found. Rerunning path in slow motion");
		try
		{
			Thread.sleep(2000);
		} catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<PuzzleStateNode> correctPath = new ArrayList<PuzzleStateNode>();
		if(currentNode != null){
			PuzzleStateNode tempNode = (PuzzleStateNode)currentNode;
			while(tempNode != null){
				correctPath.add(tempNode);
				tempNode = (PuzzleStateNode) tempNode.parent;
			}
			for(int i = correctPath.size()-1; i >= 0; i--){
				graphics.setBoard(correctPath.get(i).board);
				/*
				if(correctPath.get(i).previousPosition != null){
					graphics.markCell(correctPath.get(i).previousPosition);
				}
				*/
				try
				{
					Thread.sleep(500);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
