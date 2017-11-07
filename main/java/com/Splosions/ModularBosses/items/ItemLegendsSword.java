package com.Splosions.ModularBosses.items;

import java.util.Collection;
import java.util.List;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.client.ISwapModel;
import com.Splosions.ModularBosses.client.render.items.RenderItemLegendsSword;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLegendsSword extends BaseModSword implements ISwapModel {

	public ItemLegendsSword(ToolMaterial material) {
		super(material);
	}

	
	
	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemStackIn, World world, EntityPlayer playerIn) {
		System.out.println("Remember to fix the if in legends sword");
		
        return itemStackIn;
	}
	
	
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

	/**
	 * Override to add custom weapon damage field rather than vanilla ItemSword's field
	 */
	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Weapon modifier", Config.legendsSwordDmg, 0));
		return multimap;
	}
    
	@Override
	@SideOnly(Side.CLIENT)
	public Collection<ModelResourceLocation> getDefaultResources() {
		List<ModelResourceLocation> resources = Lists.newArrayList();

		resources.add(new ModelResourceLocation("mb:Legends_Sword", "inventory"));

		return resources;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends IBakedModel> getNewModel() {
		return RenderItemLegendsSword.class;
	}

}
