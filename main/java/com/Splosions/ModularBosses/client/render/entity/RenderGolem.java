package com.Splosions.ModularBosses.client.render.entity;


import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.entity.ModelChorpChorp;
import com.Splosions.ModularBosses.entity.EntityChorpChorp;
import com.Splosions.ModularBosses.entity.EntityGolem;
import com.Splosions.ModularBosses.entity.EntityParagon;



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