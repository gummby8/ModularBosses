package com.Splosions.ModularBosses.client.entity;



import java.util.Arrays;
import java.util.List;

import com.Splosions.ModularBosses.Sounds;

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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EntityParagon extends EntityMob
{
	
	boolean Moving = false;
	boolean PreMoving = false;
	boolean Transition = false; 
	
	int AniID = 0;
	int AniFrame = 0;
	
	
	
	
	
	
	
	
										public float HEAD;
										public float CHEST;
											
		public float LSHOULDERGEAR;								public float RSHOULDERGEAR;
	    public float LSHOULDER;									public float RSHOULDER;	    
	    public float LARM;										public float RARM;
	    public float LELBOW;										public float RELBOW;
	    public float LFOREARM;									public float RFOREARM;
	    public float LHAND;										public float RHAND;
	    public float LFINGER11;									public float RFINGER11;
	    public float LFINGER21;									public float RFINGER21;
	    public float LFINGER31;									public float RFINGER31;
	    
	    			public float GRILLL;					public float GRILLR;
	    
										public float HIPy;
	    	    
	    public float LHIPJOINT;									public float RHIPJOINT;
	    public float LTHIGH;									    public float RTHIGH;
	    public float LKNEE;										public float RKNEE;
	    public float LSHIN;										public float RSHIN;
    public float LLEGGEAR;									public float RLEGGEAR;
	    public float LFOOT;										public float RFOOT;

	    
	    
    /** The Entity this EntityCreature is set to attack. */
    public Entity entityToAttack;
    
	public int attackCounter;
	public int deathTicks;


	byte b0 = this.dataWatcher.getWatchableObjectByte(16);
	private float DeadRot;

	public EntityParagon(World par1World) {
		super(par1World);
		//sets hitbox size
		this.setSize(1F, 3F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;

		//AI STUFF
		//this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        //this.tasks.addTask(1, new EntityAIBreakDoor(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.25D, false)); //How fast mob moves towards the player
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.25D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.25D));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.25D, false));
        //this.tasks.addTask(6, new EntityAIWander(this, 0.25D)); //Wander speed
        //this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        //this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        //this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
        
	}

	
	//stuns the mob
	public boolean isMovementBlocked() {
		if (b0 == 1){
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


	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));

	}






	/**
	 * Set mob death animations, just be sure to setDead at the end or the model wont go away 
	 */
	protected void onDeathUpdate() {



		entityToAttack = null;
		byte b1 = 1;
		this.dataWatcher.updateObject(16, Byte.valueOf(b1));
		b0 = this.dataWatcher.getWatchableObjectByte(16);

		++this.deathTicks;


		if (this.deathTicks == 100 && !this.worldObj.isRemote){
			this.dropItem(Item.getItemById(3), 1);
			this.setDead();   	
		}


	}


	@SideOnly(Side.CLIENT)
	public int DeathWatcher()
	{
		if (this.dataWatcher.getWatchableObjectByte(16) == 1) {
			return this.deathTicks;
		} else {
			return -1;
		}
	}

	//@SideOnly(Side.CLIENT)
	public float DeathRotation()
	{
		if (this.dataWatcher.getWatchableObjectByte(16) == 1) {
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
	protected String getHurtSound() {
		return Sounds.CHORP_HURT;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound() {
		return Sounds.CHORP_DEATH;
	}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		this.playSound("mob.zombie.step", 0.15F, 1.0F);
	}





	public EnumCreatureAttribute getCreatureAttribute() {
		return null;
	}


    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 16.0D);
        return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
    }
    
    
    public float Round(Float NumVar){
    	float Rounded = (float) (Math.round(NumVar * 100.0) / 100.0);
    	return Rounded;
    }
    

	public void onLivingUpdate() {
		super.onLivingUpdate();

		//Spawn some particles in the furnace
        if (this.worldObj.isRemote)
        {
            for (int i = 0; i < 2; ++i)
            {
                this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX + (this.rand.nextDouble() - 0.5D), this.posY + 4.5D + this.rand.nextDouble(), this.posZ + this.rand.nextDouble() - 0.5D, this.motionX * 3, 0, this.motionZ * 3, new int[0]);
                this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE, this.posX + (this.rand.nextDouble() - 0.5D), this.posY + 4.5D + this.rand.nextDouble(), this.posZ + this.rand.nextDouble() - 0.5D, this.motionX * 2.5, 0, this.motionZ * 2.5, new int[0]);
                this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + (this.rand.nextDouble() - 0.5D), this.posY + 4.5D + this.rand.nextDouble(), this.posZ + this.rand.nextDouble() - 0.5D, this.motionX * 4, 0, this.motionZ * 4, new int[0]);
            }
        }
        
        

 

        

        
        if (this.motionX == 0 && this.motionZ == 0) {
        	this.Moving = false;
        	this.AniID = 0;
        } else if (this.motionX != 0 || this.motionZ != 0) {
        	this.Moving = true;
        }

       if (this.Moving == true && this.AniID == 0){
    	   Transition = true;
    	   this.AniID = 1;
    	}
       

       


    	PreMoving = Moving;
         
    	
    	
    	DoAnimation();
        
    	
    	
        
	}
	
	
	public void DoAnimation(){
		if (this.AniID == 0){
			Stand();
		} else
		if (this.AniID == 1){
			TransWalk();
		} else
		if (this.AniID == 2){
			Walk();
		}
		
	}
	public void Stand(){
		this.RARM = 0;				this.LARM = 0;
		this.RFOREARM = 0;		this.LFOREARM = 0;
		this.HIPy = -38;
		this.RTHIGH = 0;			this.LTHIGH = 0;
		this.RKNEE = 0;				this.LKNEE = 0;
		this.RSHIN = 0;			this.LSHIN = 0;
		this.RFOOT = 0;			this.LFOOT = 0;
		
		this.AniFrame = 0;
	}
	
	
	
	public void TransWalk(){
		if (this.AniFrame == 0)	{
			this.RARM = 0;			this.LARM = 0;
			this.RFOREARM = 0;		this.LFOREARM = 0;
			this.HIPy = -38;
			this.RTHIGH = 0;		this.LTHIGH = 0;
									this.LKNEE = 0;
			this.RSHIN = 0;			this.LSHIN = 0;
			this.RFOOT = 0;			this.LFOOT = 0;
			
		}
		
		if (this.AniFrame >= 1 && this.AniFrame <= 5)	{
			this.RARM -= 1;				this.LARM += 1.33333;
			this.RFOREARM -= 0.75;		this.LFOREARM -= 1.5;
			this.RTHIGH += 0.75;		this.LTHIGH -= 2;
										this.LKNEE += 1.66666;
			this.RSHIN -= 0.25;			this.LSHIN += 1.66666;
			this.RFOOT += 0.39185;		
		}
		
		if (this.AniFrame >= 6 && this.AniFrame <= 10)	{
			this.RARM -= 1;				this.LARM += 1.33333;
			this.RFOREARM -= 0.75;		this.LFOREARM -= 1.5;
			this.RTHIGH += 0.75;		this.LTHIGH -= 2;
										this.LKNEE += 1.66666;
			this.RSHIN -= 0.25;			this.LSHIN += 1.66666;
			this.RFOOT += 0.39185;
										
		}			
		
		if (this.AniFrame >= 11 && this.AniFrame <= 15)	{
			this.RARM -= 1;				this.LARM += 1.33333;
			this.RFOREARM -= 0.75;		this.LFOREARM -= 1.5;
			this.RTHIGH += 0.75;		this.LTHIGH -= 2;
										this.LKNEE += 1.66666;
			this.RSHIN -= 0.25;			this.LSHIN += 1.66666;
			this.RFOOT += 0.39185;	
		}
		
		if (this.AniFrame >= 16 && this.AniFrame <= 20)	{
			this.RARM -= 1;				this.LARM += 1.33333;
			this.RFOREARM -= 0.75;		this.LFOREARM -= 1.5;
			this.RTHIGH += 0.75;		this.LTHIGH -= 2;
										this.LKNEE -= 2;
			this.RSHIN -= 0.25;			this.LSHIN -= 3;
			this.RFOOT += 0.39185;		
		}
		
		this.AniFrame++;
		if (this.AniFrame > 20){
			this.AniFrame = 0;
			this.AniID = 2;
		}
	}
	
	

	public void Walk (){
		if (this.AniFrame == 0)	{
			this.RARM = -20;				this.LARM = 20;
			this.RFOREARM = -15;		this.LFOREARM = -30;
			this.HIPy = -38;
			this.RTHIGH = 15;			this.LTHIGH = -40;
			this.RKNEE = 0;				this.LKNEE = 16;
			this.RSHIN = -5;			this.LSHIN = 10;
			this.RFOOT = 7.83F;			this.LFOOT = 0;
			
		}
		
		if (this.AniFrame >= 1 && this.AniFrame <= 5)	{
			this.RARM += 1.33333;		this.LARM -= 1.33333;
			this.RFOREARM -= 3;			this.LFOREARM += 3;
			this.HIPy += 0.6;
			this.RTHIGH -= 1.83333;		this.LTHIGH += 1.83333;
			this.RKNEE += 2.66666;		this.LKNEE -= 0.53333;
			this.RSHIN += 2.04;			this.LSHIN -= 0.5;
			this.RFOOT += 4.174;		
		}
		
		if (this.AniFrame >= 6 && this.AniFrame <= 10)	{
			this.RARM += 1.33333;		this.LARM -= 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH -= 1.83333;		this.LTHIGH += 1.83333;
			this.RKNEE += 2.66666;		this.LKNEE -= 0.53333;
			this.RSHIN += 2.04;			this.LSHIN -= 0.5;
										
		}			
		
		if (this.AniFrame >= 11 && this.AniFrame <= 15)	{
			this.RARM += 1.33333;		this.LARM -= 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH -= 1.83333;		this.LTHIGH += 1.83333;
			this.RKNEE += 2.66666;		this.LKNEE -= 0.53333;
			this.RSHIN += 2.04;			this.LSHIN -= 0.5;
										this.LFOOT += 1.044;
		}
		
		if (this.AniFrame >= 16 && this.AniFrame <= 20)	{
			this.RARM += 1.33333;		this.LARM -= 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH -= 1.83333;		this.LTHIGH += 1.83333;
			this.RKNEE -= 1.6;			this.LKNEE -= 0.53333;
			this.RSHIN -= 1.04;			this.LSHIN -= 0.5;
			this.RFOOT -= 1.91333;		this.LFOOT -= 1.044;
		}
		
		if (this.AniFrame >= 21 && this.AniFrame <= 25)	{
			this.RARM += 1.33333;		this.LARM -= 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH -= 1.83333;		this.LTHIGH += 1.83333;
			this.RKNEE -= 1.6;			this.LKNEE -= 0.53333;
			this.RSHIN -= 1.04;			this.LSHIN -= 0.5;
			this.RFOOT -= 1.91333;		this.LFOOT -= 1.044;
		}
		
		if (this.AniFrame >= 26 && this.AniFrame <= 30)	{
			this.RARM += 1.33333;		this.LARM -= 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH -= 1.83333;		this.LTHIGH += 1.83333;
			this.RKNEE -= 1.6;			this.LKNEE -= 0.53333;
			this.RSHIN -= 1.04;			this.LSHIN -= 0.5;
			this.RFOOT -= 1.91333;		this.LFOOT += 2.61;
		}
		
		if (this.AniFrame >= 31 && this.AniFrame <= 35)	{
			this.RARM -= 1.33333;		this.LARM += 1.33333;
			this.RFOREARM += 3;			this.LFOREARM -= 3;
			this.HIPy += 0.6;
			this.RTHIGH += 1.83333;		this.LTHIGH -= 1.83333;
			this.RKNEE -= 0.53333;		this.LKNEE += 2.66666;
			this.RSHIN -= 0.5;			this.LSHIN += 2.04;
										this.LFOOT += 2.61;
		}
		
		if (this.AniFrame >= 36 && this.AniFrame <= 40)	{
			this.RARM -= 1.33333;		this.LARM += 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH += 1.83333;		this.LTHIGH -= 1.83333;
			this.RKNEE -= 0.53333;		this.LKNEE += 2.66666;
			this.RSHIN -= 0.5;			this.LSHIN += 2.04;
			this.RFOOT += 1.044;
		}
		
		if (this.AniFrame >= 41 && this.AniFrame <= 45)	{
			this.RARM -= 1.33333;		this.LARM += 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH += 1.83333;		this.LTHIGH -= 1.83333;
			this.RKNEE -= 0.53333;		this.LKNEE += 2.66666;
			this.RSHIN -= 0.5;			this.LSHIN += 2.04;
			this.RFOOT -= 1.044;
		}
		
		if (this.AniFrame >= 46 && this.AniFrame <= 50)	{
			this.RARM -= 1.33333;		this.LARM += 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH += 1.83333;		this.LTHIGH -= 1.83333;
			this.RKNEE -= 0.53333;		this.LKNEE -= 1.6;
			this.RSHIN -= 0.5;			this.LSHIN -= 1.04;
			this.RFOOT -= 1.044;		this.LFOOT -= 1.39133;
		}
		
		if (this.AniFrame >= 51 && this.AniFrame <= 55)	{
			this.RARM -= 1.33333;		this.LARM += 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH += 1.83333;		this.LTHIGH -= 1.83333;
			this.RKNEE -= 0.53333;		this.LKNEE -= 1.6;
			this.RSHIN -= 0.5;			this.LSHIN -= 1.04;
										this.LFOOT -= 1.39133;
		}
		
		if (this.AniFrame >= 56 && this.AniFrame <= 60)	{
			this.RARM -= 1.33333;		this.LARM += 1.33333;
			this.HIPy -= 0.12;
			this.RTHIGH += 1.83333;		this.LTHIGH -= 1.83333;
			this.RKNEE -= 0.53333;		this.LKNEE -= 1.6;
			this.RSHIN -= 0.5;			this.LSHIN -= 1.04;
			this.RFOOT += 2.61;			this.LFOOT -= 1.39133;
			
		}
		

		this.AniFrame++;
		if (this.AniFrame > 60){
			this.AniFrame = 0;
		}
	}
	
	
	

}
