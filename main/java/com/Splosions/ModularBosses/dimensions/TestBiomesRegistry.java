package com.Splosions.ModularBosses.dimensions;



import com.Splosions.ModularBosses.dimensions.BossDimension.BossBiomeBase;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class TestBiomesRegistry
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
		System.out.println("REGESTERING BIOME");
	}

	private static <T extends BiomeGenBase> T reigsterBiome(T biome, BiomeManager.BiomeType biomeType, int weight, BiomeDictionary.Type... types) {
		BiomeDictionary.registerBiomeType(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));

		return biome;
	}
}
