package com.Splosions.ModularBosses.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.Splosions.ModularBosses.util.schematic.Room;
import com.Splosions.ModularBosses.util.schematic.Schematic;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCartographer extends Entity
{

	public static final int DUNGEON = 0;
	public static final int WORM = 1;
	public Room[][] roomArray;
	
	private int cartType;
	
	static ArrayList<String[]> dataArr;
	int mapLine = 0;
	public int mapRoom = 0;
	
	public int roomWidth = 5;
	
	int roomLength = 5;
	public int schemTick = 0;
	public int schemTickInterval = 20;
	
    public EntityCartographer(World worldIn)
    {
        super(worldIn);
    }

    public EntityCartographer(World worldIn, Entity shooter, int type, double x, double y, double z)
    {
        super(worldIn);
        this.setSize(1F, 1F);
        this.setPosition(x, y, z);
        this.noClip = true;
        this.cartType = type;
        
        
    } 


    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
    	    if (this.cartType == DUNGEON){
    	    	dungeonGen();
    	    } else
    	    if (this.cartType == WORM){
    	    	wormGen();
    	    }
    	    	
    	
    	
    }
    
    public void wormGen(){
    	if (roomArray == null){
    		roomArray = new Room[7][3]; //3 Across 7 tall
    		for (int i = 0; i < roomArray.length; i++){
    			for (int j = 0; j < roomArray[i].length; j++){
    				roomArray[i][j] = new Room();
    			}
    		}
    	}
    	
    	roomArray[0][0].east = true;
    	roomArray[6][2].west = true;
    	
    	for (int i = 0; i < roomArray.length; i++){
			for (int j = 0; j < roomArray[i].length; j++){


				
				// doo stuff here
				
				
				String roomPath = "./schematics/Worm/blank.schematic";
	    		Schematic.quickBuild(roomPath, this.worldObj, this, this.posX, this.posY, this.posZ);
				this.posX += 6;
			}
			this.posZ += 6;
			this.posX -= 18;
		}
    	
    	
    	setDead();
    }
    
    
    
    
    
    public void dungeonGen(){
    	
    	schemTickInterval = 100;
    	if (this.ticksExisted == 1){
        	try {
    			readData("./schematics/derp.csv");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    	
    	
    	//Places a room every 0.5 seconds this.ticksExisted % 2 == (2 - 1) && 
    	if (!this.worldObj.isRemote && this.mapLine <= 31) {

    		//System.out.println(dataArr.get(this.mapLine)[this.mapRoom]);

    		String roomCodeRaw = dataArr.get(this.mapLine)[this.mapRoom];
    		String[] roomCodeArray = roomCodeRaw.split("\\|");
    		
    		
    		//System.out.println(roomCodeArray[0]);
    		
    		String roomPath = null;
    		
    		
    		
    		if (roomCodeArray[0].contains("1")){
    			roomPath = "wall"; 
    			
    		} else 
        	
    		if (roomCodeArray[0].contains("0")){
        		roomPath = "blank"; 
        	} else {
        		
        		roomPath = roomCodeArray[0] + "/" + roomCodeArray[1] + "/1"; 
        		
        	}
    		
    		roomPath = "./schematics/Dungeon Schematics/" + roomPath + ".schematic";
    		//System.out.println(roomPath);
    		
    		
    		Schematic.build(roomPath, this.worldObj, this, this.posX, this.posY, this.posZ);
    		
    		
    		if (mapRoom > 31){
    			mapRoom = 0;
    			this.setPositionAndUpdate(this.posX - (this.roomWidth * 32), this.posY, this.posZ + this.roomLength);
    			mapLine++;
    		} 

    		if (this.mapLine > 31){
    			setDead();
    			
    		}
    	
    	}
    	
    	
    
    	//	new Schematic("./schematics/2.schematic", this.worldObj, this.posX, this.posY, this.posZ);
    	
    }
    
    
    
    
    
    public static void readData(String filePath) throws IOException{
        BufferedReader dataBR = new BufferedReader(new FileReader(new File(filePath)));
        String line = "";

        dataArr = new ArrayList<String[]>(); //An ArrayList is used because I don't know how many records are in the file.

        while ((line = dataBR.readLine()) != null) { // Read a single line from the file until there are no more lines to read

            String[] club = new String[32]; // Each club has 3 fields, so we need room for the 3 tokens.

            for (int i = 0; i < 32; i++) { // For each token in the line that we've read:
                String[] value = line.split(",", 32);                
                club[i] = value[i]; // Place the token into the 'i'th "column"
            }
            dataArr.add(club); // Add the "club" info to the list of clubs.
        }
        dataBR.close();
    }
    
    
    
    
    
    

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub
		
	}

    
    
}