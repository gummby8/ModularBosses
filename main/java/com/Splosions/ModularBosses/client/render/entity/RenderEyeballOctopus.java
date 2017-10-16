package com.Splosions.ModularBosses.client.render.entity;

import java.util.logging.Level;

import com.Splosions.ModularBosses.entity.EntityEyeballOctopus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

public class RenderEyeballOctopus extends RenderLiving {

	private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
	ResourceLocation texture = new ResourceLocation("mb:textures/mobs/EyeballOctopus.png");

	
	public RenderEyeballOctopus(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return texture;
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
		EntityEyeballOctopus octo = (EntityEyeballOctopus) entity;
		if (octo.target != null && octo.attackCounter > 0) {
			this.drawRechargeRay(octo, x, y, z, partialTicks);
		}
	}

	/**
	 * Draws the ray from the dragon to it's crystal
	 */
	protected void drawRechargeRay(EntityMob ent, double x, double y, double z, float partialTicks) {
		EntityEyeballOctopus octo = (EntityEyeballOctopus) ent;
		float f1 = (float) 10 + partialTicks;
		float f2 = MathHelper.sin(f1 * 0F) / 2.0F + 0.9F;
		f2 = (f2 * f2 + f2) * 0.4F;
		float f3 = (float) (octo.target.posX - ent.posX - (ent.prevPosX - ent.posX) * (double) (1.0F - partialTicks));
		float f4 = (float) ((double) f2 + octo.target.posY - 0.5D - ent.posY - (ent.prevPosY - ent.posY) * (double) (1.0F - partialTicks));
		float f5 = (float) (octo.target.posZ - ent.posZ - (ent.prevPosZ - ent.posZ) * (double) (1.0F - partialTicks));
		float f6 = MathHelper.sqrt(f3 * f3 + f5 * f5);
		float f7 = MathHelper.sqrt(f3 * f3 + f4 * f4 + f5 * f5);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.8F, (float) z);
		GlStateManager.rotate((float) (-Math.atan2((double) f5, (double) f3)) * 180.0F / (float) Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((float) (-Math.atan2((double) f6, (double) f4)) * 180.0F / (float) Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableCull();
		this.bindTexture(enderDragonCrystalBeamTextures);
		GlStateManager.shadeModel(GL11.GL_FLAT);

		float f8 = 0.0F - ((float) ent.ticksExisted + partialTicks) * 0.01F;
		float f9 = MathHelper.sqrt(f3 * f3 + f4 * f4 + f5 * f5) / -32.0F - ((float) ent.ticksExisted + partialTicks) * 0.01F; // set
																							// reverse
																																	// beam
        bufferbuilder.begin(5, DefaultVertexFormats.POSITION_TEX_COLOR);
        int i = 8;

        for (int j = 0; j <= 8; ++j)
        {
            float f71 = MathHelper.sin((float)(j % 8) * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
            float f81 = MathHelper.cos((float)(j % 8) * ((float)Math.PI * 2F) / 8.0F) * 0.75F;
            float f91 = (float)(j % 8) / 8.0F;
            bufferbuilder.pos((double)(f71 * 0.2F), (double)(f81 * 0.2F), 0.0D).tex((double)f91, (double)f5).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos((double)f71, (double)f81, (double)f4).tex((double)f91, (double)f6).color(255, 255, 255, 255).endVertex();
        }

		tessellator.draw();
		GlStateManager.enableCull();
		GlStateManager.shadeModel(7424);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();
	}

}
