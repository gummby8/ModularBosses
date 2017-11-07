package com.Splosions.ModularBosses.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.entity.ModelMoldorm;
import com.Splosions.ModularBosses.entity.EntityMoldorm;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class RenderMoldorm extends Render {
	
	protected ModelBase model;
	
    public RenderMoldorm(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager);
		this.model = new ModelMoldorm();
    }
	
    
    @Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
    	EntityMoldorm ent = (EntityMoldorm) entity;

    	GL11.glPushMatrix();
    	if (entity.hurtResistantTime <= 40 && entity.hurtResistantTime >= 35){
    	    float red =  0.8F;
    	    float green = 0.2F;
    	    float blue = 0.2F;
    	    
    	    GlStateManager.color(red, green, blue);
    	}
		bindTexture(getEntityTexture(ent));
		GL11.glTranslated(x, y + 1, z);
		GL11.glScalef(1, 1, 1);
		GL11.glRotatef(180, 1, 0, 0);
		model.render(ent, yaw, partialTicks, 0.0F, 0.0F, 0.0F, 0.0475F);
		GL11.glPopMatrix();

		if (!ent.debugHitboxes) {
			renderDebugBoundingBox(ent.moldormPart1, x, y, z, yaw, partialTicks, ent.moldormPart1.posX - ent.posX, ent.moldormPart1.posY - ent.posY, ent.moldormPart1.posZ - ent.posZ);
			renderDebugBoundingBox(ent.moldormPart2, x, y, z, yaw, partialTicks, ent.moldormPart2.posX - ent.posX, ent.moldormPart2.posY - ent.posY, ent.moldormPart2.posZ - ent.posZ);
			renderDebugBoundingBox(ent.moldormPart3, x, y, z, yaw, partialTicks, ent.moldormPart3.posX - ent.posX, ent.moldormPart3.posY - ent.posY, ent.moldormPart3.posZ - ent.posZ);
			renderDebugBoundingBox(ent.moldormPart4, x, y, z, yaw, partialTicks, ent.moldormPart4.posX - ent.posX, ent.moldormPart4.posY - ent.posY, ent.moldormPart4.posZ - ent.posZ);
			renderDebugBoundingBox(ent.moldormPart5, x, y, z, yaw, partialTicks, ent.moldormPart5.posX - ent.posX, ent.moldormPart5.posY - ent.posY, ent.moldormPart5.posZ - ent.posZ);
		}
	}
    
    
    
	private void renderDebugBoundingBox(MultiPartEntityPart part, double x, double y, double z, float yaw, float partialTicks, double xOff, double yOff, double zOff) {

		GlStateManager.depthMask(false);
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.disableBlend();
		float f2 = part.width / 2.0F;
		AxisAlignedBB axisalignedbb = part.getEntityBoundingBox();
		AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX - part.posX + x + xOff, axisalignedbb.minY - part.posY + y + yOff, axisalignedbb.minZ - part.posZ + z + zOff, axisalignedbb.maxX - part.posX + x + xOff, axisalignedbb.maxY - part.posY + y + yOff, axisalignedbb.maxZ - part.posZ + z + zOff);
		RenderGlobal.drawSelectionBoundingBox(axisalignedbb1, 255, 255, 255, 1);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		Vec3d vec3 = part.getLook(partialTicks);
		bufferbuilder.begin(3, DefaultVertexFormats.POSITION_TEX_COLOR);
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
	}
	
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("mb:textures/mobs/Moldorm.png");
	}
	
}