
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


public class BlockForceFieldBlue extends Block implements IVanillaRotation
{
	

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	
	
	public BlockForceFieldBlue(Material material) {
		super(material);
		setHardness(10.0F);
		setHarvestLevel("pickaxe", 2);
		setStepSound(soundTypeStone);
		setCreativeTab(MBCreativeTabs.tabBlocks);
		
	}



	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
    public boolean isFullCube()
    {
        return false;
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
		if (entity.rotationPitch < -45.0F) {
			face = EnumFacing.UP;
		} else if (entity.rotationPitch > 45.0F) {
			face = EnumFacing.DOWN;
		}
		world.setBlockState(pos, state.withProperty(FACING, face), 3);
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


    

	


    
    
    /**
     * Called when a neighboring block changes. Places other force field blocks. 
     */
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
    	
    }
	
}