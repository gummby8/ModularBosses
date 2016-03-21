package com.Splosions.ModularBosses.items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.Splosions.ModularBosses.client.entity.EntityCartographer;
import com.Splosions.ModularBosses.client.entity.EntityCustomFallingBlock;
import com.Splosions.ModularBosses.client.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.client.models.item.ModelLegendsSword;
import com.Splosions.ModularBosses.client.render.items.ModelItemLegendsSword;
import com.Splosions.ModularBosses.util.Schematic;
import com.Splosions.ModularBosses.world.PortalLandingWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
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
    	/**
    	if (!worldIn.isRemote){
    	Schematic HI = new Schematic("ForestDungeon.schematic", worldIn, playerIn.posX, playerIn.posY, playerIn.posZ);
    	System.out.println("Schematic?");
    	}
    	*/
    	
    	/**
    	try {
			readData("./schematics/derp.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
    	
    	
    	
    	/**
    	if (!worldIn.isRemote){
    		EntityCartographer cartographer = new EntityCartographer(worldIn, playerIn, playerIn.posX, playerIn.posY, playerIn.posZ);
    		worldIn.spawnEntityInWorld(cartographer);
		}
    	*/
    	if (!worldIn.isRemote){
    		
    	
    	 
    		PortalLandingWorldData roomData = (PortalLandingWorldData) worldIn.getPerWorldStorage().loadData(PortalLandingWorldData.class, "lobbyPortals");
    	if (roomData == null){
    		System.out.println("No LobbyPortals Tag found, creating one");
    		roomData = new PortalLandingWorldData("lobbyPortals");
    		worldIn.getPerWorldStorage().setData("lobbyPortals", roomData);
    	}

    	
    	
    	float x = (float)Math.round(playerIn.posX);
    	float y = (float)Math.round(playerIn.posY);
		float z = (float)Math.round(playerIn.posZ);
    	
		System.out.println("Player = " + "[" + x + "," + y + "," + z + "]");
		
		NBTTagCompound compound = new NBTTagCompound();
		
    	roomData.addPortalLanding(0, x, y, z);

    	roomData.markDirty();
    	
    	System.out.println(roomData.portalLandingList.size());

    	
    	}
    	
        return itemStackIn;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
	

}
