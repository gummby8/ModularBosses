package com.Splosions.ModularBosses.world;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.common.util.Constants;

public class PortalLandingWorldData extends WorldSavedData {

	

	public ArrayList<String> portalLandingList = new ArrayList<String>();

	
	   public PortalLandingWorldData(String name) {
		super(name);

	}
	   


		@Override
		public void readFromNBT(NBTTagCompound compound) {
			NBTTagList tagList = compound.getTagList("portalLandings", Constants.NBT.TAG_STRING);
			for(int i = 0; i < tagList.tagCount(); i++) {
				  String s = tagList.getStringTagAt(i);
				  portalLandingList.add(i, s);
				 }
		}

		@Override
		public void writeToNBT(NBTTagCompound compound) {
			NBTTagList tagList = new NBTTagList();
			 for(int i = 0; i < portalLandingList.size(); i++){
			  String s = portalLandingList.get(i);
			  	if(s != null){
			  		NBTTagString tag = new NBTTagString(s);
			  		tagList.appendTag(tag);
		  			}
			 }
			 compound.setTag("portalLandings", tagList);
			
		}


		public void addPortalLanding(int floor, double posX, double posY, double posZ) {
			String s = floor + "," + posX + "," + posY + "," + posZ;
			portalLandingList.add(s);
		}



		
		public void deletePortalLanding(int floor, double posX, double posY, double posZ){
			String s = floor + "," + posX + "," + posY + "," + posZ;
			portalLandingList.remove(s);
		}
		
		

	
}
