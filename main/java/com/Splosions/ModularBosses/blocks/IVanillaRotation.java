package com.Splosions.ModularBosses.blocks;



/** 
 * 
 * Allow automation of Block rotation types for blocks using a vanilla rotation pattern.
 *
 */
public interface IVanillaRotation {

	/**
	 * The vanilla rotation pattern used by this block
	 */
	BlockRotationData.Rotation getRotationPattern();

}