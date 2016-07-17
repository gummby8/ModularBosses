package com.Splosions.ModularBosses.dimensions;



import com.Splosions.ModularBosses.dimensions.BossDimension.BossDimension;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class TestBiomesRegistry
{
	public static BiomeGenBase BossBiome;
	public static int idBossBiome = 140;
	
	public static void init()
	{
		BossBiome = new BossDimension(idBossBiome);
	}
}
