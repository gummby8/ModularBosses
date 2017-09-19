package com.Splosions.ModularBosses.blocks.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.Splosions.ModularBosses.blocks.BlockPortalBlock;
import com.Splosions.ModularBosses.blocks.FluidTempWormBlood;
import com.Splosions.ModularBosses.blocks.ModFluids;
import com.Splosions.ModularBosses.entity.EntityTeleportBiped;
import com.Splosions.ModularBosses.entity.projectile.EntityCustomFallingBlock;
import com.Splosions.ModularBosses.entity.projectile.EntityFlameThrower;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.server.SetControlBlockMessagePacket;
import com.Splosions.ModularBosses.world.PortalLandingWorldData;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.Entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityTempWormAcid extends TileEntity implements IUpdatePlayerListBox {

	public int ticksExisted;


	@Override
	public void update() {
		if (Integer.parseInt(this.worldObj.getBlockState(this.pos).getValue(FluidTempWormBlood.LEVEL).toString()) != 0){
		this.worldObj.removeTileEntity(this.pos);
		return;
		}
		
		if (this.ticksExisted > 100 && !this.worldObj.isRemote) {
			//System.out.println("Killing it - " + ticksExisted);	
			IBlockState state = ModFluids.fluidTempWormAcid.getBlock().getDefaultState().withProperty(BlockFluidBase.LEVEL, 0);
			this.worldObj.setBlockState(this.pos, state.withProperty(BlockFluidBase.LEVEL, 1),2);
			this.worldObj.scheduleUpdate(this.pos, this.worldObj.getBlockState(this.pos).getBlock(), 2);		

		}

	
		
		
		ticksExisted++;
	}

	

}
