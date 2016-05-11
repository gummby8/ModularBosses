package com.Splosions.ModularBosses.entity;

import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTatters extends EntityMob {

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

	private static final int TELEPORT = 20;
	public int teleport = 0;

	public EntityTatters(World par1World) {
		super(par1World);
		// sets hitbox size
		this.setSize(1F, 3.7F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;

		// AI STUFF
		// this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		// this.tasks.addTask(1, new EntityAIBreakDoor(this));
		// this.tasks.addTask(2, new EntityAIAttackOnCollide(this,
		// EntityPlayer.class, 0.25D, false)); //How fast mob moves towards the
		// player
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.25D, true));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.25D));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.25D, false));
		// this.tasks.addTask(6, new EntityAIWander(this, 0.25D)); //Wander
		// speed
		// this.tasks.addTask(7, new EntityAIWatchClosest(this,
		// EntityPlayer.class, 8.0F));
		// this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		// this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
		// EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));

	}

	// stuns the mob
	public boolean isMovementBlocked() {
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		// Follow Range - default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(TELEPORT, 0);

	}

	/**
	 * Set mob death animations, just be sure to setDead at the end or the model
	 * wont go away
	 */
	protected void onDeathUpdate() {

		this.setDead();

	}

	@Override
	protected String getHurtSound() {
		return Sounds.TATTERS_HURT;
	}

	@Override
	protected String getDeathSound() {
		return Sounds.TATTERS_DEATH;
	}

	@Override
	protected String getLivingSound() {
		return Sounds.TATTERS_LIVE;
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
					this.scythes[i] = new EntityScythe(this.worldObj, this, this, 0, 0, 0, 1, 0, 2, 2, 10);
					if (!this.worldObj.isRemote) {
						this.scythes[i].setThrown(0);
						this.worldObj.spawnEntityInWorld(this.scythes[i]);
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
							this.playSound(Sounds.TATTERS_TELEPORT, 1F, 1.0F);
							teleport();
						}
						break;
					}
				}
			}
		}
	}

	public void teleport() {
		if (this.target != null) {
			this.dataWatcher.updateObject(TELEPORT, 1);
			this.setPosition(this.target.posX + TargetUtils.getRanNum(-10, 10), this.target.posY, this.target.posZ + TargetUtils.getRanNum(-10, 10));
			this.faceEntity(this.target, 360, 1);
		}
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (rand.nextInt(2) == 0 && !this.worldObj.isRemote) {
			this.teleport();
			return false;
		}
		return super.attackEntityFrom(source, amount);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.ticksExisted % 20 == (20 - 1) && !this.worldObj.isRemote){
			System.out.println(this.getHealth());	
		}
		
		
		if (this.dataWatcher.getWatchableObjectInt(TELEPORT) == 1) {
			for (int i = 0; i < 300; ++i) {
				float x = (this.rand.nextFloat() - 0.5F) * 2F;
				float y = (this.rand.nextFloat() - 0.7F) * 4.5F;
				float z = (this.rand.nextFloat() - 0.5F) * 2F;
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (double) x, this.posY + 2.0D + (double) y, this.posZ + (double) z, 0.0D, 0.0D, 0.0D);
			}
			this.dataWatcher.updateObject(TELEPORT, 0);
		}

		this.lastAttackCounter -= (this.lastAttackCounter <= 0) ? 0 : 1;

		if (this.target != null) {
			this.getNavigator().tryMoveToEntityLiving(this.target, 0.25D);
		}

		if (this.ticksExisted % 20 == (20 - 1) && !this.worldObj.isRemote && this.target == null) {
			this.target = TargetUtils.findRandomVisablePlayer(this, 20, 4);
		}

		if (this.getHealth() <= this.getMaxHealth() * 0.5F) {
			this.scytheCountMax = 2;
		}

		throwScythe();

	}

}
