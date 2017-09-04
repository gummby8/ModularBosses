package com.Splosions.ModularBosses.blocks.tileentity;

import java.util.List;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.dimensions.BossDimension.BossTeleporter;
import com.Splosions.ModularBosses.entity.projectile.EntityBait;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.WorldServer;

public class TileEntityReturnPortalBlock extends TileEntity implements IUpdatePlayerListBox { 

	
	public int ticksExisted;
	
	
	
	
	@Override
	public void update() {
		if (!this.worldObj.isRemote) {
			ticksExisted++;

		
			AxisAlignedBB bb = new AxisAlignedBB(this.getPos().down(),this.getPos().up());
			List teleList = this.worldObj.getEntitiesWithinAABB(Entity.class, bb.expand(1, 1, 1));
			teleEntitiesInList(teleList);
		
		
		
		
		
		
		
		
		}
	}
	
	
	/**
	 * Teleports Players to the Boss dimension. Eats bait
	 */
	private void teleEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity instanceof EntityPlayer){
				EntityPlayerMP player = (EntityPlayerMP) par1List.get(i);
				player.setPosition(this.pos.getX(), this.pos.getY(), this.pos.getZ());
				BossTeleporter teleporter = new BossTeleporter(player.getServerForPlayer());
				
				if (this.worldObj.provider.getDimensionId() == -3) {
					MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, 0, teleporter);
				} else {
					MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, Config.bossDimension, teleporter);
				}
				
				//WorldServer ws = MinecraftServer.getServer().worldServerForDimension(Config.bossDimension);
				//this.worldObj.theProfiler.endSection();	
			}
		}
	}
	

	
	
}
