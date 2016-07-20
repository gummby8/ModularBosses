package com.Splosions.ModularBosses.util.schematic;

public class Room {
	public boolean north;
	public boolean west;
	public boolean south;
	public boolean east;
	
	public int type;

	public static final int BLANK = 0;
	public static final int NORMAL = 1;
	public static final int PUZZLE = 2;
	public static final int LOOT = 3;
	public static final int BOSS = 4;
	
	public Room(){} 
	
	public Room(int type, boolean north, boolean west, boolean south, boolean east) {
		
		this.type = type;
		
		this.north = north;
		this.west = west;
		this.south = south;
		this.east = east;
	}
	
	

}
