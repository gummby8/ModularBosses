package com.Splosions.ModularBosses.entity.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManager;

public class EntityRendererAlt extends EntityRenderer
{
	private final Minecraft mc;
	private float offsetY = 3.0F; // just for testing, should be based on actual render size

	public EntityRendererAlt(Minecraft mc) {
		super(mc, mc.getResourceManager()); //, mc.getResourceManager());
		this.mc = mc;
	}

	@Override
	public void updateCameraAndRender(float partialTick) {
		if (mc.thePlayer == null || mc.thePlayer.isPlayerSleeping()){
			super.updateCameraAndRender(partialTick);
			return;
		}
		// this is what changes the actual camera height
		// but also seems to affect the player model render position
		
		mc.thePlayer.renderOffsetY -= offsetY;
		mc.thePlayer.renderOffsetX = 100;
		super.updateCameraAndRender(partialTick);
		mc.thePlayer.renderOffsetY = 1.62F;
	}

	@Override
	public void getMouseOver(float partialTick) {
		if (mc.thePlayer == null || mc.thePlayer.isPlayerSleeping()){
			super.getMouseOver(partialTick);
			return;
		}
		// adjust the y position to get a mouseover at eye-level
		// not perfect, as the server posY does not match, meaning
		// that some block clicks do not process correctly
		// (distance check or something like that)
		mc.thePlayer.posY += offsetY;
		mc.thePlayer.prevPosY += offsetY;
		mc.thePlayer.lastTickPosY += offsetY;
		super.getMouseOver(partialTick);
		mc.thePlayer.posY -= offsetY;
		mc.thePlayer.prevPosY -= offsetY;
		mc.thePlayer.lastTickPosY -= offsetY;
	}
}