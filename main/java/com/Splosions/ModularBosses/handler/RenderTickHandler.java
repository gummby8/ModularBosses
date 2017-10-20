package com.Splosions.ModularBosses.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
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
