package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import com.Splosions.ModularBosses.entity.CustomEntityList;
import com.Splosions.ModularBosses.entity.EntitySandWorm;
import com.Splosions.ModularBosses.items.ItemCustomEgg;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBait extends EntityThrowable {

	
	public List<EntitySandWorm> wormList;

	public EntityBait(World world) {
		super(world);
	}

	public EntityBait(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityBait(World world, EntityLivingBase shooter) {
		super(world, shooter);

	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!this.worldObj.isRemote) {

			if (this.ticksExisted % 20 == (20 - 1)) {
				this.wormList = TargetUtils.getSpecificList(this, EntitySandWorm.class, 256, 256);
				for (EntitySandWorm worm : this.wormList) {
					worm.baitAction(this.posX, this.posY, this.posZ);
				}
			}

		
			if (this.ticksExisted >= 100) {
				setDead();
			}

		}
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		this.motionX = this.motionY = this.motionZ = 0;
	}

}