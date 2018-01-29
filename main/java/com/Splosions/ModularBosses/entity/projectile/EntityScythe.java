package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import com.Splosions.ModularBosses.entity.EntitySandWormTail;
import com.Splosions.ModularBosses.entity.EntityTatters;
import com.Splosions.ModularBosses.items.ModularBossesItems;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityScythe extends EntityMobThrowable {

	private static final DataParameter<Integer> THROWN = EntityDataManager.<Integer>createKey(EntityScythe.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SHOOTER_INDEX = EntityDataManager.<Integer>createKey(EntityScythe.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SCALE = EntityDataManager.<Integer>createKey(EntityScythe.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SPIN = EntityDataManager.<Integer>createKey(EntityScythe.class, DataSerializers.VARINT);

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
		float pitch = (this.Shooter instanceof EntityPlayer)? this.Shooter.rotationPitch : 0;
		this.setPositionAndRotation(this.Shooter.posX, this.Shooter.posY + YOffset, this.Shooter.posZ, this.Shooter.rotationYaw, pitch);
		this.Dmg = dmg;
	}

	@Override
	public void entityInit() {
		super.entityInit();
		
		this.dataManager.register(THROWN, 1);
		this.dataManager.register(SHOOTER_INDEX, -1);
		this.dataManager.register(SCALE, 1);
		this.dataManager.register(SPIN, 0);

	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	public void setThrown(int thrown) {
		if (!this.world.isRemote) {
			this.dataManager.set(THROWN, thrown);
		}
		this.thrown = this.dataManager.get(THROWN);
	}

	public int getScale() {

		return this.dataManager.get(SCALE);
	}

	public void setScale(int scale) {
		this.dataManager.set(SCALE, scale);
	}

	public int getSpin() {
		return this.dataManager.get(SPIN);
	}

	public void setSpin(int spin) {
		this.dataManager.set(SPIN, spin);
	}

	public Entity getShooter() {
		int id = this.dataManager.get(SHOOTER_INDEX);
		return (id == -1 ? null : world.getEntityByID(id));
	}

	public void setShooter(Entity entity) {
		this.dataManager.set(SHOOTER_INDEX, entity != null ? entity.getEntityId() : -1);
	}

	// set to 0 do have 0 drop
	@Override
	protected float getGravityVelocity() {
		return 0F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		this.thrown = this.dataManager.get(THROWN);
		this.spin = getSpin();
		this.Shooter = (EntityLivingBase) getShooter();
		this.noClip = true;

		if (this.Shooter != null) {
			if (this.ticksExisted < 10 && this.thrown == 1) {
				float pitch = (this.Shooter instanceof EntityPlayer)? this.Shooter.rotationPitch : 0;
				this.setPositionAndRotation(this.posX, this.posY, this.posZ, this.Shooter.rotationYaw, pitch);
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

			attackEntitiesInList(this.world.getEntitiesWithinAABB(EntityLiving.class, this.getEntityBoundingBox()));
			catchScythe(this.world.getEntitiesWithinAABB(Entity.class, this.getEntityBoundingBox().expand(1, 1, 1)));

		} else {
			setDead();
		}


		if (this.ticksExisted > 600 && !this.world.isRemote && this.Shooter != null && !(this.Shooter instanceof EntityTatters)) {
			world.spawnEntity(new EntityItem(world, posX, posY, posZ, item));
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
				} else if (entity instanceof EntityPlayer && !this.world.isRemote) {
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
						MultiPartEntityPart part = (MultiPartEntityPart) entArray[k];
						if (part.getEntityBoundingBox().intersects(this.getEntityBoundingBox())) {
							part.attackEntityFrom(DamageSource.causeMobDamage(this.Shooter), this.Dmg);
						}
					}
				} else if (entity != this.Shooter && entity != this && !entity.isDead) {
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
		float f2 = MathHelper.sqrt(par1 * par1 + par3 * par3 + par5 * par5);
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
	protected void onImpact(RayTraceResult result) {
		try {
			IBlockState iblockstate = this.world.getBlockState(result.getBlockPos());
			Boolean solid = this.world.getBlockState(result.getBlockPos()).getBlock().getMaterial(iblockstate).blocksMovement();
			if (!(result.entityHit instanceof Entity) && solid ) {
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
