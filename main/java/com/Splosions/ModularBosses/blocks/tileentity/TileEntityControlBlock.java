package com.Splosions.ModularBosses.blocks.tileentity;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.entity.CustomEntityList;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.client.OpenControlBlockEditorPacket;

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
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setString("message", message);
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
		// TODO Auto-generated method stub
		int time = 10 * 20; // 20 ticks per second times 10 seconds
		if (this.ticksExisted % time == (time - 1) && !this.worldObj.isRemote) { // if you check against 0, it will drop an item immediately every time the world loads

			spawnCreature(this.worldObj, message, this.pos.getX(),this.pos.getY() + 2, this.pos.getZ() );
			
		}
		
		
	}
	
	
	
	/**
	 * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
	 */
	public Entity spawnCreature(World world, String entityName, double x, double y, double z) {
		Entity entity = null;
		System.out.println(EntityList.getEntityNameList());
		entity = EntityList.createEntityByName(entityName, world);
		System.out.println("Entity = " + entity);
		if (entity instanceof EntityLiving && entity != null) {
			EntityLiving entityliving = (EntityLiving) entity;
			entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			entityliving.rotationYawHead = entityliving.rotationYaw;
			entityliving.renderYawOffset = entityliving.rotationYaw;
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
