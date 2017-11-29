package com.Splosions.ModularBosses.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.Splosions.ModularBosses.client.models.entity.ModelKnockdown;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerEntityOnShoulder;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import scala.reflect.internal.Trees.Super;


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
        this.addLayer(new LayerCustomHead(this.getMainModel().bipedHead));
        this.addLayer(new LayerElytra(this));
        this.addLayer(new LayerEntityOnShoulder(renderManager));
    }

    @Override
    public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entYaw, float partTicks)
    {
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Pre(entity, this, partTicks, x, y, z))) return;
        if (!entity.isUser() || this.renderManager.renderViewEntity == entity)
        {
            double d0 = y;

            if (entity.isSneaking())
            {
                d0 = y - 0.125D;
            }

            setModelVisibilities(entity);
            GlStateManager.enableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
            mDoRender(entity, x, d0, z, entYaw, partTicks);
            GlStateManager.disableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
        }
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Post(entity, this, partTicks, x, y, z));
    }
    
    
    public void mDoRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Pre(entity, this, x, y, z))) return;
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        this.mainModel.swingProgress = this.getSwingProgress((AbstractClientPlayer) entity, partialTicks);
        boolean shouldSit = entity.isRiding() && (entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
        this.mainModel.isRiding = shouldSit;
        this.mainModel.isChild = entity.isChild();

        try
        {
            float f2 = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
            float f3 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
            float f4 = f3 - f2;
            float f5;

            if (shouldSit && entity.getRidingEntity() instanceof EntityLivingBase)
            {
            	EntityLivingBase entitylivingbase1 = (EntityLivingBase)entity.getRidingEntity();
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, partialTicks);
                f4 = f3 - f2;
                f5 = MathHelper.wrapDegrees(f4);

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
            this.renderLivingAt((AbstractClientPlayer) entity, x, y, z);
            f5 = this.handleRotationFloat((AbstractClientPlayer) entity, partialTicks);
            this.applyRotations((AbstractClientPlayer) entity, f5, f2, partialTicks);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(-1.0F, -1.0F, 1.0F);
            this.preRenderCallback((AbstractClientPlayer) entity, partialTicks);
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
                flag = this.setScoreTeamColor((AbstractClientPlayer) entity);
              //  this.renderModel(entity, f8, f7, f5, f4, f9, 0.0625F);

                if (flag)
                {
                    //this.func_180565_e();
                }
            }
            else
            {
                flag = this.setDoRenderBrightness((AbstractClientPlayer) entity, partialTicks);
                mRenderModel(entity, f8, f7, f5, f4, f9, 0.0625F);

                if (flag)
                {
                    this.unsetBrightness();
                }

                GlStateManager.depthMask(true);

                //ARMOR RENDER
                if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator())
                {
                	GL11.glPushMatrix();
            		GL11.glTranslatef(0, 1.25f, 0);
            		GL11.glRotatef(-90, 1, 0, 0);
                    this.renderLayers((AbstractClientPlayer) entity, f8, f7, partialTicks, f5, f4, f9, 0.0625F);
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
    
    
    
    protected void mRenderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        boolean flag = !p_77036_1_.isInvisible();
        boolean flag1 = !flag && !p_77036_1_.isInvisibleToPlayer(Minecraft.getMinecraft().player);

        if (flag || flag1)
        {
            if (!this.mBindEntityTexture(p_77036_1_))
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
    
    protected boolean mBindEntityTexture(Entity entity)
    {
        ResourceLocation resourcelocation = this.getEntityTexture((AbstractClientPlayer)entity);
        
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
    
       
    
    
    
    private void setModelVisibilities(AbstractClientPlayer clientPlayer)
    {
        ModelPlayer modelplayer = this.getMainModel();

        if (clientPlayer.isSpectator())
        {
            modelplayer.setVisible(false);
            modelplayer.bipedHead.showModel = true;
            modelplayer.bipedHeadwear.showModel = true;
        }
        else
        {
            ItemStack itemstack = clientPlayer.getHeldItemMainhand();
            ItemStack itemstack1 = clientPlayer.getHeldItemOffhand();
            modelplayer.setVisible(true);
            modelplayer.bipedHeadwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.HAT);
            modelplayer.bipedBodyWear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.JACKET);
            modelplayer.bipedLeftLegwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.LEFT_PANTS_LEG);
            modelplayer.bipedRightLegwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.RIGHT_PANTS_LEG);
            modelplayer.bipedLeftArmwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.LEFT_SLEEVE);
            modelplayer.bipedRightArmwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.RIGHT_SLEEVE);
            modelplayer.isSneak = clientPlayer.isSneaking();
            ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
            ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;

            if (!itemstack.isEmpty())
            {
                modelbiped$armpose = ModelBiped.ArmPose.ITEM;

                if (clientPlayer.getItemInUseCount() > 0)
                {
                    EnumAction enumaction = itemstack.getItemUseAction();

                    if (enumaction == EnumAction.BLOCK)
                    {
                        modelbiped$armpose = ModelBiped.ArmPose.BLOCK;
                    }
                    else if (enumaction == EnumAction.BOW)
                    {
                        modelbiped$armpose = ModelBiped.ArmPose.BOW_AND_ARROW;
                    }
                }
            }

            if (!itemstack1.isEmpty())
            {
                modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;

                if (clientPlayer.getItemInUseCount() > 0)
                {
                    EnumAction enumaction1 = itemstack1.getItemUseAction();

                    if (enumaction1 == EnumAction.BLOCK)
                    {
                        modelbiped$armpose1 = ModelBiped.ArmPose.BLOCK;
                    }
                    // FORGE: fix MC-88356 allow offhand to use bow and arrow animation
                    else if (enumaction1 == EnumAction.BOW)
                    {
                        modelbiped$armpose1 = ModelBiped.ArmPose.BOW_AND_ARROW;
                    }
                }
            }

            if (clientPlayer.getPrimaryHand() == EnumHandSide.RIGHT)
            {
                modelplayer.rightArmPose = modelbiped$armpose;
                modelplayer.leftArmPose = modelbiped$armpose1;
            }
            else
            {
                modelplayer.rightArmPose = modelbiped$armpose1;
                modelplayer.leftArmPose = modelbiped$armpose;
            }
        }
    }

}