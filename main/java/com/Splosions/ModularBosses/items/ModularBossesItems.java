package com.Splosions.ModularBosses.items;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;

import net.minecraft.block.BlockDispenser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;



public class ModularBossesItems {
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	//================ TOOLS TAB ================//
	public static Item 
	itemScythe,
	itemLegendsBow,
	itemLegendsSword,
	itemBait,
	itemNote;
	
	
	
	//================ SPAWN EGGS TAB ================//
	public static Item
	spawn_egg;
	
	
	//================ NO TAB ================//
	public static Item
	slimeblob;
	
	
	public static void init() {
		
		itemScythe = new ItemScythe(ToolMaterial.DIAMOND).setUnlocalizedName("itemScythe");
		itemLegendsBow = new ItemLegendsBow(ToolMaterial.DIAMOND).setUnlocalizedName("Legends_Bow");
		itemLegendsSword = new ItemLegendsSword(ToolMaterial.DIAMOND).setUnlocalizedName("Legends_Sword");
		itemBait = new ItemBait(ToolMaterial.WOOD).setUnlocalizedName("itemBait");
		itemNote = new ItemNote(ToolMaterial.DIAMOND).setUnlocalizedName("itemNote");
	
		slimeblob = new Item().setUnlocalizedName("slimeblob").setMaxStackSize(16); //.setTextureName("ModularBosses:SlimeBlob")
		spawn_egg = new ItemCustomEgg().setUnlocalizedName("spawn_egg");
	}


	public static void registerItems() {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
