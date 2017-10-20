package com.Splosions.ModularBosses.blocks.tileentity;

import com.Splosions.ModularBosses.blocks.FluidTempWormBlood;
import com.Splosions.ModularBosses.blocks.ModFluids;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.BlockFluidBase;

public class TileEntityTempWormAcid extends TileEntity implements IUpdatePlayerListBox {

	public int ticksExisted;


	@Override
	public void update() {
		if (Integer.parseInt(this.worldObj.getBlockState(this.pos).getValue(FluidTempWormBlood.LEVEL).toString()) != 0){
		this.worldObj.removeTileEntity(this.pos);
		return;
		}
		
		if (this.ticksExisted > 100 && !this.worldObj.isRemote) {
			//System.out.println("Killing it - " + ticksExisted);	
			IBlockState state = ModFluids.fluidTempWormAcid.getBlock().getDefaultState().withProperty(BlockFluidBase.LEVEL, 0);
			this.worldObj.setBlockState(this.pos, state.withProperty(BlockFluidBase.LEVEL, 1),2);
			this.worldObj.scheduleUpdate(this.pos, this.worldObj.getBlockState(this.pos).getBlock(), 2);		

		}

	
		
		
		ticksExisted++;
	}

	

}
