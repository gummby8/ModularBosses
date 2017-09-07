package com.Splosions.ModularBosses.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.blocks.tileentity.TileEntityPortalBlock;
import com.Splosions.ModularBosses.client.models.projectiles.ModelCartographer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityPortalBlock extends TileEntitySpecialRenderer {

	ResourceLocation image = new ResourceLocation("mb:textures/blocks/portal_block.png");

	public RenderTileEntityPortalBlock() {

	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f, int number) {
		TileEntityPortalBlock te = (TileEntityPortalBlock) tileEntity;

		// System.out.println(te.red);
		GL11.glColor3f(te.red * 0.004f, te.green * 0.004f, te.blue * 0.004f);
		this.bindTexture(image);
		Tessellator tessellator = Tessellator.getInstance();
		GL11.glPushMatrix();
		GL11.glTranslated(x - 3.005f, y - 0.005f, z - 3.005f);
		GL11.glScalef(7.01f, 1.01f, 7.01f);

		tessellator.getWorldRenderer().startDrawingQuads();

		tessellator.getWorldRenderer().setNormal(0, 0, -1);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 0.0, 0.0, 0.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 0.0, 0.0, 1.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 1.0, 0.0, 1.0, 0.0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 1.0, 0.0, 0.0, 0.0);
		// +Z
		tessellator.getWorldRenderer().setNormal(0, 0, 1);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 0.0, 1.0, 0.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 0.0, 1.0, 1.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 1.0, 1.0, 1.0, 0.0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 1.0, 1.0, 0.0, 0.0);
		// -X
		tessellator.getWorldRenderer().setNormal(-1, 0, 0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 0.0, 0.0, 0.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 0.0, 1.0, 1.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 1.0, 1.0, 1.0, 0.0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 1.0, 0.0, 0.0, 0.0);
		// +X
		tessellator.getWorldRenderer().setNormal(1, 0, 0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 0.0, 1.0, 0.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 0.0, 0.0, 1.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 1.0, 0.0, 1.0, 0.0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 1.0, 1.0, 0.0, 0.0);
		// -Y
		tessellator.getWorldRenderer().setNormal(0, -1, 0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 0.0, 1.0, 0.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 0.0, 0.0, 1.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 0.0, 0.0, 1.0, 0.0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 0.0, 1.0, 0.0, 0.0);
		// +Y
		tessellator.getWorldRenderer().setNormal(0, 1, 0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 1.0, 1.0, 0.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(1.0, 1.0, 0.0, 1.0, 1.0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 1.0, 0.0, 1.0, 0.0);
		tessellator.getWorldRenderer().addVertexWithUV(0.0, 1.0, 1.0, 0.0, 0.0);

		tessellator.draw();

		GL11.glPopMatrix();
	}

}