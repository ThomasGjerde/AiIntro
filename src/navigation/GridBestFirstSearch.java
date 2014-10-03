package navigation;

import gui.BoardGraphics;
import model.Node;
import model.Node.Status;
import model.Point;
import search.BestFirstSearch;


public class GridBestFirstSearch extends BestFirstSearch{
	Board board;
	BoardGraphics graphics;
	public GridBestFirstSearch(Node startNode, Point endPoint, Board board) {
		super(startNode, endPoint);
		this.board = board;
		graphics = new BoardGraphics(board);
	}

	@Override
	protected void updateGui() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("GUI");
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
		graphics.setLists(this.openList, this.closedList);
		graphics.setBoard(board);
	}

	@Override
	protected void processCurrentNode() {
		
	}

}
