package com.Splosions.ModularBosses.blocks.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.Splosions.ModularBosses.blocks.BlockPortalBlock;
import com.Splosions.ModularBosses.client.entity.EntityCustomFallingBlock;
import com.Splosions.ModularBosses.client.entity.EntityTeleportBiped;
import com.Splosions.ModularBosses.client.entity.projectile.EntityFlameThrower;
import com.Splosions.ModularBosses.network.PacketDispatcher;
import com.Splosions.ModularBosses.network.server.SetControlBlockMessagePacket;
import com.Splosions.ModularBosses.world.PortalLandingWorldData;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.Entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityPortalBlock extends TileEntity implements IUpdatePlayerListBox
{

	public int ticksExisted;
	public float red = 0;
	public float green = 0;
	public float blue = 0;

	public String message;
	// gummby8 skin "minecraft:skins/2cce44e913e9726c4bb39458f1401f31ab7bf44a6921c86df9411227c8d1";
	public ArrayList<String> playerSkins = new ArrayList<String>();
	public ArrayList<String> playerNames = new ArrayList<String>();

	
	
	Random rand = new Random();
	
	
	@Override
	public void update() {
		
		if (this.ticksExisted % 20 == (20 - 1) && this.worldObj.isRemote){
			AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.pos.getX(), this.pos.getY(), this.pos.getZ(), (this.pos.getX() + 1), (this.pos.getY() + 1), (this.pos.getZ() + 1));
			List<AbstractClientPlayer> players = this.worldObj.getEntitiesWithinAABB(AbstractClientPlayer.class, axisalignedbb.expand(3, 2, 3)); 
			for (AbstractClientPlayer player : players){
				message = player.getLocationSkin().toString();
				PacketDispatcher.sendToServer(new SetControlBlockMessagePacket(this));

			}
		}
		
		
		
		
		
		
		
		if (this.ticksExisted % 200 == (200 - 1) && !this.worldObj.isRemote){


			PortalLandingWorldData roomData = (PortalLandingWorldData) this.worldObj.getPerWorldStorage().loadData(PortalLandingWorldData.class, "lobbyPortals");
			if (roomData == null){
				System.out.println("No LobbyPortals Tag found, creating one");
				roomData = new PortalLandingWorldData("lobbyPortals");
				this.worldObj.getPerWorldStorage().setData("lobbyPortals", roomData);
			}

			
			int num = (roomData.portalLandingList.size() > 1) ? getRandomNumberInRange(0,roomData.portalLandingList.size() - 1) : 0;
			String locRaw = roomData.portalLandingList.get(num);
			String[] locArray = locRaw.split(",", -1);


			double pX = Double.parseDouble(locArray[1]);
			double pY = Double.parseDouble(locArray[2]);
			double pZ = Double.parseDouble(locArray[3]);





			//TEs dont have a bounding box, have to make an AABB by hand
			AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.pos.getX(), this.pos.getY(), this.pos.getZ(), (this.pos.getX() + 1), (this.pos.getY() + 1), (this.pos.getZ() + 1));
			List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb.expand(3, 2, 3)); 
			for (EntityPlayer player : players){
				if (playerNames.contains(player.getDisplayNameString())){

					EntityTeleportBiped teleBiped = new EntityTeleportBiped(this.worldObj, player, player.posX, player.posY + 1, player.posZ, player.rotationYaw, playerSkins.get(playerNames.indexOf(player.getDisplayNameString())));
					this.worldObj.spawnEntityInWorld(teleBiped);
					player.setPositionAndUpdate(pX + 0.5, pY, pZ - 0.5);
				
				}
			}
			
			


			red = getRandomNumberInRange(50,255);
			green = getRandomNumberInRange(50,255);
			blue = getRandomNumberInRange(50,255);

			worldObj.markBlockForUpdate(this.pos);
		}

		ticksExisted++;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public net.minecraft.util.AxisAlignedBB getRenderBoundingBox()
    {
		AxisAlignedBB bb = new AxisAlignedBB(getPos(), getPos().add(3, 1, 3));
		return bb;
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

	
	public String getMessage() {
		return message;
	}


	public void setMessage(String resourceString, String name) {
		if(!playerNames.contains(name)){
			playerNames.add(name);
			playerSkins.add(resourceString);
		}
		
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
		
	}



	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}





}
