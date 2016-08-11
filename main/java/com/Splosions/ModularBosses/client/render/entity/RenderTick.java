package com.Splosions.ModularBosses.client.render.entity;

import java.util.logging.Level;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderTick extends RenderLiving {
	
	ResourceLocation rec = new ResourceLocation("mb:textures/mobs/tick.png");
	
    public RenderTick(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
    }
	

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return rec;
	}
	
}
