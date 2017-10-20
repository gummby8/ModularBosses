package com.Splosions.ModularBosses.items;

import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNote extends BaseModItem {

	public int textureID;
	
	public ItemNote(ToolMaterial material) {
		setCreativeTab(MBCreativeTabs.tabTools);
		setMaxStackSize(1);
		setMaxDamage(100);
		
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote && stack.getItemDamage() == 0){
			stack.setItemDamage(TargetUtils.getRanNum(1, 3));
		}
		
	}
}
