package com.Splosions.ModularBosses.entity;

import java.util.List;

import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.blocks.FluidWormBlood;
import com.Splosions.ModularBosses.blocks.ModBlocks;
import com.Splosions.ModularBosses.blocks.ModFluids;
import com.Splosions.ModularBosses.entity.projectile.EntityBloodBlob;
import com.Splosions.ModularBosses.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.util.TargetUtils;
import com.google.common.base.Predicate;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraft.entity.player.EntityPlayer;

public class EntityHeart extends EntityMob {

	private int deathTicks;
	public static int heartMaxHealth;
	public static int heartDmg;
	public int invulnerable;

	
	public EntityHeart(World worldIn) {
		super(worldIn);
		//sets hitbox size
		this.setSize(3F, 4F);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.3D, true));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

	}
	


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	public static void postInitConfig(Configuration config) {
		heartMaxHealth = config.get("heart", "[Max Health] Set the Hp of heart Spawns [1+]", 20).getInt();
		heartDmg = config.get("heart", "[Attack Damage] Set the damage of heart Spawns [1+]", 10).getInt();
	}

	public boolean pump;
	public boolean prevPump;
	@Override
	public void onUpdate() {
		super.onUpdate();
		invulnerable--;
		
    	float hp = 10 - (this.getMaxHealth() / this.getHealth());
    	hp = (hp <= 1)?1:hp;
    	
    	if (this.invulnerable > 0){
    		hp = 1;
    	}
    	if (MathHelper.sin(this.ticksExisted/hp ) > 0.9F ){
    		
    		pump = true;
    	} else if (MathHelper.sin(this.ticksExisted/hp ) < -0.9F ){
    		pump = false;
    	}
    	
    	if (pump != prevPump){
    		if (pump){
    			//System.out.println("UP");
    			this.playSound(Sounds.HEART_UP, 10F, 1.0F);
    		}else{
    			this.playSound(Sounds.HEART_DOWN, 10F, 1.0F);
    			//System.out.println("DOWN");
    		}
    		prevPump = pump;
    	}
		
		//spew Blood
		if (this.ticksExisted % 10 == (10 - 1) && !this.worldObj.isRemote && invulnerable > 0) {
			int xOff = TargetUtils.getRanNum(-5, 5);
			int zOff = TargetUtils.getRanNum(-5, 5);
			BlockPos pos = new BlockPos(this.posX + xOff, this.posY, this.posZ + zOff);
			EntityBloodBlob projectile = new EntityBloodBlob(this.worldObj, this, pos, (float)(Math.random() - 0.2F), 0.3F);
			worldObj.spawnEntityInWorld(projectile);

		}

	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (invulnerable <= 0) {
			this.Damage(source, amount);
			invulnerable = 100;
		}

		return false;
	}

	protected boolean Damage(DamageSource Source, float DMGAmmount) {
		return super.attackEntityFrom(Source, DMGAmmount);
	}
	

	@Override
	public void onDeathUpdate() {
		++this.deathTicks;

		if (this.deathTicks > 30) {
			int i;

			this.setDead();

			for (i = 0; i < 20; ++i) {
				double d2 = this.rand.nextGaussian() * 0.02D;
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL,
						this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width,
						this.posY + (double) (this.rand.nextFloat() * this.height),
						this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0,
						d1, new int[0]);
			}
		}

	}

}
