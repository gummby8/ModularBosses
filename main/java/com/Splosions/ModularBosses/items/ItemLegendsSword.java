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
import com.Splosions.ModularBosses.proxy.ClientProxy;
import com.Splosions.ModularBosses.util.Schematic;
import com.Splosions.ModularBosses.world.PortalLandingWorldData;
import com.google.gson.JsonSyntaxException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.lang.reflect.*;



public class ItemLegendsSword extends ItemSword {

	
	
	public ItemLegendsSword(ToolMaterial material) {
		super(material);
	}
	 
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
    	if (worldIn.isRemote){
    	
    		ClientProxy.sobelShader();
    		
    	}


    	
        return itemStackIn;
        
    }

    
}
