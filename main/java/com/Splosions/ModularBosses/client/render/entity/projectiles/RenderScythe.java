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

import com.Splosions.ModularBosses.client.models.projectiles.ModelScythe;
import com.Splosions.ModularBosses.entity.projectile.EntityScythe;

@SideOnly(Side.CLIENT)
public class RenderScythe extends Render {
	protected ModelScythe model;

	private static final ResourceLocation texture = new ResourceLocation("mb:textures/projectiles/Scythe.png");

	public RenderScythe(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelScythe();
	}

	@Override
	public void doRender(Entity FlameShot, double x, double y, double z, float yaw, float partialTick) {
		renderEntityModel(FlameShot, x, y, z, yaw, partialTick);
	}

	public void renderEntityModel(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		this.model.pt = partialTick;
		EntityScythe ent = (EntityScythe) entity;

		if (ent.Shooter != null) {
			double ax = ent.posX - ent.Shooter.posX;
			double az = ent.posZ - ent.Shooter.posZ;
			double p = ax / az;
			double angle = Math.toDegrees(Math.atan(p));

			if (ax < 0 && az < 0) {
				yaw = -(float) angle;
			
			} else if (ax > 0 && az > 0) {
				yaw = -((float) angle - 180);
			
			} else if (ax > 0 && az < 0) {
				yaw = -(float) angle;
				
			} else if (ax < 0 && az > 0) {
				yaw = -((float) angle + 180);
				
			}
			//System.out.println(yaw);
		}
		

		GL11.glPushMatrix();
		float scale = 1;
		bindTexture(getEntityTexture(ent));
		GL11.glTranslated(x, y + 1, z);
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(-yaw, 0, 1F, 0);

		model.render(ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}