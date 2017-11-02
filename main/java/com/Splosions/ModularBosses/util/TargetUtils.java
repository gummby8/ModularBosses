package com.Splosions.ModularBosses.util;

import java.util.List;
import java.util.Random;

import com.Splosions.ModularBosses.ModularBosses;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TargetUtils {

	public static void tellPlayer(String msg) {
		try {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "MB: " + EnumChatFormatting.GOLD + msg));
		} catch (Exception e) {
			ModularBosses.logger.debug("Tried to send a chat to player before there was a player");
		}

	}
	
	public static void tellPlayersInList(List list, String msg) {
		try {
			for (int i = 0; i < list.size(); ++i) {
				if (list.get(i) instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer) list.get(i);
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "MB: " + EnumChatFormatting.GOLD + msg));
				}
			}
		} catch (Exception e) {
			ModularBosses.logger.debug("Tried to send a chat to player before there was a player");
		}

	}

	public static void betaMsg(Entity entity) {
		if (entity.worldObj.isRemote && entity.ticksExisted == 1) {
			tellPlayer("This monster is still a work in progress");
		}
	}

	/**
	 * Returns a random number between the two numbers specified
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRanNum(int min, int max) {
		Random r = new Random();
		if (min >= max) {
			ModularBosses.logger.warn("Someone flipped a min and max value");
			return r.nextInt((min - max) + 1) + max;
		} else if (min == max){
			return min;
		} else {
			return r.nextInt((max - min) + 1) + min;	
		}
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
		for (dz = target.posZ - seeker.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
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
		return entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(width, height, width));
	}

	public static final List getSpecificList(Entity entity, Class targetClass, int width, int height) {
		return entity.worldObj.getEntitiesWithinAABB(targetClass, entity.getEntityBoundingBox().expand(width, height, width));
	}

	public static EntityPlayer findRandomVisablePlayer(Entity entity, int width, int height) {
		Random rn = new Random();
		List<EntityPlayer> players = entity.worldObj.getEntitiesWithinAABB(EntityPlayer.class, entity.getEntityBoundingBox().expand(width, height, width));
		if (players.size() > 0) {
			return (((EntityLivingBase) entity).canEntityBeSeen(players.get(rn.nextInt(players.size()))) ? players.get(rn.nextInt(players.size())) : null);
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
	 * Adds the stack to the player's inventory or, failing that, drops it as an
	 * EntityItem
	 */
	public static void addItemToInventory(EntityPlayer player, ItemStack stack) {
		if (!player.inventory.addItemStackToInventory(stack)) {
			player.dropPlayerItemWithRandomChoice(stack, false);
		}
	}


	/**
	 * Searches for an instance of the block target between two block pos
	 */
	public static boolean isBlockPresentPos(World worldIn, Block blockTarget, BlockPos pos1, BlockPos pos2) {
				List<BlockPos> blocks = Lists.newArrayList(BlockPos.getAllInBox(pos1, pos2));
			for (BlockPos pos : blocks) {
				if(worldIn.getBlockState(pos).getBlock() == blockTarget){
					return true;
				}
			}
		return false; 
	}
	
	/**
	 * Rolls to drop loot
	 * % Drop Chance | Quantity | Item Name
	 * @param lootList
	 * @param ent
	 */
	public static void dropLoot(Entity ent, String[] lootList) {
		try {
			if (lootList.length > 0) {
				for (String string : lootList) {
					String[] split = string.split("\\|");
					String[] item = split[2].split("\\:");

					int qty = Integer.parseInt(split[1]);

					int chance = Integer.parseInt(split[0]);
					chance = (chance > 100) ? 100 : chance;
					int roll = getRanNum(1, 100);
					if (roll <= chance) {
						ent.dropItem(GameRegistry.findItem(item[0], item[1]), qty);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void dropExp(Entity ent, int ammount){
        int j;
		while (ammount > 0)
        {
            j = EntityXPOrb.getXPSplit(ammount);
            ammount -= j;
            ent.worldObj.spawnEntityInWorld(new EntityXPOrb(ent.worldObj, ent.posX, ent.posY, ent.posZ, j));
        }
	}
	
	
	public static int distToFloor(Entity ent, double x, double y, double z){
        BlockPos blockpos = new BlockPos(x, y, z);
        int count = 0;
		while (!ent.worldObj.getBlockState(blockpos).getBlock().getMaterial().blocksMovement()) {
			blockpos = blockpos.down();
			count++;
		}
		return count;
	}
	
    public static boolean canPosBeSeen(Entity ent, double x, double y, double z)
    {
        return ent.worldObj.rayTraceBlocks(new Vec3(ent.posX, ent.posY, ent.posZ), new Vec3(x, y, z)) == null;
    }

}
