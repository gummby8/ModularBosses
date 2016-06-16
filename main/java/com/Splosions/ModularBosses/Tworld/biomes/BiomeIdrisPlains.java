package upcraftlp.shadowcreatures.world.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import upcraftlp.shadowcreatures.init.ShadowConfig;

public class BiomeIdrisPlains extends BiomeGenBase{

	private static int id = ShadowConfig.idrisPlainsId;
	private static String name = "Idris Plains";
	private static int biomeColor = 0x14bd14;
	private int skyColor = 0x0000ff;
	private int waterColor = 0x00dfff;
	
	public BiomeIdrisPlains() {
		super(id, true);
		this.setBiomeName(name);
		this.setHeight(height_LowPlains);
		this.setDisableRain();
		this.topBlock = Blocks.grass.getDefaultState();
		this.setColor(biomeColor);
		this.fillerBlock = Blocks.dirt.getDefaultState();
		this.waterColorMultiplier = waterColor;
		this.enableSnow = false;
	}
	
	@Override
	public int getWaterColorMultiplier() {
		return this.waterColor;
	}
	
	@Override
	public boolean getEnableSnow() {
		return false;
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return this.color;
	}

	@Override
	public int getSkyColorByTemp(float p_76731_1_) {
		return this.skyColor;
	}

	public static void setId(int id) {
		BiomeIdrisPlains.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
}
