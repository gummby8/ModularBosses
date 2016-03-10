package com.Splosions.ModularBosses.items;

import java.util.Collection;

import com.Splosions.ModularBosses.client.models.item.ModelLegendsSword;
import com.Splosions.ModularBosses.client.render.items.ModelItemLegendsSword;
import com.Splosions.ModularBosses.util.Schematic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ItemLegendsSword extends ItemSword {
	
	
	
	public ItemLegendsSword(ToolMaterial material) {
		super(material);
	}
	
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
    	System.out.println(playerIn.posX);
    	Schematic HI = new Schematic("ForestDungeon.schematic");
    	HI.generate(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ);
        return itemStackIn;
    }
	

}
