package com.Splosions.ModularBosses.client.render.entity;

import java.util.logging.Level;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.entity.EntityParagon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

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
		return new ResourceLocation("mb:textures/mobs/Paragon.png");
	}

}
