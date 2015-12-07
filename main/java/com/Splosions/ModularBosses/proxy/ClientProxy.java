package com.Splosions.ModularBosses.proxy;

import java.util.Map;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.entity.ModularBossesEntities;
import com.Splosions.ModularBosses.items.ModularBossesItems;
import com.google.common.collect.Maps;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;

public class ClientProxy extends CommonProxy{
	
	

	
	@Override
	public void registerRenders() {
		ModularBossesItems.registerRenders();
		ModularBossesEntities.registerRenderers();
	}
	
	

	
}
