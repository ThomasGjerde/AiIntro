package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Node;
import model.Node.Status;

public abstract class AStar {
	protected ArrayList<Node> openList = new ArrayList<Node>();
	protected ArrayList<Node> closedList = new ArrayList<Node>();
	protected Node currentNode;
	protected int steps = 0;
	protected int pathLength = 0;
	protected int totalCost = 0;
	protected boolean victory = false;
	public AStar(Node startNode){
		this.currentNode = startNode;
		//System.out.println(currentNode.cost);
	}
	public void search(){
		setHeuristic(currentNode);
		closedList.add(currentNode);
		while(!victory){
			ArrayList<Node> children = currentNode.getUnoccupiedChildren();
			for(int i = 0; i < children.size(); i++){
				Node tempNode = children.get(i);
				if(!openList.contains(tempNode) && !closedList.contains(tempNode)){
					tempNode.parent = currentNode;
					setHeuristic(tempNode);
					addToOpenList(tempNode);
				}
			}
			//System.out.println("OPenLIst: " + openList.size());
			//System.out.println("CLosedList " + closedList.size());
			openList.remove(currentNode);
			closedList.add(currentNode);
			currentNode.setStatus(Status.Visited);
			currentNode = getBestOpenList();
			if(currentNode == null){
				System.out.println("No solution: Open list empty");
				return;
			}
			currentNode.setStatus(Status.Visiting);
			steps++;
			processCurrentNode();
			updateGui();
			victory = checkVictory();
			if(victory){
				System.out.println("victory");
				calculatePathLenght();
				System.out.println("PathLength: " + pathLength);
				System.out.println("Steps: " + steps);
				System.out.println("Path cost: " + totalCost);
				runVictory();
			}

		}
	}
	protected abstract boolean checkVictory();
	protected abstract void processCurrentNode();
	protected abstract void runVictory();
	protected void calculatePathLenght(){
		pathLength = 0;
		if(currentNode != null){
			Node tempNode = currentNode;
			while(tempNode != null){
				totalCost += tempNode.cost;
				tempNode = tempNode.parent;
				pathLength++;
			}
		}
	}
	protected void addToOpenList(Node node){
		openList.add(node);
		Collections.sort(openList, new Comparator<Node>(){
		     public int compare(Node o1, Node o2){
		    	 return o1.heuristic - o2.heuristic;
		     }
		});
	}
	protected Node getBestOpenList(){
		if(openList.isEmpty()){
			return null;
		}else{
			return openList.get(0);
		}
	}
	public int getSteps(){
		return steps;
	}
	public int getPathLength(){
		return pathLength;
	}
	protected abstract void setHeuristic(Node node);
	protected abstract void updateGui();
}
