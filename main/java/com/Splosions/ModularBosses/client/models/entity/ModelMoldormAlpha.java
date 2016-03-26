package com.Splosions.ModularBosses.client.models.entity;



import com.Splosions.ModularBosses.client.entity.EntityMoldormAlpha;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;



public class ModelMoldormAlpha extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape7;
    public ModelRenderer shape6;

    public ModelMoldormAlpha() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.shape7 = new ModelRenderer(this, 0, 75);
        this.shape7.setRotationPoint(-8.0F, -6.0F, -16.0F);
        this.shape7.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 2, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.shape1.addBox(-16.0F, -16.0F, -16.0F, 32, 32, 32, 0.0F);
        this.shape5 = new ModelRenderer(this, 151, 65);
        this.shape5.setRotationPoint(0.0F, 20.0F, 80.0F);
        this.shape5.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 70);
        this.shape3.setRotationPoint(0.0F, 14.0F, 52.0F);
        this.shape3.addBox(-10.0F, -10.0F, -10.0F, 20, 20, 20, 0.0F);
        this.shape2 = new ModelRenderer(this, 131, 0);
        this.shape2.setRotationPoint(0.0F, 11.0F, 29.0F);
        this.shape2.addBox(-13.0F, -13.0F, -13.0F, 26, 26, 26, 0.0F);
        this.shape4 = new ModelRenderer(this, 82, 71);
        this.shape4.setRotationPoint(0.0F, 17.0F, 69.0F);
        this.shape4.addBox(-7.0F, -7.0F, -7.0F, 14, 14, 14, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 75);
        this.shape6.setRotationPoint(8.0F, -6.0F, -16.0F);
        this.shape6.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 2, 0.0F);
        this.shape1.addChild(this.shape6);
        this.shape1.addChild(this.shape7);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1.render(f5);
        this.shape2.render(f5);
        this.shape3.render(f5);
        this.shape5.render(f5);
        this.shape4.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    
	@Override
	public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float partialTick) {
		setLivingAnimations((EntityMoldormAlpha) entity, par2, par3, partialTick);
	}


    
    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    private void setLivingAnimations(EntityMoldormAlpha worm, float par2, float par3, float PartialTick)  {
    	
    	if (worm.motionX != 0 && worm.motionZ != 0){
    	
    	worm.part2Cur += ((worm.rotationYaw - worm.prevRotationYaw) * 0.15F) + ((0 - worm.part2Cur) * 0.02F);
    	this.shape2.rotateAngleY = (worm.part2Cur * 0.0174F) + 1.57F;
    	this.shape2.rotationPointX = (29 * MathHelper.cos(this.shape2.rotateAngleY));
    	this.shape2.rotationPointY = this.shape1.rotationPointY;
    	this.shape2.rotationPointZ = (29 * MathHelper.sin(this.shape2.rotateAngleY));    	
    	this.shape2.rotateAngleY = (worm.part2Cur * -0.0174F);
    	 
    	
    	worm.part3Cur += ((worm.rotationYaw - worm.prevRotationYaw) * 0.25F) + ((0 - worm.part3Cur) * 0.02F);
    	this.shape3.rotateAngleY = (worm.part3Cur * 0.0174F) + 1.57F;
    	this.shape3.rotationPointX = (23 * MathHelper.cos(this.shape3.rotateAngleY)) + this.shape2.rotationPointX;
    	this.shape3.rotationPointY = this.shape2.rotationPointY ;
    	this.shape3.rotationPointZ = (23 * MathHelper.sin(this.shape3.rotateAngleY)) + this.shape2.rotationPointZ;    	
    	this.shape3.rotateAngleY = (worm.part3Cur * -0.0174F);    	
    	
    	worm.part4Cur += ((worm.rotationYaw - worm.prevRotationYaw) * 0.4F) + ((0 - worm.part4Cur) * 0.02F);
    	this.shape4.rotateAngleY = (worm.part3Cur * 0.0174F) + 1.57F;
    	this.shape4.rotationPointX = (17 * MathHelper.cos(this.shape4.rotateAngleY)) + this.shape3.rotationPointX;
    	this.shape4.rotationPointY = this.shape3.rotationPointY ;
    	this.shape4.rotationPointZ = (17 * MathHelper.sin(this.shape4.rotateAngleY)) + this.shape3.rotationPointZ;    	
    	this.shape4.rotateAngleY = (worm.part4Cur * -0.0174F);    
    	
    	worm.part5Cur += ((worm.rotationYaw - worm.prevRotationYaw) * 0.45F) + ((0 - worm.part5Cur) * 0.02F);
    	this.shape5.rotateAngleY = (worm.part5Cur * 0.0174F) + 1.57F;
    	this.shape5.rotationPointX = (11 * MathHelper.cos(this.shape5.rotateAngleY)) + this.shape4.rotationPointX;
    	this.shape5.rotationPointY = this.shape4.rotationPointY ;
    	this.shape5.rotationPointZ = (11 * MathHelper.sin(this.shape5.rotateAngleY)) + this.shape4.rotationPointZ;    	
    	this.shape5.rotateAngleY = (worm.part5Cur * -0.0174F);  
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    public void PseudoChild (ModelRenderer Parent, float ParentLength, ModelRenderer Child){
    	
    	Child.rotationPointY = Parent.rotationPointY;
    	Child.rotationPointZ = (ParentLength * (MathHelper.sin(-Parent.rotateAngleY))) + (ParentLength * (MathHelper.cos(-Parent.rotateAngleY))) + Parent.rotationPointZ;
    	Child.rotationPointX = (ParentLength * (MathHelper.cos(-Parent.rotateAngleY))) - (ParentLength * (MathHelper.sin(-Parent.rotateAngleY))) + Parent.rotationPointX;

    }
}
