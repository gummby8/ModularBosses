package com.Splosions.ModularBosses.client.models;

public class FakeModelRenderer {
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
	 * QUickly set the angles and rotations point variables
	 * @param ax
	 * @param ay
	 * @param az
	 * @param px
	 * @param py
	 * @param pz
	 */
	public void set(float ax, float ay, float az, float px, float py, float pz) {
		this.rotateAngleX = (float) Math.toRadians(ax);
		this.rotateAngleY = (float) Math.toRadians(ay);
		this.rotateAngleZ = (float) Math.toRadians(az);
		this.rotationPointX = px;
		this.rotationPointY = py;
		this.rotationPointZ = pz;
	}
}
