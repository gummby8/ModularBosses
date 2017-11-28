package com.Splosions.ModularBosses.entity.projectile;

import com.Splosions.ModularBosses.blocks.ModFluids;
import com.Splosions.ModularBosses.entity.EntityGolem;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
	public void onImpact(RayTraceResult result) {
		
		if (!world.isRemote && result.entityHit == null) {
			this.world.setBlockState(this.getPosition(), ModFluids.FLUID_TEMP_WORM_BLOOD.getBlock().getDefaultState());
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