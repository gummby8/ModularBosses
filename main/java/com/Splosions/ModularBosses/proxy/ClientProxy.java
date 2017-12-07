package com.Splosions.ModularBosses.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;
import com.Splosions.ModularBosses.blocks.ISpecialRenderer;
import com.Splosions.ModularBosses.blocks.ModBlocks;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityPortalBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityReturnPortalBlock;
import com.Splosions.ModularBosses.client.ISwapModel;
import com.Splosions.ModularBosses.client.models.ModModelManager;
import com.Splosions.ModularBosses.client.render.items.ItemRenderHack;
import com.Splosions.ModularBosses.client.render.items.RenderItemBait;
import com.Splosions.ModularBosses.client.render.tileentity.RenderTileEntityControlBlock;
import com.Splosions.ModularBosses.client.render.tileentity.RenderTileEntityPortalBlock;
import com.Splosions.ModularBosses.client.render.tileentity.RenderTileEntityReturnPortalBlock;
import com.Splosions.ModularBosses.entity.ModularBossesEntities;
import com.Splosions.ModularBosses.handler.MBClientEventHandler;
import com.Splosions.ModularBosses.items.IModItem;
import com.Splosions.ModularBosses.items.ModularBossesItems;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	

	
	
	public static void sobelShader(){
		try {
			//no more reflection needed
			Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/sobel.json"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	
	public static void clearShader(){
		try{
			//no more reflection needed
			Minecraft.getMinecraft().entityRenderer.stopUseShader();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	

	
	@Override
	public void registerItemRenderers() {
		ItemRenderHack.registerItemRenderer(ModularBossesItems.itemBait, new RenderItemBait());
	}

	
	@Override
	public void preInit() {
		super.preInit();

	}
	
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);


    }
	
}
