package com.Splosions.ModularBosses.util.schematic;

import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.util.BlockPos;
import net.minecraftforge.common.ForgeChunkManager;

public class DungeonNurkach extends Dungeon {

	public Room[][] roomArray;

	public boolean roomGen;

	public int doorCount;

	public static final int OPEN = 0;
	public static final int DOOR = 1;
	public static final int WALL = 2;

	public static final int NORMAL = 0;
	public static final int PUZZLE = 1;
	public static final int LOOT = 2;
	public static final int BOSS = 3;
	public static final int ENTRANCE = 4;

	public DungeonNurkach(BlockPos pos) {
		originX = RoomPosX = pos.getX();
		originY = RoomPosY = pos.getY() - 2;
		originZ = RoomPosZ = pos.getZ();
		wormGen();
	}

	
	
	public void wormGen() {
		if (roomArray == null) {
			newRoomArray();
		}

		roomGen = false;
		while (!roomGen) {
			//System.out.println("Making Doors");
			roomGen = true;

			roomArray[0][0].type = ENTRANCE;
			roomArray[0][0].east = DOOR;
			roomArray[0][0].west = roomArray[0][0].north = roomArray[0][0].south = WALL;
			roomArray[0][0].roomCode[0] = roomArray[0][0].roomCode[2] = roomArray[0][0].roomCode[3] = "W";

			roomArray[0][0].type = BOSS;
			roomArray[6][2].west = DOOR;
			roomArray[6][2].east = roomArray[6][2].south = roomArray[6][2].north = WALL;
			roomArray[6][2].roomCode[0] = roomArray[0][0].roomCode[1] = roomArray[0][0].roomCode[2] = "W";

			for (int y = 0; y < roomArray.length; y++) {
				for (int x = 0; x < roomArray[y].length; x++) {

					// North
					try {
						if (roomArray[y + 1][x].south == DOOR) {
							roomArray[y][x].north = DOOR;
							roomArray[y][x].roomCode[0] = "D";
						}
					} catch (Throwable e) {
						roomArray[y][x].north = WALL;
						roomArray[y][x].roomCode[0] = "W";
						// System.out.println("Room (" + y + " - " + x + ") =
						// North Wall");
					}

					// East
					try {
						if (roomArray[y][x + 1].west == DOOR) {
							roomArray[y][x].east = DOOR;
							roomArray[y][x].roomCode[1] = "D";
						}
					} catch (Throwable e) {
						roomArray[y][x].east = WALL;
						roomArray[y][x].roomCode[1] = "W";
						// System.out.println("Room (" + y + " - " + x + ") =
						// East Wall");
					}

					// South
					try {
						if (roomArray[y - 1][x].north == DOOR) {
							roomArray[y][x].south = DOOR;
							roomArray[y][x].roomCode[2] = "D";
						}
					} catch (Throwable e) {
						roomArray[y][x].south = WALL;
						roomArray[y][x].roomCode[2] = "W";
						// System.out.println("Room (" + y + " - " + x + ") =
						// South Wall");
					}

					// West
					try {
						if (roomArray[y][x - 1].east == DOOR) {
							roomArray[y][x].west = DOOR;
							roomArray[y][x].roomCode[3] = "D";
						}
					} catch (Throwable e) {
						roomArray[y][x].west = WALL;
						roomArray[y][x].roomCode[3] = "W";
						// System.out.println("Room (" + y + " - " + x + ") =
						// West Wall");
					}
				}
			}

			for (int y = 0; y < roomArray.length; y++) {
				for (int x = 0; x < roomArray[y].length; x++) {

					if (roomArray[y][x].north == DOOR || roomArray[y][x].east == DOOR || roomArray[y][x].south == DOOR
							|| roomArray[y][x].west == DOOR) {
						if (roomArray[y][x].north == OPEN || roomArray[y][x].east == OPEN
								|| roomArray[y][x].south == OPEN || roomArray[y][x].west == OPEN) {
							roomGen = false;
							this.doorCount = 0;
							while (this.doorCount < 2) {
								this.doorCount = 0;
								if (roomArray[y][x].north == OPEN && TargetUtils.getRanNum(1, 100) <= 11) {
									roomArray[y][x].north = DOOR;
								}

								if (roomArray[y][x].east == OPEN && TargetUtils.getRanNum(1, 100) <= 11) {
									roomArray[y][x].east = DOOR;
								}

								if (roomArray[y][x].south == OPEN && TargetUtils.getRanNum(1, 100) <= 11) {
									roomArray[y][x].south = DOOR;
								}

								if (roomArray[y][x].west == OPEN && TargetUtils.getRanNum(1, 100) <= 11) {
									roomArray[y][x].west = DOOR;
								}

								if (roomArray[y][x].north == DOOR) {
									this.doorCount++;
									roomArray[y][x].roomCode[0] = "D";
								}
								if (roomArray[y][x].east == DOOR) {
									this.doorCount++;
									roomArray[y][x].roomCode[1] = "D";
								}
								if (roomArray[y][x].south == DOOR) {
									this.doorCount++;
									roomArray[y][x].roomCode[2] = "D";
								}
								if (roomArray[y][x].west == DOOR) {
									this.doorCount++;
									roomArray[y][x].roomCode[3] = "D";
								}
							}

							if (roomArray[y][x].north == OPEN) {
								roomArray[y][x].north = WALL;
								roomArray[y][x].roomCode[0] = "W";
							}
							if (roomArray[y][x].east == OPEN) {
								roomArray[y][x].east = WALL;
								roomArray[y][x].roomCode[1] = "W";
							}
							if (roomArray[y][x].south == OPEN) {
								roomArray[y][x].south = WALL;
								roomArray[y][x].roomCode[2] = "W";
							}
							if (roomArray[y][x].west == OPEN) {
								roomArray[y][x].west = WALL;
								roomArray[y][x].roomCode[3] = "W";
							}

						}
					}
				}
			}
			roomArray[0][0].roomCode[0] = "W";
			roomArray[0][0].roomCode[1] = "D";
			roomArray[0][0].roomCode[2] = "W";
			roomArray[0][0].roomCode[3] = "W";

			roomArray[1][0].roomCode[2] = "W";
			roomArray[5][2].roomCode[0] = "W";

			roomArray[6][2].roomCode[0] = "W";
			roomArray[6][2].roomCode[1] = "W";
			roomArray[6][2].roomCode[2] = "W";
			roomArray[6][2].roomCode[3] = "D";

			// Path Check
			if (roomGen) {
				if (roomArray[0][0].roomCode[0] == "W" && roomArray[0][1].roomCode[0] == "W" && roomArray[0][2].roomCode[0] == "W") {
					roomGen = false;
					newRoomArray();
					System.out.println("Level 0 Failed North");
				} else if (roomArray[1][0].roomCode[0] == "W" && roomArray[1][1].roomCode[0] == "W"	&& roomArray[1][2].roomCode[0] == "W") {
					roomGen = false;
					newRoomArray();
					System.out.println("Level 1 Failed North");
				} else if (roomArray[2][0].roomCode[0] == "W" && roomArray[2][1].roomCode[0] == "W"	&& roomArray[2][2].roomCode[0] == "W") {
					roomGen = false;
					newRoomArray();
					System.out.println("Level 2 Failed North");
				} else if (roomArray[3][0].roomCode[0] == "W" && roomArray[3][1].roomCode[0] == "W"	&& roomArray[3][2].roomCode[0] == "W") {
					roomGen = false;
					newRoomArray();
					System.out.println("Level 3 Failed North");
				} else if (roomArray[4][0].roomCode[0] == "W" && roomArray[4][1].roomCode[0] == "W"	&& roomArray[4][2].roomCode[0] == "W") {
					roomGen = false;
					newRoomArray();
					System.out.println("Level 4 Failed North");
				} else if (roomArray[5][0].roomCode[0] == "W" && roomArray[5][1].roomCode[0] == "W" && roomArray[5][2].roomCode[0] == "W") {
					roomGen = false;
					newRoomArray();
					System.out.println("Level 5 Failed North");
				}
			}
		}
		
		dungeonRooms = roomArray;
	}

	
	public void newRoomArray() {
		roomArray = null;
		roomArray = new Room[7][3]; // 3 Across 7 tall
		for (int y = 0; y < roomArray.length; y++) {
			for (int x = 0; x < roomArray[y].length; x++) {
				roomArray[y][x] = new Room();
			}
		}
	}
}
