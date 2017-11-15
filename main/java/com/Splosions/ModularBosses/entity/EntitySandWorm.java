package com.Splosions.ModularBosses.entity;

import java.util.List;

import com.Splosions.ModularBosses.Config;
import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.dimensions.BossDimension.BossTeleporter;
import com.Splosions.ModularBosses.entity.projectile.EntityBait;
import com.Splosions.ModularBosses.util.TargetUtils;
import com.Splosions.ModularBosses.util.schematic.Dungeon;
import com.Splosions.ModularBosses.util.schematic.DungeonNurkach;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntitySandWorm extends Entity  implements IEntityAdditionalSpawnData {

	private static final int RANDOM_X_WATCHER = 16;
	private static final int RANDOM_Y_WATCHER = 17;
	private static final int RANDOM_Z_WATCHER = 18;
	
	private static final int YAW_WATCHER = 19;
	private static final int PITCH_WATCHER = 20;
	
	public float yaw;
	public float pitch;

	public EntitySandWormTail[] bodySegments;
	
	public double preRenX;
	public double preRenY;
	public double preRenZ;

	public int spawnPosX;
	public int spawnPosZ;
	public int spawnPosY;
	
	public int ranPosX;
	public int ranPosZ;
	public int ranPosY;
	
	public int ranMidPosX;
	public int ranMidPosY;
	public int ranMidPosZ;

	public EntitySandWorm(World worldIn) {
		super(worldIn);
		this.setSize(8F, 8F);
		this.spawnPosX = 0;
		this.spawnPosZ = 0;
		this.spawnPosY = 0;
		this.isImmuneToFire = true;
		this.ignoreFrustumCheck = true;
		this.noClip = true;
		this.pitch = -90; 
		if (this.worldObj.isRemote) {
			bodySegments = new EntitySandWormTail[10];
			for (int x = 0; x < bodySegments.length; x++) {
				bodySegments[x] = new EntitySandWormTail(worldIn, this.posX, this.posY, this.posZ, this.yaw, this.pitch, x, this);
			}
		}
	}


	
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(RANDOM_X_WATCHER, 0);
		this.dataWatcher.addObject(RANDOM_Y_WATCHER, 0);
		this.dataWatcher.addObject(RANDOM_Z_WATCHER, 0);
		
		this.dataWatcher.addObject(YAW_WATCHER, 0.0F);
		this.dataWatcher.addObject(PITCH_WATCHER, 0.0F);
	}

	
	
	public void nextPosition(){
		int count = 0;
		while (!withinRange(this.ranPosX, this.posY, this.ranPosZ)){
			count++;
			int ranX = TargetUtils.getRanNum(-200, 200);
			int ranZ = TargetUtils.getRanNum(-200, 200);
			
			this.ranPosX = this.spawnPosX + ranX;
			this.ranPosZ = this.spawnPosZ + ranZ;
			
			this.ranMidPosX = this.spawnPosX + (ranX / 2);
			this.ranMidPosZ = this.spawnPosZ + (ranZ / 2);
		}


		
		this.dataWatcher.updateObject(RANDOM_X_WATCHER, this.ranMidPosX);
		this.dataWatcher.updateObject(RANDOM_Y_WATCHER, 90);
		this.dataWatcher.updateObject(RANDOM_Z_WATCHER, this.ranMidPosZ);
	}
	
	
	
	public boolean withinRange(double x, double posY, double z){
		if (this.getDistance(x, posY, z) >= 100 && this.getDistance(x, posY, z) <= 150) {
			return true;
		} else {
			return false;
		}

	}
	
    /**
     * Gets the distance to the position. Args: x, y, z
     */
    public double getDistance(double x, double y, double z){
        double d3 = this.posX - x;
        double d4 = this.posY - y;
        double d5 = this.posZ - z;
        return (double)MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
    }
    
    
    
	/**
	 * Teleports Players to the Boss dimension. Eats bait
	 */
	private void teleEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity instanceof EntityPlayer){
				EntityPlayerMP player = (EntityPlayerMP) par1List.get(i);
				player.setPosition(spawnPosX + (Config.WormRoomSizeX / 2), spawnPosY + (Config.WormRoomSizeY / 2) + 2, spawnPosZ + (Config.WormRoomSizeZ / 2));
				BossTeleporter teleporter = new BossTeleporter(player.getServerForPlayer());
				WorldServer ws = MinecraftServer.getServer().worldServerForDimension(Config.bossDimension);
				MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, Config.bossDimension, teleporter);
				this.worldObj.theProfiler.endSection();	
			} else if (entity instanceof EntityBait){
				entity.setDead();
			}
			
		}

	}
	
    
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		
		

		
		if(spawnPosX == 0 && spawnPosY == 0 && spawnPosZ == 0 && !this.worldObj.isRemote){
			
			spawnPosX = (int) this.posX;
			spawnPosY = (int) this.posY; 
			spawnPosZ = (int) this.posZ;
			nextPosition();
			createDungeon();
		}
		
		//dungeon was cleared, kill the worm...add death animation
		

		
		if (!this.worldObj.isRemote){
			if (dungeonCompleted()){
				this.setDead();
			}			

			
		AxisAlignedBB bb = new AxisAlignedBB(this.getPosition().down(),this.getPosition()).expand(15, 15, 15);
		List teleList = this.worldObj.getEntitiesWithinAABB(Entity.class, bb);
		teleEntitiesInList(teleList);

		}
		
		
		double faceX = this.dataWatcher.getWatchableObjectInt(RANDOM_X_WATCHER);
		double faceY = this.dataWatcher.getWatchableObjectInt(RANDOM_Y_WATCHER);
		double faceZ = this.dataWatcher.getWatchableObjectInt(RANDOM_Z_WATCHER);

		if (faceX == 0 && faceY == 0 && faceZ == 0){
			nextPosition();
		}
	
		faceLocation(faceX, faceY, faceZ, 2, 1);
		moveForward(0.5F);
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        
		if (!this.worldObj.isRemote && this.getDistance(faceX, faceY, faceZ) < 20) {
			if (faceY == 90){
				this.dataWatcher.updateObject(RANDOM_X_WATCHER, this.ranPosX);
				this.dataWatcher.updateObject(RANDOM_Y_WATCHER, 5);
				this.dataWatcher.updateObject(RANDOM_Z_WATCHER, this.ranPosZ);	
			} else {
				nextPosition();
			}
		}
        

		if (this.ticksExisted == 1 && this.worldObj.isRemote) {
			for (int x = 0; x < bodySegments.length; x++) {
				bodySegments[x].setPosition(this.posX, this.posY, this.posZ);
				this.worldObj.spawnEntityInWorld(bodySegments[x]);
			}
		}

		if (this.ticksExisted > 10) {
			try {
				if (this.worldObj.isRemote && bodySegments == null) {
					for (int x = 0; x < bodySegments.length; x++) {
					}
				}

				if (this.worldObj.isRemote){
				float spacing = 21;
				PseudoChild(this, spacing, bodySegments[0]);
				PseudoChild(bodySegments[0], spacing, bodySegments[1]);
				PseudoChild(bodySegments[1], spacing, bodySegments[2]);
				PseudoChild(bodySegments[2], spacing, bodySegments[3]);
				PseudoChild(bodySegments[3], spacing, bodySegments[4]);
				PseudoChild(bodySegments[4], spacing, bodySegments[5]);
				PseudoChild(bodySegments[5], spacing, bodySegments[6]);
				PseudoChild(bodySegments[6], spacing, bodySegments[7]);
				PseudoChild(bodySegments[7], spacing, bodySegments[8]);
				PseudoChild(bodySegments[8], spacing, bodySegments[9]);
				}
				
				
			} catch (Throwable e) {}

		}
	}
	/**
	 *checks if there is a dungeon with and ID that matches the worms UUID. 
	 */
	public boolean dungeonCompleted(){
		if(!ModularBosses.INSTANCE.dungeonList.isEmpty()) {
			int dungeonCount = ModularBosses.INSTANCE.dungeonList.size();
			for (int x = 0; x < dungeonCount; x++) {
				Dungeon dungeon = ModularBosses.INSTANCE.dungeonList.get(x);
				if(dungeon.dungeonID.equals(this.getUniqueID().toString())){ 
					return false;
				}
			}
		}
		
		return true;
	}
	
	/*
	 * creates a new dungeon assigns the return location and dungeon ID
	 */
	private void createDungeon() {
		if (!this.worldObj.isRemote){
			DungeonNurkach dungeon = new DungeonNurkach(this.getUniqueID().toString() ,this.getPosition(), this.dimension);
			ModularBosses.INSTANCE.dungeonList.add(dungeon);
		}
	}



	public void baitAction(double posX, double posY, double posZ){
		this.dataWatcher.updateObject(RANDOM_X_WATCHER, (int)posX);
		this.dataWatcher.updateObject(RANDOM_Y_WATCHER, (int)posY);
		this.dataWatcher.updateObject(RANDOM_Z_WATCHER, (int)posZ);	
	}
	
	
	
	
	@Override
    protected void doBlockCollisions()
    {

    }
	
	
	

	public void moveForward(float speed) {

		float f2 = MathHelper.sin(this.yaw * (float) Math.PI / 180.0F);
		float f3 = MathHelper.cos(this.yaw * (float) Math.PI / 180.0F);
		float f = 0.7F;
		this.motionX = (double)(MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
		this.motionZ = (double)(-MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
		this.motionY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
	}

	/**
	 * Changes pitch and yaw so that the entity calling the function is facing
	 * the entity provided as an argument.
	 */
	public void faceLocation(double x, double y, double z, float yawSpeed, float pitchSpeed) {
		double d0 = x - this.posX;
		double d2 = z - this.posZ;
		double d1 = y - this.posY;

		double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
		float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) + 90.0F;
		float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
		this.pitch = this.updateRotation(this.pitch, f3, pitchSpeed);
		this.yaw = this.updateRotation(this.yaw, f2, yawSpeed);
	}

	/**
	 * Arguments: current rotation, intended rotation, max increment.
	 */
	private float updateRotation(float rot, float dest, float speed) {
		float f3 = MathHelper.wrapAngleTo180_float(dest - rot);

		if (f3 > speed) {
			f3 = speed;
		}

		if (f3 < -speed) {
			f3 = -speed;
		}

		return rot + f3;
	}

	public void dead() {
		for (int x = 0; x < bodySegments.length; x++) {
			bodySegments[x].setDead();
		}
		setDead();
	}

	public void PseudoChild(Entity parentRaw, float length, EntitySandWormTail child) {
		if (parentRaw instanceof EntitySandWorm){
			EntitySandWorm parent = (EntitySandWorm) parentRaw;
			float parentYaw = (parent.yaw + 90) * 0.0174533F;
			float parentPitch = (parent.pitch) * 0.0174533F;
			double x = ((length * MathHelper.cos(parentPitch) * MathHelper.cos(parentYaw)) + parent.posX);
			double y = ((length * MathHelper.sin(parentPitch)) + parent.posY);
			double z = (length * MathHelper.cos(parentPitch) * MathHelper.sin(parentYaw)) + parent.posZ;
			child.yaw = child.yaw + ((parent.yaw - child.yaw) * 0.1F);
			child.pitch = child.pitch + ((parent.pitch - child.pitch) * 0.1F);
			child.setPositionAndUpdate(x, y, z);			
		} else {
			EntitySandWormTail parent = (EntitySandWormTail) parentRaw;
			float parentYaw = (parent.yaw + 90) * 0.0174533F;
			float parentPitch = (parent.pitch) * 0.0174533F;
			double x = ((length * MathHelper.cos(parentPitch) * MathHelper.cos(parentYaw)) + parent.posX);
			double y = ((length * MathHelper.sin(parentPitch)) + parent.posY);
			double z = (length * MathHelper.cos(parentPitch) * MathHelper.sin(parentYaw)) + parent.posZ;
			child.yaw = child.yaw + ((parent.yaw - child.yaw) * 0.1F);
			child.pitch = child.pitch + ((parent.pitch - child.pitch) * 0.1F);
			child.setPositionAndUpdate(x, y, z);
		}
	}




	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		//super.readFromNBT(compound);
		spawnPosX = compound.getInteger("spawnPosX");
		spawnPosY = compound.getInteger("spawnPosY");
		spawnPosZ = compound.getInteger("spawnPosZ");
		
	}



	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		//super.writeToNBT(compound);
		compound.setInteger("spawnPosX", spawnPosX);
		compound.setInteger("spawnPosY", spawnPosY);
		compound.setInteger("spawnPosZ", spawnPosZ);
		
	}



	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeFloat(this.pitch);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		this.pitch = additionalData.readFloat();
	}

}