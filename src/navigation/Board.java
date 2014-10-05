package navigation;
import java.io.*;
import java.util.ArrayList;

import utility.IOUtils;

import model.Node;
import model.TerrainNode;
import model.Node.Status;
import model.Point;

public class Board {
	public Point size = new Point();
	public Point startPos = new Point();
	public Point endPos = new Point();
	public int steps = 0;
	public TerrainNode[][] boardArray;
	public boolean complete = false;
	public ArrayList<TerrainNode> terrainList;
	
	public Board(String path) throws IOException{
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		fillTerrainList();
		ArrayList<TerrainNode> tempBoard = new ArrayList<TerrainNode>();
		for(int i = 0; i < input.size(); i++){
			tempBoard.addAll((parseLine(i,input.get(i))));
			size.y = i+1;
		}
		boardArray = new TerrainNode[size.x][size.y];
		for(int i = 0; i < tempBoard.size(); i++){
			TerrainNode tempNode = tempBoard.get(i);
			boardArray[tempNode.pos.x][tempNode.pos.y] = tempNode;
			if(tempNode.type.equals(TerrainNode.type_start)){
				startPos = tempNode.pos;
			}else if(tempNode.type.equals(TerrainNode.type_end)){
				endPos = tempNode.pos;
			}
		}
		setChildrenForAllNodes();
	}
	private void fillTerrainList(){
		terrainList = new ArrayList<TerrainNode>();
		terrainList.add(new TerrainNode(TerrainNode.type_open,1));
		terrainList.add(new TerrainNode(TerrainNode.type_obstacle,1));
		terrainList.add(new TerrainNode(TerrainNode.type_start,0));
		terrainList.add(new TerrainNode(TerrainNode.type_end,0));
		terrainList.add(new TerrainNode(TerrainNode.type_water,100));
		terrainList.add(new TerrainNode(TerrainNode.type_mountain,50));
		terrainList.add(new TerrainNode(TerrainNode.type_forest,10));
		terrainList.add(new TerrainNode(TerrainNode.type_grass, 5));
		terrainList.add(new TerrainNode(TerrainNode.type_road,1));
	}
	private TerrainNode parseNode(int x, int y,String input){
			for(int i = 0; i < terrainList.size(); i++){
				if(input.equals(terrainList.get(i).type)){
					return new TerrainNode(new Point(x,y),terrainList.get(i).type,terrainList.get(i).cost);
				}
			}
			return null;
	}
	public ArrayList<TerrainNode> parseLine(int y, String input){
		char tempArray[] = input.toCharArray();
		ArrayList<TerrainNode> returnArray = new ArrayList<TerrainNode>();
		for(int i = 0; i < tempArray.length; i++){
				returnArray.add(parseNode(i, y, Character.toString(tempArray[i])));
		}
		size.x = returnArray.size();
		return returnArray;
	}
	public boolean isEndNode(TerrainNode node){
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
	private void setChildren(TerrainNode node){
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
}
