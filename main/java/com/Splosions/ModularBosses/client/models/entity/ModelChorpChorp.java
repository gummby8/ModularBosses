
package com.Splosions.ModularBosses.client.models.entity;

import com.Splosions.ModularBosses.entity.EntityChorpChorp;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ModelChorpChorp extends ModelBase
{



	//fields
    ModelRenderer LegLF1;
    ModelRenderer LegLF1T;
    ModelRenderer LegLF2;
    ModelRenderer LegLF2T;
    ModelRenderer LegLF3;
    ModelRenderer LegLF3T;
    ModelRenderer LegLF4;
    ModelRenderer LegLF4T;
    ModelRenderer LegLFClaw;
    ModelRenderer LegLM1;
    ModelRenderer LegLM1T;
    ModelRenderer LegLM2;
    ModelRenderer LegLM2T;
    ModelRenderer LegLM3;
    ModelRenderer LegLM3T;
    ModelRenderer LegLM4;
    ModelRenderer LegLM4T;
    ModelRenderer LegLMClaw;
    ModelRenderer LegLB1;
    ModelRenderer LegLB1T;
    ModelRenderer LegLB2;
    ModelRenderer LegLB2T;
    ModelRenderer LegLB3;
    ModelRenderer LegLB3T;
    ModelRenderer LegLB4;
    ModelRenderer LegLB4T;
    ModelRenderer LegLBClaw;
    ModelRenderer LegRB1;
    ModelRenderer LegRB1T;
    ModelRenderer LegRB2;
    ModelRenderer LegRB2T;
    ModelRenderer LegRB3;
    ModelRenderer LegRB3T;
    ModelRenderer LegRB4;
    ModelRenderer LegRB4T;
    ModelRenderer LegRBClaw;
    ModelRenderer LegRM1;
    ModelRenderer LegRM1T;
    ModelRenderer LegRM2;
    ModelRenderer LegRM2T;
    ModelRenderer LegRM3;
    ModelRenderer LegRM3T;
    ModelRenderer LegRM4;
    ModelRenderer LegRM4T;
    ModelRenderer LegRMClaw;
    ModelRenderer LegRF1;
    ModelRenderer LegRF1T;
    ModelRenderer LegRF2;
    ModelRenderer LegRF2T;
    ModelRenderer LegRF3;
    ModelRenderer LegRF3T;
    ModelRenderer LegRF4;
    ModelRenderer LegRF4T;
    ModelRenderer LegRFClaw;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer Body3Back;
    ModelRenderer Body4;
    ModelRenderer BellyTop1;
    ModelRenderer BellyTop2;
    ModelRenderer BellyTop3;
    ModelRenderer BellyMidTop1;
    ModelRenderer BellyMidTop2;
    ModelRenderer BellyMidTop3;
    ModelRenderer BellyMidBot1;
    ModelRenderer BellyMidBot2;
    ModelRenderer BellyMidBot3;
    ModelRenderer BellyBot1;
    ModelRenderer BellyBot2;
    ModelRenderer BellyBot3;
    ModelRenderer BellyBot4;
    ModelRenderer TailBottom;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Tail4;
    ModelRenderer HeadRear1;
    ModelRenderer HeadRear2;
    ModelRenderer HeadRear3;
    ModelRenderer HeadRear4;
    ModelRenderer HeadMidRear1;
    ModelRenderer HeadMidRear2;
    ModelRenderer HeadMidRear3;
    ModelRenderer HeadMidRear4;
    ModelRenderer HeadMidFront1;
    ModelRenderer HeadMidFront2;
    ModelRenderer HeadMidFront3;
    ModelRenderer HeadMidFront4;
    ModelRenderer HeadFront1;
    ModelRenderer HeadFront2;
    ModelRenderer HeadFront3;
    ModelRenderer HeadFront4;
    ModelRenderer Face;
    ModelRenderer LeftAnt1;
    ModelRenderer LeftAnt2;
    ModelRenderer LeftAnt3;
    ModelRenderer LeftAnt4;
    ModelRenderer RightAnt1;
    ModelRenderer RightAnt2;
    ModelRenderer RightAnt3;
    ModelRenderer RightAnt4;
    ModelRenderer LeftBotArm1;
    ModelRenderer LeftBotArm2;
    ModelRenderer LeftBotArm3;
    ModelRenderer LeftMidArm1;
    ModelRenderer LeftMidArm2;
    ModelRenderer LeftMidArm3;
    ModelRenderer LeftTopArm1;
    ModelRenderer LeftTopArm2;
    ModelRenderer LeftTopArm3;
    ModelRenderer RightBotArm1;
    ModelRenderer RightBotArm2;
    ModelRenderer RightBotArm3;
    ModelRenderer RightMidArm1;
    ModelRenderer RightMidArm2;
    ModelRenderer RightMidArm3;
    ModelRenderer RightTopArm1;
    ModelRenderer RightTopArm2;
    ModelRenderer RightTopArm3;
    
	private float DeadRot = 0;
  
  public ModelChorpChorp()
  {
    textureWidth = 512;
    textureHeight = 256;
    
      LegLF1 = new ModelRenderer(this, 121, 0);
      LegLF1.addBox(-6F, -6F, -6F, 9, 12, 12);
      LegLF1.setRotationPoint(12F, 18F, -3F);
      LegLF1.setTextureSize(512, 256);
      LegLF1.mirror = true;
      setRotation(LegLF1, 0.1308997F, 0.6108652F, 0.2617994F);
      LegLF1T = new ModelRenderer(this, 124, 25);
      LegLF1T.addBox(-7F, -7F, -4F, 10, 1, 8);
      LegLF1T.setRotationPoint(12F, 18F, -3F);
      LegLF1T.setTextureSize(512, 256);
      LegLF1T.mirror = true;
      setRotation(LegLF1T, 0.1308997F, 0.6108652F, 0.2617994F);
      LegLF2 = new ModelRenderer(this, 131, 35);
      LegLF2.addBox(3F, -4F, -5F, 1, 9, 10);
      LegLF2.setRotationPoint(12F, 18F, -3F);
      LegLF2.setTextureSize(512, 256);
      LegLF2.mirror = true;
      setRotation(LegLF2, 0.1308997F, 0.6108652F, 0.2617994F);
      LegLF2T = new ModelRenderer(this, 135, 56);
      LegLF2T.addBox(3F, -5F, -3F, 1, 1, 6);
      LegLF2T.setRotationPoint(12F, 18F, -3F);
      LegLF2T.setTextureSize(512, 256);
      LegLF2T.mirror = true;
      setRotation(LegLF2T, 0.1308997F, 0.6108652F, 0.2617994F);
      LegLF3 = new ModelRenderer(this, 129, 64);
      LegLF3.addBox(1F, -3F, -4F, 5, 8, 8);
      LegLF3.setRotationPoint(12F, 18F, -3F);
      LegLF3.setTextureSize(512, 256);
      LegLF3.mirror = true;
      setRotation(LegLF3, 0.1308997F, 0.6108652F, 0.2617994F);
      LegLF3T = new ModelRenderer(this, 132, 81);
      LegLF3T.addBox(1F, -4F, -2F, 5, 1, 4);
      LegLF3T.setRotationPoint(12F, 18F, -3F);
      LegLF3T.setTextureSize(512, 256);
      LegLF3T.mirror = true;
      setRotation(LegLF3T, 0.1308997F, 0.6108652F, 0.2617994F);
      LegLF4 = new ModelRenderer(this, 129, 87);
      LegLF4.addBox(-1F, -2F, -2.5F, 9, 5, 5);
      LegLF4.setRotationPoint(15F, 21F, -5F);
      LegLF4.setTextureSize(512, 256);
      LegLF4.mirror = true;
      setRotation(LegLF4, 0F, 0.6108652F, 0F);
      LegLF4T = new ModelRenderer(this, 134, 98);
      LegLF4T.addBox(0F, -3F, -1F, 7, 1, 2);
      LegLF4T.setRotationPoint(15F, 21F, -5F);
      LegLF4T.setTextureSize(512, 256);
      LegLF4T.mirror = true;
      setRotation(LegLF4T, 0F, 0.6108652F, 0F);
      LegLFClaw = new ModelRenderer(this, 136, 102);
      LegLFClaw.addBox(0F, -0.5F, -1F, 4, 1, 2);
      LegLFClaw.setRotationPoint(22F, 21F, -5F);
      LegLFClaw.setTextureSize(512, 256);
      LegLFClaw.mirror = true;
      setRotation(LegLFClaw, 0.0F, 0.6108652F, 0.7853982F);
      LegLM1 = new ModelRenderer(this, 121, 0);
      LegLM1.addBox(-6F, -6F, -6F, 9, 12, 12);
      LegLM1.setRotationPoint(12F, 18F, 12F);
      LegLM1.setTextureSize(512, 256);
      LegLM1.mirror = true;
      setRotation(LegLM1, 0F, 0F, 0.2617994F);
      LegLM1T = new ModelRenderer(this, 124, 25);
      LegLM1T.addBox(-8F, -7F, -4F, 10, 1, 8);
      LegLM1T.setRotationPoint(12F, 18F, 12F);
      LegLM1T.setTextureSize(512, 256);
      LegLM1T.mirror = true;
      setRotation(LegLM1T, 0F, 0F, 0.2617994F);
      LegLM2 = new ModelRenderer(this, 131, 35);
      LegLM2.addBox(3F, -4F, -5F, 1, 10, 10);
      LegLM2.setRotationPoint(12F, 18F, 12F);
      LegLM2.setTextureSize(512, 256);
      LegLM2.mirror = true;
      setRotation(LegLM2, 0F, 0F, 0.2617994F);
      LegLM2T = new ModelRenderer(this, 135, 56);
      LegLM2T.addBox(3F, -5F, -3F, 1, 1, 6);
      LegLM2T.setRotationPoint(12F, 18F, 12F);
      LegLM2T.setTextureSize(512, 256);
      LegLM2T.mirror = true;
      setRotation(LegLM2T, 0F, 0F, 0.2617994F);
      LegLM3 = new ModelRenderer(this, 129, 64);
      LegLM3.addBox(1F, -3F, -4F, 5, 8, 8);
      LegLM3.setRotationPoint(12F, 18F, 12F);
      LegLM3.setTextureSize(512, 256);
      LegLM3.mirror = true;
      setRotation(LegLM3, 0F, 0F, 0.2617994F);
      LegLM3T = new ModelRenderer(this, 132, 81);
      LegLM3T.addBox(1F, -4F, -2F, 5, 1, 4);
      LegLM3T.setRotationPoint(12F, 18F, 12F);
      LegLM3T.setTextureSize(512, 256);
      LegLM3T.mirror = true;
      setRotation(LegLM3T, 0F, 0F, 0.2617994F);
      LegLM4 = new ModelRenderer(this, 129, 87);
      LegLM4.addBox(-1F, -2F, -2.5F, 9, 5, 5);
      LegLM4.setRotationPoint(16F, 21F, 12F);
      LegLM4.setTextureSize(512, 256);
      LegLM4.mirror = true;
      setRotation(LegLM4, 0F, 0F, 0F);
      LegLM4T = new ModelRenderer(this, 134, 98);
      LegLM4T.addBox(0F, -3F, -1F, 7, 1, 2);
      LegLM4T.setRotationPoint(16F, 21F, 12F);
      LegLM4T.setTextureSize(512, 256);
      LegLM4T.mirror = true;
      setRotation(LegLM4T, 0F, 0F, 0F);
      LegLMClaw = new ModelRenderer(this, 136, 102);
      LegLMClaw.addBox(0F, -0.5F, -1F, 4, 1, 2);
      LegLMClaw.setRotationPoint(23.5F, 21F, 12F);
      LegLMClaw.setTextureSize(512, 256);
      LegLMClaw.mirror = true;
      setRotation(LegLMClaw, 0F, 0F, 0.7853982F);
      LegLB1 = new ModelRenderer(this, 271, 67);
      LegLB1.addBox(-11F, -6F, -6F, 14, 12, 12);
      LegLB1.setRotationPoint(12F, 18F, 27F);
      LegLB1.setTextureSize(512, 256);
      LegLB1.mirror = true;
      setRotation(LegLB1, -0.1308997F, -0.6108652F, 0.2617994F);
      LegLB1T = new ModelRenderer(this, 274, 92);
      LegLB1T.addBox(-13F, -7F, -4F, 15, 1, 8);
      LegLB1T.setRotationPoint(12F, 18F, 27F);
      LegLB1T.setTextureSize(512, 256);
      LegLB1T.mirror = true;
      setRotation(LegLB1T, -0.1308997F, -0.6108652F, 0.2617994F);
      LegLB2 = new ModelRenderer(this, 131, 35);
      LegLB2.addBox(3F, -4F, -5F, 1, 10, 10);
      LegLB2.setRotationPoint(12F, 18F, 27F);
      LegLB2.setTextureSize(512, 256);
      LegLB2.mirror = true;
      setRotation(LegLB2, -0.1308997F, -0.6108652F, 0.2617994F);
      LegLB2T = new ModelRenderer(this, 135, 56);
      LegLB2T.addBox(3F, -5F, -3F, 1, 1, 6);
      LegLB2T.setRotationPoint(12F, 18F, 27F);
      LegLB2T.setTextureSize(512, 256);
      LegLB2T.mirror = true;
      setRotation(LegLB2T, -0.1308997F, -0.6108652F, 0.2617994F);
      LegLB3 = new ModelRenderer(this, 129, 64);
      LegLB3.addBox(1F, -3F, -4F, 5, 8, 8);
      LegLB3.setRotationPoint(12F, 18F, 27F);
      LegLB3.setTextureSize(512, 256);
      LegLB3.mirror = true;
      setRotation(LegLB3, -0.1308997F, -0.6108652F, 0.2617994F);
      LegLB3T = new ModelRenderer(this, 132, 81);
      LegLB3T.addBox(1F, -4F, -2F, 5, 1, 4);
      LegLB3T.setRotationPoint(12F, 18F, 27F);
      LegLB3T.setTextureSize(512, 256);
      LegLB3T.mirror = true;
      setRotation(LegLB3T, -0.1308997F, -0.6108652F, 0.2617994F);
      LegLB4 = new ModelRenderer(this, 129, 87);
      LegLB4.addBox(-1F, -2F, -2.5F, 9, 5, 5);
      LegLB4.setRotationPoint(15F, 21F, 29F);
      LegLB4.setTextureSize(512, 256);
      LegLB4.mirror = true;
      setRotation(LegLB4, 0F, -0.6108652F, 0F);
      LegLB4T = new ModelRenderer(this, 134, 98);
      LegLB4T.addBox(0F, -3F, -1F, 7, 1, 2);
      LegLB4T.setRotationPoint(15F, 21F, 29F);
      LegLB4T.setTextureSize(512, 256);
      LegLB4T.mirror = true;
      setRotation(LegLB4T, 0F, -0.6108652F, 0F);
      LegLBClaw = new ModelRenderer(this, 136, 102);
      LegLBClaw.addBox(0F, -0.5F, -1F, 4, 1, 2);
      LegLBClaw.setRotationPoint(22F, 21F, 29F);
      LegLBClaw.setTextureSize(512, 256);
      LegLBClaw.mirror = true;
      setRotation(LegLBClaw, 0.0F, -0.6108652F, 0.7853982F);
      LegRB1 = new ModelRenderer(this, 326, 67);
      LegRB1.addBox(-3F, -6F, -6F, 14, 12, 12);
      LegRB1.setRotationPoint(-12F, 18F, 27F);
      LegRB1.setTextureSize(512, 256);
      LegRB1.mirror = true;
      setRotation(LegRB1, -0.1308997F, 0.6108652F, -0.2617994F);
      LegRB1T = new ModelRenderer(this, 328, 92);
      LegRB1T.addBox(-2F, -7F, -4F, 15, 1, 8);
      LegRB1T.setRotationPoint(-12F, 18F, 27F);
      LegRB1T.setTextureSize(512, 256);
      LegRB1T.mirror = true;
      setRotation(LegRB1T, -0.1308997F, 0.6108652F, -0.2617994F);
      LegRB2 = new ModelRenderer(this, 173, 35);
      LegRB2.addBox(-4F, -4F, -5F, 1, 10, 10);
      LegRB2.setRotationPoint(-12F, 18F, 27F);
      LegRB2.setTextureSize(512, 256);
      LegRB2.mirror = true;
      setRotation(LegRB2, -0.1308997F, 0.6108652F, -0.2617994F);
      LegRB2T = new ModelRenderer(this, 176, 56);
      LegRB2T.addBox(-4F, -5F, -3F, 1, 1, 6);
      LegRB2T.setRotationPoint(-12F, 18F, 27F);
      LegRB2T.setTextureSize(512, 256);
      LegRB2T.mirror = true;
      setRotation(LegRB2T, -0.1308997F, 0.6108652F, -0.2617994F);
      LegRB3 = new ModelRenderer(this, 169, 64);
      LegRB3.addBox(-6F, -3F, -4F, 5, 8, 8);
      LegRB3.setRotationPoint(-12F, 18F, 27F);
      LegRB3.setTextureSize(512, 256);
      LegRB3.mirror = true;
      setRotation(LegRB3, -0.1308997F, 0.6108652F, -0.2617994F);
      LegRB3T = new ModelRenderer(this, 173, 81);
      LegRB3T.addBox(-6F, -4F, -2F, 5, 1, 4);
      LegRB3T.setRotationPoint(-12F, 18F, 27F);
      LegRB3T.setTextureSize(512, 256);
      LegRB3T.mirror = true;
      setRotation(LegRB3T, -0.1308997F, 0.6108652F, -0.2617994F);
      LegRB4 = new ModelRenderer(this, 169, 87);
      LegRB4.addBox(-8F, -2F, -2.5F, 9, 5, 5);
      LegRB4.setRotationPoint(-15F, 21F, 29F);
      LegRB4.setTextureSize(512, 256);
      LegRB4.mirror = true;
      setRotation(LegRB4, 0F, 0.6108652F, 0F);
      LegRB4T = new ModelRenderer(this, 173, 98);
      LegRB4T.addBox(-7F, -3F, -1F, 7, 1, 2);
      LegRB4T.setRotationPoint(-15F, 21F, 29F);
      LegRB4T.setTextureSize(512, 256);
      LegRB4T.mirror = true;
      setRotation(LegRB4T, 0F, 0.6108652F, 0F);
      LegRBClaw = new ModelRenderer(this, 176, 102);
      LegRBClaw.addBox(-4F, -0.5F, -1F, 4, 1, 2);
      LegRBClaw.setRotationPoint(-22F, 21F, 29F);
      LegRBClaw.setTextureSize(512, 256);
      LegRBClaw.mirror = true;
      setRotation(LegRBClaw, 0.0F, 0.6108652F, -0.7853982F);
      LegRM1 = new ModelRenderer(this, 164, 0);
      LegRM1.addBox(-3F, -6F, -6F, 9, 12, 12);
      LegRM1.setRotationPoint(-12F, 18F, 12F);
      LegRM1.setTextureSize(512, 256);
      LegRM1.mirror = true;
      setRotation(LegRM1, 0F, 0F, -0.2617994F);
      LegRM1T = new ModelRenderer(this, 166, 25);
      LegRM1T.addBox(-3F, -7F, -4F, 10, 1, 8);
      LegRM1T.setRotationPoint(-12F, 18F, 12F);
      LegRM1T.setTextureSize(512, 256);
      LegRM1T.mirror = true;
      setRotation(LegRM1T, 0F, 0F, -0.2617994F);
      LegRM2 = new ModelRenderer(this, 173, 35);
      LegRM2.addBox(-4F, -4F, -5F, 1, 10, 10);
      LegRM2.setRotationPoint(-12F, 18F, 12F);
      LegRM2.setTextureSize(512, 256);
      LegRM2.mirror = true;
      setRotation(LegRM2, 0F, 0F, -0.2617994F);
      LegRM2T = new ModelRenderer(this, 176, 56);
      LegRM2T.addBox(-4F, -5F, -3F, 1, 1, 6);
      LegRM2T.setRotationPoint(-12F, 18F, 12F);
      LegRM2T.setTextureSize(512, 256);
      LegRM2T.mirror = true;
      setRotation(LegRM2T, 0F, 0F, -0.2617994F);
      LegRM3 = new ModelRenderer(this, 169, 64);
      LegRM3.addBox(-6F, -3F, -4F, 5, 8, 8);
      LegRM3.setRotationPoint(-12F, 18F, 12F);
      LegRM3.setTextureSize(512, 256);
      LegRM3.mirror = true;
      setRotation(LegRM3, 0F, 0F, -0.2617994F);
      LegRM3T = new ModelRenderer(this, 173, 81);
      LegRM3T.addBox(-6F, -4F, -2F, 5, 1, 4);
      LegRM3T.setRotationPoint(-12F, 18F, 12F);
      LegRM3T.setTextureSize(512, 256);
      LegRM3T.mirror = true;
      setRotation(LegRM3T, 0F, 0F, -0.2617994F);
      LegRM4 = new ModelRenderer(this, 169, 87);
      LegRM4.addBox(-8F, -2F, -2.5F, 9, 5, 5);
      LegRM4.setRotationPoint(-16F, 21F, 12F);
      LegRM4.setTextureSize(512, 256);
      LegRM4.mirror = true;
      setRotation(LegRM4, 0F, 0F, 0F);
      LegRM4T = new ModelRenderer(this, 173, 98);
      LegRM4T.addBox(-7F, -3F, -1F, 8, 1, 2);
      LegRM4T.setRotationPoint(-16F, 21F, 12F);
      LegRM4T.setTextureSize(512, 256);
      LegRM4T.mirror = true;
      setRotation(LegRM4T, 0F, 0F, 0F);
      LegRMClaw = new ModelRenderer(this, 176, 102);
      LegRMClaw.addBox(-4F, -0.5F, -1F, 4, 1, 2);
      LegRMClaw.setRotationPoint(-23.5F, 21F, 12F);
      LegRMClaw.setTextureSize(512, 256);
      LegRMClaw.mirror = true;
      setRotation(LegRMClaw, 0F, 0F, -0.7853982F);
      LegRF1 = new ModelRenderer(this, 164, 0);
      LegRF1.addBox(-3F, -6F, -6F, 9, 12, 12);
      LegRF1.setRotationPoint(-12F, 18F, -3F);
      LegRF1.setTextureSize(512, 256);
      LegRF1.mirror = true;
      setRotation(LegRF1, 0.1308997F, -0.6108652F, -0.2617994F);
      LegRF1T = new ModelRenderer(this, 166, 25);
      LegRF1T.addBox(-3F, -7F, -4F, 10, 1, 8);
      LegRF1T.setRotationPoint(-12F, 18F, -3F);
      LegRF1T.setTextureSize(512, 256);
      LegRF1T.mirror = true;
      setRotation(LegRF1T, 0.1308997F, -0.6108652F, -0.2617994F);
      LegRF2 = new ModelRenderer(this, 173, 35);
      LegRF2.addBox(-4F, -4F, -5F, 1, 10, 10);
      LegRF2.setRotationPoint(-12F, 18F, -3F);
      LegRF2.setTextureSize(512, 256);
      LegRF2.mirror = true;
      setRotation(LegRF2, 0.1308997F, -0.6108652F, -0.2617994F);
      LegRF2T = new ModelRenderer(this, 176, 56);
      LegRF2T.addBox(-4F, -5F, -3F, 1, 1, 6);
      LegRF2T.setRotationPoint(-12F, 18F, -3F);
      LegRF2T.setTextureSize(512, 256);
      LegRF2T.mirror = true;
      setRotation(LegRF2T, 0.1308997F, -0.6108652F, -0.2617994F);
      LegRF3 = new ModelRenderer(this, 169, 64);
      LegRF3.addBox(-6F, -3F, -4F, 5, 8, 8);
      LegRF3.setRotationPoint(-12F, 18F, -3F);
      LegRF3.setTextureSize(512, 256);
      LegRF3.mirror = true;
      setRotation(LegRF3, 0.1308997F, -0.6108652F, -0.2617994F);
      LegRF3T = new ModelRenderer(this, 173, 81);
      LegRF3T.addBox(-6F, -4F, -2F, 5, 1, 4);
      LegRF3T.setRotationPoint(-12F, 18F, -3F);
      LegRF3T.setTextureSize(512, 256);
      LegRF3T.mirror = true;
      setRotation(LegRF3T, 0.1308997F, -0.6108652F, -0.2617994F);
      LegRF4 = new ModelRenderer(this, 169, 87);
      LegRF4.addBox(-8F, -2F, -2.5F, 9, 5, 5);
      LegRF4.setRotationPoint(-15F, 21F, -5F);
      LegRF4.setTextureSize(512, 256);
      LegRF4.mirror = true;
      setRotation(LegRF4, 0F, -0.6108652F, 0F);
      LegRF4T = new ModelRenderer(this, 173, 98);
      LegRF4T.addBox(-7F, -3F, -1F, 7, 1, 2);
      LegRF4T.setRotationPoint(-15F, 21F, -5F);
      LegRF4T.setTextureSize(512, 256);
      LegRF4T.mirror = true;
      setRotation(LegRF4T, 0F, -0.6108652F, 0F);
      LegRFClaw = new ModelRenderer(this, 176, 102);
      LegRFClaw.addBox(-4F, -0.5F, -1F, 4, 1, 2);
      LegRFClaw.setRotationPoint(-22F, 21F, -5F);
      LegRFClaw.setTextureSize(512, 256);
      LegRFClaw.mirror = true;
      setRotation(LegRFClaw, 0.0F, -0.6108652F, -0.7853982F);
      Body1 = new ModelRenderer(this, 0, 0);
      Body1.addBox(-12F, 0F, -8F, 24, 12, 35);
      Body1.setRotationPoint(0F, 12F, 0F);
      Body1.setTextureSize(512, 256);
      Body1.mirror = true;
      setRotation(Body1, 0F, 0F, 0F);
      Body2 = new ModelRenderer(this, 0, 48);
      Body2.addBox(-10F, 0F, -1F, 20, 10, 29);
      Body2.setRotationPoint(0F, 3F, -9F);
      Body2.setTextureSize(512, 256);
      Body2.mirror = true;
      setRotation(Body2, 0F, 0F, 0F);
      Body3 = new ModelRenderer(this, 0, 88);
      Body3.addBox(-9F, -12F, 0F, 18, 17, 22);
      Body3.setRotationPoint(0F, 3F, -10F);
      Body3.setTextureSize(512, 256);
      Body3.mirror = true;
      setRotation(Body3, 0F, 0F, 0F);
      Body3Back = new ModelRenderer(this, 81, 88);
      Body3Back.addBox(-7F, -6F, 22F, 14, 11, 4);
      Body3Back.setRotationPoint(0F, 3F, -10F);
      Body3Back.setTextureSize(512, 256);
      Body3Back.mirror = true;
      setRotation(Body3Back, 0F, 0F, 0F);
      Body4 = new ModelRenderer(this, 0, 126);
      Body4.addBox(-7F, -12F, 0F, 14, 14, 19);
      Body4.setRotationPoint(0F, -6F, -10F);
      Body4.setTextureSize(512, 256);
      Body4.mirror = true;
      setRotation(Body4, 0F, 0F, 0F);
      BellyTop1 = new ModelRenderer(this, 0, 162);
      BellyTop1.addBox(-6F, -9F, -2F, 12, 9, 2);
      BellyTop1.setRotationPoint(0F, -6F, -10F);
      BellyTop1.setTextureSize(512, 256);
      BellyTop1.mirror = true;
      setRotation(BellyTop1, 0F, 0F, 0F);
      BellyTop2 = new ModelRenderer(this, 31, 162);
      BellyTop2.addBox(-4F, -7F, -4F, 8, 7, 2);
      BellyTop2.setRotationPoint(0F, -6F, -10F);
      BellyTop2.setTextureSize(512, 256);
      BellyTop2.mirror = true;
      setRotation(BellyTop2, 0F, 0F, 0F);
      BellyTop3 = new ModelRenderer(this, 52, 162);
      BellyTop3.addBox(-3F, -4F, -6F, 6, 4, 2);
      BellyTop3.setRotationPoint(0F, -6F, -10F);
      BellyTop3.setTextureSize(512, 256);
      BellyTop3.mirror = true;
      setRotation(BellyTop3, 0F, 0F, 0F);
      BellyMidTop1 = new ModelRenderer(this, 0, 175);
      BellyMidTop1.addBox(-7F, -9F, -3F, 14, 12, 3);
      BellyMidTop1.setRotationPoint(0F, 3F, -10F);
      BellyMidTop1.setTextureSize(512, 256);
      BellyMidTop1.mirror = true;
      setRotation(BellyMidTop1, 0F, 0F, 0F);
      BellyMidTop2 = new ModelRenderer(this, 35, 175);
      BellyMidTop2.addBox(-6F, -9F, -5F, 12, 12, 2);
      BellyMidTop2.setRotationPoint(0F, 3F, -10F);
      BellyMidTop2.setTextureSize(512, 256);
      BellyMidTop2.mirror = true;
      setRotation(BellyMidTop2, 0F, 0F, 0F);
      BellyMidTop3 = new ModelRenderer(this, 64, 175);
      BellyMidTop3.addBox(-4F, -9F, -7F, 8, 12, 2);
      BellyMidTop3.setRotationPoint(0F, 3F, -10F);
      BellyMidTop3.setTextureSize(512, 256);
      BellyMidTop3.mirror = true;
      setRotation(BellyMidTop3, 0F, 0F, 0F);
      BellyMidBot1 = new ModelRenderer(this, 0, 196);
      BellyMidBot1.addBox(-8F, -8F, 0F, 16, 10, 4);
      BellyMidBot1.setRotationPoint(0F, 11F, -14F);
      BellyMidBot1.setTextureSize(512, 256);
      BellyMidBot1.mirror = true;
      setRotation(BellyMidBot1, 0F, 0F, 0F);
      BellyMidBot2 = new ModelRenderer(this, 41, 196);
      BellyMidBot2.addBox(-7F, -3F, 0F, 14, 11, 2);
      BellyMidBot2.setRotationPoint(0F, 6F, -16F);
      BellyMidBot2.setTextureSize(512, 256);
      BellyMidBot2.mirror = true;
      setRotation(BellyMidBot2, 0F, 0F, 0F);
      BellyMidBot3 = new ModelRenderer(this, 74, 196);
      BellyMidBot3.addBox(-5F, -5F, 0F, 10, 12, 2);
      BellyMidBot3.setRotationPoint(0F, 8F, -18F);
      BellyMidBot3.setTextureSize(512, 256);
      BellyMidBot3.mirror = true;
      setRotation(BellyMidBot3, 0F, 0F, 0F);
      BellyBot1 = new ModelRenderer(this, 0, 211);
      BellyBot1.addBox(-7F, -4F, 0F, 16, 10, 1);
      BellyBot1.setRotationPoint(-1F, 17F, -9F);
      BellyBot1.setTextureSize(512, 256);
      BellyBot1.mirror = true;
      setRotation(BellyBot1, 0F, 0F, 0F);
      BellyBot2 = new ModelRenderer(this, 35, 211);
      BellyBot2.addBox(-7F, -7F, 0F, 14, 8, 3);
      BellyBot2.setRotationPoint(0F, 20F, -12F);
      BellyBot2.setTextureSize(512, 256);
      BellyBot2.mirror = true;
      setRotation(BellyBot2, 0F, 0F, 0F);
      BellyBot3 = new ModelRenderer(this, 70, 211);
      BellyBot3.addBox(-7F, -7F, 0F, 12, 6, 2);
      BellyBot3.setRotationPoint(1F, 20F, -14F);
      BellyBot3.setTextureSize(512, 256);
      BellyBot3.mirror = true;
      setRotation(BellyBot3, 0F, 0F, 0F);
      BellyBot4 = new ModelRenderer(this, 70, 220);
      BellyBot4.addBox(-5F, -3F, 0F, 10, 3, 2);
      BellyBot4.setRotationPoint(0F, 17F, -16F);
      BellyBot4.setTextureSize(512, 256);
      BellyBot4.mirror = true;
      setRotation(BellyBot4, 0F, 0F, 0F);
      TailBottom = new ModelRenderer(this, 115, 110);
      TailBottom.addBox(-8F, 0F, 0F, 16, 10, 2);
      TailBottom.setRotationPoint(0F, 12F, 27F);
      TailBottom.setTextureSize(512, 256);
      TailBottom.mirror = true;
      setRotation(TailBottom, 0F, 0F, 0F);
      Tail1 = new ModelRenderer(this, 115, 123);
      Tail1.addBox(-7F, -10F, 0F, 14, 10, 16);
      Tail1.setRotationPoint(0F, 18F, 16F);
      Tail1.setTextureSize(512, 256);
      Tail1.mirror = true;
      setRotation(Tail1, 0F, 0F, 0F);
      Tail2 = new ModelRenderer(this, 115, 150);
      Tail2.addBox(-6F, -10F, 0F, 12, 10, 19);
      Tail2.setRotationPoint(0F, 15F, 16F);
      Tail2.setTextureSize(512, 256);
      Tail2.mirror = true;
      setRotation(Tail2, 0F, 0F, 0F);
      Tail3 = new ModelRenderer(this, 115, 180);
      Tail3.addBox(-5F, -10F, 0F, 10, 10, 8);
      Tail3.setRotationPoint(0F, 12F, 29F);
      Tail3.setTextureSize(512, 256);
      Tail3.mirror = true;
      setRotation(Tail3, 0F, 0F, 0F);
      Tail4 = new ModelRenderer(this, 152, 180);
      Tail4.addBox(-4F, -4F, -4F, 8, 10, 6);
      Tail4.setRotationPoint(0F, 2F, 37F);
      Tail4.setTextureSize(512, 256);
      Tail4.mirror = true;
      setRotation(Tail4, 0F, 0F, 0F);
      HeadRear1 = new ModelRenderer(this, 275, 0);
      HeadRear1.addBox(-8F, -5F, 10F, 16, 5, 10);
      HeadRear1.setRotationPoint(0F, -15F, -10F);
      HeadRear1.setTextureSize(512, 256);
      HeadRear1.mirror = true;
      setRotation(HeadRear1, 0F, 0F, 0F);
      HeadRear2 = new ModelRenderer(this, 275, 16);
      HeadRear2.addBox(-7F, -8F, 10F, 14, 3, 8);
      HeadRear2.setRotationPoint(0F, -15F, -10F);
      HeadRear2.setTextureSize(512, 256);
      HeadRear2.mirror = true;
      setRotation(HeadRear2, 0F, 0F, 0F);
      HeadRear3 = new ModelRenderer(this, 275, 28);
      HeadRear3.addBox(-6F, -10F, 10F, 12, 2, 6);
      HeadRear3.setRotationPoint(0F, -15F, -10F);
      HeadRear3.setTextureSize(512, 256);
      HeadRear3.mirror = true;
      setRotation(HeadRear3, 0F, 0F, 0F);
      HeadRear4 = new ModelRenderer(this, 275, 37);
      HeadRear4.addBox(-5F, -12F, 11F, 10, 2, 3);
      HeadRear4.setRotationPoint(0F, -15F, -10F);
      HeadRear4.setTextureSize(512, 256);
      HeadRear4.mirror = true;
      setRotation(HeadRear4, 0F, 0F, 0F);
      HeadMidRear1 = new ModelRenderer(this, 328, 0);
      HeadMidRear1.addBox(-7F, -6F, 0F, 14, 6, 10);
      HeadMidRear1.setRotationPoint(0F, -15F, -10F);
      HeadMidRear1.setTextureSize(512, 256);
      HeadMidRear1.mirror = true;
      setRotation(HeadMidRear1, 0F, 0F, 0F);
      HeadMidRear2 = new ModelRenderer(this, 328, 17);
      HeadMidRear2.addBox(-8F, -10F, 0F, 16, 4, 10);
      HeadMidRear2.setRotationPoint(0F, -15F, -10F);
      HeadMidRear2.setTextureSize(512, 256);
      HeadMidRear2.mirror = true;
      setRotation(HeadMidRear2, 0F, 0F, 0F);
      HeadMidRear3 = new ModelRenderer(this, 328, 32);
      HeadMidRear3.addBox(-7F, -12F, 0F, 14, 2, 11);
      HeadMidRear3.setRotationPoint(0F, -15F, -10F);
      HeadMidRear3.setTextureSize(512, 256);
      HeadMidRear3.mirror = true;
      setRotation(HeadMidRear3, 0F, 0F, 0F);
      HeadMidRear4 = new ModelRenderer(this, 328, 46);
      HeadMidRear4.addBox(-5F, -14F, 0F, 10, 2, 12);
      HeadMidRear4.setRotationPoint(0F, -15F, -10F);
      HeadMidRear4.setTextureSize(512, 256);
      HeadMidRear4.mirror = true;
      setRotation(HeadMidRear4, 0F, 0F, 0F);
      HeadMidFront1 = new ModelRenderer(this, 381, 0);
      HeadMidFront1.addBox(-6F, -5F, -9F, 12, 4, 9);
      HeadMidFront1.setRotationPoint(0F, -15F, -10F);
      HeadMidFront1.setTextureSize(512, 256);
      HeadMidFront1.mirror = true;
      setRotation(HeadMidFront1, 0F, 0F, 0F);
      HeadMidFront2 = new ModelRenderer(this, 381, 14);
      HeadMidFront2.addBox(-7F, -9F, -9F, 14, 4, 9);
      HeadMidFront2.setRotationPoint(0F, -15F, -10F);
      HeadMidFront2.setTextureSize(512, 256);
      HeadMidFront2.mirror = true;
      setRotation(HeadMidFront2, 0F, 0F, 0F);
      HeadMidFront3 = new ModelRenderer(this, 381, 28);
      HeadMidFront3.addBox(-6F, -11F, -9F, 12, 2, 9);
      HeadMidFront3.setRotationPoint(0F, -15F, -10F);
      HeadMidFront3.setTextureSize(512, 256);
      HeadMidFront3.mirror = true;
      setRotation(HeadMidFront3, 0F, 0F, 0F);
      HeadMidFront4 = new ModelRenderer(this, 381, 40);
      HeadMidFront4.addBox(-4F, -13F, -9F, 8, 2, 9);
      HeadMidFront4.setRotationPoint(0F, -15F, -10F);
      HeadMidFront4.setTextureSize(512, 256);
      HeadMidFront4.mirror = true;
      setRotation(HeadMidFront4, 0F, 0F, 0F);
      HeadFront1 = new ModelRenderer(this, 428, 0);
      HeadFront1.addBox(-4F, -4F, -16F, 8, 2, 7);
      HeadFront1.setRotationPoint(0F, -15F, -10F);
      HeadFront1.setTextureSize(512, 256);
      HeadFront1.mirror = true;
      setRotation(HeadFront1, 0F, 0F, 0F);
      HeadFront2 = new ModelRenderer(this, 428, 10);
      HeadFront2.addBox(-6F, -8F, -18F, 12, 4, 9);
      HeadFront2.setRotationPoint(0F, -15F, -10F);
      HeadFront2.setTextureSize(512, 256);
      HeadFront2.mirror = true;
      setRotation(HeadFront2, 0F, 0F, 0F);
      HeadFront3 = new ModelRenderer(this, 428, 24);
      HeadFront3.addBox(-4F, -10F, -16F, 8, 2, 7);
      HeadFront3.setRotationPoint(0F, -15F, -10F);
      HeadFront3.setTextureSize(512, 256);
      HeadFront3.mirror = true;
      setRotation(HeadFront3, 0F, 0F, 0F);
      HeadFront4 = new ModelRenderer(this, 428, 34);
      HeadFront4.addBox(-3F, -12F, -13F, 6, 2, 4);
      HeadFront4.setRotationPoint(0F, -15F, -10F);
      HeadFront4.setTextureSize(512, 256);
      HeadFront4.mirror = true;
      setRotation(HeadFront4, 0F, 0F, 0F);
      Face = new ModelRenderer(this, 471, 0);
      Face.addBox(-2.5F, -7.5F, -20F, 5, 3, 2);
      Face.setRotationPoint(0F, -15F, -10F);
      Face.setTextureSize(512, 256);
      Face.mirror = true;
      setRotation(Face, 0F, 0F, 0F);
      LeftAnt1 = new ModelRenderer(this, 220, 50);
      LeftAnt1.addBox(-2F, -2F, -2F, 3, 3, 6);
      LeftAnt1.setRotationPoint(5F, -20F, -30F);
      LeftAnt1.setTextureSize(512, 256);
      LeftAnt1.mirror = true;
      setRotation(LeftAnt1, 0.1919862F, -0.6108652F, 0F);
      LeftAnt2 = new ModelRenderer(this, 222, 60);
      LeftAnt2.addBox(-2F, -1F, -8F, 2, 2, 6);
      LeftAnt2.setRotationPoint(5F, -20F, -30F);
      LeftAnt2.setTextureSize(512, 256);
      LeftAnt2.mirror = true;
      setRotation(LeftAnt2, 0.1919862F, -0.6108652F, 0F);
      LeftAnt3 = new ModelRenderer(this, 216, 69);
      LeftAnt3.addBox(-0.5F, -0.5F, -10F, 1, 1, 10);
      LeftAnt3.setRotationPoint(4F, -20F, -37F);
      LeftAnt3.setTextureSize(512, 256);
      LeftAnt3.mirror = true;
      setRotation(LeftAnt3, 0.1919862F, -0.1047198F, 0F);
      LeftAnt4 = new ModelRenderer(this, 228, 81);
      LeftAnt4.addBox(-0.5F, -0.5F, -12F, 3, 2, 2);
      LeftAnt4.setRotationPoint(4F, -20F, -37F);
      LeftAnt4.setTextureSize(512, 256);
      LeftAnt4.mirror = true;
      setRotation(LeftAnt4, 0.1919862F, -0.1047198F, 0F);
      RightAnt1 = new ModelRenderer(this, 245, 50);
      RightAnt1.addBox(-2F, -2F, -2F, 3, 3, 6);
      RightAnt1.setRotationPoint(-4F, -20F, -30F);
      RightAnt1.setTextureSize(512, 256);
      RightAnt1.mirror = true;
      setRotation(RightAnt1, 0.1919862F, 0.6108652F, 0F);
      RightAnt2 = new ModelRenderer(this, 245, 60);
      RightAnt2.addBox(-1F, -1F, -8F, 2, 2, 6);
      RightAnt2.setRotationPoint(-4F, -20F, -30F);
      RightAnt2.setTextureSize(512, 256);
      RightAnt2.mirror = true;
      setRotation(RightAnt2, 0.1919862F, 0.6108652F, 0F);
      RightAnt3 = new ModelRenderer(this, 245, 69);
      RightAnt3.addBox(-0.5F, -0.5F, -10F, 1, 1, 10);
      RightAnt3.setRotationPoint(-4F, -20F, -37F);
      RightAnt3.setTextureSize(512, 256);
      RightAnt3.mirror = true;
      setRotation(RightAnt3, 0.1919862F, 0.1047198F, 0F);
      RightAnt4 = new ModelRenderer(this, 245, 81);
      RightAnt4.addBox(0.5F, -0.5F, -12F, 3, 2, 2);
      RightAnt4.setRotationPoint(-7F, -20F, -37F);
      RightAnt4.setTextureSize(512, 256);
      RightAnt4.mirror = true;
      setRotation(RightAnt4, 0.1919862F, 0.1047198F, 0F);
      LeftBotArm1 = new ModelRenderer(this, 207, 114);
      LeftBotArm1.addBox(0F, -2.5F, -7F, 5, 5, 11);
      LeftBotArm1.setRotationPoint(8F, 7F, -5F);
      LeftBotArm1.setTextureSize(512, 256);
      LeftBotArm1.mirror = true;
      setRotation(LeftBotArm1, 0F, 0F, 0F);
      LeftBotArm2 = new ModelRenderer(this, 209, 131);
      LeftBotArm2.addBox(-2F, -2F, -10F, 4, 4, 10);
      LeftBotArm2.setRotationPoint(11F, 7F, -10F);
      LeftBotArm2.setTextureSize(512, 256);
      LeftBotArm2.mirror = true;
      setRotation(LeftBotArm2, 0F, 0.3141593F, 0F);
      LeftBotArm3 = new ModelRenderer(this, 217, 146);
      LeftBotArm3.addBox(-4F, -1F, -1F, 4, 2, 1);
      LeftBotArm3.setRotationPoint(12F, 7F, -19F);
      LeftBotArm3.setTextureSize(512, 256);
      LeftBotArm3.mirror = true;
      setRotation(LeftBotArm3, 0F, -0.5235988F, 0F);
      LeftMidArm1 = new ModelRenderer(this, 207, 114);
      LeftMidArm1.addBox(7F, -6.5F, -2F, 5, 5, 11);
      LeftMidArm1.setRotationPoint(0F, 3F, -10F);
      LeftMidArm1.setTextureSize(512, 256);
      LeftMidArm1.mirror = true;
      setRotation(LeftMidArm1, 0F, 0F, 0F);
      LeftMidArm2 = new ModelRenderer(this, 209, 131);
      LeftMidArm2.addBox(-2F, -2F, -10F, 4, 4, 10);
      LeftMidArm2.setRotationPoint(10F, -1F, -10F);
      LeftMidArm2.setTextureSize(512, 256);
      LeftMidArm2.mirror = true;
      setRotation(LeftMidArm2, 0F, 0.3141593F, 0F);
      LeftMidArm3 = new ModelRenderer(this, 217, 146);
      LeftMidArm3.addBox(-4F, -1F, -1F, 4, 2, 1);
      LeftMidArm3.setRotationPoint(11F, -1F, -19F); 
      LeftMidArm3.setTextureSize(512, 256);
      LeftMidArm3.mirror = true;
      setRotation(LeftMidArm3, 0F, -0.5235988F, 0F);
      LeftTopArm1 = new ModelRenderer(this, 207, 114);
      LeftTopArm1.addBox(6F, -6.5F, -2F, 5, 5, 11);
      LeftTopArm1.setRotationPoint(0F, -6F, -10F);
      LeftTopArm1.setTextureSize(512, 256);
      LeftTopArm1.mirror = true;
      setRotation(LeftTopArm1, 0F, 0F, 0F);
      LeftTopArm2 = new ModelRenderer(this, 209, 131);
      LeftTopArm2.addBox(-2F, -2F, -10F, 4, 4, 10);
      LeftTopArm2.setRotationPoint(9F, -10F, -10F);
      LeftTopArm2.setTextureSize(512, 256);
      LeftTopArm2.mirror = true;
      setRotation(LeftTopArm2, 0F, 0.3141593F, 0F);
      LeftTopArm3 = new ModelRenderer(this, 217, 146);
      LeftTopArm3.addBox(-4F, -1F, -1F, 4, 2, 1);
      LeftTopArm3.setRotationPoint(10F, -10F, -19F);
      LeftTopArm3.setTextureSize(512, 256);
      LeftTopArm3.mirror = true;
      setRotation(LeftTopArm3, 0F, -0.5235988F, 0F);
      RightBotArm1 = new ModelRenderer(this, 240, 114);
      RightBotArm1.addBox(-5F, -2.5F, -7F, 5, 5, 11);
      RightBotArm1.setRotationPoint(-8F, 7F, -5F);
      RightBotArm1.setTextureSize(512, 256);
      RightBotArm1.mirror = true;
      setRotation(RightBotArm1, 0F, 0F, 0F);
      RightBotArm2 = new ModelRenderer(this, 242, 131);
      RightBotArm2.addBox(-2F, -2F, -10F, 4, 4, 10);
      RightBotArm2.setRotationPoint(-11F, 7F, -10F);
      RightBotArm2.setTextureSize(512, 256);
      RightBotArm2.mirror = true;
      setRotation(RightBotArm2, 0F, -0.3141593F, 0F);
      RightBotArm3 = new ModelRenderer(this, 250, 146);
      RightBotArm3.addBox(0F, -1F, -1F, 4, 2, 1);
      RightBotArm3.setRotationPoint(-12F, 7F, -19F);
      RightBotArm3.setTextureSize(512, 256);
      RightBotArm3.mirror = true;
      setRotation(RightBotArm3, 0F, 0.5235988F, 0F);
      RightMidArm1 = new ModelRenderer(this, 240, 114);
      RightMidArm1.addBox(-12F, -6.5F, -2F, 5, 5, 11);
      RightMidArm1.setRotationPoint(0F, 3F, -10F);
      RightMidArm1.setTextureSize(512, 256);
      RightMidArm1.mirror = true;
      setRotation(RightMidArm1, 0F, 0F, 0F);
      RightMidArm2 = new ModelRenderer(this, 242, 131);
      RightMidArm2.addBox(-2F, -2F, -10F, 4, 4, 10);
      RightMidArm2.setRotationPoint(-10F, -1F, -10F);
      RightMidArm2.setTextureSize(512, 256);
      RightMidArm2.mirror = true;
      setRotation(RightMidArm2, 0F, -0.3141593F, 0F);
      RightMidArm3 = new ModelRenderer(this, 250, 146);
      RightMidArm3.addBox(0F, -1F, -1F, 4, 2, 1);
      RightMidArm3.setRotationPoint(-11F, -1F, -19F);
      RightMidArm3.setTextureSize(512, 256);
      RightMidArm3.mirror = true;
      setRotation(RightMidArm3, 0F, 0.5235988F, 0F);
      RightTopArm1 = new ModelRenderer(this, 240, 114);
      RightTopArm1.addBox(-11F, -6.5F, -2F, 5, 5, 11);
      RightTopArm1.setRotationPoint(0F, -6F, -10F);
      RightTopArm1.setTextureSize(512, 256);
      RightTopArm1.mirror = true;
      setRotation(RightTopArm1, 0F, 0F, 0F);
      RightTopArm2 = new ModelRenderer(this, 242, 131);
      RightTopArm2.addBox(-2F, -2F, -10F, 4, 4, 10);
      RightTopArm2.setRotationPoint(-9F, -10F, -10F);
      RightTopArm2.setTextureSize(512, 256);
      RightTopArm2.mirror = true;
      setRotation(RightTopArm2, 0F, -0.3141593F, 0F);
      RightTopArm3 = new ModelRenderer(this, 250, 146);
      RightTopArm3.addBox(0F, -1F, -1F, 4, 2, 1);
      RightTopArm3.setRotationPoint(-10F, -10F, -19F);
      RightTopArm3.setTextureSize(512, 256);
      RightTopArm3.mirror = true;
      setRotation(RightTopArm3, 0F, 0.5235988F, 0F);
      
      
      convertToChild(LegLF4, LegLFClaw);
      convertToChild(LegLM4, LegLMClaw);
      convertToChild(LegLB4, LegLBClaw);
      convertToChild(LegRB4, LegRBClaw);
      convertToChild(LegRM4, LegRMClaw);
      convertToChild(LegRF4, LegRFClaw);      
      
      convertToChild(LeftAnt3, LeftAnt4 );
      convertToChild(RightAnt3, RightAnt4 );
      convertToChild(LeftAnt2, LeftAnt3 );
      convertToChild(RightAnt2, RightAnt3 );
      convertToChild(LeftAnt1, LeftAnt2 );
      convertToChild(RightAnt1, RightAnt2 );
      convertToChild(HeadFront2, LeftAnt1 );
      convertToChild(HeadFront2, RightAnt1 );
      
      convertToChild(HeadMidRear1, Face );
      
      convertToChild(HeadMidRear1, HeadRear1 );
      convertToChild(HeadMidRear1, HeadRear2 );
      convertToChild(HeadMidRear1, HeadRear3 );
      convertToChild(HeadMidRear1, HeadRear4 );
      
      convertToChild(HeadMidRear1, HeadMidRear2 );
      convertToChild(HeadMidRear1, HeadMidRear3 );
      convertToChild(HeadMidRear1, HeadMidRear4 );
      
      convertToChild(HeadMidRear1, HeadMidFront1 );
      convertToChild(HeadMidRear1, HeadMidFront2 );
      convertToChild(HeadMidRear1, HeadMidFront3 );
      convertToChild(HeadMidRear1, HeadMidFront4 );
      
      convertToChild(HeadMidRear1, HeadFront1 );
      convertToChild(HeadMidRear1, HeadFront2 );
      convertToChild(HeadMidRear1, HeadFront3 );
      convertToChild(HeadMidRear1, HeadFront4 );
      
      
      convertToChild(Body4, HeadMidRear1);
      
      convertToChild(LeftTopArm2, LeftTopArm3);
      convertToChild(LeftTopArm1, LeftTopArm2);
      convertToChild(Body4, LeftTopArm1);
      
      convertToChild(RightTopArm2, RightTopArm3);
      convertToChild(RightTopArm1, RightTopArm2);
      convertToChild(Body4, RightTopArm1);
      
      convertToChild(LeftMidArm2, LeftMidArm3);
      convertToChild(LeftMidArm1, LeftMidArm2);
      convertToChild(Body3, LeftMidArm1);
      
      convertToChild(RightMidArm2, RightMidArm3);
      convertToChild(RightMidArm1, RightMidArm2);
      convertToChild(Body3, RightMidArm1);
      
      convertToChild(LeftBotArm2, LeftBotArm3);
      convertToChild(LeftBotArm1, LeftBotArm2);
      
      convertToChild(RightBotArm2, RightBotArm3);
      convertToChild(RightBotArm1, RightBotArm2);
      
      convertToChild(Body4, BellyTop3);
      convertToChild(Body4, BellyTop2);
      convertToChild(Body4, BellyTop1);
      
      convertToChild(Body3, BellyMidTop3);
      convertToChild(Body3, BellyMidTop2);
      convertToChild(Body3, BellyMidTop1);
      convertToChild(Body3, Body3Back);
      
      convertToChild(Body3, Body4);
      
      convertToChild(Tail3, Tail4);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    LegLF1.render(f5);
    LegLF1T.render(f5);
    LegLF2.render(f5);
    LegLF2T.render(f5);
    LegLF3.render(f5);
    LegLF3T.render(f5);
    LegLF4.render(f5);
    LegLF4T.render(f5);
    //LegLFClaw.render(f5);
    LegLM1.render(f5);
    LegLM1T.render(f5);
    LegLM2.render(f5);
    LegLM2T.render(f5);
    LegLM3.render(f5);
    LegLM3T.render(f5);
    LegLM4.render(f5);
    LegLM4T.render(f5);
    //LegLMClaw.render(f5);
    LegLB1.render(f5);
    LegLB1T.render(f5);
    LegLB2.render(f5);
    LegLB2T.render(f5);
    LegLB3.render(f5);
    LegLB3T.render(f5);
    LegLB4.render(f5);
    LegLB4T.render(f5);
    //LegLBClaw.render(f5);
    LegRB1.render(f5);
    LegRB1T.render(f5);
    LegRB2.render(f5);
    LegRB2T.render(f5);
    LegRB3.render(f5);
    LegRB3T.render(f5);
    LegRB4.render(f5);
    LegRB4T.render(f5);
    //LegRBClaw.render(f5);
    LegRM1.render(f5);
    LegRM1T.render(f5);
    LegRM2.render(f5);
    LegRM2T.render(f5);
    LegRM3.render(f5);
    LegRM3T.render(f5);
    LegRM4.render(f5);
    LegRM4T.render(f5);
    //LegRMClaw.render(f5);
    LegRF1.render(f5);
    LegRF1T.render(f5);
    LegRF2.render(f5);
    LegRF2T.render(f5);
    LegRF3.render(f5);
    LegRF3T.render(f5);
    LegRF4.render(f5);
    LegRF4T.render(f5);
    //LegRFClaw.render(f5);
    Body1.render(f5);
    Body2.render(f5);
    Body3.render(f5);
    //Body3Back.render(f5);
    //Body4.render(f5);
    //BellyTop1.render(f5);
    //BellyTop2.render(f5);
    //BellyTop3.render(f5);
    //BellyMidTop1.render(f5);
    //BellyMidTop2.render(f5);
    //BellyMidTop3.render(f5);
    BellyMidBot1.render(f5);
    BellyMidBot2.render(f5);
    BellyMidBot3.render(f5);
    BellyBot1.render(f5);
    BellyBot2.render(f5);
    BellyBot3.render(f5);
    BellyBot4.render(f5);
    TailBottom.render(f5);
    Tail1.render(f5);
    Tail2.render(f5);
    Tail3.render(f5);
    //Tail4.render(f5);
    //HeadRear1.render(f5);
    //HeadRear2.render(f5);
    //HeadRear3.render(f5);
    //HeadRear4.render(f5);
    //HeadMidRear1.render(f5);
    //HeadMidRear2.render(f5);
    //HeadMidRear3.render(f5);
    //HeadMidRear4.render(f5);
    //HeadMidFront1.render(f5);
    //HeadMidFront2.render(f5);
    //HeadMidFront3.render(f5);
    //HeadMidFront4.render(f5);
    //HeadFront1.render(f5);
    //HeadFront2.render(f5);
    //HeadFront3.render(f5);
    //HeadFront4.render(f5);
    //Face.render(f5);
    //LeftAnt1.render(f5);
    //LeftAnt2.render(f5);
    //LeftAnt3.render(f5);
    //LeftAnt4.render(f5);
    //RightAnt1.render(f5);
    //RightAnt2.render(f5);
    //RightAnt3.render(f5);
    //RightAnt4.render(f5);
    LeftBotArm1.render(f5);
    //LeftBotArm2.render(f5);
    //LeftBotArm3.render(f5);
    //LeftMidArm1.render(f5);
    //LeftMidArm2.render(f5);
    //LeftMidArm3.render(f5);
    //LeftTopArm1.render(f5);
    //LeftTopArm2.render(f5);
    //LeftTopArm3.render(f5);
    RightBotArm1.render(f5);
    //RightBotArm2.render(f5);
    //RightBotArm3.render(f5);
    //RightMidArm1.render(f5);
    //RightMidArm2.render(f5);
    //RightMidArm3.render(f5);
    //RightTopArm1.render(f5);
    //RightTopArm2.render(f5);
    //RightTopArm3.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  
  // This is really useful for converting the source from a Techne model export
  // which will have absolute rotation points that need to be converted before
  // creating the addChild() relationship
  protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild)
  {
     // move child rotation point to be relative to parent
     parChild.rotationPointX -= parParent.rotationPointX;
     parChild.rotationPointY -= parParent.rotationPointY;
     parChild.rotationPointZ -= parParent.rotationPointZ;
     // make rotations relative to parent
     parChild.rotateAngleX -= parParent.rotateAngleX;
     parChild.rotateAngleY -= parParent.rotateAngleY;
     parChild.rotateAngleZ -= parParent.rotateAngleZ;
     // create relationship
     parParent.addChild(parChild);
  }
  
  
  

  
  
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)  {

	 
	  
	  float RotateY = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	  float RotateZ = MathHelper.sin(par1 * 0.6662F) * 1.4F * par2;
	  if(RotateZ > 0) {
		  RotateZ = 0;
	  }
	  
	  


	  
	  
		  
	  this.LegLF4.rotateAngleZ = RotateZ;
	  this.LegLF4.rotateAngleY = RotateY + 0.6108652F;
	  this.LegLF4T.rotateAngleZ = RotateZ;
	  this.LegLF4T.rotateAngleY = RotateY + 0.6108652F;
	  this.LegLFClaw.rotateAngleZ = ((RotateZ * 2 ) + 0.6108652F);
	  
	  this.LegLM4.rotateAngleZ = RotateZ;
	  this.LegLM4.rotateAngleY = RotateY;
	  this.LegLM4T.rotateAngleZ = RotateZ;
	  this.LegLM4T.rotateAngleY = RotateY;
	  this.LegLMClaw.rotateAngleZ = ((RotateZ * 2 ) + 0.6108652F);
	  
	  this.LegLB4.rotateAngleZ = RotateZ;
	  this.LegLB4.rotateAngleY = RotateY - 0.6108652F;
	  this.LegLB4T.rotateAngleZ = RotateZ;
	  this.LegLB4T.rotateAngleY = RotateY - 0.6108652F;
	  this.LegLBClaw.rotateAngleZ = ((RotateZ * 2 ) + 0.6108652F);
	  
	  this.LegRF4.rotateAngleZ = RotateZ * -1;
	  this.LegRF4.rotateAngleY = (RotateY + 0.6108652F) * -1;
	  this.LegRF4T.rotateAngleZ = RotateZ * -1;
	  this.LegRF4T.rotateAngleY = (RotateY + 0.6108652F) * -1;
	  this.LegRFClaw.rotateAngleZ = ((RotateZ * 2 ) + 0.6108652F) * -1;
	  
	  this.LegRM4.rotateAngleZ =  RotateZ * -1;
	  this.LegRM4.rotateAngleY =  RotateY * -1;
	  this.LegRM4T.rotateAngleZ = RotateZ * -1;
	  this.LegRM4T.rotateAngleY = RotateY * -1;
	  this.LegRMClaw.rotateAngleZ =  ((RotateZ * 2 ) + 0.6108652F) * -1;
	  
	  this.LegRB4.rotateAngleZ = RotateZ * -1;
	  this.LegRB4.rotateAngleY = (RotateY - 0.6108652F) * -1;
	  this.LegRB4T.rotateAngleZ = RotateZ * -1;
	  this.LegRB4T.rotateAngleY = (RotateY - 0.6108652F) * -1;
	  this.LegRBClaw.rotateAngleZ = ((RotateZ * 2 ) + 0.6108652F)  * -1;
	  


	  

    
  }
  
  
  
  /**
   * Used for easily adding entity-dependent animations. The second and third float params here are the same second
   * and third as in the setRotationAngles method.
   */
  public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
  {
	  
	  
	  EntityChorpChorp entitychorp = (EntityChorpChorp)par1EntityLivingBase;
	  float DeathCheck = entitychorp.DeathWatcher();
	  
	 
	  float ticks = entitychorp.ticksExisted + par4;
	  
	  
	  float BodyRotateZ = MathHelper.sin(par2 * 1.1F) * 3.4F * par3 + (MathHelper.sin(ticks / 8) / 4);
	  float BodyRotateX = MathHelper.cos(par2 * 1.1F) * 3.4F * par3 + (MathHelper.cos(ticks / 8) / 4) + 1;
	  
	  if(DeathCheck == -1){
	  
	  this.Body3.rotateAngleZ = BodyRotateZ / 15;
	  this.Body3.rotateAngleX = BodyRotateX / 15;
	  this.Body4.rotateAngleZ = BodyRotateZ / 15;
	  this.Body4.rotateAngleX = BodyRotateX / 15;
	  this.HeadMidRear1.rotateAngleZ = BodyRotateZ / 15;
	  this.HeadMidRear1.rotateAngleX = BodyRotateX / 15;
	  this.Tail4.rotateAngleX = 0;
	  this.Tail3.rotateAngleX = 0;
	  
	  //this.RightBotArm2.rotateAngleY = MathHelper.cos(par3 / 4) + 0.3F;
	  this.RightBotArm3.rotateAngleY = (MathHelper.cos(ticks / 4) / 4) + 1;
	  //this.RightMidArm2.rotateAngleY = MathHelper.cos(par3 / 4) + 0.3F;
	  this.RightMidArm3.rotateAngleY = (MathHelper.cos((ticks + 5) / 4) / 4) + 1;
	  //this.RightTopArm2.rotateAngleY = MathHelper.cos(par3 / 4) + 0.3F;
	  this.RightTopArm3.rotateAngleY = (MathHelper.cos((ticks + 10) / 4) / 4) + 1;
	  
	  //this.LeftBotArm2.rotateAngleY = MathHelper.cos(par3 / 4) + 0.3F;
	  this.LeftBotArm3.rotateAngleY = ((MathHelper.cos(ticks / 4) / 4) + 1) * -1;
	  //this.LeftMidArm2.rotateAngleY = MathHelper.cos(par3 / 4) + 0.3F;
	  this.LeftMidArm3.rotateAngleY = ((MathHelper.cos((ticks + 5) / 4) / 4) + 1) * -1;
	  //this.LeftTopArm2.rotateAngleY = MathHelper.cos(par3 / 4) + 0.3F;
	  this.LeftTopArm3.rotateAngleY = ((MathHelper.cos((ticks + 10) / 4) / 4) + 1) * -1;
	  
	  }
	  
  

	  
	  
	  //slumps body in death animation
	  if(DeathCheck > 1){

		  this.Body3.rotateAngleX = entitychorp.DeathRotation();
		  this.Body3.rotateAngleZ = 0;
		  this.Body4.rotateAngleX = entitychorp.DeathRotation();
		  this.Body4.rotateAngleZ = 0;
		  this.HeadMidRear1.rotateAngleX = entitychorp.DeathRotation();
		  this.HeadMidRear1.rotateAngleZ = 0;
		  this.Tail3.rotateAngleX = entitychorp.DeathRotation() * -2F;
		  this.Tail4.rotateAngleX = entitychorp.DeathRotation() * -4F;
		  
		  
		  
		   
	  }
	  
  }

}
