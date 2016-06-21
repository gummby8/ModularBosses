package com.Splosions.ModularBosses.dimensions;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class DimTeleporterGhost extends Teleporter {

	public DimTeleporterGhost(WorldServer worldIn) {
		super(worldIn);
	}
	
	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
		entityIn.setLocationAndAngles(entityIn.posX, entityIn.posY, entityIn.posZ, rotationYaw, entityIn.rotationPitch);
	}
	
	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		return true;
	}

}
