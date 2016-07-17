package com.Splosions.ModularBosses.dimensions;



import com.Splosions.ModularBosses.dimensions.flashverse.FlashverseWorldProvider;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public class TestDimensions
{
	public static int flashverseDimensionID = -3;
	
	public static void init()
	{		
		DimensionManager.registerProviderType(flashverseDimensionID, FlashverseWorldProvider.class, false);
		DimensionManager.registerDimension(flashverseDimensionID, flashverseDimensionID);
	}
}
