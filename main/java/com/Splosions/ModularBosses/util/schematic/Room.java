package com.Splosions.ModularBosses.util.schematic;

import java.util.ArrayList;

import com.Splosions.ModularBosses.util.BlockObject;

public class Room {
	public int north;
	public int west;
	public int south;
	public int east;
	
	public ArrayList<BlockObject> secondPass = new ArrayList<BlockObject>();;
	
	public int type = 0;
	
	public String[] roomCode = {"W","W","W","W"}; 
	
	
	public Room(){} 
	
	public Room(int type, int north, int west, int south, int east) {
		
		this.type = type;
		
		this.north = north;
		this.west = west;
		this.south = south;
		this.east = east;
	}
	
	

}
