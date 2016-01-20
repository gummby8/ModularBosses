package com.Splosions.ModularBosses.blocks;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * Interface to allow automation of TileEntitySpecialRenderer registration
 *
 */
public interface ISpecialRenderer {

	/**
	 * Called automatically for blocks to allow registration of their tile entity special renderer
	 * using {@link ClientRegistry#bindTileEntitySpecialRenderer}
	 */
	@SideOnly(Side.CLIENT)
	void registerSpecialRenderer();

}