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
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SuppressWarnings("deprecation")
@SideOnly(Side.CLIENT)
public class RenderItemLegendsBow
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


/**
		case FIRST_PERSON_RIGHT_HAND:
			GlStateManager.translate(0.5F, 1.6F, 0.5F);
			GlStateManager.rotate(-55.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.75F, 1.5F, 0.5F);
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(-20.0F, 1.0F, 0.0F, 0.0F);
			//if (aniCount < 980 && aniCount != 0){
			miniSun(scale * 0.1F, 1.05F, -0.3F, 2.5F);
			//}
			break;
		case THIRD_PERSON_RIGHT_HAND:
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
*/


	
	
	
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
    		BufferBuilder bufferbuilder = tessellator.getBuffer();
    		RenderHelper.disableStandardItemLighting();
    		//float f = ((float) spark.ticksExisted ) / 200.0F;
    		float f = 0;
    		float f1 = 0.0F;

    		if (f > 0.8F) {
    			f1 = (f - 0.8F) / 0.2F;
    		}

    		Random random = new Random(432L);
    		GlStateManager.disableTexture2D();
    		GlStateManager.shadeModel(7425);
    		GlStateManager.enableBlend();
    		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
    		GlStateManager.disableAlpha();
    		GlStateManager.enableCull();
    		GlStateManager.depthMask(false);
    		GlStateManager.pushMatrix();
    		GlStateManager.translate(0.0F, -1.0F, -2.0F);

    		for (int i = 0; (float) i < (f + f * f) / 2.0F * 60.0F; ++i) {
    			GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
    			GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
    			GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
    			GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
    			GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
    			GlStateManager.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
    			float f2 = random.nextFloat() * 20.0F + 5.0F + f1 * 10.0F;
    			float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
    			bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);

   				bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(0,255,255, (int) (255.0F * (1.0F - f1))).endVertex();

    			bufferbuilder.pos(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(255, 0, 255, 0).endVertex();
    			bufferbuilder.pos(0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(255, 0, 255, 0).endVertex();
    			bufferbuilder.pos(0.0D, (double) f2, (double) (1.0F * f3)).color(255, 0, 255, 0).endVertex();
    			bufferbuilder.pos(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(255, 0, 255, 0).endVertex();
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

	
}