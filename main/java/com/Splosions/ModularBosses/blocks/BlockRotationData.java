
package com.Splosions.ModularBosses.blocks;

import java.util.HashMap;
import java.util.Map;

import com.Splosions.ModularBosses.ModularBosses;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;


public class BlockRotationData
{
	/** Valid rotation types. Each type is handled like vanilla blocks of this kind. */
	public static enum Rotation {
		/** 0 - north/south, 1 - east/west */
		ANVIL,
		/**
		 * 0x8 flags top, for which 0x1 flags the hinge direction;
		 * Facings (for bottom only): 0 - west, 1 - north, 2 - east, 3 - south, 0x4 flags door as open
		 */
		DOOR,
		/** Facings: 0 - south, 1 - west, 2 - north, 3 - east; e.g. beds, pumpkins, tripwire hooks */
		GENERIC,
		/**
		 * Most containers (chests, furnaces) use this, as well as pistons, ladders, and other things.
		 * Facings: 2 - north, 3 - south, 4 - west, 5 - east [for ladders and signs, they are attached to that side of the block]
		 */
		PISTON_CONTAINER,
		QUARTZ,
		RAIL,
		REPEATER,
		/**
		 * Marks the direction in which text / banners show. Increments are in 1/16 of a
		 * full circle, starting from south and moving clockwise as if looking at a compass.
		 * E.g., 0 is south, 1 is south-southwest, 2 is southwest, all the way up to 16 which is south-southeast
		 */
		SIGNPOST,
		SKULL,
		/** Ascends to the: 0 - east, 1 - west, 2 - south, 3 - north; 0x4 flags inverted stairs */
		STAIRS,
		/**
		 * Attached to wall: 0 - south, 1 - north, 2 - east, 3 - west
		 * 0x4 flags trapdoor as open, 0x8 flags trapdoor as being in top half of block
		 */
		TRAPDOOR,
		/** Side of block to which vine is anchored: 1 - south, 2 - west, 4 - north, 8 - east */
		VINE,
		/**
		 * Facings: 1 - east, 2 - west, 3 - south, 4 - north
		 * (button only: 0 - down, 5 - up)
		 */
		WALL_MOUNTED,
		/**
		 * Facings: 1 - east, 2 - west, 3 - south, 4 - north,
		 * 5 - north/south ground, 6 - east/west ground,
		 * 7 - north/south ceiling, 0 - east/west ceiling
		 * 0x8 flags power
		 */
		LEVER,
		/**
		 * 0-3 - wood type, 0x4 - east/west, 0x8 north/south;
		 * if neither 0x4 nor 0x8 are set, wood is up/down; if both are set, wood is all bark
		 */
		WOOD
	};

	/** A mapping of blocks to rotation type for handling rotation. Allows custom blocks to be added. */
	private static final Map<Block, Rotation> blockRotationData = new HashMap<Block, Rotation>();

	/**
	 * Returns the rotation type for the block given, or null if no type is registered
	 */
	public static final Rotation getBlockRotationType(Block block) {
		return blockRotationData.get(block);
	}

	/**
	 * Maps a block to a specified rotation type. Allows custom blocks to rotate with structure.
	 * @param block a valid block
	 * @param rotationType types predefined by enumerated type ROTATION
	 * @return false if a rotation type has already been specified for the given block
	 */
	public static final boolean registerCustomBlockRotation(Block block, Rotation rotationType) {
		return registerCustomBlockRotation(block, rotationType, false);
	}

	/**
	 * Maps a block to a specified rotation type. Allows custom blocks to rotate with structure.
	 * @param block a valid block
	 * @param rotationType types predefined by enumerated type ROTATION
	 * @param override if true, will override the previously set rotation data for specified block
	 * @return false if a rotation type has already been specified for the given block
	 */
	public static final boolean registerCustomBlockRotation(Block block, Rotation rotationType, boolean override) {
		if (blockRotationData.containsKey(block)) {
			ModularBosses.logger.warn("Block " + block + " already has a rotation type." + (override ? " Overriding previous data." : ""));
			if (override) {
				blockRotationData.remove(block);
			} else {
				return false;
			}
		}

		blockRotationData.put(block, rotationType);

		return true;
	}

	
}