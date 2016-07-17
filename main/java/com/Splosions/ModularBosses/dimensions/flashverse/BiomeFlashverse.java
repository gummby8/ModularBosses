package com.Splosions.ModularBosses.dimensions.flashverse;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeFlashverse extends BiomeGenBase
{
	public BiomeFlashverse(int id)
	{
		super(id, true);
		setBiomeName("FlashversePlains");
		this.setHeight(height_LowPlains);
		this.setTemperatureRainfall(0.5F, 0.3F);
		this.enableRain = true;
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
