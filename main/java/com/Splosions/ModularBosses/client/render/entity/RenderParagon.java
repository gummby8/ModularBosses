package com.Splosions.ModularBosses.client.render.entity;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.entity.EntityParagon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderParagon extends RenderLiving {

	public RenderParagon(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float yaw, float partialTicks) {
		this.doRender((EntityParagon) entity, x, y, z, yaw, partialTicks);
	}

	
	public void doRender(EntityParagon entity, double x, double y, double z, float yaw, float partialTicks) {
		super.doRender((EntityLiving) entity, x, y, z, yaw, partialTicks);
		if (Config.debugHitboxes) {
			renderDebugBoundingBox(entity.paragonPartFurnace, x, y, z, yaw, partialTicks, entity.paragonPartFurnace.posX - entity.posX, entity.paragonPartFurnace.posY - entity.posY, entity.paragonPartFurnace.posZ - entity.posZ);
			renderDebugBoundingBox(entity.paragonPartRKnee, x, y, z, yaw, partialTicks, entity.paragonPartRKnee.posX - entity.posX, entity.paragonPartRKnee.posY - entity.posY, entity.paragonPartRKnee.posZ - entity.posZ);
			renderDebugBoundingBox(entity.paragonPartLKnee, x, y, z, yaw, partialTicks, entity.paragonPartLKnee.posX - entity.posX, entity.paragonPartLKnee.posY - entity.posY, entity.paragonPartLKnee.posZ - entity.posZ);
		}

	}

	private void renderDebugBoundingBox(EntityDragonPart part, double x, double y, double z, float yaw, float partialTicks, double xOff, double yOff, double zOff) {

		GlStateManager.depthMask(false);
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.disableBlend();
		float f2 = part.width / 2.0F;
		AxisAlignedBB axisalignedbb = part.getEntityBoundingBox();
		AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX - part.posX + x + xOff, axisalignedbb.minY - part.posY + y + yOff, axisalignedbb.minZ - part.posZ + z + zOff, axisalignedbb.maxX - part.posX + x + xOff, axisalignedbb.maxY - part.posY + y + yOff, axisalignedbb.maxZ - part.posZ + z + zOff);
		RenderGlobal.drawOutlinedBoundingBox(axisalignedbb1, 16777215);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		Vec3 vec3 = part.getLook(partialTicks);
		worldrenderer.startDrawing(3);
		worldrenderer.setColorOpaque_I(255);
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("mb:textures/mobs/Paragon.png");
	}

}
