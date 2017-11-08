package com.Splosions.ModularBosses.items.dispenser;

import com.Splosions.ModularBosses.items.ItemCustomEgg;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;


/**
 * 
 * Dispenser behavior for ItemCustomEgg class and sub-classes
 *
 */
public class BehaviorDispenseCustomMobEgg extends BehaviorDefaultDispenseItem {
	@Override
	public ItemStack dispenseStack(IBlockSource block, ItemStack stack) {
		EnumFacing facing = block.getBlockState().getValue(BlockDispenser.FACING);
		double dx = block.getX() + facing.getFrontOffsetX();
        double dy = ((float) block.getBlockPos().getY() + 0.2F);
        double dz= block.getZ() + facing.getFrontOffsetZ();
		Entity entity = ((ItemCustomEgg) stack.getItem()).spawnCreature(block.getWorld(), stack.getItemDamage(), dx, dy, dz);
		if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
			((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
		}
		stack.splitStack(1);
		return stack;
	}
}