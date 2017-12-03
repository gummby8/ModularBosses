package com.Splosions.ModularBosses.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.blocks.tileentity.TileEntityPortalBlock;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityPortalBlock extends TileEntitySpecialRenderer<TileEntityPortalBlock> {

	ResourceLocation image = new ResourceLocation("mb:textures/blocks/portal_block.png");

	public RenderTileEntityPortalBlock() {

	}

	@Override
	public void render(TileEntityPortalBlock tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		TileEntityPortalBlock te = (TileEntityPortalBlock) tileEntity;

		// System.out.println(te.red);
		GL11.glColor3f(te.red * 0.004f, te.green * 0.004f, te.blue * 0.004f);
		this.bindTexture(image);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		GL11.glPushMatrix();
		GL11.glTranslated(x - 3.005f, y - 0.005f, z - 3.005f);
		GL11.glScalef(7.01f, 1.01f, 7.01f);

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

		GL11.glPopMatrix();
	}

}