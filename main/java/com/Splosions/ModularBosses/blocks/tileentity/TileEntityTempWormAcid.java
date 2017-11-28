package com.Splosions.ModularBosses.blocks.tileentity;

import com.Splosions.ModularBosses.blocks.FluidTempWormBlood;
import com.Splosions.ModularBosses.blocks.ModFluids;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.BlockFluidBase;

public class TileEntityTempWormAcid extends TileEntity implements ITickable {

	public int ticksExisted;


	@Override
	public void update() {
		if (Integer.parseInt(this.world.getBlockState(this.pos).getValue(FluidTempWormBlood.LEVEL).toString()) != 0){
		this.world.removeTileEntity(this.pos);
		return;
		}
		
		if (this.ticksExisted > 100 && !this.world.isRemote) {
			//System.out.println("Killing it - " + ticksExisted);	
			IBlockState state = ModFluids.FLUID_TEMP_WORM_ACID.getBlock().getDefaultState().withProperty(BlockFluidBase.LEVEL, 0);
			this.world.setBlockState(this.pos, state.withProperty(BlockFluidBase.LEVEL, 1),2);
			this.world.scheduleUpdate(this.pos, this.world.getBlockState(this.pos).getBlock(), 2);		

		}

	
		
		
		ticksExisted++;
	}

	

}
