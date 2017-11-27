
package com.Splosions.ModularBosses.blocks;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityPortalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockPortalBlock extends Block 
{

		
	public BlockPortalBlock(Material material) {
		super(material);
		setHardness(10.0F);
		setHarvestLevel("pickaxe", 2);
		setSoundType(SoundType.STONE);
		setCreativeTab(ModularBosses.tabBlocks);
		
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
		return new TileEntityPortalBlock();
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

	
	
	
}