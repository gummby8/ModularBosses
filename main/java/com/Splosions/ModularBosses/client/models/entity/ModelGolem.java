package com.Splosions.ModularBosses.client.models.entity;

import com.Splosions.ModularBosses.client.models.FakeModelRenderer;
import com.Splosions.ModularBosses.client.models.KeyFrame;
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



	public ModelGolem() {

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
		
		build_Build();
		build_Throw();
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
	
	
	private void setLivingAnimations(EntityGolem golem, float moveCounter, float speed, float PartialTick) {
		if (golem.AniID == golem.BUILD){
			Build(golem.AniFrame, PartialTick);
		} else 
		if (golem.AniID == golem.THROW){
			Throw(golem.AniFrame, PartialTick);
		}


	}
	
	
	public void moveParts(int frame, ModelRenderer part, KeyFrame[] keyArray, float partialTick){
		int keyId = getKeyFrameNum(frame, keyArray);
		KeyFrame curKey = keyArray[keyId];

		//if it is the very first frame OR if it is the last frame in an animation
		if (keyArray.length == 1 || frame == 0 || frame == keyArray[keyArray.length - 1].frame){
			part.rotationPointX = curKey.posX;
			part.rotationPointY = curKey.posY;
			part.rotationPointZ = curKey.posZ;
			part.rotateAngleX = curKey.rotX * 0.0174533F; //Remember kids, always convert to radians;
			part.rotateAngleY = curKey.rotY * 0.0174533F; //Remember kids, always convert to radians;
			part.rotateAngleZ = curKey.rotZ * 0.0174533F; //Remember kids, always convert to radians;
		}else{
			KeyFrame nextKey = keyArray[keyId + 1];
			float step;
			float position;
			float nextPosition;
			//float total = nextKey.posX - curKey.posX
			//int dur = nextKey.frame - curKey.frame;
			
			step = (nextKey.posX - curKey.posX) / (nextKey.frame - curKey.frame); //total / duration   this is how much movement there is between each tick
			position = (frame - curKey.frame) * step;
			nextPosition = (frame + 1 - curKey.frame) * step;
			part.rotationPointX = curKey.posX + position + (partialTick * (nextPosition - position));
			
			step = (nextKey.posY - curKey.posY) / (nextKey.frame - curKey.frame);
			position = (frame - curKey.frame) * step;
			nextPosition = (frame + 1 - curKey.frame) * step;
			part.rotationPointY = curKey.posY + position + (partialTick * (nextPosition - position));
			
			step = (nextKey.posZ - curKey.posZ) / (nextKey.frame - curKey.frame);
			position = (frame - curKey.frame) * step;
			nextPosition = (frame + 1 - curKey.frame) * step;
			part.rotationPointZ = curKey.posZ + position + (partialTick * (nextPosition - position));
			
			step = (nextKey.rotX - curKey.rotX) / (nextKey.frame - curKey.frame);
			position = (frame - curKey.frame) * step;
			nextPosition = (frame + 1 - curKey.frame) * step;
			part.rotateAngleX = (curKey.rotX + position + (partialTick * (nextPosition - position))) * 0.0174533F; //Remember kids, always conver to radians;
			
			step = (nextKey.rotY - curKey.rotY) / (nextKey.frame - curKey.frame);
			position = (frame - curKey.frame) * step;
			nextPosition = (frame + 1 - curKey.frame) * step;
			part.rotateAngleY = (curKey.rotY + position + (partialTick * (nextPosition - position))) * 0.0174533F; //Remember kids, always conver to radians
			
			step = (nextKey.rotZ - curKey.rotZ) / (nextKey.frame - curKey.frame);
			position = (frame - curKey.frame) * step;
			nextPosition = (frame + 1 - curKey.frame) * step;
			part.rotateAngleZ = (curKey.rotZ + position + (partialTick * (nextPosition - position))) * 0.0174533F; //Remember kids, always conver to radians
		}
	}
	
	
	public int getKeyFrameNum(int frame, KeyFrame[] keyArray){
		if (keyArray.length == 1){
		return 0;	
		}
		
		for (int x = 0; x < keyArray.length; x++){
			if (frame == keyArray[x].frame ){
				return x;
			} else 
			if(frame > keyArray[x].frame && frame < keyArray[x + 1].frame){
				return x;
			}
		}
		return 0;
	}

	/**
	 * Throw Animation
	 */
	static final KeyFrame[] KF_Throw_HEAD = new KeyFrame[6];//
	static final KeyFrame[] KF_Throw_LRShoulder2 = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_LFShoulder1 = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_RRShoulder2 = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_RFShoulder1 = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_LRShoulder1 = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_LFShoulder2 = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_RFShoulder2 = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_RRShoulder1 = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_LARM = new KeyFrame[6];//
	static final KeyFrame[] KF_Throw_LArm1 = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_LArm2 = new KeyFrame[4];//
	static final KeyFrame[] KF_Throw_RARM = new KeyFrame[6];//
	static final KeyFrame[] KF_Throw_RArm1 = new KeyFrame[7];//
	static final KeyFrame[] KF_Throw_RArm2 = new KeyFrame[6];//
	static final KeyFrame[] KF_Throw_RChest = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_LChest = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_WAIST = new KeyFrame[7];//
	static final KeyFrame[] KF_Throw_HIP = new KeyFrame[7];//
	static final KeyFrame[] KF_Throw_LFHip = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_RRHip = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_RFHip = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_LRHip = new KeyFrame[1];//
	static final KeyFrame[] KF_Throw_RLEG = new KeyFrame[6];//
	static final KeyFrame[] KF_Throw_RLeg1 = new KeyFrame[6];//
	static final KeyFrame[] KF_Throw_RLeg2 = new KeyFrame[7];//
	static final KeyFrame[] KF_Throw_LLEG = new KeyFrame[7];//
	static final KeyFrame[] KF_Throw_LLeg1 = new KeyFrame[7];//
	static final KeyFrame[] KF_Throw_LLeg2 = new KeyFrame[6];//
	
	public void build_Throw(){
		KF_Throw_HEAD[0] = new KeyFrame(0 , 0, -54, 0, 0, 0, 0);
		KF_Throw_HEAD[1] = new KeyFrame(10, 0, -54, 0, 0, -50, 0);
		KF_Throw_HEAD[2] = new KeyFrame(12, 0, -54, 0, 0, -50, 0);
		KF_Throw_HEAD[3] = new KeyFrame(17, 0, -54, 0, 0,  40, 0);
		KF_Throw_HEAD[4] = new KeyFrame(19, 0, -54, 0, 0,  40, 0);
		KF_Throw_HEAD[5] = new KeyFrame(29, 0, -54, 0, 0, 0, 0);
		
		KF_Throw_LRShoulder2[0] = new KeyFrame(0, -4.22F, -42, 18, -30, 0, -15);
		KF_Throw_LFShoulder1[0] = new KeyFrame(0, 5.52F, -47, 5.5F, -15, 0, 15);
		KF_Throw_RRShoulder2[0] = new KeyFrame(0, -4.2F, -42, -18, 30, 0, -15);
		KF_Throw_RFShoulder1[0] = new KeyFrame(0, 5.5F, -47, -5.5F, 15, 0, 15);
		KF_Throw_LRShoulder1[0] = new KeyFrame(0, -5.5F, -47, 5.5F, -15, 0, -15);
		KF_Throw_LFShoulder2[0] = new KeyFrame(0, 4.21F, -42, 18, -30, 0, 15);
		KF_Throw_RFShoulder2[0] = new KeyFrame(0, 4.2F, -42, -18, 30, 0, 15);
		KF_Throw_RRShoulder1[0] = new KeyFrame(0, -5.52F, -47, -5.5F, 15, 0, -15);
		
		KF_Throw_LARM[0] = new KeyFrame(0 , 0, -40, 21, 40, 0, 0);
		KF_Throw_LARM[1] = new KeyFrame(10, 0, -40, 21, 40, 0, -20); 
		KF_Throw_LARM[2] = new KeyFrame(12, 0, -40, 21, 40, 0, -20);
		KF_Throw_LARM[3] = new KeyFrame(15, 0, -40, 21, 40, 0, 30);
		KF_Throw_LARM[4] = new KeyFrame(19, 0, -40, 21, 40, 0, 30);
		KF_Throw_LARM[5] = new KeyFrame(29, 0, -40, 21, 40, 0, 0);
		
		KF_Throw_LArm1[0] = new KeyFrame(0, 0.02F, 12, 0.01F, -25, 0, 0);
		
		KF_Throw_LArm2[0] = new KeyFrame(0 , 0.02F, 12, 0.01F, -10, 0, 0);
		KF_Throw_LArm2[1] = new KeyFrame(10, 0.02F, 12, 0.01F, -10, 0, -25);
		KF_Throw_LArm2[2] = new KeyFrame(19, 0.02F, 12, 0.01F, -10, 0, -25); 
		KF_Throw_LArm2[3] = new KeyFrame(29 , 0.02F, 12, 0.01F, -10, 0, 0);
		
		KF_Throw_RARM[0] = new KeyFrame(0 , 0, -40, -21, -40, 0, 0);
		KF_Throw_RARM[1] = new KeyFrame(12, 0, -40, -21, -42, 2.61F, -180);
		KF_Throw_RARM[2] = new KeyFrame(15, 0, -40, -21, -100, 0, -180);
		KF_Throw_RARM[3] = new KeyFrame(17, 0, -40, -21, -80, 35, -155);
		KF_Throw_RARM[4] = new KeyFrame(19, 0, -40, -21, -80, 35, -155);
		KF_Throw_RARM[5] = new KeyFrame(29, 0, -40, -21, -40, 0, 0);
		
		KF_Throw_RArm1[0] = new KeyFrame(0 , 0.02F, 12, 0.01F, 25, 0, 0);
		KF_Throw_RArm1[1] = new KeyFrame(10, 0.02F, 12, 0.01F, -5.22F, 0, 10);
		KF_Throw_RArm1[2] = new KeyFrame(12, 0.02F, 12, 0.01F, -5.22F, 0, 10); 
		KF_Throw_RArm1[3] = new KeyFrame(15, 0.02F, 12, 0.01F, 50, 0, 10);
		KF_Throw_RArm1[4] = new KeyFrame(17, 0.02F, 12, 0.01F, 10, 0, 30);
		KF_Throw_RArm1[5] = new KeyFrame(19, 0.02F, 12, 0.01F, 10, 0, 30); 
		KF_Throw_RArm1[6] = new KeyFrame(29, 0.02F, 12, 0.01F, 25, 0, 0);
		
		KF_Throw_RArm2[0] = new KeyFrame(0 , 0.02F, 12, 0.01F, 10, 0, 0); 
		KF_Throw_RArm2[1] = new KeyFrame(12, 0.02F, 12, 0.01F, 10, 0, 0);
		KF_Throw_RArm2[2] = new KeyFrame(15, 0.02F, 12, 0.01F, 35, 0, 0);
		KF_Throw_RArm2[3] = new KeyFrame(17, 0.02F, 12, 0.01F, 15, 0, 0);
		KF_Throw_RArm2[4] = new KeyFrame(19, 0.02F, 12, 0.01F, 15, 0, 0);
		KF_Throw_RArm2[5] = new KeyFrame(29, 0.02F, 12, 0.01F, 10, 0, 0);
		
		KF_Throw_RChest[0] = new KeyFrame(0, 0, -38, -8, 0, 0, 0);
		KF_Throw_LChest[0] = new KeyFrame(0, 0, -38, 8, 0, 0, 0);
		
		KF_Throw_WAIST[0] = new KeyFrame(0 , 0, -25, 0, 0, 0, 0);
		KF_Throw_WAIST[1] = new KeyFrame(10, 0, -22, 16, 0, 60, 0);
		KF_Throw_WAIST[2] = new KeyFrame(12, 0, -22, 16, 0, 60, 0);
		KF_Throw_WAIST[3] = new KeyFrame(15, 0, -24, -3.2F, 0, 0, 0);
		KF_Throw_WAIST[4] = new KeyFrame(17, 0, -22, -16, 0, -40, 0);
		KF_Throw_WAIST[5] = new KeyFrame(19, 0, -22, -16, 0, -40, 0);
		KF_Throw_WAIST[6] = new KeyFrame(29, 0, -25, 0, 0, 0, 0);
		
		KF_Throw_HIP[0] = new KeyFrame(0 , 0, -9, 0, 0, 90, 0);
		KF_Throw_HIP[1] = new KeyFrame(10, 0, -6, 16, 0, 150, 0);
		KF_Throw_HIP[2] = new KeyFrame(12, 0, -6, 16, 0, 150, 0);
		KF_Throw_HIP[3] = new KeyFrame(15, 0, -8, -3.2F, 0, 90, 0);
		KF_Throw_HIP[4] = new KeyFrame(17, 0, -6, -16, 0, 50, 0);
		KF_Throw_HIP[5] = new KeyFrame(19, 0, -6, -16, 0, 50, 0);
		KF_Throw_HIP[6] = new KeyFrame(29, 0, -9, 0, 0, 90, 0);
		
		KF_Throw_LFHip[0] = new KeyFrame(0, 5.5F, -9, -5.5F, 15, 0, 15);
		KF_Throw_RRHip[0] = new KeyFrame(0, -5.5F, -9, 5.5F, -15, 0, -15);
		KF_Throw_RFHip[0] = new KeyFrame(0, 5.52F, -9, 5.5F, -15, 0, 15);
		KF_Throw_LRHip[0] = new KeyFrame(0, -5.52F, -9, -5.5F, 15, 0, -15);
		
		KF_Throw_RLEG[0] = new KeyFrame(0 , 0, -11, -7, -20, 0, 0);
		KF_Throw_RLEG[1] = new KeyFrame(10, 0, -11, -7, -25, 0, 0);
		KF_Throw_RLEG[2] = new KeyFrame(15, 0, -11, -7, -25, 0, 0);
		KF_Throw_RLEG[3] = new KeyFrame(17, 0, -11, -7, -25, 25, -25);
		KF_Throw_RLEG[4] = new KeyFrame(19, 0, -11, -7, -25, 25, -25);
		KF_Throw_RLEG[5] = new KeyFrame(29, 0, -11, -7, -20, 0, 0);
		
		KF_Throw_RLeg1[0] = new KeyFrame(0 , 0.02F, 13.3F, -0.5F, 20, 0, 0);
		KF_Throw_RLeg1[1] = new KeyFrame(10, 0.02F, 13.3F, -0.5F, 5, 5.5F, 0); 
		KF_Throw_RLeg1[2] = new KeyFrame(12, 0.02F, 13.3F, -0.5F, 5, 5.5F, 0);
		KF_Throw_RLeg1[3] = new KeyFrame(15, 0.02F, 15.3F, -0.5F, 5, 5.5F, 0);
		KF_Throw_RLeg1[4] = new KeyFrame(19, 0.02F, 15.3F, -0.5F, 5, 5.5F, 0);
		KF_Throw_RLeg1[5] = new KeyFrame(29, 0.02F, 13.3F, -0.5F, 20, 0, 0);
		
		KF_Throw_RLeg2[0] = new KeyFrame(0 , 0.01F, 16, 0.01F, 0, 0, 0);
		KF_Throw_RLeg2[1] = new KeyFrame(10, 0.01F, 14, 0.01F, 15, 0, 0);
		KF_Throw_RLeg2[2] = new KeyFrame(12, 0.01F, 14, 0.01F, 15, 0, 0);
		KF_Throw_RLeg2[3] = new KeyFrame(15, 0.01F, 11, 0.01F, 15, 0, 41.74F);
		KF_Throw_RLeg2[4] = new KeyFrame(17, 0.01F, 12, 0.01F, 5, 0, 25);
		KF_Throw_RLeg2[5] = new KeyFrame(19, 0.01F, 12, 0.01F, 5, 0, 25);
		KF_Throw_RLeg2[6] = new KeyFrame(29, 0.01F, 16, 0.01F, 0, 0, 0);
		
		KF_Throw_LLEG[0] = new KeyFrame(0 , 0, -11, 7, 20, 0, 0);
		KF_Throw_LLEG[1] = new KeyFrame(10, 0, -11, 7, 20, -60, -20);
		KF_Throw_LLEG[2] = new KeyFrame(12, 0, -11, 7, 20, -60, -20);
		KF_Throw_LLEG[3] = new KeyFrame(15, 0, -11, 7, 25, -75, -30); 
		KF_Throw_LLEG[4] = new KeyFrame(17, 0, -11, 7, 30, -15, -10);
		KF_Throw_LLEG[5] = new KeyFrame(19, 0, -11, 7, 30, -15, -10);
		KF_Throw_LLEG[6] = new KeyFrame(29, 0, -11, 7, 20, 0, 0);
		
		KF_Throw_LLeg1[0] = new KeyFrame(0 , 0.02F, 13.3F, 0.5F, -20, 0, 0);
		KF_Throw_LLeg1[1] = new KeyFrame(10, 0.02F, 13.3F, 0.5F, -8, 10, -15);
		KF_Throw_LLeg1[2] = new KeyFrame(12, 0.02F, 13.3F, 0.5F, -8, 10, -15);
		KF_Throw_LLeg1[3] = new KeyFrame(15, 0.02F, 13.3F, 0.5F, 5, 0, 0);
		KF_Throw_LLeg1[4] = new KeyFrame(17, 0.02F, 13.3F, 0.5F, 5, 0, 0);
		KF_Throw_LLeg1[5] = new KeyFrame(19, 0.02F, 13.3F, 0.5F, 5, 0, 20);
		KF_Throw_LLeg1[6] = new KeyFrame(29, 0.02F, 13.3F, 0.5F, -20, 0, 0);
		
		KF_Throw_LLeg2[0] = new KeyFrame(0 , 0.01F, 16, 0.01F, 0, 0, 0);
		KF_Throw_LLeg2[1] = new KeyFrame(10, 0.01F, 13, 0.01F, 0, -15, 20);  
		KF_Throw_LLeg2[2] = new KeyFrame(12, 0.01F, 13, 0.01F, 0, -15, 20);
		KF_Throw_LLeg2[3] = new KeyFrame(15, 0.01F, 14, 0.01F, 0, 0, 10);
		KF_Throw_LLeg2[4] = new KeyFrame(19, 0.01F, 14, 0.01F, 0, 0, 10); 
		KF_Throw_LLeg2[5] = new KeyFrame(29, 0.01F, 16, 0.01F, 0, 0, 0);
	}
	
	public void Throw(int frame, float partialTick){
		moveParts(frame, HIP, KF_Throw_HIP, partialTick);
		moveParts(frame, WAIST, KF_Throw_WAIST, partialTick);
		moveParts(frame, LFHip, KF_Throw_LFHip, partialTick);
		moveParts(frame, RChest, KF_Throw_RChest, partialTick);
		moveParts(frame, LRShoulder2, KF_Throw_LRShoulder2, partialTick);
		moveParts(frame, RArm1, KF_Throw_RArm1, partialTick);
		moveParts(frame, LFShoulder1, KF_Throw_LFShoulder1, partialTick);
		moveParts(frame, LArm2, KF_Throw_LArm2, partialTick);
		moveParts(frame, LARM, KF_Throw_LARM, partialTick);
		moveParts(frame, RLeg2, KF_Throw_RLeg2, partialTick);
		moveParts(frame, RRShoulder2, KF_Throw_RRShoulder2, partialTick);
		moveParts(frame, RFShoulder1, KF_Throw_RFShoulder1, partialTick);
		moveParts(frame, RRHip, KF_Throw_RRHip, partialTick);
		moveParts(frame, LLEG, KF_Throw_LLEG, partialTick);
		moveParts(frame, LRShoulder1, KF_Throw_LRShoulder1, partialTick);
		moveParts(frame, RLeg1, KF_Throw_RLeg1, partialTick);
		moveParts(frame, LArm1, KF_Throw_LArm1, partialTick);
		moveParts(frame, LFShoulder2, KF_Throw_LFShoulder2, partialTick);
		moveParts(frame, LLeg2, KF_Throw_LLeg2, partialTick);
		moveParts(frame, RArm2, KF_Throw_RArm2, partialTick);
		moveParts(frame, LChest, KF_Throw_LChest, partialTick);
		moveParts(frame, RFShoulder2, KF_Throw_RFShoulder2, partialTick);
		moveParts(frame, LLeg1, KF_Throw_LLeg1, partialTick);
		moveParts(frame, RFHip, KF_Throw_RFHip, partialTick);
		moveParts(frame, HEAD, KF_Throw_HEAD, partialTick);
		moveParts(frame, RRShoulder1, KF_Throw_RRShoulder1, partialTick);
		moveParts(frame, RARM, KF_Throw_RARM, partialTick);
		moveParts(frame, RLEG, KF_Throw_RLEG, partialTick);
		moveParts(frame, LRHip, KF_Throw_LRHip, partialTick);
	}

	/**
	 * Build Animation
	 */
	static final KeyFrame[] KF_Build_HEAD = new KeyFrame[3];
	static final KeyFrame[] KF_Build_LRShoulder2 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_LFShoulder1 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RRShoulder2 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RFShoulder1 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_LRShoulder1 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_LFShoulder2 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RFShoulder2 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RRShoulder1 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_LARM = new KeyFrame[4];
	static final KeyFrame[] KF_Build_LArm1 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_LArm2 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RARM = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RArm1 = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RArm2 = new KeyFrame[4];	
	static final KeyFrame[] KF_Build_RChest = new KeyFrame[4];
	static final KeyFrame[] KF_Build_LChest = new KeyFrame[4];
	static final KeyFrame[] KF_Build_WAIST = new KeyFrame[4];
	static final KeyFrame[] KF_Build_LFHip = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RRHip = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RFHip = new KeyFrame[4];
	static final KeyFrame[] KF_Build_LRHip = new KeyFrame[4];
	static final KeyFrame[] KF_Build_RLEG = new KeyFrame[3];
	static final KeyFrame[] KF_Build_RLeg1 = new KeyFrame[3];
	static final KeyFrame[] KF_Build_RLeg2 = new KeyFrame[3];
	static final KeyFrame[] KF_Build_LLEG = new KeyFrame[3];
	static final KeyFrame[] KF_Build_LLeg1 = new KeyFrame[3];
	static final KeyFrame[] KF_Build_LLeg2 = new KeyFrame[3];
	static final KeyFrame[] KF_Build_HIP = new KeyFrame[1];
	
	public void build_Build(){
		KF_Build_HIP[0] = new KeyFrame(0, 0, -9, 0, 0, 90, 0);
		
		KF_Build_LFHip[0] = new KeyFrame(0, 26.5F, 49, -51.5F, 140, -170, 112);
		KF_Build_LFHip[1] = new KeyFrame(10, 26.5F, 49, -51.5F, 140, -170, 112);
		KF_Build_LFHip[2] = new KeyFrame(30, 5.5F , -9, -5.5F , 15 ,    0, 15);
		KF_Build_LFHip[3] = new KeyFrame(90, 5.5F , -9, -5.5F , 15 ,    0, 15);
		
		KF_Build_RChest[0] = new KeyFrame(0 , 0,  60, -49, -222, 222, 111);
		KF_Build_RChest[1] = new KeyFrame(20, 0,  60, -49, -222, 222, 111);
		KF_Build_RChest[2] = new KeyFrame(40, 0, -38,  -8,    0,   0,   0);
		KF_Build_RChest[3] = new KeyFrame(90, 0, -38,  -8,    0,   0,   0);
		
		KF_Build_LRShoulder2[0] = new KeyFrame(0 , -55.22F,  60,  23, -200,  90,  50);
		KF_Build_LRShoulder2[1] = new KeyFrame(30, -55.22F,  60,  23, -200,  90,  50);
		KF_Build_LRShoulder2[2] = new KeyFrame(50,  -4.22F, -42,  18,  -30,   0, -15);
		KF_Build_LRShoulder2[3] = new KeyFrame(90,  -4.22F, -42,  18,  -30,   0, -15);
		
		KF_Build_RArm1[0] = new KeyFrame(0 , -63.98F, 93, 46.01F, 81, -91, -55);
		KF_Build_RArm1[1] = new KeyFrame(45, -63.98F, 93, 46.01F, 81, -91, -55);
		KF_Build_RArm1[2] = new KeyFrame(65,   0.02F, 12,  0.01F, 25,   0,   0);
		KF_Build_RArm1[3] = new KeyFrame(90,   0.02F, 12,  0.01F, 25,   0,   0);
		
		KF_Build_LFShoulder1[0] = new KeyFrame(0 , 32.52F,  50, 29.5F, 230, 170, 100);
		KF_Build_LFShoulder1[1] = new KeyFrame(25, 32.52F,  50, 29.5F, 230, 170, 100);
		KF_Build_LFShoulder1[2] = new KeyFrame(45,  5.52F, -47,  5.5F, -15,   0,  15);
		KF_Build_LFShoulder1[3] = new KeyFrame(90,  5.52F, -47,  5.5F, -15,   0,  15);
		
		KF_Build_LArm2[0] = new KeyFrame(0 , 49.02F, 87, 40.01F,  30, 88, 120);
		KF_Build_LArm2[1] = new KeyFrame(50, 49.02F, 87, 40.01F,  30, 88, 120);
		KF_Build_LArm2[2] = new KeyFrame(70,  0.02F, 12,  0.01F, -10,  0,   0);
		KF_Build_LArm2[3] = new KeyFrame(90,  0.02F, 12,  0.01F, -10,  0,   0);
		
		KF_Build_LARM[0] = new KeyFrame(0 , -52,  64, 55, 88, 220, 19);
		KF_Build_LARM[1] = new KeyFrame(35, -52,  64, 55, 88, 220, 19);
		KF_Build_LARM[2] = new KeyFrame(55,   0, -40, 21, 40,   0,  0);
		KF_Build_LARM[3] = new KeyFrame(90,   0, -40, 21, 40,   0,  0);
		
		KF_Build_RLeg2[0] = new KeyFrame(0 , -34.99F, 56, -10.99F, 56, -50, 200);
		KF_Build_RLeg2[1] = new KeyFrame(20,   0.01F, 16,   0.01F,  0,   0,   0);
		KF_Build_RLeg2[2] = new KeyFrame(90,   0.01F, 16,   0.01F,  0,   0,   0);
		
		KF_Build_RRShoulder2[0] = new KeyFrame(0 , -47.2F,  50, -33, -50, 200, 90);
		KF_Build_RRShoulder2[1] = new KeyFrame(30, -47.2F,  50, -33, -50, 200, 90);
		KF_Build_RRShoulder2[2] = new KeyFrame(50,  -4.2F, -42, -18,  30,   0, -15);
		KF_Build_RRShoulder2[3] = new KeyFrame(90,  -4.2F, -42, -18,  30,   0, -15);
		
		KF_Build_RFShoulder1[0] = new KeyFrame(0 , 59.5F,  50, -58.5F, 100, 200, -200);
		KF_Build_RFShoulder1[1] = new KeyFrame(25, 59.5F,  50, -58.5F, 100, 200, -200);
		KF_Build_RFShoulder1[2] = new KeyFrame(45,  5.5F, -47,  -5.5F,  15,   0,   15);
		KF_Build_RFShoulder1[3] = new KeyFrame(90,  5.5F, -47,  -5.5F,  15,   0,   15);
		
		KF_Build_RRHip[0] = new KeyFrame(0 , -41.5F, 53, 37.5F, 222, 111, 200);
		KF_Build_RRHip[1] = new KeyFrame(10, -41.5F, 53, 37.5F, 222, 111, 200); 
		KF_Build_RRHip[2] = new KeyFrame(30,  -5.5F, -9,  5.5F,  -15,  0, -15);
		KF_Build_RRHip[3] = new KeyFrame(90,  -5.5F, -9,  5.5F,  -15,  0, -15);
				
		KF_Build_LLEG[0] = new KeyFrame(0 , -20,  42, 20, 200, 200, 200);
		KF_Build_LLEG[1] = new KeyFrame(20,   0, -11,  7,  20,   0,   0);
		KF_Build_LLEG[2] = new KeyFrame(90,   0, -11,  7,  20,   0,   0);
		
		KF_Build_LRShoulder1[0] = new KeyFrame(0 , -15.5F,  55, 35.5F, 120, -200, 160);
		KF_Build_LRShoulder1[1] = new KeyFrame(25, -15.5F,  55, 35.5F, 120, -200, 160);
		KF_Build_LRShoulder1[2] = new KeyFrame(45,  -5.5F, -47,  5.5F, -15,    0, -15);
		KF_Build_LRShoulder1[3] = new KeyFrame(90,  -5.5F, -47,  5.5F, -15,    0, -15);
		
		KF_Build_RLeg1[0] = new KeyFrame(0 , 0.02F, 41.3F, -13.5F, 20, -60, 0);
		KF_Build_RLeg1[1] = new KeyFrame(20, 0.02F, 13.3F,  -0.5F, 20,   0, 0);
		KF_Build_RLeg1[2] = new KeyFrame(90, 0.02F, 13.3F,  -0.5F, 20,   0, 0);
		
		KF_Build_LArm1[0] = new KeyFrame(0 , -48.98F, 74, -59.99F,  80, 120, 200);
		KF_Build_LArm1[1] = new KeyFrame(46, -48.98F, 74, -59.99F,  80, 120, 200); 
		KF_Build_LArm1[2] = new KeyFrame(66,   0.02F, 12,   0.01F, -25,   0,   0);
		KF_Build_LArm1[3] = new KeyFrame(90,   0.02F, 12,   0.01F, -25,   0,   0);
		
		KF_Build_LFShoulder2[0] = new KeyFrame(0 , 45.21F,  56, 44, 160, 200, -200);
		KF_Build_LFShoulder2[1] = new KeyFrame(30, 45.21F,  56, 44, 160, 200, -200); 
		KF_Build_LFShoulder2[2] = new KeyFrame(50,  4.21F, -42, 18, -30,   0,   15);
		KF_Build_LFShoulder2[3] = new KeyFrame(90,  4.21F, -42, 18, -30,   0,   15);
		
		KF_Build_LLeg2[0] = new KeyFrame(0 , 22.01F, 48, -20.99F, 100, -100, 50);
		KF_Build_LLeg2[1] = new KeyFrame(20,  0.01F, 16,   0.01F,   0,    0,  0);
		KF_Build_LLeg2[2] = new KeyFrame(90,  0.01F, 16,   0.01F,   0,    0,  0);
		
		KF_Build_RArm2[0] = new KeyFrame(0 , 13.02F, 90, 0.01F, 200, 190, 55);
		KF_Build_RArm2[1] = new KeyFrame(48, 13.02F, 90, 0.01F, 200, 190, 55); 
		KF_Build_RArm2[2] = new KeyFrame(68,  0.02F, 12, 0.01F, 10,    0,  0);
		KF_Build_RArm2[3] = new KeyFrame(90,  0.02F, 12, 0.01F, 10,    0,  0);
		
		KF_Build_LChest[0] = new KeyFrame(0 , 0,  60, 63, 220, -170, 160);
		KF_Build_LChest[1] = new KeyFrame(20, 0,  60, 63, 220, -170, 160); 
		KF_Build_LChest[2] = new KeyFrame(40, 0, -38,  8,   0,    0,   0);
		KF_Build_LChest[3] = new KeyFrame(90, 0, -38,  8,   0,    0,   0);
		
		KF_Build_RFShoulder2[0] = new KeyFrame(0 , 38.2F,  60, -35, -90, 120, -60);
		KF_Build_RFShoulder2[1] = new KeyFrame(30, 38.2F,  60, -35, -90, 120, -60); 
		KF_Build_RFShoulder2[2] = new KeyFrame(50,  4.2F, -42, -18,  30,   0,  15);
		KF_Build_RFShoulder2[3] = new KeyFrame(90,  4.2F, -42, -18,  30,   0,  15);
		
		KF_Build_LLeg1[0] = new KeyFrame(0 , 12.02F, 38.3F, 13.5F, 100, 201, 0);
		KF_Build_LLeg1[1] = new KeyFrame(20,  0.02F, 13.3F,  0.5F, -20,   0, 0);
		KF_Build_LLeg1[2] = new KeyFrame(90,  0.02F, 13.3F,  0.5F, -20,   0, 0);
		
		KF_Build_RFHip[0] = new KeyFrame(0 , 27.52F, 50, 42.5F, -200, 70, -80);
		KF_Build_RFHip[1] = new KeyFrame(10, 27.52F, 50, 42.5F, -200, 70, -80); 
		KF_Build_RFHip[2] = new KeyFrame(30,  5.52F, -9,  5.5F,  -15,  0,  15);
		KF_Build_RFHip[3] = new KeyFrame(90,  5.52F, -9,  5.5F,  -15,  0,  15);
		
		KF_Build_HEAD[0] = new KeyFrame(0 , 70,  54, 0, 33, 48, 160);
		KF_Build_HEAD[1] = new KeyFrame(70, 70,  54, 0, 33, 48, 160);
		KF_Build_HEAD[2] = new KeyFrame(90,  0, -54, 0,  0,  0,   0);
		
		KF_Build_RRShoulder1[0] = new KeyFrame(0 ,   -40,   70, -43.5F, 200, 200,  90);
		KF_Build_RRShoulder1[1] = new KeyFrame(25,   -40,   70, -43.5F, 200, 200,  90); 
		KF_Build_RRShoulder1[2] = new KeyFrame(45, -5.52F, -47,  -5.5F,  15,   0, -15);
		KF_Build_RRShoulder1[3] = new KeyFrame(90, -5.52F, -47,  -5.5F,  15,   0, -15);
		
		KF_Build_RARM[0] = new KeyFrame(0 , -3,  51, -63, -101, 42, -28);
		KF_Build_RARM[1] = new KeyFrame(40, -3,  51, -63, -101, 42, -28);
		KF_Build_RARM[2] = new KeyFrame(60,  0, -40, -21,  -40,  0,   0);
		KF_Build_RARM[3] = new KeyFrame(90,  0, -40, -21,  -40,  0,   0);
		
		KF_Build_RLEG[0] = new KeyFrame(0 , 35,  63, -26, 100, 150, 10);
		KF_Build_RLEG[1] = new KeyFrame(20,  0, -11,  -7, -20,   0,  0);
		KF_Build_RLEG[2] = new KeyFrame(90,  0, -11,  -7, -20,   0,  0);
		
		KF_Build_LRHip[0] = new KeyFrame(0 , -40.52F, 53, -50.5F, 222, 111, 111);
		KF_Build_LRHip[1] = new KeyFrame(10, -40.52F, 53, -50.5F, 222, 111, 111);
		KF_Build_LRHip[2] = new KeyFrame(30,  -5.52F, -9,  -5.5F,  15,   0, -15);
		KF_Build_LRHip[3] = new KeyFrame(90,  -5.52F, -9,  -5.5F,  15,   0, -15);
		
		KF_Build_WAIST[0] = new KeyFrame(0 , 0,  46, 39, -25, 24, -22);
		KF_Build_WAIST[1] = new KeyFrame(15, 0,  46, 39, -25, 24, -22); 
		KF_Build_WAIST[2] = new KeyFrame(35, 0, -25,  0,   0,  0,   0);
		KF_Build_WAIST[3] = new KeyFrame(90, 0, -25,  0,   0,  0,   0);
	}
	
	public void Build(int frame, float partialTick){
		moveParts(frame, HIP, KF_Build_HIP, partialTick);
		moveParts(frame, WAIST, KF_Build_WAIST, partialTick);
		moveParts(frame, LFHip, KF_Build_LFHip, partialTick);
		moveParts(frame, RChest, KF_Build_RChest, partialTick);
		moveParts(frame, LRShoulder2, KF_Build_LRShoulder2, partialTick);
		moveParts(frame, RArm1, KF_Build_RArm1, partialTick);
		moveParts(frame, LFShoulder1, KF_Build_LFShoulder1, partialTick);
		moveParts(frame, LArm2, KF_Build_LArm2, partialTick);
		moveParts(frame, LARM, KF_Build_LARM, partialTick);
		moveParts(frame, RLeg2, KF_Build_RLeg2, partialTick);
		moveParts(frame, RRShoulder2, KF_Build_RRShoulder2, partialTick);
		moveParts(frame, RFShoulder1, KF_Build_RFShoulder1, partialTick);
		moveParts(frame, RRHip, KF_Build_RRHip, partialTick);
		moveParts(frame, LLEG, KF_Build_LLEG, partialTick);
		moveParts(frame, LRShoulder1, KF_Build_LRShoulder1, partialTick);
		moveParts(frame, RLeg1, KF_Build_RLeg1, partialTick);
		moveParts(frame, LArm1, KF_Build_LArm1, partialTick);
		moveParts(frame, LFShoulder2, KF_Build_LFShoulder2, partialTick);
		moveParts(frame, LLeg2, KF_Build_LLeg2, partialTick);
		moveParts(frame, RArm2, KF_Build_RArm2, partialTick);
		moveParts(frame, LChest, KF_Build_LChest, partialTick);
		moveParts(frame, RFShoulder2, KF_Build_RFShoulder2, partialTick);
		moveParts(frame, LLeg1, KF_Build_LLeg1, partialTick);
		moveParts(frame, RFHip, KF_Build_RFHip, partialTick);
		moveParts(frame, HEAD, KF_Build_HEAD, partialTick);
		moveParts(frame, RRShoulder1, KF_Build_RRShoulder1, partialTick);
		moveParts(frame, RARM, KF_Build_RARM, partialTick);
		moveParts(frame, RLEG, KF_Build_RLEG, partialTick);
		moveParts(frame, LRHip, KF_Build_LRHip, partialTick);
		
	}	
}
