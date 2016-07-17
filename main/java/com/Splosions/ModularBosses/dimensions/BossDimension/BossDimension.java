package com.Splosions.ModularBosses.dimensions.BossDimension;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BossDimension extends BiomeGenBase
{
	public BossDimension(int id)
	{
		super(id, true);
		setBiomeName("BossBiome");
		this.setHeight(height_LowPlains);
		this.setTemperatureRainfall(0.5F, 0.3F);
		this.enableRain = false;
		this.enableSnow = false;
        this.theBiomeDecorator.generateLakes = false;
		
		this.spawnableMonsterList.clear();
	    this.spawnableCreatureList.clear();
	    this.spawnableWaterCreatureList.clear();
	    this.spawnableCaveCreatureList.clear();
	    
        this.topBlock = Blocks.air.getDefaultState();
        this.fillerBlock = Blocks.air.getDefaultState();
	}
}
