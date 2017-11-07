package com.Splosions.ModularBosses.items;

import java.util.Collection;
import java.util.List;

import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.client.ISwapModel;
import com.Splosions.ModularBosses.client.render.items.RenderItemBait;
import com.Splosions.ModularBosses.entity.projectile.EntityBait;
import com.google.common.collect.Lists;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBait extends BaseModItem implements ISwapModel {

	public ItemBait(ToolMaterial material) {
		setCreativeTab(MBCreativeTabs.tabTools);
		setMaxStackSize(1);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			Entity projectile = new EntityBait(world, player);
			world.spawnEntityInWorld(projectile);
			if (!player.capabilities.isCreativeMode) {
				--stack.stackSize;
			}
		}

		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Collection<ModelResourceLocation> getDefaultResources() {
		List<ModelResourceLocation> resources = Lists.newArrayList();
		resources.add(new ModelResourceLocation("mb:itemBait", "inventory"));
		return resources;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends IBakedModel> getNewModel() {
		return RenderItemBait.class;
	}
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)	{
	list.add("Smelly bait to lure out Sand Worms");
	}
	
}
