package com.Splosions.ModularBosses.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityChorpSlimeBlob extends EntityMobThrowable
{

	private int strength;
	private int duration;

	public EntityChorpSlimeBlob(World world) {
		super(world);
	}

	public EntityChorpSlimeBlob(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityChorpSlimeBlob(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityChorpSlimeBlob(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float Size1, float Size2, int dmg, int duration, int strength) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
		this.setDamage(dmg);
		this.strength = strength;
		this.duration = duration;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop.entityHit != null && mop.entityHit instanceof EntityPlayer) {
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(2, duration, strength));
			
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}


}