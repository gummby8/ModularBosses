
package com.Splosions.ModularBosses.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.BlockRotationData.Rotation;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockForceFieldGen extends Block implements IVanillaRotation {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool POWERED = PropertyBool.create("powered");

	public BlockForceFieldGen(Material material) {
		super(material);
		setHardness(10.0F);
		setHarvestLevel("pickaxe", 2);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, Boolean.valueOf(false)));
		setSoundType(SoundType.STONE);
		setCreativeTab(ModularBosses.tabBlocks);
	}

	@Override
	public Rotation getRotationPattern() {
		return BlockRotationData.Rotation.PISTON_CONTAINER;
	}

	/**
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase entity) {
		EnumFacing enumfacing = entity.getHorizontalFacing();
		return super.onBlockPlaced(world, pos, face, hitX, hitY, hitZ, meta, entity).withProperty(FACING, enumfacing).withProperty(POWERED, false);
	}
	*/

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
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(POWERED,
				powered);
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



    /**
     * Determine if this block can make a redstone connection on the side provided,
     * Useful to control which sides are inputs and outputs for redstone wires.
     *
     * @param state The current state
     * @param world The current world
     * @param pos Block position in world
     * @param side The side that is trying to make the connection, CAN BE NULL
     * @return True to make the connection
     */
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side)
    {
        return state.canProvidePower() && side != null;
    }
    
    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */

    @Override
    public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

	protected boolean canPowerSide(Block blockIn) {
		return true;
	}

	@Override
	public int getWeakPower(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
		if (enumfacing == side && !((Boolean) state.getValue(POWERED)).booleanValue()) {
			return 15;
		} else {
			return 0;
		}
	}


	/**
	 * Called when a neighboring block changes.
	 */
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if (neighborBlock.canProvidePower(state)) {
			worldIn.scheduleUpdate(pos, this, 40);
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
		if (!worldIn.isRemote) {
			if (worldIn.getStrongPower(pos.offset(enumfacing)) == 0) {
				worldIn.setBlockState(pos,state.withProperty(FACING, (EnumFacing) state.getValue(FACING)).withProperty(POWERED, true), 3);
				worldIn.setBlockState(pos.up(2),ModBlocks.FORCE_FIELD.getDefaultState().withProperty(BlockForceFieldBlue.FACING, (EnumFacing) state.getValue(FACING)).withProperty(BlockForceFieldBlue.STATE, 1),3);
			} else if (worldIn.getStrongPower(pos.offset(enumfacing)) > 0) {
				worldIn.setBlockState(pos,state.withProperty(FACING, (EnumFacing) state.getValue(FACING)).withProperty(POWERED, false),3);
				worldIn.setBlockToAir(pos.up(2));
				worldIn.setBlockState(pos.up(2),ModBlocks.FORCE_FIELD.getDefaultState().withProperty(BlockForceFieldBlue.FACING, (EnumFacing) state.getValue(FACING)).withProperty(BlockForceFieldBlue.STATE, 0),3);
			}
		}
	}

}