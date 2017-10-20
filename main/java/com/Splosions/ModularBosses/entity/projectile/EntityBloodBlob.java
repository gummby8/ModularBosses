package com.Splosions.ModularBosses.entity.projectile;

import com.Splosions.ModularBosses.blocks.ModFluids;
import com.Splosions.ModularBosses.entity.EntityGolem;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBloodBlob extends EntityMobThrowable implements IEntityAdditionalSpawnData
{

	private EntityGolem shooter;
	public ResourceLocation textureLoc;
	
	public EntityBloodBlob(World world) {
		super(world);
	}
	
	public EntityBloodBlob(World world, EntityLivingBase shooter, BlockPos pos, float velocity, float MotionY) {
		super(world,shooter,pos,velocity,MotionY);
	}
	@Override
	protected void onImpact(MovingObjectPosition mop) {
		
		if (!worldObj.isRemote && mop.entityHit == null) {
			this.worldObj.setBlockState(this.getPosition(), ModFluids.fluidTempWormBlood.getBlock().getDefaultState());
			setDead();
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {

		
	}


}