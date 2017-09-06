package com.Splosions.ModularBosses.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FluidWormAcid extends BlockFluidClassic{

	public FluidWormAcid(Fluid fluid, Material material) {
		super(fluid, Material.water);
		this.lightOpacity = 0;
		this.lightValue = 15;
		this.setMaxScaledLight(0);
	}
 

    /**
     * Called When an Entity Collided with the Block
     */
	@Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.4D;
        entityIn.motionZ *= 0.4D;
		if (entityIn instanceof EntityPlayer && entityIn.ticksExisted % 20 == (20 - 1)){
			entityIn.attackEntityFrom(DamageSource.wither, 5);

		}
		
    }
	
	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

}
