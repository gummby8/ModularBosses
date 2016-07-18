package com.Splosions.ModularBosses.items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.ISwapModel;
import com.Splosions.ModularBosses.client.models.item.ModelLegendsSword;
import com.Splosions.ModularBosses.client.render.items.RenderItemLegendsSword;
import com.Splosions.ModularBosses.dimensions.BossDimension.BossTeleporter;
import com.Splosions.ModularBosses.entity.EntityCartographer;
import com.Splosions.ModularBosses.entity.EntityCustomFallingBlock;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;
import com.Splosions.ModularBosses.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.entity.projectile.EntityFlameThrower;
import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.Splosions.ModularBosses.proxy.ClientProxy;
import com.Splosions.ModularBosses.util.Schematic;
import com.Splosions.ModularBosses.util.TargetUtils;
import com.Splosions.ModularBosses.world.PortalLandingWorldData;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.gson.JsonSyntaxException;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.lang.reflect.*;

public class ItemLegendsSword extends BaseModSword implements ISwapModel {

	public ItemLegendsSword(ToolMaterial material) {
		super(material);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		
		Entity entityIn = (Entity)playerIn;
		if (entityIn.ridingEntity == null && entityIn.riddenByEntity == null && !worldIn.isRemote
				&& worldIn instanceof WorldServer && entityIn instanceof EntityPlayer) {
			worldIn.theProfiler.startSection("portal");
			int dim = 0;
			if (worldIn.provider.getDimensionId() == -3) {
				dim = 0;
			} else {
				dim = -3;
			}
				
			EntityPlayerMP player = (EntityPlayerMP) entityIn;
			BossTeleporter teleporter = new BossTeleporter(player.getServerForPlayer());
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, dim, teleporter);
			worldIn.theProfiler.endSection();
			WorldServer ws = MinecraftServer.getServer().worldServerForDimension(dim);
			ws.setBlockState(new BlockPos(playerIn.posX, playerIn.posY - 2, playerIn.posZ), Blocks.stone.getDefaultState());			
			

		}

		/**
		Entity entity = new EntityCartographer(worldIn, playerIn, playerIn.posX, playerIn.posY, playerIn.posZ);
		worldIn.spawnEntityInWorld(entity);
		*/
        return itemStackIn;
	}
	
	
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

	/**
	 * Override to add custom weapon damage field rather than vanilla ItemSword's field
	 */
	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Weapon modifier", Config.legendsSwordDmg, 0));
		return multimap;
	}
    
	@Override
	@SideOnly(Side.CLIENT)
	public Collection<ModelResourceLocation> getDefaultResources() {
		List<ModelResourceLocation> resources = Lists.newArrayList();

		resources.add(new ModelResourceLocation("mb:Legends_Sword", "inventory"));

		return resources;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends IBakedModel> getNewModel() {
		return RenderItemLegendsSword.class;
	}

}
