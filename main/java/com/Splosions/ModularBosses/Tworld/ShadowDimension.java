package upcraftlp.shadowcreatures.init;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import upcraftlp.shadowcreatures.world.WorldProviderIdris;

public class ShadowDimension {

	private static int id = ShadowConfig.idrisId;
	public static String name = "Idris";
	private static ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();

	public static void init() {
		id = ShadowConfig.idrisId;
		biomes.add(ShadowBiomes.idris_plains);
		biomes.add(ShadowBiomes.idris_plains);
		//biomes.add(BiomeGenBase.extremeHillsEdge);
		DimensionManager.registerProviderType(id, WorldProviderIdris.class, false);
		DimensionManager.registerDimension(id, id);
		

	}
	
	public static void setId(int id) {
		ShadowDimension.id = id;
	}
	
	public static int getId() {
		return id;
	}
	
	public static BiomeGenBase getBiome(int index)
	{
		return biomes.get(index);
	}
	
	public static BiomeGenBase getRandomBiome()
	{
		Random random = new Random();
		int index = random.nextInt(biomes.size());
		return getBiome(index);
	}

}
