package com.Splosions.ModularBosses.dimensions.BossDimension;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;

public class BossBiomeBase extends BiomeGenBase {
	
	public BossBiomeBase(int id) {
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
	
	@Override
    public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_)
    {

    }
}
