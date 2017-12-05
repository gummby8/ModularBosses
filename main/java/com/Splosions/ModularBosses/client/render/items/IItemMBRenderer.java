package com.Splosions.ModularBosses.client.render.items;

import net.minecraft.item.ItemStack;

/**
 * indicates that this item should use 1.7 style render hook
 */
public interface IItemMBRenderer {
	/**
	 * Return if the passed ItemStack should use the renderhack
	 */
	default boolean shouldUseRenderHack(ItemStack stack){return true;};
	
	default boolean isModelBase(ItemStack stack){return true;};
}