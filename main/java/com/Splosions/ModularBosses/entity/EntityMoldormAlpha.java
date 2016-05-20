package com.Splosions.ModularBosses.entity;



import java.util.Random;

import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.client.models.FakeModelRenderer;

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
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EntityMoldormAlpha extends EntityMob
{


	Random rand = new Random();

	public FakeModelRenderer part2 = new FakeModelRenderer();
	public FakeModelRenderer part3 = new FakeModelRenderer();
	public FakeModelRenderer part4 = new FakeModelRenderer();
	public FakeModelRenderer part5 = new FakeModelRenderer();
	
	public int ranPosX = 0;
	public int ranPosZ = 0;
	public int flip;
	public int ranTicks = 20;


	public EntityMoldormAlpha(World par1World) {
		super(par1World);
		//sets hitbox size
		this.setSize(3F, 3F);
		this.experienceValue = 10;
		this.isImmuneToFire = true;
		this.ignoreFrustumCheck = true;
		this.maxHurtResistantTime = 40;
		//AI STUFF
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.25D, false)); //How fast mob moves towards the player

		

	}

	
	//won't despawn even if the chunk unloads
	protected boolean canDespawn()
	{
		return false;
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
		return false;
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



	/**
	 * Set mob death animations, just be sure to setDead at the end or the model wont go away 
	 */
	protected void onDeathUpdate() {
			this.setDead();   	
	}





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

	//stuns the mob
    @Override
    public boolean isMovementBlocked() {
    	if (this.hurtResistantTime > 0){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    
	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
    
    
    
    
	public void onLivingUpdate() {
		if (this.ticksExisted % this.ranTicks== (20 - 1) && !this.worldObj.isRemote){
		
		this.ranTicks = getRandomNumberInRange(20, 40);
		this.ranPosX = getRandomNumberInRange(-20, 20);
		this.ranPosZ = getRandomNumberInRange(-20, 20);
		
		this.getNavigator().tryMoveToXYZ(this.posX + ranPosX, this.posY, this.posZ + ranPosZ, 0.70D);
		}
		
		if (this.isCollidedHorizontally && !this.worldObj.isRemote && this.flip == 0){
			this.ranPosX *= -1;
			this.ranPosZ *= -1;
			this.flip = 10;
		} 
		
		if (!this.worldObj.isRemote){
			this.flip -=(this.flip <= 0)? 0 : 1;
		}
		
		this.moveHelper.setMoveTo(this.posX + ranPosX, this.posY, this.posZ + ranPosZ, 0.70D);
		super.onLivingUpdate();
	}
	

}
