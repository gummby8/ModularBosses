package com.Splosions.ModularBosses.client.render.entity;

import java.util.logging.Level;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.entity.ModelMoldormAlpha;
import com.Splosions.ModularBosses.client.models.entity.ModelTeleportBiped;
import com.Splosions.ModularBosses.entity.EntityMoldormAlpha;
import com.Splosions.ModularBosses.entity.EntityParagon;
import com.Splosions.ModularBosses.entity.EntityTeleportBiped;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMoldormAlpha extends Render {
	
	protected ModelBase model;
	
    public RenderMoldormAlpha(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager);
		this.model = new ModelMoldormAlpha();
    }
	
    
    @Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
    	//super.doRender(entity, x, y, z, 0, partialTicks);
    	EntityMoldormAlpha ent = (EntityMoldormAlpha) entity;
    	
		GL11.glPushMatrix();
		bindTexture(getEntityTexture(ent));
		GL11.glTranslated(x, y + 1, z);
		GL11.glScalef(1, 1, 1);
		GL11.glRotatef(180, 1, 0, 0);
				
		model.render(ent, yaw, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
		
		
		GL11.glPopMatrix();

	}
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
				
		
		return new ResourceLocation("mb:textures/mobs/Moldorm.png");
	}
	
}
