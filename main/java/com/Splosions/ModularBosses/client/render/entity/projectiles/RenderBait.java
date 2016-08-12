package com.Splosions.ModularBosses.client.render.entity.projectiles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.Splosions.ModularBosses.client.models.item.ModelBait;
import com.Splosions.ModularBosses.client.models.projectiles.ModelBoulder;
import com.Splosions.ModularBosses.client.models.projectiles.ModelFlameThrower;
import com.Splosions.ModularBosses.entity.projectile.EntityBait;
import com.Splosions.ModularBosses.entity.projectile.EntityBoulder;
import com.Splosions.ModularBosses.entity.projectile.EntityFlameThrower;



@SideOnly(Side.CLIENT)
public class RenderBait extends Render
{
	protected ModelBase model;
	
	private static final ResourceLocation tex = new ResourceLocation("minecraft:textures/items/Bait.png");

	public RenderBait(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelBait();
	}


	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		renderEntityModel(entity, x, y, z, yaw, partialTick);
	}

	public void renderEntityModel(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		EntityBait ent = (EntityBait) entity;
		GL11.glPushMatrix();
		float scale = 1;
		bindTexture(getEntityTexture(ent));
		GL11.glTranslated(x, y, z);
		GL11.glScalef(scale, scale, scale);
		model.render(ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return tex;
	}
}