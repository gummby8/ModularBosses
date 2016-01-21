package com.Splosions.ModularBosses.network.server;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.Splosions.ModularBosses.network.AbstractMessage.AbstractServerMessage;

public class SetControlBlockMessagePacket extends AbstractServerMessage<SetControlBlockMessagePacket>
{
	private BlockPos pos;
	private String message;

	public SetControlBlockMessagePacket() {}

	public SetControlBlockMessagePacket(TileEntityControlBlock te) {
		 
		this.pos = te.getPos();
		this.message = te.getMessage();
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		this.pos = BlockPos.fromLong(buffer.readLong());
		this.message = ByteBufUtils.readUTF8String(buffer);

	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeLong(this.pos.toLong());
		System.out.println("Write? " + this.pos.getX());
		ByteBufUtils.writeUTF8String(buffer, this.message);
	}

	@Override
	protected void process(EntityPlayer player, Side side) {
		TileEntity te = player.worldObj.getTileEntity(this.pos);
		if (te instanceof TileEntityControlBlock) {
			((TileEntityControlBlock) te).setMessage(this.message);
			System.out.println("Processed? " + this.message);
			System.out.println("Processed? " + this.pos.getX());
		}
	}
}