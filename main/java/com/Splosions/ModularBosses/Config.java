package com.Splosions.ModularBosses;

import java.io.File;

import com.Splosions.ModularBosses.entity.EntityChorpChorp;
import com.Splosions.ModularBosses.entity.EntityEyeballOctopus;
import com.Splosions.ModularBosses.entity.EntityGolem;
import com.Splosions.ModularBosses.entity.EntityHeavyChorp;
import com.Splosions.ModularBosses.entity.EntityParagon;
import com.Splosions.ModularBosses.entity.EntitySkull;
import com.Splosions.ModularBosses.entity.EntityTatters;
import com.Splosions.ModularBosses.entity.EntityTick;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
	public static Configuration config;
	/*================== DIMENSION & DUNGEON SETTINGS  =====================*/
	public static int bossDimension;
	public static int buildsPerTick;
	
	public static int WormRoomSizeX;
	public static int WormRoomSizeY;
	public static int WormRoomSizeZ;
	
	public static boolean debugHitboxes;
	/*================== BOSS & MONSTER SETTINGS  =====================*/
	
	
	/*================== SHADE HOWLER SETTINGS  =====================*/
	public static double shadehowlerMaxHealth;
	public static double shadehowlerTouchDmg;
	
	
	/*================== SKULL SETTINGS  =====================*/
	public static double skullMaxHealth;
	public static double skullTouchDmg;
	
	/*================== GOLEM SETTINGS  =====================*/
	public static double golemMaxHealth;
	public static double golemBoulderDmg;
	public static double golemPunchDmg;
	
	/*================== MOLDORM SETTINGS  =====================*/
	public static double moldormMaxHealth;
	public static double moldormTouchDmg;
	
	
	
	/*================== WEAPON & TOOL SETTINGS  =====================*/
	/*================== LEGEND'S SWORD SETTINGS  =====================*/
	public static int legendsSwordDmg;
	
	/*================== LEGEND'S BOW SETTINGS  =====================*/
	public static int legendsBowDmg;
	public static int legendsBowEnergyDmg;
	
	/*================== TATTERS SCYTHE SETTINGS  =====================*/
	public static int tattersScytheDmg;
	public static int tattersScytheThrowDmg;
	
	
	
	public static void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + Reference.CONFIG_PATH));
		config.load();
		
		bossDimension = config.get("000 Dimension Config", "[Config] The Custom Dimension ID [1+]", -3).getInt();
		buildsPerTick = config.get("000 Dimension Config", "[Config] How many blocks per tick for a dungeon to build [1+]", 5000).getInt();
		
		WormRoomSizeX = config.get("001 Worm Dungeon", "[Worm Room Witdth] [1+]", 21).getInt();
		WormRoomSizeY = config.get("001 Worm Dungeon", "[Worm Room Height] [1+]", 10).getInt();
		WormRoomSizeZ = config.get("001 Worm Dungeon", "[Worm Room Length] [1+]", 21).getInt();
		
		debugHitboxes = config.get("002 Config", "[Config] Show Multi-part monster hitboxes", false).getBoolean(false);
		
		config.addCustomCategoryComment("101 Weapon & Item Settings", "This Category Contains Settings For All Of The Various Weapons And Items From Modular Bosses");
		
		legendsSwordDmg = config.get("101 Weapon & Item Settings", "[Weapon Dmg] Legend's Sword Melee Damage [1+]", 20).getInt();
		
		legendsBowDmg = config.get("101 Weapon & Item Settings", "[Weapon Dmg] Legend's Bow Un-Charged Arrow Damage [1+]", 10).getInt();
		legendsBowEnergyDmg = config.get("101 Weapon & Item Settings", "[Weapon Dmg] Legend's Bow Charged Arrow Damage [1+]", 20).getInt();
		
		tattersScytheDmg = config.get("101 Weapon & Item Settings", "[Weapon Dmg] Tatter's Scythe Melee Damage [1+]", 20).getInt();
		tattersScytheThrowDmg = config.get("101 Weapon & Item Settings", "[Weapon Dmg] Tatter's Scythe Thrown Damage [1+]", 10).getInt();
		
		
		
		config.save();
	}
	
	
	public static void postInit() {
		config.addCustomCategoryComment("200 Boss & Monster Settings", "This Category Contains Settings For All Of The Various Bosses And Monsters From Modular Bosses");

		EntityParagon.postInitConfig(config); 
		EntityTatters.postInitConfig(config);
		EntityChorpChorp.postInitConfig(config); 
		EntityHeavyChorp.postInitConfig(config);
		EntityGolem.postInitConfig(config); 
		EntityEyeballOctopus.postInitConfig(config);
		EntityTick.postInitConfig(config);
		EntitySkull.postInitConfig(config);

		
		if (config.hasChanged()) {
			config.save();
		}
	}
	
	
}
