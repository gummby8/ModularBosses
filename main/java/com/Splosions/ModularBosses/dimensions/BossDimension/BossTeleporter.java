package com.Splosions.ModularBosses.dimensions.BossDimension;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class BossTeleporter extends Teleporter {
	private final WorldServer worldServerInstance;

	public BossTeleporter(WorldServer world) {
		super(world);
		this.worldServerInstance = world;
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		//this.makePortal(entityIn);

		int i = MathHelper.floor_double(entityIn.posX);
		int j = MathHelper.floor_double(entityIn.posY) - 1;
		int k = MathHelper.floor_double(entityIn.posZ);
		byte b0 = 1;
		byte b1 = 0;



		entityIn.setLocationAndAngles((double) i, (double) j, (double) k, entityIn.rotationYaw, 0.0F);
		entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;

	}

	public boolean makePortal(Entity ent) {
		this.worldServerInstance.setBlockState(ent.getPosition().down(), Blocks.obsidian.getDefaultState());

		return true;
	}

}