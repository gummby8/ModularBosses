package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityFlameThrower extends EntityMobThrowable {

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
	}
	
	public EntityFlameThrower(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide,float Size1,float Size2,int dmg) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
		this.Dmg = dmg;
	}





	//set to 0 do have 0 spell drop
	@Override
	protected float getGravityVelocity() {
		return 0F;
	}



	@Override
	public void onUpdate() {
		super.onUpdate();

	
		this.noClip = true;

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

			if (entity instanceof EntityLivingBase && entity != getThrower())
			{


				entity.attackEntityFrom(DamageSource.causeMobDamage(getThrower()), this.Dmg);

				System.out.println(entity);
			} 
		}
	}



	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit instanceof EntityPlayer){
			result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(getThrower()), this.Dmg);
			result.entityHit.setFire(5);
		}
		setDead();
		
	}


	
}
