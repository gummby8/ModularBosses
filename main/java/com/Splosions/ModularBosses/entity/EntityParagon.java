package com.Splosions.ModularBosses.entity;

import java.sql.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.entity.projectile.EntityFlameThrower;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
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
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityParagon extends EntityMob implements IBossDisplayData, IEntityMultiPart, IMob {
	public boolean debugHitboxes = false;

	private static final int DEATH_WATCHER = 16;
	private static final int ANI_ID_WATCHER = 17;
	private static final int ANI_FRAME_WATCHER = 18;

	private static final int STAND = 0;
	private static final int TRANSWALK = 1;
	private static final int WALK = 2;
	private static final int TRANSSPRINT = 3;
	private static final int SPRINT = 4;
	private static final int COLLAPSE = 5;
	private static final int KICK = 6;
	private static final int DOUBLEFISTSLAM = 7;
	private static final int SQUISH = 8;
	private static final int JUMP = 9;
	private static final int FLAMETHROWER = 10;
	private static final int SLAP = 11;

	boolean Moving = false;

	public int AniID = 0;
	public int AniFrame = 0;
	public int PrevAniFrame = 0;
	public int AniPause = 0;

	public boolean sprintAttack = false;
	public int flameAttackCounter = 0;
	private int lastAttackCounter = 0;

	Entity target;
	double targetX = 0;
	double targetY = 0;
	double targetZ = 0;

	double jumpX = 0;
	double jumpY = 0;
	double jumpZ = 0;

	public EntityDragonPart[] paragonPartArray;
	public EntityDragonPart paragonPartFurnace;
	public EntityDragonPart paragonPartRKnee;
	public EntityDragonPart paragonPartLKnee;

	public double KneeHP = 10;

	public double FurnacePosY;

	EntityCustomFallingBlock falling;
	BlockPos pos;

	double X;
	double Z;

	double X1;
	double Z1;

	double X2;
	double Z2;

	Random rand = new Random();

	public int attackCounter;
	public int deathTicks;

	Entity projectile;
	private float DeadRot;

	private boolean retaliation;

	public EntityParagon(World par1World) {
		super(par1World);
		this.paragonPartArray = new EntityDragonPart[] { this.paragonPartFurnace = new EntityDragonPart(this, "furnace", 1.0F, 1.0F), this.paragonPartRKnee = new EntityDragonPart(this, "RKnee", 1.0F, 1.0F), this.paragonPartLKnee = new EntityDragonPart(this, "LKnee", 1.0F, 1.0F) };

		// sets hitbox size
		this.setSize(1F, 3F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;
		this.ignoreFrustumCheck = true; // renders mob even if the camera is not
										// looking at a hitbox

		this.paragonPartFurnace.width = this.paragonPartFurnace.height = 1.3F;
		this.paragonPartRKnee.width = this.paragonPartRKnee.height = 0.9F;
		this.paragonPartLKnee.width = this.paragonPartLKnee.height = 0.9F;

		// AI STUFF
		// this.getNavigator().setBreakDoors(true);
		// this.tasks.addTask(0, new EntityAISwimming(this));
		// this.tasks.addTask(1, new EntityAIBreakDoor(this));
		// this.tasks.addTask(2, new EntityAIAttackOnCollide(this,
		// EntityVillager.class, 0.25D, false)); // How
		// fast
		// mob
		// moves
		// towards
		// the
		// player
		// this.tasks.addTask(6, new EntityAIWander(this, 0.25D)); // Wander
		// speed
		// this.tasks.addTask(7, new EntityAIWatchClosest(this,
		// EntityPlayer.class, 8.0F));
		// this.tasks.addTask(7, new EntityAILookIdle(this));
		// this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		// this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
		// EntityVillager.class, true));
		// this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
		// EntityVillager.class, true));

	}

	// stuns the mob
	public boolean isMovementBlocked() {
		return (this.AniID == COLLAPSE)? true : false;
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
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.01D);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DEATH_WATCHER, 0);
		this.dataWatcher.addObject(ANI_ID_WATCHER, 0);
		this.dataWatcher.addObject(ANI_FRAME_WATCHER, 0);

	}

	/**
	 * Set mob death animations, just be sure to setDead at the end or the model
	 * wont go away
	 */
	protected void onDeathUpdate() {
		 this.target = null; 
		 this.AniPause = 10;
		 
		 for (int i = 0; i < 5; ++i) {
		 float f = (this.rand.nextFloat() - 0.5F) * 5.5F;
         float f1 = (this.rand.nextFloat() - 0.5F) * 5.5F;
         float f2 = (this.rand.nextFloat() - 0.5F) * 5.5F;
		 this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
		 }
		 for (int i = 0; i < 5; ++i) {
		 float f = (this.rand.nextFloat() - 0.5F) * 5.5F;
         float f1 = (this.rand.nextFloat() - 0.5F) * 5.5F;
         float f2 = (this.rand.nextFloat() - 0.5F) * 5.5F;
		 this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
		 }
		  ++this.deathTicks;

		 if (this.deathTicks == 100 && !this.worldObj.isRemote){
		 this.dropItem(Item.getItemById(3), 1); 
		 this.setDead(); 
		 } 
	}

	@Override
	protected String getLivingSound() {
		return Sounds.PARAGON_LIVING;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 * 
	 * @Override protected String getHurtSound() { return Sounds.CHORP_HURT; }
	 */
	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound() {
		return Sounds.CHORP_DEATH;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return null;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		this.AniID = this.dataWatcher.getWatchableObjectInt(ANI_ID_WATCHER);
		this.AniFrame = (this.dataWatcher.getWatchableObjectInt(ANI_FRAME_WATCHER) == this.PrevAniFrame)? this.AniFrame : this.dataWatcher.getWatchableObjectInt(ANI_FRAME_WATCHER);
		this.PrevAniFrame = this.AniFrame;

		if(this.retaliation){
			if (this.target.getDistanceToEntity(this) < 3) {
				this.AniID = SLAP;
				this.AniFrame = 0;
			} else {
				this.AniID = DOUBLEFISTSLAM;
				this.AniFrame = 0;
			}
			this.retaliation = false;
		}
		
		
		
		if (this.target != null && this.AniID == FLAMETHROWER && this.flameAttackCounter > 0) {
			for (int i = 0; i < 5; ++i) {
				this.projectile = new EntityFlameThrower(worldObj, this, (EntityLivingBase) this.target, (rand.nextFloat() * 0.2F) + 0.4F, 2F, 2.2F, -2.3F, 1.9F, 0, 0, 0);
				if (!worldObj.isRemote) {
					worldObj.spawnEntityInWorld(this.projectile);
				}
			}
		} else if (this.target != null && this.AniID == FLAMETHROWER && this.flameAttackCounter == 0) {
			this.AniID = WALK;
			setLastAttackCounter();
		}
		this.flameAttackCounter -= (this.flameAttackCounter == 0) ? 0 : 1;

		setHitBoxes();
		// Spawn some particles in the furnace
		furnaceParticles();
		// Plays sound effects at spesified point sin amimations
		playSoundEffects();

		if (this.ticksExisted % 20 == (20 - 1) && !this.worldObj.isRemote && this.target == null) {
			this.target = TargetUtils.findRandomVisablePlayer(this, 20, 4);
		}

		if (this.ticksExisted % 20 == (20 - 1) && this.target != null && this.lastAttackCounter == 0 && this.flameAttackCounter == 0) {
			if (this.AniID == WALK || this.AniID == TRANSWALK || this.AniID == STAND) {
				pickAttack();
			}
		}
		this.lastAttackCounter -= (this.lastAttackCounter == 0) ? 0 : 1;

		if (this.motionX == 0 && this.motionZ == 0 && this.AniID < TRANSSPRINT) {
			this.Moving = false;
			this.AniID = 0;
		} else if (this.motionX != 0 || this.motionZ != 0) {
			this.Moving = true;
		}
		if (this.Moving == true && this.AniID == 0) {
			this.AniID = 1;
		}

		if (KneeHP <= 0) {
			this.KneeHP = 10;
			this.AniID = 5;
			this.AniFrame = 0;
		}

		if (this.target != null) {
			if (this.AniID == STAND || this.AniID == TRANSWALK || this.AniID == WALK || this.AniID == FLAMETHROWER) {
				this.getNavigator().tryMoveToEntityLiving(this.target, 0.25D);
			} else if (this.AniID == TRANSSPRINT || this.AniID == SPRINT) {
				this.getNavigator().tryMoveToEntityLiving(this.target, 0.6D);
			}
		}

		if (this.AniID == STAND) {
			this.AniFrame = 0;
		} else if (this.AniID == TRANSWALK && this.AniFrame > 19) {
			this.AniFrame = 0;
			this.AniID = WALK;
		} else if (this.AniID == WALK && this.AniFrame > 29 && this.sprintAttack) {
			this.AniFrame = 0;
			this.AniID = TRANSSPRINT;
		} else if (this.AniID == WALK && this.AniFrame > 59 && !this.sprintAttack) {
			this.AniFrame = 0;
		} else if (this.AniID == FLAMETHROWER && this.AniFrame > 59 && !this.sprintAttack) {
			this.AniFrame = 0;
		} else if (this.AniID == TRANSSPRINT && this.AniFrame > 14) {
			this.AniFrame = 0;
			this.AniID = SPRINT;
		} else if (this.AniID == SPRINT && this.AniFrame > 29) {
			this.AniFrame = 0;
			this.AniID = KICK;
		} else if (this.AniID == COLLAPSE && this.AniFrame == 19) {
			this.AniPause = 80;
			this.AniFrame++;
		} else if (this.AniID == COLLAPSE && this.AniFrame > 104) {
			this.AniFrame = 0;
			this.AniID = SQUISH;
		} else if (this.AniID == KICK && this.AniFrame == 10) {
			this.kickEntitiesInListIfInfront(TargetUtils.getList(this, 4, 4), 30, 4, 2, 5);
			this.AniFrame++;
		} else if (this.AniID == KICK && this.AniFrame > 24) {
			this.AniFrame = 0;
			this.AniID = STAND;
			this.sprintAttack = false;
			setLastAttackCounter();
		} else if (this.AniID == SLAP && this.AniFrame == 10) {
			this.kickEntitiesInListIfInfront(TargetUtils.getList(this, 4, 4), 30, 4D, 1D, 5);
			this.AniFrame++;
		} else if (this.AniID == SLAP && this.AniFrame > 24) {
			this.AniFrame = 0;
			this.AniID = STAND;
			pickAttack();
		} else if (this.AniID == SQUISH && this.AniFrame >= 15 && this.AniFrame <= 20) {
			this.playSound("mob.ghast.fireball", 1F, 1.0F);
			CamShake(this, 10, 40);
			for (int i = 0; i < 40; ++i) {
				this.X = ((AniFrame - 14) * Math.cos(Math.toRadians(i * 9))) + this.posX;
				this.Z = ((AniFrame - 14) * Math.sin(Math.toRadians(i * 9))) + this.posZ;
				pos = new BlockPos(this.X, this.posY - 1, this.Z);
				falling = new EntityCustomFallingBlock(this.worldObj, this, this.X, this.posY - 1, this.Z, 0.4F, i * 9, pos);
				if (!this.worldObj.isRemote) {
					this.worldObj.spawnEntityInWorld(falling);
				}
			}
			this.AniFrame++;
		} else if (this.AniID == SQUISH && this.AniFrame > 29) {
			this.AniID = STAND;
			this.AniFrame = 0;
			setLastAttackCounter();
		} else if (this.AniID == JUMP && this.AniFrame == 13) {
			if (this.target != null) {
				this.targetX = target.posX;
				this.targetZ = target.posZ;
				this.jumpX = this.posX;
				this.jumpZ = this.posZ;
			} else {
				this.targetX = this.posX;
				this.targetZ = this.posZ;
				this.jumpX = this.posX;
				this.jumpZ = this.posZ;
			}
			this.motionY = 1.5;
			System.out.println("JUMP!");
			this.AniFrame++;
		} else if (this.AniID == JUMP && this.AniFrame > 13 && this.AniFrame <= 20) {
			if (this.target != null) {
				double d0 = this.posX - this.target.posX;
				double d2 = this.posZ - this.target.posZ;
				double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
				float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
				this.rotationYaw = f + 180;
			}
			for (int i = 0; i < 5; ++i) {
				this.projectile = new EntityFlameThrower(this.worldObj, this, 4, (rand.nextFloat() * 0.5F) - 1.5F, rand.nextFloat() + 4, rand.nextFloat() * 0.2F);
				if (!worldObj.isRemote) {
					worldObj.spawnEntityInWorld(this.projectile);
				}
			}
			for (int i = 0; i < 5; ++i) {
				this.projectile = new EntityFlameThrower(this.worldObj, this, 4, (rand.nextFloat() * 0.5F) + 1F, rand.nextFloat() + 4, rand.nextFloat() * 0.2F);
				worldObj.spawnEntityInWorld(this.projectile);

			}
			this.AniFrame++;
		} else if (this.AniID == JUMP && this.AniFrame >= 55 && this.AniFrame <= 60) {
			this.playSound("mob.ghast.fireball", 1F, 1.0F);
			CamShake(this, 10, 40);
			for (int i = 0; i < 40; ++i) {
				this.X = ((AniFrame - 54) * Math.cos(Math.toRadians(i * 9))) + this.posX;
				this.Z = ((AniFrame - 54) * Math.sin(Math.toRadians(i * 9))) + this.posZ;
				pos = new BlockPos(this.X, this.posY - 1, this.Z);
				falling = new EntityCustomFallingBlock(this.worldObj, this, this.X, this.posY - 1, this.Z, 0.4F, i * 9, pos);
				if (!this.worldObj.isRemote) {
					this.worldObj.spawnEntityInWorld(falling);
				}
			}
			this.AniFrame++;
		} else if (this.AniID == JUMP && this.AniFrame > 69) {
			this.AniID = STAND;
			this.AniFrame = 0;
			setLastAttackCounter();
		} else if (this.AniID == DOUBLEFISTSLAM && this.AniFrame > 34) {
			this.AniFrame = 0;
			this.AniID = STAND;
			setLastAttackCounter();
		} else if (this.AniID == DOUBLEFISTSLAM && this.AniFrame > 14) {
			this.playSound("mob.ghast.fireball", 1F, 1.0F);
			CamShake(this, 10, 40);
			this.attackCounter = this.AniFrame - 12;

			this.X = (attackCounter * Math.cos(Math.toRadians(this.rotationYaw + 90))) + this.posX;
			this.Z = (attackCounter * Math.sin(Math.toRadians(this.rotationYaw + 90))) + this.posZ;

			this.X1 = (1 * Math.cos(Math.toRadians(this.rotationYaw + 180))) + X;
			this.Z1 = (1 * Math.sin(Math.toRadians(this.rotationYaw + 180))) + Z;

			this.X2 = (1 * Math.cos(Math.toRadians(this.rotationYaw))) + X;
			this.Z2 = (1 * Math.sin(Math.toRadians(this.rotationYaw))) + Z;

			this.pos = new BlockPos(this.X, this.posY - 1, this.Z);
			this.falling = new EntityCustomFallingBlock(this.worldObj, this, this.X, this.posY - 1, this.Z, 0.4F, this.rotationYaw, this.pos);
			if (!this.worldObj.isRemote) {
				this.worldObj.spawnEntityInWorld(this.falling);
			}

			this.pos = new BlockPos(this.X1, this.posY - 1, this.Z1);
			this.falling = new EntityCustomFallingBlock(this.worldObj, this, this.X1, this.posY - 1, this.Z1, 0.4F, this.rotationYaw, this.pos);
			if (!this.worldObj.isRemote) {
				this.worldObj.spawnEntityInWorld(this.falling);
			}

			this.pos = new BlockPos(this.X2, this.posY - 1, this.Z2);
			this.falling = new EntityCustomFallingBlock(this.worldObj, this, this.X2, this.posY - 1, this.Z2, 0.4F, this.rotationYaw, this.pos);
			if (!this.worldObj.isRemote) {
				this.worldObj.spawnEntityInWorld(falling);
			}
			this.AniFrame++;
		} else if (this.AniPause == 0) {
			this.AniFrame++;
		} else {
			this.AniPause -= (this.AniPause > 0) ? 1 : 0;
		}

		if (this.onGround == false && target != null && this.AniID == JUMP) {
			this.motionX = (this.jumpX - this.targetX) / -35;
			this.motionZ = (this.jumpZ - this.targetZ) / -35;
		}

		if (!this.worldObj.isRemote) {
			this.dataWatcher.updateObject(ANI_ID_WATCHER, AniID);
			this.dataWatcher.updateObject(ANI_FRAME_WATCHER, AniFrame);
		}
	}

	private void pickAttack() {
		int attack = rand.nextInt(3);
		switch (attack) {
		case 0:
			this.sprintAttack = true;
			break;
		case 1:
			this.AniID = JUMP;
			this.AniFrame = 0;
			break;
		case 2:
			this.AniID = FLAMETHROWER;
			this.flameAttackCounter = 80;
			break;
		default:
			this.AniID = STAND;
			this.AniFrame = 0;
			break;
		}

	}

	private void setLastAttackCounter() {
		this.lastAttackCounter = 80;
		this.target = null;
	}

	private void playSoundEffects() {
		if (this.AniID == WALK) {
			if (this.AniFrame == 0) {
				this.playSound(Sounds.PARAGON_LFOOT, 1F, 1.0F);
			} else if (this.AniFrame == 30) {
				this.playSound(Sounds.PARAGON_RFOOT, 1F, 1.0F);
			}
		} else if (this.AniID == SPRINT) {
			if (this.AniFrame == 0) {
				this.playSound(Sounds.PARAGON_LFOOT, 1F, 1.0F);
			} else if (this.AniFrame == 15) {
				this.playSound(Sounds.PARAGON_RFOOT, 1F, 1.0F);
			}
		} else if (this.AniID == COLLAPSE) {
			if (this.AniFrame == 1) {
				this.playSound(Sounds.PARAGON_COLLAPSE, 1F, 1.0F);
			} else if (this.AniFrame == 20 && this.AniPause > 70) {
				CamShake(this, 10, 40);
			} else if (this.AniFrame == 65 || this.AniFrame == 75) {
				this.playSound(Sounds.PARAGON_CHEST_DOOR_CLOSE, 1F, 1.0F);
			} else if (this.AniFrame == 80) {
				this.playSound(Sounds.PARAGON_STAND_UP, 1F, 1.0F);
			}
		} else if (this.AniID == FLAMETHROWER && this.AniFrame == 1) {
			this.playSound(Sounds.PARAGON_FLAME_THROWER, 1F, 1.0F);
		} else if (this.AniID == JUMP && this.AniFrame == 13) {
			this.playSound(Sounds.PARAGON_JUMP_JETS, 1F, 1.0F);
		}

	}

	private void furnaceParticles() {
		if (this.worldObj.isRemote) {
			double y = (this.AniID == COLLAPSE && this.AniFrame > 19 && this.AniFrame < 41) ? -0.3D : 0;
			for (int i = 0; i < 2; ++i) {
				this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.paragonPartFurnace.posX + (this.rand.nextDouble() - 0.5D), this.paragonPartFurnace.posY + this.rand.nextDouble(), this.paragonPartFurnace.posZ + this.rand.nextDouble() - 0.5D, this.motionX, y, this.motionZ, new int[0]);
				this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE, this.paragonPartFurnace.posX + (this.rand.nextDouble() - 0.5D), this.paragonPartFurnace.posY + this.rand.nextDouble(), this.paragonPartFurnace.posZ + this.rand.nextDouble() - 0.5D, this.motionX, y, this.motionZ, new int[0]);
				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.paragonPartFurnace.posX + (this.rand.nextDouble() - 0.5D), this.paragonPartFurnace.posY + this.rand.nextDouble(), this.paragonPartFurnace.posZ + this.rand.nextDouble() - 0.5D, this.motionX, y, this.motionZ, new int[0]);

			}
		}
	}

	/**
	 * Attacks all entities inside this list, dealing 5 hearts of damage.
	 */
	private void kickEntitiesInListIfInfront(List par1List, float fov, double force, double height, float Damage) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (TargetUtils.isTargetInFrontOf(this, entity, fov)) {
				double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
				double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
				double d2 = entity.posX - d0;
				double d3 = entity.posZ - d1;
				double d4 = d2 * d2 + d3 * d3;
				entity.addVelocity(d2 / d4 * force, height, d3 / d4 * force);
				System.out.println(entity);
			}

		}
	}

	@Override
	public World getWorld() {
		return this.worldObj;
	}

	/*
	 * Called to attack the mob from the damage source that just hit it
	 */
	public boolean attackEntityFromPart(EntityDragonPart Part, DamageSource Source, float DMGAmmount) {
		if (Part == this.paragonPartLKnee || Part == this.paragonPartRKnee) {
			if (Source.getEntity() instanceof EntityPlayer && rand.nextInt(4) == 0) {
				if (this.AniID == STAND || this.AniID == TRANSWALK || this.AniID == WALK) {
					this.retaliation = true;
					this.target = Source.getEntity(); 
				}
			}

			if (DMGAmmount > 0 && !this.worldObj.isRemote && this.AniID != SLAP && this.AniID != DOUBLEFISTSLAM && this.AniID != COLLAPSE) {
				this.playSound(Sounds.PARAGON_KNEE_HURT, 1F, 1.0F);
				KneeHP--;
			}
		}
		if (Part == this.paragonPartFurnace)

		{
			if (this.AniID == COLLAPSE && this.AniFrame > 19 && this.AniFrame < 41) {
				this.Damage(Source, DMGAmmount);
				this.playSound(Sounds.PARAGON_FURNACE_HURT, 1F, 1.0F);
				return true;
			} else {
				this.playSound(Sounds.PARAGON_FURNACE_DEFLECT, 1F, 1.0F);
				return false;
			}
		}
		return false;

	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source instanceof EntityDamageSource && ((EntityDamageSource) source).getIsThornsDamage()) {
			this.Damage(source, amount);
		}

		return false;
	}

	protected boolean Damage(DamageSource Source, float DMGAmmount) {
		return super.attackEntityFrom(Source, DMGAmmount);
	}

	/**
	 * Return the Entity parts making up this Entity (currently only for
	 * dragons)
	 */
	public Entity[] getParts() {
		return this.paragonPartArray;
	}

	private void setHitBoxes() {
		if (this.AniID == COLLAPSE && this.AniFrame > 19 && this.AniFrame <= 40) {
			moveHitBoxes(this.paragonPartFurnace, 1.5D, 0, 2.3D);
		} else if (this.AniID == COLLAPSE && this.AniFrame > 40 && this.AniFrame <= 90) {
			moveHitBoxes(this.paragonPartFurnace, 0.8D, 0, 2.6D);
		} else {
			moveHitBoxes(this.paragonPartFurnace, 0.5D, 0, 4.6D);
		}

		moveHitBoxes(this.paragonPartRKnee, 0D, -1.2D, 1.7D);
		moveHitBoxes(this.paragonPartLKnee, 0D, 1.2D, 1.7D);

	}

	/*
	 * Called to move the hitboses of the knees when the mob turns
	 */
	public void moveHitBoxes(EntityDragonPart part, double FrontToBack, double SideToSide, double TopToBot) {

		float f3 = this.rotationYaw * (float) Math.PI / 180.0F;
		float f11 = MathHelper.sin(f3);
		float f4 = MathHelper.cos(f3);

		part.onUpdate();
		part.setLocationAndAngles(this.posX + (double) (f11 * -FrontToBack) + (double) (f4 * SideToSide), this.posY + TopToBot, this.posZ + (double) (f4 * FrontToBack) + (double) (f11 * SideToSide), 0.0F, 0.0F);

	}

	/**
	 * Camera Shake stuff 4 intensity is pretty good shake
	 */
	boolean CamShake = false;
	float CamShakeIntensity;

	public void CamShake(Entity entity, float distance, float Intenstity) {
		if (this.worldObj.isRemote) {
			List<EntityPlayer> players = entity.worldObj.getEntitiesWithinAABB(EntityPlayer.class, entity.getEntityBoundingBox().expand(distance, 4, distance));
			this.CamShake = (this.CamShake == false) ? true : false;
			this.CamShakeIntensity = (this.CamShake) ? Intenstity : -Intenstity;
			for (EntityPlayer player : players) {
				player.setAngles(0, CamShakeIntensity);
			}
		}
	}

}
