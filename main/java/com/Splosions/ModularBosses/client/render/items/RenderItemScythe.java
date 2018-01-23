package com.Splosions.ModularBosses.client.render.items;


import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.models.item.ModelBait;
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



@SideOnly(Side.CLIENT)
public class RenderItemScythe extends RenderItemBase
{
	
	private final static ResourceLocation TEXTURE = new ResourceLocation("mb:textures/items/Scythe.png");


	public RenderItemScythe() {
		super(new ModelScythe(), TEXTURE);
		this.scale_ground=this.scale_thirdp;
	}
}