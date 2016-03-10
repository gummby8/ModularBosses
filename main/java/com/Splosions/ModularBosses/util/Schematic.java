package com.Splosions.ModularBosses.util;
import java.io.FileInputStream;
import java.io.InputStream;

import com.google.common.primitives.UnsignedBytes;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

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
         System.out.println("Schematic?");
         width = nbtdata.getShort("Width");
         height = nbtdata.getShort("Height");
         length = nbtdata.getShort("Length");
         size = width * height * length;
         blockObjects = new BlockObject[size];
         
         byte[] blockIDs = nbtdata.getByteArray("Blocks");
         byte[] metadata = nbtdata.getByteArray("Data");
         
         int counter = 0;
         for(int i = 0; i < height; i++) {
            for(int j = 0; j < length; j++) {
               for(int k = 0; k < width; k++) {
                   int blockId = UnsignedBytes.toInt(blockIDs[counter]);
                  BlockPos pos = new BlockPos(k, i, j);
                  IBlockState state = Block.getBlockById(blockId).getStateFromMeta(metadata[counter]);
                  blockObjects[counter] = new BlockObject(pos, state);
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
      }
   }

}