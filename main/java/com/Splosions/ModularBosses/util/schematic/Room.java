package com.Splosions.ModularBosses.util.schematic;

public class Room {
	public int north;
	public int west;
	public int south;
	public int east;
	
	public int type = 0;
	

	
	
	public Room(){} 
	
	public Room(int type, int north, int west, int south, int east) {
		
		this.type = type;
		
		this.north = north;
		this.west = west;
		this.south = south;
		this.east = east;
	}
	
	

}
