package search;

import model.NavNode;
import model.Node;
import model.Point;



public abstract class AStarWithEndPoint extends AStar{

	protected Point endPoint = new Point();
	public AStarWithEndPoint(Node startNode, Point endPoint) {
		super(startNode);
		this.endPoint = endPoint;
	}

	@Override
	protected boolean checkVictory() {
		if(((NavNode)currentNode).pos.x == endPoint.x && ((NavNode)currentNode).pos.y == endPoint.y){
			return true;
		}else{
			return false;
		}
	}
}
