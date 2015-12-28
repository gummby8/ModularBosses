package com.Splosions.ModularBosses.client.render.entity;

import java.util.logging.Level;

import com.Splosions.ModularBosses.client.entity.EntityParagon;
import com.Splosions.ModularBosses.client.entity.MBEntityPart;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderParagon extends RenderLiving {
	
	
    public RenderParagon(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
	}
    
    
    public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityParagon)entity, x, y, z, p_76986_8_, partialTicks);
    }
    
    public void doRender(EntityParagon entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
    	super.doRender((EntityLiving)entity, x, y, z, p_76986_8_, partialTicks);
    	
    			
    			renderDebugBoundingBox(entity.paragonPartFurnace, x, y, z, p_76986_8_, partialTicks, entity.paragonPartFurnace.PPosX, entity.paragonPartFurnace.PPosY, entity.paragonPartFurnace.PPosZ);
    			renderDebugBoundingBox(entity.paragonPartRKnee, x, y, z, p_76986_8_, partialTicks, entity.paragonPartRKnee.PPosX, entity.paragonPartRKnee.PPosY, entity.paragonPartRKnee.PPosZ);
    			renderDebugBoundingBox(entity.paragonPartLKnee, x, y, z, p_76986_8_, partialTicks, entity.paragonPartLKnee.PPosX, entity.paragonPartLKnee.PPosY, entity.paragonPartLKnee.PPosZ);
    		
    
    }
    

    
    
    private void renderDebugBoundingBox(MBEntityPart p_85094_1_, double p_85094_2_, double p_85094_4_, double p_85094_6_, float p_85094_8_, float p_85094_9_, double xOff, double yOff, double zOff)
    {
    	
    	
    	
    	
    	GlStateManager.depthMask(false);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        float f2 = p_85094_1_.width / 2.0F;
        AxisAlignedBB axisalignedbb = p_85094_1_.getEntityBoundingBox();
        AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX - p_85094_1_.posX + p_85094_2_ + xOff, axisalignedbb.minY - p_85094_1_.posY + p_85094_4_ + yOff, axisalignedbb.minZ - p_85094_1_.posZ + p_85094_6_ + zOff, axisalignedbb.maxX - p_85094_1_.posX + p_85094_2_ + xOff, axisalignedbb.maxY - p_85094_1_.posY + p_85094_4_ + yOff, axisalignedbb.maxZ - p_85094_1_.posZ + p_85094_6_ + zOff);
        RenderGlobal.drawOutlinedBoundingBox(axisalignedbb1, 16777215);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        Vec3 vec3 = p_85094_1_.getLook(p_85094_9_);
        worldrenderer.startDrawing(3);
        worldrenderer.setColorOpaque_I(255);
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
    }
    
    
    
	

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
	  return new ResourceLocation("mb:textures/mobs/Paragon.png");
	}
	
	
	
	
	
}
