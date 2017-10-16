package com.Splosions.ModularBosses.client.models.entity;

import com.Splosions.ModularBosses.entity.EntityHeart;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Heart - Undefined
 * Created using Tabula 5.1.0
 */
public class ModelHeart extends ModelBase {
    public ModelRenderer Heart;
    public ModelRenderer MAIN;
    public ModelRenderer front4;
    public ModelRenderer front3;
    public ModelRenderer front2;
    public ModelRenderer front1;
    public ModelRenderer rear1;
    public ModelRenderer rear2;
    public ModelRenderer rear3;
    public ModelRenderer rear4;
    public ModelRenderer MAIN_1;
    public ModelRenderer front41;
    public ModelRenderer front31;
    public ModelRenderer front21;
    public ModelRenderer front11;
    public ModelRenderer rear11;
    public ModelRenderer rear21;
    public ModelRenderer rear31;
    public ModelRenderer rear41;

    public ModelHeart() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.front3 = new ModelRenderer(this, 0, 0);
        this.front3.setRotationPoint(10.0F, -14.0F, -23.0F);
        this.front3.addBox(-12.0F, -17.0F, -2.0F, 24, 34, 4, 0.0F);
        this.front31 = new ModelRenderer(this, 0, 0);
        this.front31.setRotationPoint(0.0F, -18.0F, 1.0F);
        this.front31.addBox(-8.0F, -3.0F, -1.0F, 16, 6, 2, 0.0F);
        this.rear31 = new ModelRenderer(this, 0, 0);
        this.rear31.setRotationPoint(0.0F, -18.0F, -1.0F);
        this.rear31.addBox(-8.0F, -3.0F, -1.0F, 16, 6, 2, 0.0F);
        this.rear41 = new ModelRenderer(this, 0, 0);
        this.rear41.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.rear41.addBox(-5.0F, -3.0F, -1.0F, 10, 6, 2, 0.0F);
        this.Heart = new ModelRenderer(this, 0, 0);
        this.Heart.setRotationPoint(-10.0F, 12.5F, 0.0F);
        this.Heart.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.MAIN_1 = new ModelRenderer(this, 0, 0);
        this.MAIN_1.setRotationPoint(10.0F, 31.0F, 0.0F);
        this.MAIN_1.addBox(-15.0F, -2.0F, -5.0F, 30, 4, 10, 0.0F);
        this.rear4 = new ModelRenderer(this, 0, 0);
        this.rear4.setRotationPoint(10.0F, -12.0F, 26.0F);
        this.rear4.addBox(-8.5F, -11.0F, -2.0F, 17, 22, 4, 0.0F);
        this.front2 = new ModelRenderer(this, 0, 0);
        this.front2.setRotationPoint(10.0F, -16.0F, -18.0F);
        this.front2.addBox(-14.0F, -22.0F, -3.0F, 28, 44, 6, 0.0F);
        this.rear3 = new ModelRenderer(this, 0, 0);
        this.rear3.setRotationPoint(10.0F, -14.0F, 23.0F);
        this.rear3.addBox(-12.0F, -17.0F, -2.0F, 24, 34, 4, 0.0F);
        this.front11 = new ModelRenderer(this, 0, 0);
        this.front11.setRotationPoint(0.0F, -30.0F, 0.0F);
        this.front11.addBox(-14.0F, -3.0F, -3.5F, 28, 6, 7, 0.0F);
        this.rear21 = new ModelRenderer(this, 0, 0);
        this.rear21.setRotationPoint(0.0F, -24.0F, 0.0F);
        this.rear21.addBox(-10.0F, -3.0F, -2.0F, 20, 6, 3, 0.0F);
        this.front4 = new ModelRenderer(this, 0, 0);
        this.front4.setRotationPoint(10.0F, -12.0F, -26.0F);
        this.front4.addBox(-8.5F, -11.0F, -2.0F, 17, 22, 4, 0.0F);
        this.rear2 = new ModelRenderer(this, 0, 0);
        this.rear2.setRotationPoint(10.0F, -16.0F, 18.0F);
        this.rear2.addBox(-14.0F, -22.0F, -3.0F, 28, 44, 6, 0.0F);
        this.front41 = new ModelRenderer(this, 0, 0);
        this.front41.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.front41.addBox(-5.0F, -3.0F, -1.0F, 10, 6, 2, 0.0F);
        this.MAIN = new ModelRenderer(this, 0, 0);
        this.MAIN.setRotationPoint(0.0F, -24.0F, 0.0F);
        this.MAIN.addBox(-12.0F, -31.0F, -10.0F, 44, 62, 20, 0.0F);
        this.front1 = new ModelRenderer(this, 0, 0);
        this.front1.setRotationPoint(10.0F, -19.0F, -10.0F);
        this.front1.addBox(-18.0F, -27.5F, -6.0F, 36, 58, 12, 0.0F);
        this.rear11 = new ModelRenderer(this, 0, 0);
        this.rear11.setRotationPoint(0.0F, -30.0F, 0.0F);
        this.rear11.addBox(-14.0F, -3.0F, -3.5F, 28, 6, 7, 0.0F);
        this.rear1 = new ModelRenderer(this, 0, 0);
        this.rear1.setRotationPoint(10.0F, -19.0F, 10.0F);
        this.rear1.addBox(-18.0F, -27.5F, -6.0F, 36, 58, 12, 0.0F);
        this.front21 = new ModelRenderer(this, 0, 0);
        this.front21.setRotationPoint(0.0F, -24.0F, 0.0F);
        this.front21.addBox(-10.0F, -3.0F, -1.0F, 20, 6, 3, 0.0F);
        this.Heart.addChild(this.front3);
        this.front3.addChild(this.front31);
        this.rear3.addChild(this.rear31);
        this.rear4.addChild(this.rear41);
        this.MAIN.addChild(this.MAIN_1);
        this.Heart.addChild(this.rear4);
        this.Heart.addChild(this.front2);
        this.Heart.addChild(this.rear3);
        this.front1.addChild(this.front11);
        this.rear2.addChild(this.rear21);
        this.Heart.addChild(this.front4);
        this.Heart.addChild(this.rear2);
        this.front4.addChild(this.front41);
        this.Heart.addChild(this.MAIN);
        this.Heart.addChild(this.front1);
        this.rear1.addChild(this.rear11);
        this.Heart.addChild(this.rear1);
        this.front2.addChild(this.front21);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
    	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	this.Heart.render(f5);
        
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    float hp;
    EntityHeart heart;
    @Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
    	heart = (EntityHeart)entity;
    	hp = 10 - (heart.getMaxHealth() / heart.getHealth());
    	hp = (hp <= 1)?1:hp;
    	
    	if (heart.invulnerable > 0){
    		hp = 1;
    	}
    	this.rear1.rotationPointZ = MathHelper.sin(par3/hp ) + 9F;
    	this.rear2.rotationPointZ = MathHelper.sin(par3/hp) * 2 + 16F;
    	this.rear3.rotationPointZ = MathHelper.sin(par3/hp) * 4 + 19F;
    	this.rear4.rotationPointZ = MathHelper.sin(par3/hp) * 6 + 20F;
    	
    	this.front1.rotationPointZ = -MathHelper.sin(par3/hp) - 9F;
    	this.front2.rotationPointZ = -MathHelper.sin(par3/hp) * 2 - 16F;
    	this.front3.rotationPointZ = -MathHelper.sin(par3/hp) * 4 - 19F;
    	this.front4.rotationPointZ = -MathHelper.sin(par3/hp) * 6 - 20F;
    }

}
