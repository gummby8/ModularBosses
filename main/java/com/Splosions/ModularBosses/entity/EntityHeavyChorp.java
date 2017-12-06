package com.Splosions.ModularBosses.entity;


import com.Splosions.ModularBosses.MBSounds;
import com.Splosions.ModularBosses.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;


public class EntityHeavyChorp extends EntityMob
{
	
	public static int heavychorpMaxHealth;
	public static int heavychorpTouchDmg;
	public static int heavychorpSlimeDmg;
	public static int heavychorpSlimeSlow;
	public static int heavychorpSlimeSlowDuration;
	
	public static int heavyChorpExpDrop;
	public static String[] heavyChorpLoot = new String[]{"100|1|mb:itemNote","1|1|mb:itemNote"};
	
    /** The Entity this EntityCreature is set to attack. */
    public Entity entityToAttack;
	
    public int attackCounter;
    public int deathTicks;
    
	private float randomMotionVecX;
	private float randomMotionVecY;
	private float randomMotionVecZ;
	
	private float DeadRot;

	public EntityHeavyChorp(World par1World) {
		super(par1World);
		//sets hitbox size
		this.setSize(3F, 7F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;
		
		//AI STUFF
		//this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        //this.tasks.addTask(1, new EntityAIBreakDoor(this));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.25D));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.25D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 0.25D)); //Wander speed
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
	}
	
	//stuns the mob
    public boolean isMovementBlocked() {
    	if (this.getHealth() <= 0){
    		return true;
    	} else {
    		return false;
    	}
    }
	//won't despawn even if the chunk unloads
	 protected boolean canDespawn()
	    {
		 return true;
	    }
	
    public boolean isBurning()
    {
        return false;
    }
	
    public boolean isEntityInvulnerable()
    {
        return false;
    }
	
	protected boolean isAIEnabled() {
		return true;
	}
	
	protected void applyEntityAttributes()
	{
	  super.applyEntityAttributes();
	  // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
	  this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(heavychorpMaxHealth);
	  // Follow Range - default 32.0D - min 0.0D - max 2048.0D
	  this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
	  // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
	  this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	  // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
	  this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.699D);
	  // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
	  this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(heavychorpTouchDmg);
	}
	
	public static void postInitConfig(Configuration config) {
		heavychorpMaxHealth = config.get("207 Heavy Chorp", "1 [Max Health] Set the Hp of Heavy Chorp Spawns [1+]", 600).getInt();
		heavychorpTouchDmg = config.get("207 Heavy Chorp", "2 [Attack Damage] Set the Touch Damage of Heavy Chorp Spawns [1+]", 100).getInt();
		heavychorpSlimeDmg = config.get("207 Heavy Chorp", "3 [Attack Damage] Set Slime Damage of Heavy Chorp Spawns [1+]", 10).getInt();
		heavychorpSlimeSlow = config.get("207 Heavy Chorp", "4 [Attribute] Set Slime Slow Debuff Strength of Heavy Chorp Spawns [1+]", 4).getInt();
		heavychorpSlimeSlowDuration = config.get("207 Heavy Chorp", "5 [Attribute] Set Slime Slow Debuff Durration of Heavy Chorp Spawns [1+]", 6).getInt() * 20;
		
		heavyChorpExpDrop = config.get("207 Heavy Chorp", "6 [Attribute] Set Exp drop of Heavy Chorp Spawns [1+]", 100).getInt();
		heavyChorpLoot = config.getStringList("7 [Loot]", "207 Heavy Chorp", heavyChorpLoot, "Set loot drops for Heavy Chorp {% Drop Chance|Quantity|Item Name}");
	}
	
    protected void entityInit()
    {
        super.entityInit();
    }
    

	
	
	
	
	/**
	 * Set mob death animations, just be sure to setDead at the end or the model wont go away 
	 */
	 protected void onDeathUpdate() {
		 
		 
         float f = (this.rand.nextFloat() - 0.5F) * 4.0F;
         float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
         float f2 = (this.rand.nextFloat() - 0.5F) * 4.0F;
		 this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
		 
		 
		 entityToAttack = null;
		 ++this.deathTicks;
		 
		 
	        if (this.deathTicks >= 100 && !this.world.isRemote){
					TargetUtils.dropExp(this, this.heavyChorpExpDrop);
					TargetUtils.dropLoot(this, this.heavyChorpLoot);
	        	this.setDead();   	
	        }
	        
		 
	 }
	 
	 
	   // @SideOnly(Side.CLIENT)
	    public int DeathWatcher()
	    {
	        if (this.getHealth() <= 0) {
	        	return this.deathTicks;
	        } else {
	        	return -1;
	        }
	    }
	    
	    //@SideOnly(Side.CLIENT)
	    public float DeathRotation()
	    {
	        if (this.getHealth() <= 0) {
	  		  	this.DeadRot += 0.0003F;
			  if (this.DeadRot > 0.209F) {
				this.DeadRot = 0.209F;
			  }
	        } else {
	        	return 0;
	        }
			return DeadRot;
	    }
	
    /**
     * Returns the sound this mob makes while it's alive.
     
    protected String getLivingSound() {
        return "mob.zombie.say";
    }
    */
    
		/**
		 * Returns the sound this mob makes when it is hurt.
		 */
		@Override
	    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
	    {
	    	return MBSounds.CHORP_HURT;
	    }

		/**
		 * Returns the sound this mob makes on death.
		 */
		@Override
		protected SoundEvent getDeathSound() {
			return MBSounds.CHORP_DEATH;
		}

		/**
		 * Plays step sound
		 */
	    protected SoundEvent getStepSound()
	    {
	        return SoundEvents.ENTITY_ZOMBIE_STEP;
	    }



    
    
    
    public EnumCreatureAttribute getCreatureAttribute() {
    	return EnumCreatureAttribute.ARTHROPOD;
    }
    

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = this.world.getClosestPlayerToEntity(this, 25.0D);
        return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
    }
    
    
    public void onLivingUpdate() {
    	super.onLivingUpdate();
    	
		float distance = 0.0F;
		
		
		if (entityToAttack == null) {
			entityToAttack = findPlayerToAttack();
			
		} else if (entityToAttack.isEntityAlive() && entityToAttack instanceof EntityPlayer) {
			distance = entityToAttack.getDistanceToEntity(this);
			if (distance > 25.0F) {
				entityToAttack = null;
			} else if (canEntityBeSeen(entityToAttack)) {
				faceEntity(entityToAttack, 30.0F, 120.0F);
				attackEntity(entityToAttack, distance);
			}
		} else {
			entityToAttack = null;
		}


    }
    
	
	protected void attackEntity(Entity entity, float distance) {
		EntityLivingBase ent = (EntityLivingBase) entity;
		if (!this.world.isRemote){
		if (!ent.isPotionActive(MobEffects.SLOWNESS) && this.getHealth() > 0) {
			if (this.attackCounter == 40) {
				this.playSound(MBSounds.CHORP_SLIME, 1.0F, 1.0F);
			}
			if (this.attackCounter >= 40) {
				float f = (float) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
				Entity projectile;
				for (int i = 0; i < 4; i++){
						projectile = new EntityChorpSlimeBlob(world, this, (EntityLivingBase) entity, 1.1F, 14,0,0,0,0,0, heavychorpSlimeDmg, heavychorpSlimeSlowDuration, heavychorpSlimeSlow);
						world.spawnEntity(projectile);
					}	
			}
			if (this.attackCounter >= 60) {
				this.attackCounter = -40;
			}
		}

		this.attackCounter++;
		
		}		
	}
    	 
    	 
}
