package model;

import java.util.ArrayList;


public class NavNode extends Node{
	public Point pos = new Point();
	public static final String type_open = ".";
	public static final String type_obstacle = "#";
	public static final String type_start = "A";
	public static final String type_end = "B";
	public static final String type_water = "w";
	public static final String type_mountain = "m";
	public static final String type_forest = "f";
	public static final String type_grass = "g";
	public static final String type_road = "r";
	public String type;
	public int cost;
	
	public NavNode(Point position,String type, int cost) {
		super();
		this.pos = position;
		this.type = type;
		this.cost = cost;
		if(type.equals(type_obstacle)){
			this.status = Status.Obstacle;
		}else{
			this.status = Status.Unvisited;
		}
		// TODO Auto-generated constructor stub
	}
	public NavNode(String type, int cost){
		this.type = type;
		this.cost = cost;
	}
}
