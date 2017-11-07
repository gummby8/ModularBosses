package com.Splosions.ModularBosses.client.render.entity;


import com.Splosions.ModularBosses.entity.EntityGolem;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



@SideOnly(Side.CLIENT)
public class RenderGolem extends RenderLiving
{


    public RenderGolem(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
 
    }
    

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getGolemTexture((EntityGolem) entity);
	}

	protected ResourceLocation getGolemTexture(EntityGolem entity) {
		return entity.textureLoc;
	}


}