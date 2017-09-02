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

public class FluidWormSaliva extends BlockFluidClassic{

	public FluidWormSaliva(Fluid fluid, Material material) {
		super(fluid, Material.water);
		// limits fluid spread to 4 blocks
		//quantaPerBlock = 4;
	}
}
