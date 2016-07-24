package com.Splosions.ModularBosses.entity.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author Thanks to Noppes for the original code
 *
 */
@SideOnly(Side.CLIENT)
public class EntityPlayerKnockedDown extends EntityRenderer
{
	private final Minecraft mc;
	private float ySize = 3.0F;
	private float offsetY = ySize / 2.0F;

	public EntityPlayerKnockedDown(Minecraft mc) {
		super(mc, mc.getResourceManager());
		this.mc = mc;
	}

	@Override
	public void updateCameraAndRender(float partialTick) {
		if (mc.thePlayer == null || mc.thePlayer.isPlayerSleeping()) {
			super.updateCameraAndRender(partialTick);
			return;
		}
		// TODO used to be yOffset; not sure what renderOffsetY will do
		mc.thePlayer.renderOffsetY -= ySize;
		super.updateCameraAndRender(partialTick);
		mc.thePlayer.renderOffsetY = 1.62F;
	}

	@Override
	public void getMouseOver(float partialTick) {
		if (mc.thePlayer == null || mc.thePlayer.isPlayerSleeping()) {
			super.getMouseOver(partialTick);
			return;
		}
		/*
		ModelData data = PlayerDataController.instance.getPlayerData(player.getCommandSenderName());
		float offset = data.offsetY();
		if (player.ridingEntity != null || data.animation == EnumAnimation.SITTING)
			offset += -data.getLegsY();
		if (data.isSleeping())
			offset = 1.18f;
		player.posY += -offset;
		player.prevPosY += -offset;
		player.lastTickPosY += -offset;
		super.getMouseOver(partialTick);
		player.posY -= -offset;
		player.prevPosY -= -offset;
		player.lastTickPosY -= -offset;
		 */
		/*
		 * Need to adjust the player's position to get an accurate mouse-over.
		 * Unlike 1.6.4, the player position must be adjusted the opposite direction.
		 * Clicking blocks at the player's new height still does not work well if
		 * the player is larger, as the distance seems to be calculated from the
		 * player's foot level instead of eye level.
		 */
		mc.thePlayer.posY -= offsetY;
		mc.thePlayer.prevPosY -= offsetY;
		mc.thePlayer.lastTickPosY -= offsetY;
		super.getMouseOver(partialTick);
		mc.thePlayer.posY += offsetY;
		mc.thePlayer.prevPosY += offsetY;
		mc.thePlayer.lastTickPosY += offsetY;
	}
}
