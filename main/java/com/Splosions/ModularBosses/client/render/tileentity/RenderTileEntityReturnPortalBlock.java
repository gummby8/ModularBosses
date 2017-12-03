package com.Splosions.ModularBosses.client.render.tileentity;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.blocks.tileentity.TileEntityPortalBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityReturnPortalBlock;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityReturnPortalBlock extends TileEntitySpecialRenderer<TileEntityReturnPortalBlock> {

	ResourceLocation image = new ResourceLocation("mb:textures/blocks/portal_block.png");

	public RenderTileEntityReturnPortalBlock() {

	}

	@Override
	public void render(TileEntityReturnPortalBlock tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		TileEntityReturnPortalBlock te = (TileEntityReturnPortalBlock) tileEntity;

		// System.out.println(te.red);

		this.bindTexture(image);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		GL11.glPushMatrix();
		GL11.glTranslated(x - 0.005F, y, z - 0.005F);
		GL11.glScalef(1.01f, 1.01f, 1.01f);

		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

		bufferbuilder.normal(0, 0, -1);
		bufferbuilder.pos(1.0, 0.0, 0.0).tex( 0.0, 1.0);
		bufferbuilder.pos(0.0, 0.0, 0.0).tex( 1.0, 1.0);
		bufferbuilder.pos(0.0, 1.0, 0.0).tex(1.0, 0.0);
		bufferbuilder.pos(1.0, 1.0, 0.0).tex(0.0, 0.0);
		// +Z
		bufferbuilder.normal(0, 0, 1);
		bufferbuilder.pos(0.0, 0.0, 1.0).tex(0.0, 1.0);
		bufferbuilder.pos(1.0, 0.0, 1.0).tex(1.0, 1.0);
		bufferbuilder.pos(1.0, 1.0, 1.0).tex(1.0, 0.0);
		bufferbuilder.pos(0.0, 1.0, 1.0).tex(0.0, 0.0);
		// -X
		bufferbuilder.normal(-1, 0, 0);
		bufferbuilder.pos(0.0, 0.0, 0.0).tex(0.0, 1.0);
		bufferbuilder.pos(0.0, 0.0, 1.0).tex(1.0, 1.0);
		bufferbuilder.pos(0.0, 1.0, 1.0).tex(1.0, 0.0);
		bufferbuilder.pos(0.0, 1.0, 0.0).tex(0.0, 0.0);
		// +X
		bufferbuilder.normal(1, 0, 0);
		bufferbuilder.pos(1.0, 0.0, 1.0).tex(0.0, 1.0);
		bufferbuilder.pos(1.0, 0.0, 0.0).tex(1.0, 1.0);
		bufferbuilder.pos(1.0, 1.0, 0.0).tex(1.0, 0.0);
		bufferbuilder.pos(1.0, 1.0, 1.0).tex(0.0, 0.0);
		// -Y
		bufferbuilder.normal(0, -1, 0);
		bufferbuilder.pos(0.0, 0.0, 1.0).tex(0.0, 1.0);
		bufferbuilder.pos(0.0, 0.0, 0.0).tex(1.0, 1.0);
		bufferbuilder.pos(1.0, 0.0, 0.0).tex(1.0, 0.0);
		bufferbuilder.pos(1.0, 0.0, 1.0).tex(0.0, 0.0);
		// +Y
		bufferbuilder.normal(0, 1, 0);
		bufferbuilder.pos(1.0, 1.0, 1.0).tex(0.0, 1.0);
		bufferbuilder.pos(1.0, 1.0, 0.0).tex(1.0, 1.0);
		bufferbuilder.pos(0.0, 1.0, 0.0).tex(1.0, 0.0);
		bufferbuilder.pos(0.0, 1.0, 1.0).tex(0.0, 0.0);

		tessellator.draw();

		if (tileEntity.getWorld().isBlockPowered(tileEntity.getPos())) {
			miniSun(0.1F, 0, 0, 0);
		}

		GL11.glPopMatrix();
	}

	protected void miniSun(float scale, float x, float y, float z) {
		float rotation = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5F, y, z + 0.5F);
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.pushMatrix();
		GlStateManager.rotate(rotation, 0.0F, 1F, 0.0F);
		// GlStateManager.rotate(rotation / 2, 0.0F, 0.0F, 0.5F);
		renderDragonDeath(180, 0.0F, 0.0F, 0.0F);
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
	}

	private void renderDragonDeath(float density, float x, float y, float z) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		RenderHelper.disableStandardItemLighting();
		float f7 = density / 200.0F;
		float f8 = 0.0F;

		if (f7 > 0.8F)
			f8 = (f7 - 0.8F) / 0.2F;

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

		for (int i = 0; i < 4; ++i) {
			GlStateManager.rotate(1 * 360.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(90, 0.0F, 1.0F, 0.0F);
			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
			float f9 = 1 * 20.0F + 5.0F + f8 * 10.0F;
			float f10 = 1 * 2.0F + 1.0F + f8 * 21.0F;
			bufferbuilder.color(0, 0, 255, 255);
			bufferbuilder.pos(0.0D, 0.0D, 0.0D);
			bufferbuilder.color(0, 255, 0, 0);
			bufferbuilder.pos(-0.866D * (double) f10, (double) f9, (double) (-0.5F * f10));
			bufferbuilder.pos(0.866D * (double) f10, (double) f9, (double) (-0.5F * f10));
			bufferbuilder.pos(-0.866D * (double) f10, (double) f9, (double) (-0.5F * f10));
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