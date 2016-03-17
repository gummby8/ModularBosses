package com.Splosions.ModularBosses.items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.Splosions.ModularBosses.client.entity.EntityCartographer;
import com.Splosions.ModularBosses.client.entity.EntityCustomFallingBlock;
import com.Splosions.ModularBosses.client.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.client.models.item.ModelLegendsSword;
import com.Splosions.ModularBosses.client.render.items.ModelItemLegendsSword;
import com.Splosions.ModularBosses.util.Schematic;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ItemLegendsSword extends ItemSword {
	
	
	
	public ItemLegendsSword(ToolMaterial material) {
		super(material);
	}
	
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
    	/**
    	if (!worldIn.isRemote){
    	Schematic HI = new Schematic("ForestDungeon.schematic", worldIn, playerIn.posX, playerIn.posY, playerIn.posZ);
    	System.out.println("Schematic?");
    	}
    	*/
    	
    	/**
    	try {
			readData("./schematics/derp.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
    	
    	if (!worldIn.isRemote){
    		EntityCartographer cartographer = new EntityCartographer(worldIn, playerIn, playerIn.posX, playerIn.posY, playerIn.posZ);
    		worldIn.spawnEntityInWorld(cartographer);
		}
    	
        return itemStackIn;
        
    }
    
    
    
    
    
    
    public static void readData(String filePath) throws IOException{
        BufferedReader dataBR = new BufferedReader(new FileReader(new File(filePath)));
        String line = "";

        ArrayList<String[]> dataArr = new ArrayList<String[]>(); //An ArrayList is used because I don't know how many records are in the file.

        while ((line = dataBR.readLine()) != null) { // Read a single line from the file until there are no more lines to read

            String[] club = new String[32]; // Each club has 3 fields, so we need room for the 3 tokens.

            for (int i = 0; i < 32; i++) { // For each token in the line that we've read:
                String[] value = line.split(",", 32);                
                club[i] = value[i]; // Place the token into the 'i'th "column"
            }

            dataArr.add(club); // Add the "club" info to the list of clubs.
        }

        for (int i = 0; i < dataArr.size(); i++) {
            for (int x = 0; x < dataArr.get(i).length; x++) {
                //System.out.printf("dataArr[%d][%d]: ", i, x);
                System.out.println(dataArr.get(i)[x]);
            }
        }
    
    }
    
    
    
    
	

}
