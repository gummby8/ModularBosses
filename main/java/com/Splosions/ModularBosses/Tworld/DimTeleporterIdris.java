package com.Splosions.ModularBosses.Tworld;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import upcraftlp.shadowcreatures.init.ShadowDimension;

public class DimTeleporterIdris extends Teleporter {

	public DimTeleporterIdris(WorldServer worldIn) {
		super(worldIn);
	}
		
	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
			BlockPos posNew = MinecraftServer.getServer().worldServerForDimension(ShadowDimension.getId()).getTopSolidOrLiquidBlock(entityIn.getPosition());
			entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
			entityIn.setLocationAndAngles(posNew.getX(), posNew.getY(), posNew.getZ(), rotationYaw, entityIn.rotationPitch);
	}
	
	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		return true;
	}

}
