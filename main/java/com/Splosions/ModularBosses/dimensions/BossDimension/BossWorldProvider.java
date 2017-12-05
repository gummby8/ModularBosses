package com.Splosions.ModularBosses.dimensions.BossDimension;

import com.Splosions.ModularBosses.dimensions.EmptyBiomeRegistry;
import com.Splosions.ModularBosses.dimensions.BossDimension;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class BossWorldProvider extends WorldProvider
{

	
	@Override
	protected void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(EmptyBiomeRegistry.BossBiome, 0.0f);
		this.setAllowedSpawnTypes(false, false);
		this.dimensionId = BossDimension.BossDimensionID;
		this.setDimension(BossDimension.BossDimensionID);
		this.hasNoSky = false;
	}
	
	@Override
    public WorldChunkManager getWorldChunkManager()
    {
        return this.worldChunkMgr;
    }
	
    /**
     * Returns a new chunk provider which generates chunks for this world
     */
	@Override
    public IChunkProvider createChunkGenerator()
    {
        return new BossChunkProvider(worldObj, worldObj.getSeed(), true);
    }
	
	@Override
	public String getDimensionName()
	{
		return "BossDimension";
	}

	@Override
	public String getInternalNameSuffix()
	{
		return "_BossDimension";
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
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "Leaving your universe";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getDepartMessage()
	{
		return "Returning to your universe";
	}
	
	
}
