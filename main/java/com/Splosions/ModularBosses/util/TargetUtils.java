package com.Splosions.ModularBosses.util;

import java.util.List;
import java.util.Random;

import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.server.SetControlBlockMessagePacket;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class TargetUtils {



	/**
	 * Returns whether the target is in the seeker's field of view based on
	 * relative position
	 * 
	 * @param fov
	 *            seeker's field of view; a wider angle returns true more often
	 */
	public static final boolean isTargetInFrontOf(Entity seeker, Entity target, float fov) {
		// thanks again to Battlegear2 for the following code snippet
		double dx = target.posX - seeker.posX;
		double dz;
		for (dz = target.posZ - seeker.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random())
				* 0.01D) {
			dx = (Math.random() - Math.random()) * 0.01D;
		}
		while (seeker.rotationYaw > 360) {
			seeker.rotationYaw -= 360;
		}
		while (seeker.rotationYaw < -360) {
			seeker.rotationYaw += 360;
		}
		float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - seeker.rotationYaw;
		yaw = yaw - 90;
		while (yaw < -180) {
			yaw += 360;
		}
		while (yaw >= 180) {
			yaw -= 360;
		}
		return yaw < fov && yaw > -fov;
	}

	/*
	 * Gets an List of entities within an AABB
	 */
	public static final List getList(Entity entity, int width, int height) {
		return entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity,
				entity.getEntityBoundingBox().expand(width, height, width));
	}

	public static EntityPlayer findRandomVisablePlayer(Entity entity, int width, int height) {
		Random rn = new Random();
		List<EntityPlayer> players = entity.worldObj.getEntitiesWithinAABB(EntityPlayer.class,
				entity.getEntityBoundingBox().expand(width, height, width));
		if (players.size() > 0) {
			return (((EntityLivingBase) entity).canEntityBeSeen(players.get(rn.nextInt(players.size())))
					? players.get(rn.nextInt(players.size())) : null);
		} else {
			return null;
		}
	}

	/**
	 * Finds the closest player within 16 blocks to attack, or null if this
	 * Entity isn't interested in attacking (Animals, Spiders at day, peaceful
	 * PigZombies).
	 */
	public static EntityPlayer findNearestVisablePlayer(Entity entity, int dist) {
		EntityPlayer entityplayer = entity.worldObj.getClosestPlayerToEntity(entity, dist);
		return entityplayer != null && ((EntityLivingBase) entity).canEntityBeSeen(entityplayer) ? entityplayer : null;
	}

	

}
