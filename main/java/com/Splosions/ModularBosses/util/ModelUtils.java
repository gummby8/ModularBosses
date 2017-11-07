package com.Splosions.ModularBosses.util;

import com.Splosions.ModularBosses.client.models.FakeModelRenderer;
import com.Splosions.ModularBosses.client.models.KeyFrame;

import net.minecraft.client.model.ModelRenderer;

public class ModelUtils {


	public static void setPiece(ModelRenderer targetPart, float ax, float ay, float az, float px, float py, float pz) {
		targetPart.rotateAngleX = (float) Math.toRadians(ax);
		targetPart.rotateAngleY = (float) Math.toRadians(ay);
		targetPart.rotateAngleZ = (float) Math.toRadians(az);
		
		targetPart.rotationPointX = px;
		targetPart.rotationPointY = py;
		targetPart.rotationPointZ = pz;
	}
	
	
	public static void teleportPiece(ModelRenderer targetPart, FakeModelRenderer destinationPart){
		targetPart.rotateAngleX = destinationPart.rotateAngleX;
		targetPart.rotateAngleY = destinationPart.rotateAngleY;
		targetPart.rotateAngleZ = destinationPart.rotateAngleZ;
		
		targetPart.rotationPointX = destinationPart.rotationPointX;
		targetPart.rotationPointY = destinationPart.rotationPointY;
		targetPart.rotationPointZ = destinationPart.rotationPointZ;
	}
	
	public static void movePieceOverTime(ModelRenderer targetPart, FakeModelRenderer destinationPart, int ticks){
		
		destinationPart.rotateAngleXDist = (destinationPart.rotateAngleXDist == 0)? targetPart.rotateAngleX - destinationPart.rotateAngleX : destinationPart.rotateAngleXDist;  
		targetPart.rotateAngleX = movePart(targetPart.rotateAngleX, destinationPart.rotateAngleX, destinationPart.rotateAngleXDist / ticks);
		
		destinationPart.rotateAngleYDist = (destinationPart.rotateAngleYDist == 0)? targetPart.rotateAngleY - destinationPart.rotateAngleY : destinationPart.rotateAngleYDist;
		targetPart.rotateAngleY = movePart(targetPart.rotateAngleY, destinationPart.rotateAngleY, destinationPart.rotateAngleYDist / ticks);
		
		destinationPart.rotateAngleZDist = (destinationPart.rotateAngleZDist == 0)? targetPart.rotateAngleZ - destinationPart.rotateAngleZ : destinationPart.rotateAngleZDist;
		targetPart.rotateAngleZ = movePart(targetPart.rotateAngleZ, destinationPart.rotateAngleZ, destinationPart.rotateAngleZDist / ticks);
		
		destinationPart.rotationPointXDist = (destinationPart.rotationPointXDist == 0)? targetPart.rotationPointX - destinationPart.rotationPointX : destinationPart.rotationPointXDist;
		targetPart.rotationPointX = movePart(targetPart.rotationPointX, destinationPart.rotationPointX, destinationPart.rotationPointXDist / ticks);
		
		destinationPart.rotationPointYDist = (destinationPart.rotationPointYDist == 0)? targetPart.rotationPointY - destinationPart.rotationPointY : destinationPart.rotationPointYDist;
		targetPart.rotationPointY = movePart(targetPart.rotationPointY, destinationPart.rotationPointY, destinationPart.rotationPointYDist / ticks);
		
		destinationPart.rotationPointZDist = (destinationPart.rotationPointZDist == 0)? targetPart.rotationPointZ - destinationPart.rotationPointZ : destinationPart.rotationPointZDist;
		targetPart.rotationPointZ = movePart(targetPart.rotationPointZ, destinationPart.rotationPointZ, destinationPart.rotationPointZDist / ticks);
	}
	
	public static float movePart(float part, float destination, float speed){
		speed = (speed < 0)? -speed : speed;
		
		if(part < destination) {
			part += speed;
		} else if (part > destination + speed) {
			part -= speed;
		} else {
			part = destination;
		}
		return part;
	}
	
	public static void movePiecePos(ModelRenderer targetPart, FakeModelRenderer destinationPart, float speed){
		targetPart.rotationPointX += ((destinationPart.rotationPointX - targetPart.rotationPointX) / speed);
		targetPart.rotationPointY += ((destinationPart.rotationPointY - targetPart.rotationPointY) / speed);
		targetPart.rotationPointZ += ((destinationPart.rotationPointZ - targetPart.rotationPointZ) / speed);
	}
	
	public static void movePieceAng(ModelRenderer targetPart, FakeModelRenderer destinationPart, float speed){
		targetPart.rotateAngleX += ((destinationPart.rotateAngleX - targetPart.rotateAngleX) / speed);
		targetPart.rotateAngleY += ((destinationPart.rotateAngleY - targetPart.rotateAngleY) / speed);
		targetPart.rotateAngleZ += ((destinationPart.rotateAngleZ - targetPart.rotateAngleZ) / speed);
	}
	
	
	public float toRadians(float degrees){
		return degrees * 0.0174533F;
	}
	
	
	public static void moveParts(int frame, ModelRenderer part, KeyFrame[] keyArray, float partialTick){
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
	
	public static int getKeyFrameNum(int frame, KeyFrame[] keyArray){
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

}
