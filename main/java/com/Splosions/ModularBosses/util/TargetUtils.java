package com.Splosions.ModularBosses.util;

import java.util.List;
import java.util.Random;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.server.SetControlBlockMessagePacket;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.collection.generic.Sizing;

public class TargetUtils {


	public static void tellPlayer(String msg){
		try{
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "MB: " + EnumChatFormatting.GOLD + msg));	
		} catch (Exception e){
			ModularBosses.logger.debug("Tried to send a chat to player before there was a player");
		}
		
	}
	
	
	public static void betaMsg(Entity entity){
		if (entity.worldObj.isRemote && entity.ticksExisted == 1){
		tellPlayer("This monster is still a work in progress");
		}
	}
	
	/**
	 * Returns a random number between the two numbers specified
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRanNum(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean playerColided(Entity ent) {
		List list = ent.worldObj.getEntitiesWithinAABB(EntityPlayer.class, ent.getEntityBoundingBox());
		return list.isEmpty();
	}

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

	public static final List getSpecificList(Entity entity, Class targetClass, int width, int height) {
		return entity.worldObj.getEntitiesWithinAABB(targetClass, entity.getEntityBoundingBox().expand(width, height, width));
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
	 * Finds the closest player within X blocks to attack, or null if this
	 * Entity isn't interested in attacking (Animals, Spiders at day, peaceful
	 * PigZombies).
	 */
	public static EntityPlayer findNearestVisablePlayer(Entity entity, int dist) {
		EntityPlayer entityplayer = entity.worldObj.getClosestPlayerToEntity(entity, dist);
		return entityplayer != null && ((EntityLivingBase) entity).canEntityBeSeen(entityplayer) ? entityplayer : null;
	}

	
	/**
	 * Adds the stack to the player's inventory or, failing that, drops it as an EntityItem
	 */
	public static void addItemToInventory(EntityPlayer player, ItemStack stack) {
		if (!player.inventory.addItemStackToInventory(stack)) {
			player.dropPlayerItemWithRandomChoice(stack, false);
		}
	}
	

	
    /**
     * Searches AABB for an instance of the block target
     */
    public static boolean isBlockPresent(World worldIn, AxisAlignedBB aabb, Block blockTarget)
    {
        int i = MathHelper.floor_double(aabb.minX);
        int j = MathHelper.floor_double(aabb.minY);
        int k = MathHelper.floor_double(aabb.minZ);
        int l = MathHelper.floor_double(aabb.maxX);
        int i1 = MathHelper.floor_double(aabb.maxY);
        int j1 = MathHelper.floor_double(aabb.maxZ);
        boolean flag = false;
        

        for (int k1 = i; k1 <= l; ++k1)
        {
            for (int l1 = j; l1 <= i1; ++l1)
            {
                for (int i2 = k; i2 <= j1; ++i2)
                {
 
                    if (worldIn.getBlockState(new BlockPos(k1, l1, i2)).getBlock() == blockTarget)
                    {
                    	return true;
                    }
                }
            }
        }
        return false;
    }
	
}
