package com.Splosions.ModularBosses.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.entity.EntityBrain;
import com.Splosions.ModularBosses.util.TargetUtils;

/**
 * Brain - Undefined
 * Created using Tabula 5.1.0
 */
public class ModelSpark extends ModelBase {
	

    public ModelSpark() {
        this.textureWidth = 1;
        this.textureHeight = 1;
 
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    
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
