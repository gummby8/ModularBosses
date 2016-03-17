package com.Splosions.ModularBosses.client.entity;



import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.client.entity.projectile.EntityChorpSlimeBlob;

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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EntityTatters extends EntityMob
{

	
	
	
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
	
	public float[] StripL1 = new float[40];
	public float[] StripL2 = new float[40];
	public float[] StripL3 = new float[40];
	public float[] StripL4 = new float[40];
	public float[] StripL5 = new float[40];
	public float[] StripL6 = new float[40];
	public float[] StripL7 = new float[40];
	public float[] StripL8 = new float[40];
	public float[] StripL9 = new float[40];
	public float[] StripL10 = new float[40];
	public float[] StripL11 = new float[40];
	public float[] StripL12 = new float[40];
	public float[] StripL13 = new float[40];
	public float[] StripL14 = new float[40];
	
	public float[] StripR1 = new float[40];
	public float[] StripR2 = new float[40];
	public float[] StripR3 = new float[40];
	public float[] StripR4 = new float[40];
	public float[] StripR5 = new float[40];
	public float[] StripR6 = new float[40];
	public float[] StripR7 = new float[40];
	public float[] StripR8 = new float[40];
	public float[] StripR9 = new float[40];
	public float[] StripR10 = new float[40];
	public float[] StripR11 = new float[40];
	public float[] StripR12 = new float[40];
	public float[] StripR13 = new float[40];
	public float[] StripR14 = new float[40];
	
	
	public float count;
	public float Ccount;
	
	

    /** The Entity this EntityCreature is set to attack. */
    public Entity entityToAttack;
    
	public int attackCounter;
	public int deathTicks;

	private float randomMotionVecX;
	private float randomMotionVecY;
	private float randomMotionVecZ;

	byte b0 = this.dataWatcher.getWatchableObjectByte(16);
	private float DeadRot;


	

	public EntityTatters(World par1World) {
		super(par1World);
		//sets hitbox size
		this.setSize(1F, 3F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;

		//AI STUFF
		//this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		//this.tasks.addTask(1, new EntityAIBreakDoor(this));
		//this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.25D, false)); //How fast mob moves towards the player
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.25D, true));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.25D));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.25D, false));
		//this.tasks.addTask(6, new EntityAIWander(this, 0.25D)); //Wander speed
		//this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		//this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		//this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
		

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
		return EnumCreatureAttribute.ARTHROPOD;
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


    
    
    
    
    
	public void onLivingUpdate() {
		super.onLivingUpdate();

	
		
		
		
		
		
	}
	
	
	
	


}
