package com.Splosions.ModularBosses.client.render.entity;

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

import com.Splosions.ModularBosses.client.models.projectiles.ModelCartographer;
import com.Splosions.ModularBosses.entity.EntityCartographer;




@SideOnly(Side.CLIENT)
public class RenderCartographer extends Render
{
	protected ModelBase model;
	
	private static final ResourceLocation textureglow = new ResourceLocation("mb:textures/projectiles/FlameThrower.png");

	public RenderCartographer(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelCartographer();
	}


	@Override
	public void doRender(Entity FlameShot, double x, double y, double z, float yaw, float partialTick) {
		renderEntityModel(FlameShot, x, y, z, yaw, partialTick);
	}

	public void renderEntityModel(Entity FlameShot, double x, double y, double z, float yaw, float partialTick) {
		EntityCartographer Blue = (EntityCartographer) FlameShot;
		GL11.glPushMatrix();
		float scale = 1;
		bindTexture(getEntityTexture(Blue));
		GL11.glTranslated(x, y, z);
		GL11.glScalef(scale, scale, scale);
		model.render(Blue, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return textureglow;
	}
}