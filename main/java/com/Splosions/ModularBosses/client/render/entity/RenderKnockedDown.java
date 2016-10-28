package com.Splosions.ModularBosses.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.models.entity.ModelKnockdown;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class RenderKnockedDown extends RenderPlayer
{
    /** this field is used to indicate the 3-pixel wide arms */
    private boolean smallArms;
    
    public static ModelPlayer modelKnockdown;
	
    public RenderKnockedDown(RenderManager renderManager)
    {
        this(renderManager, false);
    }

    public RenderKnockedDown(RenderManager renderManager, boolean useSmallArms)
    {
        super(renderManager);
        this.smallArms = useSmallArms;
        this.addLayer(new LayerBipedArmor(this));
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerArrow(this));
        this.addLayer(new LayerDeadmau5Head(this));
        this.addLayer(new LayerCape(this));
        this.addLayer(new LayerCustomHead(this.getPlayerModel().bipedHead));
        
        this.mainModel = new ModelKnockdown(1,false);
    }

    @Override
    public void doRender(EntityLivingBase entity, double x, double y, double z, float yaw, float partialTicks)
    {

		
		try {
			func_180596_a((AbstractClientPlayer)entity, x, y, z, yaw, partialTicks);
		} catch(Exception e) {
			e.printStackTrace();
		}

        
    }
    
    
    
    public void func_180596_a(AbstractClientPlayer p_180596_1_, double p_180596_2_, double p_180596_4_, double p_180596_6_, float p_180596_8_, float p_180596_9_)
    {
    	
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Pre(p_180596_1_, this, p_180596_9_, p_180596_2_, p_180596_4_, p_180596_6_))) return;

        if (!p_180596_1_.isUser() || Minecraft.getMinecraft().getRenderManager().livingPlayer == p_180596_1_)
        {
            double d3 = p_180596_4_;

            if (p_180596_1_.isSneaking() && !(p_180596_1_ instanceof EntityPlayerSP))
            {
                d3 = p_180596_4_ - 0.125D;
            }

            this.func_177137_d(p_180596_1_);

            mDoRender((EntityLivingBase)p_180596_1_, p_180596_2_, d3, p_180596_6_, p_180596_8_, p_180596_9_);
            
        }
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Post(p_180596_1_, this, p_180596_9_, p_180596_2_, p_180596_4_, p_180596_6_));
    }
    
    
    public void mDoRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Pre(entity, this, x, y, z))) return;
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        this.mainModel.swingProgress = this.getSwingProgress(entity, partialTicks);
        this.mainModel.isRiding = entity.isRiding();
        this.mainModel.isChild = entity.isChild();

        try
        {
            float f2 = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
            float f3 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
            float f4 = f3 - f2;
            float f5;

            if (entity.isRiding() && entity.ridingEntity instanceof EntityLivingBase)
            {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase)entity.ridingEntity;
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, partialTicks);
                f4 = f3 - f2;
                f5 = MathHelper.wrapAngleTo180_float(f4);

                if (f5 < -85.0F)
                {
                    f5 = -85.0F;
                }

                if (f5 >= 85.0F)
                {
                    f5 = 85.0F;
                }

                f2 = f3 - f5;

                if (f5 * f5 > 2500.0F)
                {
                    f2 += f5 * 0.2F;
                }
            }

            float f9 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
            this.renderLivingAt(entity, x, y, z);
            f5 = this.handleRotationFloat(entity, partialTicks);
            this.rotateCorpse(entity, f5, f2, partialTicks);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(-1.0F, -1.0F, 1.0F);
            this.preRenderCallback(entity, partialTicks);
            float f6 = 0.0625F;
            GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
            float f7 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
            float f8 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);

            if (entity.isChild())
            {
                f8 *= 3.0F;
            }

            if (f7 > 1.0F)
            {
                f7 = 1.0F;
            }

            GlStateManager.enableAlpha();
            this.mainModel.setLivingAnimations(entity, f8, f7, partialTicks);
            this.mainModel.setRotationAngles(f8, f7, f5, f4, f9, 0.0625F, entity);
            boolean flag;

            if (this.renderOutlines)
            {
                flag = this.func_177088_c(entity);
              //  this.renderModel(entity, f8, f7, f5, f4, f9, 0.0625F);

                if (flag)
                {
                    //this.func_180565_e();
                }
            }
            else
            {
                flag = this.func_177090_c(entity, partialTicks);
                this.renderModel(entity, f8, f7, f5, f4, f9, 0.0625F);

                if (flag)
                {
                    this.func_177091_f();
                }

                GlStateManager.depthMask(true);

                if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator())
                {
                	GL11.glPushMatrix();
            		GL11.glTranslatef(0, 1.25f, 0);
            		GL11.glRotatef(-90, 1, 0, 0);
                    this.func_177093_a(entity, f8, f7, partialTicks, f5, f4, f9, 0.0625F);
                    GL11.glPopMatrix();
                }
            }

            GlStateManager.disableRescaleNormal();
        } catch(Exception e) {
		
		e.printStackTrace();
        }

        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableCull();
        GlStateManager.popMatrix();

        if (!this.renderOutlines)
        {
           // super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
        }
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Post(entity, this, x, y, z));
    }
    
    
    
    protected void renderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        boolean flag = !p_77036_1_.isInvisible();
        boolean flag1 = !flag && !p_77036_1_.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer);

        if (flag || flag1)
        {
            if (!this.bindEntityTexture(p_77036_1_))
            {
                return;
            }

            if (flag1)
            {
                GlStateManager.pushMatrix();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 0.15F);
                GlStateManager.depthMask(false);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(770, 771);
                GlStateManager.alphaFunc(516, 0.003921569F);
            }

            this.mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);

            if (flag1)
            {
                GlStateManager.disableBlend();
                GlStateManager.alphaFunc(516, 0.1F);
                GlStateManager.popMatrix();
                GlStateManager.depthMask(true);
            }
        }
    }
    
    protected boolean bindEntityTexture(Entity entity)
    {
        ResourceLocation resourcelocation = this.getEntityTexture((EntityPlayer)entity);
        
        if (resourcelocation == null)
        {
            return false;
        }
        else
        {
        	
        	this.bindTexture(resourcelocation);
            return true;
        }
    }
    
    
    
    
    public void bindTexture(ResourceLocation location)
    {
       Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(location);
    }
    
       
    
    
    
    private void func_177137_d(AbstractClientPlayer p_177137_1_)
    {
        ModelPlayer modelplayer = this.getPlayerModel();

        if (p_177137_1_.isSpectator())
        {
            modelplayer.setInvisible(false);
            modelplayer.bipedHead.showModel = true;
            modelplayer.bipedHeadwear.showModel = true;
        }
        else
        {
            ItemStack itemstack = p_177137_1_.inventory.getCurrentItem();
            modelplayer.setInvisible(true);
            modelplayer.bipedHeadwear.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.HAT);
            modelplayer.bipedBodyWear.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.JACKET);
            modelplayer.bipedLeftLegwear.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.LEFT_PANTS_LEG);
            modelplayer.bipedRightLegwear.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.RIGHT_PANTS_LEG);
            modelplayer.bipedLeftArmwear.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.LEFT_SLEEVE);
            modelplayer.bipedRightArmwear.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.RIGHT_SLEEVE);
            modelplayer.heldItemLeft = 0;
            modelplayer.aimedBow = false;
            modelplayer.isSneak = p_177137_1_.isSneaking();

            if (itemstack == null)
            {
                modelplayer.heldItemRight = 0;
            }
            else
            {
                modelplayer.heldItemRight = 1;

                if (p_177137_1_.getItemInUseCount() > 0)
                {
                    EnumAction enumaction = itemstack.getItemUseAction();

                    if (enumaction == EnumAction.BLOCK)
                    {
                        modelplayer.heldItemRight = 3;
                    }
                    else if (enumaction == EnumAction.BOW)
                    {
                        modelplayer.aimedBow = true;
                    }
                }
            }
        }
    }

}