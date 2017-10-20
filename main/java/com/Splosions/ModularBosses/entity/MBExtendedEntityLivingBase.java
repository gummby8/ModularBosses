package com.Splosions.ModularBosses.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class MBExtendedEntityLivingBase implements IExtendedEntityProperties {
	
	public final static String EXT_PROP_NAME = "MBExtendedEntityLivingBase";
	
	public EntityLivingBase entity;
	
	
	public int limboTime;
	public int limbo;


	public static final int LIMBO_WATCHER = 27;

	
	
	public MBExtendedEntityLivingBase(EntityLivingBase entity)
	{
	this.entity = entity;
	entity.getDataWatcher().addObject(LIMBO_WATCHER, 0);
	}

	
	/**
	* Used to register these extended properties for the player during EntityConstructing event
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final void register(EntityLivingBase ent)
	{
		ent.registerExtendedProperties(MBExtendedEntityLivingBase.EXT_PROP_NAME, new MBExtendedEntityLivingBase(ent));
	}
	

	/**
	* Returns ExtendedPlayer properties for player
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final MBExtendedEntityLivingBase get(EntityLivingBase ent){
	return (MBExtendedEntityLivingBase) ent.getExtendedProperties(EXT_PROP_NAME);
	}

	
	
	public void onUpdate() {
		if (this.entity.getEntityWorld().isRemote) {
			this.limbo = this.entity.getDataWatcher().getWatchableObjectInt(LIMBO_WATCHER);
			} else {
			this.entity.getDataWatcher().updateObject(LIMBO_WATCHER, (this.limboTime > 0 ? 1 : 0));
			this.limbo = this.limboTime > 0 ? 1 : 0;
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
