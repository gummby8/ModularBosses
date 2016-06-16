package com.Splosions.ModularBosses.Tworld;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import upcraftlp.shadowcreatures.init.ShadowBlocks;

public class WorldGen implements IWorldGenerator {
	
	
	private WorldGenerator gen_crystalore_end;
	private WorldGenerator gen_crystalore_default;
	
	public WorldGen()
	{
		this.gen_crystalore_end = new WorldGenMinable(ShadowBlocks.crystalore_end.getDefaultState(), 16, BlockHelper.forBlock(Blocks.end_stone));
		this.gen_crystalore_default = new WorldGenSingle(ShadowBlocks.crystalore.getDefaultState());
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkprovider)
	{
		switch (world.provider.getDimensionId())
		{
		case 0:
			this.runGenerator(this.gen_crystalore_default, world, random, chunkX, chunkZ, 7, 20, 64);
			break;
		
		case -1:
			
			break;
		
		case 1:
			this.runGenerator(this.gen_crystalore_end, world, random, chunkX, chunkZ, 8, 0, 256);
			break;
		}
	}
	
	
	
	private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight)
	{
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++){
			int x = chunkX * 16 + random.nextInt(16);
			int y = minHeight + random.nextInt(heightDiff);
			int z = chunkZ * 16 + random.nextInt(16);
			generator.generate(world,  random,  new BlockPos(x, y, z));
		}
	}
	
	
	
	
	
	
}
