package com.Splosions.ModularBosses.client.render.items;


import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.models.item.ModelBait;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SuppressWarnings("deprecation")
@SideOnly(Side.CLIENT)
public class RenderItemBait
{
	protected final ModelBait baitModel;
	private final IBakedModel baseModel;
	private final IBakedModel emptyModel;
	
	private final ResourceLocation loc = new ResourceLocation("mb:textures/items/Bait.png");

	public RenderItemBait(IBakedModel baseModel) {
		baitModel = new ModelBait();
		this.baseModel = baseModel;
		ModelResourceLocation resource = new ModelResourceLocation("mb:invisible_block", "inventory");
		this.emptyModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(resource);
		if (emptyModel == null) {
			ModularBosses.logger.warn("Failed to retrieve model for resource location: " + resource);
		}
	}

}