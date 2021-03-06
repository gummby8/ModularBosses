package com.Splosions.ModularBosses.items;

import java.util.Collection;
import java.util.List;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.client.ISwapModel;
import com.Splosions.ModularBosses.client.render.items.RenderItemScythe;
import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemScythe extends BaseModItem implements ISwapModel {
	
	

	public ItemScythe(ToolMaterial material) {
		setCreativeTab(MBCreativeTabs.tabTools);
		setMaxStackSize(1);
	}


	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		itemStackIn.setRepairCost(itemStackIn.getRepairCost() + 1);
		playerIn.swingItem();
		if (!worldIn.isRemote) {
			EntityScythe projectile = new EntityScythe(playerIn.worldObj, playerIn, playerIn, 0, 0, 0, 1, 0, 1, 2, 30, Config.tattersScytheThrowDmg).setInvStack(playerIn.inventory.currentItem);
			playerIn.worldObj.spawnEntityInWorld(projectile);
		}
		playerIn.setCurrentItemOrArmor(0, null);
		return itemStackIn;
	}
	
	
	
	/**
	 * Override to add custom weapon damage field rather than vanilla ItemSword's field
	 */
	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Weapon modifier", Config.tattersScytheDmg, 0));
		return multimap;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Collection<ModelResourceLocation> getDefaultResources() {
		List<ModelResourceLocation> resources = Lists.newArrayList();
		resources.add(new ModelResourceLocation("mb:itemScythe", "inventory"));
		return resources;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends IBakedModel> getNewModel() {
		return RenderItemScythe.class;
	}

}
