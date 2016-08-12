package com.Splosions.ModularBosses.client.models.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelBomb - Either Mojang or a mod author
 * Created using Tabula 5.1.0
 */
public class ModelBait extends ModelBase {
    public ModelRenderer shape21;
    public ModelRenderer shape3;
    public ModelRenderer shape3_1;
    public ModelRenderer shape3_2;
    public ModelRenderer shape3_3;
    public ModelRenderer shape3_4;
    public ModelRenderer shape9;
    public ModelRenderer shape4;
    public ModelRenderer shape3_5;
    public ModelRenderer shape8;
    public ModelRenderer shape2;
    public ModelRenderer shape1;
    public ModelRenderer shape7;
    public ModelRenderer shape6;
    public ModelRenderer shape5;
    public ModelRenderer shape10;
    public ModelRenderer shape11;
    public ModelRenderer shape12;
    public ModelRenderer shape13;

    public ModelBait() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.shape2.addBox(-3.0F, -2.0F, -3.0F, 8, 10, 6, 0.0F);
        this.shape21 = new ModelRenderer(this, 0, 0);
        this.shape21.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.shape21.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.shape7 = new ModelRenderer(this, 0, 0);
        this.shape7.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.shape7.addBox(-4.0F, 0.0F, -4.0F, 10, 6, 8, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-1.0F, 13.01F, 0.0F);
        this.shape1.addBox(-2.0F, -2.0F, -4.0F, 6, 10, 8, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 22);
        this.shape3.setRotationPoint(8.0F, 17.0F, 0.0F);
        this.shape3.addBox(0.0F, 0.0F, -4.0F, 2, 2, 8, 0.0F);
        this.shape8 = new ModelRenderer(this, 0, 0);
        this.shape8.setRotationPoint(-1.0F, 13.01F, 0.0F);
        this.shape8.addBox(-1.0F, -3.0F, -3.0F, 4, 12, 6, 0.0F);
        this.shape3_5 = new ModelRenderer(this, 0, 0);
        this.shape3_5.setRotationPoint(-1.0F, 13.0F, 0.01F);
        this.shape3_5.addBox(-2.0F, -1.0F, -5.0F, 6, 8, 10, 0.0F);
        this.shape10 = new ModelRenderer(this, 0, 0);
        this.shape10.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.shape10.addBox(-5.0F, 0.0F, -2.0F, 12, 6, 4, 0.0F);
        this.shape3_3 = new ModelRenderer(this, 0, 22);
        this.shape3_3.setRotationPoint(10.0F, 13.0F, 0.0F);
        this.shape3_3.addBox(-2.0F, 0.0F, -4.0F, 2, 2, 8, 0.0F);
        this.shape3_2 = new ModelRenderer(this, 0, 22);
        this.shape3_2.setRotationPoint(-10.0F, 13.0F, 0.0F);
        this.shape3_2.addBox(0.0F, 0.0F, -4.0F, 2, 2, 8, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 0);
        this.shape9.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.shape9.addBox(-2.0F, -3.0F, -2.0F, 6, 12, 4, 0.0F);
        this.shape3_1 = new ModelRenderer(this, 0, 22);
        this.shape3_1.setRotationPoint(-8.0F, 17.0F, 0.0F);
        this.shape3_1.addBox(-2.0F, 0.0F, -4.0F, 2, 2, 8, 0.0F);
        this.shape5 = new ModelRenderer(this, 0, 0);
        this.shape5.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.shape5.addBox(-3.0F, -1.0F, -4.0F, 8, 8, 8, 0.0F);
        this.shape12 = new ModelRenderer(this, 0, 0);
        this.shape12.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.shape12.addBox(-1.0F, 0.0F, -6.0F, 4, 6, 12, 0.0F);
        this.shape11 = new ModelRenderer(this, 0, 0);
        this.shape11.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.shape11.addBox(-5.0F, 1.0F, -3.0F, 12, 4, 6, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.shape6.addBox(-4.0F, -1.0F, -3.0F, 10, 8, 6, 0.0F);
        this.shape13 = new ModelRenderer(this, 0, 0);
        this.shape13.setRotationPoint(-1.0F, 13.0F, 0.01F);
        this.shape13.addBox(-2.0F, 1.0F, -6.0F, 6, 4, 12, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 0);
        this.shape4.setRotationPoint(-1.0F, 13.0F, 0.0F);
        this.shape4.addBox(-3.0F, 0.0F, -5.0F, 8, 6, 10, 0.0F);
        this.shape3_4 = new ModelRenderer(this, 0, 24);
        this.shape3_4.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.shape3_4.addBox(-9.0F, 0.0F, -2.0F, 18, 4, 4, 0.0F);
        this.shape21.addChild(this.shape2);
        this.shape21.addChild(this.shape7);
        this.shape21.addChild(this.shape1);
        this.shape21.addChild(this.shape3);
        this.shape21.addChild(this.shape8);
        this.shape21.addChild(this.shape3_5);
        this.shape21.addChild(this.shape10);
        this.shape21.addChild(this.shape3_3);
        this.shape21.addChild(this.shape3_2);
        this.shape21.addChild(this.shape9);
        this.shape21.addChild(this.shape3_1);
        this.shape21.addChild(this.shape5);
        this.shape21.addChild(this.shape12);
        this.shape21.addChild(this.shape11);
        this.shape21.addChild(this.shape6);
        this.shape21.addChild(this.shape13);
        this.shape21.addChild(this.shape4);
        this.shape21.addChild(this.shape3_4);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape21.render(f5);
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
