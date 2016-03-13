package com.Splosions.ModularBosses.blocks.tileentity;

import java.time.Instant;
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

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class TileEntityControlBlock extends TileEntity implements IUpdatePlayerListBox
{
	/** Maximum number of characters that will fit on one chat line */
	public static final int LINE_LENGTH = 100;
	public static final int MAX_MESSAGE_LENGTH = LINE_LENGTH * 3;
	private String message = "";
	public int ticksExisted;
	
	public boolean powerBlock;
		
	public ArrayList<String> spawnList = new ArrayList<String>();
	public ArrayList<String> foundList = new ArrayList<String>();
	public int triggerPower = 0;
	public int inputPower = 0;
	
	String spawnMob;
	int xOff = 1;
	int yOff = 1;
	int zOff = 1;
	int spawnFreq = 1;
	//int spawnDelay;
	int spawnCount = 0;
	
	long targetTime = 0;
	boolean startTime = false;
	
	
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
		String[] mesArray = message.split("\\|", -1);
		 if (mesArray.length >= 7){
			spawnMob = mesArray[0];
			xOff = Integer.parseInt(mesArray[1]);
			yOff = Integer.parseInt(mesArray[2]);
			zOff = Integer.parseInt(mesArray[3]);
			spawnFreq = (Integer.parseInt(mesArray[4]) > 0) ? Integer.parseInt(mesArray[4]) : 1 ;
			//spawnDelay = Integer.parseInt(mesArray[5]);
			spawnCount = Integer.parseInt(mesArray[5]);
		 }
		markDirty();
		System.out.println(this.message);
	}
	




	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		 NBTTagList tagList = compound.getTagList("MyStringList", Constants.NBT.TAG_COMPOUND);
		 //System.out.println("TagCount = " + tagList.tagCount());
		 for(int i = 0; i < tagList.tagCount(); i++) {
		  NBTTagCompound tag = tagList.getCompoundTagAt(i);
		  String s = tag.getString("MyString" + i);
		  //System.out.println("ReadNBT = " + s);
		  spawnList.add(i, s);
		 }
		 
		 
		 
		 
		 
		 
		 
		 message = compound.getString("message");
		 setMessage(message);
		triggerPower = compound.getInteger("triggerPower");
		inputPower = compound.getInteger("inputPower");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList tagList = new NBTTagList();
		 for(int i = 0; i < spawnList.size(); i++){
		  String s = spawnList.get(i);
	  if(s != null){
		  //System.out.println("WriteNBT = " + s);
		  NBTTagCompound tag = new NBTTagCompound();
		  tag.setString("MyString" + i, s);
		  tagList.appendTag(tag);
	  		}
		}
		 compound.setTag("MyStringList", tagList);
		 
		 compound.setString("message", message);
		 setMessage(message);
		 
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
	

	@Override
	public void update() {
		ticksExisted++;
		//called each tick just like onUpdate

		if (triggerPower == 1 && !this.worldObj.isRemote && spawnMob != "" && spawnMob != null && !spawnMob.isEmpty()){
			findMobs();
			spawnList = new ArrayList<String>(foundList);
			while (spawnList.size() < spawnCount){
				spawnCreature(this.worldObj, spawnMob, this.pos.getX(),this.pos.getY() + 2, this.pos.getZ());
			}
		}
		
		
		if (this.ticksExisted % 20 == (20 - 1) && !this.worldObj.isRemote) {findMobs();}//searches for mobs every 1 second
		
		

		if (foundList.size() < spawnCount && inputPower == 1){
			//System.out.println("DERP");
			if (Instant.now().getEpochSecond() >= targetTime && inputPower == 1 && !this.worldObj.isRemote && spawnMob != "" && spawnMob != null && !spawnMob.isEmpty()) {
				findMobs();
				spawnList = new ArrayList<String>(foundList);
					while (spawnList.size() < spawnCount){
						spawnCreature(this.worldObj, spawnMob, this.pos.getX(),this.pos.getY() + 2, this.pos.getZ());
					}
				}
		} else {
			targetTime = Instant.now().getEpochSecond() + spawnFreq;
		}
	}
	
	
	
	
	
	
	public void findMobs(){
		foundList.clear();
		List list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.getRenderBoundingBox().expand(xOff, yOff, zOff));
		Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();
            
        	if (spawnList.contains(entity.getUniqueID().toString())){
        		foundList.add(entity.getUniqueID().toString());
        		//System.out.println(entity.getUniqueID().toString());
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
			Random rn = new Random();
			x += (rn.nextInt(4) - 2);
			z += (rn.nextInt(4) - 2);
		
			/**
			BlockPos pos = new BlockPos(x, y, z);
			IBlockState block = this.worldObj.getBlockState(pos);
			block.getBlock().getMaterial();
			*/
			entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			entityliving.rotationYawHead = entityliving.rotationYaw;
			entityliving.renderYawOffset = entityliving.rotationYaw;
			spawnList.add(entity.getUniqueID().toString());
			world.spawnEntityInWorld(entity);
			entityliving.playLivingSound();
			//System.out.println("Spawning");
		
		} else if (entity == null){
			ModularBosses.logger.warn("Monster Name: " + entityName + " Given to ControlBlock in World: " + world + " at loc: " + x + ", " + y + ", " + z + " is not a valid name");
		} else {
			ModularBosses.logger.warn("Monster Name: " + entityName + " Given to ControlBlock in World: " + world + " at loc: " + x + ", " + y + ", " + z + " is not an instance of EntityLiving");
		}
		
		return entity;
	}

}
