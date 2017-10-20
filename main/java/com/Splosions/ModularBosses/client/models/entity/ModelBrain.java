package com.Splosions.ModularBosses.client.models.entity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.entity.EntityBrain;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * Brain - Undefined
 * Created using Tabula 5.1.0
 */
public class ModelBrain extends ModelBase {
	private ModelRenderer[] Energy = new ModelRenderer[400];
	private boolean flip = true;
	
    public ModelRenderer LEFTPARENT;
    public ModelRenderer LEFTPARENT_1;
    public ModelRenderer LEFTPARENT_2;
    public ModelRenderer LEFTPARENT_3;
    public ModelRenderer shape2;
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape2_1;
    public ModelRenderer shape1_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;
    public ModelRenderer shape1_8;
    public ModelRenderer shape1_9;
    public ModelRenderer shape2_2;
    public ModelRenderer shape1_10;
    public ModelRenderer shape1_11;
    public ModelRenderer shape1_12;
    public ModelRenderer shape1_13;
    public ModelRenderer shape1_14;
    public ModelRenderer shape2_3;
    public ModelRenderer shape1_15;
    public ModelRenderer shape1_16;
    public ModelRenderer shape1_17;
    public ModelRenderer shape1_18;
    public ModelRenderer shape1_19;


	int pixelY = 0;
	int pixelX = 0;
	int pixelZ = 0;

    public ModelBrain() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        
        for (int i = 0; i < this.Energy.length; ++i)
        {
         	this.pixelX = (i < 300)? TargetUtils.getRanNum(-70, 70): TargetUtils.getRanNum(-20, 20);
         	this.pixelY = TargetUtils.getRanNum(0, 360);
        	this.Energy[i] = new ModelRenderer(this, 0, 40);
            this.Energy[i].addBox(0, 0, 50F, 8, 4, 0);
            this.Energy[i].setRotationPoint(0, 8, 0);
            this.Energy[i].rotateAngleY = (float) Math.toRadians(this.pixelY);
            this.Energy[i].rotateAngleX = (float) Math.toRadians(this.pixelX);
        }
        
        
        this.shape1_12 = new ModelRenderer(this, 0, 0);
        this.shape1_12.setRotationPoint(-12.0F, -8.0F, 0.0F);
        this.shape1_12.addBox(-28.0F, 0.0F, 0.0F, 54, 8, 12, 0.0F);
        this.shape1_9 = new ModelRenderer(this, 0, 0);
        this.shape1_9.setRotationPoint(-12.0F, 8.0F, -32.0F);
        this.shape1_9.addBox(-16.0F, 0.0F, 0.0F, 24, 4, 72, 0.0F);
        this.shape1_10 = new ModelRenderer(this, 0, 0);
        this.shape1_10.setRotationPoint(-12.0F, -24.0F, 12.0F);
        this.shape1_10.addBox(-16.0F, 0.0F, 0.0F, 32, 8, 12, 0.0F);
        this.shape1_16 = new ModelRenderer(this, 0, 0);
        this.shape1_16.setRotationPoint(-12.0F, -16.0F, -4.0F);
        this.shape1_16.addBox(-24.0F, 0.0F, -12.0F, 46, 8, 12, 0.0F);
        this.LEFTPARENT_1 = new ModelRenderer(this, 0, 0);
        this.LEFTPARENT_1.setRotationPoint(1.0F, 12.0F, 0.0F);
        this.LEFTPARENT_1.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 0, 0);
        this.shape1_3.setRotationPoint(12.0F, 0.0F, -40.0F);
        this.shape1_3.addBox(-8.0F, 0.0F, 0.0F, 32, 8, 84, 0.0F);
        this.shape1_8 = new ModelRenderer(this, 0, 0);
        this.shape1_8.setRotationPoint(-12.0F, 0.0F, -40.0F);
        this.shape1_8.addBox(-24.0F, 0.0F, 0.0F, 32, 8, 84, 0.0F);
        this.shape1_13 = new ModelRenderer(this, 0, 0);
        this.shape1_13.setRotationPoint(-12.0F, 0.0F, 4.0F);
        this.shape1_13.addBox(-24.0F, 0.0F, 0.0F, 40, 8, 12, 0.0F);
        this.LEFTPARENT_2 = new ModelRenderer(this, 0, 0);
        this.LEFTPARENT_2.setRotationPoint(-35.0F, 12.0F, 2.0F);
        this.LEFTPARENT_2.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(LEFTPARENT_2, 0.0F, 1.5707963267948966F, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(12.0F, -16.0F, -32.0F);
        this.shape1_1.addBox(-8.0F, 0.0F, 0.0F, 32, 8, 76, 0.0F);
        this.shape1_5 = new ModelRenderer(this, 0, 0);
        this.shape1_5.setRotationPoint(-12.0F, -24.0F, -16.0F);
        this.shape1_5.addBox(-16.0F, 0.0F, 0.0F, 24, 8, 56, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(12.0F, -24.0F, -16.0F);
        this.shape1.addBox(-8.0F, 0.0F, 0.0F, 24, 8, 56, 0.0F);
        this.LEFTPARENT_3 = new ModelRenderer(this, 0, 0);
        this.LEFTPARENT_3.setRotationPoint(35.0F, 12.0F, 2.0F);
        this.LEFTPARENT_3.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(LEFTPARENT_3, 0.0F, 1.5707963267948966F, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 32);
        this.shape2.setRotationPoint(12.0F, -28.0F, 0.0F);
        this.shape2.addBox(-8.0F, 0.0F, 0.0F, 16, 4, 32, 0.0F);
        this.shape1_14 = new ModelRenderer(this, 0, 0);
        this.shape1_14.setRotationPoint(-12.0F, 8.0F, 10.0F);
        this.shape1_14.addBox(-16.0F, 0.0F, 0.0F, 24, 4, 14, 0.0F);
        this.shape1_15 = new ModelRenderer(this, 0, 0);
        this.shape1_15.setRotationPoint(-12.0F, -24.0F, -12.0F);
        this.shape1_15.addBox(-16.0F, 0.0F, -12.0F, 32, 8, 12, 0.0F);
        this.shape1_18 = new ModelRenderer(this, 0, 0);
        this.shape1_18.setRotationPoint(-12.0F, 0.0F, -4.0F);
        this.shape1_18.addBox(-24.0F, 0.0F, -12.0F, 40, 8, 12, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 0, 0);
        this.shape1_4.setRotationPoint(12.0F, 8.0F, -32.0F);
        this.shape1_4.addBox(-8.0F, 0.0F, 0.0F, 24, 4, 72, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 0, 0);
        this.shape1_2.setRotationPoint(12.0F, -8.0F, -48.0F);
        this.shape1_2.addBox(-8.0F, 0.0F, 0.0F, 36, 8, 96, 0.0F);
        this.shape1_7 = new ModelRenderer(this, 0, 0);
        this.shape1_7.setRotationPoint(-12.0F, -8.0F, -48.0F);
        this.shape1_7.addBox(-28.0F, 0.0F, 0.0F, 36, 8, 96, 0.0F);
        this.shape1_17 = new ModelRenderer(this, 0, 0);
        this.shape1_17.setRotationPoint(-12.0F, -8.0F, 0.0F);
        this.shape1_17.addBox(-28.0F, 0.0F, -12.0F, 54, 8, 12, 0.0F);
        this.shape1_11 = new ModelRenderer(this, 0, 0);
        this.shape1_11.setRotationPoint(-12.0F, -16.0F, 4.0F);
        this.shape1_11.addBox(-24.0F, 0.0F, 0.0F, 46, 8, 12, 0.0F);
        this.shape2_1 = new ModelRenderer(this, 0, 32);
        this.shape2_1.setRotationPoint(-12.0F, -28.0F, 0.0F);
        this.shape2_1.addBox(-8.0F, 0.0F, 0.0F, 16, 4, 32, 0.0F);
        this.shape1_6 = new ModelRenderer(this, 0, 0);
        this.shape1_6.setRotationPoint(-12.0F, -16.0F, -32.0F);
        this.shape1_6.addBox(-24.0F, 0.0F, 0.0F, 32, 8, 76, 0.0F);
        this.shape2_3 = new ModelRenderer(this, 0, 32);
        this.shape2_3.setRotationPoint(-12.0F, -28.0F, -16.0F);
        this.shape2_3.addBox(-8.0F, 0.0F, -16.0F, 16, 4, 16, 0.0F);
        this.shape1_19 = new ModelRenderer(this, 0, 0);
        this.shape1_19.setRotationPoint(-12.0F, 8.0F, -10.0F);
        this.shape1_19.addBox(-16.0F, 0.0F, -14.0F, 24, 4, 14, 0.0F);
        this.LEFTPARENT = new ModelRenderer(this, 0, 0);
        this.LEFTPARENT.setRotationPoint(-1.0F, 12.0F, 0.0F);
        this.LEFTPARENT.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.shape2_2 = new ModelRenderer(this, 0, 32);
        this.shape2_2.setRotationPoint(-12.0F, -28.0F, 16.0F);
        this.shape2_2.addBox(-8.0F, 0.0F, 0.0F, 16, 4, 16, 0.0F);
        this.LEFTPARENT_2.addChild(this.shape1_12);
        this.LEFTPARENT_1.addChild(this.shape1_9);
        this.LEFTPARENT_2.addChild(this.shape1_10);
        this.LEFTPARENT_3.addChild(this.shape1_16);
        this.LEFTPARENT.addChild(this.shape1_3);
        this.LEFTPARENT_1.addChild(this.shape1_8);
        this.LEFTPARENT_2.addChild(this.shape1_13);
        this.LEFTPARENT.addChild(this.shape1_1);
        this.LEFTPARENT_1.addChild(this.shape1_5);
        this.LEFTPARENT.addChild(this.shape1);
        this.LEFTPARENT.addChild(this.shape2);
        this.LEFTPARENT_2.addChild(this.shape1_14);
        this.LEFTPARENT_3.addChild(this.shape1_15);
        this.LEFTPARENT_3.addChild(this.shape1_18);
        this.LEFTPARENT.addChild(this.shape1_4);
        this.LEFTPARENT.addChild(this.shape1_2);
        this.LEFTPARENT_1.addChild(this.shape1_7);
        this.LEFTPARENT_3.addChild(this.shape1_17);
        this.LEFTPARENT_2.addChild(this.shape1_11);
        this.LEFTPARENT_1.addChild(this.shape2_1);
        this.LEFTPARENT_1.addChild(this.shape1_6);
        this.LEFTPARENT_3.addChild(this.shape2_3);
        this.LEFTPARENT_3.addChild(this.shape1_19);
        this.LEFTPARENT_2.addChild(this.shape2_2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	EntityBrain brain = (EntityBrain)entity;
        
    	GlStateManager.pushMatrix();
        GlStateManager.translate(this.LEFTPARENT_1.offsetX, this.LEFTPARENT_1.offsetY, this.LEFTPARENT_1.offsetZ);
        GlStateManager.translate(this.LEFTPARENT_1.rotationPointX * f5, this.LEFTPARENT_1.rotationPointY * f5, this.LEFTPARENT_1.rotationPointZ * f5);
        GlStateManager.scale(0.7D, 1.0D, 0.7D);
        GlStateManager.translate(-this.LEFTPARENT_1.offsetX, -this.LEFTPARENT_1.offsetY, -this.LEFTPARENT_1.offsetZ);
        GlStateManager.translate(-this.LEFTPARENT_1.rotationPointX * f5, -this.LEFTPARENT_1.rotationPointY * f5, -this.LEFTPARENT_1.rotationPointZ * f5);
        this.LEFTPARENT_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.LEFTPARENT_2.offsetX, this.LEFTPARENT_2.offsetY, this.LEFTPARENT_2.offsetZ);
        GlStateManager.translate(this.LEFTPARENT_2.rotationPointX * f5, this.LEFTPARENT_2.rotationPointY * f5, this.LEFTPARENT_2.rotationPointZ * f5);
        GlStateManager.scale(0.7D, 1.0D, 0.7D);
        GlStateManager.translate(-this.LEFTPARENT_2.offsetX, -this.LEFTPARENT_2.offsetY, -this.LEFTPARENT_2.offsetZ);
        GlStateManager.translate(-this.LEFTPARENT_2.rotationPointX * f5, -this.LEFTPARENT_2.rotationPointY * f5, -this.LEFTPARENT_2.rotationPointZ * f5);
        this.LEFTPARENT_2.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.LEFTPARENT_3.offsetX, this.LEFTPARENT_3.offsetY, this.LEFTPARENT_3.offsetZ);
        GlStateManager.translate(this.LEFTPARENT_3.rotationPointX * f5, this.LEFTPARENT_3.rotationPointY * f5, this.LEFTPARENT_3.rotationPointZ * f5);
        GlStateManager.scale(0.7D, 1.0D, 0.7D);
        GlStateManager.translate(-this.LEFTPARENT_3.offsetX, -this.LEFTPARENT_3.offsetY, -this.LEFTPARENT_3.offsetZ);
        GlStateManager.translate(-this.LEFTPARENT_3.rotationPointX * f5, -this.LEFTPARENT_3.rotationPointY * f5, -this.LEFTPARENT_3.rotationPointZ * f5);
        this.LEFTPARENT_3.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.LEFTPARENT.offsetX, this.LEFTPARENT.offsetY, this.LEFTPARENT.offsetZ);
        GlStateManager.translate(this.LEFTPARENT.rotationPointX * f5, this.LEFTPARENT.rotationPointY * f5, this.LEFTPARENT.rotationPointZ * f5);
        GlStateManager.scale(0.7D, 1.0D, 0.7D);
        GlStateManager.translate(-this.LEFTPARENT.offsetX, -this.LEFTPARENT.offsetY, -this.LEFTPARENT.offsetZ);
        GlStateManager.translate(-this.LEFTPARENT.rotationPointX * f5, -this.LEFTPARENT.rotationPointY * f5, -this.LEFTPARENT.rotationPointZ * f5);
        this.LEFTPARENT.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        
		GL11.glEnable(GL11.GL_BLEND);
	    GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
	    GlStateManager.color(0F, 0F, 1F, 0.5F);
        for (int i = 0; i < this.Energy.length; ++i) // - this.fade
        {
    	this.Energy[i].rotateAngleY += (flip)? 0.04D : -0.04D;
    	flip = (flip)?false:true;
	    
    	if (brain.shieldUp){
		this.Energy[i].render(f5);
    	}
        }
        GL11.glDisable(GL11.GL_BLEND);


        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
