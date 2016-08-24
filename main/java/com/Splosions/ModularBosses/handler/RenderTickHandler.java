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
	/**
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event) {
		//System.out.println("Derp?");
		if (event.phase == Phase.START) {
			
			
			if (mc.thePlayer != null) {
				updateRenderer();
				
			}
		}
	}


	@SuppressWarnings("unused")
	private void updateRenderer() {
		//make this a real if statement later
		//if (true) {
			if (renderer == null) {
				System.out.println("Renderer was nul making a new alt render");
				renderer = new EntityRendererAlt(mc);
			}
			if (mc.entityRenderer != renderer) {
				prevRenderer = mc.entityRenderer;
				mc.entityRenderer = renderer;
				System.out.println("Doing stuff");
			}
	
		} else if (prevRenderer != null && mc.entityRenderer != prevRenderer) {
			mc.entityRenderer = prevRenderer;
			System.out.println("Undoing stuff");
		}

	}
	*/
}
