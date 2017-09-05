package com.Splosions.ModularBosses.blocks.tileentity;

import java.util.List;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.dimensions.BossDimension.BossTeleporter;
import com.Splosions.ModularBosses.entity.projectile.EntityBait;
import com.Splosions.ModularBosses.util.TargetUtils;
import com.Splosions.ModularBosses.util.schematic.Dungeon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

public class TileEntityReturnPortalBlock extends TileEntity implements IUpdatePlayerListBox, IEntityMultiPart {

	public int ticksExisted;
	public int countDown;

	public String dungeonID;
	public int returnDimension;
	public int returnX;
	public int returnY;
	public int returnZ;
	
	public EntityDragonPart[] paragonPartArray;
	public EntityDragonPart paragonPartFurnace;
	public EntityDragonPart paragonPartRKnee;
	public EntityDragonPart paragonPartLKnee;

	public TileEntityReturnPortalBlock(){
		this.paragonPartArray = new EntityDragonPart[] { this.paragonPartFurnace = new EntityDragonPart(this, "furnace", 10.0F, 10.0F), this.paragonPartRKnee = new EntityDragonPart(this, "RKnee", 1.0F, 1.0F), this.paragonPartLKnee = new EntityDragonPart(this, "LKnee", 1.0F, 1.0F) };
		this.paragonPartFurnace.width = this.paragonPartFurnace.height = 10.3F;
		this.paragonPartRKnee.width = this.paragonPartRKnee.height = 10.9F;
		this.paragonPartLKnee.width = this.paragonPartLKnee.height = 10.9F;		
	}

	
	@Override
	public void update() {
		if (!this.worldObj.isRemote) {
			ticksExisted++;
			if (this.ticksExisted % 20 == (20 - 1) && this.worldObj.isBlockPowered(pos)) {
				AxisAlignedBB bb = new AxisAlignedBB(this.getPos().down().south().east(), this.getPos().up());
				List teleList = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, bb.expand(0, 1, 0));
				if (!teleList.isEmpty()) {
					countDown--;
					TargetUtils.tellPlayersInList(teleList, "Returning to world in " + countDown);

					if (countDown <= 0) {
						teleEntitiesInList(teleList);
					}
				} else {
					countDown = 6;
				}
				//if the block is powered that means the boss has died, the dungeon is complete
				//remove the dungeon from the dungeon list so the worm entity can die
				if(!ModularBosses.instance.dungeonList.isEmpty()) {
					int dungeonCount = ModularBosses.instance.dungeonList.size();
					for (int x = 0; x < dungeonCount; x++) {
						Dungeon dungeon = ModularBosses.instance.dungeonList.get(x);
						if(dungeon.finishedBuilding && dungeon.dungeonID == dungeonID){
							ModularBosses.instance.dungeonList.remove(x);
							break;
						}
					}
				}
				

			}

		}
		
		
		
	}

	/**
	 * Teleports Players to the Boss dimension. Eats bait
	 */
	private void teleEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity instanceof EntityPlayer) {
				EntityPlayerMP player = (EntityPlayerMP) par1List.get(i);
				player.setPosition(returnX, returnY + 2, returnZ);
				BossTeleporter teleporter = new BossTeleporter(player.getServerForPlayer());
				if (this.worldObj.provider.getDimensionId() == Config.bossDimension) {
					MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, returnDimension, teleporter);
				} else {
					MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, Config.bossDimension, teleporter);
				}
			}
		}
	}

	public void setReturnLocation(int x, int y, int z, int dimension, String id) {
		returnX = x;
		returnY = y + 2;
		returnZ = z;
		returnDimension = dimension;
		dungeonID = id;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		returnX = compound.getInteger("returnX");
		returnY = compound.getInteger("returnY");
		returnZ = compound.getInteger("returnZ");
		returnDimension = compound.getInteger("dimension");
		dungeonID = compound.getString("dungeonID")
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("returnX", returnX);
		compound.setInteger("returnY", returnY);
		compound.setInteger("returnZ", returnZ);
		compound.setInteger("dimension", returnDimension);
		compound.setString("dungeonID", dungeonID);
	}

	@Override
	public boolean attackEntityFromPart(EntityDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_) {
		// TODO Auto-generated method stub
		return false;
	}

}
