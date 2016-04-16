package com.Splosions.ModularBosses.blocks;

import java.util.Random;

import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;
import com.Splosions.ModularBosses.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class BlockPhaseFire extends Block

{

	//world.scheduleBlockUpdate(wx, wy, wz, block, ticks);
	
	public BlockPhaseFire(Material material) {
		super(material);
		disableStats();
		setBlockUnbreakable();
		setLightLevel(5.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(MBCreativeTabs.tabBlocks);
		this.setTickRandomly(true);

	}
	
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, 100);		
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		//worldIn.setBlockToAir(pos);
		System.out.println("ERPDERP");
		worldIn.scheduleUpdate(pos, this, 100);
	}
		
	
    /**
     * Called When an Entity Collided with the Block
     */
	@Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn instanceof EntityPlayer && !worldIn.isRemote){
        	MBExtendedPlayer.get((EntityPlayer)entityIn).limboTime = 100;
        }
        
        if (entityIn instanceof EntityPlayer && worldIn.isRemote){
        	((ClientProxy) ModularBosses.proxy).sobelShader();
        }
        
        worldIn.setBlockToAir(pos);
    }
	
    /**
	@Override
	public boolean isCollidable() {
		return false;
	}
     */


	@Override
	public boolean isOpaqueCube() {
		return false;
	}



	@Override
	public boolean isFullCube() {
		return false;
	}



	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}



	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state) {
		return null;
	}



	@Override
	public boolean canEntityDestroy(IBlockAccess world, BlockPos pos, Entity entity) {
		return false;
	}






	
	/**

	 * Extinguishes the flames at this location, setting the block to air if flames are not renewable

	 */

	protected void extinguishFlame(World world, BlockPos pos) {
			world.setBlockToAir(pos);
	}



	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return world.isSideSolid(pos.down(), EnumFacing.UP);
	}



	






}