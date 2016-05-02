package com.Splosions.ModularBosses.client.render.items;


import java.util.List;

import javax.vecmath.Matrix4f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.ISmartItemModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;
import com.Splosions.ModularBosses.client.models.item.ModelLegendsSword;


@SuppressWarnings("deprecation")
@SideOnly(Side.CLIENT)
public class ModelItemLegendsSword implements ISmartItemModel, IPerspectiveAwareModel
{
	protected final ModelLegendsSword swordModel;
	private final IBakedModel baseModel;
	private final IBakedModel emptyModel;
	
	private final ResourceLocation loc = new ResourceLocation("mb:textures/items/StevesSword.png");

	public ModelItemLegendsSword(IBakedModel baseModel) {
		swordModel = new ModelLegendsSword();
		this.baseModel = baseModel;
		ModelResourceLocation resource = new ModelResourceLocation(Reference.MOD_ID + ":empty", "inventory");
		this.emptyModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(resource);
		if (emptyModel == null) {
			ModularBosses.logger.warn("Failed to retrieve model for resource location: " + resource);
		}
	}


	@Override
	public Pair<IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {

		// gui renders as 2D sprite; this is apparently also what renders when the item is dropped
		if (cameraTransformType == ItemCameraTransforms.TransformType.GUI) {
			RenderItem.applyVanillaTransform(baseModel.getItemCameraTransforms().gui);
			return Pair.of(baseModel, null);
		}

		GlStateManager.pushMatrix();
		GL11.glScalef(0.1F, 0.1F, 0.1F);
		switch (cameraTransformType) {
		case FIRST_PERSON:
			GlStateManager.translate(0.5F, 0.5F, 0.5F);
			GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(-40.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.75F, 0.2F, 0.5F);
			break;
		case THIRD_PERSON:
			GlStateManager.rotate(100.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(90.0F, 0F, 1.0F, 0.0F);
			GlStateManager.translate(0.3F, -0.3F, 0.2F);
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			break;
		default:
			break;
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture1());
		// first Entity parameter not used for anything in ModelLegendsSword, so null is safe
		swordModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
		GlStateManager.popMatrix();
		// return empty model to render nothing - bomb model already rendered
		return Pair.of(emptyModel, null);
	}

	@Override
	public List getFaceQuads(EnumFacing face) {
		return baseModel.getFaceQuads(face);
	}

	@Override
	public List getGeneralQuads() {
		return baseModel.getGeneralQuads();
	}

	@Override
	public boolean isAmbientOcclusion() {
		return baseModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return baseModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getTexture() {
		return baseModel.getTexture();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return baseModel.getItemCameraTransforms();
	}

	private ResourceLocation getTexture1() {
		return loc;
	}


	@Override
	public IBakedModel handleItemState(ItemStack stack) {
		return this;
	}
}