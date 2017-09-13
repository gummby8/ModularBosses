
package com.Splosions.ModularBosses.blocks;

import java.util.List;
import java.util.Random;

import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.BlockRotationData.Rotation;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.entity.CustomEntityList;
import com.Splosions.ModularBosses.handler.GuiHandler;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.client.OpenControlBlockEditorPacket;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.StringUtils;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockForceFieldGen extends Block implements IVanillaRotation {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool POWERED = PropertyBool.create("powered");

	public BlockForceFieldGen(Material material) {
		super(material);
		setHardness(10.0F);
		setHarvestLevel("pickaxe", 2);
		setStepSound(soundTypeStone);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, Boolean.valueOf(false)));
		setCreativeTab(MBCreativeTabs.tabBlocks);
	}

	@Override
	public Rotation getRotationPattern() {
		return BlockRotationData.Rotation.PISTON_CONTAINER;
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase entity) {
		EnumFacing enumfacing = entity.getHorizontalFacing();
		return super.onBlockPlaced(world, pos, face, hitX, hitY, hitZ, meta, entity).withProperty(FACING, enumfacing).withProperty(POWERED, false);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
		EnumFacing face = EnumFacing.fromAngle(entity.rotationYaw);
		world.setBlockState(pos, state.withProperty(FACING, face.getOpposite()).withProperty(POWERED, false));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean powered = false;
		int facing = meta;

		if (facing >= 4) {
			facing -= 4;
			powered = true;
		}
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(POWERED, powered);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing rot = (EnumFacing) state.getValue(FACING);
		int i;
		i = rot.getHorizontalIndex();
		if (((Boolean) state.getValue(POWERED)).booleanValue()) {
			i += 4;
		}
		return i;
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { FACING, POWERED });
	}

	@Override
	public boolean canProvidePower() {
		return true;
	}

	protected boolean canPowerSide(Block blockIn) {
		return true;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
		EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

		if (enumfacing == side && !((Boolean) state.getValue(POWERED)).booleanValue()) {
			return 15;
		} else {
			return 0;
		}
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
		return 0;
	}

	/**
	 * Called when a neighboring block changes.
	 */
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if (neighborBlock.canProvidePower()) {
			worldIn.scheduleUpdate(pos, this, 1);
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

		if (worldIn.getStrongPower(pos.offset(enumfacing)) == 0) {
			worldIn.setBlockState(pos, state.withProperty(FACING, (EnumFacing) state.getValue(FACING)).withProperty(POWERED, true), 3);
			worldIn.setBlockState(pos.up(2), ModBlocks.force_field_blue.getDefaultState().withProperty(BlockForceFieldBlue.FACING, (EnumFacing) state.getValue(FACING)).withProperty(BlockForceFieldBlue.STATE, 1), 3);
		} else if (worldIn.getStrongPower(pos.offset(enumfacing)) > 0) {
			worldIn.setBlockState(pos, state.withProperty(FACING, (EnumFacing) state.getValue(FACING)).withProperty(POWERED, false), 3);
			worldIn.setBlockToAir(pos.up(2));
			worldIn.setBlockState(pos.up(2), ModBlocks.force_field_blue.getDefaultState().withProperty(BlockForceFieldBlue.FACING, (EnumFacing) state.getValue(FACING)).withProperty(BlockForceFieldBlue.STATE, 0), 3);
		}
	}

}