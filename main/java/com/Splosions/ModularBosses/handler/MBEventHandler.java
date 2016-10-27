package com.Splosions.ModularBosses.handler;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.entity.player.EntityRendererAlt;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;
import com.Splosions.ModularBosses.proxy.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MBEventHandler {

	/*
	 * used to make the player look ghostly when in limbo.
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Pre event) {
		if (MBExtendedPlayer.get((EntityPlayer) event.entity).preLimbo > 0) {
			EntityPlayer player = event.entityPlayer;
			GlStateManager.enableBlend();
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(1, 1);
		}

		if (ModularBosses.instance.playerTarget != null && MBExtendedPlayer.get((EntityPlayer) event.entity).knockdownTime > 0) {
			EntityPlayer player = event.entityPlayer;
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0.15f, 0);
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glRotatef(-player.rotationYaw, 0, 0, 1);
			GL11.glRotatef(player.rotationYaw, 0, 1, 0);
			
			if (player != Minecraft.getMinecraft().thePlayer){
				Entity client = Minecraft.getMinecraft().thePlayer;
				double x = client.posX - player.posX;
				double y = client.posY - player.posY;
				double z = client.posZ - player.posZ;
				GL11.glTranslated(0,-y,0);
			}
			
			player.setInWeb();
			player.motionX = player.motionY = player.motionZ = 0;

			Entity target = ModularBosses.instance.playerTarget;
			double dx = player.posX - target.posX;
			double dz = player.posZ - target.posZ;
			double angle = Math.atan2(dz, dx) * 180 / Math.PI;
			double pitch = Math.atan2(player.posY - (target.posY + (target.height / 2.0F)), Math.sqrt(dx * dx + dz * dz)) * 180 / Math.PI;
			double distance = player.getDistanceToEntity(target);
			float rYaw = (float) (angle - player.rotationYaw);
			while (rYaw > 180) {
				rYaw -= 360;
			}
			while (rYaw < -180) {
				rYaw += 360;
			}
			rYaw += 90F;
			float rPitch = (float) pitch - (float) (10.0F / Math.sqrt(distance)) + (float) (distance * Math.PI / 90);
			player.setAngles(rYaw, -(rPitch - player.rotationPitch));
		}

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Post event) {
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();

		if (ModularBosses.instance.playerTarget != null && MBExtendedPlayer.get((EntityPlayer) event.entity).knockdownTime > 0) {
			GL11.glPopMatrix();
		}

	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && MBExtendedPlayer.get((EntityPlayer) event.entity) == null)
			MBExtendedPlayer.register((EntityPlayer) event.entity);
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(MBExtendedPlayer.EXT_PROP_NAME) == null)
			event.entity.registerExtendedProperties(MBExtendedPlayer.EXT_PROP_NAME, new MBExtendedPlayer((EntityPlayer) event.entity));
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			MBExtendedPlayer.get(player).onUpdate();
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
					IBakedModel customRender = clazz.getConstructor(IBakedModel.class).newInstance((IBakedModel) object);
					event.modelRegistry.putObject(resource, customRender);
					ModularBosses.logger.warn("Registered new renderer for resource " + resource + ": " + customRender.getClass().getSimpleName());
				} catch (NoSuchMethodException e) {
					ModularBosses.logger.warn("Failed to swap model: class " + clazz.getSimpleName() + " is missing a constructor that takes an IBakedModel");
				} catch (Exception e) {
					ModularBosses.logger.warn("Failed to swap model with exception: " + e.getMessage());
				}
			} else {
				ModularBosses.logger.warn("Resource is not a baked model! Failed resource: " + resource.toString());
			}
		}
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
