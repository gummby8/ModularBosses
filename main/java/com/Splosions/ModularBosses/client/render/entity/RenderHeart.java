package com.Splosions.ModularBosses.client.render.entity;

import java.util.logging.Level;

import com.Splosions.ModularBosses.entity.EntityEyeballOctopus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

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
