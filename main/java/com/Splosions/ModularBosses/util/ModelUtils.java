package com.Splosions.ModularBosses.util;

import com.Splosions.ModularBosses.client.models.FakeModelRenderer;

import net.minecraft.client.model.ModelRenderer;

public class ModelUtils {

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

}
