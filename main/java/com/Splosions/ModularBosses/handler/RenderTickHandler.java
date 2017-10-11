package com.Splosions.ModularBosses.handler;

import com.Splosions.ModularBosses.entity.player.EntityRendererAlt;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTickHandler {
	private Minecraft mc;
	private EntityRenderer renderer, prevRenderer;

	public RenderTickHandler() {
		this.mc = Minecraft.getMinecraft();
	}

}
