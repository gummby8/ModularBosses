package com.Splosions.ModularBosses.blocks.tileentity;

import java.util.List;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.dimensions.BossDimension.BossTeleporter;
import com.Splosions.ModularBosses.entity.projectile.EntityBait;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

public class TileEntityReturnPortalBlock extends TileEntity implements IUpdatePlayerListBox {

	public int ticksExisted;
	public int countDown;

	public int returnDimension;
	public int returnX;
	public int returnY;
	public int returnZ;

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

	public void setReturnLocation(int x, int y, int z, int dimension) {
		returnX = x;
		returnY = y + 2;
		returnZ = z;
		returnDimension = dimension;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		returnX = compound.getInteger("returnX");
		returnY = compound.getInteger("returnY");
		returnZ = compound.getInteger("returnZ");
		returnDimension = compound.getInteger("dimension");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("returnX", returnX);
		compound.setInteger("returnY", returnY);
		compound.setInteger("returnZ", returnZ);
		compound.setInteger("dimension", returnDimension);
	}

}
