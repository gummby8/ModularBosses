package com.Splosions.ModularBosses.client.render.items;


import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.models.item.ModelLegendsSword;
import com.Splosions.ModularBosses.client.models.projectiles.ModelScythe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SuppressWarnings("deprecation")
@SideOnly(Side.CLIENT)
public class RenderItemScythe
{
	protected final ModelBase scytheModel;
	protected final ModelBase swordModel;
	private final IBakedModel baseModel;
	private final IBakedModel emptyModel;
	public Entity ent;
	public int modelNum = 0;
	
	private final ResourceLocation loc = new ResourceLocation("mb:textures/items/Scythe.png");

	public RenderItemScythe(IBakedModel baseModel) {
		scytheModel = new ModelScythe();
		swordModel = new ModelLegendsSword();
		this.baseModel = baseModel;
		ModelResourceLocation resource = new ModelResourceLocation("mb:invisible_block", "inventory");
		this.emptyModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(resource);
		if (emptyModel == null) {
			ModularBosses.logger.warn("Failed to retrieve model for resource location: " + resource);
		}
	}


	private ResourceLocation getTexture1() {
		return loc;
	}

}