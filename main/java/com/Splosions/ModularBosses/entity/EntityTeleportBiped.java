package com.Splosions.ModularBosses.entity;

import com.google.common.collect.Lists;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTeleportBiped extends Entity implements IEntityAdditionalSpawnData
{
	Random rand = new Random();
	public String strLoc;
	public ResourceLocation reLoc;
	public EntityTeleportBiped(World worldIn)
	{
		super(worldIn);
	}

	public EntityTeleportBiped(World worldIn, Entity shooter, double x, double y, double z, float yaw, String tLoc)
	{
		super(worldIn);
		this.setSize(1F, 1F);
		this.setPositionAndRotation(x, y, z, yaw, 0);
		this.strLoc = tLoc;	
	} 


	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{







		if (this.ticksExisted > 80){
			for (int l = 0; l < 20; ++l){
				this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX, this.posY + 1, this.posZ, rand.nextFloat() - 0.5F,rand.nextFloat() - 0.5F,rand.nextFloat() - 0.5F, 5);
			}
			
			setDead();
		}


	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, strLoc);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		String bytes = ByteBufUtils.readUTF8String(additionalData);
		reLoc = new ResourceLocation(bytes);

	}






}