package com.Splosions.ModularBosses.entity.player;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.proxy.ClientProxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class MBExtendedPlayer implements IExtendedEntityProperties {
	
	public final static String EXT_PROP_NAME = "MBExtendedPlayer";
	
	public EntityPlayer player;
	
	
	public int limboTime;
	public int preLimbo;
	public int limbo;

	public static final int LIMBO_WATCHER = 27;
	
	
	public MBExtendedPlayer(EntityPlayer player)
	{
	this.player = player;
	//Every player starts out of limbo.
	this.limbo = 0;
	this.preLimbo = 0;
	this.limboTime = 0;
	player.getDataWatcher().addObject(LIMBO_WATCHER, 0);
	}

	
	/**
	* Used to register these extended properties for the player during EntityConstructing event
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final void register(EntityPlayer player)
	{
	player.registerExtendedProperties(MBExtendedPlayer.EXT_PROP_NAME, new MBExtendedPlayer(player));
	}
	

	/**
	* Returns ExtendedPlayer properties for player
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final MBExtendedPlayer get(EntityPlayer player){
	return (MBExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}

	
	
	public void onUpdate() {

		if (this.player.getEntityWorld().isRemote){
			this.limbo = this.player.getDataWatcher().getWatchableObjectInt(LIMBO_WATCHER);
		}
		
		/*
		 * Sets the datawatcher based on limboTime on the server
		 */
		if (this.limboTime > 0 && !this.player.getEntityWorld().isRemote){
			this.player.getDataWatcher().updateObject(LIMBO_WATCHER, 1);
		} else if (!this.player.getEntityWorld().isRemote){
			this.player.getDataWatcher().updateObject(LIMBO_WATCHER ,0);
		}
		
		
		/*
		 * Sets the shader when the datawatcher changes to 1 clientside
		 */
		if (this.player.getEntityWorld().isRemote && this.preLimbo != this.limbo && this.limbo == 1){
			
	
		}
		
		/*
		 * Clears the shader when the datawatcher changes to 0 clientside
		 */
		if (this.player.getEntityWorld().isRemote && this.preLimbo != this.limbo && this.limbo == 0){
			ClientProxy.clearShader();
		}
		
		
		
		if (this.player.getEntityWorld().isRemote){
			this.preLimbo = this.limbo;
		}
		
		if (!this.player.getEntityWorld().isRemote){
			this.limboTime -= (this.limboTime > 0) ? 1 : 0;
		}
	}
	
	
	
	
	
	
	

	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {

	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
