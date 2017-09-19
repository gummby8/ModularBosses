package com.Splosions.ModularBosses.entity;

import java.util.List;
import java.util.Random;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;
import com.Splosions.ModularBosses.entity.projectile.EntityFlameThrower;
import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityShadeHowler extends EntityMob {

	private static final int DEATH_WATCHER = 16;
	private static final int ANI_ID_WATCHER = 17;
	private static final int ANI_FRAME_WATCHER = 18;

	public static final int STAND = 0;
	public static final int WALK = 1;
	public static final int JUMP = 2;
	public static final int HOWL = 3;
	public static final int BITE = 4;
	

	private int lastAttackCounter = 0;
	
	Entity target;
	double targetX;
	double targetY;
	double targetZ;

	double jumpX;
	double jumpY;
	double jumpZ;
	boolean jumpOff = false;
	int jumpCount;
	boolean bite;
	
	double X;
	double Z;

	double X1;
	double Z1;

	double X2;
	double Z2;
	
	public int aniID;
	public int prevaniID;
	public int aniFrame;
	public int aniPause;


	
	
	public boolean targetLocked;
	public float howlEnd;
	public float howlBegin;

	public double howlEndX;
	public double howlEndZ;
	
	public double howlBeginX;
	public double howlBeginZ;

	public EntityShadeHowler(World worldIn) {
		super(worldIn);
		this.setSize(1.5F, 3.0F);
		this.isImmuneToFire = true;
		this.experienceValue = 5;
		// AI STUFF
		// this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		// this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.21D, false)); 
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.21D, true));
		//this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.25D));
		//this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.25D, false));
		//this.tasks.addTask(6, new EntityAIWander(this, 0.25D)); // Wander speed
		//this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));

		this.ignoreFrustumCheck = true;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DEATH_WATCHER, 0);
		this.dataWatcher.addObject(ANI_ID_WATCHER, 0);
		this.dataWatcher.addObject(ANI_FRAME_WATCHER, 0);

	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		TargetUtils.betaMsg(this);
		
		
		this.ignoreFrustumCheck = true;
		

		
		this.aniID = this.dataWatcher.getWatchableObjectInt(ANI_ID_WATCHER);
		this.aniFrame = (this.aniID != this.prevaniID)? 0 : this.aniFrame;
		
		if (this.ticksExisted == 1 && !this.worldObj.isRemote){
			aniID = JUMP;	
		}
		
		if (this.ticksExisted % 20 == (20 - 1) && !this.worldObj.isRemote && this.target == null) {
			this.target = TargetUtils.findRandomVisablePlayer(this, 20, 4);
		}
		
		
		try {
		if (this.aniID == STAND) {
			this.aniFrame = 0;
		} else if (this.aniID == HOWL && this.aniFrame > 4 && this.aniFrame < 30) {
			howl(TargetUtils.getList(this, 7, 4),45,1F,0.5F,5);
			placeHowl(6);
			this.aniFrame++;
		} else if (this.aniID == HOWL && this.aniFrame > 43) {
			this.aniFrame = 0;
			aniID = STAND;
		} else if (this.aniID == JUMP && this.aniFrame == 5) {
			if (this.target != null) {
				this.targetX = target.posX;
				this.targetZ = target.posZ;
				this.jumpX = this.posX;
				this.jumpZ = this.posZ;
			} else {
				this.targetX = this.posX;
				this.targetZ = this.posZ;
				this.jumpX = this.posX;
				this.jumpZ = this.posZ;
			}
			this.bite = false;
			this.motionY = 0.4D;
			this.jumpCount++;
			this.jumpOff = (this.jumpOff) ? false : true;
						
			double d0 = this.posX - this.targetX;
			double d2 = this.posZ - this.targetZ;
			double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
			float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			this.rotationYaw = f + 180;
			
			this.X = (20 * Math.cos(Math.toRadians(this.rotationYaw + 90))) + this.posX;
			this.Z = (20 * Math.sin(Math.toRadians(this.rotationYaw + 90))) + this.posZ;
			
			this.X1 = (10 * Math.cos(Math.toRadians(this.rotationYaw + 180))) + X;
			this.Z1 = (10 * Math.sin(Math.toRadians(this.rotationYaw + 180))) + Z;

			this.X2 = (10 * Math.cos(Math.toRadians(this.rotationYaw))) + X;
			this.Z2 = (10 * Math.sin(Math.toRadians(this.rotationYaw))) + Z;
			
			this.aniFrame++;
			} else if (this.aniID == JUMP && this.aniFrame > 5 && this.aniFrame <= 20) {
				if (this.target != null) {
					double d0;
					double d2;
					if (this.target.getDistanceToEntity(this) < 5) {
						d0 = this.posX - this.X;
						d2 = this.posZ - this.Z;
					} else {
						d0 = (this.jumpOff) ? this.posX - this.X1 : this.posX - this.X2;
						d2 = (this.jumpOff) ? this.posZ - this.Z1 : this.posZ - this.Z2;
					}

					double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
					float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
					this.rotationYaw = f + 180;
					moveForward(0.1F);
				}
				
				List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(2, 2, 2));

				if (!list.isEmpty()) {
					this.bite = true;
					this.target = (Entity) list.get(0);
					MBExtendedPlayer.get((EntityPlayer) this.target).knockdownTime = 20;
					if (this.target == Minecraft.getMinecraft().thePlayer) {
						ModularBosses.INSTANCE.playerTarget = this;
					}
				}

				this.aniFrame++;
			} else if (this.aniID == JUMP && this.aniFrame > 20 && this.aniFrame < 25) {
			if (!this.isAirBorne){
				if (this.bite){
					this.target.rotationPitch = this.rotationPitch;
					this.target.rotationYaw = this.rotationYaw;
					this.target.posX = this.posX;
					this.target.posY = this.posY;
					this.target.posZ = this.posZ;
					this.aniID = STAND;
					
				} else {
					if (this.jumpCount < 5) {
						this.aniID = JUMP;
						this.aniFrame = 4;	
					} else {
						this.aniFrame++;
					}	
				}
			}
		} else if (this.aniID == JUMP && this.aniFrame > 25) {
			this.aniID = STAND;
			this.aniFrame = 0;
		} else if (this.aniID != STAND) {
			this.aniFrame++;
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		


		
		
			
		this.prevaniID = this.aniID;
		if (!this.worldObj.isRemote) {
			this.dataWatcher.updateObject(ANI_ID_WATCHER, aniID);
		}
	}
	
	
	/**
	 * Set mob death animations, just be sure to setDead at the end or the model
	 * wont go away
	 */
	protected void onDeathUpdate() {
		ModularBosses.INSTANCE.playerTarget = null;	
		setDead();
	}
	
	
	
	public void moveForward(float speed){
		float f2 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F);
		float f3 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F);
		this.motionX += (double)(-1 * speed * f2);
		this.motionZ += (double)(speed * f3);
	}
	
	private void setLastAttackCounter() {
		this.lastAttackCounter = 80;
		this.target = null;
	}
	
	
	
	private void howl(List par1List, float fov, double force, double height, float Damage) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (TargetUtils.isTargetInFrontOf(this, entity, fov) && entity instanceof EntityPlayer && entity.hurtResistantTime == 0) {
				EntityPlayer player = (EntityPlayer) entity;
				double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
				double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
				double d2 = entity.posX - d0;
				double d3 = entity.posZ - d1;
				double d4 = d2 * d2 + d3 * d3;
				entity.hurtResistantTime = 10;
				//player.addPotionEffect(new PotionEffect(15, 200, 1));
				
				//entity.attackEntityFrom(DamageSource.causeMobDamage(this), Damage);
				//entity.addVelocity(d2 / d4 * force, height, d3 / d4 * force);
				ModularBosses.INSTANCE.playerTarget = this;
				//MBExtendedPlayer.get(player).knockdownTime = 60;
					
				//System.out.println(entity);
			}
		}
	}
	

	public void placeHowl(float length) {
		if (this.worldObj.isRemote) {
			float angle = (float) Math.toRadians(this.rotationYaw + 90);
			double xe = ((length * MathHelper.cos(angle)) + this.posX);
			double ze = ((length * MathHelper.sin(angle)) + this.posZ);
			howlEndX = xe;
			howlEndZ = ze;
		}
	}

}