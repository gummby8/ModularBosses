package com.Splosions.ModularBosses.entity;

import java.util.Random;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityTeleportBiped extends Entity implements IEntityAdditionalSpawnData
{
	Random rand = new Random();
	public String strLoc;
	public ResourceLocation reLoc;
	public EntityTeleportBiped(World worldIn)
	{
		super(worldIn);
	}

	public EntityTeleportBiped(World worldIn, Entity shooter, double x, double y, double z, float yaw, String tLoc)
	{
		super(worldIn);
		this.setSize(1F, 1F);
		this.setPositionAndRotation(x, y, z, yaw, 0);
		this.strLoc = tLoc;	
	} 


	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{







		if (this.ticksExisted > 80){
			for (int l = 0; l < 20; ++l){
				this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX, this.posY + 1, this.posZ, rand.nextFloat() - 0.5F,rand.nextFloat() - 0.5F,rand.nextFloat() - 0.5F, 5);
			}
			
			setDead();
		}


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

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, strLoc);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		String bytes = ByteBufUtils.readUTF8String(additionalData);
		reLoc = new ResourceLocation(bytes);

	}






}