package com.Splosions.ModularBosses.entity.projectile;

import com.Splosions.ModularBosses.MBSounds;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityMBParticleEmitter  extends Entity implements IEntityAdditionalSpawnData{

	public static final int TATTERS_SMOKE = 1;
	
	
	
	protected static final int SHOOTER_INDEX = 22;
	
	public Entity Shooter;
	public int variant;
	public int arg1;
	public int arg2;

	public EntityMBParticleEmitter(World world) {
		super(world);
	}

	
	public EntityMBParticleEmitter(Entity entity, double posX, double posY, double posZ, int variant, int arg1, int arg2){
		super(entity.worldObj);
		setShooter(entity);
		this.Shooter = getShooter();
		this.setPosition(posX, posY, posZ);
		this.variant = variant;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	
	@Override
	protected void entityInit() {
		dataWatcher.addObject(SHOOTER_INDEX, -1);
		
	}

	
	@Override
	public void onUpdate() {
		super.onUpdate();

		if (Shooter == null) {
			this.setDead();
			return;
		}

		if (this.variant == TATTERS_SMOKE) {
			for (int i = 0; i < 300; ++i) {
				float x = (this.rand.nextFloat() - 0.5F) * 2F;
				float y = (this.rand.nextFloat() - 0.7F) * 4.5F;
				float z = (this.rand.nextFloat() - 0.5F) * 2F;
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (double) x, this.posY + 2.0D + (double) y, this.posZ + (double) z, 0.0D, 0.0D, 0.0D);
			}
			this.playSound(MBSounds.TATTERS_TELEPORT, 1F, 1.0F);
			this.setDead();

		}
	}

	
	
	public Entity getShooter() {
		int id = dataWatcher.getWatchableObjectInt(SHOOTER_INDEX);
		return (id == -1 ? null : worldObj.getEntityByID(id));
	}

	public void setShooter(Entity entity) {
		dataWatcher.updateObject(SHOOTER_INDEX, entity != null ? entity.getEntityId() : -1);
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
		ByteBufUtils.writeVarInt(buffer, Shooter.getEntityId(), 4);
		ByteBufUtils.writeVarInt(buffer, variant, 2);
		ByteBufUtils.writeVarInt(buffer, arg1, 2);
		ByteBufUtils.writeVarInt(buffer, arg2, 2);
	}


	@Override
	public void readSpawnData(ByteBuf additionalData) {
		Shooter = worldObj.getEntityByID(ByteBufUtils.readVarInt(additionalData, 4));
		variant = ByteBufUtils.readVarInt(additionalData, 2);
		arg1 = ByteBufUtils.readVarInt(additionalData, 2);
		arg2 = ByteBufUtils.readVarInt(additionalData, 2);
		
	}


}