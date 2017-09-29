package com.Splosions.ModularBosses.client.models.entity;

import com.Splosions.ModularBosses.client.models.FakeModelRenderer;
import com.Splosions.ModularBosses.entity.EntityGolem;
import com.Splosions.ModularBosses.entity.EntityParagon;
import com.Splosions.ModularBosses.util.ModelUtils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelGolem extends ModelBase {
	public ModelRenderer WAIST;
	public ModelRenderer HIP;
	public ModelRenderer Body;
	public ModelRenderer RARM;
	public ModelRenderer LARM;
	public ModelRenderer LRShoulder2;
	public ModelRenderer LFShoulder2;
	public ModelRenderer RFShoulder2;
	public ModelRenderer RRShoulder2;
	public ModelRenderer RFShoulder1;
	public ModelRenderer RRShoulder1;
	public ModelRenderer LRShoulder1;
	public ModelRenderer LFShoulder1;
	public ModelRenderer RChest;
	public ModelRenderer LChest;
	public ModelRenderer HEAD;
	public ModelRenderer RArm1;
	public ModelRenderer RArm2;
	public ModelRenderer LArm1;
	public ModelRenderer LArm2;
	public ModelRenderer LFHip;
	public ModelRenderer LLEG;
	public ModelRenderer RLEG;
	public ModelRenderer RRHip;
	public ModelRenderer LRHip;
	public ModelRenderer RFHip;
	public ModelRenderer LLeg1;
	public ModelRenderer LLeg2;
	public ModelRenderer RLeg1;
	public ModelRenderer RLeg2;

	public FakeModelRenderer FWAIST = new FakeModelRenderer();
	public FakeModelRenderer FRARM = new FakeModelRenderer();
	public FakeModelRenderer FLARM = new FakeModelRenderer();
	public FakeModelRenderer FLRShoulder2 = new FakeModelRenderer();
	public FakeModelRenderer FLFShoulder2 = new FakeModelRenderer();
	public FakeModelRenderer FRFShoulder2 = new FakeModelRenderer();
	public FakeModelRenderer FRRShoulder2 = new FakeModelRenderer();
	public FakeModelRenderer FRFShoulder1 = new FakeModelRenderer();
	public FakeModelRenderer FRRShoulder1 = new FakeModelRenderer();
	public FakeModelRenderer FLRShoulder1 = new FakeModelRenderer();
	public FakeModelRenderer FLFShoulder1 = new FakeModelRenderer();
	public FakeModelRenderer FRChest = new FakeModelRenderer();
	public FakeModelRenderer FLChest = new FakeModelRenderer();
	public FakeModelRenderer FHEAD = new FakeModelRenderer();
	public FakeModelRenderer FRArm1 = new FakeModelRenderer();
	public FakeModelRenderer FRArm2 = new FakeModelRenderer();
	public FakeModelRenderer FLArm1 = new FakeModelRenderer();
	public FakeModelRenderer FLArm2 = new FakeModelRenderer();
	public FakeModelRenderer FLFHip = new FakeModelRenderer();
	public FakeModelRenderer FLLEG = new FakeModelRenderer();
	public FakeModelRenderer FRLEG = new FakeModelRenderer();
	public FakeModelRenderer FRRHip = new FakeModelRenderer();
	public FakeModelRenderer FLRHip = new FakeModelRenderer();
	public FakeModelRenderer FRFHip = new FakeModelRenderer();
	public FakeModelRenderer FLLeg1 = new FakeModelRenderer();
	public FakeModelRenderer FLLeg2 = new FakeModelRenderer();
	public FakeModelRenderer FRLeg1 = new FakeModelRenderer();
	public FakeModelRenderer FRLeg2 = new FakeModelRenderer();

	public ModelGolem() {

		FWAIST.setModelVars(-25, 24, -22, 0, 46, 39);
		FRARM.setModelVars(-100, 42, -28, -3, -51, -63);
		FLARM.setModelVars(88, 222, 11.17F, -52, 64, 55);
		FLRShoulder2.setModelVars(-200, 90, 50, -55.22F, 60, 23);
		FLFShoulder2.setModelVars(160, 200, -200, 45.21F, 56, 44);
		FRFShoulder2.setModelVars(-90, 120, -60, 38.2F, 60, -35);
		FRRShoulder2.setModelVars(-50, 200, 90, -47.2F, 50, -33);
		FRFShoulder1.setModelVars(100, 200, -200, 59.5F, 50, -58.5F);
		FRRShoulder1.setModelVars(200, 200, 90, -40, 70, -43.5F);
		FLRShoulder1.setModelVars(120, 200, 160, -15.5F, 55, 35.5F);
		FLFShoulder1.setModelVars(230, 170, 100, 32.52F, 50, 29.5F);
		FRChest.setModelVars(-222, 222, 111, 0, 60, -49);
		FLChest.setModelVars(220, -170, 160, 0, 60, 63);
		FHEAD.setModelVars(33, 48, 160, 70, 54, 0);
		FRArm1.setModelVars(81, -91, -55, -63.98F, 93, 46.01F);
		FRArm2.setModelVars(200, 190, 55, 13.02F, 90, 0.01F);
		FLArm1.setModelVars(80, 120, 194.78F, -48.98F, 74, -59.99F);
		FLArm2.setModelVars(30, 88, 112.17F, 49.02F, 87, 40.01F);
		FLFHip.setModelVars(140, -170, 112, 26.5F, 49, -51.5F);
		FLLEG.setModelVars(200, 200, 200, -20, 42, 20);
		FRLEG.setModelVars(100, 150, 10, 35, 63, -26);
		FRRHip.setModelVars(222, 111, 200, -41.5F, 53, 37.5F);
		FLRHip.setModelVars(220, 111, 111, -40.52F, 53, -50.5F);
		FRFHip.setModelVars(-200, 70, -80, 27.52F, 50, 42.5F);
		FLLeg1.setModelVars(100, 201, 0, 12.02F, 38.3F, 13.5F);
		FLLeg2.setModelVars(100, -100, 50, 22.01F, 48, -20.99F);
		FRLeg1.setModelVars(100, 201, 0, 12.02F, 38.3F, 13.5F);
		FRLeg2.setModelVars(56, -50, 200, -34.99F, 56, -10.99F);

		this.textureWidth = 16;
		this.textureHeight = 16;
		this.RRShoulder1 = new ModelRenderer(this, 0, 0);
		this.RRShoulder1.setRotationPoint(-5.520000000000022F, -47.0F, -5.5F);
		this.RRShoulder1.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RRShoulder1, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
		this.RArm1 = new ModelRenderer(this, 0, 0);
		this.RArm1.setRotationPoint(0.020000000000003126F, 12.0F, 0.00999999999999801F);
		this.RArm1.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RArm1, 0.4363323129985824F, 0.0F, 0.0F);
		this.RRShoulder2 = new ModelRenderer(this, 0, 0);
		this.RRShoulder2.setRotationPoint(-4.200000000000003F, -42.0F, -18.0F);
		this.RRShoulder2.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RRShoulder2, 0.5235987755982988F, 0.0F, -0.2617993877991494F);
		this.RArm2 = new ModelRenderer(this, 0, 0);
		this.RArm2.setRotationPoint(0.019999999999999574F, 12.0F, 0.01F);
		this.RArm2.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RArm2, 0.17453292519943295F, 0.0F, 0.0F);
		this.RLEG = new ModelRenderer(this, 0, 0);
		this.RLEG.setRotationPoint(0.0F, -11.0F, -7.0F);
		this.RLEG.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RLEG, -0.3490658503988659F, 0.0F, 0.0F);
		this.WAIST = new ModelRenderer(this, 0, 0);
		this.WAIST.setRotationPoint(0.0F, -25.0F, 0.0F);
		this.WAIST.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
		this.LArm2 = new ModelRenderer(this, 0, 0);
		this.LArm2.setRotationPoint(0.020000000000003126F, 12.0F, 0.00999999999999801F);
		this.LArm2.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LArm2, -0.17453292519943295F, 0.0F, 0.0F);
		this.Body = new ModelRenderer(this, 0, 0);
		this.Body.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.Body.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(Body, 0.0F, 1.5707963267948966F, 0.0F);
		this.RFShoulder1 = new ModelRenderer(this, 0, 0);
		this.RFShoulder1.setRotationPoint(5.5F, -47.0F, -5.5F);
		this.RFShoulder1.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RFShoulder1, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
		this.LLEG = new ModelRenderer(this, 0, 0);
		this.LLEG.setRotationPoint(0.0F, -11.0F, 7.0F);
		this.LLEG.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LLEG, 0.3490658503988659F, 0.0F, 0.0F);
		this.LLeg1 = new ModelRenderer(this, 0, 0);
		this.LLeg1.setRotationPoint(0.019999999999999574F, 13.299999999999997F, 0.5F);
		this.LLeg1.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LLeg1, -0.3490658503988659F, 0.0F, 0.0F);
		this.RLeg1 = new ModelRenderer(this, 0, 0);
		this.RLeg1.setRotationPoint(0.02F, 13.299999999999997F, -0.5F);
		this.RLeg1.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RLeg1, 0.3490658503988659F, 0.0F, 0.0F);
		this.HEAD = new ModelRenderer(this, 0, 0);
		this.HEAD.setRotationPoint(0.0F, -54.0F, 0.0F);
		this.HEAD.addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
		this.LArm1 = new ModelRenderer(this, 0, 0);
		this.LArm1.setRotationPoint(0.020000000000003126F, 12.0F, 0.00999999999999801F);
		this.LArm1.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LArm1, -0.4363323129985824F, 0.0F, 0.0F);
		this.LRHip = new ModelRenderer(this, 0, 0);
		this.LRHip.setRotationPoint(-5.520000000000003F, -9.0F, -5.5F);
		this.LRHip.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LRHip, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
		this.RFHip = new ModelRenderer(this, 0, 0);
		this.RFHip.setRotationPoint(5.52F, -9.0F, 5.5F);
		this.RFHip.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RFHip, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
		this.RFShoulder2 = new ModelRenderer(this, 0, 0);
		this.RFShoulder2.setRotationPoint(4.200000000000003F, -42.0F, -18.0F);
		this.RFShoulder2.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RFShoulder2, 0.5235987755982988F, 0.0F, 0.2617993877991494F);
		this.LLeg2 = new ModelRenderer(this, 0, 0);
		this.LLeg2.setRotationPoint(0.010000000000001563F, 16.0F, 0.010000000000001563F);
		this.LLeg2.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.LFHip = new ModelRenderer(this, 0, 0);
		this.LFHip.setRotationPoint(5.5F, -9.0F, -5.5F);
		this.LFHip.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LFHip, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
		this.LRShoulder2 = new ModelRenderer(this, 0, 0);
		this.LRShoulder2.setRotationPoint(-4.219999999999999F, -42.0F, 18.0F);
		this.LRShoulder2.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LRShoulder2, -0.5235987755982988F, 0.0F, -0.2617993877991494F);
		this.RChest = new ModelRenderer(this, 0, 0);
		this.RChest.setRotationPoint(0.0F, -38.0F, -8.0F);
		this.RChest.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.LFShoulder2 = new ModelRenderer(this, 0, 0);
		this.LFShoulder2.setRotationPoint(4.210000000000001F, -42.0F, 18.0F);
		this.LFShoulder2.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LFShoulder2, -0.5235987755982988F, 0.0F, 0.2617993877991494F);
		this.LChest = new ModelRenderer(this, 0, 0);
		this.LChest.setRotationPoint(0.0F, -38.0F, 8.0F);
		this.LChest.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.RARM = new ModelRenderer(this, 0, 0);
		this.RARM.setRotationPoint(0.0F, -40.0F, -21.0F);
		this.RARM.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RARM, -0.6981317007977318F, 0.0F, 0.0F);
		this.RRHip = new ModelRenderer(this, 0, 0);
		this.RRHip.setRotationPoint(-5.5F, -9.0F, 5.5F);
		this.RRHip.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(RRHip, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
		this.RLeg2 = new ModelRenderer(this, 0, 0);
		this.RLeg2.setRotationPoint(0.00999999999999801F, 16.0F, 0.009999999999999787F);
		this.RLeg2.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.HIP = new ModelRenderer(this, 0, 0);
		this.HIP.setRotationPoint(0.0F, -9.0F, 0.0F);
		this.HIP.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(HIP, 0.0F, 1.5707963267948966F, 0.0F);
		this.LRShoulder1 = new ModelRenderer(this, 0, 0);
		this.LRShoulder1.setRotationPoint(-5.5F, -47.0F, 5.5F);
		this.LRShoulder1.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LRShoulder1, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
		this.LARM = new ModelRenderer(this, 0, 0);
		this.LARM.setRotationPoint(0.0F, -40.0F, 21.0F);
		this.LARM.addBox(-8.0F, 0.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LARM, 0.6981317007977318F, 0.03490658503988659F, 0.0F);
		this.LFShoulder1 = new ModelRenderer(this, 0, 0);
		this.LFShoulder1.setRotationPoint(5.520000000000002F, -47.0F, 5.5F);
		this.LFShoulder1.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
		this.setRotateAngle(LFShoulder1, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
		this.Body.addChild(this.RRShoulder1);
		this.RARM.addChild(this.RArm1);
		this.Body.addChild(this.RRShoulder2);
		this.RArm1.addChild(this.RArm2);
		this.HIP.addChild(this.RLEG);
		this.LArm1.addChild(this.LArm2);
		this.WAIST.addChild(this.Body);
		this.Body.addChild(this.RFShoulder1);
		this.HIP.addChild(this.LLEG);
		this.LLEG.addChild(this.LLeg1);
		this.RLEG.addChild(this.RLeg1);
		this.Body.addChild(this.HEAD);
		this.LARM.addChild(this.LArm1);
		this.HIP.addChild(this.LRHip);
		this.HIP.addChild(this.RFHip);
		this.Body.addChild(this.RFShoulder2);
		this.LLeg1.addChild(this.LLeg2);
		this.HIP.addChild(this.LFHip);
		this.Body.addChild(this.LRShoulder2);
		this.Body.addChild(this.RChest);
		this.Body.addChild(this.LFShoulder2);
		this.Body.addChild(this.LChest);
		this.Body.addChild(this.RARM);
		this.HIP.addChild(this.RRHip);
		this.RLeg1.addChild(this.RLeg2);
		this.Body.addChild(this.LRShoulder1);
		this.Body.addChild(this.LARM);
		this.Body.addChild(this.LFShoulder1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.WAIST.render(f5);
		this.HIP.render(f5);
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
		setLivingAnimations((EntityGolem) entity, par2, par3, partialTick);
	}



	
	public float[] WAIST_rotationPointX;
	public float[] WAIST_rotationPointY;
	public float[] WAIST_rotationPointZ;
	public float[] WAIST_rotateAngleX;
	public float[] WAIST_rotateAngleY;
	public float[] WAIST_rotateAngleZ;

	public float[] Body_rotationPointX;
	public float[] Body_rotationPointY;
	public float[] Body_rotationPointZ;
	public float[] Body_rotateAngleX;
	public float[] Body_rotateAngleY;
	public float[] Body_rotateAngleZ;

	public float[] RARM_rotationPointX;
	public float[] RARM_rotationPointY;
	public float[] RARM_rotationPointZ;
	public float[] RARM_rotateAngleX;
	public float[] RARM_rotateAngleY;
	public float[] RARM_rotateAngleZ;

	public float[] RArm1_rotationPointX;
	public float[] RArm1_rotationPointY;
	public float[] RArm1_rotationPointZ;
	public float[] RArm1_rotateAngleX;
	public float[] RArm1_rotateAngleY;
	public float[] RArm1_rotateAngleZ;

	public float[] RArm2_rotationPointX;
	public float[] RArm2_rotationPointY;
	public float[] RArm2_rotationPointZ;
	public float[] RArm2_rotateAngleX;
	public float[] RArm2_rotateAngleY;
	public float[] RArm2_rotateAngleZ;

	public float[] LARM_rotationPointX;
	public float[] LARM_rotationPointY;
	public float[] LARM_rotationPointZ;
	public float[] LARM_rotateAngleX;
	public float[] LARM_rotateAngleY;
	public float[] LARM_rotateAngleZ;

	public float[] LArm1_rotationPointX;
	public float[] LArm1_rotationPointY;
	public float[] LArm1_rotationPointZ;
	public float[] LArm1_rotateAngleX;
	public float[] LArm1_rotateAngleY;
	public float[] LArm1_rotateAngleZ;

	public float[] LArm2_rotationPointX;
	public float[] LArm2_rotationPointY;
	public float[] LArm2_rotationPointZ;
	public float[] LArm2_rotateAngleX;
	public float[] LArm2_rotateAngleY;
	public float[] LArm2_rotateAngleZ;

	public float[] LRShoulder2_rotationPointX;
	public float[] LRShoulder2_rotationPointY;
	public float[] LRShoulder2_rotationPointZ;
	public float[] LRShoulder2_rotateAngleX;
	public float[] LRShoulder2_rotateAngleY;
	public float[] LRShoulder2_rotateAngleZ;

	public float[] LFShoulder2_rotationPointX;
	public float[] LFShoulder2_rotationPointY;
	public float[] LFShoulder2_rotationPointZ;
	public float[] LFShoulder2_rotateAngleX;
	public float[] LFShoulder2_rotateAngleY;
	public float[] LFShoulder2_rotateAngleZ;

	public float[] RFShoulder2_rotationPointX;
	public float[] RFShoulder2_rotationPointY;
	public float[] RFShoulder2_rotationPointZ;
	public float[] RFShoulder2_rotateAngleX;
	public float[] RFShoulder2_rotateAngleY;
	public float[] RFShoulder2_rotateAngleZ;

	public float[] RRShoulder2_rotationPointX;
	public float[] RRShoulder2_rotationPointY;
	public float[] RRShoulder2_rotationPointZ;
	public float[] RRShoulder2_rotateAngleX;
	public float[] RRShoulder2_rotateAngleY;
	public float[] RRShoulder2_rotateAngleZ;

	public float[] RFShoulder1_rotationPointX;
	public float[] RFShoulder1_rotationPointY;
	public float[] RFShoulder1_rotationPointZ;
	public float[] RFShoulder1_rotateAngleX;
	public float[] RFShoulder1_rotateAngleY;
	public float[] RFShoulder1_rotateAngleZ;

	public float[] RRShoulder1_rotationPointX;
	public float[] RRShoulder1_rotationPointY;
	public float[] RRShoulder1_rotationPointZ;
	public float[] RRShoulder1_rotateAngleX;
	public float[] RRShoulder1_rotateAngleY;
	public float[] RRShoulder1_rotateAngleZ;

	public float[] LRShoulder1_rotationPointX;
	public float[] LRShoulder1_rotationPointY;
	public float[] LRShoulder1_rotationPointZ;
	public float[] LRShoulder1_rotateAngleX;
	public float[] LRShoulder1_rotateAngleY;
	public float[] LRShoulder1_rotateAngleZ;

	public float[] LFShoulder1_rotationPointX;
	public float[] LFShoulder1_rotationPointY;
	public float[] LFShoulder1_rotationPointZ;
	public float[] LFShoulder1_rotateAngleX;
	public float[] LFShoulder1_rotateAngleY;
	public float[] LFShoulder1_rotateAngleZ;

	public float[] RChest_rotationPointX;
	public float[] RChest_rotationPointY;
	public float[] RChest_rotationPointZ;
	public float[] RChest_rotateAngleX;
	public float[] RChest_rotateAngleY;
	public float[] RChest_rotateAngleZ;

	public float[] LChest_rotationPointX;
	public float[] LChest_rotationPointY;
	public float[] LChest_rotationPointZ;
	public float[] LChest_rotateAngleX;
	public float[] LChest_rotateAngleY;
	public float[] LChest_rotateAngleZ;

	public float[] HEAD_rotationPointX;
	public float[] HEAD_rotationPointY;
	public float[] HEAD_rotationPointZ;
	public float[] HEAD_rotateAngleX;
	public float[] HEAD_rotateAngleY;
	public float[] HEAD_rotateAngleZ;

	public float[] HIP_rotationPointX;
	public float[] HIP_rotationPointY;
	public float[] HIP_rotationPointZ;
	public float[] HIP_rotateAngleX;
	public float[] HIP_rotateAngleY;
	public float[] HIP_rotateAngleZ;

	public float[] LFHip_rotationPointX;
	public float[] LFHip_rotationPointY;
	public float[] LFHip_rotationPointZ;
	public float[] LFHip_rotateAngleX;
	public float[] LFHip_rotateAngleY;
	public float[] LFHip_rotateAngleZ;

	public float[] RRHip_rotationPointX;
	public float[] RRHip_rotationPointY;
	public float[] RRHip_rotationPointZ;
	public float[] RRHip_rotateAngleX;
	public float[] RRHip_rotateAngleY;
	public float[] RRHip_rotateAngleZ;

	public float[] LRHip_rotationPointX;
	public float[] LRHip_rotationPointY;
	public float[] LRHip_rotationPointZ;
	public float[] LRHip_rotateAngleX;
	public float[] LRHip_rotateAngleY;
	public float[] LRHip_rotateAngleZ;

	public float[] RFHip_rotationPointX;
	public float[] RFHip_rotationPointY;
	public float[] RFHip_rotationPointZ;
	public float[] RFHip_rotateAngleX;
	public float[] RFHip_rotateAngleY;
	public float[] RFHip_rotateAngleZ;

	public float[] LLEG_rotationPointX;
	public float[] LLEG_rotationPointY;
	public float[] LLEG_rotationPointZ;
	public float[] LLEG_rotateAngleX;
	public float[] LLEG_rotateAngleY;
	public float[] LLEG_rotateAngleZ;

	public float[] LLeg1_rotationPointX;
	public float[] LLeg1_rotationPointY;
	public float[] LLeg1_rotationPointZ;
	public float[] LLeg1_rotateAngleX;
	public float[] LLeg1_rotateAngleY;
	public float[] LLeg1_rotateAngleZ;

	public float[] LLeg2_rotationPointX;
	public float[] LLeg2_rotationPointY;
	public float[] LLeg2_rotationPointZ;
	public float[] LLeg2_rotateAngleX;
	public float[] LLeg2_rotateAngleY;
	public float[] LLeg2_rotateAngleZ;

	public float[] RLEG_rotationPointX;
	public float[] RLEG_rotationPointY;
	public float[] RLEG_rotationPointZ;
	public float[] RLEG_rotateAngleX;
	public float[] RLEG_rotateAngleY;
	public float[] RLEG_rotateAngleZ;

	public float[] RLeg1_rotationPointX;
	public float[] RLeg1_rotationPointY;
	public float[] RLeg1_rotationPointZ;
	public float[] RLeg1_rotateAngleX;
	public float[] RLeg1_rotateAngleY;
	public float[] RLeg1_rotateAngleZ;

	public float[] RLeg2_rotationPointX;
	public float[] RLeg2_rotationPointY;
	public float[] RLeg2_rotationPointZ;
	public float[] RLeg2_rotateAngleX;
	public float[] RLeg2_rotateAngleY;
	public float[] RLeg2_rotateAngleZ;
	
	
	
	
	private void setLivingAnimations(EntityGolem entity, float moveCounter, float speed, float PartialTick) {



	}
	
	
	
	public void Build(){
		
	}
	
	
	public void Roll(){
		
	}
	
	
	public void Punch(){
		
	}

	
}
