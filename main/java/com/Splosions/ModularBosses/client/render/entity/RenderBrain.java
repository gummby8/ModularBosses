package com.Splosions.ModularBosses.client.render.entity;

import java.util.logging.Level;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderBrain extends RenderLiving {
	
	ResourceLocation rec = new ResourceLocation("mb:textures/mobs/Brain.png");
	
    public RenderBrain(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
    }
	

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return rec;
	}
	
}
