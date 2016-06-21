package com.Splosions.ModularBosses.dimensions;

import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;


public class WorldProviderIdris extends WorldProvider {

	@Override
	public String getDimensionName() {
		return ShadowDimension.name;
	}

	@Override
	public String getInternalNameSuffix() {
		return "shadowDim";
	}
	
	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(ShadowBiomes.idris_plains, 0.0f);
		//TODO UNFINISHED
		//this.worldChunkMgr = new WorldChunkManager(this.getSeed(), WorldType.DEFAULT, "");
		this.setDimension(ShadowDimension.getId());
		this.setAllowedSpawnTypes(true, true);
		this.hasNoSky = false;
	}
	
	@Override
	public BiomeGenBase getBiomeGenForCoords(BlockPos pos) {
		return ShadowDimension.getRandomBiome();
	}
	
	
	@Override
	public boolean isSurfaceWorld() {
		return true;
	}
	

}
