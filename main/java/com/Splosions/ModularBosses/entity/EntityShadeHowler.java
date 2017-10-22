package com.Splosions.ModularBosses.entity;

import java.util.List;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;
import com.Splosions.ModularBosses.entity.projectile.EntityBlackBomb;
import com.Splosions.ModularBosses.entity.projectile.EntityEnergyClaw;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class EntityShadeHowler extends EntityMob {

	private static final int DEATH_WATCHER = 16;
	private static final int ANI_ID_WATCHER = 17;
	private static final int ANI_FRAME_WATCHER = 18;

	public static final int STAND = 0;
	public static final int JUMP = 2;
	public static final int HOWL = 3;
	public static final int BITE = 4;
	public static final int MELT_MELT = 50;
	public static final int MELT_MOVE = 51;
	public static final int MELT_REFORM = 52;
	public static final int CLAW = 6;
	public static final int BOMB = 7;	

	private int lastAttackCounter = 0;
	private EntityAIWander entityAIWander = new EntityAIWander(this, 0.25F);
	
	Entity target;
	double targetX;
	double targetY;
	double targetZ;

	double jumpX;
	double jumpY;
	double jumpZ;
	boolean jumpOff = false;
	int jumpCount;
	boolean bite;
	
	double X;
	double Z;

	double X1;
	double Z1;

	double X2;
	double Z2;
	
	
	
	public int aniID;
	public int prevaniID;
	public int aniFrame;
	
	public float meltPercent;
	public BlockPos meltDest;
	public int meltTime;
	
	
	public boolean targetLocked;
	public float howlEnd;
	public float howlBegin;

	public double howlEndX;
	public double howlEndZ;
	
	public double howlBeginX;
	public double howlBeginZ;
	private int attackCounter;
	
	public static int shadeHowlerJumpDmg;
	public static int shadeHowlerClawDmg;
	public static int shadeHowlerBombDmg;
	public static int shadeHowlerBombDur;
	public static int shadeHowlerHowlDmg;
	public static int shadeHowlerHowlDur;
	
	public static int shadeHowlerMaxHealth;
	public static int ShadeHowlerExpDrop;
	public static String[] ShadeHowlerLoot = new String[]{"100|1|mb:itemNote","1|1|mb:itemNote"};
	private static int attackCooldown;

	public EntityShadeHowler(World worldIn) {
		super(worldIn);
		this.setSize(1.5F, 3.0F);
		this.isImmuneToFire = true;
		this.experienceValue = 5;
		this.meltPercent = 0;
		this.aniID = STAND;
		

		this.tasks.addTask(0, new EntityAISwimming(this));

		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.21D, false)); 
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.21D, true));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));

		//this.ignoreFrustumCheck = true;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DEATH_WATCHER, 0);
		this.dataWatcher.addObject(ANI_ID_WATCHER, 0);
		this.dataWatcher.addObject(ANI_FRAME_WATCHER, 0);

	}
	
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(shadeHowlerMaxHealth);
		// Follow Range - default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	
	public static void postInitConfig(Configuration config) {
		shadeHowlerMaxHealth = config.get("206 Shade Howler", "01 [Max Health] Shade Howler health... [1+]", 200).getInt();
		
		shadeHowlerJumpDmg = config.get("206 Shade Howler", "02 [Attack Dmg] Shade Howler Pounce Damage... [1+]", 10).getInt();
		shadeHowlerClawDmg = config.get("206 Shade Howler", "03 [Attack Dmg] Shade Howler Claw Damage... [1+]", 20).getInt();
		shadeHowlerBombDmg = config.get("206 Shade Howler", "04 [Attack Dmg] Shade Howler Dark Bomb Damage... [1+]", 50).getInt();
		shadeHowlerBombDur = config.get("206 Shade Howler", "05 [Attack Dmg] Shade Howler Dark Bomb Darkness Duration... [1+]", 5).getInt() * 20;
		shadeHowlerHowlDmg = config.get("206 Shade Howler", "06 [Attack Dmg] Shade Howler Howl Damage... [1+]", 20).getInt();
		shadeHowlerHowlDur = config.get("206 Shade Howler", "07 [Attack Dmg] Shade Howler Howl Darkness Duration... [1+]", 2).getInt() * 20;		
		
		attackCooldown = config.get("206 Shade Howler", "08 [Attack Cooldown] Ammount of seconds between attacks... [1+]", 2).getInt() * 20;
		ShadeHowlerExpDrop = config.get("206 Shade Howler", "09 [Attribute] Set Exp drop of Shade Howler Spawns [1+]", 100).getInt();
		ShadeHowlerLoot = config.getStringList("10 [Loot]", "206 Shade Howler", ShadeHowlerLoot, "Set loot drops for Shade Howler {% Drop Chance|Quantity|Item Name}");
		
	}
	
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		TargetUtils.betaMsg(this);
		
		
		this.ignoreFrustumCheck = true;

		this.aniID = this.dataWatcher.getWatchableObjectInt(ANI_ID_WATCHER);
		this.aniFrame = (this.aniID != this.prevaniID)? 0 : this.aniFrame;
		
		if (!this.worldObj.isRemote && this.target == null) {
			this.target = TargetUtils.findRandomVisablePlayer(this, 20, 4);
		}

		if (this.aniID == STAND && target != null && !this.worldObj.isRemote) {
			this.moveHelper.setMoveTo(this.target.posX, this.target.posY, this.target.posZ, 0.35F);
		} 
		
		if (this.target == null){
			this.tasks.addTask(1, entityAIWander);
		} else {
			this.tasks.removeTask(entityAIWander);
		}
		
		if (!this.worldObj.isRemote && this.aniID == STAND && target != null) {
			attackPicker();
		}

		this.aniFrame++;
		
		
		//***********************STAND************************************

		if (this.aniID == STAND) {
			this.aniFrame = 0;
			this.aniID = STAND;
		}
		
			
		//***********************HOWL************************************
			
		 else if (this.aniID == HOWL && this.aniFrame > 4 && this.aniFrame < 30) {
			if (this.aniFrame == 5) {this.playSound(Sounds.SHADEHOWLER_HOWL, 15F, 1);}
			howl(TargetUtils.getList(this, 7, 4),45,1F,0.5F,shadeHowlerHowlDmg);
			placeHowl(6);
		} else if (this.aniID == HOWL && this.aniFrame > 43) {
			this.aniFrame = 0;
			this.aniID = STAND;
		}
		
		
		//***********************BLACK BOMB************************************
		
		 else if (this.aniID == BOMB && this.aniFrame == 4 && !this.worldObj.isRemote) {
				if (this.aniFrame == 4) {this.playSound(Sounds.SHADEHOWLER_HOWL, 15F, 1);}
				EntityBlackBomb projectile = new EntityBlackBomb(this.worldObj, this, this, 3F, 0, 0, 0, 0,1,1,1, shadeHowlerBombDmg, shadeHowlerBombDur);
				projectile.setPosition(this.posX, this.posY + 1.3F, this.posZ);
				Vec3 vec = this.getLookVec();
				projectile.setThrowableHeading(vec.xCoord, vec.yCoord, vec.zCoord, 0.1F, 0);
				this.worldObj.spawnEntityInWorld(projectile);	
		} else if (this.aniID == BOMB && this.aniFrame > 43) {
			this.aniFrame = 0;
			this.aniID = STAND;
		}
		
		//***********************JUMP************************************
			
		 else if (this.aniID == JUMP && this.aniFrame == 1) {
			 this.jumpCount = 0;
		 }else if (this.aniID == JUMP && this.aniFrame == 5) {
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
			this.bite = false;
			this.motionY = 0.4D;
			this.jumpCount++;
			this.jumpOff = (this.jumpOff) ? false : true;
						
			double d0 = this.posX - this.targetX;
			double d2 = this.posZ - this.targetZ;
			double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
			float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			this.rotationYaw = f + 180;
			
			this.X = (20 * Math.cos(Math.toRadians(this.rotationYaw + 90))) + this.posX;
			this.Z = (20 * Math.sin(Math.toRadians(this.rotationYaw + 90))) + this.posZ;
			
			this.X1 = (10 * Math.cos(Math.toRadians(this.rotationYaw + 180))) + X;
			this.Z1 = (10 * Math.sin(Math.toRadians(this.rotationYaw + 180))) + Z;

			this.X2 = (10 * Math.cos(Math.toRadians(this.rotationYaw))) + X;
			this.Z2 = (10 * Math.sin(Math.toRadians(this.rotationYaw))) + Z;

			} else if (this.aniID == JUMP && this.aniFrame > 5 && this.aniFrame <= 20) {
				if (this.target != null) {
					double d0;
					double d2;
					if (this.target.getDistanceToEntity(this) < 5) {
						d0 = this.posX - this.X;
						d2 = this.posZ - this.Z;
					} else {
						d0 = (this.jumpOff) ? this.posX - this.X1 : this.posX - this.X2;
						d2 = (this.jumpOff) ? this.posZ - this.Z1 : this.posZ - this.Z2;
					}

					double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
					float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
					this.rotationYaw = f + 180;
					moveForward(0.1F);
				}
				
				List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(2, 2, 2));

				if (!list.isEmpty()) {
					this.bite = true;
					this.target = (Entity) list.get(0);
					MBExtendedPlayer.get((EntityPlayer) this.target).knockdownTime = 20;
				}

			} else if (this.aniID == JUMP && this.aniFrame > 20 && this.aniFrame < 25) {
			if (!this.isAirBorne){
				if (this.bite){
					target.attackEntityFrom(DamageSource.causeMobDamage(this), shadeHowlerJumpDmg);
					this.target.posX = this.posX;
					this.target.posY = this.posY;
					this.target.posZ = this.posZ;
					this.aniID = STAND;
					
				} else {
					if (this.jumpCount < 4) {
						this.aniID = JUMP;
						this.aniFrame = 4;	
					}
				}
			}
		} else if (this.aniID == JUMP && this.aniFrame > 30) {
			this.aniID = STAND;
			this.aniFrame = 0;
		} 
		

		
		//***********************MELT************************************

		else if (this.aniID == MELT_MELT || this.aniID == MELT_MOVE || this.aniID == MELT_REFORM) {
			if (this.aniID == MELT_MELT) {
				meltTime = 100;
				this.meltPercent += 5;
				if (this.meltPercent >= 100) {
					this.aniID = MELT_MOVE;
					if (!this.worldObj.isRemote) {
						if (target != null) {
							Vec3 vec = target.getLookVec();
							double dx = target.posX - (vec.xCoord * 2);
							double dy = target.posY;
							double dz = target.posZ - (vec.zCoord * 2);
							meltDest = new BlockPos(dx,dy,dz);	
						} else {
							meltDest = this.getPosition();
						}
					}
					
				}
			} else if (this.aniID == MELT_MOVE) {
				if (!this.worldObj.isRemote) {
				meltTime--;
				this.moveHelper.setMoveTo(meltDest.getX(), meltDest.getY(), meltDest.getZ(), 1);
				this.aniID = (this.getDistanceSq(meltDest) < 2 || meltTime < 0)? MELT_REFORM : MELT_MOVE;
				}
				this.meltPercent = 100;
			} else if (this.aniID == MELT_REFORM) {
				if (target != null) {this.faceEntity(this.target, 500, 500);}
				this.meltPercent -= 5;
				if (this.meltPercent <= -10) {
					attackPicker();
					this.meltPercent = 0;
				}
			}
		}

		//***********************CLAW************************************
		else if (this.aniID == CLAW) {
			if (this.aniFrame == 17) {
				if (!this.worldObj.isRemote) {
					EntityEnergyClaw projectile = new EntityEnergyClaw(this.worldObj, this, this, 3F, 0, 0, 0, 0,1,1,1, shadeHowlerClawDmg);
					projectile.posY = this.posY + 1.5F;
					this.worldObj.spawnEntityInWorld(projectile);	
				}
			} else if (this.aniFrame > 27) {
				this.aniID = STAND;
				this.aniFrame = 0;				
			}
			
		}

		
		
		
		
		
		this.prevaniID = this.aniID;
		if (!this.worldObj.isRemote) {
			this.dataWatcher.updateObject(ANI_ID_WATCHER, aniID);
		}
	}
	
	
	public void attackPicker(){
		if (this.attackCounter <= 0 || this.prevaniID == MELT_REFORM){
			int pick;
			if (this.prevaniID == MELT_REFORM) {
				pick = TargetUtils.getRanNum(4, 6);
			} else {
				pick = TargetUtils.getRanNum(0, 10);	
			}
			
			if (pick < 4){
				this.aniID = MELT_MELT;
			} else if (pick == 4) {
				this.aniID = HOWL;
			} else if (pick == 5) {
				this.aniID = CLAW;
			} else if (pick == 6) {
				this.aniID = BOMB;
			} else if (pick > 6) {
				this.aniID = JUMP;	
			}

			this.aniFrame = 0;
			this.attackCounter = attackCooldown;
			this.dataWatcher.updateObject(ANI_ID_WATCHER, aniID);
		} else {
			this.attackCounter--;
		}
	}

	
	
	/**
	 * Set mob death animations, just be sure to setDead at the end or the model
	 * wont go away
	 */
	protected void onDeathUpdate() {
		super.onDeathUpdate();
		this.aniID = STAND;
		this.target = null;
        if (this.deathTime == 20 && !this.worldObj.isRemote){
			TargetUtils.dropExp(this, this.ShadeHowlerExpDrop);
			TargetUtils.dropLoot(this, this.ShadeHowlerLoot);
        }
		
	}
	
	
	
	public void moveForward(float speed){
		float f2 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F);
		float f3 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F);
		this.motionX += (double)(-1 * speed * f2);
		this.motionZ += (double)(speed * f3);
	}
	
	private void setLastAttackCounter() {
		this.lastAttackCounter = 80;
		this.target = null;
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
				player.addPotionEffect(new PotionEffect(15, shadeHowlerHowlDur, 1));
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), Damage);
				entity.addVelocity(d2 / d4 * force, height, d3 / d4 * force);
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

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.aniID != MELT_MELT || this.aniID != MELT_MOVE || this.aniID != MELT_REFORM) {
				this.Damage(source, amount);
		}
		return false;
	}

	protected boolean Damage(DamageSource Source, float DMGAmmount) {
		return super.attackEntityFrom(Source, DMGAmmount);
	}

}