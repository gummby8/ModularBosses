package com.Splosions.ModularBosses.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.blocks.BlockControlBlock;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityControlBlock;
import com.google.common.primitives.UnsignedBytes;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.GameData;

public class Schematic {
   
   private short width; 
   private short height;
   private short length;
   private int size;
   private BlockObject[] blockObjects;
   
   private static final FMLControlledNamespacedRegistry<Block> BLOCK_REGISTRY = GameData.getBlockRegistry();

   
   public Schematic(String fileName, World world, double x, double y, double z) {
	   /**
	   File theDir = new File("./NNNNNNERPDERP");
		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: ");
		    boolean result = false;
		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
	   */
	   
	   if (!world.isRemote){
      try {
    	  File file = new File(fileName);
          NBTTagCompound nbtdata = SchematicUtil.readTagCompoundFromFile(file);
    	  
         //InputStream is = Schematic.class.getResourceAsStream("/assets/mb/schematics/2.schematic"); 
         //NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(is);
      
         //is.close();
         
         width = nbtdata.getShort("Width");
         height = nbtdata.getShort("Height");
         length = nbtdata.getShort("Length");
         ItemStack icon = SchematicUtil.getIconFromNBT(nbtdata);
         
         size = width * height * length;
         blockObjects = new BlockObject[size];
         
         byte[] blockIDs = nbtdata.getByteArray("Blocks");
         byte[] metadata = nbtdata.getByteArray("Data");


         
         //testing schematica schematics
         Short id = null;
         final Map<Short, Short> oldToNew = new HashMap<Short, Short>();
         if (nbtdata.hasKey("SchematicaMapping")) {
             final NBTTagCompound mapping = nbtdata.getCompoundTag("SchematicaMapping");
             final Set<String> names = mapping.getKeySet();
             for (final String name : names) {
                 oldToNew.put(mapping.getShort(name), (short) BLOCK_REGISTRY.getId(name));
             }
         }

         

         int counter = 0;
         for(int i = 0; i < height; i++) {
            for(int j = 0; j < length; j++) {
               for(int k = 0; k < width; k++) {
                  int blockId = UnsignedBytes.toInt(blockIDs[counter]);
                  //Checks the id reference Map                 
                  if ((id = oldToNew.get((short) blockId)) != null) {
                      blockId = id;
                  }

                  //BlockPos pos = new BlockPos(k, i, j);
                  IBlockState state = Block.getBlockById(blockId).getStateFromMeta(metadata[counter]);
 
                  
                  /**
                  String message = null;
                  NBTTagList tagList = nbtdata.getTagList("TileEntities", Constants.NBT.TAG_COMPOUND);
                  for(int i = 0; i < tagList.tagCount(); i++) {
         		  NBTTagCompound tag = tagList.getCompoundTagAt(i);
         		  String gotMessage = tag.getString("message");
         		  int Xx = tag.getInteger("x");
         		  int Yy = tag.getInteger("y");
         		  int Zz = tag.getInteger("z");
         		  
         		  if (Xx == X && Yy == Y && Zz == Z){
         			  message = gotMessage;
         			  
         		  }
         		  
         		  
         		 }
                  */
                  
                  
               	  //blockObjects[counter] = new BlockObject(pos, state);
               	  world.setBlockState(new BlockPos(x + k, y + i, z + j), state);
               	  counter++; 
               }
            }
         }
         
         NBTTagList tileEntitiesList = nbtdata.getTagList("TileEntities", Constants.NBT.TAG_COMPOUND);

         for (int i = 0; i < tileEntitiesList.tagCount(); i++) {
             try {
                 TileEntity tileEntity = NBTHelper.readTileEntityFromCompound(tileEntitiesList.getCompoundTagAt(i));
                 if (tileEntity != null) {
                	 NBTTagCompound tag = tileEntitiesList.getCompoundTagAt(i);
                	 int Xx = tag.getInteger("x");
            		 int Yy = tag.getInteger("y");
            		 int Zz = tag.getInteger("z");
                	 BlockPos bPos = new BlockPos(x + Xx, y + Yy, z + Zz);
                	 world.removeTileEntity(bPos);
                     world.setTileEntity(bPos, tileEntity);
                     System.out.println(tileEntity);
                 }
             } catch (Exception e) {
                 ModularBosses.logger.warn("TileEntity failed to load properly!", e);
             }
         }
         
         
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
	   
   }
   
}