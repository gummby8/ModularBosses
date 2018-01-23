package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityEnergyClaw extends EntityMobThrowable {

	private float Dmg;

	

	public EntityEnergyClaw(World world) {
		super(world);
		setSize(0.1F,0.1F);
		this.ignoreFrustumCheck = true;




	}

	public EntityEnergyClaw(World world, EntityLivingBase entity) {
		super(world, entity);
	}
	
	


	public EntityEnergyClaw(World world, double x, double y, double z) {
		super(world, x, y, z);
	}


	public EntityEnergyClaw(World world, EntityLivingBase shooter, float wobble, float FrontToBack, float YOffset, float SideToSide) {
		super(world, shooter, wobble, FrontToBack, YOffset, SideToSide);
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
	}
	
	public EntityEnergyClaw(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide,float Size1,float Size2,int scale, int dmg) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
		this.Dmg = dmg;
	}







	//set to 0 do have 0 spell drop
	@Override
	protected float getGravityVelocity() {
		return 0.000F;
	}



	@Override
	public void onUpdate() {
		super.onUpdate();


		
		

		//wave will last for 5 seconds
		if (this.ticksExisted > 100){
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
			} 
		}
	}




	@Override
	protected void onImpact(RayTraceResult result) {
        if (result != null)
        {
            if (result.entityHit != null)
            {
			result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(getThrower()), this.Dmg);
		}
        }
		setDead();
		
	}


	
}
