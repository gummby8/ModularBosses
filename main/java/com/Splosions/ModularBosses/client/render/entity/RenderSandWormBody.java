package com.Splosions.ModularBosses.client.render.entity;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.culling.ICamera;
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
import com.Splosions.ModularBosses.entity.EntityEyeballOctopus;



@SideOnly(Side.CLIENT)
public class RenderSandWormBody extends RenderLiving
{
	ResourceLocation rec = new ResourceLocation("mb:textures/mobs/SandWormSegment.png");

    public RenderSandWormBody(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
		
 
    }
    
    @Override
    public boolean shouldRenderLiving(EntityLiving p_177104_1_, ICamera p_177104_2_, double p_177104_3_, double p_177104_5_, double p_177104_7_)
    {
    	return true;
    }
	
    @Override
    public boolean shouldRender(Entity entity, ICamera camera, double camX, double camY, double camZ){
    	return true;
    }


	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return rec;
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		super.doRender(entity, x, y, z, p_76986_8_, partialTicks);

		GL11.glPushMatrix();
		float scale = 5F;
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(0.0F, -10F, 0.0F);
		GL11.glRotated(90, 0, 1, 0);
		GL11.glPopMatrix();
	}

}