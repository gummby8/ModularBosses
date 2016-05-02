package com.Splosions.ModularBosses.items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.Splosions.ModularBosses.client.ISwapModel;
import com.Splosions.ModularBosses.client.render.items.RenderItemScythe;
import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.google.common.collect.Lists;

import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;





public class ItemScythe extends BaseModSword implements ISwapModel {
	
	

	 
    public ItemScythe(ToolMaterial material) {
		super(material);
	}

	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {

    		EntityScythe projectile = new EntityScythe(playerIn.worldObj, playerIn, playerIn, 0, 0, 0, 1, 0, 1, 2);
			if (!playerIn.worldObj.isRemote) {
				playerIn.worldObj.spawnEntityInWorld(projectile);
			}
    		
    	


    	
        return itemStackIn;
        
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
