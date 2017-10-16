package com.Splosions.ModularBosses.network;

import java.util.Collection;

import com.Splosions.ModularBosses.Reference;
import com.Splosions.ModularBosses.network.client.OpenControlBlockEditorPacket;
import com.Splosions.ModularBosses.network.server.SetControlBlockMessagePacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


/**
 * 
 * Houses the SimpleNetworkWrapper instance and provides wrapper methods for sending packets.
 *
 */
public class PacketDispatcher
{
	private static byte packetId = 0;

	private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.CHANNEL);

	/**
	 *  Registers all packets and handlers - call this during {@link FMLPreInitializationEvent}
	 */
	public static final void preInit() {
		// Bidirectional packets


		// Packets handled on CLIENT

		registerMessage(OpenControlBlockEditorPacket.class);


		// Packets handled on SERVER

		registerMessage(SetControlBlockMessagePacket.class);

	}

	/**
	 * Registers an {@link AbstractMessage} to the appropriate side(s)
	 */
	private static final <T extends AbstractMessage<T> & IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz) {
		if (AbstractMessage.AbstractClientMessage.class.isAssignableFrom(clazz)) {
			PacketDispatcher.dispatcher.registerMessage(clazz, clazz, packetId++, Side.CLIENT);
		} else if (AbstractMessage.AbstractServerMessage.class.isAssignableFrom(clazz)) {
			PacketDispatcher.dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		} else {
			PacketDispatcher.dispatcher.registerMessage(clazz, clazz, packetId, Side.CLIENT);
			PacketDispatcher.dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		}
	}

	/**
	 * Send this message to the specified player's client-side counterpart.
	 * See {@link SimpleNetworkWrapper#sendTo(IMessage, EntityPlayerMP)}
	 */
	public static final void sendTo(IMessage message, EntityPlayerMP player) {
		PacketDispatcher.dispatcher.sendTo(message, player);
	}

	/**
	 * Sends this message to players provided. SERVER->CLIENT only.
	 */
	public static void sendToPlayers(IMessage message, Collection<EntityPlayer> players) {
		for (EntityPlayer player : players) {
			if (player instanceof EntityPlayerMP) {
				PacketDispatcher.dispatcher.sendTo(message, (EntityPlayerMP) player);
			}
		}
	}

	/**
	 * Send this message to everyone.
	 * See {@link SimpleNetworkWrapper#sendToAll(IMessage)}
	 */
	public static void sendToAll(IMessage message) {
		PacketDispatcher.dispatcher.sendToAll(message);
	}

	/**
	 * Send this message to everyone within a certain range of a point.
	 * See {@link SimpleNetworkWrapper#sendToAllAround(IMessage, NetworkRegistry.TargetPoint)}
	 */
	public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
		PacketDispatcher.dispatcher.sendToAllAround(message, point);
	}

	/**
	 * Sends a message to everyone within a certain range of the coordinates in the same dimension.
	 * Shortcut to {@link SimpleNetworkWrapper#sendToAllAround(IMessage, NetworkRegistry.TargetPoint)}
	 */
	public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
		PacketDispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
	}

	/**
	 * Sends a message to everyone within a certain range of the entity provided.
	 * Shortcut to {@link SimpleNetworkWrapper#sendToAllAround(IMessage, NetworkRegistry.TargetPoint)}
	 */
	public static final void sendToAllAround(IMessage message, Entity entity, double range) {
		PacketDispatcher.sendToAllAround(message, entity.world.provider.getDimension(), entity.posX, entity.posY, entity.posZ, range);
	}

	/**
	 * Send this message to everyone within the supplied dimension.
	 * See {@link SimpleNetworkWrapper#sendToDimension(IMessage, int)}
	 */
	public static final void sendToDimension(IMessage message, int dimensionId) {
		PacketDispatcher.dispatcher.sendToDimension(message, dimensionId);
	}

	/**
	 * Send this message to the server.
	 * See {@link SimpleNetworkWrapper#sendToServer(IMessage)}
	 */
	public static final void sendToServer(IMessage message) {
		//System.out.println("sendToServer? " + message);
		PacketDispatcher.dispatcher.sendToServer(message);
	}

	/**
	 * Sends a vanilla Packet to a player. SERVER->CLIENT only.
	 */
	public static void sendTo(Packet packet, EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			((EntityPlayerMP) player).playerNetServerHandler.sendPacket(packet);
		}
	}

	/**
	 * Sends a vanilla Packet to players provided. SERVER->CLIENT only.
	 */
	public static void sendToPlayers(Packet packet, Collection<EntityPlayer> players) {
		for (EntityPlayer player : players) {
			if (player instanceof EntityPlayerMP) {
				((EntityPlayerMP) player).playerNetServerHandler.sendPacket(packet);
			}
		}
	}

	/**
	 * Sends a vanilla Packet to all players in the list except for the one player. SERVER->CLIENT only.
	 */
	public static void sendToPlayersExcept(Packet packet, EntityPlayer player, Collection<EntityPlayer> players) {
		for (EntityPlayer p : players) {
			if (p != player && p instanceof EntityPlayerMP) {
				((EntityPlayerMP) p).playerNetServerHandler.sendPacket(packet);
			}
		}
	}

	/**
	 * Sends a vanilla Packet to all players in the same dimension. SERVER->CLIENT only.
	 */
	public static void sendToAll(Packet packet, World world) {
		if (world instanceof WorldServer) {
			for (Object o : ((WorldServer) world).playerEntities) {
				if (o instanceof EntityPlayerMP) {
					((EntityPlayerMP) o).playerNetServerHandler.sendPacket(packet);
				}
			}
		}
	}

	/**
	 * Sends a vanilla Packet to all players within the given range of an entity. SERVER->CLIENT only.
	 */
	public static void sendToAllAround(Packet packet, Entity entity, int range) {
		int rangeSq = (range * range);
		if (entity.world instanceof WorldServer) {
			for (Object o : ((WorldServer) entity.world).playerEntities) {
				if (o instanceof EntityPlayerMP) {
					EntityPlayerMP player = (EntityPlayerMP) o;
					if (player.getDistanceSqToEntity(entity) <= rangeSq) {
						((EntityPlayerMP) o).playerNetServerHandler.sendPacket(packet);
					}
				}
			}
		}
	}
}