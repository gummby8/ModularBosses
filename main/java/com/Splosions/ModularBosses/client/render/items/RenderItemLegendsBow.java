package com.Splosions.ModularBosses.client.render.items;


import java.util.List;
import java.util.Random;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.models.item.ModelLegendsBow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
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


@SuppressWarnings("deprecation")
@SideOnly(Side.CLIENT)
public class RenderItemLegendsBow implements ISmartItemModel, IPerspectiveAwareModel
{
	protected  ModelBase bowModel;
	public  IBakedModel baseModel;
	public IBakedModel emptyModel;
	public int aniCount = 0;
	
	private final ResourceLocation loc = new ResourceLocation("mb:textures/items/LegendsBow.png");
	

	public RenderItemLegendsBow(IBakedModel baseModel) {
		bowModel = new ModelLegendsBow();
		this.baseModel = baseModel;

		ModelResourceLocation resource = new ModelResourceLocation("mb:invisible_block", "inventory");
		this.emptyModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getModel(resource);
		IBakedModel def = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getMissingModel(); 
		if (emptyModel == def) {
			ModularBosses.logger.warn("Failed to retrieve model for resource location: " + resource);
		}
		
	}


	@Override
	public Pair<IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
		
		float scale = ((aniCount < 960 && aniCount > 980)? 20 : 980 - aniCount);
		scale = (aniCount == 0 || scale < 0)? 0 : scale / 20;
		scale = (scale >= 1)? 1 : scale;
		
		
		// gui renders as 2D sprite; this is apparently also what renders when the item is dropped
		if (cameraTransformType == ItemCameraTransforms.TransformType.GUI) {
			RenderItem.applyVanillaTransform(baseModel.getItemCameraTransforms().gui);
			return Pair.of(baseModel, null);
		}
		GlStateManager.pushMatrix();
		GL11.glScalef(0.1F, 0.1F, 0.1F);
		switch (cameraTransformType) {
		case FIRST_PERSON:
			GlStateManager.translate(0.5F, 1.6F, 0.5F);
			GlStateManager.rotate(-55.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.75F, 1.5F, 0.5F);
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(-20.0F, 1.0F, 0.0F, 0.0F);
			//if (aniCount < 980 && aniCount != 0){
			miniSun(scale * 0.1F, 1.05F, -0.3F, 2.5F);
			//}
			break;
		case THIRD_PERSON:
			GlStateManager.rotate(100.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(0.0F, 0F, 1.0F, 0.0F);
			GlStateManager.translate(0.8F, -0.3F, -0.9F);
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			if (aniCount < 980 && aniCount != 0){
			miniSun(scale * 0.2F, 0, 0, -1.5F);
			}
			break;
		default:
			break;
		}

		Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture1());
		// first Entity parameter not used for anything in ModelLegendsSword, so null is safe
		bowModel.render(null, this.aniCount, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);

		GlStateManager.popMatrix();
		// return empty model to render nothing - bomb model already rendered
		
		
		
		return Pair.of(emptyModel, null);
	}
	
	
	
    protected void miniSun(float scale, float x, float y, float z) {
            float rotation = (float)(720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

            GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			GlStateManager.scale(scale, scale, scale);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(rotation, 0.0F, 0.5F, 0.0F);
            GlStateManager.rotate(rotation / 2, 0.0F, 0.0F, 0.5F);
            renderDragonDeath(180, 0.0F, 0.0F, 0.0F);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
    }

    private void renderDragonDeath(float density, float x, float y, float z) {
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            RenderHelper.disableStandardItemLighting();
            float f7 = density / 200.0F;
            float f8 = 0.0F;

            if (f7 > 0.8F) f8 = (f7 - 0.8F) / 0.2F;

            Random random = new Random(432L);
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 1);
            GlStateManager.disableAlpha();
            GlStateManager.enableCull();
            GlStateManager.depthMask(false);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x, y, z);

            for (int i = 0;
            (float) i < (f7 + f7 * f7) / 2.0F * 60.0F; ++i) {
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F + f7 * 90.0F, 0.0F, 0.0F, 1.0F);
                    worldrenderer.startDrawing(6);
                    float f9 = random.nextFloat() * 20.0F + 5.0F + f8 * 10.0F;
                    float f10 = random.nextFloat() * 2.0F + 1.0F + f8 * 2.0F;
                    //density.worldrenderer.setColorRGBA_I(16760576, (int)(255.0F * (1.0F - f8)));
                    worldrenderer.setColorRGBA(0,255,255, (int)(255.0F * (1.0F - f8)));
                    worldrenderer.addVertex(0.0D, 0.0D, 0.0D);
                    //worldrenderer.setColorRGBA_I(16711680, 0);
                    worldrenderer.setColorRGBA(0,0,255,0);
                    worldrenderer.addVertex(-0.866D * (double) f10, (double) f9, (double)(-0.5F * f10));
                    worldrenderer.addVertex(0.866D * (double) f10, (double) f9, (double)(-0.5F * f10));
                    worldrenderer.addVertex(0.0D, (double) f9, (double)(1.0F * f10));
                    worldrenderer.addVertex(-0.866D * (double) f10, (double) f9, (double)(-0.5F * f10));
                    tessellator.draw();
            }

            GlStateManager.popMatrix();
            GlStateManager.depthMask(true);
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.shadeModel(7424);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableTexture2D();
            GlStateManager.enableAlpha();
            RenderHelper.enableStandardItemLighting();
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
		this.aniCount = stack.animationsToGo;
		return this;
	}
}