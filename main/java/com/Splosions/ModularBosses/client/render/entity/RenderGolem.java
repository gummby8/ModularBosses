package com.Splosions.ModularBosses.client.render.entity;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.entity.ModelChorpChorp;
import com.Splosions.ModularBosses.entity.EntityChorpChorp;



@SideOnly(Side.CLIENT)
public class RenderGolem extends RenderLiving
{


    public RenderGolem(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
 
    }
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return new ResourceLocation("minecraft:textures/blocks/dirt.png");
	}



}