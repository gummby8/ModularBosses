package com.Splosions.ModularBosses.entity;

import com.Splosions.ModularBosses.blocks.ModFluids;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class EntityTick extends EntityMob {


	private static final DataParameter<Integer> GROW_WATCHER = EntityDataManager.<Integer>createKey(EntityTick.class, DataSerializers.VARINT);
	
	public double startMaxHp;
	public int growth;
	private int deathTicks;
	private boolean fullgrown;
	
	public static int tickMaxHealth;
	public static int tickDmg;
	
	public static int tickExpDrop;
	public static String[] tickLoot = new String[]{"100|1|mb:itemNote","1|1|mb:itemNote"};

	public EntityTick(World worldIn) {
		super(worldIn);
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIWander(this, 0.25D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(tickMaxHealth);
		this.startMaxHp = this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
		// Follow Range - default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(tickDmg);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(GROW_WATCHER, 0);
	}

	public static void postInitConfig(Configuration config) {
		tickMaxHealth = config.get("209 Tick", "1 [Max Health] Set the Hp of Tick Spawns [1+]", 40).getInt();
		tickDmg = config.get("209 Tick", "2 [Attack Damage] Set the damage of Tick Spawns [1+]", 10).getInt();
		
		tickExpDrop = config.get("209 Tick", "3 [Attribute] Set Exp drop of Tick Spawns [1+]", 100).getInt();
		tickLoot = config.getStringList("4 [Loot]", "209 Tick", tickLoot, "Set loot drops for Tick {% Drop Chance|Quantity|Item Name}");
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.growth = this.dataManager.get(GROW_WATCHER);

		if (!this.world.isRemote) {
			if (this.world.getBlockState(this.getPosition()).getBlock() == ModFluids.FLUID_WORM_BLOOD.getBlock() || this.world.getBlockState(this.getPosition()).getBlock() == ModFluids.FLUID_TEMP_WORM_BLOOD.getBlock() ) {
				this.grow();
			}
		}

	}

	// stuns the mob
	public boolean isMovementBlocked() {
		if (this.deathTicks > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onDeathUpdate() {

		if (this.growth == 5 && !this.world.isRemote && !this.fullgrown) {
			this.world.setBlockState(this.getPosition(), ModFluids.FLUID_TEMP_WORM_BLOOD.getBlock().getDefaultState());
			this.fullgrown = true;
		}
		++this.deathTicks;

		if (this.deathTicks % 5 == (5 - 1)) {
			this.growth--;
			if (this.growth < -1) {
				this.growth = -1;
			}
			this.dataManager.set(GROW_WATCHER, this.growth);
		}

		if (this.deathTicks > 30) {
			for (int i = 0; i < 20; ++i) {
				double d2 = this.rand.nextGaussian() * 0.02D;
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0, d1, new int[0]);
			}
			
			if (!this.world.isRemote) {
				TargetUtils.dropExp(this, this.tickExpDrop);
				TargetUtils.dropLoot(this, this.tickLoot);
			}
			
			this.setDead();
		}

	}

	public void grow() {
		this.growth++;
		if (this.growth < 6 && this.deathTicks == 0) {
			this.dataManager.set(GROW_WATCHER, this.growth);
			double maxHP = this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue() + (this.startMaxHp * 0.2D);
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHP);
			this.heal((float) this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue());
		}
		this.growth = (this.growth > 5) ? 5 : growth;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		int i = 0;

		if (entity instanceof EntityLivingBase) {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entity).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
		}

		boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (flag) {
			if (i > 0) {
				entity.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}
			grow();

		}

		return flag;
	}

}
