package com.Splosions.ModularBosses.blocks.tileentity;

import java.util.List;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.dimensions.BossTeleporter;
import com.Splosions.ModularBosses.util.TargetUtils;
import com.Splosions.ModularBosses.util.schematic.Dungeon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityReturnPortalBlock extends TileEntity implements ITickable {

	public int ticksExisted;
	public int countDown;

	public String dungeonID = "";
	public int returnDimension;
	public int returnX;
	public int returnY;
	public int returnZ;
		
	@Override
	public void update() {
		if (!this.world.isRemote) {
			ticksExisted++;
			if (this.ticksExisted % 20 == (20 - 1) && this.world.isBlockPowered(pos)) {
				AxisAlignedBB bb = new AxisAlignedBB(this.getPos().down().south().east(), this.getPos().up());
				List teleList = this.world.getEntitiesWithinAABB(EntityPlayer.class, bb.expand(0, 1, 0));
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
				if(!ModularBosses.INSTANCE.dungeonList.isEmpty()) {
					int dungeonCount = ModularBosses.INSTANCE.dungeonList.size();
					for (int x = 0; x < dungeonCount; x++) {
						Dungeon dungeon = ModularBosses.INSTANCE.dungeonList.get(x);
						if(dungeon.dungeonID.equals(dungeonID)){
							System.out.println("Removed ID = " + dungeonID);
							ModularBosses.INSTANCE.dungeonList.remove(x);
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
				BossTeleporter teleporter = new BossTeleporter(player.getServerWorld());
				if (this.world.provider.getDimension() == Config.bossDimension) {
					player.changeDimension(returnDimension);
				} else {
					player.changeDimension(Config.bossDimension);
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
		dungeonID = compound.getString("dungeonID");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("returnX", returnX);
		compound.setInteger("returnY", returnY);
		compound.setInteger("returnZ", returnZ);
		compound.setInteger("dimension", returnDimension);
		compound.setString("dungeonID", dungeonID);
		return compound;
	}
}
