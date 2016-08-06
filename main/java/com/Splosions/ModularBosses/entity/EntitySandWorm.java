package com.Splosions.ModularBosses.entity;

import java.util.Random;

import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.Splosions.ModularBosses.util.TargetUtils;
import com.Splosions.ModularBosses.util.schematic.Room;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySandWorm extends Entity implements IEntityAdditionalSpawnData {

	private static final int RANDOM_X_WATCHER = 16;
	private static final int RANDOM_Y_WATCHER = 17;
	private static final int RANDOM_Z_WATCHER = 18;

	public Entity[] bodySegments;
	public int entIDs[] = new int[10];

	public int ranPosX;
	public int ranPosZ;
	public int ranPosY;

	public EntitySandWorm(World worldIn) {
		super(worldIn);
		this.setSize(18.0F, 18.0F);
		this.isImmuneToFire = true;
		this.ignoreFrustumCheck = true;
		this.noClip = true;
		if (!this.worldObj.isRemote) {
			bodySegments = new Entity[10];
			for (int x = 0; x < bodySegments.length; x++) {
				bodySegments[x] = new EntitySandWormTail(worldIn, this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch, x, this);
				entIDs[x] = bodySegments[x].getEntityId();
			}
		}
		this.noClip = true;
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(RANDOM_X_WATCHER, 0);
		this.dataWatcher.addObject(RANDOM_Y_WATCHER, 0);
		this.dataWatcher.addObject(RANDOM_Z_WATCHER, 0);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.worldObj.isRemote) {
			this.ranPosX = this.dataWatcher.getWatchableObjectInt(RANDOM_X_WATCHER);
			this.ranPosY = this.dataWatcher.getWatchableObjectInt(RANDOM_Y_WATCHER);
			this.ranPosZ = this.dataWatcher.getWatchableObjectInt(RANDOM_Z_WATCHER);

		}

		if (this.ticksExisted % 200 == (200 - 1) && !this.worldObj.isRemote) {
			this.ranPosX = 0;//TargetUtils.getRanNum(-2000, 2000);
			this.ranPosY = 0;//TargetUtils.getRanNum(0, 256);
			this.ranPosZ = 0;//TargetUtils.getRanNum(-2000, 2000);
			this.dataWatcher.updateObject(RANDOM_X_WATCHER, this.ranPosX);
			this.dataWatcher.updateObject(RANDOM_Y_WATCHER, this.ranPosY);
			this.dataWatcher.updateObject(RANDOM_Z_WATCHER, this.ranPosZ);
		}

		faceLocation(this.ranPosX, this.ranPosY, this.ranPosZ, 1, 1);

		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;

		if (this.ticksExisted == 1 && !this.worldObj.isRemote) {
			for (int x = 0; x < bodySegments.length; x++) {
				bodySegments[x].setPosition(this.posX, this.posY, this.posZ);
				this.worldObj.spawnEntityInWorld(bodySegments[x]);
				System.out.println("Spawning ID " + bodySegments[x].getEntityId() + " on server");
				entIDs[x] = bodySegments[x].getEntityId();
			}
		}

		if (this.ticksExisted > 80) {
			try {
				if (this.worldObj.isRemote && bodySegments == null) {
					bodySegments = new Entity[10];
					for (int x = 0; x < bodySegments.length; x++) {
						bodySegments[x] = this.worldObj.getEntityByID(entIDs[x]);
					}
				}

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

				// dead();
			} catch (Throwable e) {
				e.printStackTrace();
				if (this.worldObj.isRemote) {
					for (int x = 0; x < bodySegments.length; x++) {
						bodySegments[x] = this.worldObj.getEntityByID(entIDs[x]);
					}
				}

			}

		}

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
		float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
		float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
		this.rotationPitch = this.updateRotation(this.rotationPitch, f3, pitchSpeed);
		this.rotationYaw = this.updateRotation(this.rotationYaw, f2, yawSpeed);
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

	public void PseudoChild(Entity parent, float length, Entity child) {

		float parentYaw = (parent.rotationYaw + 90) * 0.0174533F;
		float parentPitch = (parent.rotationPitch) * 0.0174533F;

		double x = ((length * MathHelper.cos(parentPitch) * MathHelper.cos(parentYaw)) + parent.posX);
		double y = ((length * MathHelper.sin(parentPitch)) + parent.posY);
		double z = (length * MathHelper.cos(parentPitch) * MathHelper.sin(parentYaw)) + parent.posZ;

		float yaw = child.rotationYaw + ((parent.rotationYaw - child.rotationYaw) * 0.1F);
		float pitch = child.rotationPitch + ((parent.rotationPitch - child.rotationPitch) * 0.1F);

		child.setLocationAndAngles(x, y, z, yaw, pitch);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		for (int x = 0; x < entIDs.length; x++) {
			buffer.writeInt(entIDs[x]);
		}

	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		for (int x = 0; x < entIDs.length; x++) {
			entIDs[x] = additionalData.readInt();
		}

	}

}