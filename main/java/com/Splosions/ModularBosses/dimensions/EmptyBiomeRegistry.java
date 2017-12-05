package com.Splosions.ModularBosses.dimensions;



import static net.minecraftforge.common.BiomeDictionary.Type.WET;

import com.Splosions.ModularBosses.dimensions.BossDimension.BossBiomeBase;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class EmptyBiomeRegistry
{
	//public static BiomeGenBase BossBiome;
	public static BossBiomeBase BossBiome;
	public static int idBossBiome = 140;
	
	public static void init()
	{
		BossBiome = new BossBiomeBase(idBossBiome);
	}
	
	public static void registerBiomes() {
		BossBiome = reigsterBiome(new BossBiomeBase(idBossBiome), BiomeManager.BiomeType.ICY, 10000, WET);
	}

	private static <T extends BiomeGenBase> T reigsterBiome(T biome, BiomeManager.BiomeType biomeType, int weight, BiomeDictionary.Type... types) {
		BiomeDictionary.registerBiomeType(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));

		return biome;
	}
}
