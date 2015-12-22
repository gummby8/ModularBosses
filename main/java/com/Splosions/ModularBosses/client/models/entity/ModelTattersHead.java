package com.Splosions.ModularBosses.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * test - Undefined
 * Created using Tabula 5.1.0
 */
public class ModelTattersHead extends ModelBase {
    
	public ModelRenderer StripF1P;
    public ModelRenderer[] StripF1 = new ModelRenderer[40];
	
	public ModelRenderer shape1;
    public ModelRenderer shape2;

    public ModelTattersHead() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, -31.0F, 0.0F);
        this.shape1.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.shape2.addBox(-0.5F, 0.0F, -9.5F, 1, 1, 10, 0.0F);
        this.shape1.addChild(this.shape2);
        
        
        this.StripF1P = new ModelRenderer(this, 101, 0);
        this.StripF1P.setRotationPoint(0.0F, -31F, -6.9F);
        this.StripF1P.addBox(-1.0F, 0F, 0F, 2, 1, 0, 0.0F);
        
        for (int i = 0; i < this.StripF1.length; ++i){
        this.StripF1[i] = new ModelRenderer(this, 101, 0);
        this.StripF1[i].setRotationPoint(0.0F, 0F, -5.4F);
        this.StripF1[i].addBox(-1.0F, 0F, 0F, 2, 1, 0, 0.0F);}
        
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1.render(f5);
        
    	this.StripF1P.render(f5);
    
    	
        for (int i = 0; i < this.StripF1.length; ++i){this.StripF1[i].render(f5);}
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity Entity)  {
    	
    	shape1.rotationPointY = 0;
    	shape1.rotateAngleX = 0F; //0.872665f
    	float X = 0; //(MathHelper.sin((float)Tatters.ticksExisted / Speed) * Swing * 0.0174533F); 
    	  	
    	
    	
    	this.StripF1P.rotateAngleX = shape1.rotateAngleX;
    	
    	this.StripF1P.rotationPointX = shape1.rotationPointX;
    	this.StripF1P.rotationPointY = (-9.5F * (MathHelper.sin(-shape1.rotateAngleX))) + (6.5F * (MathHelper.cos(-shape1.rotateAngleX)));
    	this.StripF1P.rotationPointZ = (-9.5F * (MathHelper.cos(-shape1.rotateAngleX))) - (6.5F * (MathHelper.sin(-shape1.rotateAngleX)));
    	
    	PseudoChild(StripF1P,1,this.StripF1[0]);
    	this.StripF1[0].rotateAngleX = X + shape1.rotateAngleX;
    	
    	
    	
    	for (int i = 1; i < this.StripF1.length; ++i){ 
    	//StripF1[i].rotateAngleX = Tatters.StripF1[i] + ((StripF1[i - 1].rotateAngleX - Tatters.StripF1[i]) * 0.15F);
    	//Tatters.StripF1[i] = StripF1[i].rotateAngleX;
    	PseudoChild(StripF1[i - 1], 1, StripF1[i]);
    	}
    	
    	
    	
    	
    	
    }
    
    
    
    

    public float DegsToRads (float input){
    	float result = input * (180 / (float)Math.PI);
    	return result;
    }
    
    
    public float RadsToDegs (float input){
    	float result = input * ((float)Math.PI / 180);
    	return result;
    }
    

    public void PseudoChild(ModelRenderer Parent, float ParentLength, ModelRenderer Child){
    	
	float X = ParentLength * MathHelper.sin(Parent.rotateAngleX) * MathHelper.sin(Parent.rotateAngleY);
	float Y = ParentLength * MathHelper.cos(Parent.rotateAngleX);
	float Z = ParentLength * MathHelper.sin(Parent.rotateAngleX) * MathHelper.cos(Parent.rotateAngleY);
	
	Child.rotationPointX = X + Parent.rotationPointX;
	Child.rotationPointY = Y + Parent.rotationPointY;
	Child.rotationPointZ = Z + Parent.rotationPointZ;

    }
    
    
    
}
