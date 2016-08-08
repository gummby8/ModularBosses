package com.Splosions.ModularBosses.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class GasWormGas extends BlockFluidClassic{

	public GasWormGas(Fluid fluid, Material material) {
		super(fluid, material);
		// TODO Auto-generated constructor stub
	}


    /**
     * Called When an Entity Collided with the Block
     */
	@Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityPlayer && entityIn.ticksExisted % 20 == (20 - 1)){
			entityIn.attackEntityFrom(DamageSource.wither, 5);

		}
    }
	

}
