package com.Splosions.ModularBosses.entity.player;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.proxy.ClientProxy;

import net.minecraft.client.Minecraft;
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
		if (this.player.getEntityWorld().isRemote) {
			this.limbo = this.player.getDataWatcher().getWatchableObjectInt(LIMBO_WATCHER);
			if (this.preLimbo != this.limbo && this.player == Minecraft.getMinecraft().thePlayer) {
				if (this.limbo == 1) {
					ClientProxy.sobelShader();
				} else {
					ClientProxy.clearShader();
				}
			}
			this.preLimbo = this.limbo;
		} else {
			this.player.getDataWatcher().updateObject(LIMBO_WATCHER, (this.limboTime > 0 ? 1 : 0));
		}
		this.limboTime -= (this.limboTime > 0) ? 1 : 0;
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
