
package com.Splosions.ModularBosses.blocks;

import java.util.List;

import com.Splosions.ModularBosses.MBCreativeTabs;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.client.entity.CustomEntityList;
import com.Splosions.ModularBosses.handler.GuiHandler;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.client.OpenControlBlockEditorPacket;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockControlBlock extends Block
{
	


	public BlockControlBlock(Material material) {
		super(material);
		setHardness(10.0F);
		setHarvestLevel("pickaxe", 2);
		setStepSound(soundTypeStone);
		setCreativeTab(MBCreativeTabs.tabBlocks);
		
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
			if (world.isRemote && te instanceof TileEntityControlBlock) {
				ItemStack helm = player.getEquipmentInSlot(0);
				if (helm == null) {
									
					System.out.println("GUI?");
					player.openGui(ModularBosses.instance, GuiHandler.GUI_EDIT_CONTROL_BLOCK, player.worldObj, pos.getX(), pos.getY(), pos.getZ());
					
				} else {
			
					

					
				}
			}
			
			String msg = ((TileEntityControlBlock) te).getMessage();
			System.out.println(msg);
		return true;
	}
	
	
	@SideOnly(Side.CLIENT)
	public void openGui(BlockPos pos, EntityPlayer player, EnumFacing face){
		
			System.out.println("Packet sent");
			PacketDispatcher.sendTo(new OpenControlBlockEditorPacket(pos.offset(face)), (EntityPlayerMP) player);
			
	}

		


	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		if (world.isRemote) {
			return;
		}

	}





	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 8));
	}
	
}