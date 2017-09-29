package com.Splosions.ModularBosses.client.models;

public class FakeModelRenderer {

	public double posX;
	public double posY;
	public double posZ;
	
	public float yaw;
	public float pitch;
	
	public float rotateAngleX;
	public float rotateAngleY;
	public float rotateAngleZ;

	public float rotationPointX;
	public float rotationPointY;
	public float rotationPointZ;

	public float rotateAngleXDist;
	public float rotateAngleYDist;
	public float rotateAngleZDist;

	public float rotationPointXDist;
	public float rotationPointYDist;
	public float rotationPointZDist;

	/**
	 * Quickly set the angles and rotation point variables of a fake modelRenderer
	 * @param ax
	 * @param ay
	 * @param az
	 * @param px
	 * @param py
	 * @param pz
	 */
	public void setModelVars(float ax, float ay, float az, float px, float py, float pz) {
		this.rotateAngleX = (float) Math.toRadians(ax);
		this.rotateAngleY = (float) Math.toRadians(ay);
		this.rotateAngleZ = (float) Math.toRadians(az);
		this.rotationPointX = px;
		this.rotationPointY = py;
		this.rotationPointZ = pz;
	}
	
	/**
	 * Quickly set the postition and rotation angles of a fake entity
	 * @param x
	 * @param y
	 * @param z
	 * @param yaw
	 * @param pitch
	 */
	public void setEntityVars(double x, double y, double z, float yaw, float pitch){
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
}