package com.Splosions.ModularBosses.client.render.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderItemNote {
	
	private static final ResourceLocation NOTE_1 = new ResourceLocation("mb:textures/items/loot_note_1.png");
	private static final ResourceLocation NOTE_2 = new ResourceLocation("mb:textures/items/loot_note_2.png");
	private static final ResourceLocation NOTE_3 = new ResourceLocation("mb:textures/items/loot_note_3.png");
	
    public static void renderNote(AbstractClientPlayer p_178097_1_, float p_178097_2_, float p_178097_3_, float p_178097_4_, ItemStack note)
    {
    	ResourceLocation rec;
    	if (note.getItemDamage() == 1){}

        switch (note.getItemDamage()) {
            case 1:  rec = NOTE_1;
                     break;
            case 2:  rec = NOTE_2;
                     break;
            case 3:  rec = NOTE_3;
                     break;
            default: rec = NOTE_1;
            break;
        }
    	
    	Minecraft mc = Minecraft.getMinecraft();
        float f3 = -0.4F * MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * (float)Math.PI);
        float f4 = 0.2F * MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * (float)Math.PI * 2.0F);
        float f5 = -0.2F * MathHelper.sin(p_178097_4_ * (float)Math.PI);
        GlStateManager.translate(f3, f4, f5);
        float f6 = func_178100_c(p_178097_2_);
        GlStateManager.translate(0.0F, 0.74F, -0.72F);
        GlStateManager.translate(0.0F, p_178097_3_, 0.0F);
        GlStateManager.translate(0.0F, f6 * -0.5F, 0.0F);
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f6 * -85.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
       // func_178102_b(p_178097_1_);
        float f7 = MathHelper.sin(p_178097_4_ * p_178097_4_ * (float)Math.PI);
        float f8 = MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * (float)Math.PI);
        GlStateManager.rotate(f7 * -20.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f8 * -20.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(f8 * -80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(0.38F, 0.38F, 0.38F);
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(-1.0F, -1.0F, 0.0F);
        GlStateManager.scale(0.015625F, 0.015625F, 0.015625F);
        mc.getTextureManager().bindTexture(rec);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV(-7.0D, 135.0D, 0.0D, 0.0D, 1.0D);
        worldrenderer.addVertexWithUV(135.0D, 135.0D, 0.0D, 1.0D, 1.0D);
        worldrenderer.addVertexWithUV(135.0D, -7.0D, 0.0D, 1.0D, 0.0D);
        worldrenderer.addVertexWithUV(-7.0D, -7.0D, 0.0D, 0.0D, 0.0D);
        tessellator.draw();
    }
	
    public static float func_178100_c(float p_178100_1_)
    {
        float f1 = 1.0F - p_178100_1_ / 45.0F + 0.1F;
        f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
        f1 = -MathHelper.cos(f1 * (float)Math.PI) * 0.5F + 0.5F;
        return f1;
    }
    
    public static void func_178102_b(AbstractClientPlayer p_178102_1_)
    {
        Minecraft mc = Minecraft.getMinecraft();
        RenderManager renderManager = mc.getRenderManager();
        RenderItem itemRenderer = mc.getRenderItem();
    	
        mc.getTextureManager().bindTexture(p_178102_1_.getLocationSkin());
        Render render = renderManager.getEntityRenderObject(mc.thePlayer);
        RenderPlayer renderplayer = (RenderPlayer)render;


    }
    
    public static void func_178101_a(float p_178101_1_, float p_178101_2_)
    {
        //GlStateManager.pushMatrix();
        GlStateManager.rotate(p_178101_1_, 0.0F, 0.0F, 0.0F);
        GlStateManager.rotate(p_178101_2_ + 180, 0.0F, -1.0F, 0.0F);
        //RenderHelper.enableStandardItemLighting();
       // GlStateManager.popMatrix();
    }
    
    
    public static void func_178110_a(EntityPlayerSP p_178110_1_, float p_178110_2_)
    {
        float f1 = p_178110_1_.prevRenderArmPitch + (p_178110_1_.renderArmPitch - p_178110_1_.prevRenderArmPitch) * p_178110_2_;
        float f2 = p_178110_1_.prevRenderArmYaw + (p_178110_1_.renderArmYaw - p_178110_1_.prevRenderArmYaw) * p_178110_2_;
        GlStateManager.rotate((p_178110_1_.rotationPitch - f1) * 0.1F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate((p_178110_1_.rotationYaw - f2) * 0.1F, 0.0F, 1.0F, 0.0F);
    }
    
}
