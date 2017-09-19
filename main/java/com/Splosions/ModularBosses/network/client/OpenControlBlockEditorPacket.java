
package com.Splosions.ModularBosses.network.client;


import java.io.IOException;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.handler.GuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import com.Splosions.ModularBosses.network.AbstractMessage.AbstractClientMessage;

public class OpenControlBlockEditorPacket extends AbstractClientMessage<OpenControlBlockEditorPacket>
{
	private BlockPos pos;

	public OpenControlBlockEditorPacket() {}

	/**
	 * Constructor taking just the block position - TileEntity validated when opening GUI
	 */
	public OpenControlBlockEditorPacket(BlockPos pos) {
		this.pos = pos;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		this.pos = BlockPos.fromLong(buffer.readLong());
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeLong(pos.toLong());
	}

	@Override
	protected void process(EntityPlayer player, Side side) {
		// TileEntity checked in IGuiHandler, so no need to do so here
		player.openGui(ModularBosses.INSTANCE, GuiHandler.GUI_EDIT_CONTROL_BLOCK, player.worldObj, pos.getX(), pos.getY(), pos.getZ());
	}
}