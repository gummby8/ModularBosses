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

import com.Splosions.ModularBosses.client.models.projectiles.ModelEnergyArrow;
import com.Splosions.ModularBosses.entity.projectile.EntityEnergyArrow;




@SideOnly(Side.CLIENT)
public class RenderEnergyArrow extends Render
{
	protected ModelBase model;
	
	private static final ResourceLocation textureglow = new ResourceLocation("modularbosses", "textures/projectiles/BlueWave.png");

	public RenderEnergyArrow(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelEnergyArrow();
	}


	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		renderEntityModel(entity, x, y, z, yaw, partialTick);
	}

	public void renderEntityModel(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		EntityEnergyArrow ent = (EntityEnergyArrow) entity;
		//float Yaw = Blue.Shooter.FireingYaw;
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glPushMatrix();
		float scale = ent.getScale() / 100;
	    this.bindTexture(textureglow);
        float f1 = 1.0F;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

        if (ent.isInvisible())
        {
            GL11.glDepthMask(false);
        }
        else
        {
            GL11.glDepthMask(true);
        }

        char c0 = 61680;
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
		
		GL11.glTranslated(x, y, z);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(scale, scale, scale);
		
		//set rotation to mob
		GL11.glRotatef(yaw, 0, 1F, 0);
		GL11.glRotatef(180, 1F, 0, 0);
		
		
		//GL11.glTranslatef(0.0F, -1.0F, 0.0F);
		model.render(ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glPopAttrib();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}