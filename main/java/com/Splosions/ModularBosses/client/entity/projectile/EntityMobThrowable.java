/**
 * Credit due to coolAlias
 * please dont sue me
 * 
 */
package com.Splosions.ModularBosses.client.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * 
 * Abstract class that provides constructor for throwing entity as a mob
 *
 */
public abstract class EntityMobThrowable extends EntityThrowable
{
	/** Usually the damage this entity will cause upon impact */
	private float damage;

	public EntityMobThrowable(World world) {
		super(world);
	}

	public EntityMobThrowable(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityMobThrowable(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
	
	/**
	 * Constructs a throwable entity heading towards target's initial position with given velocity, with possible abnormal trajectory;
	 * @param wobble amount of deviation from base trajectory, used by Skeletons and the like; set to 0.0F for no x/z deviation
	 * YOffset is subtracted from the Y coordinates that determin spawn location.
	 */
	public EntityMobThrowable(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float HitBoxSize1, float HitBoxSize2) {
		super(world, shooter);
		if (HitBoxSize1 != 0 || HitBoxSize2 != 0){
			this.setSize(HitBoxSize1, HitBoxSize2);
		}
		

        float r3 = this.rotationYaw * (float)Math.PI / 180.0F;
        float r11 = MathHelper.cos(r3);
        float r4 = MathHelper.sin(r3);
        
        
		
		
		
		this.posY = shooter.posY + (double) shooter.getEyeHeight() - 0.2D;		
		double d0 = target.posX - shooter.posX;
		double d1 = target.posY - this.posY - 2;
		double d2 = target.posZ - shooter.posZ;
		double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
		if (d3 >= 1.0E-7D) {
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			
			
			double xOff = (double)(r11 * -FrontToBack) + (double)(r4 * SideToSide);
			double zOff = (double)(r4 * FrontToBack) + (double)(r11 * SideToSide);
			
			
			
			setLocationAndAngles(shooter.posX + xOff, this.posY - YOffset, shooter.posZ + zOff, f2, f3);
			YOffset = 0.0F;
			float f4 = (float) d3 * 0.2F;
			setThrowableHeading(d0 - xOff, d1 + (double) f4, d2 - zOff, velocity, wobble);//0 used to be "d1 + (double) f4" 0 causes the projectile to always fire straight out of entity with no upward or downward arc
		}
	}
	
	
	
	public EntityMobThrowable(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float MotionY, float YawOffset) {
		super(world, shooter);

		

		
		this.posY = shooter.posY + (double) shooter.getEyeHeight() - 0.10000000149011612D;
		double d0 = target.posX - shooter.posX;
		double d1 = target.getBoundingBox().minY + 1 - this.posY;
		double d2 = target.posZ - shooter.posZ;
		double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
		
		if (d3 >= 1.0E-7D) {
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			setLocationAndAngles(shooter.posX - d4, this.posY, shooter.posZ - d5, f2, f3); 
			float f4 = (float) d3 * 0.2F;
			setThrowableHeading(d0 * -1, 0, d2 * -1, velocity, wobble);//0 used to be "d1 + (double) f4" 0 causes the projectile to always fire straight out of entity with no upward or downward arc
	
			float A2 = MathHelper.sin(YawOffset + shooter.rotationYaw * (float)Math.PI / 180.0F);
	        float A3 = MathHelper.cos(YawOffset + shooter.rotationYaw * (float)Math.PI / 180.0F);
	        this.motionX += (double)(-1 * velocity * A2);
	        this.motionY += MotionY;
	        this.motionZ += (double)(velocity * A3);
	        
		}
	}
	

	
	/** Returns the amount of damage this entity will cause upon impact */
	public float getDamage() {
		return damage;
	}
	
	/**
	 * Sets the damage this entity will cause upon impact
	 */
	public EntityMobThrowable setDamage(float amount) {
		this.damage = amount;
		return this;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setFloat("damage", damage);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		damage = compound.getFloat("damage");
	}
}