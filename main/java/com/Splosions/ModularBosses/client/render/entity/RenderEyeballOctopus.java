package com.Splosions.ModularBosses.client.render.entity;

import java.util.logging.Level;

import com.Splosions.ModularBosses.entity.EntityEyeballOctopus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEyeballOctopus extends RenderLiving {

	private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");

	public RenderEyeballOctopus(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return new ResourceLocation("mb:textures/mobs/EyeballOctopus.png");
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
		float f6 = MathHelper.sqrt_float(f3 * f3 + f5 * f5);
		float f7 = MathHelper.sqrt_float(f3 * f3 + f4 * f4 + f5 * f5);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y + 0.8F, (float) z);
		GlStateManager.rotate((float) (-Math.atan2((double) f5, (double) f3)) * 180.0F / (float) Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((float) (-Math.atan2((double) f6, (double) f4)) * 180.0F / (float) Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableCull();
		this.bindTexture(enderDragonCrystalBeamTextures);
		GlStateManager.shadeModel(GL11.GL_FLAT);

		float f8 = 0.0F - ((float) ent.ticksExisted + partialTicks) * 0.01F;
		float f9 = MathHelper.sqrt_float(f3 * f3 + f4 * f4 + f5 * f5) / -32.0F - ((float) ent.ticksExisted + partialTicks) * 0.01F; // set
																																	// the
																																	// 32
																																	// to
																																	// positive
																																	// to
																																	// reverse
																																	// beam
		worldrenderer.startDrawing(5);
		byte b0 = 8;

		for (int i = 0; i <= b0; ++i) {
			float f10 = MathHelper.sin((float) (i % b0) * (float) Math.PI * 2.0F / (float) b0) * 0.45F;
			float f11 = MathHelper.cos((float) (i % b0) * (float) Math.PI * 2.0F / (float) b0) * 0.45F;
			float f12 = 1;// (float) (i % b0) * 1.0F / (float) b0;

			worldrenderer.setColorOpaque_I(16711680);
			worldrenderer.addVertexWithUV((double) f10, (double) f11, (double) f7, (double) f12, (double) f8 + 0.2D); // 0.23D
																														// is
																														// the
																														// beam
																														// speed
			worldrenderer.addVertexWithUV((double) (f10 * 0.2F), (double) (f11 * 0.2F), 0.0D, (double) f12, (double) f9);

		}

		tessellator.draw();
		GlStateManager.enableCull();
		GlStateManager.shadeModel(7424);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();
	}

}
