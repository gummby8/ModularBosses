package com.Splosions.ModularBosses.util.schematic;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.BlockControlBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.entity.EntityCartographer;
import com.Splosions.ModularBosses.util.BlockObject;
import com.Splosions.ModularBosses.util.NBTHelper;
import com.google.common.primitives.UnsignedBytes;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.GameData;

public class Schematic {

	private static short width;
	private static short height;
	private static short length;
	private static int size;
	private static int counter = 0;
	private static int i = 0;
	private static int j = 0;
	private static int k = 0;
	private static BlockObject[] blockObjects;

	private static final FMLControlledNamespacedRegistry<Block> BLOCK_REGISTRY = GameData.getBlockRegistry();

	public static void staggeredBuild(World world, Dungeon dungeon) {
		try {

			int x = dungeon.RoomPosX;
			int y = dungeon.RoomPosY;
			int z = dungeon.RoomPosZ;

			Room room = dungeon.dungeonRooms[dungeon.dgnRoomsLength][dungeon.dgnRoomsWidth];

			String roomPath = room.roomCode[0] + room.roomCode[1] + room.roomCode[2] + room.roomCode[3];
			String fileName = "./schematics/Worm/" + roomPath + "/1.schematic";

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
					oldToNew.put(mapping.getShort(name), (short) BLOCK_REGISTRY.getId(name));
				}
			}

			
			i = 0;
			j = 0;
			k = 0;
			for (int q = 0; q < dungeon.buildCount; q++) {
				if (i >= height) {
					break;
				} else if (j >= length) {
					i++;
					j = 0;
				} else if (k >= width) {
					j++;
					k = 0;
				}
				k++;
			}
			
			System.out.println(i + " " + j + " " + k);
			
			for (int q = 0; q < dungeon.buildsPerTick; q++) {
				if (i >= height) {
					dungeon.buildCount = 0;
					i = 0;
					j = 0;
					k = 0;
					dungeon.roomCount++;
					dungeon.nextRoom();
					break;
				} else if (j >= length) {
					i++;
					j = 0;
				} else if (k >= width) {
					j++;
					k = 0;
				} else {

					int blockId = UnsignedBytes.toInt(blockIDs[counter]);
					// Checks the id reference Map
					if ((id = oldToNew.get((short) blockId)) != null) {
						blockId = id;
					}

					//System.out.println(i + " " + j + " " + k);
					IBlockState state = Block.getBlockById(blockId).getStateFromMeta(metadata[dungeon.buildCount]);

					world.setBlockState(new BlockPos(x + k, y + i, z + j), state);
					dungeon.buildCount++;

					k++;
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void build(String fileName, World world, Entity entity, double x, double y, double z) {

		if (!world.isRemote) {
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
						oldToNew.put(mapping.getShort(name), (short) BLOCK_REGISTRY.getId(name));
					}
				}

				EntityCartographer ent = (EntityCartographer) entity;

				for (int q = 0; q < ent.schemTickInterval; q++) {

					if (i >= height) {
						counter = 0;
						i = 0;
						j = 0;
						k = 0;
						ent.mapRoom++;
						ent.setPositionAndUpdate(ent.posX + ent.roomWidth, ent.posY, ent.posZ);
						ent.forceChunk();
						break;
					} else if (j >= length) {
						i++;
						j = 0;
					} else if (k >= width) {
						j++;
						k = 0;
						System.out.println("Building New Room");
					} else {

						int blockId = UnsignedBytes.toInt(blockIDs[counter]);
						// Checks the id reference Map
						if ((id = oldToNew.get((short) blockId)) != null) {
							blockId = id;
						}

						// BlockPos pos = new BlockPos(k, i, j);
						IBlockState state = Block.getBlockById(blockId).getStateFromMeta(metadata[counter]);

						// blockObjects[counter] = new BlockObject(pos, state);
						world.setBlockState(new BlockPos(x + k, y + i, z + j), state);
						counter++;

						k++;
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

			} catch (Exception e) {
				e.printStackTrace();
			}
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
					oldToNew.put(mapping.getShort(name), (short) BLOCK_REGISTRY.getId(name));
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