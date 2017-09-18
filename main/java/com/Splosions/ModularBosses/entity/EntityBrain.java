package com.Splosions.ModularBosses.entity;

import com.Splosions.ModularBosses.blocks.FluidWormBlood;
import com.Splosions.ModularBosses.blocks.ModBlocks;
import com.Splosions.ModularBosses.blocks.ModFluids;
import com.Splosions.ModularBosses.entity.projectile.EntityBrainEnergy;
import com.Splosions.ModularBosses.entity.projectile.EntityCustomFallingBlock;
import com.Splosions.ModularBosses.entity.projectile.EntityEnergyArrow;
import com.Splosions.ModularBosses.util.TargetUtils;
import com.google.common.base.Predicate;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class EntityBrain extends EntityMob {

	private int deathTicks;
	public boolean shieldUp;
	public EntityPlayer target;

	public static int brainMaxHealth;
	public static int brainDmg;

	public EntityBrain(World worldIn) {
		super(worldIn);
		//sets hitbox size
		this.setSize(4F, 3F);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.3D, true));
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
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		
	}

	public static void postInitConfig(Configuration config) {
		brainMaxHealth = config.get("Brain", "[Max Health] Set the Hp of Brain Spawns [1+]", 20).getInt();
		brainDmg = config.get("Brain", "[Attack Damage] Set the damage of Brain Spawns [1+]", 10).getInt();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.ticksExisted % 20 == (20 - 1)) {
			target = TargetUtils.findRandomVisablePlayer(this, 15, 5);
			shieldUp = (sparkCheck()) ? false : true;
		}

		if (this.ticksExisted % 600 == (20 - 1) && !this.worldObj.isRemote && target != null) {
			int count = TargetUtils.getRanNum(1, 6);
			for (int i = 1; i < count; ++i) {
				EntitySpark spark = new EntitySpark(worldObj);
				spark.setPosition(this.posX + TargetUtils.getRanNum(-5, 5), this.posY, this.posZ + TargetUtils.getRanNum(-5, 5));
				this.worldObj.spawnEntityInWorld(spark);	
			}
		}

		
		if (this.ticksExisted % 100 == (20 - 1) && !this.worldObj.isRemote) {
			int shots = TargetUtils.getRanNum(12, 20);
			float direction = 360 / shots;
			for (int i = 0; i < shots; ++i) {
				EntityBrainEnergy energy = new EntityBrainEnergy(this.worldObj, this.posX,this.posY,this.posZ, (i * direction));
				this.worldObj.spawnEntityInWorld(energy);
			}
		}
		
		
		
		
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
				this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL,
						this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width,
						this.posY + (double) (this.rand.nextFloat() * this.height),
						this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0,
						d1, new int[0]);
			}
		}

	}

	public boolean sparkCheck() {
		return TargetUtils.getSpecificList(this, EntitySpark.class, 15, 5).isEmpty();

	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (shieldUp) {
			return false;
		}
		return super.attackEntityFrom(source, amount);
	}

}
