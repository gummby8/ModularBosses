package com.Splosions.ModularBosses.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.entity.EntityShadeHowler;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderShadeHowler extends RenderLiving {

	ResourceLocation rec = new ResourceLocation("mb:textures/mobs/ShadeHowler.png");
	

	private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");

	public RenderShadeHowler(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return rec;
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
		EntityShadeHowler shade = (EntityShadeHowler) entity;

		
			if (shade.aniID == shade.HOWL && shade.aniFrame > 4 && shade.aniFrame < 30 ){
				//this.drawRay(shade, x, y - 0.7F, z, partialTicks);
				/**
				this.bindTexture(ENDERCRYSTAL_BEAM_TEXTURES);
	            float f = MathHelper.sin(((float)entity.healingEnderCrystal.ticksExisted + partialTicks) * 0.2F) / 2.0F + 0.5F;
	            f = (f * f + f) * 0.2F;
	            renderCrystalBeams(x, y, z, partialTicks, entity.posX + (entity.prevPosX - entity.posX) * (double)(1.0F - partialTicks), entity.posY + (entity.prevPosY - entity.posY) * (double)(1.0F - partialTicks), entity.posZ + (entity.prevPosZ - entity.posZ) * (double)(1.0F - partialTicks), entity.ticksExisted, entity.healingEnderCrystal.posX, (double)f + entity.healingEnderCrystal.posY, entity.healingEnderCrystal.posZ);
				*/
			}
		
	}
	
	


	/**
	 * Draws the ray from the dragon to it's crystal
	 */
	 public static void renderCrystalBeams(double p_188325_0_, double p_188325_2_, double p_188325_4_, float p_188325_6_, double p_188325_7_, double p_188325_9_, double p_188325_11_, int p_188325_13_, double p_188325_14_, double p_188325_16_, double p_188325_18_)
	    {
	        float f = (float)(p_188325_14_ - p_188325_7_);
	        float f1 = (float)(p_188325_16_ - 1.0D - p_188325_9_);
	        float f2 = (float)(p_188325_18_ - p_188325_11_);
	        float f3 = MathHelper.sqrt(f * f + f2 * f2);
	        float f4 = MathHelper.sqrt(f * f + f1 * f1 + f2 * f2);
	        GlStateManager.pushMatrix();
	        GlStateManager.translate((float)p_188325_0_, (float)p_188325_2_ + 2.0F, (float)p_188325_4_);
	        GlStateManager.rotate((float)(-Math.atan2((double)f2, (double)f)) * (180F / (float)Math.PI) - 90.0F, 0.0F, 1.0F, 0.0F);
	        GlStateManager.rotate((float)(-Math.atan2((double)f3, (double)f1)) * (180F / (float)Math.PI) - 90.0F, 1.0F, 0.0F, 0.0F);
	        Tessellator tessellator = Tessellator.getInstance();
	        BufferBuilder bufferbuilder = tessellator.getBuffer();
	        RenderHelper.disableStandardItemLighting();
	        GlStateManager.disableCull();
	        GlStateManager.shadeModel(7425);
	        float f5 = 0.0F - ((float)p_188325_13_ + p_188325_6_) * 0.01F;
	        float f6 = MathHelper.sqrt(f * f + f1 * f1 + f2 * f2) / 32.0F - ((float)p_188325_13_ + p_188325_6_) * 0.01F;
	        bufferbuilder.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);
	        int i = 8;

	        for (int j = 0; j <= 8; ++j)
	        {
	            float f7 = MathHelper.sin((float)(j % 8) * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
	            float f8 = MathHelper.cos((float)(j % 8) * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
	            float f9 = (float)(j % 8) / 8.0F;
	            bufferbuilder.pos((double)(f7 * 0.2F), (double)(f8 * 0.2F), 0.0D).tex((double)f9, (double)f5).color(0, 0, 0, 255).endVertex();
	            bufferbuilder.pos((double)f7, (double)f8, (double)f4).tex((double)f9, (double)f6).color(255, 255, 255, 255).endVertex();
	        }

	        tessellator.draw();
	        GlStateManager.enableCull();
	        GlStateManager.shadeModel(7424);
	        RenderHelper.enableStandardItemLighting();
	        GlStateManager.popMatrix();
	    }


}
