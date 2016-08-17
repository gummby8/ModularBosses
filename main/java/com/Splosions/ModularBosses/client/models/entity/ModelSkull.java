package com.Splosions.ModularBosses.client.models.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;


public class ModelSkull extends ModelBase
{
	

	
	
  //fields
    ModelRenderer MRJaw;
    ModelRenderer MJaw;
    ModelRenderer BRJaw;
    ModelRenderer BLJaw;
    ModelRenderer MLJaw;
    ModelRenderer TRJaw;
    ModelRenderer TLJaw;
    ModelRenderer BMTooth;
    ModelRenderer BRTooth;
    ModelRenderer BLTooth;
    ModelRenderer MidLip;
    ModelRenderer Shape1;
    ModelRenderer RightLip;
    ModelRenderer LeftLip;
    ModelRenderer TLTooth;
    ModelRenderer TRTooth;
    ModelRenderer SkullMid;
    ModelRenderer SkullMain;
    ModelRenderer SkullTop;
    ModelRenderer EyeBrow;

  
  public ModelSkull()
  {
    textureWidth = 64;
    textureHeight = 32;
    
     

      MRJaw = new ModelRenderer(this, 36, 7);
      MRJaw.addBox(2.5F, 2F, -3F, 1, 1, 2);
      MRJaw.setRotationPoint(0F, 16F, 0F);
      MRJaw.setTextureSize(64, 32);
      MRJaw.mirror = true;
      setRotation(MRJaw, 0F, 0F, 0F);
      MJaw = new ModelRenderer(this, 20, 10);
      MJaw.addBox(-1.5F, 3F, -5F, 3, 1, 1);
      MJaw.setRotationPoint(0F, 16F, 0F);
      MJaw.setTextureSize(64, 32);
      MJaw.mirror = true;
      setRotation(MJaw, 0F, 0F, 0F);
      BRJaw = new ModelRenderer(this, 29, 8);
      BRJaw.addBox(1.5F, 3F, -4F, 1, 1, 2);
      BRJaw.setRotationPoint(0F, 16F, 0F);
      BRJaw.setTextureSize(64, 32);
      BRJaw.mirror = true;
      setRotation(BRJaw, 0F, 0F, 0F);
      BLJaw = new ModelRenderer(this, 13, 8);
      BLJaw.addBox(-2.5F, 3F, -4F, 1, 1, 2);
      BLJaw.setRotationPoint(0F, 16F, 0F);
      BLJaw.setTextureSize(64, 32);
      BLJaw.mirror = true;
      setRotation(BLJaw, 0F, 0F, 0F);
      MLJaw = new ModelRenderer(this, 6, 7);
      MLJaw.addBox(-3.5F, 2F, -3F, 1, 1, 2);
      MLJaw.setRotationPoint(0F, 16F, 0F);
      MLJaw.setTextureSize(64, 32);
      MLJaw.mirror = true;
      setRotation(MLJaw, 0F, 0F, 0F);
      TRJaw = new ModelRenderer(this, 36, 0);
      TRJaw.addBox(2.5F, 0F, -2F, 1, 2, 2);
      TRJaw.setRotationPoint(0F, 16F, 0F);
      TRJaw.setTextureSize(64, 32);
      TRJaw.mirror = true;
      setRotation(TRJaw, 0F, 0F, 0F);
      TLJaw = new ModelRenderer(this, 6, 0);
      TLJaw.addBox(-3.5F, 0F, -2F, 1, 2, 2);
      TLJaw.setRotationPoint(0F, 16F, 0F);
      TLJaw.setTextureSize(64, 32);
      TLJaw.mirror = true;
      setRotation(TLJaw, 0F, 0F, 0F);
      BMTooth = new ModelRenderer(this, 21, 6);
      BMTooth.addBox(-1.5F, 2F, -4.7F, 3, 1, 0);
      BMTooth.setRotationPoint(0F, 16F, 0F);
      BMTooth.setTextureSize(64, 32);
      BMTooth.mirror = true;
      setRotation(BMTooth, 0F, 0F, 0F);
      BRTooth = new ModelRenderer(this, 29, 6);
      BRTooth.addBox(1.5F, 2F, -3.7F, 1, 1, 0);
      BRTooth.setRotationPoint(0F, 16F, 0F);
      BRTooth.setTextureSize(64, 32);
      BRTooth.mirror = true;
      setRotation(BRTooth, 0F, 0F, 0F);
      BLTooth = new ModelRenderer(this, 17, 6);
      BLTooth.addBox(-2.5F, 2F, -3.7F, 1, 1, 0);
      BLTooth.setRotationPoint(0F, 16F, 0F);
      BLTooth.setTextureSize(64, 32);
      BLTooth.mirror = true;
      setRotation(BLTooth, 0F, 0F, 0F);
      MidLip = new ModelRenderer(this, 20, 0);
      MidLip.addBox(-1.5F, 0F, -5F, 3, 1, 1);
      MidLip.setRotationPoint(0F, 16F, 0F);
      MidLip.setTextureSize(64, 32);
      MidLip.mirror = true;
      setRotation(MidLip, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 21, 4);
      Shape1.addBox(-1.5F, 1F, -4.9F, 3, 1, 0);
      Shape1.setRotationPoint(0F, 16F, 0F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      RightLip = new ModelRenderer(this, 29, 0);
      RightLip.addBox(1.5F, 0F, -4F, 1, 1, 2);
      RightLip.setRotationPoint(0F, 16F, 0F);
      RightLip.setTextureSize(64, 32);
      RightLip.mirror = true;
      setRotation(RightLip, 0F, 0F, 0F);
      LeftLip = new ModelRenderer(this, 13, 0);
      LeftLip.addBox(-2.5F, 0F, -4F, 1, 1, 2);
      LeftLip.setRotationPoint(0F, 16F, 0F);
      LeftLip.setTextureSize(64, 32);
      LeftLip.mirror = true;
      setRotation(LeftLip, 0F, 0F, 0F);
      TLTooth = new ModelRenderer(this, 17, 4);
      TLTooth.addBox(-2.5F, 1F, -3.9F, 1, 1, 0);
      TLTooth.setRotationPoint(0F, 16F, 0F);
      TLTooth.setTextureSize(64, 32);
      TLTooth.mirror = true;
      setRotation(TLTooth, 0F, 0F, 0F);
      TRTooth = new ModelRenderer(this, 29, 4);
      TRTooth.addBox(1.5F, 1F, -3.9F, 1, 1, 0);
      TRTooth.setRotationPoint(0F, 16F, 0F);
      TRTooth.setTextureSize(64, 32);
      TRTooth.mirror = true;
      setRotation(TRTooth, 0F, 0F, 0F);
      SkullMid = new ModelRenderer(this, 50, 14);
      SkullMid.addBox(-2.5F, -4F, -4F, 5, 4, 2);
      SkullMid.setRotationPoint(0F, 16F, 0F);
      SkullMid.setTextureSize(64, 32);
      SkullMid.mirror = true;
      setRotation(SkullMid, 0F, 0F, 0F);
      SkullMain = new ModelRenderer(this, 36, 21);
      SkullMain.addBox(-4F, -4F, -2F, 8, 4, 6);
      SkullMain.setRotationPoint(0F, 16F, 0F);
      SkullMain.setTextureSize(64, 32);
      SkullMain.mirror = true;
      setRotation(SkullMain, 0F, 0F, 0F);
      SkullTop = new ModelRenderer(this, 44, 0);
      SkullTop.addBox(-3F, -5F, -1F, 6, 1, 4);
      SkullTop.setRotationPoint(0F, 16F, 0F);
      SkullTop.setTextureSize(64, 32);
      SkullTop.mirror = true;
      setRotation(SkullTop, 0F, 0F, 0F);
      EyeBrow = new ModelRenderer(this, 54, 6);
      EyeBrow.addBox(-1.5F, -5F, -3F, 3, 1, 2);
      EyeBrow.setRotationPoint(0F, 16F, 0F);
      EyeBrow.setTextureSize(64, 32);
      EyeBrow.mirror = true;
      setRotation(EyeBrow, 0F, 0F, 0F);
  }
  
  
  float red;
  float green;
  float blue;
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    RightLip.render(f5);
    LeftLip.render(f5);
    TLTooth.render(f5);
    TRTooth.render(f5);
    SkullMid.render(f5);
    SkullMain.render(f5);
    MRJaw.render(f5);
    MJaw.render(f5);
    BRJaw.render(f5);
    BLJaw.render(f5);
    MLJaw.render(f5);
    TRJaw.render(f5);
    TLJaw.render(f5);
    BMTooth.render(f5);
    BRTooth.render(f5);
    BLTooth.render(f5);
    MidLip.render(f5);
    Shape1.render(f5);
    SkullTop.render(f5);
    EyeBrow.render(f5);
   }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)  {
  
	  // makes it so the skull doesnt bit its lip
	  float RotateX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 * 0.5F;
	  if ( RotateX <= 0 ) {
		  RotateX = 0;
	  }
	  
	  this.TRJaw.rotateAngleX = RotateX;
	  this.MRJaw.rotateAngleX = RotateX;
	  this.BRJaw.rotateAngleX = RotateX;
	  
	  this.TLJaw.rotateAngleX = RotateX;
	  this.MLJaw.rotateAngleX = RotateX;
	  this.BLJaw.rotateAngleX = RotateX;
    
	  this.MJaw.rotateAngleX = RotateX;
	  
	  this.BRTooth.rotateAngleX = RotateX;
	  this.BLTooth.rotateAngleX = RotateX;
	  this.BMTooth.rotateAngleX = RotateX;

	  
	  
  }

}
