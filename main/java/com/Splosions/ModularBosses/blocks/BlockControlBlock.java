
package com.Splosions.ModularBosses.blocks;

import java.util.Random;


import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.BlockRotationData.Rotation;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.handler.GuiHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockControlBlock extends Block implements IVanillaRotation
{
	

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	
	
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing face, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(pos);
			if (world.isRemote && te instanceof TileEntityControlBlock && player.getHeldItem() == null) {
			
					//System.out.println("GUI?");
					player.openGui(ModularBosses.INSTANCE, GuiHandler.GUI_EDIT_CONTROL_BLOCK, player.worldObj, pos.getX(), pos.getY(), pos.getZ());

			}
			
			String msg = ((TileEntityControlBlock) te).getMessage();
			//System.out.println(msg);
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
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase entity) {
		return getStateFromMeta(meta).withProperty(FACING, face.getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
		EnumFacing face = EnumFacing.fromAngle(entity.rotationYaw);
		/**		
		if (entity.rotationPitch < -45.0F) {
			face = EnumFacing.UP;
		} else if (entity.rotationPitch > 45.0F) {
			face = EnumFacing.DOWN;
		}
		*/
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
	protected BlockState createBlockState() {
		return new BlockState(this, FACING);
	}

	
	
	
	
	
	
	
	
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public boolean canProvidePower()
	{
	 return true;
	}
    
    protected boolean canPowerSide(Block blockIn)
    {
        return true;
    }
	
	

	@Override
    public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side)
    {
    	TileEntityControlBlock te = (TileEntityControlBlock) worldIn.getTileEntity(pos);
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
        //System.out.println("Trigger = " + this.getPowerOnSide(worldIn, pos.offset(enumfacing1), enumfacing1));
        //System.out.println("Input = " + this.getPowerOnSide(worldIn, pos.offset(enumfacing), enumfacing));
    }
    
    protected int getPowerOnSide(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
    	
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        //System.out.println(block.isProvidingWeakPower(worldIn, pos, iblockstate, side));
        //System.out.println(this.canPowerSide(block) ? block.isProvidingWeakPower(worldIn, pos, iblockstate, side) : 0);
        return this.canPowerSide(block) ? block.isProvidingWeakPower(worldIn, pos, iblockstate, side) : 0;
    }
    
    
    /**
     * Called when a neighboring block changes.
     */
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
    	getPowerOnSides(worldIn, pos, state);
    }
	
}