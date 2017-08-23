package com.Splosions.ModularBosses.dimensions;



import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.dimensions.BossDimension.BossWorldProvider;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public class TestDimensions
{
	public static int BossDimensionID = Config.bossDimension;
	
	public static void init()
	{		
		DimensionManager.registerProviderType(BossDimensionID, BossWorldProvider.class, false);
		DimensionManager.registerDimension(BossDimensionID, BossDimensionID);
	}
}
