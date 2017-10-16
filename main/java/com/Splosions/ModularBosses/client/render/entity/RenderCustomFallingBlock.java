package com.Splosions.ModularBosses.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.entity.projectile.EntityCustomFallingBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCustomFallingBlock extends Render
{
    private static final String __OBFID = "CL_00000994";

    public RenderCustomFallingBlock(RenderManager p_i46177_1_)
    {
        super(p_i46177_1_);
        this.shadowSize = 0.5F;
    }
    
    

    public void doRender(EntityCustomFallingBlock entity, double x, double y, double z, float entityYaw, float partialTicks)
    {

    	
        if (entity.getBlock() != null)
        {
        	
            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            IBlockState iblockstate = entity.getBlock();
            Block block = iblockstate.getBlock();
            
            if (block.getMaterial(iblockstate) != Material.AIR && !(block instanceof BlockStaticLiquid)){
            	
            		World world = entity.getWorldObj(); 
            		
            
                    GlStateManager.pushMatrix();
                    GlStateManager.translate((float)x, (float)y, (float)z);
                    GlStateManager.disableLighting();
                    
                    GlStateManager.enableNormalize();
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder bufferbuilder = tessellator.getBuffer();

                    if (this.renderOutlines)
                    {
                        GlStateManager.enableColorMaterial();
                        GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                    }

                    bufferbuilder.begin(7, DefaultVertexFormats.BLOCK);
                    BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);
                    GlStateManager.translate((float)(x - (double)blockpos.getX() - 0.5D), (float)(y - (double)blockpos.getY()), (float)(z - (double)blockpos.getZ() - 0.5D));
                    BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                    blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(iblockstate), iblockstate, blockpos, bufferbuilder, false, MathHelper.getPositionRandom(entity.getOrigin()));
                    tessellator.draw();

                    if (this.renderOutlines)
                    {
                        GlStateManager.disableOutlineMode();
                        GlStateManager.disableColorMaterial();
                    }
                    GlStateManager.enableLighting();
                    GlStateManager.popMatrix();
                   // super.doRender(entity, x, y, z, entityYaw, partialTicks);
            
            }
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCustomFallingBlock entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntityCustomFallingBlock)entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityCustomFallingBlock)entity, x, y, z, p_76986_8_, partialTicks);
    }
}