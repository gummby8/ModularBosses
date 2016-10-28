package com.Splosions.ModularBosses.handler;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.render.entity.RenderKnockedDown;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MBClientEventHandler {
	
	boolean needsPop;
	private final RenderKnockedDown knockedDown = new RenderKnockedDown(Minecraft.getMinecraft().getRenderManager());
	/*
	 * used to make the player look ghostly when in limbo.
	 */
	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Pre event) {
		EntityPlayer player = event.entityPlayer;
		
		if (MBExtendedPlayer.get((EntityPlayer) event.entity).preLimbo > 0) {
			GlStateManager.enableBlend();
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(1, 1);
		}


		if (ModularBosses.instance.playerTarget != null && !ModularBosses.instance.playerTarget.isDead  && event.entityPlayer == Minecraft.getMinecraft().thePlayer){
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
	
		
		if (MBExtendedPlayer.get((EntityPlayer) event.entity).knockdownTime > 0 && !(event.renderer instanceof RenderKnockedDown)) {
			event.setCanceled(true);
			knockedDown.doRender(player, event.x, event.y, event.z, 0.0625F, event.partialRenderTick);
		}

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Post event) {
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
