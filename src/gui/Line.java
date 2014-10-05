package gui;

import model.Point;


public class Line {
	public Point startPoint = new Point();
	public Point endPoint = new Point();
	public Line(Point startPoint, Point endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
}
