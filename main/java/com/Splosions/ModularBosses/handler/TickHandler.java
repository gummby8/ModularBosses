package com.Splosions.ModularBosses.handler;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.util.schematic.Dungeon;
import com.Splosions.ModularBosses.util.schematic.DungeonNurkach;
import com.Splosions.ModularBosses.util.schematic.Schematic;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickHandler {


	
	@SubscribeEvent
	public void worldTick(TickEvent.WorldTickEvent event) {
		if(!ModularBosses.INSTANCE.dungeonList.isEmpty() && event.phase == TickEvent.Phase.START && event.world.provider.getDimensionId() == 0) {
			int dungeonCount = ModularBosses.INSTANCE.dungeonList.size();
			for (int x = 0; x < dungeonCount; x++) {
				Dungeon dungeon = ModularBosses.INSTANCE.dungeonList.get(x);
				if(!dungeon.finishedBuilding){
					Schematic.staggeredBuild(MinecraftServer.getServer().worldServerForDimension(Config.bossDimension), dungeon);
					break;//only 1 dungeon can be building at a time
				} else {
					if (!(dungeon instanceof DungeonNurkach)){ //worm dungeons are removed by the worm return portal upon activation
						ModularBosses.INSTANCE.dungeonList.remove(x);
						break;
					}
				}
			}
		}
	}
	
	
	
	
}
