package com.Splosions.ModularBosses.entity;

import java.util.List;

import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;

public class EntitySpark extends EntityMob {

	private int deathTicks;
	public boolean shieldUp;

	public static int sparkMaxHealth;
	public static int sparkDmg;
	public static int sparkHeal;
	public static int sparkDark;
	public static int sparkAttackInterval;
	
	public static int sparkExpDrop;
	public static String[] sparkLoot = new String[]{};

	public int variant;
	public static final int ORANGE = 1;
	public static final int BLUE = 2;
	public static final int GREEN = 3;
	public static final int PURPLE = 4;

	private static final DataParameter<Integer> COLOR_WATCHER = EntityDataManager.<Integer>createKey(EntitySpark.class, DataSerializers.VARINT);
	
	public EntitySpark(World worldIn) {
		super(worldIn);
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.tasks.addTask(2, new EntityAISwimming(this));
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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(sparkMaxHealth);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1);
		if (!this.world.isRemote) {
			variant = TargetUtils.getRanNum(1, 4);
			this.dataManager.set(COLOR_WATCHER, variant);

		}
		variant = this.dataManager.get(COLOR_WATCHER);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(COLOR_WATCHER, 0);
	}

	public static void postInitConfig(Configuration config) {
		sparkMaxHealth = config.get("211 spark", "1 [Max Health] Set the Hp of spark Spawns [1+]", 20).getInt();
		sparkDmg = config.get("211 spark", "2 [Attack Damage] Set the damage of spark Spawns [1+]", 10).getInt();
		sparkHeal = config.get("211 spark", "3 [Heal] Set the heal ammount of Green Sparks [1+]", 20).getInt();
		sparkDark = config.get("211 spark", "4 [Status Durration] Set the darkness durration of Purple Sparks [1+]", 5).getInt() * 20;
		sparkAttackInterval = config.get("211 spark", "5 [Attack Interval] Set the attack timer of Sparks [1+]", 1).getInt() * 20;
		
		sparkExpDrop = config.get("211 Spark", "6 [Attribute] Set Exp drop of Spark Spawns [1+]", 100).getInt();
		sparkLoot = config.getStringList("7 [Loot]", "211 Spark", sparkLoot, "Set loot drops for Spark {% Drop Chance|Quantity|Item Name}");
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		variant = this.dataManager.get(COLOR_WATCHER);

		if (this.ticksExisted % sparkAttackInterval == (20 - 1)) {
			List list = TargetUtils.getList(this, 4, 4);
			if (this.variant == ORANGE && !this.world.isRemote) {
				for (int i = 0; i < list.size(); ++i) {
					Entity entity = (Entity) list.get(i);
					if (entity instanceof EntityPlayer && entity.hurtResistantTime == 0) {
						EntityPlayer player = (EntityPlayer) entity;
						if (!player.capabilities.isCreativeMode){
							entity.attackEntityFrom(DamageSource.causeMobDamage(this), sparkDmg);	
						}
						
					}
				}
			} else if (this.variant == BLUE && this.world.isRemote) {
				kickEntitiesInListIfInfront(list, 8, 1.5);
			} else if (this.variant == PURPLE && !this.world.isRemote) {
				for (int i = 0; i < list.size(); ++i) {
					Entity entity = (Entity) list.get(i);
					if (entity instanceof EntityPlayer && entity.hurtResistantTime == 0) {
						EntityPlayer player = (EntityPlayer) entity;
						if (!player.capabilities.isCreativeMode){
							player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, sparkDark, 1));	
						}
					}
				}
			} else if (this.variant == GREEN && !this.world.isRemote) {
				for (int i = 0; i < list.size(); ++i) {
					Entity entity = (Entity) list.get(i);
					if (entity instanceof EntityLiving && !(entity instanceof EntityPlayer)) {
						EntityLiving ent = (EntityLiving) entity;
						ent.heal(sparkHeal);
					}
				}
			}
		}

	}

	private void kickEntitiesInListIfInfront(List par1List, double force, double height) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity instanceof EntityPlayer && entity.hurtResistantTime == 0) {
				EntityPlayer player = (EntityPlayer) entity;
				if (!player.capabilities.isCreativeMode){
					double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
					double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
					double d2 = entity.posX - d0;
					double d3 = entity.posZ - d1;
					double d4 = d2 * d2 + d3 * d3;
					entity.addVelocity(d2 / d4 * force, height, d3 / d4 * force);	
				}
			}

		}
	}

	@Override
	public void onDeathUpdate() {
			for (int i = 0; i < 20; ++i) {
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
				TargetUtils.dropExp(this, this.sparkExpDrop);
				TargetUtils.dropLoot(this, this.sparkLoot);	
			}
			
			this.setDead();

	}

}
