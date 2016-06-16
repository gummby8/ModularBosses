package com.Splosions.ModularBosses.Tworld;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import upcraftlp.shadowcreatures.init.GhostDimension;

public class WorldProviderGhost extends WorldProvider {

	//One of them MUST be 0x00, otherwise the sky color is white.
	private int skyColorR = 0x00;
	private int skyColorG = 0x00;
	private int skyColorB = 0x00;
	
	@Override
	protected void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManager(this.getSeed(), WorldType.DEFAULT, "");
		this.setDimension(GhostDimension.getId());
		this.setAllowedSpawnTypes(true, false);
		this.hasNoSky = true;
	}
	
	@Override
	public String getDimensionName() {
		return GhostDimension.name;
	}
	
	@Override
	public boolean getHasNoSky() {
		return true;
	}

	@Override
	public String getInternalNameSuffix() {
		return "ghostDim";
	}
	
	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
	@Override
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
		double r = (double) this.skyColorR;
		double g = (double) this.skyColorG;
		double b = (double) this.skyColorB;
		return new Vec3(r, g, b);
	}
	
	@Override
	public boolean isSkyColored() {
		return true;
	}
	
	@Override
	public float getCloudHeight() {
		return 70.0f;
	}
	
	@Override
	public boolean canDoRainSnowIce(Chunk chunk) {
		return false;
	}
	
	@Override
	public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
		return false;
	}
	
	@Override
	public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
		return new Vec3(0x00, 0x00, 0x00);
	}
	
	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}
	
	@Override
	public boolean shouldMapSpin(String entity, double x, double y, double z) {
		return true;
	}
}
