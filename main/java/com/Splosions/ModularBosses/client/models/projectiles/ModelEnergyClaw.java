package com.Splosions.ModularBosses.client.models.projectiles;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelEnergyClaw extends ModelBase
{
  //fields
	private ModelRenderer[] Energy = new ModelRenderer[360];
	
	int pixelY = 0;
	int pixelX = 0;
	int pixelZ = 0;
	int fade = 0;
 
  
  public ModelEnergyClaw()
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
    

    

  }
  

  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);


   	this.fade = (entity.ticksExisted > 50)? (entity.ticksExisted - 50) * 6 : 0; 

    for (int i = 0; i < this.Energy.length; ++i) // - this.fade
    {	
    	GL11.glPushMatrix();
    	GL11.glRotatef(i, 0, 1F, 0);

    	--this.Energy[i].rotationPointY;
    	this.Energy[i].rotationPointY = ((this.Energy[i].rotationPointY - this.Energy[i].rotateAngleY) < -35)? -6 : this.Energy[i].rotationPointY; 
    	
    	this.Energy[i].rotationPointZ = -0.5F + (this.Energy[i].rotationPointY * this.Energy[i].rotationPointY) / 80;
		this.Energy[i].rotationPointX =	((this.Energy[i].rotationPointY * this.Energy[i].rotationPointY) / -1200);
		
	    float red = 1;
	    float green = 0;
	    float blue = 0;
	    
	    GlStateManager.color(red, green, blue);
		
		
	    
		this.Energy[i].render(f5);
		GL11.glPopMatrix();
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
