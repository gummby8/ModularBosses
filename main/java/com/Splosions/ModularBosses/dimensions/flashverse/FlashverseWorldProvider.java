package com.Splosions.ModularBosses.dimensions.flashverse;

import com.Splosions.ModularBosses.dimensions.TestBiomesRegistry;
import com.Splosions.ModularBosses.dimensions.TestDimensions;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class FlashverseWorldProvider extends WorldProvider
{
	@Override
	protected void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(TestBiomesRegistry.FlashversePlains, 0.0f);
		this.setAllowedSpawnTypes(false, false);
		this.dimensionId = TestDimensions.flashverseDimensionID;
		this.setDimension(TestDimensions.flashverseDimensionID);
		this.hasNoSky = false;
	}
	
	@Override
	public String getDimensionName()
	{
		return "Flashverse";
	}

	@Override
	public String getInternalNameSuffix()
	{
		return "_flashverse";
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
