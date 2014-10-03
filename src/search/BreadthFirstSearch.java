package search;

import model.Node;
import model.Point;


public abstract class BreadthFirstSearch extends AStarWithEndPoint{

	public BreadthFirstSearch(Node startNode, Point endPoint) {
		super(startNode, endPoint);
		// TODO Auto-generated constructor stub
	}
	protected void setHeuristic(Node node) {
		node.heuristic = 0;
	}
}
