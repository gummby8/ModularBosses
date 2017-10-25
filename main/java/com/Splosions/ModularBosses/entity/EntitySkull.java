package com.Splosions.ModularBosses.entity;

import java.util.Random;

import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySkull extends EntityFlying implements IMob {

	public EntityPlayer target;
	public int countdown;

	public static int skullMaxHealth;
	public static int skullDmg;
	public static int skullFollowDistance;

	public static int skullExpDrop;
	public static String[] skullLoot = new String[] { "100|1|mb:itemNote", "1|1|mb:itemNote" };

	public double ranX;
	public double ranY;
	public double ranZ;

	public EntitySkull(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 0.5F);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(skullFollowDistance);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(skullMaxHealth);
	}

	public static void postInitConfig(Configuration config) {
		skullMaxHealth = config.get("210 Skull", "1 [Max Health] Set the Hp of Skull Spawns [1+]", 20).getInt();
		skullDmg = config.get("210 Skull", "2 [Attack Damage] Set the Beam Damage of Skull Spawns [1+]", 10).getInt();
		skullFollowDistance = config.get("210 Skull", "3 [Config] Set the distance a Skull can follow a player [1+]", 20).getInt();

		skullExpDrop = config.get("210 Skull", "4 [Attribute] Set Exp drop of skull Spawns [1+]", 100).getInt();
		skullLoot = config.getStringList("5 [Loot]", "210 Skull", skullLoot, "Set loot drops for skull {% Drop Chance|Quantity|Item Name}");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		player.attackEntityFrom(DamageSource.causeMobDamage(this), skullDmg);
		this.target = null;
		countdown = 40;
	}

	public void onUpdate() {
		super.onUpdate();
		if (this.ticksExisted == 1) {
			this.ranX = this.posX;
			this.ranY = this.posY;
			this.ranZ = this.posZ;
		}

		if (!this.worldObj.isRemote) {
			if (target == null && countdown <= 0) {
				target = TargetUtils.findRandomVisablePlayer(this, 15, 15);
			}
			
			countdown--;	
			if (target == null){
				if (this.getDistance(ranX, ranY, ranZ) < 2) {
					int count = 0;
					ranPos();
					while (!TargetUtils.canPosBeSeen(this, ranX, ranY, ranZ) || TargetUtils.distToFloor(this, ranX, ranY, ranZ) > 6) {
						ranPos();
						count++;
						if (count > 20) {
							this.ranX = this.posX;
							this.ranY = this.posY;
							this.ranZ = this.posZ;
							break;
						}
					}
				}
				moveTo(ranX, ranY, ranZ, 0.1D);
			}

			if (target != null && target.getDistanceSqToEntity(this) < skullFollowDistance * skullFollowDistance && this.canEntityBeSeen(target)) {
				moveTo(target.posX, target.posY, target.posZ, 0.2D);
			} 
			

		}
	}
	
	
	public void moveTo(double tarX, double tarY, double tarZ, double speed) {
		double d0 = this.posX - tarX;
		double d2 = this.posZ - tarZ;
		double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
		float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;

		this.rotationYaw = f + 180;

		double e0 = tarX - this.posX;
		double e1 = tarY - this.posY + 1;
		double e2 = tarZ - this.posZ;
		double e3 = e0 * e0 + e1 * e1 + e2 * e2;

		this.motionX += e0 / e3 * speed;
		this.motionY += e1 / e3 * speed;
		this.motionZ += e2 / e3 * speed;
		if (this.target != null) {
			this.faceEntity(target, 20, 20);	
		}
		
	}

	protected void entityInit() {
		super.entityInit();
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound() {
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return Sounds.SKULL_HIT;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "mob.ghast.death";
	}
	
	

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		super.attackEntityFrom(source, amount);
		target = null;
		countdown = 40;
		return true;
	}

	@Override
	public void onDeathUpdate() {
		super.onDeathUpdate();
		if (!this.worldObj.isRemote && this.deathTime == 20) {
			TargetUtils.dropExp(this, this.skullExpDrop);
			TargetUtils.dropLoot(this, this.skullLoot);
		}
	}

	public void ranPos() {
		ranX = this.posX + TargetUtils.getRanNum(-10, 10);
		ranY = this.posY + TargetUtils.getRanNum(-10, 10);
		ranZ = this.posZ + TargetUtils.getRanNum(-10, 10);
	}

}