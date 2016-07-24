package com.Splosions.ModularBosses.entity;

import com.google.common.base.Predicate;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityEyeballOctopus extends EntityMob implements IRangedAttackMob {

	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.3D, 20, 60, 8.0F);
	public int innerRotation;

	public EntityLivingBase target;
	public int attackCounter;

	private static final int DEATH_WATCHER = 16;
	private static final int TARGET_ID_WATCHER = 17;
	private static final int ATTACK_WATCHER = 18;

	public EntityEyeballOctopus(World worldIn) {
		super(worldIn);

		this.innerRotation = this.rand.nextInt(100000);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, new Predicate() {
			private static final String __OBFID = "CL_00002203";

			public boolean func_179945_a(Entity p_179945_1_) {
				return p_179945_1_ instanceof EntityWolf;
			}

			public boolean apply(Object p_apply_1_) {
				return this.func_179945_a((Entity) p_apply_1_);
			}
		}, 6.0F, 1.0D, 1.2D));
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
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DEATH_WATCHER, 0);
		this.dataWatcher.addObject(TARGET_ID_WATCHER, 0);
		this.dataWatcher.addObject(ATTACK_WATCHER, 0);
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float p_82196_2_) {
		this.attackCounter = 10;
		this.target = entity;
		this.dataWatcher.updateObject(TARGET_ID_WATCHER, this.target.getEntityId());
		this.dataWatcher.updateObject(ATTACK_WATCHER, this.attackCounter);
		System.out.println("FIRINGGGGGGGGGG");
		EntityArrow entityarrow = new EntityArrow(this.worldObj, this, entity, 1.6F, (float) (14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
		entityarrow.setDamage((double) (p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double) ((float) this.worldObj.getDifficulty().getDifficultyId() * 0.11F));

		if (i > 0) {
			entityarrow.setDamage(entityarrow.getDamage() + (double) i * 0.5D + 0.5D);
		}

		if (j > 0) {
			entityarrow.setKnockbackStrength(j);
		}

		// this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat()
		// * 0.4F + 0.8F));
		// this.worldObj.spawnEntityInWorld(entityarrow);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.attackCounter = this.dataWatcher.getWatchableObjectInt(ATTACK_WATCHER);
		this.attackCounter--;
		this.ignoreFrustumCheck = true;

		if (this.attackCounter > 0) {

			this.target = (EntityLivingBase) this.worldObj.getEntityByID(this.dataWatcher.getWatchableObjectInt(TARGET_ID_WATCHER));
		}

		this.dataWatcher.updateObject(ATTACK_WATCHER, this.attackCounter);
	}

}
