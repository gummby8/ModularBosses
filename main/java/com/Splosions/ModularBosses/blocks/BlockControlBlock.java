
package com.Splosions.ModularBosses.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.BlockRotationData.Rotation;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.handler.GuiHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockControlBlock extends Block implements IVanillaRotation
{
	

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	
	
	public BlockControlBlock(Material material) {
		super(material);
		setHardness(-1.0F);
		setHarvestLevel("pickaxe", 2);
		setCreativeTab(ModularBosses.tabBlocks);
		
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityControlBlock();
	}








	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(pos);
			if (world.isRemote && te instanceof TileEntityControlBlock && player.getHeldItem(hand) == null) {
					player.openGui(ModularBosses.INSTANCE, GuiHandler.GUI_EDIT_CONTROL_BLOCK, player.world, pos.getX(), pos.getY(), pos.getZ());
			}
			String msg = ((TileEntityControlBlock) te).getMessage();
		return true;
	}
	
	


		


	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		return;
	}

	@Override
	public Rotation getRotationPattern() {
		return BlockRotationData.Rotation.PISTON_CONTAINER;
	}



	@Override
	  public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getStateFromMeta(meta).withProperty(FACING, face.getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
		EnumFacing face = EnumFacing.fromAngle(entity.rotationYaw);
		world.setBlockState(pos, state.withProperty(FACING, face.getOpposite()), 3);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
	
	
	
	
	
	
	
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
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

    
    protected boolean canPowerSide(Block blockIn)
    {
        return true;
    }
	
	

	@Override
	public int getWeakPower(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	TileEntityControlBlock te = (TileEntityControlBlock) blockAccess.getTileEntity(pos);
    	if (state.getValue(FACING) == side && te.foundList.size() == 0 && te.firstSpawn) {
    		return 15;
    	} else {
    		return 0;
    	}
    }
    
    protected void getPowerOnSides(IBlockAccess worldIn, BlockPos pos, IBlockState state)
    {
    	TileEntityControlBlock te = (TileEntityControlBlock) worldIn.getTileEntity(pos);
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        EnumFacing enumfacing1 = enumfacing.rotateY();
        te.triggerPower = this.getPowerOnSide(worldIn, pos.offset(enumfacing1), enumfacing1) != 0 ? 1 : 0;
        te.inputPower = this.getPowerOnSide(worldIn, pos.offset(enumfacing), enumfacing) != 0 ? 1 : 0;
    }
    
    protected int getPowerOnSide(IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	
        IBlockState iblockstate = blockAccess.getBlockState(pos);
        Block block = iblockstate.getBlock();
        return this.canPowerSide(block) ? block.getWeakPower(iblockstate, blockAccess, pos, side) : 0;
    }
    
    
    /**
     * Called when a neighboring block changes.
     */
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
    	getPowerOnSides(worldIn, pos, state);
    }
	
}