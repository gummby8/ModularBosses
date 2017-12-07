package com.Splosions.ModularBosses.entity;


import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;
import com.Splosions.ModularBosses.client.models.entity.ModelBrain;
import com.Splosions.ModularBosses.client.models.entity.ModelChorpChorp;
import com.Splosions.ModularBosses.client.models.entity.ModelEyeballOctopus;
import com.Splosions.ModularBosses.client.models.entity.ModelGolem;
import com.Splosions.ModularBosses.client.models.entity.ModelHeart;
import com.Splosions.ModularBosses.client.models.entity.ModelHeavyChorp;
import com.Splosions.ModularBosses.client.models.entity.ModelMoldorm;
import com.Splosions.ModularBosses.client.models.entity.ModelParagon;
import com.Splosions.ModularBosses.client.models.entity.ModelShadeHowler;
import com.Splosions.ModularBosses.client.models.entity.ModelSkull;
import com.Splosions.ModularBosses.client.models.entity.ModelSpark;
import com.Splosions.ModularBosses.client.models.entity.ModelTatters;
import com.Splosions.ModularBosses.client.models.entity.ModelTick;
import com.Splosions.ModularBosses.client.render.entity.RenderBrain;
import com.Splosions.ModularBosses.client.render.entity.RenderChorpChorp;
import com.Splosions.ModularBosses.client.render.entity.RenderCustomFallingBlock;
import com.Splosions.ModularBosses.client.render.entity.RenderEyeballOctopus;
import com.Splosions.ModularBosses.client.render.entity.RenderGolem;
import com.Splosions.ModularBosses.client.render.entity.RenderHeart;
import com.Splosions.ModularBosses.client.render.entity.RenderHeavyChorp;
import com.Splosions.ModularBosses.client.render.entity.RenderKnockedDown;
import com.Splosions.ModularBosses.client.render.entity.RenderMoldorm;
import com.Splosions.ModularBosses.client.render.entity.RenderParagon;
import com.Splosions.ModularBosses.client.render.entity.RenderSandWorm;
import com.Splosions.ModularBosses.client.render.entity.RenderSandWormTail;
import com.Splosions.ModularBosses.client.render.entity.RenderShadeHowler;
import com.Splosions.ModularBosses.client.render.entity.RenderSkull;
import com.Splosions.ModularBosses.client.render.entity.RenderSpark;
import com.Splosions.ModularBosses.client.render.entity.RenderTatters;
import com.Splosions.ModularBosses.client.render.entity.RenderTeliportBiped;
import com.Splosions.ModularBosses.client.render.entity.RenderTick;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderBait;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderBloodBlob;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderBoulder;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderBrainEnergy;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderEnergyArrow;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderFlameThrower;
import com.Splosions.ModularBosses.client.render.entity.projectiles.RenderScythe;
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
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
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
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "slimeblob"), EntityChorpSlimeBlob.class, "sunstrike", ++modEntityIndex, ModularBosses.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "boulder"), EntityBoulder.class, "boulder", ++modEntityIndex, ModularBosses.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "flame_thrower"), EntityFlameThrower.class, "flame_thrower", ++modEntityIndex, ModularBosses.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "energy_arrow"), EntityEnergyArrow.class, "energy_arrow", ++modEntityIndex, ModularBosses.INSTANCE, 300, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "brain_energy"), EntityBrainEnergy.class, "brain_energy", ++modEntityIndex, ModularBosses.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "blood_blob"), EntityBloodBlob.class, "blood_blob", ++modEntityIndex, ModularBosses.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "scythe"), EntityScythe.class, "scythe", ++modEntityIndex, ModularBosses.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "falling_block"), EntityCustomFallingBlock.class, "falling_block", ++modEntityIndex, ModularBosses.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "bait"), EntityBait.class, "bait", ++modEntityIndex, ModularBosses.INSTANCE, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "teleport_biped"), EntityTeleportBiped.class, "teleport_biped", ++modEntityIndex, ModularBosses.INSTANCE, 64, 1, true);


	
		
		// MOBS and egg colors
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "sandworm"), EntitySandWorm.class, "sandworm", ++modEntityIndex, ModularBosses.INSTANCE, 400, 3, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "sandworm_tail"), EntityBoulder.class, "sandworm_tail", ++modEntityIndex, ModularBosses.INSTANCE, 400, 3, true);
		CustomEntityList.addMapping(EntitySandWorm.class, "sandworm", 0x663300, 0xFFFFFF);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "brain"), EntityBrain.class, "brain", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityBrain.class, "brain", 0xffc1c1, 0xccbdbd);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "heart"), EntityHeart.class, "heart", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityHeart.class, "heart", 0xff0000, 0x000000);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "spark"), EntitySpark.class, "spark", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntitySpark.class, "spark", 0x9800ff, 0x00ff2a);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "skull"), EntitySkull.class, "skull", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntitySkull.class, "skull", 0xFFFBAF, 0x000000);

		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "golem"), EntityGolem.class, "golem", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityGolem.class, "golem", 0x7f7f7f, 0x262626);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "moldorm"), EntityMoldorm.class, "moldorm", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityMoldorm.class, "moldorm", 0x89FF01, 0xDEFF01);

		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "chorpchorp"), EntityChorpChorp.class, "chorpchorp", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityChorpChorp.class, "chorpchorp", 0x3F5A8C, 0xFFFFFF );
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "heavychorp"), EntityHeavyChorp.class, "heavychorp", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityHeavyChorp.class, "heavychorp", 0x8C713F, 0xFFFFFF);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "paragon"), EntityParagon.class, "paragon", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityParagon.class, "paragon", 0x5C2918, 0xFF9100);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "tatters"), EntityTatters.class, "tatters", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityTatters.class, "tatters", 0x666565, 0x000000);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "eyeball_octopus"), EntityEyeballOctopus.class, "eyeball_octopus", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityEyeballOctopus.class, "eyeball_octopus", 0xff66cc, 0x00ccff);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "tick"), EntityTick.class, "tick", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityTick.class, "tick", 0x6B3201, 0xFF6200);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "shade_howler"), EntityShadeHowler.class, "shade_howler", ++modEntityIndex, ModularBosses.INSTANCE, 80, 3, true);
		CustomEntityList.addMapping(EntityShadeHowler.class, "shade_howler", 0x000000, 0x00ccff);

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
		RenderingRegistry.registerEntityRenderingHandler(EntityMoldorm.class, new RenderMoldorm(manager, new ModelMoldorm(), 1));
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