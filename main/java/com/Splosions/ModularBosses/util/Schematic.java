package com.Splosions.ModularBosses.util;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.google.common.primitives.UnsignedBytes;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class Schematic {
   
   private short width; 
   private short height;
   private short length;
   private int size;
   private BlockObject[] blockObjects;

   
   public Schematic(String fileName) {
      try { 
         InputStream is = Schematic.class.getResourceAsStream("/assets/mb/schematics/2.schematic"); 
         NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(is);
      
         is.close();
         //System.out.println("Schematic?");
         width = nbtdata.getShort("Width");
         height = nbtdata.getShort("Height");
         length = nbtdata.getShort("Length");
         size = width * height * length;
         blockObjects = new BlockObject[size];
         
         byte[] blockIDs = nbtdata.getByteArray("Blocks");
         byte[] metadata = nbtdata.getByteArray("Data");



         
         
         int counter = 0;
         for(int Y = 0; Y < height; Y++) {
            for(int Z = 0; Z < length; Z++) {
               for(int X = 0; X < width; X++) {
                  int blockId = UnsignedBytes.toInt(blockIDs[counter]);
                  BlockPos pos = new BlockPos(X, Y, Z);
                  IBlockState state = Block.getBlockById(blockId).getStateFromMeta(metadata[counter]);
                  
                  String message = null;
                  
                  NBTTagList tagList = nbtdata.getTagList("TileEntities", Constants.NBT.TAG_COMPOUND);
         		  
                  for(int i = 0; i < tagList.tagCount(); i++) {
         		  NBTTagCompound tag = tagList.getCompoundTagAt(i);
         		  String gotMessage = tag.getString("message");
         		  int x = tag.getInteger("x");
         		  int y = tag.getInteger("y");
         		  int z = tag.getInteger("z");
         		  
         		  if (x == X && y == Y && z == Z){
         			  message = gotMessage;
         		  }

         		 
         		 }
                  
                  blockObjects[counter] = new BlockObject(pos, state, message);
                  counter++;
               }
            }
         }
         
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   
   
   public void generate(World world, double x, double y, double z) {
      for(BlockObject obj : blockObjects) {
         world.setBlockState(obj.getPosWithOffset((int) x, (int) y, (int) z), obj.getState());
         if(obj.getMessage() != null){
        	 TileEntity te = world.getTileEntity(obj.getPosWithOffset((int) x, (int) y, (int) z));
     		if (te instanceof TileEntityControlBlock) {
     			((TileEntityControlBlock) te).setMessage(obj.getMessage());
     		}
         }

      }
   }
   

}