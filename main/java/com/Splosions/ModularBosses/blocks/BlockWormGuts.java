package com.Splosions.ModularBosses.blocks;

import com.Splosions.ModularBosses.MBCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockWormGuts extends Block {

	public BlockWormGuts(Material material) {
			super(material);
			setHardness(1000.0F);
			setHarvestLevel("pickaxe", 100);
			setStepSound(soundTypeStone);
			setCreativeTab(MBCreativeTabs.tabBlocks);

		}

}
