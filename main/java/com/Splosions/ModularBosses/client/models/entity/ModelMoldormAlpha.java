package com.Splosions.ModularBosses.client.models.entity;



import com.Splosions.ModularBosses.client.models.FakeModelRenderer;
import com.Splosions.ModularBosses.entity.EntityMoldormAlpha;
import com.Splosions.ModularBosses.util.ModelUtils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;



public class ModelMoldormAlpha extends ModelBase {
    public ModelRenderer Part1;
    public ModelRenderer Part2;
    public ModelRenderer Part3;
    public ModelRenderer Part4;
    public ModelRenderer Part5;
    public ModelRenderer shape9;
    public ModelRenderer shape4;
    public ModelRenderer shape3;
    public ModelRenderer shape2;
    public ModelRenderer shape1;
    public ModelRenderer shape8;
    public ModelRenderer shape7;
    public ModelRenderer shape6;
    public ModelRenderer shape5;
    public ModelRenderer shape11;
    public ModelRenderer shape10;
    public ModelRenderer shape12;
    public ModelRenderer shape13;
    public ModelRenderer eyeR;
    public ModelRenderer eyeL;
    public ModelRenderer shape75;
    public ModelRenderer shape75_1;
    public ModelRenderer pupilR;
    public ModelRenderer shape75_2;
    public ModelRenderer shape75_3;
    public ModelRenderer pupilL;
    public ModelRenderer shape9_1;
    public ModelRenderer shape4_1;
    public ModelRenderer shape3_1;
    public ModelRenderer shape2_1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape8_1;
    public ModelRenderer shape7_1;
    public ModelRenderer shape6_1;
    public ModelRenderer shape5_1;
    public ModelRenderer shape11_1;
    public ModelRenderer shape10_1;
    public ModelRenderer shape12_1;
    public ModelRenderer shape13_1;
    public ModelRenderer shape9_2;
    public ModelRenderer shape4_2;
    public ModelRenderer shape3_2;
    public ModelRenderer shape2_2;
    public ModelRenderer shape1_2;
    public ModelRenderer shape8_2;
    public ModelRenderer shape7_2;
    public ModelRenderer shape6_2;
    public ModelRenderer shape5_2;
    public ModelRenderer shape11_2;
    public ModelRenderer shape10_2;
    public ModelRenderer shape12_2;
    public ModelRenderer shape13_2;
    public ModelRenderer shape9_3;
    public ModelRenderer shape4_3;
    public ModelRenderer shape3_3;
    public ModelRenderer shape2_3;
    public ModelRenderer shape1_3;
    public ModelRenderer shape8_3;
    public ModelRenderer shape7_3;
    public ModelRenderer shape6_3;
    public ModelRenderer shape5_3;
    public ModelRenderer shape11_3;
    public ModelRenderer shape10_3;
    public ModelRenderer shape12_3;
    public ModelRenderer shape13_3;
    public ModelRenderer shape9_4;
    public ModelRenderer shape4_4;
    public ModelRenderer shape3_4;
    public ModelRenderer shape2_4;
    public ModelRenderer shape1_4;
    public ModelRenderer shape8_4;
    public ModelRenderer shape7_4;
    public ModelRenderer shape6_4;
    public ModelRenderer shape5_4;
    public ModelRenderer shape11_4;
    public ModelRenderer shape10_4;
    public ModelRenderer shape12_4;
    public ModelRenderer shape13_4;

    public ModelMoldormAlpha() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.shape10_1 = new ModelRenderer(this, 0, 0);
        this.shape10_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape10_1.addBox(-33.0F, -19.0F, -13.0F, 66, 38, 26, 0.0F);
        this.shape4_2 = new ModelRenderer(this, 0, 0);
        this.shape4_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4_2.addBox(-22.0F, -16.0F, -26.0F, 44, 32, 52, 0.0F);
        this.shape13 = new ModelRenderer(this, 0, 0);
        this.shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape13.addBox(-22.0F, -16.0F, -36.0F, 44, 32, 72, 0.0F);
        this.shape9_2 = new ModelRenderer(this, 28, 16);
        this.shape9_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape9_2.addBox(-16.0F, -30.0F, -10.0F, 32, 60, 20, 0.0F);
        this.shape7 = new ModelRenderer(this, 0, 0);
        this.shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape7.addBox(-32.0F, -22.0F, -28.0F, 64, 44, 56, 0.0F);
        this.shape8_3 = new ModelRenderer(this, 41, 0);
        this.shape8_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape8_3.addBox(-7.0F, -27.0F, -13.0F, 14, 54, 26, 0.0F);
        this.shape7_4 = new ModelRenderer(this, 0, 0);
        this.shape7_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape7_4.addBox(-20.0F, -10.0F, -16.0F, 40, 20, 32, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 0);
        this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4.addBox(-28.0F, -22.0F, -32.0F, 56, 44, 64, 0.0F);
        this.shape4_3 = new ModelRenderer(this, 0, 0);
        this.shape4_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4_3.addBox(-19.0F, -13.0F, -23.0F, 38, 26, 46, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(-28.0F, -32.0F, -22.0F, 56, 64, 44, 0.0F);
        this.shape8_4 = new ModelRenderer(this, 182, 188);
        this.shape8_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape8_4.addBox(-4.0F, -24.0F, -10.0F, 8, 48, 20, 0.0F);
        this.shape4_1 = new ModelRenderer(this, 0, 0);
        this.shape4_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4_1.addBox(-25.0F, -19.0F, -29.0F, 50, 38, 58, 0.0F);
        this.Part2 = new ModelRenderer(this, 0, 0);
        this.Part2.setRotationPoint(0.0F, -1.0F, 69.0F);
        this.Part2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.eyeL = new ModelRenderer(this, 0, 200);
        this.eyeL.setRotationPoint(28.0F, -20.0F, -31.0F);
        this.eyeL.addBox(-6.0F, -6.0F, -4.5F, 12, 12, 9, 0.0F);
        this.setRotateAngle(eyeL, 0.0F, -0.7853981633974483F, 0.0F);
        this.shape10_4 = new ModelRenderer(this, 143, 227);
        this.shape10_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape10_4.addBox(-24.0F, -10.0F, -4.0F, 48, 20, 8, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 0, 0);
        this.shape1_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1_3.addBox(-14.0F, -23.0F, -19.0F, 26, 46, 38, 0.0F);
        this.shape6_3 = new ModelRenderer(this, 0, 0);
        this.shape6_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6_3.addBox(-23.0F, -19.0F, -13.0F, 46, 38, 26, 0.0F);
        this.shape3_2 = new ModelRenderer(this, 0, 0);
        this.shape3_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3_2.addBox(-16.0F, -22.0F, -26.0F, 32, 44, 52, 0.0F);
        this.shape7_2 = new ModelRenderer(this, 0, 0);
        this.shape7_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape7_2.addBox(-26.0F, -16.0F, -22.0F, 52, 32, 44, 0.0F);
        this.shape5_3 = new ModelRenderer(this, 0, 0);
        this.shape5_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5_3.addBox(-19.0F, -19.0F, -19.0F, 38, 38, 38, 0.0F);
        this.shape3_4 = new ModelRenderer(this, 0, 0);
        this.shape3_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3_4.addBox(-10.0F, -16.0F, -20.0F, 20, 32, 40, 0.0F);
        this.shape13_4 = new ModelRenderer(this, 119, 199);
        this.shape13_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape13_4.addBox(-10.0F, -4.0F, -24.0F, 20, 8, 48, 0.0F);
        this.eyeR = new ModelRenderer(this, 0, 200);
        this.eyeR.setRotationPoint(-28.0F, -20.0F, -31.0F);
        this.eyeR.addBox(-6.0F, -6.0F, -4.5F, 12, 12, 9, 0.0F);
        this.setRotateAngle(eyeR, 0.0F, 0.7853981633974483F, 0.0F);
        this.shape10_3 = new ModelRenderer(this, 0, 0);
        this.shape10_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape10_3.addBox(-27.0F, -13.0F, -7.0F, 54, 26, 14, 0.0F);
        this.shape11_2 = new ModelRenderer(this, 0, 0);
        this.shape11_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape11_2.addBox(-30.0F, -10.0F, -16.0F, 60, 20, 32, 0.0F);
        this.Part5 = new ModelRenderer(this, 46, 200);
        this.Part5.setRotationPoint(0.0F, 8.0F, 240.0F);
        this.Part5.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.shape13_2 = new ModelRenderer(this, 0, 0);
        this.shape13_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape13_2.addBox(-16.0F, -10.0F, -30.0F, 32, 20, 60, 0.0F);
        this.shape5_4 = new ModelRenderer(this, 0, 0);
        this.shape5_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5_4.addBox(-16.0F, -16.0F, -16.0F, 32, 32, 32, 0.0F);
        this.shape11 = new ModelRenderer(this, 0, 0);
        this.shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape11.addBox(-36.0F, -16.0F, -22.0F, 72, 32, 44, 0.0F);
        this.shape3_3 = new ModelRenderer(this, 0, 0);
        this.shape3_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3_3.addBox(-13.0F, -19.0F, -23.0F, 26, 38, 46, 0.0F);
        this.shape8_1 = new ModelRenderer(this, 41, 0);
        this.shape8_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape8_1.addBox(-13.0F, -33.0F, -19.0F, 26, 66, 38, 0.0F);
        this.Part4 = new ModelRenderer(this, 0, 0);
        this.Part4.setRotationPoint(0.0F, 5.0F, 189.0F);
        this.Part4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.shape12_4 = new ModelRenderer(this, 143, 188);
        this.shape12_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape12_4.addBox(-4.0F, -10.0F, -24.0F, 8, 20, 48, 0.0F);
        this.shape9_1 = new ModelRenderer(this, 28, 16);
        this.shape9_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape9_1.addBox(-19.0F, -33.0F, -13.0F, 38, 66, 26, 0.0F);
        this.shape4_4 = new ModelRenderer(this, 0, 0);
        this.shape4_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4_4.addBox(-16.0F, -10.0F, -20.0F, 32, 20, 40, 0.0F);
        this.shape5_1 = new ModelRenderer(this, 0, 0);
        this.shape5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5_1.addBox(-25.0F, -25.0F, -25.0F, 50, 50, 50, 0.0F);
        this.shape2_3 = new ModelRenderer(this, 0, 0);
        this.shape2_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2_3.addBox(-19.0F, -23.0F, -13.0F, 38, 46, 26, 0.0F);
        this.shape8 = new ModelRenderer(this, 41, 0);
        this.shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape8.addBox(-16.0F, -36.0F, -22.0F, 32, 72, 44, 0.0F);
        this.pupilR = new ModelRenderer(this, 0, 180);
        this.pupilR.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.pupilR.addBox(-5.5F, -2.0F, 0.0F, 4, 4, 1, 0.0F);
        this.shape3_1 = new ModelRenderer(this, 0, 0);
        this.shape3_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3_1.addBox(-19.0F, -25.0F, -29.0F, 38, 50, 58, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(-22.0F, -32.0F, -28.0F, 44, 64, 56, 0.0F);
        this.shape12 = new ModelRenderer(this, 0, 0);
        this.shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape12.addBox(-16.0F, -22.0F, -36.0F, 32, 44, 72, 0.0F);
        this.shape6_2 = new ModelRenderer(this, 0, 0);
        this.shape6_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6_2.addBox(-26.0F, -22.0F, -16.0F, 52, 44, 32, 0.0F);
        this.shape10 = new ModelRenderer(this, 0, 0);
        this.shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape10.addBox(-36.0F, -22.0F, -16.0F, 72, 44, 32, 0.0F);
        this.shape5_2 = new ModelRenderer(this, 0, 0);
        this.shape5_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5_2.addBox(-22.0F, -22.0F, -22.0F, 44, 44, 44, 0.0F);
        this.shape12_2 = new ModelRenderer(this, 0, 0);
        this.shape12_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape12_2.addBox(-10.0F, -16.0F, -30.0F, 20, 32, 60, 0.0F);
        this.shape9_3 = new ModelRenderer(this, 28, 16);
        this.shape9_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape9_3.addBox(-13.0F, -27.0F, -7.0F, 26, 54, 14, 0.0F);
        this.shape11_1 = new ModelRenderer(this, 0, 0);
        this.shape11_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape11_1.addBox(-33.0F, -13.0F, -19.0F, 66, 26, 38, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 0);
        this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3.addBox(-22.0F, -28.0F, -32.0F, 44, 56, 64, 0.0F);
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1_1.addBox(-20.0F, -29.0F, -25.0F, 38, 58, 50, 0.0F);
        this.shape7_3 = new ModelRenderer(this, 0, 0);
        this.shape7_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape7_3.addBox(-23.0F, -13.0F, -19.0F, 46, 26, 38, 0.0F);
        this.shape75 = new ModelRenderer(this, 0, 200);
        this.shape75.setRotationPoint(-5.0F, -7.5F, -3.5F);
        this.shape75.addBox(0.0F, 0.0F, 0.0F, 10, 15, 6, 0.0F);
        this.shape1_2 = new ModelRenderer(this, 0, 0);
        this.shape1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1_2.addBox(-17.0F, -26.0F, -22.0F, 32, 52, 44, 0.0F);
        this.shape75_2 = new ModelRenderer(this, 0, 200);
        this.shape75_2.setRotationPoint(-5.0F, -7.5F, -3.5F);
        this.shape75_2.addBox(0.0F, 0.0F, 0.0F, 10, 15, 6, 0.0F);
        this.shape7_1 = new ModelRenderer(this, 0, 0);
        this.shape7_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape7_1.addBox(-29.0F, -19.0F, -25.0F, 58, 38, 50, 0.0F);
        this.shape6_4 = new ModelRenderer(this, 0, 0);
        this.shape6_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6_4.addBox(-20.0F, -16.0F, -10.0F, 40, 32, 20, 0.0F);
        this.shape2_1 = new ModelRenderer(this, 0, 0);
        this.shape2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2_1.addBox(-25.0F, -29.0F, -19.0F, 50, 58, 38, 0.0F);
        this.shape13_1 = new ModelRenderer(this, 0, 0);
        this.shape13_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape13_1.addBox(-19.0F, -13.0F, -33.0F, 38, 26, 66, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 0, 0);
        this.shape1_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1_4.addBox(-11.0F, -20.0F, -16.0F, 20, 40, 32, 0.0F);
        this.pupilL = new ModelRenderer(this, 0, 180);
        this.pupilL.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.pupilL.addBox(1.5F, -2.0F, 0.0F, 4, 4, 1, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6.addBox(-32.0F, -28.0F, -22.0F, 64, 56, 44, 0.0F);
        this.shape6_1 = new ModelRenderer(this, 0, 0);
        this.shape6_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6_1.addBox(-29.0F, -25.0F, -19.0F, 58, 50, 38, 0.0F);
        this.shape5 = new ModelRenderer(this, 0, 0);
        this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5.addBox(-28.0F, -28.0F, -28.0F, 56, 56, 56, 0.0F);
        this.shape75_3 = new ModelRenderer(this, 0, 200);
        this.shape75_3.setRotationPoint(-7.5F, -5.0F, -3.5F);
        this.shape75_3.addBox(0.0F, 0.0F, 0.0F, 15, 10, 6, 0.0F);
        this.Part1 = new ModelRenderer(this, 0, 0);
        this.Part1.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Part1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.shape10_2 = new ModelRenderer(this, 0, 0);
        this.shape10_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape10_2.addBox(-30.0F, -16.0F, -10.0F, 60, 32, 20, 0.0F);
        this.shape75_1 = new ModelRenderer(this, 0, 200);
        this.shape75_1.setRotationPoint(-7.5F, -5.0F, -3.5F);
        this.shape75_1.addBox(0.0F, 0.0F, 0.0F, 15, 10, 6, 0.0F);
        this.shape9 = new ModelRenderer(this, 28, 16);
        this.shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape9.addBox(-22.0F, -36.0F, -16.0F, 44, 72, 32, 0.0F);
        this.shape8_2 = new ModelRenderer(this, 41, 0);
        this.shape8_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape8_2.addBox(-10.0F, -30.0F, -16.0F, 20, 60, 32, 0.0F);
        this.shape12_1 = new ModelRenderer(this, 0, 0);
        this.shape12_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape12_1.addBox(-13.0F, -19.0F, -33.0F, 26, 38, 66, 0.0F);
        this.shape11_3 = new ModelRenderer(this, 0, 0);
        this.shape11_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape11_3.addBox(-27.0F, -7.0F, -13.0F, 54, 14, 26, 0.0F);
        this.shape11_4 = new ModelRenderer(this, 119, 227);
        this.shape11_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape11_4.addBox(-24.0F, -4.0F, -10.0F, 48, 8, 20, 0.0F);
        this.shape9_4 = new ModelRenderer(this, 179, 199);
        this.shape9_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape9_4.addBox(-10.0F, -24.0F, -4.0F, 20, 48, 8, 0.0F);
        this.shape13_3 = new ModelRenderer(this, 0, 0);
        this.shape13_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape13_3.addBox(-13.0F, -7.0F, -27.0F, 26, 14, 54, 0.0F);
        this.shape2_2 = new ModelRenderer(this, 0, 0);
        this.shape2_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2_2.addBox(-22.0F, -26.0F, -16.0F, 44, 52, 32, 0.0F);
        this.shape2_4 = new ModelRenderer(this, 0, 0);
        this.shape2_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2_4.addBox(-16.0F, -20.0F, -10.0F, 32, 40, 20, 0.0F);
        this.Part3 = new ModelRenderer(this, 0, 0);
        this.Part3.setRotationPoint(0.0F, 2.0F, 132.0F);
        this.Part3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.shape12_3 = new ModelRenderer(this, 0, 0);
        this.shape12_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape12_3.addBox(-7.0F, -13.0F, -27.0F, 14, 26, 54, 0.0F);
        this.Part2.addChild(this.shape10_1);
        this.Part3.addChild(this.shape4_2);
        this.Part1.addChild(this.shape13);
        this.Part3.addChild(this.shape9_2);
        this.Part1.addChild(this.shape7);
        this.Part4.addChild(this.shape8_3);
        this.Part5.addChild(this.shape7_4);
        this.Part1.addChild(this.shape4);
        this.Part4.addChild(this.shape4_3);
        this.Part1.addChild(this.shape2);
        this.Part5.addChild(this.shape8_4);
        this.Part2.addChild(this.shape4_1);
        this.Part1.addChild(this.eyeL);
        this.Part5.addChild(this.shape10_4);
        this.Part4.addChild(this.shape1_3);
        this.Part4.addChild(this.shape6_3);
        this.Part3.addChild(this.shape3_2);
        this.Part3.addChild(this.shape7_2);
        this.Part4.addChild(this.shape5_3);
        this.Part5.addChild(this.shape3_4);
        this.Part5.addChild(this.shape13_4);
        this.Part1.addChild(this.eyeR);
        this.Part4.addChild(this.shape10_3);
        this.Part3.addChild(this.shape11_2);
        this.Part3.addChild(this.shape13_2);
        this.Part5.addChild(this.shape5_4);
        this.Part1.addChild(this.shape11);
        this.Part4.addChild(this.shape3_3);
        this.Part2.addChild(this.shape8_1);
        this.Part5.addChild(this.shape12_4);
        this.Part2.addChild(this.shape9_1);
        this.Part5.addChild(this.shape4_4);
        this.Part2.addChild(this.shape5_1);
        this.Part4.addChild(this.shape2_3);
        this.Part1.addChild(this.shape8);
        this.eyeR.addChild(this.pupilR);
        this.Part2.addChild(this.shape3_1);
        this.Part1.addChild(this.shape1);
        this.Part1.addChild(this.shape12);
        this.Part3.addChild(this.shape6_2);
        this.Part1.addChild(this.shape10);
        this.Part3.addChild(this.shape5_2);
        this.Part3.addChild(this.shape12_2);
        this.Part4.addChild(this.shape9_3);
        this.Part2.addChild(this.shape11_1);
        this.Part1.addChild(this.shape3);
        this.Part2.addChild(this.shape1_1);
        this.Part4.addChild(this.shape7_3);
        this.eyeR.addChild(this.shape75);
        this.Part3.addChild(this.shape1_2);
        this.eyeL.addChild(this.shape75_2);
        this.Part2.addChild(this.shape7_1);
        this.Part5.addChild(this.shape6_4);
        this.Part2.addChild(this.shape2_1);
        this.Part2.addChild(this.shape13_1);
        this.Part5.addChild(this.shape1_4);
        this.eyeL.addChild(this.pupilL);
        this.Part1.addChild(this.shape6);
        this.Part2.addChild(this.shape6_1);
        this.Part1.addChild(this.shape5);
        this.eyeL.addChild(this.shape75_3);
        this.Part3.addChild(this.shape10_2);
        this.eyeR.addChild(this.shape75_1);
        this.Part1.addChild(this.shape9);
        this.Part3.addChild(this.shape8_2);
        this.Part2.addChild(this.shape12_1);
        this.Part4.addChild(this.shape11_3);
        this.Part5.addChild(this.shape11_4);
        this.Part5.addChild(this.shape9_4);
        this.Part4.addChild(this.shape13_3);
        this.Part3.addChild(this.shape2_2);
        this.Part5.addChild(this.shape2_4);
        this.Part4.addChild(this.shape12_3);
    }

    @Override
    public void render(Entity entity, float yaw, float partialTick, float f2, float f3, float f4, float f5) { 
    	EntityMoldormAlpha worm = (EntityMoldormAlpha) entity;
    	this.Part1.rotateAngleY = (float)Math.toRadians(yaw);
    	this.Part2.rotateAngleY = (float)Math.toRadians(worm.moldormPart2.rotationYaw);
    	this.Part3.rotateAngleY = (float)Math.toRadians(worm.moldormPart3.rotationYaw);
    	this.Part4.rotateAngleY = (float)Math.toRadians(worm.moldormPart4.rotationYaw);
    	this.Part5.rotateAngleY = (float)Math.toRadians(worm.moldormPart5.rotationYaw);
    	
    	this.Part2.rotationPointX = (float) (worm.moldormPart2.posX - worm.moldormPart1.posX) * 25;
    	this.Part2.rotationPointZ = (float) (worm.moldormPart2.posZ - worm.moldormPart1.posZ) * -25;
    	
    	this.Part3.rotationPointX = (float) (worm.moldormPart3.posX - worm.moldormPart1.posX) * 25;
    	this.Part3.rotationPointZ = (float) (worm.moldormPart3.posZ - worm.moldormPart1.posZ) * -25;

    	this.Part4.rotationPointX = (float) (worm.moldormPart4.posX - worm.moldormPart1.posX) * 22;
    	this.Part4.rotationPointZ = (float) (worm.moldormPart4.posZ - worm.moldormPart1.posZ) * -22;

    	//actual{XYZ} = prev{XYZ} + ({XYZ} - prev{XYZ}) * partialTicks.
    	
    	float p1actualX =  (float) ((worm.moldormPart1.lastTickPosX + (worm.moldormPart1.posX - worm.moldormPart1.lastTickPosX) * partialTick));
    	float p1actualZ =  (float) ((worm.moldormPart1.lastTickPosZ + (worm.moldormPart1.posZ - worm.moldormPart1.lastTickPosZ) * partialTick));
    	
    	float p5actualX =  (float) ((worm.moldormPart5.lastTickPosX + (worm.moldormPart5.posX - worm.moldormPart5.lastTickPosX) * partialTick));
    	float p5actualZ =  (float) ((worm.moldormPart5.lastTickPosZ + (worm.moldormPart5.posZ - worm.moldormPart5.lastTickPosZ) * partialTick));
    	
    	this.Part5.rotationPointX = (float) (p5actualX - worm.posX);
    	this.Part5.rotationPointZ = (float) (p5actualZ - worm.posZ);
    	
    	this.Part5.rotationPointX *= 22;
    	this.Part5.rotationPointZ *= -22;
    	
    	//this.Part5.rotationPointX = (float) (worm.moldormPart5.posX - worm.moldormPart1.posX) * 22;
    	//this.Part5.rotationPointZ = (float) (worm.moldormPart5.posZ - worm.moldormPart1.posZ) * -22;
    	setLivingAnimations((EntityLivingBase) entity,0,0,partialTick);
    	this.Part1.render(f5);
    	this.Part2.render(f5);
        this.Part3.render(f5);
        this.Part4.render(f5);
        this.Part5.render(f5);
        
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    
	@Override
	public void setLivingAnimations(EntityLivingBase entity, float par2, float par3, float partialTick) {
		setLivingAnimations((EntityMoldormAlpha) entity, par2, par3, partialTick);
	}


    
    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    private void setLivingAnimations(EntityMoldormAlpha worm, float par2, float par3, float PartialTick)  {

    	/**
    	 * Eyes spin around when damaged
    	 */
    	if (worm.hurtResistantTime > 0){
    		this.pupilR.rotateAngleZ = ((worm.ticksExisted * 50) + (PartialTick * 50)) * 0.0174F; 
    		this.pupilL.rotateAngleZ = ((worm.ticksExisted * 50) + (PartialTick * 50)) * 0.0174F;
    	} else {
    		this.pupilR.rotateAngleZ = 0; 
    		this.pupilL.rotateAngleZ = 0;
    	}
    	
    	
    	
    	
    	/**
    	
    	worm.part2Cur += ((worm.rotationYaw - worm.prevRotationYaw) * 0.2F) + ((0 - worm.part2Cur) * 0.02F);
    	this.Part2.rotateAngleY = (worm.part2Cur * 0.0174F) + 1.57F;
    	this.Part2.rotationPointX = (69 * MathHelper.cos(this.Part2.rotateAngleY));
    	this.Part2.rotationPointY = this.Part1.rotationPointY + 3;
    	this.Part2.rotationPointZ = (69 * MathHelper.sin(this.Part2.rotateAngleY));    	
    	this.Part2.rotateAngleY = (worm.part2Cur * -0.0174F);
    	 
    	worm.part3Cur += ((worm.rotationYaw - worm.prevRotationYaw) * 0.4F) + ((0 - worm.part3Cur) * 0.02F);
    	this.Part3.rotateAngleY = (worm.part3Cur * 0.0174F) + 1.57F;
    	this.Part3.rotationPointX = (63 * MathHelper.cos(this.Part3.rotateAngleY)) + this.Part2.rotationPointX;
    	this.Part3.rotationPointY = this.Part2.rotationPointY + 3;
    	this.Part3.rotationPointZ = (63 * MathHelper.sin(this.Part3.rotateAngleY)) + this.Part2.rotationPointZ;    	
    	this.Part3.rotateAngleY = (worm.part3Cur * -0.0174F);    	
    	
    	worm.part4Cur += ((worm.rotationYaw - worm.prevRotationYaw) * 0.5F) + ((0 - worm.part4Cur) * 0.02F);
    	this.Part4.rotateAngleY = (worm.part4Cur * 0.0174F) + 1.57F;
    	this.Part4.rotationPointX = (57 * MathHelper.cos(this.Part4.rotateAngleY)) + this.Part3.rotationPointX;
    	this.Part4.rotationPointY = this.Part3.rotationPointY + 3;
    	this.Part4.rotationPointZ = (57 * MathHelper.sin(this.Part4.rotateAngleY)) + this.Part3.rotationPointZ;    	
    	this.Part4.rotateAngleY = (worm.part4Cur * -0.0174F);    
    	
    	worm.part5Cur += ((worm.rotationYaw - worm.prevRotationYaw) * 0.6F) + ((0 - worm.part5Cur) * 0.02F);
    	this.Part5.rotateAngleY = (worm.part5Cur * 0.0174F) + 1.57F;
    	this.Part5.rotationPointX = (51 * MathHelper.cos(this.Part5.rotateAngleY)) + this.Part4.rotationPointX;
    	this.Part5.rotationPointY = this.Part4.rotationPointY + 3;
    	this.Part5.rotationPointZ = (51 * MathHelper.sin(this.Part5.rotateAngleY)) + this.Part4.rotationPointZ;    	
    	this.Part5.rotateAngleY = (worm.part5Cur * -0.0174F);  
		*/
    	
    }
    
    
 
    
    
    
    public void PseudoChild (ModelRenderer Parent, float ParentLength, ModelRenderer Child){
    	
    	Child.rotationPointY = Parent.rotationPointY;
    	Child.rotationPointZ = (ParentLength * (MathHelper.sin(-Parent.rotateAngleY))) + (ParentLength * (MathHelper.cos(-Parent.rotateAngleY))) + Parent.rotationPointZ;
    	Child.rotationPointX = (ParentLength * (MathHelper.cos(-Parent.rotateAngleY))) - (ParentLength * (MathHelper.sin(-Parent.rotateAngleY))) + Parent.rotationPointX;

    }
    
    public void FakePseudoChild (ModelRenderer Parent, float ParentLength, FakeModelRenderer Child){
    	float yOff = (float)Math.toRadians(45);
    	
    	Child.rotationPointY = Parent.rotationPointY;
    	Child.rotationPointZ = (ParentLength * (MathHelper.sin(-Parent.rotateAngleY + yOff))) + (ParentLength * (MathHelper.cos(-Parent.rotateAngleY + yOff))) + Parent.rotationPointZ;
    	Child.rotationPointX = (ParentLength * (MathHelper.cos(-Parent.rotateAngleY + yOff))) - (ParentLength * (MathHelper.sin(-Parent.rotateAngleY + yOff))) + Parent.rotationPointX;
    	Child.rotateAngleY = Parent.rotateAngleY;

    }
}
