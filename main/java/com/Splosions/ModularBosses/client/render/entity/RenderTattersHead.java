package com.Splosions.ModularBosses.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTattersHead extends RenderLiving {
	float CamDist;
	
    public RenderTattersHead(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
    }
	

    
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		CamDist = entity.getDistanceToEntity(Minecraft.getMinecraft().getRenderViewEntity());
		if (CamDist <= 10 && CamDist > 9){
			return new ResourceLocation("mb:textures/mobs/Tatters/90.png");
		} else 
		if (CamDist <= 9 && CamDist > 8){
			return new ResourceLocation("mb:textures/mobs/Tatters/80.png");
		} else	
		if (CamDist <= 8 && CamDist > 7){
			return new ResourceLocation("mb:textures/mobs/Tatters/70.png");
		} else 
		if (CamDist <= 7 && CamDist > 6){
			return new ResourceLocation("mb:textures/mobs/Tatters/60.png");
		} else
		if (CamDist <= 6 && CamDist > 5){
			return new ResourceLocation("mb:textures/mobs/Tatters/50.png");
		} else 
		if (CamDist < 5 && CamDist > 4){
			return new ResourceLocation("mb:textures/mobs/Tatters/40.png");
		} else	
		if (CamDist < 4 && CamDist > 3){
			return new ResourceLocation("mb:textures/mobs/Tatters/30.png");
		} else 
		if (CamDist < 3 && CamDist > 2){
			return new ResourceLocation("mb:textures/mobs/Tatters/20.png");
		} else 
		if (CamDist < 2 && CamDist > 1){
			return new ResourceLocation("mb:textures/mobs/Tatters/10.png");
		} else 
		if (CamDist < 1){
			return new ResourceLocation("mb:textures/mobs/Tatters/0.png");
		} else {
			return new ResourceLocation("mb:textures/mobs/Tatters/100.png");	
		}
	}
	
}
