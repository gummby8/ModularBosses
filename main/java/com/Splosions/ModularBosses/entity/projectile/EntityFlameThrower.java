package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityFlameThrower extends EntityMobThrowable implements IEntityAdditionalSpawnData {

	
	
	protected static final int SHOOTER_INDEX = 22;
	protected static final int SCALE = 23;
	public EntityLivingBase Shooter;

	private float Dmg;

	

	public EntityFlameThrower(World world) {
		super(world);
		setSize(1,2);




	}

	public EntityFlameThrower(World world, EntityLivingBase entity) {
		super(world, entity);
	}
	
	


	public EntityFlameThrower(World world, double x, double y, double z) {
		super(world, x, y, z);
	}


	public EntityFlameThrower(World world, EntityLivingBase shooter, float wobble, float FrontToBack, float YOffset, float SideToSide, int dmg) {
		super(world, shooter, wobble, FrontToBack, YOffset, SideToSide);
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
		this.Dmg = dmg;
		this.setScale(1);
	}
	
	public EntityFlameThrower(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide,float Size1,float Size2,int dmg) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
		setShooter(shooter);
		this.Shooter = (EntityLivingBase) getShooter();
		this.Dmg = dmg;
	}



	@Override
	public void entityInit() {
		super.entityInit();
		dataWatcher.addObject(SHOOTER_INDEX, -1);
		dataWatcher.addObject(SCALE, 1);
		//SET SIZE HERE NEXT TIME
	}

	public int getScale() {
		
		return dataWatcher.getWatchableObjectInt(SCALE);
	}

	public void setScale(int scale) {
		dataWatcher.updateObject(SCALE, scale);
	}
	

	public Entity getShooter() {
		int id = dataWatcher.getWatchableObjectInt(SHOOTER_INDEX);
		return (id == -1 ? null : worldObj.getEntityByID(id));
	}

	public void setShooter(Entity entity) {
		dataWatcher.updateObject(SHOOTER_INDEX, entity != null ? entity.getEntityId() : -1);
	}




	//set to 0 do have 0 spell drop
	@Override
	protected float getGravityVelocity() {
		return 0.005F;
	}



	@Override
	public void onUpdate() {
		super.onUpdate();


		this.Shooter = (EntityLivingBase) getShooter();
	
		this.noClip = true;
		
		//this.attackEntitiesInList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox()));
		


		//wave will last for 5 seconds
		if (this.ticksExisted > 30){
			setDead();
		}


	}


	/**
	 * Attacks all entities inside this list, dealing 5 hearts of damage.
	 */
	private void attackEntitiesInList(List par1List)
	{
		for (int i = 0; i < par1List.size(); ++i)
		{
			Entity entity = (Entity)par1List.get(i);

			if (entity instanceof EntityLivingBase && entity != this.Shooter)
			{
				entity.attackEntityFrom(DamageSource.causeMobDamage(this.Shooter), this.Dmg);
			} 
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (mop.entityHit instanceof EntityPlayer){
			mop.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.Shooter), this.Dmg);
			mop.entityHit.setFire(5);
		}
		setDead();
	}


	
}
