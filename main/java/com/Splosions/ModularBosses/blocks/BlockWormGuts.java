package com.Splosions.ModularBosses.blocks;

import com.Splosions.ModularBosses.MBCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.Explosion;

public class BlockWormGuts extends Block {

	public BlockWormGuts(Material material) {
			super(material);
			setHardness(-1.0F);
			setHarvestLevel("pickaxe", 100);
			setStepSound(SLIME_SOUND);
			setCreativeTab(MBCreativeTabs.tabBlocks);
		}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return false;
	}

}
