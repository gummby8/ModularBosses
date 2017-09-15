package com.Splosions.ModularBosses.entity;


import java.util.logging.Level;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.models.entity.ModelBrain;
import com.Splosions.ModularBosses.client.models.entity.ModelChorpChorp;
import com.Splosions.ModularBosses.client.models.entity.ModelEyeballOctopus;
import com.Splosions.ModularBosses.client.models.entity.ModelGolem;
import com.Splosions.ModularBosses.client.models.entity.ModelHeart;
import com.Splosions.ModularBosses.client.models.entity.ModelHeavyChorp;
import com.Splosions.ModularBosses.client.models.entity.ModelMoldormAlpha;
import com.Splosions.ModularBosses.client.models.entity.ModelParagon;
import com.Splosions.ModularBosses.client.models.entity.ModelSandWormBody;
import com.Splosions.ModularBosses.client.models.entity.ModelSandWormHead;
import com.Splosions.ModularBosses.client.models.entity.ModelSandWormTail;
import com.Splosions.ModularBosses.client.models.entity.ModelShadeHowler;
import com.Splosions.ModularBosses.client.models.entity.ModelSkull;
import com.Splosions.ModularBosses.client.models.entity.ModelSpark;
import com.Splosions.ModularBosses.client.models.entity.ModelTatters;
import com.Splosions.ModularBosses.client.models.entity.ModelTattersHead;
import com.Splosions.ModularBosses.client.models.entity.ModelTick;
import com.Splosions.ModularBosses.client.render.entity.RenderBrain;
import com.Splosions.ModularBosses.client.render.entity.RenderChorpChorp;
import com.Splosions.ModularBosses.client.render.entity.RenderCustomFallingBlock;
import com.Splosions.ModularBosses.client.render.entity.RenderEyeballOctopus;
import com.Splosions.ModularBosses.client.render.entity.RenderGolem;
import com.Splosions.ModularBosses.client.render.entity.RenderHeart;
import com.Splosions.ModularBosses.client.render.entity.RenderHeavyChorp;
import com.Splosions.ModularBosses.client.render.entity.RenderKnockedDown;
import com.Splosions.ModularBosses.client.render.entity.RenderMoldormAlpha;
import com.Splosions.ModularBosses.client.render.entity.RenderParagon;
import com.Splosions.ModularBosses.client.render.entity.RenderSandWorm;
import com.Splosions.ModularBosses.client.render.entity.RenderSandWormTail;
import com.Splosions.ModularBosses.client.render.entity.RenderShadeHowler;
import com.Splosions.ModularBosses.client.render.entity.RenderSkull;
import com.Splosions.ModularBosses.client.render.entity.RenderSpark;
import com.Splosions.ModularBosses.client.render.entity.RenderTatters;
import com.Splosions.ModularBosses.client.render.entity.RenderTattersHead;
import com.Splosions.ModularBosses.client.render.entity.RenderTeliportBiped;
import com.Splosions.ModularBosses.client.render.entity.RenderTick;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderBait;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderBloodBlob;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderBoulder;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderBrainEnergy;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderEnergyArrow;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderFlameThrower;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderScythe;
import com.Splosions.ModularBosses.client.render.items.RenderItemBait;
import com.Splosions.ModularBosses.entity.projectile.EntityBait;
import com.Splosions.ModularBosses.entity.projectile.EntityBloodBlob;
import com.Splosions.ModularBosses.entity.projectile.EntityBoulder;
import com.Splosions.ModularBosses.entity.projectile.EntityBrainEnergy;
import com.Splosions.ModularBosses.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.entity.projectile.EntityCustomEgg;
import com.Splosions.ModularBosses.entity.projectile.EntityCustomFallingBlock;
import com.Splosions.ModularBosses.entity.projectile.EntityEnergyArrow;
import com.Splosions.ModularBosses.entity.projectile.EntityFlameThrower;
import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.Splosions.ModularBosses.items.ModularBossesItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
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
		//Projectile Entities
		EntityRegistry.registerModEntity(EntityChorpSlimeBlob.class, "slimeblob", ++modEntityIndex, ModularBosses.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityBoulder.class, "boulder", ++modEntityIndex, ModularBosses.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityFlameThrower.class, "Flame Thrower", ++modEntityIndex, ModularBosses.instance, 64, 3, true);
		EntityRegistry.registerModEntity(EntityEnergyArrow.class, "Energy Arrow", ++modEntityIndex, ModularBosses.instance, 300, 1, true);
		EntityRegistry.registerModEntity(EntityBrainEnergy.class, "Brain Energy", ++modEntityIndex, ModularBosses.instance, 300, 1, true);
		EntityRegistry.registerModEntity(EntityBloodBlob.class, "Blood Blob", ++modEntityIndex, ModularBosses.instance, 300, 1, true);
		EntityRegistry.registerModEntity(EntityScythe.class, "Scythe", ++modEntityIndex, ModularBosses.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityCustomFallingBlock.class, "Falling Block", ++modEntityIndex, ModularBosses.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityBait.class, "Bait", ++modEntityIndex, ModularBosses.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntityBlueWave.class, "Blue Wave", ++modEntityIndex, ModularBossesMain.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntitySpiritShard.class, "Spirit Shard", ++modEntityIndex, ModularBossesMain.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntityBlackHole.class, "Black Hole", ++modEntityIndex, ModularBossesMain.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntityFlameShot.class, "Flame SHot", ++modEntityIndex, ModularBossesMain.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityTeleportBiped.class, "Teleport Model", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
	
		
		// MOBS and egg colors
		EntityRegistry.registerModEntity(EntitySandWorm.class, "SandWorm", ++modEntityIndex, ModularBosses.instance, 400, 3, true);
		EntityRegistry.registerModEntity(EntitySandWormTail.class, "SandWormTail", ++modEntityIndex, ModularBosses.instance, 400, 3, true);
		CustomEntityList.addMapping(EntitySandWorm.class, "SandWorm", 0x663300, 0xFFFFFF);
		
		EntityRegistry.registerModEntity(EntityBrain.class, "Brain", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityBrain.class, "Brain", 0xffc1c1, 0xccbdbd);
		
		EntityRegistry.registerModEntity(EntityHeart.class, "Heart", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityHeart.class, "Heart", 0xff0000, 0x000000);
		
		EntityRegistry.registerModEntity(EntitySpark.class, "Spark", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntitySpark.class, "Spark", 0x9800ff, 0x00ff2a);
		
		EntityRegistry.registerModEntity(EntitySkull.class, "Skull", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntitySkull.class, "Skull", 0xFFFBAF, 0x000000);
				
		EntityRegistry.registerModEntity(EntityGolem.class, "Golem", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityGolem.class, "Golem", 0x7f7f7f, 0x262626);
		
		EntityRegistry.registerModEntity(EntityChorpChorp.class, "ChorpChorp", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityChorpChorp.class, "ChorpChorp", 0x3F5A8C, 0xFFFFFF );
		
		EntityRegistry.registerModEntity(EntityMoldormAlpha.class, "Moldorm", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityMoldormAlpha.class, "Moldorm", 0x89FF01, 0xDEFF01);
		
		EntityRegistry.registerModEntity(EntityHeavyChorp.class, "HeavyChorp", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityHeavyChorp.class, "HeavyChorp", 0x8C713F, 0xFFFFFF);
		
		EntityRegistry.registerModEntity(EntityParagon.class, "Paragon", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityParagon.class, "Paragon", 0x5C2918, 0xFF9100);
		
		EntityRegistry.registerModEntity(EntityTatters.class, "Tatters", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityTatters.class, "Tatters", 0x666565, 0x000000);
		
		EntityRegistry.registerModEntity(EntityEyeballOctopus.class, "EyeballOctopus", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityEyeballOctopus.class, "EyeballOctopus", 0xff66cc, 0x00ccff);
		
		EntityRegistry.registerModEntity(EntityTick.class, "Tick", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityTick.class, "Tick", 0x6B3201, 0xFF6200);
		
		EntityRegistry.registerModEntity(EntityShadeHowler.class, "ShadeHowler", ++modEntityIndex, ModularBosses.instance, 80, 3, true);
		CustomEntityList.addMapping(EntityShadeHowler.class, "ShadeHowler", 0x000000, 0x00ccff);

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
		RenderingRegistry.registerEntityRenderingHandler(EntitySkull.class, new RenderSkull(manager, new ModelSkull(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityBrain.class, new RenderBrain(manager, new ModelBrain(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityHeart.class, new RenderHeart(manager, new ModelHeart(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpark.class, new RenderSpark(manager, new ModelSpark(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityChorpChorp.class, new RenderChorpChorp(manager, new ModelChorpChorp(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityHeavyChorp.class, new RenderHeavyChorp(manager, new ModelHeavyChorp(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityEyeballOctopus.class, new RenderEyeballOctopus(manager, new ModelEyeballOctopus(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityGolem.class, new RenderGolem(manager, new ModelGolem(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityParagon.class, new RenderParagon(manager, new ModelParagon(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityTatters.class, new RenderTatters(manager, new ModelTatters(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityMoldormAlpha.class, new RenderMoldormAlpha(manager, new ModelMoldormAlpha(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntitySandWorm.class, new RenderSandWorm(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntitySandWormTail.class, new RenderSandWormTail(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityTick.class, new RenderTick(manager, new ModelTick(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityShadeHowler.class, new RenderShadeHowler(manager, new ModelShadeHowler(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityTeleportBiped.class, new RenderTeliportBiped(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderKnockedDown(manager));
		
		 
		//projectiles
		RenderingRegistry.registerEntityRenderingHandler(EntityCustomEgg.class, new RenderSnowball(manager, ModularBossesItems.spawn_egg, itemRender));
		RenderingRegistry.registerEntityRenderingHandler(EntityChorpSlimeBlob.class, new RenderSnowball(manager, ModularBossesItems.slimeblob, itemRender));
		RenderingRegistry.registerEntityRenderingHandler(EntityBoulder.class, new RenderBoulder(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityFlameThrower.class, new RenderFlameThrower(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnergyArrow.class, new RenderEnergyArrow(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityBrainEnergy.class, new RenderBrainEnergy(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityBloodBlob.class, new RenderBloodBlob(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityBait.class, new RenderBait(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityScythe.class, new RenderScythe(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityCustomFallingBlock.class, new RenderCustomFallingBlock(manager));

		//RenderingRegistry.registerEntityRenderingHandler(EntityBlueWave.class, new RenderBlueWave());
		//RenderingRegistry.registerEntityRenderingHandler(EntitySpiritShard.class, new RenderSpiritShard());
		//RenderingRegistry.registerEntityRenderingHandler(EntityBlackHole.class, new RenderBlackHole());
		//RenderingRegistry.registerEntityRenderingHandler(EntityFlameShot.class, new RenderFlameShot());
	}

	private static void addSpawns() {

	}
}