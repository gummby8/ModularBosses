package com.Splosions.ModularBosses.client.models;

import net.minecraft.client.model.ModelRenderer;

public class KeyFrame {
	
	public int frame;
	public float posX;
	public float posY;
	public float posZ;
	public float rotX;
	public float rotY;
	public float rotZ;
	

	public KeyFrame(int frame, float posX, float posY, float posZ, float rotX, float rotY, float rotZ){
		 this.frame = frame;
		 this.posX = posX;
		 this.posY = posY;
		 this.posZ = posZ;
		 this.rotX = rotX;
		 this.rotY = rotY;
		 this.rotZ = rotZ;
	}
	
	public void setVars(int frame, float posX, float posY, float posZ, float rotX, float rotY, float rotZ){
		 this.frame = frame;
		 this.posX = posX;
		 this.posY = posY;
		 this.posZ = posZ;
		 this.rotX = rotX;
		 this.rotY = rotY;
		 this.rotZ = rotZ;
	}
	
	
	
	
	

	/**
	public float getValue(int frame){
		if (move){
			if (frame > startFrame && frame < endFrame){
				int frameCount = frame - startFrame; 
				return startValue + (totalValue / duration * frameCount);
			} else 
			if (frame <= startFrame){
				return startValue; 
			} else 
			if (frame >= endFrame){
				return endValue;
			}
		}		
		return startValue;
	}
	*/
	
	

}
