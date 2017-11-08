package com.Splosions.ModularBosses.items;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.ISwapModel;
import com.Splosions.ModularBosses.client.render.items.RenderItemBait;
import com.Splosions.ModularBosses.entity.projectile.EntityBait;
import com.google.common.collect.Lists;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBait extends BaseModItem implements ISwapModel {

	public ItemBait(ToolMaterial material) {
		setCreativeTab(ModularBosses.tabTools);
		setMaxStackSize(1);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
	
		if (!playerIn.capabilities.isCreativeMode) {
			stack.shrink(1);
		}
		
		if (!worldIn.isRemote) {
			Entity projectile = new EntityBait(worldIn, playerIn);
			worldIn.spawnEntity(projectile);
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
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
	
	
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    super.addInformation(stack, worldIn, tooltip, flagIn);
	tooltip.add("Smelly bait to lure out Sand Worms");
	}
	
}
