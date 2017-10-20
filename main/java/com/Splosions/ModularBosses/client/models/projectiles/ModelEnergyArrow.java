package com.Splosions.ModularBosses.client.models.projectiles;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelEnergyArrow extends ModelBase
{
  //fields
	private ModelRenderer[] Energy = new ModelRenderer[360];
	private ModelRenderer[] Arrow = new ModelRenderer[180];
	int pixelY = 0;
	int pixelX = 0;
	int pixelZ = 0;

 
  
  public ModelEnergyArrow()
  {
    textureWidth = 4;
    textureHeight = 3;
    

    
    for (int i = 0; i < this.Energy.length; ++i)
    {
     	this.pixelY = TargetUtils.getRanNum(-35, -6);
     	this.pixelX = TargetUtils.getRanNum(0, 15);
    	this.Energy[i] = new ModelRenderer(this, 0, 0);
        this.Energy[i].addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.Energy[i].setRotationPoint(0, this.pixelY, 0);
        this.Energy[i].rotateAngleY = this.pixelX;
    }
    
    for (int i = 0; i < this.Arrow.length; ++i)
    {
     	this.pixelY = TargetUtils.getRanNum(-80, -6);
    	this.Arrow[i] = new ModelRenderer(this, 0, 0);
        this.Arrow[i].addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.Arrow[i].setRotationPoint(0, this.pixelY, 0);
    }
    

  }
  
  public int randInt(int min, int max) {

      // NOTE: Usually this should be a field rather than a method
      // variable so that it is not re-seeded every call.
      Random rand = new Random();

      // nextInt is normally exclusive of the top value,
      // so add 1 to make it inclusive
      int randomNum = rand.nextInt((max - min) + 1) + min;

      return randomNum;
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);


   	

    for (int i = 0; i < this.Energy.length; ++i) // - this.fade
    {	
    	GL11.glPushMatrix();
    	GL11.glRotatef(i, 0, 1F, 0);

    	--this.Energy[i].rotationPointY;
    	this.Energy[i].rotationPointY = ((this.Energy[i].rotationPointY - this.Energy[i].rotateAngleY) < -35)? -6 : this.Energy[i].rotationPointY; 
    	
    	this.Energy[i].rotationPointZ = -0.5F + (this.Energy[i].rotationPointY * this.Energy[i].rotationPointY) / 80;
		this.Energy[i].rotationPointX =	((this.Energy[i].rotationPointY * this.Energy[i].rotationPointY) / -1200);
		
	    float red = 1 - this.Energy[i].rotationPointY * -0.04F;
	    float green = 1 - this.Energy[i].rotationPointY * -0.04F;
	    float blue = 1F;
	    
	    GlStateManager.color(red, green, blue);
		
		
	    
		this.Energy[i].render(f5);
		GL11.glPopMatrix();
    }
    
    
    for (int i = 0; i < this.Arrow.length; ++i) // - this.fade
    {	
    	GL11.glPushMatrix();
    	GL11.glRotatef(i * 2, 0, 1F, 0);
    	--this.Arrow[i].rotationPointY;
    	this.Arrow[i].rotationPointY = (this.Arrow[i].rotationPointY < -80)? -6 : this.Arrow[i].rotationPointY; 
    	this.Arrow[i].rotationPointZ = ((float) Math.cos((this.Arrow[i].rotationPointY / 20) + -1.0F) * 1.5F) - 1; 
    	//this.Arrow[i].rotationPointZ = -0.5F + (this.Energy[i].rotationPointY * this.Energy[i].rotationPointY) / 80;
		//this.Arrow[i].rotationPointX =	((this.Energy[i].rotationPointY * this.Energy[i].rotationPointY) / -1200);
		
	    float red = 0;
	    float green = 0;
	    float blue = 1F;
	    
	    GlStateManager.color(red, green, blue);
		
		
	    
		this.Arrow[i].render(f5);
		GL11.glPopMatrix();
    }
  
    
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {

	  

  }
}
