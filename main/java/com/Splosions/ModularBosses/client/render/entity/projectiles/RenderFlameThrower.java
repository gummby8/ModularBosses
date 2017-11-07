package com.Splosions.ModularBosses.client.render.entity.projectiles;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.projectiles.ModelFlameThrower;
import com.Splosions.ModularBosses.entity.projectile.EntityFlameThrower;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



@SideOnly(Side.CLIENT)
public class RenderFlameThrower extends Render
{
	protected ModelBase model;
	
	private static final ResourceLocation textureglow = new ResourceLocation("mb:textures/projectiles/FlameThrower.png");

	public RenderFlameThrower(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelFlameThrower();
	}


	@Override
	public void doRender(Entity FlameShot, double x, double y, double z, float yaw, float partialTick) {
		renderEntityModel(FlameShot, x, y, z, yaw, partialTick);
	}

	public void renderEntityModel(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		EntityFlameThrower ent = (EntityFlameThrower) entity;
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
		return textureglow;
	}
}