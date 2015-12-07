package com.Splosions.ModularBosses.client.render.items;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.item.ModelLegendsSword;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLegendsSword implements IItemRenderer 
{
	public static final ResourceLocation texture = new ResourceLocation("modularbosses", "textures/items/StevesSword.png");
	public static final ResourceLocation texture1 = new ResourceLocation("modularbosses", "textures/items/StevesSword1.png");

	private final ModelLegendsSword model;
	private final Minecraft mc;
	
	public int RenderPass = 1;
	
	public RenderLegendsSword() {
		model = new ModelLegendsSword();
		mc = Minecraft.getMinecraft();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP && type != ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{

		System.out.println("RENDERING");

			GL11.glPushMatrix();

			Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			float scale = 0.1F;
			GL11.glScalef(scale, scale, scale);

			if(data[1] instanceof EntityPlayer) {
				if (type == ItemRenderType.EQUIPPED) {
					GL11.glRotatef(210, 1F, 1F, 300F);
					GL11.glTranslatef(-7.5F, 2.5F, -0.1F);
					//System.out.println("IN THIRD PERSON");
				} else {
					GL11.glRotatef(210, 0F, 0F, 300F);
					GL11.glTranslatef(-4.5F, 0F, -2.5F);
					//System.out.println("IN FIRST PERSON");
				}
			} else {
				//System.out.println("IN MOBS HANDS");
				GL11.glRotatef(205, 0F, 0F, 1F);
				GL11.glRotatef(135, 0F, 1F, 0F);
				GL11.glRotatef(180, 1F, 0F, 0F);
				//GL11.glTranslatef(0F, -1F, -1F);
			}
			

			model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();

	}
	
	

}
