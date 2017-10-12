package com.Splosions.ModularBosses.handler;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.BlockForceFieldBlue;
import com.Splosions.ModularBosses.blocks.BlockInvisible;
import com.Splosions.ModularBosses.blocks.FluidWormAcid;
import com.Splosions.ModularBosses.blocks.FluidWormBlood;
import com.Splosions.ModularBosses.client.render.entity.RenderKnockedDown;
import com.Splosions.ModularBosses.client.render.items.RenderItemNote;
import com.Splosions.ModularBosses.entity.MBExtendedEntityLivingBase;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;
import com.Splosions.ModularBosses.items.ItemNote;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.GuiScreenEvent.KeyboardInputEvent;
import net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MBClientEventHandler {

	private final RenderKnockedDown knockedDown = new RenderKnockedDown(Minecraft.getMinecraft().getRenderManager());
	/*
	 * used to make the player look ghostly when in limbo.
	 */

	// Block Draw Box is not shown on force fields or invisible blocks
	@SubscribeEvent
	public void onDrawBlockHighlight(DrawBlockHighlightEvent event) {
		try {
			if (event.target.entityHit == null) {
				Block block = event.player.worldObj.getBlockState(event.target.getBlockPos()).getBlock();
				if (block instanceof BlockForceFieldBlue || block instanceof BlockInvisible) {
					event.setCanceled(true);
				}
			}
		} catch (Exception e) {
			ModularBosses.logger.warn(e.getMessage());
		}

	}

	//custom screen overlay when submerged in worm blood
	@SubscribeEvent
	public void RenderBlockOverlayEvent(RenderBlockOverlayEvent event) {
		if (event.player.worldObj.getBlockState(event.blockPos).getBlock() instanceof FluidWormBlood || event.player.worldObj.getBlockState(event.blockPos).getBlock() instanceof FluidWormAcid) {
			event.setCanceled(true);
			TextureAtlasSprite atlas = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(event.player.worldObj.getBlockState(event.blockPos));
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer worldrenderer = tessellator.getWorldRenderer();
			float f1 = 0.1F;
			GlStateManager.pushMatrix();
			GL11.glEnable(GL11.GL_BLEND);

			if (event.player.worldObj.getBlockState(event.blockPos).getBlock() instanceof FluidWormBlood){
				GlStateManager.color(0.7F, 0, 0, 0.41F);
				GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_SRC_COLOR);// favorite so far
			} else 
			if(event.player.worldObj.getBlockState(event.blockPos).getBlock() instanceof FluidWormAcid){
				GlStateManager.color(0.5F, 0.5F, 0F, 0.41F);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);// favorite so far				
			}
	
			float f2 = -1.0F;
			float f3 = 1.0F;
			float f4 = -1.0F;
			float f5 = 1.0F;
			float f6 = -0.5F;
			float f7 = atlas.getMinU();
			float f8 = atlas.getMaxU();
			float f9 = atlas.getMinV();
			float f10 = atlas.getMaxV();
			worldrenderer.startDrawingQuads();
			worldrenderer.addVertexWithUV((double) f2, (double) f4, (double) f6, (double) f8, (double) f10);
			worldrenderer.addVertexWithUV((double) f3, (double) f4, (double) f6, (double) f7, (double) f10);
			worldrenderer.addVertexWithUV((double) f3, (double) f5, (double) f6, (double) f7, (double) f9);
			worldrenderer.addVertexWithUV((double) f2, (double) f5, (double) f6, (double) f8, (double) f9);
			tessellator.draw();
			GL11.glDisable(GL11.GL_BLEND);
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}



	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Pre event) {
		EntityPlayer player = event.entityPlayer;

		if (MBExtendedPlayer.get((EntityPlayer) event.entity).preLimbo > 0) {
			GlStateManager.enableBlend();
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(1, 1);
		}

		if (ModularBosses.INSTANCE.playerTarget != null && !ModularBosses.INSTANCE.playerTarget.isDead && event.entityPlayer == Minecraft.getMinecraft().thePlayer) {
			player.motionX = player.motionY = player.motionZ = 0;

			Entity target = ModularBosses.INSTANCE.playerTarget;
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

		if (MBExtendedPlayer.get((EntityPlayer) event.entity).knockdownTime != 0 && !(event.renderer instanceof RenderKnockedDown)) {
			event.setCanceled(true);
			knockedDown.doRender(player, event.x, event.y, event.z, 0.0625F, event.partialRenderTick);
		}

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Post event) {
		if (MBExtendedPlayer.get((EntityPlayer) event.entity).preLimbo > 0) {
			GlStateManager.disableBlend();
			GlStateManager.enableAlpha();			
		}
	}
	
	

	@SideOnly(Side.CLIENT)
	@SubscribeEvent	
	public void renderHand(RenderHandEvent event){
		AbstractClientPlayer player = Minecraft.getMinecraft().thePlayer;
		ItemStack weapon = player.getHeldItem();

		if (weapon != null && weapon.getItem() instanceof ItemNote)	{
			event.setCanceled(true);

	        float f1 = 1.0F;
	        float f2 = 0;
	        float f3 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * event.partialTicks;
	        float f4 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * event.partialTicks;
	        
	        GlStateManager.enableRescaleNormal();
	        GlStateManager.pushMatrix();
	        RenderItemNote.func_178101_a(f3, f4);
	        RenderItemNote.func_178110_a((EntityPlayerSP)player, event.partialTicks);
			
			RenderItemNote.renderNote(player, f3, f1,  f2, weapon);
	        GlStateManager.popMatrix();
	        GlStateManager.disableRescaleNormal();
		}
	}
	

	@SubscribeEvent
	public void onRenderEntityLiving(RenderLivingEvent.Pre event) {
		if (MBExtendedEntityLivingBase.get((EntityLivingBase) event.entity).limbo == 1) {
			GlStateManager.enableBlend();
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(1, 1);
		}
	}
	
	
	@SubscribeEvent
	public void onRenderEntityLiving(RenderLivingEvent.Post event) {
		if (MBExtendedEntityLivingBase.get((EntityLivingBase) event.entity).limbo == 1) {
			GlStateManager.disableBlend();
			GlStateManager.enableAlpha();
		}
	}

	
}
