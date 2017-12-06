package com.Splosions.ModularBosses.dimensions;

import com.Splosions.ModularBosses.Config;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class MBBossWorldProvider extends WorldProvider
{

	

	
    /**
     * Returns a new chunk provider which generates chunks for this world
     */
	@Override
    public IChunkGenerator createChunkGenerator()
    {
        return new BossChunkProvider(world, world.getSeed(), true);
    }
	
	
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
	
	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		return 0;
		
	}
	


	@Override
	public DimensionType getDimensionType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
