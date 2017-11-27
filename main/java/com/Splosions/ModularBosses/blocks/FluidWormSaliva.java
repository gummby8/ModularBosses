package com.Splosions.ModularBosses.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FluidWormSaliva extends BlockFluidClassic{

	public FluidWormSaliva(Fluid fluid, Material material) {
		super(fluid, Material.WATER);
		// limits fluid spread to 4 blocks
		//quantaPerBlock = 4;
		this.lightOpacity = 0;
		this.lightValue = 7;
		this.setMaxScaledLight(0);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	
    /**
     * Convert the given metadata into a BlockState for this Block
     */
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(LEVEL, Integer.valueOf(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
	@Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(LEVEL)).intValue();
    }
}
