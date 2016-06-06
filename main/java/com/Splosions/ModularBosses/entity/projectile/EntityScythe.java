package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import com.Splosions.ModularBosses.entity.EntityTatters;
import com.Splosions.ModularBosses.items.ModularBossesItems;
import com.Splosions.ModularBosses.util.TargetUtils;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityScythe extends EntityMobThrowable {

	protected static final int THROWN = 21;
	protected static final int SHOOTER_INDEX = 22;
	protected static final int SCALE = 23;
	protected static final int SPIN = 24;
	public int spin = 40;
	public EntityLivingBase Shooter;
	public ItemStack item = new ItemStack(ModularBossesItems.itemScythe);
	public int thrown = 1;
	public int throwTime = 0;
	private float Dmg;

	private double PerfectMotionX;
	private double PerfectMotionY;
	private double PerfectMotionZ;
	private double perfRotationYaw;
	public int slot;

	public EntityScythe(World world) {
		super(world);
		this.setSize(1, 2);

	}

	public EntityScythe(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityScythe(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityScythe(World world, EntityLivingBase shooter, float wobble, float FrontToBack, float YOffset, float SideToSide) {
		super(world, shooter, wobble, FrontToBack, YOffset, SideToSide);
	}

	public EntityScythe(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float Size1, float Size2, int spin, int dmg) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);
		setShooter(shooter);
		this.Shooter = (EntityLivingBase) getShooter();
		setSpin(spin);
		this.spin = getSpin();
		this.setPositionAndRotation(this.Shooter.posX, this.Shooter.posY + YOffset, this.Shooter.posZ, this.Shooter.rotationYaw, 0);
		this.Dmg = dmg;
	}

	@Override
	public void entityInit() {
		super.entityInit();
		dataWatcher.addObject(THROWN, 1);
		dataWatcher.addObject(SHOOTER_INDEX, -1);
		dataWatcher.addObject(SCALE, 1);
		dataWatcher.addObject(SPIN, 0);

	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	public void setThrown(int thrown) {
		if (!this.worldObj.isRemote) {
			dataWatcher.updateObject(THROWN, thrown);
		}
		this.thrown = dataWatcher.getWatchableObjectInt(THROWN);
	}

	public int getScale() {

		return dataWatcher.getWatchableObjectInt(SCALE);
	}

	public void setScale(int scale) {
		dataWatcher.updateObject(SCALE, scale);
	}

	public int getSpin() {
		return dataWatcher.getWatchableObjectInt(SPIN);
	}

	public void setSpin(int spin) {
		dataWatcher.updateObject(SPIN, spin);
	}

	public Entity getShooter() {
		int id = dataWatcher.getWatchableObjectInt(SHOOTER_INDEX);
		return (id == -1 ? null : worldObj.getEntityByID(id));
	}

	public void setShooter(Entity entity) {
		dataWatcher.updateObject(SHOOTER_INDEX, entity != null ? entity.getEntityId() : -1);
	}

	// set to 0 do have 0 drop
	@Override
	protected float getGravityVelocity() {
		return 0F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		this.thrown = dataWatcher.getWatchableObjectInt(THROWN);
		this.spin = getSpin();
		this.Shooter = (EntityLivingBase) getShooter();
		this.noClip = true;

		if (this.Shooter != null) {
			if (this.ticksExisted < 10 && this.thrown == 1) {
				this.setPositionAndRotation(this.posX, this.posY, this.posZ, this.Shooter.rotationYaw, 0);
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
				moveForward(1F);
			}

			this.posY = this.Shooter.posY + 0.5F;

			rotate();
			if (this.thrown == 1) {
				this.throwTime++;
				moveForward(0.1F);
			} else {
				this.throwTime = 0;
			}

			attackEntitiesInList(this.worldObj.getEntitiesWithinAABB(Entity.class, this.getEntityBoundingBox()));
			catchScythe(this.worldObj.getEntitiesWithinAABB(Entity.class, this.getEntityBoundingBox().expand(1, 1, 1)));

		} else {
			setDead();
		}

		/**
		 * double d0 = this.Shooter.posX - this.posX; double d1 =
		 * this.Shooter.posY + 1 - this.posY; double d2 = this.Shooter.posZ -
		 * this.posZ;
		 * 
		 * SetThrowableHeading(d0, d1, d2, 0.4F, 1.0F);
		 * 
		 * this.motionX = MovePart(this.motionX, this.PerfectMotionX, 0.04);
		 * this.motionY = MovePart(this.motionY, this.PerfectMotionY, 0.005);
		 * this.motionZ = MovePart(this.motionZ, this.PerfectMotionZ, 0.04);
		 */

		if (this.ticksExisted > 600 && !this.worldObj.isRemote && this.Shooter != null && !(this.Shooter instanceof EntityTatters)) {
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, item));
			setDead();
		}

	}

	public void rotate() {
		if (this.Shooter != null) {
			if (this.thrown == 1) {
				double x = this.posX - this.Shooter.posX;
				double z = this.posZ - this.Shooter.posZ;
				double p = x / z;
				float angle = (float) Math.toDegrees(Math.atan(p));

				if (x <= 0 && z <= 0) {
					this.rotationYaw = -angle;
					// System.out.println("-x -z = " + this.rotationYaw);
				} else if (x >= 0 && z >= 0) {
					this.rotationYaw = -(angle - 180);
					// System.out.println("+x +z = " + this.rotationYaw);
				} else if (x >= 0 && z <= 0) {
					this.rotationYaw = -angle;
					// System.out.println("+x -z = " + this.rotationYaw);
				} else if (x <= 0 && z >= 0) {
					this.rotationYaw = -(angle + 180);
					// System.out.println("-x +z = " + this.rotationYaw);
				}
			} else {
				this.rotationYaw = this.Shooter.rotationYaw;
			}
		}
	}

	public void moveForward(float speed) {

		float f2 = MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F);
		float f3 = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F);
		this.motionX += (double) (-1 * speed * f2);
		this.motionZ += (double) (speed * f3);
	}

	/**
	 * Catches the Scythe
	 */
	public void catchScythe(List list) {
		for (int i = 0; i < list.size(); ++i) {
			Entity entity = (Entity) list.get(i);
			if (entity == this.Shooter && this.throwTime > 10) {
				if (entity instanceof EntityTatters) {
					setThrown(0);
					setSpin(20);
				} else if (entity instanceof EntityPlayer && !this.worldObj.isRemote) {
					EntityPlayer player = (EntityPlayer) entity;
					if (player.inventory.getStackInSlot(slot) == null) {
						player.inventory.setInventorySlotContents(slot, item);
					} else {
						int s = player.inventory.getFirstEmptyStack();
						if (s >= 0) {
							player.inventory.setInventorySlotContents(s, player.inventory.getStackInSlot(slot));
							player.inventory.setInventorySlotContents(slot, item);
						} else {
							TargetUtils.addItemToInventory(player, item);
						}
					}
					this.setDead();
				}
			}
		}
	}

	/**
	 * Attacks all entities inside this list, dealing 5 hearts of damage.
	 */
	private void attackEntitiesInList(List list) {

		for (int i = 0; i < list.size(); ++i) {
			Entity entity = (Entity) list.get(i);
			if (this.Shooter instanceof EntityPlayer) {
				if (entity instanceof IEntityMultiPart) {
					Entity[] entArray = entity.getParts();
					for (int k = 0; k < entArray.length; ++k) {
						EntityDragonPart part = (EntityDragonPart) entArray[k];
						if (part.getEntityBoundingBox().intersectsWith(this.getEntityBoundingBox())) {
							System.out.println(part.partName);
							part.attackEntityFrom(DamageSource.causeMobDamage(this.Shooter), this.Dmg);
						}
					}
				} else if (entity != this.Shooter && entity != this) {
					entity.attackEntityFrom(DamageSource.causeMobDamage(this.Shooter), this.Dmg);
					// System.out.println(entity);
				}
			} else {
				if (this.Shooter instanceof EntityTatters && entity instanceof EntityPlayer) {
					entity.attackEntityFrom(DamageSource.causeMobDamage(this.Shooter), this.Dmg);
				}
			}
		}

	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z
	 * direction.
	 */
	public void SetThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= (double) f2;
		par3 /= (double) f2;
		par5 /= (double) f2;
		par1 += this.rand.nextGaussian() * 0.007499999832361937D * (double) par8;
		par3 += this.rand.nextGaussian() * 0.007499999832361937D * (double) par8;
		par5 += this.rand.nextGaussian() * 0.007499999832361937D * (double) par8;
		par1 *= (double) par7;
		par3 *= (double) par7;
		par5 *= (double) par7;
		this.PerfectMotionX = par1;
		this.PerfectMotionY = par3;
		this.PerfectMotionZ = par5;

	}

	public double MovePart(double part, double destination, double speed) {
		if (part < destination) {
			part += speed;
		} else if (part > destination + speed) {
			part -= speed;
		} else {
			part = destination;
		}
		return part;
	}

	/** Sets the scythe's original itemstack and inventory slot index */
	public EntityScythe setInvStack(int slot) {
		this.slot = slot;
		return this;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		try {
			if (!(mop.entityHit instanceof Entity) && !(this.worldObj.getBlockState(mop.getBlockPos()) instanceof BlockAir)) {
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
				rotate();
				moveForward(0.1F);

			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
