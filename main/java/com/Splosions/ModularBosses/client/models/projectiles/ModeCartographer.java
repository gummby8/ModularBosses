package com.Splosions.ModularBosses.client.models.projectiles;



import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;


public class ModeCartographer extends ModelBase
{
	



	 public ModelRenderer part;
 
  public ModeCartographer()
  {
    textureWidth = 10;
    textureHeight = 10;
  

    part = new ModelRenderer(this, 0, 0);
    part.addBox(0F, 0F, 0F, 1, 1, 1);
    part.setRotationPoint(0F, 0F, 0F);
    part.setTextureSize(1, 1);

    
    
    
  }
  
  
  float red;
  float green;
  float blue;
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    part.render(f5);
    
   }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)  {
  
  

	  
  
  }
}
