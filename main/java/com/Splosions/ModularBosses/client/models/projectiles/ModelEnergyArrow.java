package com.Splosions.ModularBosses.client.models.projectiles;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEnergyArrow extends ModelBase
{
  //fields
	private ModelRenderer[] Energy = new ModelRenderer[300];
	int pixelY = 0;
	int pixelX = 0;
	int pixelZ = 0;
	int fade = 0;
 
  
  public ModelEnergyArrow()
  {
    textureWidth = 4;
    textureHeight = 3;
    
    for (int i = 0; i < this.Energy.length; ++i)
    {
    	this.pixelY = randInt(1,35) * -1;
    	this.pixelX = randInt(1,14) - 7;
    	this.pixelZ = randInt(1,3) - 2;
        this.Energy[i] = new ModelRenderer(this, 0, 0);
        this.Energy[i].addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.Energy[i].setRotationPoint(this.pixelX, this.pixelY, this.pixelZ);
        this.Energy[i].setTextureSize(4, 3);
        this.Energy[i].mirror = true;
        setRotation(this.Energy[i], 0F, 0F, 0F);
        this.Energy[i].rotateAngleY = this.pixelX;
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


   	this.fade = (entity.ticksExisted > 50)? (entity.ticksExisted - 50) * 6 : 0; 

    for (int i = 0; i < this.Energy.length - this.fade; ++i)
    {	
    	
    	this.Energy[i].rotationPointY -= 1;
    	
    	
    	if (this.Energy[i].rotationPointY < -35)
    	{
    		this.Energy[i].rotationPointY = 1;
    	}
	
	    float Xoff;
	    
	    if (this.Energy[i].rotateAngleY > 0){
	    	Xoff = (this.Energy[i].rotateAngleY / 1.6F);
	    } else if (this.Energy[i].rotateAngleY < 0){
	    	Xoff = (this.Energy[i].rotateAngleY / -1.6F);
	    } else {
	    	Xoff = 1;
	    }
    	
    	this.Energy[i].rotationPointZ = Xoff + (this.Energy[i].rotationPointY * this.Energy[i].rotationPointY) / 60;
		this.Energy[i].rotationPointX =	this.Energy[i].rotateAngleY + ((this.Energy[i].rotationPointY * this.Energy[i].rotationPointY) / -1200 * this.Energy[i].rotateAngleY);
		
		this.Energy[i].render(f5);
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
