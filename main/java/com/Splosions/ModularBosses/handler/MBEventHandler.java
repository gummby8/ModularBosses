package com.Splosions.ModularBosses.handler;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.FluidWormAcid;
import com.Splosions.ModularBosses.blocks.FluidWormBlood;
import com.Splosions.ModularBosses.blocks.FluidWormSaliva;
import com.Splosions.ModularBosses.blocks.GasWormGas;
import com.Splosions.ModularBosses.blocks.ModBlocks;
import com.Splosions.ModularBosses.client.render.entity.RenderKnockedDown;
import com.Splosions.ModularBosses.entity.MBExtendedEntityLivingBase;
import com.Splosions.ModularBosses.entity.player.EntityRendererAlt;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;
import com.Splosions.ModularBosses.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MBEventHandler {

	// no block breaking in the boss dimension
	@SubscribeEvent
	public void onBlockBreak(BreakEvent event) {
		if (Config.bossDimBreak) {
			if (event.world.provider.getDimensionId() == Config.bossDimension
					&& !event.getPlayer().capabilities.isCreativeMode) {
				event.setCanceled(true);
				event.world.scheduleUpdate(event.pos, event.state.getBlock(), 1);
			}
		}
	}

	// Explosions will hurt entities, but won't break blocks in the boss
	// dimension
	@SubscribeEvent
	public void onExplosion(ExplosionEvent.Detonate event) {
		if (Config.bossDimExplosions) {
			if (event.world.provider.getDimensionId() == Config.bossDimension) {
				event.getAffectedBlocks().clear();
			}
		}
	}
	
	// no block placing in the boss dimension
	@SubscribeEvent
	public void onBlockPlace(PlaceEvent event) {
		if (Config.bossDimBuild) {
			if (event.world.provider.getDimensionId() == Config.bossDimension
					&& !event.player.capabilities.isCreativeMode) {
				event.setCanceled(true);
				event.world.scheduleUpdate(event.pos, event.state.getBlock(), 1);
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		try {
			if (event.entity instanceof EntityPlayer && MBExtendedPlayer.get((EntityPlayer) event.entity) == null){
				MBExtendedPlayer.register((EntityPlayer) event.entity);
			}
				
			if (event.entity instanceof EntityLivingBase && !(event.entity instanceof EntityPlayer) && MBExtendedEntityLivingBase.get((EntityLivingBase) event.entity) == null){
				MBExtendedEntityLivingBase.register((EntityLivingBase) event.entity);
			}
				

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@SideOnly(Side.SERVER)
	@SubscribeEvent
	public void dimensionLoad(Load event){
		System.out.println("Loading Dimension");
		if (event.world.provider.getDimensionId() == Config.bossDimension){
			MinecraftServer.getServer().worldServerForDimension(Config.bossDimension).setBlockState(new BlockPos(1,1,1), ModBlocks.chunkLoaderBlock.getDefaultState());
		}
	}
	
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			MBExtendedPlayer.get(player).onUpdate();
		}
		
		if (event.entity instanceof EntityLivingBase && !(event.entity instanceof EntityPlayer)){
			EntityLivingBase entity = (EntityLivingBase) event.entity;
			MBExtendedEntityLivingBase.get(entity).onUpdate();
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onBakeModel(ModelBakeEvent event) {
		for (ModelResourceLocation resource : ClientProxy.smartModels.keySet()) {
			Object object = event.modelRegistry.getObject(resource);
			if (object instanceof IBakedModel) {
				Class<? extends IBakedModel> clazz = ClientProxy.smartModels.get(resource);
				try {
					IBakedModel customRender = clazz.getConstructor(IBakedModel.class)
							.newInstance((IBakedModel) object);
					event.modelRegistry.putObject(resource, customRender);
					ModularBosses.logger.warn("Registered new renderer for resource " + resource + ": "
							+ customRender.getClass().getSimpleName());
				} catch (NoSuchMethodException e) {
					ModularBosses.logger.warn("Failed to swap model: class " + clazz.getSimpleName()
							+ " is missing a constructor that takes an IBakedModel");
				} catch (Exception e) {
					ModularBosses.logger.warn("Failed to swap model with exception: " + e.getMessage());
				}
			} else {
				ModularBosses.logger.warn("Resource is not a baked model! Failed resource: " + resource.toString());
			}
		}
	}

	@SubscribeEvent
	public void SpawnEvent(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntitySquid) {
			BlockPos pos = new BlockPos(event.entity.posX, event.entity.posY, event.entity.posZ);
			Block block = event.world.getBlockState(pos).getBlock();
			if (block instanceof FluidWormBlood || block instanceof FluidWormSaliva || block instanceof FluidWormAcid || block instanceof GasWormGas) {
				event.setCanceled(true);
			}
		}
	}

	
	@SubscribeEvent
	public void LivingHurt(LivingHurtEvent event){
		if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase){
			if (!(event.source.getEntity() instanceof EntityPlayer) && event.entity instanceof EntityPlayer){
				if (MBExtendedEntityLivingBase.get((EntityLivingBase) event.source.getEntity()).limbo != MBExtendedPlayer.get((EntityPlayer) event.entity).limbo){
					event.setCanceled(true);
				}					
			}else
			if (event.source.getEntity() instanceof EntityPlayer && !(event.entity instanceof EntityPlayer)){
				if (MBExtendedPlayer.get((EntityPlayer) event.source.getEntity()).limbo != MBExtendedEntityLivingBase.get((EntityLivingBase) event.entity).limbo){
					event.setCanceled(true);
				}					
			}else
			if (event.source.getEntity() instanceof EntityPlayer && event.entity instanceof EntityPlayer){
				if (MBExtendedPlayer.get((EntityPlayer) event.source.getEntity()).limbo != MBExtendedPlayer.get((EntityPlayer) event.entity).limbo){
					event.setCanceled(true);
				}
			}
		}
	}

}
