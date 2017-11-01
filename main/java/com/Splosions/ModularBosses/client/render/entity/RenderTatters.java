package com.Splosions.ModularBosses.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.entity.EntityTatters;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.ResourceLocation;

public class RenderTatters extends RenderLiving {
	
	private static final ResourceLocation rec = new ResourceLocation("mb:textures/mobs/Tatters.png");
    private static final ResourceLocation enderDragonExplodingTextures = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
	
    public RenderTatters(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
    }
	

    
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
				
		if (entity.getDistanceToEntity(Minecraft.getMinecraft().getRenderViewEntity()) > 10){
			
		
		}
		
		return rec;
	}
	
	
	
	
    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityTatters p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {

        
		GL11.glEnable(GL11.GL_BLEND);
		GlStateManager.color(1,1,1,0.9F);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.bindEntityTexture(p_77036_1_);
        this.mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
		GL11.glDisable(GL11.GL_BLEND);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
        
        
       
    }
	
    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        this.renderModel((EntityTatters)p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
    }
	
	
}
