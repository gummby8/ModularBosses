package com.Splosions.ModularBosses.blocks;

import java.lang.reflect.Field;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityPortalBlock;
import com.Splosions.ModularBosses.items.ItemModBlock;
import com.Splosions.ModularBosses.items.ModularBossesItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ModularBossesBlocks
{
	public static Block
	controlBlock,
	portalBlock;
	
	/**
	 * Call during FMLPreInitializationEvent to initialize and register all blocks
	 */
	public static void preInit() {
		
		// NOTE: pass getUnlocalizedString WITHOUT 'tile.' or blockstate=>model will get confused
		// NOTE: new Object[]{args...} is required for vararg constructor invocation via Reflection

		
		
		controlBlock = new BlockControlBlock(Material.rock).setUnlocalizedName("control_block");
		GameRegistry.registerBlock(controlBlock, ItemModBlock.class, controlBlock.getUnlocalizedName().substring(5));
		GameRegistry.registerTileEntity(TileEntityControlBlock.class, Reference.MOD_ID + ":tileEntityControlBlock");
		
		portalBlock = new BlockPortalBlock(Material.barrier).setLightLevel(1).setUnlocalizedName("portal_block");
		GameRegistry.registerBlock(portalBlock, ItemModBlock.class, portalBlock.getUnlocalizedName().substring(5));
		GameRegistry.registerTileEntity(TileEntityPortalBlock.class, Reference.MOD_ID + ":tileEntityPortalBlock");
		
		
		// register block items for creative tab comparator sorting:
		try {
			for (Field f: ModularBossesBlocks.class.getFields()) {
				if (Block.class.isAssignableFrom(f.getType())) {
					Block block = (Block) f.get(null);
					if (block != null) {
						ItemStack stack = new ItemStack(block);
						if (stack != null && stack.getItem() != null) {
							ModularBossesItems.registerItemBlock(stack.getItem());
						}
						if (block instanceof IVanillaRotation) {
							ModularBosses.logger.warn("Registering custom rotation for " + block.getUnlocalizedName());
							BlockRotationData.registerCustomBlockRotation(block, ((IVanillaRotation) block).getRotationPattern());
						}
					}
				}
			}
		} catch (Exception e) {
			ModularBosses.logger.warn("Caught exception while registering block ItemBlocks: " + e.toString());
			e.printStackTrace();
		}
	}
}