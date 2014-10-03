package search;

import model.Node;
import model.Point;


public abstract class DepthFirstSearch extends AStarWithEndPoint{
	int currentHeuristic =  0;
	public DepthFirstSearch(Node startNode, Point endPoint) {
		super(startNode, endPoint);
		// TODO Auto-generated constructor stub
	}
	protected void setHeuristic(Node node) {
		currentHeuristic--;
		node.heuristic = currentHeuristic;
	}
}
