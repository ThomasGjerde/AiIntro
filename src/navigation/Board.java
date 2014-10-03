package navigation;

import gui.BoardGraphics;

import java.io.*;
import java.util.ArrayList;

import utility.IOUtils;

import model.NavNode;
import model.Node;
import model.Node.Status;
import model.Point;

public class Board {
	public Point size = new Point();
	public Point startPos = new Point();
	public Point endPos = new Point();
	public int steps = 0;
	public NavNode[][] boardArray;
	public boolean complete = false;
	public ArrayList<NavNode> terrainList;
	
	public Board(String path) throws IOException{
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		fillTerrainList();
		ArrayList<NavNode> tempBoard = new ArrayList<NavNode>();
		for(int i = 0; i < input.size(); i++){
			tempBoard.addAll((parseLine(i,input.get(i))));
			size.y = i+1;
		}
		boardArray = new NavNode[size.x][size.y];
		for(int i = 0; i < tempBoard.size(); i++){
			NavNode tempNode = tempBoard.get(i);
			boardArray[tempNode.pos.x][tempNode.pos.y] = tempNode;
			if(tempNode.type.equals(NavNode.type_start)){
				startPos = tempNode.pos;
			}else if(tempNode.type.equals(NavNode.type_end)){
				endPos = tempNode.pos;
			}
		}
		setChildrenForAllNodes();
		/*
		String total = "";
		for(int i = 0; i < boardArray[0].length; i++){
			
			for(int j = 0; j < boardArray.length; j++){
				total += (boardArray[j][i].type);
				//System.out.println(boardArray[i][j].pos.x +" " + boardArray[i][j].pos.y);
			}
			total += "\n";
		}
		System.out.println(total);
		*/
		
	}
	private void fillTerrainList(){
		terrainList = new ArrayList<NavNode>();
		terrainList.add(new NavNode(NavNode.type_open,1));
		terrainList.add(new NavNode(NavNode.type_obstacle,1));
		terrainList.add(new NavNode(NavNode.type_start,0));
		terrainList.add(new NavNode(NavNode.type_end,0));
		terrainList.add(new NavNode(NavNode.type_water,100));
		terrainList.add(new NavNode(NavNode.type_mountain,50));
		terrainList.add(new NavNode(NavNode.type_forest,10));
		terrainList.add(new NavNode(NavNode.type_grass, 5));
		terrainList.add(new NavNode(NavNode.type_road,1));
	}
	private NavNode parseNode(int x, int y,String input){
			for(int i = 0; i < terrainList.size(); i++){
				if(input.equals(terrainList.get(i).type)){
					return new NavNode(new Point(x,y),terrainList.get(i).type,terrainList.get(i).cost);
				}
			}
			System.out.println("Unproccessed string: " + input);
			return null;
	}
	public ArrayList<NavNode> parseLine(int y, String input){
		//input = input.replace("\r", "");
		char tempArray[] = input.toCharArray();
		ArrayList<NavNode> returnArray = new ArrayList<NavNode>();
		for(int i = 0; i < tempArray.length; i++){
			System.out.println(tempArray[i]);
				returnArray.add(parseNode(i, y, Character.toString(tempArray[i])));
			
		}
		size.x = returnArray.size();
		return returnArray;
	}
	public boolean isEndNode(NavNode node){
		if(node.pos.x == endPos.x && node.pos.y == endPos.y){
			return true;
		}else{
			return false;
		}
	}
	public void resetBoard(){
		complete = false;
		steps = 0;
		for(int i = 0; i < size.x; i++){
			for(int j = 0; j < size.y; j++){
				Node currentNode = boardArray[i][j];
				if(currentNode.status != Status.Obstacle){
					currentNode.status = Status.Unvisited;
				}
			}
		}
	}
	private void setChildrenForAllNodes(){
		for(int i = 0; i < size.x; i++){
			for(int j = 0; j < size.y; j++){
				setChildren(boardArray[i][j]);
			}
		}
	}
	private void setChildren(NavNode node){
		if(node.pos.x > 0){
			node.addChild(boardArray[node.pos.x -1][node.pos.y]);
		}
		if(node.pos.y < size.y - 1){
			node.addChild(boardArray[node.pos.x][node.pos.y + 1]);
		}
		if(node.pos.x < size.x - 1){
			node.addChild(boardArray[node.pos.x + 1][node.pos.y]);
		}
		if(node.pos.y > 0){
			node.addChild(boardArray[node.pos.x][node.pos.y - 1]);
		}
	}
	private void setStartAndEnd(ArrayList<Integer> input){
		startPos.x = input.get(0);
		startPos.y = input.get(1);
		endPos.x = input.get(2);
		endPos.y = input.get(3);
	}
}
