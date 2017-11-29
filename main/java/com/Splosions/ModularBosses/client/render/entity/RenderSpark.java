package com.Splosions.ModularBosses.client.render.entity;

import java.util.Random;

import com.Splosions.ModularBosses.entity.EntitySpark;

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
import net.minecraft.util.ResourceLocation;

public class RenderSpark extends RenderLiving {

	public RenderSpark(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
	}

	public void doRender(EntityLiving entity, double x, double y, double z, float yaw, float partialTicks) {
		miniSun((EntitySpark) entity, 0.04F, x, y + 1, z, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

	protected void miniSun(EntitySpark spark, float scale, double x, double y, double z, float partialTicks) {
		float rotation = (float) (1720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.pushMatrix();
		GlStateManager.rotate(rotation, 0.0F, 0.5F, 0.0F);
		GlStateManager.rotate(rotation / 2, 0.0F, 0.0F, 0.5F);
		renderDragonDeath(spark, 180, 0.0F, 0.0F, 0.0F, partialTicks);
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
	}

	private void renderDragonDeath(EntitySpark spark, float density, float x, float y, float z, float partialTicks) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		RenderHelper.disableStandardItemLighting();
		float f = ((float) spark.ticksExisted + partialTicks) / 200.0F;
		float f1 = 0.0F;

		if (f > 0.8F) {
			f1 = (f - 0.8F) / 0.2F;
		}

		Random random = new Random(432L);
		GlStateManager.disableTexture2D();
		GlStateManager.shadeModel(7425);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
		GlStateManager.disableAlpha();
		GlStateManager.enableCull();
		GlStateManager.depthMask(false);
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, -1.0F, -2.0F);

		for (int i = 0; (float) i < (f + f * f) / 2.0F * 60.0F; ++i) {
			GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
			float f2 = random.nextFloat() * 20.0F + 5.0F + f1 * 10.0F;
			float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);

			if (spark.variant == spark.ORANGE) {
				bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(255, 115, 0, (int) (255.0F * (1.0F - f1))).endVertex();
			} else if (spark.variant == spark.BLUE) {
				bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(0, 255, 255, (int) (255.0F * (1.0F - f1))).endVertex();
			} else if (spark.variant == spark.GREEN) {
				bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(0, 0, 255, (int) (255.0F * (1.0F - f1))).endVertex();
			} else if (spark.variant == spark.PURPLE) {
				bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(102, 0, 204, (int) (255.0F * (1.0F - f1))).endVertex();
			}

			bufferbuilder.pos(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(255, 0, 255, 0).endVertex();
			bufferbuilder.pos(0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(255, 0, 255, 0).endVertex();
			bufferbuilder.pos(0.0D, (double) f2, (double) (1.0F * f3)).color(255, 0, 255, 0).endVertex();
			bufferbuilder.pos(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(255, 0, 255, 0).endVertex();
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
