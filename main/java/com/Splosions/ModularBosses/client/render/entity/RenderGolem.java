package com.Splosions.ModularBosses.client.render.entity;


import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.entity.ModelChorpChorp;
import com.Splosions.ModularBosses.entity.EntityChorpChorp;



@SideOnly(Side.CLIENT)
public class RenderGolem extends RenderLiving
{


    public RenderGolem(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
 
    }
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		IBlockState iblockstate = entity.worldObj.getBlockState(entity.getPosition().down());
		
		
		
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        IBakedModel ibakedmodel = blockrendererdispatcher.getModelFromBlockState(iblockstate, entity.worldObj, entity.getPosition());
        
        System.out.println(iblockstate.getBlock().getBlockHardness(entity.worldObj, entity.getPosition().down()));
        
        String string = ibakedmodel.getTexture().getIconName() + ".png";
        String[] parts = string.split(":");
        String part1 = parts[0];
        String part2 = parts[1];
        
		// TODO Auto-generated method stub
		return new ResourceLocation(parts[0] + ":textures/" + parts[1]);
		//"minecraft:textures/blocks/dirt.png"
        //return new ResourceLocation("mb:textures/blocks/worm_guts_2.png");
	}



}