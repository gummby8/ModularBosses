package com.Splosions.ModularBosses.entity;

import com.Splosions.ModularBosses.entity.projectile.EntityBrainEnergy;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class EntityBrain extends EntityMob {

	private int deathTicks;
	public boolean shieldUp;
	public EntityPlayer target;
	public EntityPlayer prevTarget;

	public static int brainMaxHealth;
	public static int brainDmg;
	public static int brainAttackTImer;
	public static int sparkTimer;
	public static int sparkMax;
	public static int sparkMin;
	
	public static int brainExpDrop;
	public static String[] brainLoot = new String[]{"100|1|mb:itemNote","1|1|mb:itemNote"};
	
	
	public EntityBrain(World worldIn) {
		super(worldIn);
		//sets hitbox size
		this.setSize(4F, 3F);
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(brainMaxHealth);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	public static void postInitConfig(Configuration config) {
		brainMaxHealth = config.get("201 Brain", "1 [Max Health] Set max Hp[1+]", 200).getInt();
		brainDmg = config.get("201 Brain", "2 [Attack Damage] Set the damage [1+]", 10).getInt();
		brainAttackTImer = config.get("201 Brain", "3 [Attack Times] Set the attack interval[1+]", 5).getInt() * 20;
		sparkTimer = config.get("201 Brain", "4 [Spark Spawn TIme] Set the spawn interval of Spark waves [1+]", 30).getInt() * 20;
		sparkMax = config.get("201 Brain", "5 [Spark Wave Count Max] Set the maximum spawn count for Spark waves [1+]", 6).getInt();
		sparkMin = config.get("201 Brain", "6 [Spark Wave Count Min] Set the minimum spawn count for Spark waves [1+]", 3).getInt();
		brainExpDrop = config.get("201 Brain", "7 [Attribute] Set Exp drop of Brain Spawns [1+]", 100).getInt();
		brainLoot = config.getStringList("8 [Loot]", "201 Brain", brainLoot, "Set loot drops for Brain {% Drop Chance|Quantity|Item Name}");
	}

	@Override
	public void onUpdate() {
		super.onUpdate();



		if (this.ticksExisted % sparkTimer == (20 - 1) && !this.world.isRemote && target != null) {
			makeSparks();
		} 
		
		if (this.ticksExisted % 20 == (20 - 1)) {
			target = TargetUtils.findRandomVisablePlayer(this, 15, 5);
			shieldUp = (sparkCheck()) ? false : true;
			//Generates sparks the moment a player walks in the room
			if (prevTarget == null && target != null && !this.world.isRemote){
				makeSparks();
				prevTarget = target;
			}
		}
		
		if (this.ticksExisted % brainAttackTImer == (20 - 1) && !this.world.isRemote) {
			int shots = TargetUtils.getRanNum(12, 20);
			float direction = 360 / shots;
			for (int i = 0; i < shots; ++i) {
				EntityBrainEnergy energy = new EntityBrainEnergy(this.world, this.posX,this.posY,this.posZ, (i * direction), brainDmg);
				this.world.spawnEntity(energy);
			}
		}
	}

	
	public void makeSparks(){
		int count = TargetUtils.getRanNum(sparkMin, sparkMax);
		for (int i = 1; i < count; ++i) {
			EntitySpark spark = new EntitySpark(world);
			spark.setPosition(this.posX + TargetUtils.getRanNum(-5, 5), this.posY, this.posZ + TargetUtils.getRanNum(-5, 5));
			this.world.spawnEntity(spark);	
		}
	}
	
	
	@Override
	public void onDeathUpdate() {
		++this.deathTicks;

		if (this.deathTicks > 20) {
			int i;
			
			for (i = 0; i < 20; ++i) {
				double d2 = this.rand.nextGaussian() * 0.02D;
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL,
						this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width,
						this.posY + (double) (this.rand.nextFloat() * this.height),
						this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0,
						d1, new int[0]);
			}
			
			if (!this.world.isRemote){
				TargetUtils.dropExp(this, this.brainExpDrop);
				TargetUtils.dropLoot(this, this.brainLoot);	
			}
			this.setDead();
		}

	}

	public boolean sparkCheck() {
		return TargetUtils.getSpecificList(this, EntitySpark.class, 10, 5).isEmpty();

	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (shieldUp) {
			return false;
		}
		return super.attackEntityFrom(source, amount);
	}

}
