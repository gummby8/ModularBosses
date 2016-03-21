package com.Splosions.ModularBosses.blocks.tileentity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.Splosions.ModularBosses.blocks.BlockPortalBlock;
import com.Splosions.ModularBosses.client.entity.EntityCustomFallingBlock;
import com.Splosions.ModularBosses.client.entity.EntityTeleportBiped;
import com.Splosions.ModularBosses.client.entity.projectile.EntityFlameThrower;
import com.Splosions.ModularBosses.client.particle.MagicFX;
import com.Splosions.ModularBosses.world.PortalLandingWorldData;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;

public class TileEntityPortalBlock extends TileEntity implements IUpdatePlayerListBox
{
	
	public int ticksExisted;
	public float red = 0;
	public float green = 0;
	public float blue = 0;
	
	
	Random rand = new Random();
	


	
	
	@Override
	public void update() {
		if (this.ticksExisted % 20 == (20 - 1) && !this.worldObj.isRemote){
			
			
			PortalLandingWorldData roomData = (PortalLandingWorldData) this.worldObj.getPerWorldStorage().loadData(PortalLandingWorldData.class, "lobbyPortals");
	    	if (roomData == null){
	    		System.out.println("No LobbyPortals Tag found, creating one");
	    		roomData = new PortalLandingWorldData("lobbyPortals");
	    		this.worldObj.getPerWorldStorage().setData("lobbyPortals", roomData);
	    	}

	    	int num = getRandomNumberInRange(0,roomData.portalLandingList.size() - 1);
	    	String locRaw = roomData.portalLandingList.get(num);
	    	String[] locArray = locRaw.split(",", -1);
    	
	    	 	    	 
	    	 double pX = Double.parseDouble(locArray[1]);
	    	 double pY = Double.parseDouble(locArray[2]);
	    	 double pZ = Double.parseDouble(locArray[3]);
	    	 
	    	
	    	
	    	 
			
			
	    	 double i = this.pos.getX();
	    	 double j = this.pos.getY();
	    	 double k = this.pos.getZ();
	        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(i, j, k, (i + 1), (j + 1), (k + 1)));
	        
			List list = this.worldObj.getEntitiesWithinAABB(Entity.class, axisalignedbb.expand(3, 2, 3));
			Iterator iterator = list.iterator();
	        while (iterator.hasNext())
	        {
	            Entity player = (Entity)iterator.next();
	            EntityTeleportBiped teleBiped = new EntityTeleportBiped(this.worldObj, player, player.posX, player.posY + 1, player.posZ, player.rotationYaw);
				//if (!this.worldObj.isRemote){this.worldObj.spawnEntityInWorld(teleBiped);}
	            for (int l = 0; l < 20; ++l)
	            {
				this.worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX - 1 + rand.nextFloat(), this.pos.getY() + 1, player.posZ - 1 + rand.nextFloat(), 0, 2, 0, 5);
	            }
				
				player.setPositionAndUpdate(pX + 0.5, pY, pZ - 0.5);
	            
	            
	            /**
	            EntityPlayerMP mp = (EntityPlayerMP)iterator.next();
	            NetworkPlayerInfo npi = new NetworkPlayerInfo(mp.getGameProfile());
	            ResourceLocation rl = npi.getLocationSkin();
	            */
					        	
	        	
	        }
	        
	        
	        
	         red = getRandomNumberInRange(50,255);
	         green = getRandomNumberInRange(50,255);
	         blue = getRandomNumberInRange(50,255);

	         worldObj.markBlockForUpdate(this.pos);
		}
		
		

		ticksExisted++;
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		red = compound.getFloat("red");
		green = compound.getFloat("green");
		blue = compound.getFloat("blue");

		
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setFloat("red", red);
		compound.setFloat("green", green);
		compound.setFloat("blue", blue);
		
	}
	
	
	
	
	   @Override
	   public Packet getDescriptionPacket()
	   {
	       NBTTagCompound syncData = new NBTTagCompound();
	       syncData.setFloat("red", red);
	       syncData.setFloat("green", green);
	       syncData.setFloat("blue", blue);
	       this.writeToNBT(syncData);
	       return new S35PacketUpdateTileEntity(this.pos, 1, syncData);
	   }
	   
		@Override
		public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
			readFromNBT(packet.getNbtCompound());	
			
	    	 double i = this.pos.getX();
	    	 double j = this.pos.getY();
	    	 double k = this.pos.getZ();
			 AxisAlignedBB axisalignedbb = (new AxisAlignedBB(i, j, k, (i + 1), (j + 1), (k + 1)));
		        
				List list = this.worldObj.getEntitiesWithinAABB(Entity.class, axisalignedbb.expand(3, 2, 3));
				Iterator iterator = list.iterator();
		        while (iterator.hasNext())
		        {
		            Entity player = (Entity)iterator.next();
		            EntityTeleportBiped teleBiped = new EntityTeleportBiped(this.worldObj, player, player.posX, player.posY + 1, player.posZ, player.rotationYaw);
					//if (!this.worldObj.isRemote){this.worldObj.spawnEntityInWorld(teleBiped);}
		            for (int l = 0; l < 200; ++l)
		            {
					this.worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX - 1 + rand.nextFloat(), this.pos.getY() + 1, player.posZ - 1 + rand.nextFloat(), 0, rand.nextDouble() * 3, 0, 5);
		            }
		        }
					
			
		}
		
		
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	
	
	

}
