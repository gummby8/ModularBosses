package com.Splosions.ModularBosses.util.schematic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityReturnPortalBlock;
import com.Splosions.ModularBosses.util.BlockObject;
import com.Splosions.ModularBosses.util.NBTHelper;
import com.google.common.primitives.UnsignedBytes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.BlockFluidBase;


public class Schematic {

	private static short width;
	private static short height;
	private static short length;
	private static int size;
	private static int counter = 0;
	private static int buildHeight = 0;
	private static int buildLength = 0;
	private static int buildWidth = 0;
	private static BlockObject[] blockObjects;
	private static File file;
	private static String fileName;
	private static Room room;
	private static String roomPath;
	private static NBTTagCompound nbtdata;


	public static void staggeredBuild(World world, Dungeon dungeon) {
		try {

			int x = dungeon.RoomPosX;
			int y = dungeon.RoomPosY;
			int z = dungeon.RoomPosZ;

			room = dungeon.dungeonRooms[dungeon.dgnRoomsLength][dungeon.dgnRoomsWidth];
			roomPath = room.roomCode[0] + room.roomCode[1] + room.roomCode[2] + room.roomCode[3];

			//skips rooms with no doors
			if (roomPath.equals("WWWW")){
				dungeon.buildCount = 0;
				buildHeight = 0;
				buildLength = 0;
				buildWidth = 0;
				dungeon.roomCount++;
				dungeon.nextRoom();
				return;
			}
			
			
			fileName = "./schematics/Worm/" + roomPath + "/1.schematic";
			file = new File(fileName);

			if (file.exists()) {
				nbtdata = SchematicUtil.readTagCompoundFromFile(file);
			} else {
				//TargetUtils.tellPlayer("No Schematics found in schematics folder");
				//TargetUtils.tellPlayer("Reverting to built-in starter schematics");
				//TargetUtils.tellPlayer("Please review readme file");
				fileName = "/assets/mb/StarterSchematics/Worm/" + roomPath + "/1.schematic";
				nbtdata = CompressedStreamTools.readCompressed(ModularBosses.INSTANCE.getClass().getResourceAsStream(fileName));
			}

			width = nbtdata.getShort("Width");
			height = nbtdata.getShort("Height");
			length = nbtdata.getShort("Length");
			ItemStack icon = SchematicUtil.getIconFromNBT(nbtdata);

			size = width * height * length;
			blockObjects = new BlockObject[size];

			byte[] blockIDs = nbtdata.getByteArray("Blocks");
			byte[] metadata = nbtdata.getByteArray("Data");

			// testing schematica schematics
			Short id = null;
			final Map<Short, Short> oldToNew = new HashMap<Short, Short>();
			if (nbtdata.hasKey("SchematicaMapping")) {
				final NBTTagCompound mapping = nbtdata.getCompoundTag("SchematicaMapping");
				final Set<String> names = mapping.getKeySet();
				for (final String name : names) {
					oldToNew.put(mapping.getShort(name), (short) Block.REGISTRY.getIDForObject(Block.REGISTRY.getObject(new ResourceLocation(name))));
				}
			}

			buildHeight = 0;
			buildLength = 0;
			buildWidth = 0;
			for (int q = 0; q < dungeon.buildCount; q++) {
				buildWidth++;
				if (buildWidth >= width) {
					buildLength++;
					buildWidth = 0;
				}
				if (buildLength >= length) {
					buildHeight++;
					buildLength = 0;
				}
				if (buildHeight >= height) {
					break;
				}

			}


			for (int q = 0; q < dungeon.buildsPerTick; q++) {
				int blockId = UnsignedBytes.toInt(blockIDs[dungeon.buildCount]);
				// Checks the id reference Map
				if ((id = oldToNew.get((short) blockId)) != null) {
					blockId = id;
				}
				
				Block block = Block.getBlockById(blockId);
				
				//stores blockstates in second pass for placement after first pass
				if (block == Blocks.REDSTONE_TORCH || block == Blocks.UNLIT_REDSTONE_TORCH || block instanceof BlockFluidBase || block instanceof BlockLiquid){
					IBlockState state = Block.getBlockById(blockId).getStateFromMeta(metadata[dungeon.buildCount]);
					room.secondPass.add(new BlockObject(new BlockPos(x + buildWidth, y + buildHeight, z + buildLength), state));
				} else {
					IBlockState state = Block.getBlockById(blockId).getStateFromMeta(metadata[dungeon.buildCount]);
					world.setBlockState(new BlockPos(x + buildWidth, y + buildHeight, z + buildLength), state);					
				}
				

				dungeon.buildCount++;

				buildWidth++;

				if (buildWidth >= width) {
					buildLength++;
					buildWidth = 0;
				}
				if (buildLength >= length) {
					buildHeight++;
					buildLength = 0;
				}
				if (buildHeight >= height) {
					dungeon.buildCount = 0;
					buildHeight = 0;
					buildLength = 0;
					buildWidth = 0;
					dungeon.roomCount++;
					dungeon.nextRoom();
					break;
				}
			}
			
			//Place blocks from second pass
			for (int b = 0; b < room.secondPass.size(); b++) {
				world.setBlockState(new BlockPos(room.secondPass.get(b).getPos().getX(), room.secondPass.get(b).getPos().getY(), room.secondPass.get(b).getPos().getZ()), room.secondPass.get(b).getState());	
			}

			NBTTagList tileEntitiesList = nbtdata.getTagList("TileEntities", Constants.NBT.TAG_COMPOUND);

			if (buildHeight == 0 && buildLength == 0 && buildWidth == 0) {
				for (int i = 0; i < tileEntitiesList.tagCount(); i++) {
					try {
						TileEntity tileEntity = NBTHelper
								.readTileEntityFromCompound(tileEntitiesList.getCompoundTagAt(i));
						if (tileEntity != null) {
							NBTTagCompound tag = tileEntitiesList.getCompoundTagAt(i);
							int Xx = tag.getInteger("x");
							int Yy = tag.getInteger("y");
							int Zz = tag.getInteger("z");
							BlockPos bPos = new BlockPos(x + Xx, y + Yy, z + Zz);
							world.removeTileEntity(bPos);
							// checks if the TE is a return portal and sets the
							// return destination coords and dimension according
							// to the dungeon specs
							if (tileEntity instanceof TileEntityReturnPortalBlock) {
								TileEntityReturnPortalBlock te = (TileEntityReturnPortalBlock) tileEntity;
								te.setReturnLocation(dungeon.originX, dungeon.originY, dungeon.originZ,
										dungeon.returnDimension, dungeon.dungeonID);
								te.writeToNBT(tag);
								tileEntity = te;
							}

							world.setTileEntity(bPos, tileEntity);
							world.markChunkDirty(bPos, tileEntity);
							//System.out.println(tileEntity);
						}
					} catch (Exception e) {
						ModularBosses.logger.warn("TileEntity failed to load properly!", e);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static void instantBuild(World world, Dungeon dungeon) {

		for (int i = 0; i < dungeon.dungeonRooms.length; i++) {
			for (int j = 0; j < dungeon.dungeonRooms[i].length; j++) {

				Room room = dungeon.dungeonRooms[i][j];

				String roomPath = room.roomCode[0] + room.roomCode[1] + room.roomCode[2] + room.roomCode[3];
				String fileName = "./schematics/Worm/" + roomPath + "/1.schematic";

				double x = dungeon.originX + (dungeon.roomWidth * j);
				double y = dungeon.originY;
				double z = dungeon.originZ - (dungeon.roomLength * i);

				quickBuild(fileName, world, x, y, z);
			}

		}
		dungeon.finishedBuilding = true;
	}

	public static void quickBuild(String fileName, World world, double x, double y, double z) {
		try {
			File file = new File(fileName);
			NBTTagCompound nbtdata = SchematicUtil.readTagCompoundFromFile(file);

			width = nbtdata.getShort("Width");
			height = nbtdata.getShort("Height");
			length = nbtdata.getShort("Length");
			ItemStack icon = SchematicUtil.getIconFromNBT(nbtdata);

			size = width * height * length;
			blockObjects = new BlockObject[size];

			byte[] blockIDs = nbtdata.getByteArray("Blocks");
			byte[] metadata = nbtdata.getByteArray("Data");

			// testing schematica schematics
			Short id = null;
			final Map<Short, Short> oldToNew = new HashMap<Short, Short>();
			if (nbtdata.hasKey("SchematicaMapping")) {
				final NBTTagCompound mapping = nbtdata.getCompoundTag("SchematicaMapping");
				final Set<String> names = mapping.getKeySet();
				for (final String name : names) {
					oldToNew.put(mapping.getShort(name), (short) Block.REGISTRY.getIDForObject(Block.REGISTRY.getObject(new ResourceLocation(name))));
				}
			}

			counter = 0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < length; j++) {
					for (int k = 0; k < width; k++) {
						int blockId = UnsignedBytes.toInt(blockIDs[counter]);
						// Checks the id reference Map
						if ((id = oldToNew.get((short) blockId)) != null) {
							blockId = id;
						}

						IBlockState state = Block.getBlockById(blockId).getStateFromMeta(metadata[counter]);

						world.setBlockState(new BlockPos(x + k, y + i, z + j), state);
						counter++;
					}
				}
			}

			NBTTagList tileEntitiesList = nbtdata.getTagList("TileEntities", Constants.NBT.TAG_COMPOUND);

			for (int i = 0; i < tileEntitiesList.tagCount(); i++) {
				try {
					TileEntity tileEntity = NBTHelper.readTileEntityFromCompound(tileEntitiesList.getCompoundTagAt(i));
					if (tileEntity != null) {
						NBTTagCompound tag = tileEntitiesList.getCompoundTagAt(i);
						int Xx = tag.getInteger("x");
						int Yy = tag.getInteger("y");
						int Zz = tag.getInteger("z");
						BlockPos bPos = new BlockPos(x + Xx, y + Yy, z + Zz);
						world.removeTileEntity(bPos);
						world.setTileEntity(bPos, tileEntity);
						System.out.println(tileEntity);
					}
				} catch (Exception e) {
					ModularBosses.logger.warn("TileEntity failed to load properly!", e);
				}
			}

			counter = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}