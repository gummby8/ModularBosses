package com.Splosions.ModularBosses.blocks;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityPortalBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityReturnPortalBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityTempWormAcid;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityTempWormBlood;
import com.Splosions.ModularBosses.items.ModularBossesItems;
import com.google.common.base.Preconditions;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
/**
	
	public static final BlockInvisible INVISIBLE_BLOCK = new BlockInvisible(Material.BARRIER);
		
	public static final BlockControlBlock CONTROL_BLOCK = new BlockControlBlock(Material.BARRIER);
	public static final BlockPortalBlock PORTAL_BLOCK = new BlockPortalBlock(Material.BARRIER);//.setLightLevel(1)
	public static final BlockPortalLanding PORTAL_LANDING = new BlockPortalLanding(Material.BARRIER);//.setLightLevel(1)
	public static final BlockReturnPortal DUNGEON_EXIT_PORTAL = new BlockReturnPortal(Material.BARRIER);//.setLightLevel(1)
	public static final BlockPhaseFire PHASE_FIRE = new BlockPhaseFire(Material.BARRIER);//.setLightLevel(0.3F)
	public static final BlockForceFieldGen FORCE_FIELD_GENERATOR = new BlockForceFieldGen(Material.BARRIER);
	public static final BlockForceFieldBlue FORCE_FIELD = new BlockForceFieldBlue(Material.BARRIER);
	
	
	public static final BlockWormGuts WORM_GUTS_1 = new BlockWormGuts(Material.BARRIER);
	public static final BlockWormGuts WORM_GUTS_2 = new BlockWormGuts(Material.BARRIER);
	public static final BlockWormTumor WORM_TUMOR = new BlockWormTumor(Material.BARRIER);//.setLightLevel(1)

	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandler {
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();
		

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			final Block[] blocks = {
					CONTROL_BLOCK,
					PORTAL_LANDING,
					PORTAL_BLOCK,
					PORTAL_LANDING,
					FORCE_FIELD_GENERATOR,
					FORCE_FIELD,
					WORM_GUTS_1,
					WORM_GUTS_2,
					WORM_TUMOR,
					PHASE_FIRE,
					INVISIBLE_BLOCK,
				};
			registry.registerAll(blocks);
		}
		
		

		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			final ItemBlock[] items = {
					new ItemBlock(CONTROL_BLOCK),
					new ItemBlock(PORTAL_LANDING),
					new ItemBlock(PORTAL_BLOCK),
					new ItemBlock(PORTAL_LANDING),
					new ItemBlock(FORCE_FIELD_GENERATOR),
					new ItemBlock(FORCE_FIELD),
					new ItemBlock(WORM_GUTS_1),
					new ItemBlock(WORM_GUTS_2),
					new ItemBlock(WORM_TUMOR),
					new ItemBlock(PHASE_FIRE),
					new ItemBlock(INVISIBLE_BLOCK),
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) {
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
				registry.register(item.setRegistryName(registryName));
				ITEM_BLOCKS.add(item);
			}

			registerTileEntities();
		}
	}

	private static void registerTileEntities() {
		registerTileEntity(TileEntityControlBlock.class, "tileEntityControlBlock");
		registerTileEntity(TileEntityPortalBlock.class, "tileEntityPortalBlock");
		registerTileEntity(TileEntityReturnPortalBlock.class, "tileEntityReturnPortalBlock");
		
		registerTileEntity(TileEntityTempWormBlood.class, "tileEntityTempWormBlood");
		registerTileEntity(TileEntityTempWormAcid.class, "tileEntityTempWormAcid");
	}

	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		GameRegistry.registerTileEntity(tileEntityClass, Reference.MOD_ID + ":" + name);
	}
	
	*/
	@GameRegistry.ObjectHolder("control_block")
	public static BlockControlBlock CONTROL_BLOCK = null;

	@GameRegistry.ObjectHolder("myblock")
	public static ItemBlock CONTROL_BLOCK_ITEM = null;
	
	  @SubscribeEvent
	  public static void registerBlocks(RegistryEvent.Register<Block> event) {
		  System.out.println("REGESTERING BLOCKS");
		  System.out.println("REGESTERING BLOCKS");
		  System.out.println("REGESTERING BLOCKS");
		  System.out.println("REGESTERING BLOCKS");
		  System.out.println("REGESTERING BLOCKS");
		  System.out.println("REGESTERING BLOCKS");
		  System.out.println("REGESTERING BLOCKS");
		  System.out.println("REGESTERING BLOCKS");
	    event.getRegistry().register(new BlockControlBlock(Material.BARRIER).setRegistryName("control_block"));
	  }
	  
	  @SubscribeEvent
	  public static void registerBlockItems(RegistryEvent.Register<Item> event) {
	    // will MY_BLOCK have been injected at this point?
		  System.out.println("REGESTERING BLOCK ITEMS");
		  System.out.println("REGESTERING BLOCK ITEMS");
		  System.out.println("REGESTERING BLOCK ITEMS");
		  System.out.println("REGESTERING BLOCK ITEMS");
		  System.out.println("REGESTERING BLOCK ITEMS");
		  System.out.println("REGESTERING BLOCK ITEMS");
		  System.out.println("REGESTERING BLOCK ITEMS");
		  System.out.println("REGESTERING BLOCK ITEMS");
		  System.out.println("REGESTERING BLOCK ITEMS");
	    event.getRegistry().register(new ItemBlock(CONTROL_BLOCK).setRegistryName("control_block"));
	  }
	  
	  @SideOnly(Side.CLIENT)
	  @SubscribeEvent
	  public static void registerModels(ModelRegistryEvent event) {
	    //ModelLoader.setCustomModelResourceLocation(CONTROL_BLOCK_ITEM, 0, new ModelResourceLocation("mb", "textures/blocks/control_block/blank.png"));
	  }
	
}
