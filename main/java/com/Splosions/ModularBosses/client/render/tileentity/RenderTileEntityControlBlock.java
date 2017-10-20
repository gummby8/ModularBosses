package com.Splosions.ModularBosses.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;



public class RenderTileEntityControlBlock extends TileEntitySpecialRenderer{
	
	 ResourceLocation image = new ResourceLocation("mb:textures/blocks/portal_block.png");
	
    public RenderTileEntityControlBlock(){

    
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f, int number){
    	
    	TileEntityControlBlock te = (TileEntityControlBlock) tileEntity;
       
       
        this.bindTexture(image);
        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.005F,y,z + 0.005F);
        GL11.glScalef(0.99f, 0.99f, 0.99f);
        
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
		RenderGlobal.drawOutlinedBoundingBox(axisalignedbb1, 16777215);
		Tessellator tessellator = Tessellator.getInstance();
		
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
	
		worldrenderer.startDrawing(3);
		worldrenderer.setColorOpaque_I(255);
	
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
	}
    
    

}