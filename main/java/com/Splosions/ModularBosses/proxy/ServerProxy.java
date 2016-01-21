package com.Splosions.ModularBosses.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * 
 * TODO try out if this works, i.e. making CommonProxy abstract, which prevents
 * ClientProxy from calling super.getPlayerEntity etc. ClientProxy code seems
 * to be called when on integrated server, and this could very well break stuff
 * if it always only returns the client player.
 *
 */
public class ServerProxy extends CommonProxy {

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}

	@Override
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity.getServerForPlayer();
	}
}