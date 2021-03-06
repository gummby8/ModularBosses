package com.Splosions.ModularBosses.blocks.tileentity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.Splosions.ModularBosses.ModularBosses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class TileEntityControlBlock extends TileEntity implements IUpdatePlayerListBox {
	/** Maximum number of characters that will fit on one chat line */
	public static final int LINE_LENGTH = 100;
	public static final int MAX_MESSAGE_LENGTH = LINE_LENGTH * 3;
	private String message = "";
	public boolean ranSpawnLoc;
	public boolean showBorder = false;
	
	public int ticksExisted;



	public ArrayList<String> spawnList = new ArrayList<String>();
	public ArrayList<String> foundList = new ArrayList<String>();
	public int triggerPower = 0;
	public int inputPower = 0;

	String spawnMob;
	public int xOff = 1;
	public int yOff = 1;
	public int zOff = 1;
	int spawnFreq = 1;
	// int spawnDelay;
	int spawnCount = 0;

	long targetTime = 0;

	public boolean firstSpawn = false;

	public TileEntityControlBlock() {

	}

	@Override
	public void update() {
		if (!this.worldObj.isRemote) {
			ticksExisted++;
			// called each tick just like onUpdate

			if (!this.worldObj.isRemote && spawnMob != "" && spawnMob != null && !spawnMob.isEmpty()) {
				if (triggerPower == 1) {
					spawnAndFind();
				} else if (foundList.size() < spawnCount && ticksExisted > 100 && !firstSpawn) {
					spawnAndFind();
				} else if (foundList.size() < spawnCount && inputPower == 1) {
					if (Instant.now().getEpochSecond() >= targetTime) {
						spawnAndFind();
					}
				} else {
					targetTime = Instant.now().getEpochSecond() + spawnFreq;
				}

			}

			// searches for mobs every 1 second
			if (this.ticksExisted % 20 == (20 - 1) && !this.worldObj.isRemote) {
				findMobs();
			}

		}
	}

	
	public void spawnAndFind(){
		findMobs();
		spawnList = new ArrayList<String>(foundList);
		while (spawnList.size() < spawnCount) {
			spawnCreature(this.worldObj, spawnMob, this.pos.getX(), this.pos.getY() + 2, this.pos.getZ());
		}
		targetTime = Instant.now().getEpochSecond() + spawnFreq;
		firstSpawn = true;
	}
	
	
	public void findMobs() {
		foundList.clear();
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		AxisAlignedBB axisalignedbb = (new AxisAlignedBB((double) i, (double) j, (double) k, (double) (i + 1), (double) (j + 1 + yOff), (double) (k + 1)));

		List list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, axisalignedbb.expand(xOff, 0, zOff));
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Entity entity = (Entity) iterator.next();

			if (spawnList.contains(entity.getUniqueID().toString())) {
				foundList.add(entity.getUniqueID().toString());
			}
		}
		this.worldObj.notifyNeighborsOfStateChange(pos, blockType);
	}

	/**
	 * Spawns the creature specified by the egg's type in the location specified
	 * by the last three parameters.
	 */
	public Entity spawnCreature(World world, String entityName, double x, double y, double z) {
		Entity entity = null;
		entity = EntityList.createEntityByName(entityName, world);
		if (entity instanceof EntityLiving && entity != null) {
			EntityLiving entityliving = (EntityLiving) entity;
			entityliving.enablePersistence();
			Random rn = new Random();
			x += (ranSpawnLoc == true) ? (rn.nextInt(4) - 2) : 0;
			z += (ranSpawnLoc == true) ? (rn.nextInt(4) - 2) : 0;

			/**
			 * BlockPos pos = new BlockPos(x, y, z); IBlockState block =
			 * this.worldObj.getBlockState(pos); block.getBlock().getMaterial();
			 */
			entityliving.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			entityliving.rotationYawHead = entityliving.rotationYaw;
			entityliving.renderYawOffset = entityliving.rotationYaw;
			spawnList.add(entity.getUniqueID().toString());
			world.spawnEntityInWorld(entityliving);
		} else if (entity == null) {
			ModularBosses.logger.warn("Monster Name: " + entityName + " Given to ControlBlock in World: " + world + " at loc: " + x + ", " + y + ", " + z + " is not a valid name");
		} else {
			ModularBosses.logger.warn("Monster Name: " + entityName + " Given to ControlBlock in World: " + world + " at loc: " + x + ", " + y + ", " + z + " is not an instance of EntityLiving");
		}

		return entity;
	}

	/**
	 * Returns this Tile Entity's message, or a default message if none was set.
	 * Note that the message is NOT updated on the client side.
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		String[] mesArray = message.split("\\|");
		if (mesArray.length >= 8) {
			spawnMob = mesArray[0];
			xOff = Integer.parseInt(mesArray[1]);
			yOff = Integer.parseInt(mesArray[2]);
			zOff = Integer.parseInt(mesArray[3]);
			spawnFreq = (Integer.parseInt(mesArray[4]) > 0) ? Integer.parseInt(mesArray[4]) : 1;
			spawnCount = Integer.parseInt(mesArray[5]);
			ranSpawnLoc = (Integer.parseInt(mesArray[6]) == 1)?true:false;
			showBorder = (Integer.parseInt(mesArray[7]) == 1)?true:false;
		}
		markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList tagList = compound.getTagList("MyStringList", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			String s = tag.getString("MyString" + i);
			spawnList.add(i, s);
		}

		message = compound.getString("message");
		setMessage(message);
		firstSpawn = compound.getBoolean("firstSpawn");
		targetTime = compound.getLong("targetTime");
		triggerPower = compound.getInteger("triggerPower");
		inputPower = compound.getInteger("inputPower");

	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList tagList = new NBTTagList();
		for (int i = 0; i < spawnList.size(); i++) {
			String s = spawnList.get(i);
			if (s != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("MyString" + i, s);
				tagList.appendTag(tag);
			}
		}
		compound.setTag("MyStringList", tagList);
		compound.setString("message", message);
		setMessage(message);
		compound.setBoolean("firstSpawn", firstSpawn);
		compound.setLong("targetTime", targetTime);
		compound.setInteger("triggerPower", triggerPower);
		compound.setInteger("inputPower", inputPower);
		markDirty();
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		writeToNBT(nbtTag);
		return new S35PacketUpdateTileEntity(pos, 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

}
