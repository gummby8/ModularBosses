package com.Splosions.ModularBosses.dimensions;



import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ShadowConfig {

	public static boolean isDebug;
	
	private static int entityID;
	
	public static int idrisId;
	public static int idrisPlainsId;
	public static int ghostId;
	
	
	
	@SuppressWarnings("deprecation")
	public static boolean init(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		entityID = config.getInt("EntityID", "Creatures", EntityRegistry.findGlobalUniqueEntityId(), 0, 255, "Start ID for registering entities");
		ghostId = config.getInt("DimIdGhost", "Ghost Dimension", 32, 0, 16384, "Ghost Dimension ID");
		idrisId = config.getInt("DimIdIdris", "Idris", 31, 0, 16384, "Idris Dimension ID");
		idrisPlainsId = config.getInt("IdrisPlaindsID", "Idris", 80, 0, 16384, "Idris Plains Biome ID");
		
		
		isDebug = config.getBoolean("Debug Mod", Configuration.CATEGORY_GENERAL, false, "enable debug");
		if(isDebug) System.out.println("[ShadowCreatures] DEBUG MODE ENABLED!");
		config.save();
		return true;
	}
	
	public static int getEntityID()
	{
		return entityID++;
	}
}
