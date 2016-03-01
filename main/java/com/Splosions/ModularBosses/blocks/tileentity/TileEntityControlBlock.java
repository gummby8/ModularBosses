package com.Splosions.ModularBosses.blocks.tileentity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.BlockControlBlock;
import com.Splosions.ModularBosses.client.entity.CustomEntityList;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.client.OpenControlBlockEditorPacket;
import com.jcraft.jorbis.Block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;



public class TileEntityControlBlock extends TileEntity implements IUpdatePlayerListBox
{
	/** Maximum number of characters that will fit on one chat line */
	public static final int LINE_LENGTH = 100;
	public static final int MAX_MESSAGE_LENGTH = LINE_LENGTH * 3;
	private String message = "";
	public int ticksExisted;
	
	public boolean powerBlock;
		
	public ArrayList<Integer> spawnList = new ArrayList<Integer>();
	public ArrayList<Integer> foundList = new ArrayList<Integer>();
	public boolean triggerPower;
	public boolean inputPower;
	
	

	
	public TileEntityControlBlock() {}

	/**
	 * Returns this Tile Entitie's message, or a default message if none was set.
	 * Note that the message is NOT updated on the client side.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message to display when a Gossip Stone is activated while wearing the Mask of Truth
	 */
	public void setMessage(String message) {
		this.message = message;
		markDirty();
	}
	



	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		message = compound.getString("message");
		int[] ints = compound.getIntArray("spawnList");
		
		List<Integer> intList = new ArrayList<Integer>();
	    for (int index = 0; index < ints.length; index++)
	    {
	        intList.add(ints[index]);
	    }
		
		spawnList = new ArrayList<Integer>(intList);
		
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setString("message", message);
		if (spawnList.size() != 0){
		
			 	int s = spawnList.size();
			    int[] intArray = new int[s];
			    for (int i = 0; i < s; i++) {
			        intArray[i] = spawnList.get(i).intValue();
			    }
			
		compound.setIntArray("spawnList", intArray);
		}
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
	

	@Override
	public void update() {
		ticksExisted++;
		//called each tick just like onUpdate

		if (triggerPower && !this.worldObj.isRemote){
			findMobs();
			spawnList = new ArrayList<Integer>(foundList);
			while (spawnList.size() < 5){
				spawnCreature(this.worldObj, message, this.pos.getX(),this.pos.getY() + 2, this.pos.getZ());
			}
		}
		
		if (this.ticksExisted % 20 == (20 - 1) && !this.worldObj.isRemote) {findMobs();}//searches for mobs every 1 second		
		
			int time = 10 * 20; // 20 ticks per second times 10 seconds
		if (this.ticksExisted % time == (time - 1) && inputPower && !this.worldObj.isRemote) { // if you check against 0, it will drop an item immediately every time the world loads
			findMobs();
	        System.out.println("spawnList = " + spawnList.size());
	        System.out.println("foundList = " + foundList.size());
			spawnList = new ArrayList<Integer>(foundList);
			
			if (spawnList.size() < 5){
				spawnCreature(this.worldObj, message, this.pos.getX(),this.pos.getY() + 2, this.pos.getZ());
			} else {
				System.out.println("Better count your chickens");
			}
					
			
		} 
	}
	
	
	public void findMobs(){
		foundList.clear();
		List list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.getRenderBoundingBox().expand(20, 20, 20));
		Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();
            System.out.println(entity.getUniqueID());
        	if (spawnList.contains(entity.getEntityId())){
        		foundList.add(entity.getEntityId());
        		//System.out.println("found = " + entity.getEntityId());
        	}
        }
        
        this.worldObj.notifyNeighborsOfStateChange(pos, blockType);
	}
	
	/**
	 * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
	 */
	public Entity spawnCreature(World world, String entityName, double x, double y, double z) {
		Entity entity = null;
		//System.out.println(EntityList.getEntityNameList());
		entity = EntityList.createEntityByName(entityName, world);
		//System.out.println("Entity = " + entity);
		if (entity instanceof EntityLiving && entity != null) {
			EntityLiving entityliving = (EntityLiving) entity;
			entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			entityliving.rotationYawHead = entityliving.rotationYaw;
			entityliving.renderYawOffset = entityliving.rotationYaw;
			spawnList.add(entity.getEntityId());
			world.spawnEntityInWorld(entity);
			entityliving.playLivingSound();
			System.out.println("Spawning");
		} else if (entity == null){
			ModularBosses.logger.warn("Monster Name: " + entityName + " Given to ControlBlock in World: " + world + " at loc: " + x + ", " + y + ", " + z + " is not a valid name");
		} else {
			ModularBosses.logger.warn("Monster Name: " + entityName + " Given to ControlBlock in World: " + world + " at loc: " + x + ", " + y + ", " + z + " is not an instance of EntityLiving");
		}
		
		return entity;
	}

}
