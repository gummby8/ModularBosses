package com.Splosions.ModularBosses.blocks;

import com.Splosions.ModularBosses.ModularBosses;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.Explosion;

public class BlockWormGuts extends Block {

	public BlockWormGuts(Material material) {
			super(material);
			setHardness(-1.0F);
			setHarvestLevel("pickaxe", 100);
			setSoundType(SoundType.SLIME);
			setCreativeTab(ModularBosses.tabBlocks);
		}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return false;
	}

}
