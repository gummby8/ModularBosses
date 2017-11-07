package com.Splosions.ModularBosses.client.render.entity.projectiles;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.projectiles.ModelEnergyClaw;
import com.Splosions.ModularBosses.entity.projectile.EntityEnergyClaw;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;




@SideOnly(Side.CLIENT)
public class RenderEnergyClaw extends Render
{
	protected ModelBase model;
	
	private static final ResourceLocation textureglow = new ResourceLocation("mb:textures/projectiles/FlameThrower.png");

	public RenderEnergyClaw(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelEnergyClaw();
	}


	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		renderEntityModel1(entity, x, y, z, yaw, partialTick);
	}
	
	public void renderEntityModel1(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		EntityEnergyClaw ent = (EntityEnergyClaw) entity;
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
       // GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_DST_COLOR);
		float scale = 1;
		this.bindTexture(textureglow);
		GL11.glTranslated(x, y, z);
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(yaw, 0, 1F, 0);
		GL11.glRotatef(-ent.rotationPitch, 1, 0, 0);
		GL11.glTranslated(0F, 0.05F, 0.5F);
		//GL11.glRotatef(90, 1F, 0, 0);
		model.render(ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
        GL11.glDisable(GL11.GL_BLEND);
        //GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}


}