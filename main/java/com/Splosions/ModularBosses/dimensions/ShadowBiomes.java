package com.Splosions.ModularBosses.dimensions;

import com.Splosions.ModularBosses.dimensions.biomes.BiomeIdrisPlains;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;


public class ShadowBiomes {

	public static BiomeGenBase idris_plains;
	
	public static void create()
	{
		idris_plains = new BiomeIdrisPlains();
	}
	
	public static void register()
	{
		//NOT needed for custom DImension!
		//registerBiome(idris_plains, BiomeType.COOL, 5);
	}
	
	private static void registerBiome(BiomeGenBase biome, BiomeType type, int weight)
	{
		BiomeManager.addBiome(type, new BiomeEntry(biome, weight));
	}
}
