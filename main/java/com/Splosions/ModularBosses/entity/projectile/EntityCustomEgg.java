package com.Splosions.ModularBosses.entity.projectile;

import com.Splosions.ModularBosses.entity.CustomEntityList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCustomEgg extends EntityMobThrowable
{

	private int dmg;

	public EntityCustomEgg(World world) {
		super(world);
	}

	public EntityCustomEgg(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityCustomEgg(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityCustomEgg(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float Size1, float Size2, int dmg) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
		this.dmg = dmg;
		
	}
	
	/**
	 * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
	 */
	public Entity spawnCreature(World world, int entityID, double x, double y, double z) {
		Entity entity = null;
		Class<? extends Entity> oclass = CustomEntityList.getClassFromID(entityID);
		if (CustomEntityList.entityEggs.containsKey(oclass)) {
			//oclass = EntityList.getClassFromID(EntityList.getIDFromString("mb.HeavyChorp"));
			entity = CustomEntityList.createEntity(oclass, world);
			if (entity instanceof EntityLiving) {
				EntityLiving entityliving = (EntityLiving) entity;
				entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
				entityliving.rotationYawHead = entityliving.rotationYaw;
				entityliving.renderYawOffset = entityliving.rotationYaw;
				world.spawnEntityInWorld(entity);
				entityliving.playLivingSound();
			} else {
				//entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
				entity.setPosition(x, y, z);
				world.spawnEntityInWorld(entity);
			}
		}
		return entity;
	}

	

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (!this.worldObj.isRemote){
			spawnCreature(worldObj, dmg, this.posX, this.posY, this.posZ);			
		}
		setDead();
	}


}