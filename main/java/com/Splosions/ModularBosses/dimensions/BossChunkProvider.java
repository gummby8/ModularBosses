package com.Splosions.ModularBosses.dimensions;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class BossChunkProvider implements IChunkProvider, IChunkGenerator
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




	@Override
	public Chunk generateChunk(int x, int z) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void populate(int x, int z) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		// TODO Auto-generated method stub
		return false;
	}
}
