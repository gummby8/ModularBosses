package com.Splosions.ModularBosses.blocks;

import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * 
 * Interface for blocks that have a custom IStateMapper
 *
 */
public interface ICustomStateMapper {

	/**
	 * Return the {@link IStateMapper IStateMapper} to use for {@link net.minecraftforge.client.model.ModelLoader#setCustomStateMapper ModelLoader.setCustomStateMapper}
	 */
	@SideOnly(Side.CLIENT)
	IStateMapper getCustomStateMap();

}