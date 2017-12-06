package com.Splosions.ModularBosses.entity;

import com.Splosions.ModularBosses.MBSounds;
import com.Splosions.ModularBosses.entity.projectile.EntityMBParticleEmitter;
import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class EntityTatters extends EntityMob {

	public static int tattersMaxHealth;
	public static int tattersScytheDmg;
	public static int tattersTeleportChance;
	
	public static int tattersExpDrop;
	public static String[] tattersLoot = new String[]{"100|1|mb:itemNote","1|1|mb:itemNote"};
	
	public float SHOULDERS;

	public float[] StripF1 = new float[40];
	public float[] StripF2 = new float[40];
	public float[] StripF3 = new float[40];
	public float[] StripF4 = new float[40];
	public float[] StripF5 = new float[40];
	public float[] StripF6 = new float[40];
	public float[] StripF7 = new float[40];
	public float[] StripF8 = new float[40];
	public float[] StripF9 = new float[40];
	public float[] StripF10 = new float[40];
	public float[] StripF11 = new float[40];
	public float[] StripF12 = new float[40];
	public float[] StripF13 = new float[40];
	public float[] StripF14 = new float[40];
	public float[] StripF15 = new float[40];
	public float[] StripF16 = new float[40];
	public float[] StripF17 = new float[40];
	public float[] StripF18 = new float[40];
	public float[] StripF19 = new float[40];

	public float[] StripB1 = new float[40];
	public float[] StripB2 = new float[40];
	public float[] StripB3 = new float[40];
	public float[] StripB4 = new float[40];
	public float[] StripB5 = new float[40];
	public float[] StripB6 = new float[40];
	public float[] StripB7 = new float[40];
	public float[] StripB8 = new float[40];
	public float[] StripB9 = new float[40];
	public float[] StripB10 = new float[40];
	public float[] StripB11 = new float[40];
	public float[] StripB12 = new float[40];
	public float[] StripB13 = new float[40];
	public float[] StripB14 = new float[40];
	public float[] StripB15 = new float[40];
	public float[] StripB16 = new float[40];
	public float[] StripB17 = new float[40];
	public float[] StripB18 = new float[40];
	public float[] StripB19 = new float[40];

	public float count;
	public float Ccount;

	/** The Entity this EntityCreature is set to attack. */
	public Entity entityToAttack;

	public int deathTicks;

	private float randomMotionVecX;
	private float randomMotionVecY;
	private float randomMotionVecZ;

	public EntityScythe[] scythes = new EntityScythe[2];
	public int scytheCountMax = 1;

	public EntityPlayer target;

	public int lastAttackCounter;


	public int teleport = 0;

	public EntityTatters(World par1World) {
		super(par1World);
		this.setSize(1F, 3.7F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;

		// AI STUFF
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.25D));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.25D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));

	}

	// stuns the mob
	public boolean isMovementBlocked() {
		if (this.deathTicks > 0) {
			this.target = null;
			return true;
		}
		return false;
	}

	// won't despawn even if the chunk unloads
	protected boolean canDespawn() {
		return true;
	}

	public boolean isBurning() {
		return false;
	}

	public boolean isEntityInvulnerable() {
		return false;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(tattersMaxHealth);
		// Follow Range - default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}

	public static void postInitConfig(Configuration config) {
		tattersMaxHealth = config.get("205 Tatters", "1 [Max Health] Set the Hp of Tatters Spawns [1+]", 200).getInt();
		tattersScytheDmg = config.get("205 Tatters", "2 [Attack Dmg] Thrown Scythe Attack Damage [1+]", 20).getInt();
		tattersTeleportChance = MathHelper.clamp(config.get("205 Tatters", "3 [Attribute] Chance to Teleport on Damage [1/Chance] [1-100]", 2).getInt(), 1, 100);
		
		tattersExpDrop = config.get("205 Tatters", "4 [Attribute] Set Exp drop of Tatters Spawns [1+]", 100).getInt();
		tattersLoot = config.getStringList("5 [Loot]", "205 Tatters", tattersLoot, "Set loot drops for Tatters {% Drop Chance|Quantity|Item Name}");
	}
	
	protected void entityInit() {
		super.entityInit();
	}
	
	


	protected void onDeathUpdate() {
		deathTicks++;
		if (this.deathTicks == 100 && !this.world.isRemote) {
			TargetUtils.dropExp(this, this.tattersExpDrop);
			TargetUtils.dropLoot(this, this.tattersLoot);
			this.setDead();
		}


	}

    protected SoundEvent getAmbientSound()
    {
        return MBSounds.TATTERS_LIVE;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return MBSounds.TATTERS_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return MBSounds.TATTERS_DEATH;
    }

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	public int countScythes() {
		int num = 0;
		for (int i = 0; i < this.scytheCountMax; i++) {
			num += (this.scythes[i].thrown == 1) ? 1 : 0;
		}
		return num;
	}

	public void moveScythe(EntityScythe part, double FrontToBack, double SideToSide, double TopToBot) {
		float f3 = this.rotationYaw * (float) Math.PI / 180.0F;
		float f11 = MathHelper.sin(f3);
		float f4 = MathHelper.cos(f3);
		part.motionX = 0;
		part.motionY = 0;
		part.motionZ = 0;
		part.setLocationAndAngles(this.posX + (double) (f11 * -FrontToBack) + (double) (f4 * SideToSide), this.posY + TopToBot, this.posZ + (double) (f4 * FrontToBack) + (double) (f11 * SideToSide), this.rotationYaw, 0.0F);
	}

	public void throwScythe() {
		if (this.target != null) {
			for (int i = 0; i < this.scytheCountMax; i++) {
				if (this.scythes[i] == null) {
					this.scythes[i] = new EntityScythe(this.world, this, this, 0, 0, 0, 1, 0, 2, 2, 10, tattersScytheDmg);
					if (!this.world.isRemote) {
						this.scythes[i].setThrown(0);
						this.world.spawnEntity(this.scythes[i]);
					}
				} else if (this.scythes[i].thrown == 0) {
					if (this.lastAttackCounter != 0) {
						float off = (i > 0) ? -0.2F : 1.2F;
						moveScythe(this.scythes[i], 0, i - off, 0.5);
					} else if (this.lastAttackCounter == 0) {
						this.scythes[i].setThrown(1);
						this.scythes[i].setSpin(40);
						this.scythes[i].moveForward(2F);
						this.lastAttackCounter = (countScythes() == this.scytheCountMax) ? 100 : 20;
						if (this.lastAttackCounter == 100) {
							teleport();
						}
						break;
					}
				}
			}
		}
	}

	public void teleport() {
		if (this.target != null && !this.world.isRemote) {

			Entity particleEmitter = new EntityMBParticleEmitter(this, this.posX, this.posY, this.posZ, EntityMBParticleEmitter.TATTERS_SMOKE, 0, 0);
			this.world.spawnEntity(particleEmitter);

			this.setPosition(this.target.posX + TargetUtils.getRanNum(-10, 10), this.target.posY, this.target.posZ + TargetUtils.getRanNum(-10, 10));
			this.faceEntity(this.target, 360, 1);
		}
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (rand.nextInt(tattersTeleportChance) == 0 && !this.world.isRemote) {
			this.teleport();
			return false;
		}
		return super.attackEntityFrom(source, amount);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (deathTicks == 0) {


		this.lastAttackCounter -= (this.lastAttackCounter <= 0) ? 0 : 1;

		if (this.target != null) {
			this.getNavigator().tryMoveToEntityLiving(this.target, 0.25D);
		}

		if (this.ticksExisted % 20 == (20 - 1) && !this.world.isRemote && this.target == null) {
			this.target = TargetUtils.findRandomVisablePlayer(this, 20, 4);
		}

		if (this.getHealth() <= this.getMaxHealth() * 0.5F) {
			this.scytheCountMax = 2;
		}

		throwScythe();
		}
	}

}
