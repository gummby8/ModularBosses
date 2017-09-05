package com.Splosions.ModularBosses.util.schematic;

public class Dungeon {
	public int originX = 0;
	public int originY = 0;
	public int originZ = 0;
	
	public int returnDimension;
	
	public int RoomPosX = 0;
	public int RoomPosY = 0;
	public int RoomPosZ = 0;
	
    public int roomWidth = 5;
    public int roomHeight = 2;
    public int roomLength = 5;
	
    public Boolean instantBuild = false;
    
	public int buildsPerTick = 10;
	public int buildCount = 0;
	public int roomCount = 0;
	
	public Boolean finishedBuilding = false;
	
	public String dungeonID;

	public Room[][] dungeonRooms;
	public int dgnRoomsLength = 0;
	public int dgnRoomsWidth = 0;
	
	public String fileName;

	public void nextRoom() {
		//System.out.println("Building New Room");
		 dgnRoomsLength = 0;
		 dgnRoomsWidth = 0;
		for(int c = 0; c < roomCount; c++) {
			 if (dgnRoomsLength >= dungeonRooms.length - 1 && dgnRoomsWidth >= dungeonRooms[dgnRoomsLength].length - 1){
				 dgnRoomsLength = 0;
				 dgnRoomsWidth = 0;
				 finishedBuilding = true;
            	 break;
             } else 
             if (dgnRoomsWidth >= dungeonRooms[dgnRoomsLength].length - 1){
            	 dgnRoomsLength++;	
            	 dgnRoomsWidth = 0;
             } else {
            	 dgnRoomsWidth++;
             }
		}
		 RoomPosX = originX + (roomWidth * dgnRoomsWidth);
		 RoomPosZ = originZ - (roomLength * dgnRoomsLength);
	}
}
