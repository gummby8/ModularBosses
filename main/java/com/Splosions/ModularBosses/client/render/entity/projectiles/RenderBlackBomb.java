package com.Splosions.ModularBosses.client.render.entity.projectiles;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.entity.projectile.EntityBlackBomb;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBlackBomb extends Render {

	public RenderBlackBomb(RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		float scale = (float) entity.ticksExisted * 0.0002F;
		miniSun((EntityBlackBomb)entity, scale, x, y, z);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	

    protected void miniSun(EntityBlackBomb entity, float scale, double x, double y, double z) {
            float rotation = (float)(1720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

            GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			GlStateManager.scale(scale, scale, scale);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(rotation, 0.0F, 0.5F, 0.0F);
            GlStateManager.rotate(rotation / 2, 0.0F, 0.0F, 0.5F);
            renderDragonDeath(entity, 180, 0.0F, 0.0F, 0.0F);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
    }

    private void renderDragonDeath(EntityBlackBomb entity, float density, float x, float y, float z) {
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            RenderHelper.disableStandardItemLighting();
            float f7 = density / 100.0F;
            float f8 = 0.0F;

            if (f7 > 0.8F) f8 = (f7 - 0.8F) / 0.2F;

            Random random = new Random(432L);
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.enableBlend();
            //GlStateManager.blendFunc(770, 1);
            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_DST_COLOR);
            //GlStateManager.disableAlpha();
            GlStateManager.enableCull();
            GlStateManager.depthMask(false);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x, y, z);

            //for (int i = 0;(float) i < (f7 + f7 * f7) / 2.0F * 60.0F; ++i) {
            for (int i = 0; i < 260.0F; ++i) {
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F + f7 * 90.0F, 0.0F, 0.0F, 1.0F);
                    worldrenderer.startDrawing(6);
                    float f9 = random.nextFloat() * 20.0F + 5.0F + f8 * 10.0F;
                    float f10 = random.nextFloat() * 2.0F + 1.0F + f8 * 2.0F;
                    
      
                    worldrenderer.setColorRGBA(0,0,0, 255);
                    worldrenderer.addVertex(0.0D, 0.0D, 0.0D);
                    worldrenderer.setColorRGBA(0,0,0,0);	
                   

                    worldrenderer.addVertex(-0.866D * (double) f10, (double) f9, (double)(-0.5F * f10));
                    worldrenderer.addVertex(0.866D * (double) f10, (double) f9, (double)(-0.5F * f10));
                    worldrenderer.addVertex(0.0D, (double) f9, (double)(1.0F * f10));
                    worldrenderer.addVertex(-0.866D * (double) f10, (double) f9, (double)(-0.5F * f10));
                    tessellator.draw();
            }

            GlStateManager.popMatrix();
            GlStateManager.depthMask(true);
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.shadeModel(7424);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableTexture2D();
            GlStateManager.enableAlpha();
            RenderHelper.enableStandardItemLighting();
    }

	

}
