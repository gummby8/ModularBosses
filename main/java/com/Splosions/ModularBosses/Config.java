package com.Splosions.ModularBosses;

import java.io.File;

import com.Splosions.ModularBosses.entity.EntityChorpChorp;
import com.Splosions.ModularBosses.entity.EntityEyeballOctopus;
import com.Splosions.ModularBosses.entity.EntityHeavyChorp;
import com.Splosions.ModularBosses.entity.EntityParagon;
import com.Splosions.ModularBosses.entity.EntityTatters;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
	public static Configuration config;
	
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
		
		config.addCustomCategoryComment("Weapon & Item Settings", "This Category Contains Settings For All Of The Various Weapons And Items From Modular Bosses");
		
		legendsSwordDmg = config.get("Legend's Sword", "[Weapon Dmg] Legend's Sword Melee Damage [1+]", 20).getInt();
		
		legendsBowDmg = config.get("Legend's Bow", "[Weapon Dmg] Legend's Bow Un-Charged Arrow Damage [1+]", 10).getInt();
		legendsBowEnergyDmg = config.get("Legend's Bow", "[Weapon Dmg] Legend's Bow Charged Arrow Damage [1+]", 20).getInt();
		
		tattersScytheDmg = config.get("Tatter's Scythe", "[Weapon Dmg] Tatter's Scythe Melee Damage [1+]", 20).getInt();
		tattersScytheThrowDmg = config.get("Tatter's Scythe", "[Weapon Dmg] Tatter's Scythe Thrown Damage [1+]", 10).getInt();
		
		
		
		config.save();
	}
	
	
	public static void postInit() {
		config.addCustomCategoryComment("Boss & Monster Settings", "This Category Contains Settings For All Of The Various Bosses And Monsters From Modular Bosses");
		
		EntityParagon.postInitConfig(config); 
		EntityTatters.postInitConfig(config);
		EntityChorpChorp.postInitConfig(config); 
		EntityHeavyChorp.postInitConfig(config); 
		EntityEyeballOctopus.postInitConfig(config);
		

		
		if (config.hasChanged()) {
			config.save();
		}
	}
	
	
}
