package com.Splosions.ModularBosses.client.render.entity;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
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
import com.Splosions.ModularBosses.client.models.entity.ModelSandWormBody;
import com.Splosions.ModularBosses.client.models.entity.ModelSandWormTail;
import com.Splosions.ModularBosses.entity.EntityChorpChorp;
import com.Splosions.ModularBosses.entity.EntityEyeballOctopus;
import com.Splosions.ModularBosses.entity.EntitySandWormTail;





@SideOnly(Side.CLIENT)
public class RenderSandWormTail extends Render
{
	ResourceLocation rec = new ResourceLocation("mb:textures/mobs/SandWormSegment.png");
	protected ModelBase model;
	
	public RenderSandWormTail(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelSandWormTail();
	}


	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		EntitySandWormTail worm = (EntitySandWormTail) entity;
		GL11.glPushMatrix();
		float scale = 3;
		bindTexture(getEntityTexture(worm));
		GL11.glTranslated(x, y - 5, z);
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(-worm.yaw + 90, 0, 1, 0);
		GL11.glRotatef(-worm.pitch, 0, 0, 1);
		model.render(worm, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
		GL11.glPopMatrix();
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return rec;
	}

}
