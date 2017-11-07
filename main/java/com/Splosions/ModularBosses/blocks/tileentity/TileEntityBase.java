package com.Splosions.ModularBosses.blocks.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * 
 * No vanilla tile entity returns true from {@link TileEntity#shouldRefresh} unless the
 * block actually changes; this class simply preserves that behavior so saved data is
 * not erased by default. Override shouldRefresh if different behavior is needed.
 *
 */
public abstract class TileEntityBase extends TileEntity {
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return (oldState.getBlock() != newState.getBlock());
	}
}