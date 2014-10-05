package model;

public class PuzzleNode extends NavNode
{
	public int nodeValue;
	public PuzzleNode(Point pos, int nodeValue){
		super(pos);
		this.nodeValue = nodeValue;
	}
	public PuzzleNode(int nodeValue){
		this.nodeValue = nodeValue;
	}
}
