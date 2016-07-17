package com.Splosions.ModularBosses.dimensions.BossDimension;

import java.util.List;
import java.util.Random;

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


public class BossTeleporter extends Teleporter
{
	public BossTeleporter(WorldServer world)
	{
		super(world);
	}
	
	@Override
	public void placeInPortal(Entity entity, float rotationYaw)
	{
		entity.motionX = entity.motionY = entity.motionZ = 0.0D;
		entity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, rotationYaw, entity.rotationPitch);
	}
	
	@Override
	public boolean placeInExistingPortal(Entity entity, float rotationYaw)
	{
		return true;
	}
}