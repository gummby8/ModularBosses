package com.Splosions.ModularBosses.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.Splosions.ModularBosses.util.TargetUtils;
import com.Splosions.ModularBosses.util.schematic.Room;
import com.Splosions.ModularBosses.util.schematic.Schematic;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityCartographer extends Entity {

	public static final int DUNGEON = 0;
	public static final int WORM = 1;
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

	private int cartType;

	static ArrayList<String[]> dataArr;
	int mapLine = 0;
	public int mapRoom = 0;

	public int roomWidth = 5;

	int roomLength = 5;
	public int schemTick = 0;
	public int schemTickInterval = 20;

	public EntityCartographer(World worldIn) {
		super(worldIn);
	}

	public EntityCartographer(World worldIn, Entity shooter, int type, double x, double y, double z) {
		super(worldIn);
		this.setSize(1F, 1F);
		this.setPosition(x, y, z);
		this.noClip = true;
		this.cartType = type;

	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		if (this.cartType == DUNGEON) {
			dungeonGen();
		} else if (this.cartType == WORM) {
			wormGen();
		}

	}

	public void wormGen() {
		if (roomArray == null) {
			roomArray = new Room[7][3]; // 3 Across 7 tall
			for (int y = 0; y < roomArray.length; y++) {
				for (int x = 0; x < roomArray[y].length; x++) {
					roomArray[y][x] = new Room();
				}
			}
		}

		roomArray[0][0].type = ENTRANCE;
		roomArray[0][0].east = DOOR;
		roomArray[0][0].west = roomArray[0][0].north = roomArray[0][0].south = WALL;
		roomArray[0][0].roomCode[0] = roomArray[0][0].roomCode[2] = roomArray[0][0].roomCode[3] = "W"

		roomArray[0][0].type = BOSS;
		roomArray[6][2].west = DOOR;
		roomArray[6][2].east = roomArray[6][2].south = roomArray[6][2].north = WALL;
		roomArray[6][2].roomCode[0] = roomArray[0][0].roomCode[1] = roomArray[0][0].roomCode[2] = "W"
		
		roomGen = false;
		while (!roomGen) {
			System.out.println("Making Doors");
			roomGen = true;
		
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
					System.out.println("Room (" + y + " - " + x + ") = North Wall");
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
					System.out.println("Room (" + y + " - " + x + ") = East Wall");
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
					System.out.println("Room (" + y + " - " + x + ") = South Wall");
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
					System.out.println("Room (" + y + " - " + x + ") = West Wall");
				}
			}
		}


			for (int y = 0; y < roomArray.length; y++) {
				for (int x = 0; x < roomArray[y].length; x++) {

					
					if (roomArray[y][x].north == DOOR || roomArray[y][x].east == DOOR || roomArray[y][x].south == DOOR || roomArray[y][x].west == DOOR) {
						if (roomArray[y][x].north == OPEN || roomArray[y][x].east == OPEN || roomArray[y][x].south == OPEN || roomArray[y][x].west == OPEN) {
							roomGen = false;
							this.doorCount = 0;
							while (this.doorCount < 2) {
								this.doorCount = 0;
								if (roomArray[y][x].north == OPEN && TargetUtils.getRanNum(1, 100) <= 25) {
									roomArray[y][x].north = DOOR;
									System.out.println("Made a door at north " + y + " - " + x);
								}

								if (roomArray[y][x].east == OPEN && TargetUtils.getRanNum(1, 100) <= 25) {
									roomArray[y][x].east = DOOR;
									System.out.println("Made a door at east " + y + " - " + x);
								}

								if (roomArray[y][x].south == OPEN && TargetUtils.getRanNum(1, 100) <= 25) {
									roomArray[y][x].south = DOOR;
									System.out.println("Made a door at south " + y + " - " + x);
								}

								if (roomArray[y][x].west == OPEN && TargetUtils.getRanNum(1, 100) <= 25) {
									roomArray[y][x].west = DOOR;
									System.out.println("Made a door at west " + y + " - " + x);
								}
								
								if (roomArray[y][x].north == DOOR){this.doorCount++;roomArray[y][x].roomCode[0] = "D";}
								if (roomArray[y][x].east == DOOR){this.doorCount++;roomArray[y][x].roomCode[1] = "D";}
								if (roomArray[y][x].south == DOOR){this.doorCount++;roomArray[y][x].roomCode[2] = "D";}
								if (roomArray[y][x].west == DOOR){this.doorCount++;roomArray[y][x].roomCode[3] = "D";}
								
								System.out.println("Doorcount = " + this.doorCount);
							}
							System.out.println("Room (" + y + " - " + x + ") = " + roomArray[y][x].roomCode[0] + roomArray[y][x].roomCode[1] + roomArray[y][x].roomCode[2] + roomArray[y][x].roomCode[3]);
							System.out.println("Room (" + y + " - " + x + ") = " + roomArray[y][x].north + " " + roomArray[y][x].east + " " + roomArray[y][x].south + " " + roomArray[y][x].west); 
									
							if (roomArray[y][x].north == OPEN) {
								roomArray[y][x].north = WALL;
								roomArray[y][x].roomCode[0] = "W";
								System.out.println("Made a wall at north " + y + " - " + x);
							}
							if (roomArray[y][x].east == OPEN) {
								roomArray[y][x].east = WALL;
								roomArray[y][x].roomCode[1] = "W";
								System.out.println("Made a wall at east " + y + " - " + x);
							}
							if (roomArray[y][x].south == OPEN) {
								roomArray[y][x].south = WALL;
								roomArray[y][x].roomCode[2] = "W";
								System.out.println("Made a wall at south " + y + " - " + x);
							}
							if (roomArray[y][x].west == OPEN) {
								roomArray[y][x].west = WALL;
								roomArray[y][x].roomCode[3] = "W";
								System.out.println("Made a wall at west " + y + " - " + x);
							}
							
						}
					}
					System.out.println("Room (" + y + " - " + x + ") = " + roomArray[y][x].roomCode[0] + roomArray[y][x].roomCode[1] + roomArray[y][x].roomCode[2] + roomArray[y][x].roomCode[3]);
				}
			}
		}

		// String roomPath = "./schematics/Worm/blank.schematic";
		// Schematic.quickBuild(roomPath, this.worldObj, this, this.posX,
		// this.posY, this.posZ);
		// this.posX += 6;

		// this.posZ += 6;
		// this.posX -= 18;

		setDead();
	}

	public void dungeonGen() {

		schemTickInterval = 100;
		if (this.ticksExisted == 1) {
			try {
				readData("./schematics/derp.csv");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Places a room every 0.5 seconds this.ticksExisted % 2 == (2 - 1) &&
		if (!this.worldObj.isRemote && this.mapLine <= 31) {

			// System.out.println(dataArr.get(this.mapLine)[this.mapRoom]);

			String roomCodeRaw = dataArr.get(this.mapLine)[this.mapRoom];
			String[] roomCodeArray = roomCodeRaw.split("\\|");

			// System.out.println(roomCodeArray[0]);

			String roomPath = null;

			if (roomCodeArray[0].contains("1")) {
				roomPath = "wall";

			} else

			if (roomCodeArray[0].contains("0")) {
				roomPath = "blank";
			} else {

				roomPath = roomCodeArray[0] + "/" + roomCodeArray[1] + "/1";

			}

			roomPath = "./schematics/Dungeon Schematics/" + roomPath + ".schematic";
			// System.out.println(roomPath);

			Schematic.build(roomPath, this.worldObj, this, this.posX, this.posY, this.posZ);

			if (mapRoom > 31) {
				mapRoom = 0;
				this.setPositionAndUpdate(this.posX - (this.roomWidth * 32), this.posY, this.posZ + this.roomLength);
				mapLine++;
			}

			if (this.mapLine > 31) {
				setDead();

			}

		}

		// new Schematic("./schematics/2.schematic", this.worldObj, this.posX,
		// this.posY, this.posZ);

	}

	public static void readData(String filePath) throws IOException {
		BufferedReader dataBR = new BufferedReader(new FileReader(new File(filePath)));
		String line = "";

		dataArr = new ArrayList<String[]>(); // An ArrayList is used because I
												// don't know how many records
												// are in the file.

		while ((line = dataBR.readLine()) != null) { // Read a single line from
														// the file until there
														// are no more lines to
														// read

			String[] club = new String[32]; // Each club has 3 fields, so we
											// need room for the 3 tokens.

			for (int i = 0; i < 32; i++) { // For each token in the line that
											// we've read:
				String[] value = line.split(",", 32);
				club[i] = value[i]; // Place the token into the 'i'th "column"
			}
			dataArr.add(club); // Add the "club" info to the list of clubs.
		}
		dataBR.close();
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub

	}

}