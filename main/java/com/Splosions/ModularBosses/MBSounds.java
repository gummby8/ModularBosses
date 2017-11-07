package com.Splosions.ModularBosses;

import java.util.ArrayList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * 
 * REMEMBER TO PUT THE SOUNDS IN THE SOUND.JSON
 *
 */
public class MBSounds {
	private static ArrayList<SoundEvent> events = new ArrayList<>();
	
	
	// AMBIENT SOUNDS
	public static final SoundEvent LIMBO = createSoundEvent("limbo");
	
	// MOB SOUNDS
	public static final SoundEvent PARAGON_LIVING = createSoundEvent("paragon_living");
	public static final SoundEvent PARAGON_RFOOT = createSoundEvent("paragon_walk_rfoot");
	public static final SoundEvent PARAGON_LFOOT = createSoundEvent("paragon_walk_lfoot");
	public static final SoundEvent PARAGON_FURNACE_DEFLECT = createSoundEvent("paragon_furnace_deflect");
	public static final SoundEvent PARAGON_FURNACE_HURT = createSoundEvent("paragon_furnace_hurt");
	public static final SoundEvent PARAGON_KNEE_HURT = createSoundEvent("paragon_knee_hurt");
	public static final SoundEvent PARAGON_CHEST_DOOR_CLOSE = createSoundEvent("paragon_chest_door_close");
	public static final SoundEvent PARAGON_STAND_UP = createSoundEvent("paragon_stand_up");
	public static final SoundEvent PARAGON_COLLAPSE = createSoundEvent("paragon_collapse");
	public static final SoundEvent PARAGON_JUMP_JETS = createSoundEvent("paragon_jump_jets");
	public static final SoundEvent PARAGON_FLAME_THROWER = createSoundEvent("paragon_flame_thrower");
	

	public static final SoundEvent TATTERS_LIVE = createSoundEvent("tatters_live");
	public static final SoundEvent TATTERS_HURT = createSoundEvent("tatters_hurt");
	public static final SoundEvent TATTERS_TELEPORT = createSoundEvent("tatters_teleport");
	public static final SoundEvent TATTERS_DEATH = createSoundEvent("tatters_death");		
	
	public static final SoundEvent HEART_UP = createSoundEvent("heart_up");
	public static final SoundEvent HEART_DOWN = createSoundEvent("heart_down");
	
	public static final SoundEvent CHORP_SLIME = createSoundEvent("chorp_slime");
	public static final SoundEvent CHORP_DEATH = createSoundEvent("chorp_death");
	public static final SoundEvent CHORP_HURT = createSoundEvent("chorp_hurt");
	
	public static final SoundEvent GOLEM_LIVING = createSoundEvent("golem_living");
	public static final SoundEvent GOLEM_BUILD = createSoundEvent("golem_build");
	public static final SoundEvent GOLEM_ROLL = createSoundEvent("golem_roll");
	public static final SoundEvent BOULDER_HIT = createSoundEvent("boulder_hit");

	public static void registerSounds(RegistryEvent.Register<SoundEvent> event){
		IForgeRegistry<SoundEvent> reg = event.getRegistry();
		events.forEach(ev -> reg.register(ev));
		
		//can be deleted afterwards
		events = null;
	}
	
	private static SoundEvent createSoundEvent(String name){
		SoundEvent event = new SoundEvent(new ResourceLocation(Reference.MOD_ID, name));
		event.setRegistryName(Reference.MOD_ID, name);
		events.add(event);
		return event;
	}
	
	
}
