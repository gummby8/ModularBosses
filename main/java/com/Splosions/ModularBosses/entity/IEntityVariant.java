package com.Splosions.ModularBosses.entity;



/**
 * 
 * For Entities with variants that can be set upon spawning with a custom spawn egg.
 *
 */
public interface IEntityVariant {

	/**
	 * Sets the Entity's sub-type, where type is often the ordinal value of an Enum set;
	 * in such cases, it's best to get the Enum type and pass that to another setter method.
	 * @param type	The damage value of the ItemStack, denoting the mob's type
	 */
	IEntityVariant setType(int type);

}