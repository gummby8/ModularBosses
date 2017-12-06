package com.Splosions.ModularBosses.dimensions;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class BossChunkProvider implements IChunkProvider
{
    private World worldObj;

    public BossChunkProvider(World world, long dimID, boolean mapFeaturesEnabled)
    {
    	this.worldObj = world;
    }
    
	


	@Override
	public Chunk provideChunk(int x, int z) 
	{
		Chunk chunk = new Chunk(worldObj, new ChunkPrimer(), x, z);
		chunk.generateSkylightMap();
		return chunk;
	}



	@Override
	public String makeString() 
	{
		return "RandomLevelSource";
	}



	@Override
	public Chunk getLoadedChunk(int x, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tick() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChunkGeneratedAt(int x, int z) {
		// TODO Auto-generated method stub
		return false;
	}
}
