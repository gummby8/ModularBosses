
package com.Splosions.ModularBosses.blocks;

import java.util.Random;

import com.Splosions.ModularBosses.blocks.BlockRotationData.Rotation;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockForceFieldBlue extends Block implements IVanillaRotation {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyInteger STATE = PropertyInteger.create("state", 0, 2);

	public static final int STANDBY = 2;
	public static final int ON = 1;
	public static final int OFF = 0;

	public BlockForceFieldBlue(Material material) {
		super(material);
		setHardness(-1.0F);
		setHarvestLevel("pickaxe", 2);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(STATE,
				Integer.valueOf(ON)));
		// setCreativeTab(MBCreativeTabs.tabBlocks);

	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return false;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, 1);

	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if ((int)state.getValue(STATE) == ON && !worldIn.isRemote) {
			if ((EnumFacing) state.getValue(FACING) == EnumFacing.NORTH
					|| (EnumFacing) state.getValue(FACING) == EnumFacing.SOUTH) {
				if (!TargetUtils.isBlockPresentPos(worldIn, ModBlocks.force_field_gen, pos.west(10).down(10),
						pos.east(10).up(10))) {
					worldIn.setBlockToAir(pos);
					return;
				}

				if (worldIn.getBlockState(pos.east()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.east(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(ON)), 3);
				}
				if (worldIn.getBlockState(pos.west()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.west(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(ON)), 3);
				}
				if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.up(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(ON)), 3);
				}
				if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.down(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(ON)), 3);
				}
			} else { // must be facing EAST or WEST
				if (!TargetUtils.isBlockPresentPos(worldIn, ModBlocks.force_field_gen, pos.north(10).down(10),
						pos.south(10).up(10))) {
					worldIn.setBlockToAir(pos);
					return;
				}
				if (worldIn.getBlockState(pos.north()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.north(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(ON)), 3);
				}
				if (worldIn.getBlockState(pos.south()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.south(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(ON)), 3);
				}
				if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.up(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(ON)), 3);
				}
				if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR) {
					worldIn.setBlockState(pos.down(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(ON)), 3);
				}
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
					.withProperty(STATE, Integer.valueOf(STANDBY)), 3);
		} else if ((int) state.getValue(STATE) == OFF && !worldIn.isRemote) {
			if ((EnumFacing) state.getValue(FACING) == EnumFacing.NORTH
					|| (EnumFacing) state.getValue(FACING) == EnumFacing.SOUTH) {
				if (worldIn.getBlockState(pos.east()).getBlock() == ModBlocks.force_field_blue) {
					worldIn.setBlockState(pos.east(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(OFF)), 3);
					worldIn.scheduleUpdate(pos.east(), this, 1);
				}
				if (worldIn.getBlockState(pos.west()).getBlock() == ModBlocks.force_field_blue) {
					worldIn.setBlockState(pos.west(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(OFF)), 3);
					worldIn.scheduleUpdate(pos.west(), this, 1);
				}
			} else { // must be facing EAST or WEST
				if (worldIn.getBlockState(pos.north()).getBlock() == ModBlocks.force_field_blue) {
					worldIn.setBlockState(pos.north(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(OFF)), 3);
					worldIn.scheduleUpdate(pos.north(), this, 1);
				}
				if (worldIn.getBlockState(pos.south()).getBlock() == ModBlocks.force_field_blue) {
					worldIn.setBlockState(pos.south(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
							.withProperty(STATE, Integer.valueOf(OFF)), 3);
					worldIn.scheduleUpdate(pos.south(), this, 1);
				}
			}
			if (worldIn.getBlockState(pos.up()).getBlock() == ModBlocks.force_field_blue) {
				worldIn.setBlockState(pos.up(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
						.withProperty(STATE, Integer.valueOf(OFF)), 3);
				worldIn.scheduleUpdate(pos.up(), this, 1);
			}
			if (worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.force_field_blue) {
				worldIn.setBlockState(pos.down(), state.withProperty(FACING, (EnumFacing) state.getValue(FACING))
						.withProperty(STATE, Integer.valueOf(OFF)), 3);
				worldIn.scheduleUpdate(pos.down(), this, 1);
			}
			worldIn.setBlockToAir(pos);
		}

	}

	@Override
	public Rotation getRotationPattern() {
		return BlockRotationData.Rotation.PISTON_CONTAINER;
	}

	/**
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		EnumFacing enumfacing1 = placer.getHorizontalFacing().rotateY();

		
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, enumfacing1).withProperty(STATE, Integer.valueOf(meta >> 2));
	}
	 */

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity,
			ItemStack stack) {
		EnumFacing face = EnumFacing.fromAngle(entity.rotationYaw);
		world.setBlockState(pos, state.withProperty(FACING, face), 3);

	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(STATE,
				Integer.valueOf((meta & 15) >> 2));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0 | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		i |= ((Integer) state.getValue(STATE)).intValue() << 2;
		// System.out.println("Meta = " + i);
		return i;
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { FACING, STATE });
	}

	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {

		// worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	/**
	 * Called when a neighboring block changes. Places other force field blocks.
	 */
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {

	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

	@Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

}