package com.Splosions.ModularBosses;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import com.Splosions.ModularBosses.blocks.ModBlocks;
import com.Splosions.ModularBosses.blocks.ModFluids;
import com.Splosions.ModularBosses.client.render.entity.RenderKnockedDown;
import com.Splosions.ModularBosses.dimensions.TestBiomesRegistry;
import com.Splosions.ModularBosses.dimensions.TestDimensions;
import com.Splosions.ModularBosses.entity.ModularBossesEntities;
import com.Splosions.ModularBosses.handler.ChunkLoadingHandler;
import com.Splosions.ModularBosses.handler.GuiHandler;
import com.Splosions.ModularBosses.handler.MBEventHandler;
import com.Splosions.ModularBosses.handler.TickHandler;
import com.Splosions.ModularBosses.handler.commands.CommandItemInfo;
import com.Splosions.ModularBosses.items.ModularBossesItems;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.proxy.ClientProxy;
import com.Splosions.ModularBosses.proxy.CommonProxy;
import com.Splosions.ModularBosses.util.schematic.Dungeon;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ModularBosses {

	public ArrayList<Dungeon> dungeonList = new ArrayList<Dungeon>();
	public Entity playerTarget;
	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@Instance(Reference.MOD_ID)
	public static ModularBosses INSTANCE;

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandItemInfo());
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
 		ForgeChunkManager.setForcedChunkLoadingCallback(INSTANCE, new ChunkLoadingHandler());
		FMLCommonHandler.instance().bus().register(new TickHandler());
		TestBiomesRegistry.registerBiomes();
		ModFluids.registerFluids();
		ModBlocks.preInit();
		ModularBossesItems.init();
		ModularBossesItems.registerItems();
		ModularBossesEntities.init();
		PacketDispatcher.preInit();
		Config.preInit(event);
		proxy.preInit();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		TestDimensions.init();
		proxy.registerRenders();
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Config.postInit();
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new MBEventHandler());
	}
}
