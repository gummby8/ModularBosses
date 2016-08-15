package com.Splosions.ModularBosses.util;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.util.schematic.Dungeon;
import com.Splosions.ModularBosses.util.schematic.Schematic;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickHandler {

	
	
	@SubscribeEvent
	public void worldTick(TickEvent.WorldTickEvent event) {
		if(!ModularBosses.instance.dungeonList.isEmpty() && event.phase == TickEvent.Phase.START && event.world.provider.getDimensionId() == 0) {
			int dungeonCount = ModularBosses.instance.dungeonList.size();
			for (int x = 0; x < dungeonCount; x++) {
				Dungeon dungeon = ModularBosses.instance.dungeonList.get(x);
				if(!dungeon.finishedBuilding){
					Schematic.staggeredBuild(MinecraftServer.getServer().worldServerForDimension(Config.bossDimension), dungeon);
				} else {
					ModularBosses.instance.dungeonList.remove(x);
					break;
				}
			}
		}
	}
	
	
	
	
}
