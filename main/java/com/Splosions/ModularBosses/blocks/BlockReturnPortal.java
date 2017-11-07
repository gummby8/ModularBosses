
package com.Splosions.ModularBosses.blocks;

import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityReturnPortalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockReturnPortal extends Block 
{

		
	public BlockReturnPortal(Material material) {
		super(material);
		setHardness(10.0F);
		setBlockUnbreakable();
		setStepSound(soundTypeStone);
		setCreativeTab(MBCreativeTabs.tabBlocks);
		
	}
	
	
	public int getRenderBlockPass()
	{
	return -1;
	}	

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityReturnPortalBlock();
	}
	

	
	
	
}