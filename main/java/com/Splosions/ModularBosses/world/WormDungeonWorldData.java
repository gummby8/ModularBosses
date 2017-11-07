package com.Splosions.ModularBosses.world;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

public class WormDungeonWorldData extends WorldSavedData {

	//If I want worms to persist across server restarts I am going to have to use this

	public ArrayList<String> wormDungeonList = new ArrayList<String>();

	
	   public WormDungeonWorldData(String name) {
		super(name);

	}
	   


		@Override
		public void readFromNBT(NBTTagCompound compound) {
			NBTTagList tagList = compound.getTagList("wormDungeons", Constants.NBT.TAG_STRING);
			for(int i = 0; i < tagList.tagCount(); i++) {
				  String s = tagList.getStringTagAt(i);
				  wormDungeonList.add(i, s);
				 }
		}

		@Override
		public void writeToNBT(NBTTagCompound compound) {
			NBTTagList tagList = new NBTTagList();
			 for(int i = 0; i < wormDungeonList.size(); i++){
			  String s = wormDungeonList.get(i);
			  	if(s != null){
			  		NBTTagString tag = new NBTTagString(s);
			  		tagList.appendTag(tag);
		  			}
			 }
			 compound.setTag("wormDungeons", tagList);
			
		}


		public void addPortalLanding(int floor, double posX, double posY, double posZ) {
			String s = floor + "," + posX + "," + posY + "," + posZ;
			wormDungeonList.add(s);
		}



		
		public void deletePortalLanding(int floor, double posX, double posY, double posZ){
			String s = floor + "," + posX + "," + posY + "," + posZ;
			wormDungeonList.remove(s);
		}
		
		

	
}
