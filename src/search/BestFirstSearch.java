package search;

import model.NavNode;
import model.Node;
import model.Point;


public abstract class BestFirstSearch extends AStarWithEndPoint{

	public BestFirstSearch(Node startNode, Point endPoint) {
		super(startNode, endPoint);
	}
}
