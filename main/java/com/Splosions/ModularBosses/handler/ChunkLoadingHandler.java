package com.Splosions.ModularBosses.handler;

import java.util.List;

import com.Splosions.ModularBosses.blocks.tileentity.TileEntityChunkLoader;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class ChunkLoadingHandler implements LoadingCallback
{
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) 
	{
		for (Ticket ticket : tickets) 
		{
			int x = ticket.getModData().getInteger("xCoord");
			int y = ticket.getModData().getInteger("yCoord");
			int z = ticket.getModData().getInteger("zCoord");
			TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
			if ((te instanceof TileEntityChunkLoader)) 
			{
				((TileEntityChunkLoader) te).forceChunkLoading(ticket);
			}
		}
	}
}