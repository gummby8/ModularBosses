package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import com.Splosions.ModularBosses.entity.EntitySandWorm;
import com.Splosions.ModularBosses.items.ModularBossesItems;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMBParticleEmitter  extends Entity {

	public int variant;
	

	public EntityMBParticleEmitter(World world) {
		super(world);
	}

	
	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (this.worldObj.isRemote) {
			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY + 2.0D, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub
		
	}


}