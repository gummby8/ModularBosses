package com.Splosions.ModularBosses.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderHeart extends RenderLiving {

	ResourceLocation texture = new ResourceLocation("mb:textures/mobs/heart.png");
	protected ModelBase model;
	public RenderHeart(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
		this.model = model;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return texture;
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		super.doRender(entity, x, y, z, p_76986_8_, partialTicks);

	}

	
}
