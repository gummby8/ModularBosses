package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;




public class EntityBlackBomb extends EntityMobThrowable implements IEntityAdditionalSpawnData 
{



	protected static final int SHOOTER_INDEX = 22;
	protected static final int SCALE = 23;

	public EntityLivingBase Shooter;

	private boolean Explode = false;

	public float Scale;
	
	public int Dmg;
	public int Dur;

	public EntityBlackBomb(World world) {
		super(world);
	}

	public EntityBlackBomb(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityBlackBomb(World world, double x, double y, double z) {
		super(world, x, y, z);
	}


	public EntityBlackBomb(World world, EntityLivingBase shooter, float wobble, float FrontToBack, float YOffset, float SideToSide) {
		super(world, shooter, wobble, FrontToBack, YOffset, SideToSide);
		setShooter(shooter);
		this.Shooter = (EntityLivingBase) getShooter();
	}

	
	public EntityBlackBomb(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide,float Size1,float Size2,int scale, int dmg, int dur) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
		setScale(scale);
		setShooter(shooter);
		this.Shooter = (EntityLivingBase) getShooter();
		this.setRotation(shooter.rotationYaw, shooter.rotationPitch);
		Dmg = dmg;
		Dur = dur;
	}


	@Override
	public void entityInit() {
		super.entityInit();
		dataWatcher.addObject(SHOOTER_INDEX, -1);
		dataWatcher.addObject(SCALE, 1);

		setSize(1,1);
	}

	


	//set to 0 do have 0 spell drop
	@Override
	protected float getGravityVelocity() {
		return 0.0F;
	}



	@Override
	public void onUpdate() {
		super.onUpdate();
		this.Shooter =  (EntityLivingBase) getShooter();
		this.Scale++;
		this.noClip = true;

		if (this.ticksExisted == 195){
			
			if (this.world.isRemote) {
				for (int A = 0; A < 100; ++A){
					float f = (this.rand.nextFloat() - 0.5F) * 20.0F;
					float f1 = (this.rand.nextFloat() - 1.5F);
					float f2 = (this.rand.nextFloat() - 0.5F) * 20.0F;
					this.world.spawnParticle(EnumParticleTypes.LAVA, this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
				}	
			}

		} else if (this.ticksExisted == 200) {
			hurtEntities(this.world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(7, 7, 7)), Dmg); //set real damage here
			setDead();
		}
	}
	

	
	private void hurtEntities(List par1List, float dmg)
	{
		for (int i = 0; i < par1List.size(); ++i)
		{
			Entity entity = (Entity)par1List.get(i);

			if (entity != this.Shooter)
			{
				double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
				double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
				double d2 = entity.posX - d0;
				double d3 = entity.posZ - d1;
				double d4 = d2 * d2 + d3 * d3;
				entity.addVelocity(d2 / d4 * 1, 1, d3 / d4 * 1);
				//((EntityPlayer) entity).addPotionEffect(new PotionEffect(15, Dur, 1));
				((EntityPlayer) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, Dur, 1));
                entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase) this.getShooter()), dmg);
				
			} 
		}
	}
	

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != this.getShooter()) {
			hurtEntities(this.world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(6, 6, 6)), 10); //set real damage here
			setDead();			
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {

	}




}