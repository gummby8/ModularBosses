package com.Splosions.ModularBosses.client.render.entity.projectiles;

import java.util.Random;

import com.Splosions.ModularBosses.entity.projectile.EntityBrainEnergy;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBrainEnergy extends Render {

	public RenderBrainEnergy(RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		miniSun((EntityBrainEnergy)entity, 0.02F, x, y + 1, z);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	

    protected void miniSun(EntityBrainEnergy spark, float scale, double x, double y, double z) {
            float rotation = (float)(1720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

            GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			GlStateManager.scale(scale, scale, scale);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(rotation, 0.0F, 0.5F, 0.0F);
            GlStateManager.rotate(rotation / 2, 0.0F, 0.0F, 0.5F);
            renderDragonDeath(spark, 180, 0.0F, 0.0F, 0.0F);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
    }

    private void renderDragonDeath(EntityBrainEnergy spark, float density, float x, float y, float z) {
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            RenderHelper.disableStandardItemLighting();
            float f7 = density / 200.0F;
            float f8 = 0.0F;

            if (f7 > 0.8F) f8 = (f7 - 0.8F) / 0.2F;

            Random random = new Random(432L);
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 1);
            GlStateManager.disableAlpha();
            GlStateManager.enableCull();
            GlStateManager.depthMask(false);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x, y, z);

            for (int i = 0;
            (float) i < (f7 + f7 * f7) / 2.0F * 60.0F; ++i) {
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F + f7 * 90.0F, 0.0F, 0.0F, 1.0F);
                    worldrenderer.startDrawing(6);
                    float f9 = random.nextFloat() * 20.0F + 5.0F + f8 * 10.0F;
                    float f10 = random.nextFloat() * 2.0F + 1.0F + f8 * 2.0F;
                    
      
                    worldrenderer.setColorRGBA(255,255,200, (int)(255.0F * (1.0F - f8)));
                    worldrenderer.addVertex(0.0D, 0.0D, 0.0D);
                    worldrenderer.setColorRGBA(255,144,0,0);	
                   

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
