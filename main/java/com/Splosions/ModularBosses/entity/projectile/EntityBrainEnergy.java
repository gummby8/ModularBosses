package com.Splosions.ModularBosses.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBrainEnergy extends EntityMobThrowable {

	float YAW;

	protected static final int SHOOTER_INDEX = 22;
	public EntityLivingBase Shooter;

	private float Dmg = 1;

	public EntityBrainEnergy(World world) {
		super(world);
		this.ignoreFrustumCheck = true;
	}

	public EntityBrainEnergy(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityBrainEnergy(World world, double x, double y, double z, float YAW, int dmg) {
		super(world, x, y, z);
		this.YAW = YAW;
		this.Dmg = dmg;
	}

	public EntityBrainEnergy(World world, EntityLivingBase shooter, float wobble, float FrontToBack, float YOffset, float SideToSide) {
		super(world, shooter, wobble, FrontToBack, YOffset, SideToSide);
	}

	// set to 0 do have 0 spell drop
	@Override
	protected float getGravityVelocity() {
		return 0.000F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		rotationPitch = 0;
		this.rotationYaw = YAW;
		float f2 = MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F);
		float f3 = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F);
		this.motionX += (double) (-1 * 0.05F * f2);
		this.motionZ += (double) (0.05F * f3);

		// wave will last for 2 seconds
		if (this.ticksExisted > 40) {
			setDead();
		}

	}



	@Override
	protected void onImpact(RayTraceResult rtr) {
		if (rtr != null) {
			if (rtr.typeOfHit == RayTraceResult.Type.BLOCK) {
				this.setDead();
			}
			if (rtr.entityHit instanceof EntityPlayer) {
				rtr.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.Shooter), this.Dmg);	
			}
			
		}
		
	}

}
