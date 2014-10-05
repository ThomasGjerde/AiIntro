package slidingblock;

import java.io.IOException;
import java.util.ArrayList;

import model.Point;
import model.PuzzleNode;
import model.PuzzleStateNode;

import utility.IOUtils;

public class PuzzleBoard
{
	public PuzzleNode[][] boardArray;
	public Point size = new Point();
	public PuzzleBoard(String path) throws IOException{
		ArrayList<String> input = IOUtils.getInputFromFile(path);
		ArrayList<PuzzleNode> tempArray = new ArrayList<PuzzleNode>();
		for(int i = 0; i < input.size(); i++){
			tempArray.addAll(parseLine(i, input.get(i)));
			this.size.y = i +1;
		}
		boardArray = new PuzzleNode[this.size.x][this.size.y];
		for(int i = 0; i < tempArray.size(); i++){
			PuzzleNode tempNode = tempArray.get(i);
			boardArray[tempNode.pos.x][tempNode.pos.y] = tempNode;
		}
		
		for(int i = 0; i < boardArray.length; i++){
			for(int j = 0; j < boardArray[0].length; j++){
				System.out.print(boardArray[j][i].nodeValue); 
			}
			System.out.println();
		}
		
	}
	public ArrayList<PuzzleNode> parseLine(int y, String input){
		String tempArray[] = input.split(",");
		ArrayList<PuzzleNode> returnArray = new ArrayList<PuzzleNode>();
		for(int i = 0; i < tempArray.length; i++){
			returnArray.add(new PuzzleNode(new Point(i,y), Integer.parseInt(tempArray[i])));
		}
		this.size.x = returnArray.size();
		return returnArray;
	}
	public PuzzleStateNode generateInitialStateNode(){
		for(int i = 0; i < boardArray.length; i++){
			for(int j = 0; j < boardArray[0].length; j++){
				PuzzleNode tempNode = boardArray[j][i];
				if(tempNode.nodeValue == -1){
					return new PuzzleStateNode(boardArray, new Point(j , i) , null);
				}
			}
		}
		return null;
	}
}
