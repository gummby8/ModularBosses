package com.Splosions.ModularBosses.client.render.entity.projectiles;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.projectiles.ModelBoulder;
import com.Splosions.ModularBosses.entity.projectile.EntityBoulder;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



@SideOnly(Side.CLIENT)
public class RenderBoulder extends Render
{
	protected ModelBase model;
	


	public RenderBoulder(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelBoulder();
	}


	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		renderEntityModel(entity, x, y, z, yaw, partialTick);
	}

	public void renderEntityModel(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		EntityBoulder ent = (EntityBoulder) entity;
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
		return getBoulderTexture((EntityBoulder) entity);
	}

	protected ResourceLocation getBoulderTexture(EntityBoulder entity) {
		return entity.textureLoc;
	}
}