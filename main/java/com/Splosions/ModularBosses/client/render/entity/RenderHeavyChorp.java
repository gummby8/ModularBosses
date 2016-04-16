package com.Splosions.ModularBosses.client.render.entity;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.entity.ModelChorpChorp;
import com.Splosions.ModularBosses.entity.EntityHeavyChorp;



@SideOnly(Side.CLIENT)
public class RenderHeavyChorp extends RenderLiving
{
    private static final ResourceLocation ChorpChorpEyesTextures = new ResourceLocation("mb:textures/mobs/ChorpChorpGlow.png");
    private static final ResourceLocation ChorpChorpTextures = new ResourceLocation("mb:textures/mobs/HeavyChorp.png");

    
    
    public RenderHeavyChorp(RenderManager renderManager, ModelBase model, float shadowSize) {
		super(renderManager, model, shadowSize);
    }
	




    /**
     * Sets the ChorpChorp's glowing eyes
     */
    protected int setChorpChorpEyeBrightness(EntityHeavyChorp par1EntityChorpChorp, int par2, float par3)
    {
    	

        if (par2 != 0)
        {
            return -1;
        }
        else
        {
            this.bindTexture(ChorpChorpEyesTextures);
            float f1 = 1.0F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);

            if (par1EntityChorpChorp.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            return 1;
        }
    }

    protected ResourceLocation getChorpChorpTextures(EntityHeavyChorp par1EntityChorpChorp)
    {
        return ChorpChorpTextures;
    }



    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.setChorpChorpEyeBrightness((EntityHeavyChorp)par1EntityLivingBase, par2, par3);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		float scale = 2F;
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(0.0F, -0.7F, 0.0F);
		return this.getChorpChorpTextures((EntityHeavyChorp)par1Entity);
    }
}