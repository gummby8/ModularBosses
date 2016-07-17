package com.Splosions.ModularBosses.dimensions.BossDimension;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;

public class BossChunkProvider implements IChunkProvider
{
    private World worldObj;

    public BossChunkProvider(World world, long dimID, boolean mapFeaturesEnabled)
    {
    	this.worldObj = world;
    }
    
	@Override
	public boolean chunkExists(int x, int z)
	{
		return true;
	}

	
//	@Override
//	public Chunk provideChunk(int x, int z)
//	{
//        ChunkPrimer chunkprimer = new ChunkPrimer();
//
//        for (int i = 0; i < this.cachedBlockIDs.length; ++i)
//        {
//            IBlockState iblockstate = this.cachedBlockIDs[i];
//
//            if (iblockstate != null)
//            {
//                for (int j = 0; j < 16; ++j)
//                {
//                    for (int k = 0; k < 16; ++k)
//                    {
//                        chunkprimer.setBlockState(j, i, k, iblockstate);
//                    }
//                }
//            }
//        }
//
//        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
//        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, x * 16, z * 16, 16, 16);
//        byte[] abyte = chunk.getBiomeArray();
//
//        for (int l = 0; l < abyte.length; ++l)
//        {
//            abyte[l] = (byte)abiomegenbase[l].biomeID;
//        }
//
//        chunk.generateSkylightMap();
//        return chunk;
//	}

	@Override
	public Chunk provideChunk(int x, int z) 
	{
		Chunk chunk = new Chunk(worldObj, new ChunkPrimer(), x, z);
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public Chunk provideChunk(BlockPos pos) 
	{
		return provideChunk(pos.getX(), pos.getZ());
	}

	@Override
	public void populate(IChunkProvider icp, int x, int z) {}
	@Override
	public void recreateStructures(Chunk ch, int x, int z) {}
	@Override
	public void saveExtraData() {}


	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) 
	{
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() 
	{
		return false;
	}

	@Override
	public boolean canSave() 
	{
		return true;
	}

	@Override
	public String makeString() 
	{
		return "RandomLevelSource";
	}

	@Override
	public int getLoadedChunkCount() 
	{
		return 0;
	}

	@Override
	public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) 
	{
		return true;
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_) 
	{
		return null;
	}



	@Override
	public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
		// TODO Auto-generated method stub
		return null;
	}
}
