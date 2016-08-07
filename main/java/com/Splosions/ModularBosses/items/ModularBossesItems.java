package com.Splosions.ModularBosses.items;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;



public class ModularBossesItems {
	
	
	
	/** Map Item to internal ID index for Creative Tab comparator sorting to force even old saves to have correct order */
	private static final Map<Item, Integer> itemList = new HashMap<Item, Integer>(256);
	private static int sortId = 0;
	/** List of items added by other mods that are scheduled to have comparator mappings added */
	private static final List<Item> addonItems = new ArrayList<Item>();
	private static Comparator<Item> itemComparator = new Comparator<Item>() {
		@Override
		public int compare(Item a, Item b) {
			if (itemList.containsKey(a) && itemList.containsKey(b)) {
				return itemList.get(a) - itemList.get(b);
			} else {
				ModularBosses.logger.warn("A mod item " + a.getUnlocalizedName() + " or " + b.getUnlocalizedName() + " is missing a comparator mapping");
				return GameData.getItemRegistry().getId(a) - GameData.getItemRegistry().getId(b);
			}
		}
	};

	
	
	
	
	
	
	
	
	
	
	
	
	//================ TOOLS TAB ================//
	public static Item 
	itemScythe,
	itemLegendsBow,
	itemLegendsSword;
	
	
	
	//================ SPAWN EGGS TAB ================//
	public static Item
	spawn_egg;
	
	
	//================ NO TAB ================//
	public static Item
	slimeblob;
	
	
	public static void init() {
		
		itemScythe = new ItemScythe(ToolMaterial.EMERALD).setUnlocalizedName("itemScythe");
		itemLegendsBow = new ItemLegendsBow(ToolMaterial.EMERALD).setUnlocalizedName("Legends_Bow");
		itemLegendsSword = new ItemLegendsSword(ToolMaterial.EMERALD).setUnlocalizedName("Legends_Sword");
		 
		 slimeblob = new Item().setUnlocalizedName("slimeblob").setMaxStackSize(16); //.setTextureName("ModularBosses:SlimeBlob")
		 
		 spawn_egg = new ItemCustomEgg().setUnlocalizedName("spawn_egg");
	}

	
	/**
	 * Actually adds the item comparator mapping
	 */
	private static void registerItemComparatorMapping(Item item) {
		if (itemList.containsKey(item)) {
			ModularBosses.logger.warn("Item already has a comparator mapping: " + (item == null ? "NULL" : item.getUnlocalizedName()));
		} else {
			itemList.put(item, sortId++);
		}
	}

	public static void registerItems() {
		try {
			for (Field f: ModularBossesItems.class.getFields()) {
				if (Item.class.isAssignableFrom(f.getType())) {
					Item item = (Item) f.get(null);
					if (item != null) {
						
						ModularBossesItems.registerItemComparatorMapping(item);
						String name = item.getUnlocalizedName();
						GameRegistry.registerItem(item, name.substring(name.lastIndexOf(".") + 1));
						if (item instanceof ICustomDispenserBehavior) {
							BlockDispenser.dispenseBehaviorRegistry.putObject(item, ((ICustomDispenserBehavior) item).getNewDispenserBehavior());
						}
					}
				}
			}
		} catch(Exception e) {
			ModularBosses.logger.warn("Caught exception while registering items: " + e.toString());
			e.printStackTrace();
		}
	}
	
	
	@SideOnly(Side.CLIENT)
	public static void registerRenders(){
		registerRender(slimeblob);
	}
	
	
	public static void registerRender(Item item) {
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));

	}
	
	/**
	 * Registers an ItemBlock to the item sorter for creative tabs sorting
	 */
	public static void registerItemBlock(Item block) {
		if (block instanceof ItemBlock) {
			ModularBossesItems.registerItemComparatorMapping(block);
			
		} else {
			ModularBosses.logger.warn("Tried to register a non-ItemBlock item for " + (block == null ? "NULL" : block.getUnlocalizedName()));
		}
	}
	
	
}
