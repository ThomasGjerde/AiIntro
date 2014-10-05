package model;

public class PuzzleStateNode extends Node
{
	public PuzzleNode[][] board;
	public Point currentPosition;
	public Point previousPosition;
	public boolean invalid = false;
	public PuzzleStateNode(PuzzleNode[][] board,Point currentPosition, Direction change){
		this.currentPosition = new Point(currentPosition.x,currentPosition.y);
		this.board = new PuzzleNode[board.length][board[0].length];
		for(int i = 0; i < board[0].length; i++){
			for(int j = 0; j < board.length; j++){
				this.board[i][j] = new PuzzleNode(board[i][j].nodeValue);
			}
		}
		previousPosition = new Point(currentPosition.x, currentPosition.y);
		changeBoardState(change);
	}
	public void changeBoardState(Direction change){
		if(change == Direction.UP){
			if(currentPosition.y > 0){
				switchPlaces(new Point(currentPosition.x,currentPosition.y), new Point(currentPosition.x , currentPosition.y - 1));
				currentPosition.y -= 1;
			}else{
				invalid = true;
			}
		}else if(change == Direction.RIGHT){
			if(currentPosition.x < board[0].length - 1){
				switchPlaces(new Point(currentPosition.x,currentPosition.y), new Point(currentPosition.x + 1, currentPosition.y));
				currentPosition.x += 1;
			}else{
				invalid = true;
			}
		}else if(change == Direction.DOWN){
			if(currentPosition.y < board.length - 1){
				switchPlaces(new Point(currentPosition.x,currentPosition.y), new Point(currentPosition.x, currentPosition.y + 1));
				currentPosition.y += 1;
			}else{
				invalid = true;
			}
		}else if(change == Direction.LEFT){
			if(currentPosition.x > 0){
				switchPlaces(new Point(currentPosition.x,currentPosition.y), new Point(currentPosition.x - 1, currentPosition.y));
				currentPosition.x -= 1;
			}else{
				invalid = true;
			}
		}
	}
	public int getBlocksOutOfPlace(){
		int errors = 0;
		PuzzleStateNode tempNode = this;
		for(int i = 0; i < tempNode.board.length; i++){
			for(int j = 0; j < tempNode.board[0].length; j++){
				if(tempNode.board[j][i].nodeValue != ((tempNode.board[0].length)*i) + j + 1 && tempNode.board[j][i].nodeValue != -1){
					errors += 1;
				}
			}
		}
		return errors;
	}
	public void printNode(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				System.out.print(board[j][i].nodeValue); 
			}
			System.out.println();
		}
	}
	private void switchPlaces(Point node1, Point node2){
		PuzzleNode tempNode1 = board[node1.x][node1.y];
		board[node1.x][node1.y] = board[node2.x][node2.y];
		board[node2.x][node2.y] = tempNode1;
	}
	public enum Direction {
		UP,
		RIGHT,
		DOWN,
		LEFT
	}
}
