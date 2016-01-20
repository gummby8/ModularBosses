package com.Splosions.ModularBosses.items;

import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.client.OpenControlBlockEditorPacket;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;


public class ItemControlBlock extends ItemBlockUnbreakable {

	public ItemControlBlock(Block block) {
		super(block);
		setMaxStackSize(16);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ) {
		if (!super.onItemUse(stack, player, world, pos, face, hitX, hitY, hitZ)) {
			return false;
		} else if (player instanceof EntityPlayerMP) {
			System.out.println("GUI?");	
			PacketDispatcher.sendTo(new OpenControlBlockEditorPacket(pos.offset(face)), (EntityPlayerMP) player);
		}
		return true;
	}
}