package com.Splosions.ModularBosses.client.render.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.Splosions.ModularBosses.client.models.entity.ModelKnockdown;
import com.Splosions.ModularBosses.client.models.entity.ModelTeleportBiped;
import com.Splosions.ModularBosses.entity.EntityTeleportBiped;
import com.google.common.base.Objects;





@SideOnly(Side.CLIENT)
public class RenderTeliportBiped extends Render
{
	public static ModelTeleportBiped model;
	
	float rot = 0;
	float wScale = 1.3F;
	float hScale = 1.3F;
	float yOff = 0.5F;
	
	private  ResourceLocation texture = new ResourceLocation("mb:textures/mobs/blank.png");

	public RenderTeliportBiped(RenderManager renderManager) {
		super(renderManager);
		this.model = new ModelTeleportBiped(1,true);
		
		
		
	}


	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		renderEntityModel(entity, x, y, z, yaw, partialTick);
	}

	public void renderEntityModel(Entity entity, double x, double y, double z, float yaw, float partialTick) {
		EntityTeleportBiped ent = (EntityTeleportBiped) entity;
		
		//wScale = 1.3f;
		//hScale = 1.3f;
		//yOff = 0.5F;

		if (entity.ticksExisted <= 50 ){
		wScale = 1.3f - (entity.ticksExisted * 0.02F);
		wScale = (wScale <= 0.3F)? 0.3F : wScale ;
		
		hScale = 1.3f + (entity.ticksExisted * 0.02F);
		hScale = (hScale >= 2.7F)? 2.7F : hScale ;
		
		yOff = 0.5F + (entity.ticksExisted * 0.035F);
		yOff = (yOff >= 2F)? 2F : yOff ;
		}

		
		if (entity.ticksExisted >= 70 ){
		wScale = 1.3f - (entity.ticksExisted * 0.02F);
		wScale = (wScale <= 0.3F)? 0.3F : wScale ;
		
		hScale = 2.7f - ((entity.ticksExisted - 70) * 0.27F);
		hScale = (hScale >= 2.7F)? 2.7F : hScale ;
		
		yOff = 2F - ((entity.ticksExisted - 70) * 0.1F);
		yOff = (yOff >= 2F)? 2F : yOff ;
		}
		
		
		rot = entity.ticksExisted * entity.ticksExisted; 
		
		GL11.glPushMatrix();
		bindTexture(getEntityTexture(ent));
		GL11.glTranslated(x, y + yOff, z);
		GL11.glScalef(wScale, hScale, wScale);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(yaw + rot , 0, 1F, 0); //+ (entity.ticksExisted * 50)
		
		model.render(ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
		
		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityTeleportBiped etb = (EntityTeleportBiped) entity;
		return etb.reLoc;
	}
}