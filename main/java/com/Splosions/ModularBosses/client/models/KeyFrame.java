package com.Splosions.ModularBosses.client.models;

public class KeyFrame {
	public Boolean move;
	public int startFrame;
	public int endFrame;
	
	public int duration;
	public float startValue;
	public float endValue;
	public float totalValue;
	
	
	KeyFrame(Boolean shouldMove, int firstFrame, int lastFrame, float startPos, float endPos){
		move = shouldMove;
		startFrame = firstFrame;
		endFrame = lastFrame;
		duration = endFrame - startFrame;
		startValue = startPos;
		endValue = endPos;
		totalValue = endValue - startValue;
		
	}
	
	
	KeyFrame(float position){
		move = false;
		startValue = position;
	}	
	
	

	
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
	
	
	

}
