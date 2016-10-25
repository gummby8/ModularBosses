package com.Splosions.ModularBosses.entity;

import java.util.List;
import java.util.Random;

import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityShadeHowler extends EntityMob {
	/** The explosion radius of spawned fireballs. */
	private int explosionStrength = 1;
	private static final String __OBFID = "CL_00001689";

	public boolean TargetLocked;
	public float howlEnd;
	public float howlBegin;

	public double howlEndX;
	public double howlEndZ;
	
	public double howlBeginX;
	public double howlBeginZ;

	public EntityShadeHowler(World worldIn) {
		super(worldIn);
		this.setSize(1.5F, 3.0F);
		this.isImmuneToFire = true;
		this.experienceValue = 5;
		// AI STUFF
		// this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		// this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.21D, false)); 
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.21D, true));
		//this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.25D));
		//this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.25D, false));
		//this.tasks.addTask(6, new EntityAIWander(this, 0.25D)); // Wander speed
		//this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));

		this.ignoreFrustumCheck = true;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.ignoreFrustumCheck = true;

		howl(TargetUtils.getList(this, 7, 4),45,1F,0.5F,5);
		
		
		placeHowl(6);
	}
	
	
	private void howl(List par1List, float fov, double force, double height, float Damage) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (TargetUtils.isTargetInFrontOf(this, entity, fov) && entity instanceof EntityPlayer && entity.hurtResistantTime == 0) {
				EntityPlayer player = (EntityPlayer) entity;
				double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
				double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
				double d2 = entity.posX - d0;
				double d3 = entity.posZ - d1;
				double d4 = d2 * d2 + d3 * d3;
				entity.hurtResistantTime = 10;
				player.addPotionEffect(new PotionEffect(15, 200, 1));
				player.eyeHeight = 5;
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), Damage);
				entity.addVelocity(d2 / d4 * force, height, d3 / d4 * force);
				System.out.println(entity);
			}
		}
	}
	

	public void placeHowl(float length) {
		if (this.worldObj.isRemote) {
			float angle = (float) Math.toRadians(this.rotationYaw + 90);
			double xe = ((length * MathHelper.cos(angle)) + this.posX);
			double ze = ((length * MathHelper.sin(angle)) + this.posZ);
			howlEndX = xe;
			howlEndZ = ze;
		}
	}

}