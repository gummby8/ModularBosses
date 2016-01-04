package com.Splosions.ModularBosses.client.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityChorpSlimeBlob extends EntityMobThrowable
{

	public EntityChorpSlimeBlob(World world) {
		super(world);
	}

	public EntityChorpSlimeBlob(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityChorpSlimeBlob(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityChorpSlimeBlob(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float Size1, float Size2) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		
		if (mop.entityHit != null) {
			Potion potioneffect = (Potion.moveSlowdown);
			//System.out.println(mop.entityHit.getEntityName());
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(2, 120,4));
			
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}


}