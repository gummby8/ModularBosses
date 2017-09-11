package com.Splosions.ModularBosses.entity;

import com.Splosions.ModularBosses.blocks.FluidWormBlood;
import com.Splosions.ModularBosses.blocks.ModBlocks;
import com.Splosions.ModularBosses.blocks.ModFluids;
import com.Splosions.ModularBosses.util.TargetUtils;
import com.google.common.base.Predicate;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class EntitySpark extends EntityMob {

	private int deathTicks;
	public boolean shieldUp;
	
	public static int sparkMaxHealth;
	public static int sparkDmg;
	
	public int variant;
	public static final int ORANGE = 1;
	public static final int BLUE = 2;
	public static final int GREEN = 3;
	public static final int PURPLE = 4;
	

	public EntitySpark(World worldIn) {
		super(worldIn);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3D, false)); 
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.3D, true));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIWander(this, 0.25D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1);
		variant = TargetUtils.getRanNum(1, 4);
	}
	
	

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	public static void postInitConfig(Configuration config) {
		sparkMaxHealth = config.get("spark", "[Max Health] Set the Hp of spark Spawns [1+]", 20).getInt();
		sparkDmg = config.get("spark", "[Attack Damage] Set the damage of spark Spawns [1+]", 10).getInt();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		

	}



	@Override
	public void onDeathUpdate() {
		++this.deathTicks;

		if (this.deathTicks > 30) {
			int i;



			this.setDead();

			for (i = 0; i < 20; ++i) {
				double d2 = this.rand.nextGaussian() * 0.02D;
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0, d1, new int[0]);
			}
		}

	}



}
