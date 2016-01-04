package com.Splosions.ModularBosses.proxy;

import java.util.Map;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.client.entity.ModularBossesEntities;
import com.Splosions.ModularBosses.items.IModItem;
import com.Splosions.ModularBosses.items.ModularBossesItems;
import com.google.common.collect.Maps;
import java.lang.reflect.Field;
import net.minecraft.item.Item;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.ModelBakeEvent;

public class ClientProxy extends CommonProxy{
	
	private final Minecraft mc = Minecraft.getMinecraft();

	
	@Override
	public void registerRenders() {
		ModularBossesEntities.registerRenderers();
		ModularBossesItems.registerRenders();
		
		try {
			for (Field f: ModularBossesItems.class.getFields()) {
				if (Item.class.isAssignableFrom(f.getType())) {
					Item item = (Item) f.get(null);
					if (item instanceof IModItem) {
						((IModItem) item).registerRenderers(mc.getRenderItem().getItemModelMesher());
					}
				}
			}
		} catch(Exception e) {
			System.out.println("DERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERPDERP");
		}
	}
	
	
	
	
	
	
	

	

	
}
