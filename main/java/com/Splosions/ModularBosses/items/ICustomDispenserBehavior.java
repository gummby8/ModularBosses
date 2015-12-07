package com.Splosions.ModularBosses.items;

import net.minecraft.dispenser.IBehaviorDispenseItem;

/**
 * 
 * Allows automating registration of custom dispenser behaviors for items
 *
 */
public interface ICustomDispenserBehavior {

	/**
	 * Return a new instance of the correct dispenser behavior class to use for this item
	 */
	IBehaviorDispenseItem getNewDispenserBehavior();

}