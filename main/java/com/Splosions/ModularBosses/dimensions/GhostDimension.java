package com.Splosions.ModularBosses.dimensions;

import net.minecraftforge.common.DimensionManager;


public class GhostDimension {

	//private static int id = ShadowConfig.ghostId;
	private static int id = 32;
	public static String name = "Ghost Dimension";

	public static void init() {
		//id = ShadowConfig.ghostId;
		id = 32;
		DimensionManager.registerProviderType(id, WorldProviderGhost.class, true);
		DimensionManager.registerDimension(id, id);
		

	}
	
	public static void setId(int id) {
		GhostDimension.id = id;
	}
	
	public static int getId() {
		return id;
	}

}
