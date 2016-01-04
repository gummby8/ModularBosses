package com.Splosions.ModularBosses.client.models.projectiles;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;


public class ModelFlameThrower extends ModelBase
{
	
	 Random rand = new Random();

  //fields
	public ModelRenderer[] part = new ModelRenderer[50];
 
  public ModelFlameThrower()
  {
    textureWidth = 10;
    textureHeight = 10;
  
  //using offsets as a speed variable older  
    for (int i = 0; i < this.part.length; ++i){
    part[i] = new ModelRenderer(this, 0, 0);
    part[i].addBox(0F, 0F, 0F, 1, 1, 1);
    part[i].setRotationPoint(0F, 0F, 0F);
    part[i].setTextureSize(1, 1);
    part[i].rotateAngleX = (rand.nextFloat() * 2) - 1F;
    part[i].rotateAngleY = rand.nextFloat();
    part[i].rotateAngleZ = (rand.nextFloat() * 2) - 1F;
    
    }
    
  }
  
  
  float red;
  float green;
  float blue;
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    red = 1;
    green = (entity.ticksExisted > 15) ?  1 - (entity.ticksExisted * 0.025F) : 1;
    blue = (entity.ticksExisted > 3) ? 0 : 1;
    
    GlStateManager.color(red, green, blue);
    
    for (int i = 0; i < this.part.length; ++i){part[i].render(f5);}
    
   }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)  {
  
	  for (int i = 0; i < this.part.length; ++i){
		  part[i].rotationPointX = 0.2F * part[i].rotateAngleX * par7Entity.ticksExisted;
		  part[i].rotationPointY = 0.5F * part[i].rotateAngleY * par7Entity.ticksExisted;
		  part[i].rotationPointZ = 0.2F * part[i].rotateAngleZ * par7Entity.ticksExisted;
	  }
		  
	  

	  
  
  }
}
