package com.Splosions.ModularBosses.handler;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.FluidWormAcid;
import com.Splosions.ModularBosses.blocks.FluidWormBlood;
import com.Splosions.ModularBosses.blocks.FluidWormSaliva;
import com.Splosions.ModularBosses.blocks.GasWormGas;
import com.Splosions.ModularBosses.entity.MBExtendedEntityLivingBase;
import com.Splosions.ModularBosses.entity.MBExtendedPlayer;
import com.Splosions.ModularBosses.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MBEventHandler {

	// no block breaking in the boss dimension
	@SubscribeEvent
	public void onBlockBreak(BreakEvent event) {
		if (Config.bossDimBreak) {
			if (event.getWorld().provider.getDimension() == Config.bossDimension && !event.getPlayer().capabilities.isCreativeMode) {
				event.setCanceled(true);
				event.getWorld().scheduleUpdate(event.getPos(), event.getState().getBlock(), 1);
			}
		}
	}

	// Explosions will hurt entities, but won't break blocks in the boss
	// dimension
	@SubscribeEvent
	public void onExplosion(ExplosionEvent.Detonate event) {
		if (Config.bossDimExplosions) {
			if (event.getWorld().provider.getDimension() == Config.bossDimension) {
				event.getAffectedBlocks().clear();
			}
		}
	}
	
	// no block placing in the boss dimension
	@SubscribeEvent
	public void onBlockPlace(PlaceEvent event) {
		if (Config.bossDimBuild) {
			if (event.getWorld().provider.getDimension() == Config.bossDimension
					&& !event.getPlayer().capabilities.isCreativeMode) {
				event.setCanceled(true);
				event.getWorld().scheduleUpdate(event.getPos(), event.getState().getBlock(), 1);
			}
		}
	}
	
	
	@SubscribeEvent
	public void SpawnEvent(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntitySquid) {
			BlockPos pos = new BlockPos(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
			Block block = event.getWorld().getBlockState(pos).getBlock();
			if (block instanceof FluidWormBlood || block instanceof FluidWormSaliva || block instanceof FluidWormAcid || block instanceof GasWormGas) {
				event.setCanceled(true);
			}
		}
	}
	
	/**
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		try {
			if (event.getEntity() instanceof EntityPlayer && MBExtendedPlayer.get((EntityPlayer) event.getEntity()) == null){
				MBExtendedPlayer.register((EntityPlayer) event.getEntity());
			}
				
			if (event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer) && MBExtendedEntityLivingBase.get((EntityLivingBase) event.getEntity()) == null){
				MBExtendedEntityLivingBase.register((EntityLivingBase) event.getEntity());
			}
				

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			MBExtendedPlayer.get(player).onUpdate();
		}
		
		if (event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer)){
			EntityLivingBase entity = (EntityLivingBase) event.getEntity();
			MBExtendedEntityLivingBase.get(entity).onUpdate();
		}
	}





	
	@SubscribeEvent
	public void LivingAttack(LivingAttackEvent event){
		if (event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityLivingBase){
			if (!(event.getSource().getTrueSource() instanceof EntityPlayer) && event.getEntity() instanceof EntityPlayer){
				if (MBExtendedEntityLivingBase.get((EntityLivingBase) event.getSource().getTrueSource()).limbo != MBExtendedPlayer.get((EntityPlayer) event.getEntity()).limbo){
					event.setCanceled(true);
				}					
			}else if (event.getSource().getTrueSource() instanceof EntityPlayer && !(event.getEntity() instanceof EntityPlayer)){
				if (MBExtendedPlayer.get((EntityPlayer) event.getSource().getTrueSource()).limbo != MBExtendedEntityLivingBase.get((EntityLivingBase) event.getEntity()).limbo){
					event.setCanceled(true);
				}					
			}else if (event.getSource().getTrueSource() instanceof EntityPlayer && event.getEntity() instanceof EntityPlayer){
				if (MBExtendedPlayer.get((EntityPlayer) event.getSource().getTrueSource()).limbo != MBExtendedPlayer.get((EntityPlayer) event.getEntity()).limbo){
					event.setCanceled(true);
				}
			}
		} else if(event.getSource().getTrueSource() == null){
			if (event.getEntity() instanceof EntityPlayer && MBExtendedPlayer.get((EntityPlayer) event.getEntity()).limbo == 1){
				event.setCanceled(true);
			} else if (event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer) && MBExtendedEntityLivingBase.get((EntityLivingBase) event.getEntity()).limbo == 1){
				event.setCanceled(true);
			}
		}
	}
	
	
	*/

}
