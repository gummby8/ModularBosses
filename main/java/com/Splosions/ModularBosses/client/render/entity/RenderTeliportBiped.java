package com.Splosions.ModularBosses.client.render.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;


import com.Splosions.ModularBosses.client.entity.EntityTeleportBiped;
import com.Splosions.ModularBosses.client.models.entity.ModelTeleportBiped;
import com.google.common.base.Objects;





@SideOnly(Side.CLIENT)
public class RenderTeliportBiped extends Render
{
	protected ModelBase model;
	
	private  ResourceLocation texture = new ResourceLocation("mb:textures/mobs/blank.png");

	public RenderTeliportBiped(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelTeleportBiped();
		
		
	}


	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		renderEntityModel(entity, x, y, z, yaw, partialTick);
	}

	public void renderEntityModel(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		EntityTeleportBiped ent = (EntityTeleportBiped) entity;
		
		
		GL11.glPushMatrix();
		float scale = 1.3f;
		bindTexture(getEntityTexture(ent));
		
		GL11.glTranslated(x, y + 0.5f, z);
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(yaw + (entity.ticksExisted * 50), 0, 1F, 0);
		
		model.render(ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
		
		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}