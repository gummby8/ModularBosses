package com.Splosions.ModularBosses.client.entity;


import java.util.logging.Level;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.client.entity.projectile.EntityFlameThrower;
import com.Splosions.ModularBosses.client.models.entity.ModelChorpChorp;
import com.Splosions.ModularBosses.client.models.entity.ModelHeavyChorp;
import com.Splosions.ModularBosses.client.models.entity.ModelParagon;
import com.Splosions.ModularBosses.client.models.entity.ModelSkull;
import com.Splosions.ModularBosses.client.models.entity.ModelTatters;
import com.Splosions.ModularBosses.client.models.entity.ModelTattersHead;
import com.Splosions.ModularBosses.client.render.entity.RenderCartographer;
import com.Splosions.ModularBosses.client.render.entity.RenderChorpChorp;
import com.Splosions.ModularBosses.client.render.entity.RenderCustomFallingBlock;
import com.Splosions.ModularBosses.client.render.entity.RenderHeavyChorp;
import com.Splosions.ModularBosses.client.render.entity.RenderParagon;
import com.Splosions.ModularBosses.client.render.entity.RenderSkull;
import com.Splosions.ModularBosses.client.render.entity.RenderTatters;
import com.Splosions.ModularBosses.client.render.entity.RenderTattersHead;
import com.Splosions.ModularBosses.client.render.entity.RenderTeliportBiped;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderFlameThrower;
import com.Splosions.ModularBosses.items.ModularBossesItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ModularBossesEntities
{
	/** Spawn rates */


	

	/**
	 * Initializes entity spawn rates 
	
	public static void init(Configuration config) {
		// SPAWN RATES
	}
	 */
	
	
	/**
	 * Registers all entities, entity eggs, and adds spawns
	 */
	public static void init() {
		registerEntities();
		addSpawns();
	}

	private static void registerEntities() {
		int modEntityIndex = 0;
		//Effect Entities
		EntityRegistry.registerModEntity(EntityChorpSlimeBlob.class, "slimeblob", ++modEntityIndex, ModularBosses.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityFlameThrower.class, "Flame Thrower", ++modEntityIndex, ModularBosses.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityCustomFallingBlock.class, "Falling Block", ++modEntityIndex, ModularBosses.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntityBlueWave.class, "Blue Wave", ++modEntityIndex, ModularBossesMain.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntitySpiritShard.class, "Spirit Shard", ++modEntityIndex, ModularBossesMain.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntityBlackHole.class, "Black Hole", ++modEntityIndex, ModularBossesMain.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntityFlameShot.class, "Flame SHot", ++modEntityIndex, ModularBossesMain.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityCartographer.class, "Cartographer", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityTeleportBiped.class, "Teleport Model", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
	
		
		// MOBS and egg colors
		//EntityRegistry.registerGlobalEntityID(EntityEyeTest.class, "Eye Test", ++modEntityIndex);
		//CustomEntityList.addMapping(EntityEyeTest.class, "Eye Test", 0x3c768c, 0xb50000);
		
		EntityRegistry.registerModEntity(EntitySkull.class, "Skull", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntitySkull.class, "Skull", 0xFFFBAF, 0x000000);
		
		//EntityRegistry.registerGlobalEntityID(EntityLavaBlob.class, "Lava Blob", ++modEntityIndex);
		//CustomEntityList.addMapping(EntityLavaBlob.class, "Lava Blob", 0xF7FF00, 0xFF0000);
		
		//EntityRegistry.registerGlobalEntityID(EntityTestHead.class, "Test Head", ++modEntityIndex);
		//CustomEntityList.addMapping(EntityTestHead.class, "Test Head", 0xF7FF00, 0xFF0000);
		
		EntityRegistry.registerModEntity(EntityChorpChorp.class, "Chorp Chorp", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityChorpChorp.class, "Chorp Chorp", 0x3F5A8C, 0xFFFFFF);
		
		
		
		EntityRegistry.registerModEntity(EntityHeavyChorp.class, "HeavyChorp", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityHeavyChorp.class, "HeavyChorp", 0x8C713F, 0xFFFFFF);
		
		//EntityRegistry.registerGlobalEntityID(EntitySenseiSteve.class, "SenseiSteve", ++modEntityIndex);
		//CustomEntityList.addMapping(EntitySenseiSteve.class, "SenseiSteve", 0x3c768c, 0x000000);
		
		//EntityRegistry.registerGlobalEntityID(EntityTestDummy.class, "Test Dummy", ++modEntityIndex);
		//CustomEntityList.addMapping(EntityTestDummy.class, "Test Dummy", 0xF2FF00, 0x000000);
		
		EntityRegistry.registerModEntity(EntityParagon.class, "Paragon", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityParagon.class, "Paragon", 0x5C2918, 0xE30000);
		
		EntityRegistry.registerModEntity(EntityTatters.class, "Tatters", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityTatters.class, "Tatters", 0x666565, 0x000000);
		
		EntityRegistry.registerModEntity(EntityTattersHead.class, "TattersHead", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityTattersHead.class, "TattersHead", 0x000000, 0x000000);

		}

	public static void registerEntity(Class<? extends Entity> entityClass, String name, int modEntityIndex, int primaryColor, int secondaryColor) {
		EntityRegistry.registerModEntity(entityClass, name, modEntityIndex, ModularBosses.instance, 80, 3, false);
		CustomEntityList.addMapping(entityClass, name, primaryColor, secondaryColor);
	}

	//Register your Renderers!
	@SideOnly(Side.CLIENT) 
	public static void registerRenderers() {
		
		RenderManager manager = Minecraft.getMinecraft().getRenderManager();
		RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
		
		
		//mobs
		//RenderingRegistry.registerEntityRenderingHandler(EntityEyeTest.class, new RenderEyeTest(new ModelEyeTest(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySkull.class, new RenderSkull(manager, new ModelSkull(), 1));
		//RenderingRegistry.registerEntityRenderingHandler(EntityLavaBlob.class, new RenderLavaBlob(new ModelLavaBlob(), 0.7F));
		//RenderingRegistry.registerEntityRenderingHandler(EntityTestHead.class, new RenderTestHead(new ModelTestHead(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntityChorpChorp.class, new RenderChorpChorp(manager, new ModelChorpChorp(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityHeavyChorp.class, new RenderHeavyChorp(manager, new ModelHeavyChorp(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityParagon.class, new RenderParagon(manager, new ModelParagon(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityTatters.class, new RenderTatters(manager, new ModelTatters(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityTattersHead.class, new RenderTattersHead(manager, new ModelTattersHead(), 1));
		//RenderingRegistry.registerEntityRenderingHandler(EntitySenseiSteve.class, new RenderSenseiSteve(new ModelSenseiSteve(), 2));
		//RenderingRegistry.registerEntityRenderingHandler(EntityTestDummy.class, new RenderTestDummy(new ModelTestDummy(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCartographer.class, new RenderCartographer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportBiped.class, new RenderTeliportBiped(manager));
		
		//projectiles
		RenderingRegistry.registerEntityRenderingHandler(EntityChorpSlimeBlob.class, new RenderSnowball(manager, ModularBossesItems.slimeblob, itemRender));
		RenderingRegistry.registerEntityRenderingHandler(EntityFlameThrower.class, new RenderFlameThrower(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityCustomFallingBlock.class, new RenderCustomFallingBlock(manager));

		//RenderingRegistry.registerEntityRenderingHandler(EntityBlueWave.class, new RenderBlueWave());
		//RenderingRegistry.registerEntityRenderingHandler(EntitySpiritShard.class, new RenderSpiritShard());
		//RenderingRegistry.registerEntityRenderingHandler(EntityBlackHole.class, new RenderBlackHole());
		//RenderingRegistry.registerEntityRenderingHandler(EntityFlameShot.class, new RenderFlameShot());
	}

	private static void addSpawns() {

	}
}