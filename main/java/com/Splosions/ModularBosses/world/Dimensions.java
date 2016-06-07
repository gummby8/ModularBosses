package com.Splosions.ModularBosses.world;

import com.Splosions.ModularBosses.ModularBosses;

import net.minecraftforge.common.DimensionManager;

public class Dimensions {
	public static int dimensionID = 85;
	public static int backupdimensionID = -85;
	public static int dimensionProviderID = 85;

	public static void registerDimensionSndWorm(){
		// dimension provider
					DimensionManager.registerProviderType(dimensionProviderID, WorldProviderTwilightForest.class, false);
	}
	
	
	
	public static void createDimensionSandWorm() {
		// register dimension with Forge
		if (!DimensionManager.isDimensionRegistered(dimensionID)) {
			DimensionManager.registerDimension(dimensionID, dimensionProviderID);
		} else {
			ModularBosses.logger.warn(
					"[TwilightForest] Twilight Forest detected that the configured dimension id '%d' is being used.  Using backup ID.  It is recommended that you configure this mod to use a unique dimension ID.",
					dimensionID);
			DimensionManager.registerDimension(backupdimensionID, dimensionProviderID);
			dimensionID = backupdimensionID;
		}
	}

	/**
	 * Change what dimension ID the Twilight Forest is. This is called when we
	 * connect to a server that has a different dimensionID set.
	 */
	public static void setDimensionID(int dim) {
		if (dimensionID != dim) {
			ModularBosses.logger.info(
					"[TwilightForest] Server has a different dimension ID (%d) for the Twilight Forest.  Changing this on the client.  This change will not be saved.",
					dim);

			DimensionManager.unregisterDimension(dimensionID);
			dimensionID = dim;
			DimensionManager.registerDimension(dimensionID, dimensionProviderID);
		}
	}
}
