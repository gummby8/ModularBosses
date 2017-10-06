package com.Splosions.ModularBosses.entity.projectile;

import com.Splosions.ModularBosses.entity.EntityGolem;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBoulder extends EntityMobThrowable implements IEntityAdditionalSpawnData
{

	private EntityGolem shooter;
	public ResourceLocation textureLoc;
	public int textureBlockID;
	
	public EntityBoulder(World world) {
		super(world);
	}

	public EntityBoulder(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityBoulder(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityBoulder(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float Size1, float Size2) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
	
		this.shooter = (EntityGolem) shooter;
		this.textureLoc = this.shooter.textureLoc;
		try{
			textureBlockID = this.shooter.textureBlockID;	
		} catch (Exception e){
			this.setDead();
			return;
		}
		
		
	}

	@Override
	public void onUpdate(){
		super.onUpdate();
		if (this.worldObj.isRemote){
			for (int x = 0; x < 15; x++){
				this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, Math.random() / 5, Math.random() / 5, Math.random() / 5, textureBlockID );
			}		
		}
	}
	
	@Override
	protected void onImpact(MovingObjectPosition mop) {
		
		if (mop.entityHit != null && mop.entityHit instanceof EntityPlayer) {
			Potion potioneffect = (Potion.moveSlowdown);
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			//((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(2, 60,100));
			EntityPlayer player = (EntityPlayer) mop.entityHit;
			//MBExtendedPlayer.get(player).knockdownTime = 60;
		}
		if (mop.entityHit != this.shooter) {
			setDead();
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, this.textureLoc.toString());
		ByteBufUtils.writeVarInt(buffer, textureBlockID, 2);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		textureLoc = new ResourceLocation(ByteBufUtils.readUTF8String(additionalData));
		textureBlockID = ByteBufUtils.readVarInt(additionalData, 2);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		textureLoc = new ResourceLocation(compound.getString("textureLoc"));
		textureBlockID = compound.getInteger("blockID");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setString("textureLoc", this.textureLoc.toString());
		compound.setInteger("blockID", textureBlockID);

	}

}