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


public class ModBlocks
{
	public static Block
	invisibleBlock,
	controlBlock,
	portalBlock,
	portalLanding,
	phaseFire,
	force_field_gen,
	force_field_blue,
	
	wormGutsBlock1,
	wormGutsBlock2
	;
	
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
		
		portalLanding = new BlockPortalLanding(Material.barrier).setLightLevel(1).setUnlocalizedName("portal_landing");
		GameRegistry.registerBlock(portalLanding, ItemModBlock.class, portalLanding.getUnlocalizedName().substring(5));
		
		phaseFire = new BlockPhaseFire(Material.barrier).setLightLevel(0.3F).setUnlocalizedName("phase_fire");
		GameRegistry.registerBlock(phaseFire, ItemModBlock.class, phaseFire.getUnlocalizedName().substring(5));
		
		wormGutsBlock1 = new BlockWormGuts(Material.barrier).setUnlocalizedName("worm_guts_1");
		GameRegistry.registerBlock(wormGutsBlock1, ItemModBlock.class, wormGutsBlock1.getUnlocalizedName().substring(5));
		
		wormGutsBlock2 = new BlockWormGuts(Material.barrier).setUnlocalizedName("worm_guts_2");
		GameRegistry.registerBlock(wormGutsBlock2, ItemModBlock.class, wormGutsBlock2.getUnlocalizedName().substring(5));
		
		force_field_gen = new BlockForceFieldGen(Material.barrier).setLightLevel(1).setUnlocalizedName("force_field_gen");
		GameRegistry.registerBlock(force_field_gen, ItemModBlock.class, force_field_gen.getUnlocalizedName().substring(5));
		
		force_field_blue = new BlockForceFieldBlue(Material.barrier).setLightLevel(1).setUnlocalizedName("force_field_blue");
		GameRegistry.registerBlock(force_field_blue, ItemModBlock.class, force_field_blue.getUnlocalizedName().substring(5));
		
		invisibleBlock = new BlockInvisible(Material.barrier).setUnlocalizedName("invisible_block");
		GameRegistry.registerBlock(invisibleBlock, ItemModBlock.class, invisibleBlock.getUnlocalizedName().substring(5));
		

		
		// register block items for creative tab comparator sorting:
		try {
			for (Field f: ModBlocks.class.getFields()) {
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