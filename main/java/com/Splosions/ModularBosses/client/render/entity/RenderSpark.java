package com.Splosions.ModularBosses.client.render.entity;

import java.util.Random;
import java.util.logging.Level;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.entity.EntityParagon;
import com.Splosions.ModularBosses.entity.EntitySpark;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderSpark extends RenderLiving {

	public RenderSpark(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
	}

	public void doRender(EntityLiving entity, double x, double y, double z, float yaw, float partialTicks) {
		miniSun((EntitySpark)entity, 0.04F, x, y + 1, z);
	}



	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	

    protected void miniSun(EntitySpark spark, float scale, double x, double y, double z) {
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

    private void renderDragonDeath(EntitySpark spark, float density, float x, float y, float z) {
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

            for (int i = 0; (float) i < (f7 + f7 * f7) / 2.0F * 60.0F; ++i) {
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F + f7 * 90.0F, 0.0F, 0.0F, 1.0F);
                    worldrenderer.startDrawing(6);
                    float f9 = random.nextFloat() * 20.0F + 5.0F + f8 * 10.0F;
                    float f10 = random.nextFloat() * 2.0F + 1.0F + f8 * 2.0F;
                    
                    if (spark.variant == spark.ORANGE){
                        worldrenderer.setColorRGBA_I(16760576, (int)(255.0F * (1.0F - f8)));
                        worldrenderer.addVertex(0.0D, 0.0D, 0.0D);
                        worldrenderer.setColorRGBA_I(16711680, 0);
                    } else
                    if (spark.variant == spark.BLUE){
                        worldrenderer.setColorRGBA(0,255,255, (int)(255.0F * (1.0F - f8)));
                        worldrenderer.addVertex(0.0D, 0.0D, 0.0D);
                        worldrenderer.setColorRGBA(0,0,255,0);	
                    } else
                    if (spark.variant == spark.GREEN){
                        worldrenderer.setColorRGBA(0,0,255, (int)(255.0F * (1.0F - f8)));
                        worldrenderer.addVertex(0.0D, 0.0D, 0.0D);
                        worldrenderer.setColorRGBA(0,255,0,0);	
                    } else
                    if (spark.variant == spark.PURPLE){
                        worldrenderer.setColorRGBA(102,0,204, (int)(255.0F * (1.0F - f8)));
                        worldrenderer.addVertex(0.0D, 0.0D, 0.0D);
                        worldrenderer.setColorRGBA(0,0,204,0);	
                    }

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
