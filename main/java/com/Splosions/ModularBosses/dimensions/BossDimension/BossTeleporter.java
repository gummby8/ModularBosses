package com.Splosions.ModularBosses.dimensions.BossDimension;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import com.google.common.collect.Lists;

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

		for (int l = -2; l <= 2; ++l) {
			for (int i1 = -2; i1 <= 2; ++i1) {
				for (int j1 = -1; j1 < 3; ++j1) {
					int k1 = i + i1 * b0 + l * b1;
					int l1 = j + j1;
					int i2 = k + i1 * b1 - l * b0;
					boolean flag = j1 < 0;
					//this.worldServerInstance.setBlockState(new BlockPos(k1, l1, i2), flag ? Blocks.obsidian.getDefaultState() : Blocks.air.getDefaultState());
				}
			}
		}

		entityIn.setLocationAndAngles((double) i, (double) j, (double) k, entityIn.rotationYaw, 0.0F);
		entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;

	}

	public boolean makePortal(Entity ent) {
		this.worldServerInstance.setBlockState(ent.getPosition().down(), Blocks.obsidian.getDefaultState());

		return true;
	}

}