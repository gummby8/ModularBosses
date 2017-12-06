package com.Splosions.ModularBosses.entity;

import com.Splosions.ModularBosses.util.TargetUtils;
import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class EntityEyeballOctopus extends EntityMob implements IRangedAttackMob {

    private final EntityAIAttackRangedBow<EntityEyeballOctopus> aiArrowAttack = new EntityAIAttackRangedBow<EntityEyeballOctopus>(this, 1.0D, 20, 15.0F);
	
	
	public int innerRotation;

	public EntityLivingBase target;
	public int attackCounter;

	public static int eyeballOctopusMaxHealth;
	public static int eyeballOctopusDmg;
	public static int attackInterval;
	
	public static int eyeballOctopusExpDrop;
	public static String[] eyeballOctopusLoot = new String[]{"100|1|mb:itemNote","1|1|mb:itemNote"};
	


	private static final DataParameter<Integer> TARGET_ID_WATCHER = EntityDataManager.<Integer>createKey(EntityEyeballOctopus.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> ATTACK_WATCHER = EntityDataManager.<Integer>createKey(EntityEyeballOctopus.class, DataSerializers.VARINT);


	public EntityEyeballOctopus(World worldIn) {
		super(worldIn);

		this.innerRotation = this.rand.nextInt(100000);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIWander(this, 0.2D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));

		if (worldIn != null && !worldIn.isRemote) {
			this.tasks.addTask(4, this.aiArrowAttack);
		}
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		// Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(eyeballOctopusMaxHealth);
		// Follow Range - default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20);
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.dataManager.register(TARGET_ID_WATCHER, 0);
		this.dataManager.register(ATTACK_WATCHER, 0);
	}

	
	public static void postInitConfig(Configuration config) {
		eyeballOctopusMaxHealth = config.get("209 Eyeball Octopus", "1 [Max Health] Set the Hp of Eyeball Octopus Spawns [1+]", 20).getInt();
		eyeballOctopusDmg = config.get("209 Eyeball Octopus", "2 [Attack Damage] Set the Beam Damage of Eyeball Octopus Spawns [1+]", 10).getInt();
		attackInterval = config.get("209 Eyeball Octopus", "3 [Attack Interval] Set the Beam Interval timer [1+]", 1).getInt() * 20;
		
		eyeballOctopusExpDrop = config.get("209 Eyeball Octopus", "4 [Attribute] Set Exp drop of Eyeball Octopus Spawns [1+]", 100).getInt();
		eyeballOctopusLoot = config.getStringList("5 [Loot]", "209 Eyeball Octopus", eyeballOctopusLoot, "Set loot drops for Eyeball Octopus {% Drop Chance|Quantity|Item Name}");
	}
	
	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float p_82196_2_) {
		this.attackCounter += attackInterval;
		this.target = entity;
		this.dataManager.set(TARGET_ID_WATCHER, this.target.getEntityId());
		this.dataManager.set(ATTACK_WATCHER, this.attackCounter);


		this.playSound(SoundEvents.ENTITY_ENDERDRAGON_HURT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.target.attackEntityFrom(DamageSource.causeMobDamage(this), eyeballOctopusDmg);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.attackCounter = this.dataManager.get(ATTACK_WATCHER);
		this.attackCounter--;
		this.ignoreFrustumCheck = true;

		if (this.attackCounter > 0) {

			this.target = (EntityLivingBase) this.world.getEntityByID(this.dataManager.get(TARGET_ID_WATCHER));
		}

		this.dataManager.set(ATTACK_WATCHER, this.attackCounter);
	}
	
	@Override
	public void onDeathUpdate(){
		super.onDeathUpdate();
		if (!this.world.isRemote && this.deathTime == 20) {
			TargetUtils.dropExp(this, this.eyeballOctopusExpDrop);
			TargetUtils.dropLoot(this, this.eyeballOctopusLoot);	
		}
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
		// TODO Auto-generated method stub
		
	}

}
