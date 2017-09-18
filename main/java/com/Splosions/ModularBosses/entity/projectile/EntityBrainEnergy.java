package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBrainEnergy extends EntityMobThrowable {

	float YAW;

	protected static final int SHOOTER_INDEX = 22;
	public EntityLivingBase Shooter;

	private float Dmg;

	public EntityBrainEnergy(World world) {
		super(world);
		this.ignoreFrustumCheck = true;
	}

	public EntityBrainEnergy(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityBrainEnergy(World world, double x, double y, double z, float YAW) {
		super(world, x, y, z);
		this.YAW = YAW;
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

	/**
	 * Attacks all entities inside this list, dealing 5 hearts of damage.
	 */
	private void attackEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);

			if (entity instanceof EntityLivingBase && entity != this.Shooter) {

				entity.attackEntityFrom(DamageSource.causeMobDamage(this.Shooter), this.Dmg);

				System.out.println(entity);
			}
		}
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop != null) {
			if (mop.typeOfHit == MovingObjectType.BLOCK) {
				this.setDead();
			}
			if (mop.entityHit instanceof EntityPlayer) {
				mop.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.Shooter), this.Dmg);	
			}
			
		}
	}

}
