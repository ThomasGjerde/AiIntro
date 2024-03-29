package navigation;

import search.BreadthFirstSearch;
import model.Node;
import model.Node.Status;
import model.Point;
import gui.BoardGraphics;


public class GridBreadthFirstSearch extends BreadthFirstSearch{
	Board board;
	BoardGraphics graphics;
	public GridBreadthFirstSearch(Node startNode, Point endPoint, Board board) {
		super(startNode, endPoint);
		this.board = board;
		graphics = new BoardGraphics(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateGui() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < board.size.x; i++){
			for(int j = 0; j < board.size.y; j++){
				if(board.boardArray[i][j].status == Status.Visiting){
					board.boardArray[i][j].status = Status.Visited;
				}
			}
		}
		Node r2 = currentNode;
		while(r2.parent != null)
		{
			r2.setStatus(Status.Visiting);
			r2 = r2.parent;
		}
		graphics.setBoard(board);
	}

	@Override
	protected void processCurrentNode() {
		
	}

	@Override
	protected void runVictory()
	{
		// TODO Auto-generated method stub
		
	}

}
