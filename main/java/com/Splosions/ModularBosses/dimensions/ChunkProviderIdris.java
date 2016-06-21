package com.Splosions.ModularBosses.dimensions;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderIdris implements IChunkProvider {

	private World worldObj;
	
	public ChunkProviderIdris(World worldIn, long dimId)
	{
		this.worldObj = worldIn;
	}
	
	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		Chunk chunk = new Chunk(worldObj, new ChunkPrimer(), x, z);
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public Chunk provideChunk(BlockPos blockPosIn) {
		return provideChunk(blockPosIn.getX(), blockPosIn.getZ());
	}

	@Override
	public void populate(IChunkProvider chunkprovider, int x, int z) {
		//TODO this.
	}

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate progressCallback) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "RandomLevelSource";
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
		return true;
	}

	@Override
	public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
		return new ArrayList<SpawnListEntry>();
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
		//TODO IMPLEMENT STRUCTURES		
	}

	@Override
	public void saveExtraData() {}


}
