package com.Splosions.ModularBosses.client.models.projectiles;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEnergyClaw extends ModelBase
{
  //fields
	private ModelRenderer[] Energy = new ModelRenderer[100];
	
	int pixelY = 0;
	int pixelX = 0;
	int pixelZ = 0;

	int startMin = -20;
	int startMax = 20;
 
  
  public ModelEnergyClaw()
  {
    textureWidth = 4;
    textureHeight = 3;
    

    
    for (int i = 0; i < this.Energy.length; ++i)
    {
     	this.pixelY = TargetUtils.getRanNum(startMin, startMax);
     	this.pixelX = TargetUtils.getRanNum(-1, 1);
    	this.Energy[i] = new ModelRenderer(this, 0, 0);
        this.Energy[i].addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.Energy[i].setRotationPoint(0, this.pixelY, 0);
        this.Energy[i].rotateAngleY = this.pixelX;
    }
    

    

  }
  

  
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		int rotate = 45;

		for (int b = 1; b < 3; ++b) {
			for (int i = 0; i < this.Energy.length; ++i) {

				GL11.glPushMatrix();

				if (b == 1) {
					GL11.glRotatef(rotate, 0, 0, 1);	
				} else {
					GL11.glRotatef(-rotate, 0, 0, 1);	
				}
				
				
				

				--this.Energy[i].rotationPointY;

				this.Energy[i].rotationPointY = (this.Energy[i].rotationPointY < startMin) ? startMax
						: this.Energy[i].rotationPointY;
				this.Energy[i].rotationPointX = MathHelper.cos(this.Energy[i].rotationPointY / 13)
						* this.Energy[i].rotateAngleY;
				this.Energy[i].rotationPointZ = MathHelper.sin((this.Energy[i].rotationPointY + 15) / 10) * 2;

				float red = 1F;
				float green = 0;
				float blue = 0;

				GlStateManager.color(red, green, blue);

				this.Energy[i].render(f5);
				GL11.glPopMatrix();
			}
		}
	}
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
