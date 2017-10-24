package com.Splosions.ModularBosses.entity;



import java.util.List;
import java.util.Random;

import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.client.models.FakeModelRenderer;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;


public class EntityMoldorm extends EntityMob implements IEntityMultiPart, IMob 
{
	public EntityDragonPart[] moldormPartArray;
	public EntityDragonPart moldormPart1;
	public EntityDragonPart moldormPart2;
	public EntityDragonPart moldormPart3;
	public EntityDragonPart moldormPart4;
	public EntityDragonPart moldormPart5;

	public boolean debugHitboxes = false;
	
	Random rand = new Random();

	public FakeModelRenderer part1 = new FakeModelRenderer();
	public FakeModelRenderer part2 = new FakeModelRenderer();
	public FakeModelRenderer part3 = new FakeModelRenderer();
	public FakeModelRenderer part4 = new FakeModelRenderer();
	public FakeModelRenderer part5 = new FakeModelRenderer();
	
	public int ranPosX = 0;
	public int ranPosZ = 0;
	public int flip;
	public int ranTicks = 20;

	public static int moldormAttack;
	public static int moldormMaxHealth;
	public static int moldormExpDrop;
	public static String[] moldormLoot = new String[] { "100|1|mb:itemNote", "1|1|mb:itemNote" };

	public EntityMoldorm(World par1World) {
		super(par1World);
		//sets hitbox size
		this.setSize(3F, 3F);
		this.experienceValue = 10;
		this.isImmuneToFire = true;
		this.maxHurtResistantTime = 40;
		//AI STUFF
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.25D, false)); //How fast mob moves towards the player
		this.moldormPartArray = new EntityDragonPart[] { 
				this.moldormPart1 = new EntityDragonPart(this, "part1", 3.0F, 3.0F), 
				this.moldormPart2 = new EntityDragonPart(this, "part2", 3.0F, 3.0F), 
				this.moldormPart3 = new EntityDragonPart(this, "part3", 2.5F, 2.5F),
				this.moldormPart4 = new EntityDragonPart(this, "part4", 2.0F, 2.0F),
				this.moldormPart5 = new EntityDragonPart(this, "part5", 1.7F, 1.7F) 
			};
		

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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(moldormMaxHealth);
		// Follow Range - default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(moldormAttack);
	}



	/**
	 * Set mob death animations, just be sure to setDead at the end or the model wont go away 
	 */
	protected void onDeathUpdate() {
		this.deathTime++;

		for (int i = 0; i < 5; ++i) {
			float f = (this.rand.nextFloat() - 0.5F) * 5.5F;
			float f1 = (this.rand.nextFloat() - 0.5F) * 5.5F;
			float f2 = (this.rand.nextFloat() - 0.5F) * 5.5F;
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (double) f,
					this.posY + 2.0D + (double) f1, this.posZ + (double) f2, 0.0D, 0.0D, 0.0D);
		}

		if (!this.worldObj.isRemote && this.deathTime == 20) {
			TargetUtils.dropExp(this, this.moldormExpDrop);
			TargetUtils.dropLoot(this, this.moldormLoot);
		}
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
    	if (this.hurtResistantTime > 0 || this.getHealth() <= 0){
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
		super.onLivingUpdate();
		TargetUtils.betaMsg(this);
		
		this.ignoreFrustumCheck = true;
		

		
		if ( !this.worldObj.isRemote && this.ticksExisted % this.ranTicks == (20 - 1) && flip == 0) {
			this.ranTicks = getRandomNumberInRange(20, 40);
			this.ranPosX = getRandomNumberInRange(-20, 20);
			this.ranPosZ = getRandomNumberInRange(-20, 20);
		}
		

		
		if (!this.worldObj.isRemote) {
			if (edgeDetect() || isCollidedHorizontally) {
				while (isAir()){}
				this.flip = 10; 
				this.motionX = 0;
				this.motionZ = 0;
			}
		}

		


		if (!this.worldObj.isRemote){
			this.flip -=(this.flip <= 0)? 0 : 1;
		}
		
		
		this.moveHelper.setMoveTo(this.posX + ranPosX, this.posY, this.posZ + ranPosZ, 0.70D);
		setHitBoxes();
		
		
		this.kickEntitiesInList(TargetUtils.getList(this.moldormPart1, 0, 0), 3, 1, 5);
		this.kickEntitiesInList(TargetUtils.getList(this.moldormPart2, 0, 0), 3, 1, 5);
		this.kickEntitiesInList(TargetUtils.getList(this.moldormPart3, 0, 0), 3, 1, 5);
		this.kickEntitiesInList(TargetUtils.getList(this.moldormPart4, 0, 0), 3, 1, 5);
		
	}

	public boolean edgeDetect(){
		Vec3 look = this.getLookVec();
		float distance = 1.5F; //distance in front of entity
		double dx = this.posX + (look.xCoord * distance);
		double dy = this.posY; 
		double dz = this.posZ + (look.zCoord * distance);
		BlockPos bp = new BlockPos(dx, dy - 1, dz);
		Block block = this.worldObj.getBlockState(bp).getBlock();
		return (block instanceof BlockAir);
	}
	
	public Boolean isAir(){
		this.ranPosX = getRandomNumberInRange(-20, 20);
		this.ranPosZ = getRandomNumberInRange(-20, 20);
		BlockPos pos = new BlockPos(this.posX + ranPosX, this.posY - 1, this.posZ + ranPosZ);
		if (this.worldObj.getBlockState(pos).getBlock() instanceof BlockAir){
			return true;	
		} else {
			return false;
		}
		
	}
	

	
	@Override
	public World getWorld() {
		return this.worldObj;
	}


	@Override
	public boolean attackEntityFromPart(EntityDragonPart part, DamageSource source, float dmg) {
		if (part == this.moldormPart5){
			Damage(source , dmg);
			return true;	
		} else {
			dmg = 0;
			return false;
		}
	}
	
	
	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source instanceof EntityDamageSource && ((EntityDamageSource) source).getIsThornsDamage()) {
			this.Damage(source, amount);
		}
		return true;
	}
	
	protected boolean Damage(DamageSource Source, float DMGAmmount) {
		return super.attackEntityFrom(Source, DMGAmmount);
	}
	

	private void kickEntitiesInList(List par1List, double force, double height, float Damage) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity instanceof EntityPlayer && entity.hurtResistantTime == 0) {
				double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
				double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
				double d2 = entity.posX - d0;
				double d3 = entity.posZ - d1;
				double d4 = d2 * d2 + d3 * d3;
				entity.addVelocity(d2 / d4 * force, height, d3 / d4 * force);
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), moldormAttack);
			}
		}
	}
	
	/**
	 * Return the Entity parts making up this Entity 
	 */
	@Override
	public Entity[] getParts() {
		return this.moldormPartArray;
	}
	
	
	private void setHitBoxes() {
		
		if(this.ticksExisted == 1){
			this.moldormPart2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
			this.moldormPart3.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
			this.moldormPart4.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
			this.moldormPart5.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);

		}

		
		this.moldormPart1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
    	movePiecePos(this.moldormPart2, this.moldormPart1, 6, 4);
    	movePiecePos(this.moldormPart3, this.moldormPart2, 6, 4);
    	movePiecePos(this.moldormPart4, this.moldormPart3, 7, 3);
    	movePiecePos(this.moldormPart5, this.moldormPart4, 7, 3);
	}
	
    
    
	public static void movePiecePos(EntityDragonPart targetPart, EntityDragonPart destinationPart, float speed, float yawSpeed){
		targetPart.posX += ((destinationPart.posX - targetPart.posX) / speed);
		targetPart.posY += ((destinationPart.posY - targetPart.posY) / speed);
		targetPart.posZ += ((destinationPart.posZ - targetPart.posZ) / speed);
		targetPart.rotationYaw += ((destinationPart.rotationYaw - targetPart.rotationYaw) / yawSpeed);
		
		targetPart.setLocationAndAngles(targetPart.posX, targetPart.posY, targetPart.posZ, targetPart.rotationYaw, 0);
		

		
	}

	


	public static void postInitConfig(Configuration config) {
		moldormMaxHealth = config.get("212 Moldorm", "1 [Max Health] Set the max HP [1+]", 200).getInt();
		moldormAttack = config.get("212 Moldorm", "2 [Attack] Set the ammount of damage Moldorm does to players  [1+]", 16).getInt() * 20;
		moldormExpDrop = config.get("212 Moldorm", "3 [Attribute] Set Exp drop of Moldorm Spawns [1+]", 100).getInt();
		moldormLoot = config.getStringList("4 [Loot]", "212 Moldorm", moldormLoot, "Set loot drops for Moldorm {% Drop Chance|Quantity|Item Name}");
		
	}
}
