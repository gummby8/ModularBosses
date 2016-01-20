package com.Splosions.ModularBosses.handler;

import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.client.gui.GuiEditControlBlock;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class GuiHandler implements IGuiHandler
{
	public static final int GUI_EDIT_CONTROL_BLOCK = 6;
		



	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		switch(id) {
		case GUI_EDIT_CONTROL_BLOCK:
			if (te == null) {
				// modeled after vanilla sign editor handling, since TE is not yet available on client
				te = new TileEntityControlBlock();
				te.setWorldObj(world);
				te.setPos(new BlockPos(x, y, z));
			}
			if (te instanceof TileEntityControlBlock) {
				return new GuiEditControlBlock((TileEntityControlBlock) te);
			}
			return null;
		
		}
		return null;
	}



	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}
}