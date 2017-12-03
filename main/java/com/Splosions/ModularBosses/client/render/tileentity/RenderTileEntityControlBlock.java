package com.Splosions.ModularBosses.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityReturnPortalBlock;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;




public class RenderTileEntityControlBlock extends TileEntitySpecialRenderer<TileEntityControlBlock> {
	
	 ResourceLocation image = new ResourceLocation("mb:textures/blocks/portal_block.png");
	
    public RenderTileEntityControlBlock(){

    
    }

    @Override
    public void render(TileEntityControlBlock tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
    	
    	TileEntityControlBlock te = (TileEntityControlBlock) tileEntity;
       
       
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
        

        if(te.showBorder){
        	renderDebugBoundingBox(te, x, y, z);	
        }
        
        
        GL11.glPopMatrix();
        
        
    }

    
	private void renderDebugBoundingBox(TileEntityControlBlock te, double x, double y, double z) {

		GlStateManager.depthMask(false);
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.disableBlend();
		
		float f2 = 2.0F;
		
		AxisAlignedBB axisalignedbb = te.getRenderBoundingBox().expand(0.5f, 0.5f, 0.5f);
		AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(
				axisalignedbb.minX - te.getPos().getX() + x + 0.5F + (te.xOff * 2),
				axisalignedbb.minY - te.getPos().getY() + y + 0.5F + (te.yOff * 2),
				axisalignedbb.minZ - te.getPos().getZ() + z + 0.5F + (te.zOff * 2),
				axisalignedbb.maxX - te.getPos().getX() + x + 0.5F - (te.xOff * 2),
				axisalignedbb.maxY - te.getPos().getY() + y + 0.2F,
				axisalignedbb.maxZ - te.getPos().getZ() + z + 0.5F - (te.zOff * 2));
		RenderGlobal.drawSelectionBoundingBox(axisalignedbb1,  255,  255,  255,  1);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		
		bufferbuilder.begin(3, DefaultVertexFormats.POSITION_TEX_COLOR);
		
		bufferbuilder.color(255, 255, 255, 255);
	
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
	}
    
    

}