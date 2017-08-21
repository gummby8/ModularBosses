package com.Splosions.ModularBosses.entity.projectile;

import com.Splosions.ModularBosses.entity.EntityGolem;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBoulder extends EntityMobThrowable implements IEntityAdditionalSpawnData
{

	private EntityGolem shooter;
	public ResourceLocation textureLoc;
	
	public EntityBoulder(World world) {
		super(world);
	}

	public EntityBoulder(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityBoulder(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityBoulder(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float Size1, float Size2) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
		this.shooter = (EntityGolem) shooter;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		
		if (mop.entityHit != null) {
			Potion potioneffect = (Potion.moveSlowdown);
			//System.out.println(mop.entityHit.getEntityName());
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			//((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(2, 120,4));
			
		}

		if (!worldObj.isRemote) {
			setDead();
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, this.shooter.textureLoc.toString());
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		textureLoc = new ResourceLocation(ByteBufUtils.readUTF8String(additionalData));
		
	}


}