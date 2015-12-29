package com.Splosions.ModularBosses.client.entity;



import java.sql.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.Splosions.ModularBosses.Sounds;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EntityParagon extends EntityMob implements IBossDisplayData, MBEntityMultiPart, IMob
{
	
	
	boolean Moving = false;
	boolean PreMoving = false;
	boolean Transition = false;
	
	public int PreAniID = 0;
	public int AniID = 0;
	public int AniFrame = 0;

	public MBEntityPart[] paragonPartArray;
	public MBEntityPart paragonPartFurnace;
	public MBEntityPart paragonPartRKnee;
	public MBEntityPart paragonPartLKnee;
		
	public double KneeHP = 10; 
	
	public double FurnacePosY;


	    
	    
    /** The Entity this EntityCreature is set to attack. */
    public Entity entityToAttack;
    
	public int attackCounter;
	public int deathTicks;


	byte b0 = this.dataWatcher.getWatchableObjectByte(16);
	private float DeadRot;

	public EntityParagon(World par1World) {
		super(par1World);
		this.paragonPartArray = new MBEntityPart[] {this.paragonPartFurnace = new MBEntityPart(this, "furnace", 1.0F, 1.0F), this.paragonPartRKnee = new MBEntityPart(this, "RKnee", 1.0F, 1.0F), this.paragonPartLKnee = new MBEntityPart(this, "LKnee", 1.0F, 1.0F)};
		
		//sets hitbox size
		this.setSize(1F, 3F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;
		this.ignoreFrustumCheck = true; //renders mob even if the camera is not looking at a hitbox
		

		
		
		
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
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.01D);
	}


	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(17, 0);
		this.dataWatcher.addObject(18, 0);
		
		

	}






	/**
	 * Set mob death animations, just be sure to setDead at the end or the model wont go away 
	 */
	protected void onDeathUpdate() {
		this.setDead(); 
		
		
		/**
		entityToAttack = null;
		byte b1 = 1;
		this.dataWatcher.updateObject(16, Byte.valueOf(b1));
		b0 = this.dataWatcher.getWatchableObjectByte(16);

		++this.deathTicks;


		if (this.deathTicks == 100 && !this.worldObj.isRemote){
			this.dropItem(Item.getItemById(3), 1);
			this.setDead();   	
		}
		*/


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
    
    
    /**
     * Pushes all entities inside the list away from the enderdragon.
     */
    private void collideWithEntities(List p_70970_1_)
    {
        double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
        double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
        Iterator iterator = p_70970_1_.iterator();

        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();

            if (entity instanceof EntityLivingBase)
            {
            	if(!this.worldObj.isRemote){System.out.println(entity.getEntityId());}
                double d2 = entity.posX - d0;
                double d3 = entity.posZ - d1;
                double d4 = d2 * d2 + d3 * d3;
                //entity.addVelocity(d2 / d4 * 3.0D, 3D, d3 / d4 * 3.0D);
            }
        }
    }
	

    

	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		
		System.out.println(KneeHP);
		
		KneeHP = (KneeHP == 0) ? 10 : KneeHP;
		
		
		this.paragonPartFurnace.width = this.paragonPartFurnace.height = 1.3F;
		this.paragonPartRKnee.width = this.paragonPartRKnee.height = 0.9F;
		this.paragonPartLKnee.width = this.paragonPartLKnee.height = 0.9F;
		
		moveHitBoxes(this.paragonPartFurnace, 0.5D, 0, 4.6D);
        moveHitBoxes(this.paragonPartRKnee, 0.0D, -1, 1.7D);
        moveHitBoxes(this.paragonPartLKnee, 0.0D, 1, 1.7D);
        
		
        this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.paragonPartFurnace.getEntityBoundingBox()));
        this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.paragonPartRKnee.getEntityBoundingBox()));
        this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.paragonPartLKnee.getEntityBoundingBox()));
		
		
		this.AniID = this.dataWatcher.getWatchableObjectInt(17);
		this.AniFrame = this.dataWatcher.getWatchableObjectInt(18);

		
		
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
        
   

        
        

        if (!this.worldObj.isRemote){
        	
        	if (this.motionX == 0 && this.motionZ == 0 && this.AniID != 5) {
        		this.Moving = false;
        		this.AniID = 0;
        	} else if (this.motionX != 0 || this.motionZ != 0) {
        		this.Moving = true;
        	}
        	if (this.Moving == true && this.AniID == 0){
        		this.AniID = 1; 
    		}
       
        
    
        
     
       
		if (this.AniID == 0){
			this.AniFrame = 0;
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.699D);
		}else if (this.AniID == 1 && this.AniFrame > 19){
				this.AniFrame = 0;
				this.AniID = 2;
		}else if (this.AniID == 2 && this.AniFrame > 59){
				this.AniFrame = 2;
				this.AniFrame = 0;
				
		}else if (this.AniID == 3 && this.AniFrame > 14){
			this.AniFrame = 0;
			this.AniID = 4;
		}else if (this.AniID == 4 && this.AniFrame > 29){
			this.AniFrame = 0;
			this.AniID = 5;
		}else if (this.AniID == 5 && this.AniFrame == 10 ){
			this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(8.0D, 8.0D, 8.0D)));
			System.out.println("KICK!");
			this.AniFrame++;
		}else if (this.AniID == 5 && this.AniFrame > 24){
			this.AniFrame = 0;
			this.AniID = 0;
		}else{
			this.AniFrame++;
		}
		
        }
    		 

			//System.out.println("AniFrame = " + this.AniFrame);

			this.dataWatcher.updateObject(17, AniID);
			this.dataWatcher.updateObject(18, AniFrame);
		
		
	}
	
	
	public void moveHitBoxes(MBEntityPart part, double FrontToBack, double SideToSide, double TopToBot){
        
        float f3 = this.rotationYaw * (float)Math.PI / 180.0F;
        float f11 = MathHelper.sin(f3);
        float f4 = MathHelper.cos(f3);
		
        part.onUpdate();
        part.setLocationAndAngles(this.posX + (double)(f11 * -FrontToBack) + (double)(f4 * SideToSide), this.posY + TopToBot, this.posZ + (double)(f4 * FrontToBack) + (double)(f11 * SideToSide), 0.0F, 0.0F);
        setOffsets(part, (double)(f11 * -FrontToBack) + (double)(f4 * SideToSide), TopToBot, (double)(f4 * FrontToBack) +  (double)(f11 * SideToSide));
	}
	
	
	public void setOffsets(MBEntityPart part, double xOff, double yOff, double zOff){
		part.PPosX = xOff;
		part.PPosY = yOff;
		part.PPosZ = zOff;
	}

	
	
	

	@Override
	public World getWorld() {
		return this.worldObj;
	}


	
	public boolean attackEntityFromPart(MBEntityPart Part, DamageSource Source, float DMGAmmount) {
	       if (Part == this.paragonPartLKnee || Part == this.paragonPartRKnee){
	    	   DMGAmmount = 0;
	    	   KneeHP--;
	    	}
	       
	       this.Damage(Source, DMGAmmount);   
		return true ;
	}
	

/**
 * Called when the entity is attacked.
 */
public boolean attackEntityFrom(DamageSource source, float amount)
{
    if (source instanceof EntityDamageSource && ((EntityDamageSource)source).getIsThornsDamage())
    {
        this.Damage(source, amount);
    }

    return false;
}

protected boolean Damage(DamageSource Source, float DMGAmmount)
{
    return super.attackEntityFrom(Source, DMGAmmount);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
    /**
     * Return the Entity parts making up this Entity (currently only for dragons)
     */
    public Entity[] getParts()
    {
        return this.paragonPartArray;
    }

}
