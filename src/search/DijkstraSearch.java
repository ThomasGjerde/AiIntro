package search;

import model.NavNode;
import model.Node;
import model.Point;


public abstract class DijkstraSearch extends AStarWithEndPoint{

	public DijkstraSearch(Node startNode, Point endPoint) {
		super(startNode, endPoint);
	}
	protected void setHeuristic(Node node) {
		node.heuristic = node.getCostFromStart();
	}
}
