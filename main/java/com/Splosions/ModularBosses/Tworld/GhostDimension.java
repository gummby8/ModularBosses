package com.Splosions.ModularBosses.Tworld;

import net.minecraftforge.common.DimensionManager;
import upcraftlp.shadowcreatures.world.WorldProviderGhost;

public class GhostDimension {

	private static int id = ShadowConfig.ghostId;
	public static String name = "Ghost Dimension";

	public static void init() {
		id = ShadowConfig.ghostId;
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
