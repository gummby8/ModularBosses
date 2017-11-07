package com.Splosions.ModularBosses.blocks;


import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityChunkLoader;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockChunkLoader extends BlockContainer implements ITileEntityProvider
{

	public BlockChunkLoader(Material materialIn) 
	{
		super(Material.IRON);
		setCreativeTab(ModularBosses.tabBlocks);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityChunkLoader();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}


}