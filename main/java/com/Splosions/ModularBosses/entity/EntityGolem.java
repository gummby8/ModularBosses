package com.Splosions.ModularBosses.entity;

import java.util.List;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.entity.projectile.EntityBoulder;
import com.Splosions.ModularBosses.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.entity.projectile.EntityCustomFallingBlock;
import com.Splosions.ModularBosses.util.TargetUtils;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGolem extends EntityMob implements IEntityAdditionalSpawnData {

	/** The Entity this EntityCreature is set to attack. */
	public Entity entityToAttack;

	public int attackCounter;
	public int deathTicks;
	
	public int count;
	public BlockPos targetPos;

	public Boolean dead;
	public float hardness;
	public ResourceLocation textureLoc;

	public static final int BUILD = 0;
	public static final int STAND = 1;
	public static final int THROW = 2;
	public static final int ROLL = 3;
	public static final int STOMP = 4;
	public static final int DIE = 5;

	private static final int ANI_ID_WATCHER = 17;

	public int AniID = 0;
	public int PrevAniID = 0;
	public int AniFrame = 0;

	public int textureBlockID;

	/* ================== PARAGON CONFIG SETTINGS ===================== */
	public static double golemMaxHealthMulti;
	/** Golem Damage Multiplier */
	public static int golemDmgMulti;

	/** Paragon Jump Damage */

	public EntityGolem(World par1World) {
		super(par1World);
		// sets hitbox size
		this.setSize(2F, 6.5F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;

		// AI STUFF
		// this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		// this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.21D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.21D, true));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.25D));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.25D, false));
		this.tasks.addTask(6, new EntityAIWander(this, 0.25D)); // Wander speed
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
	}

	// stuns the mob
	public boolean isMovementBlocked() {
		if (this.AniID == BUILD) {
			return true;
		} else {
			return false;
		}
	}

	// won't despawn even if the chunk unloads
	protected boolean canDespawn() {
		return true;
	}

	public boolean isBurning() {
		return false;
	}

	public boolean isEntityInvulnerable() {
		if (this.AniID == BUILD) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected String getLivingSound() {
		return Sounds.GOLEM_LIVING;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		// Follow Range - default 32.0D - min 0.0D - max 2048.0D
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
		// Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		// Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.699D);
		// Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(ANI_ID_WATCHER, 0);
	}

	public static void postInitConfig(Configuration config) {
		golemMaxHealthMulti = config.get("Golem", "[Max Health] Golem Spawn Block Hardness multiplied by... [1+]", 20).getInt();
		golemDmgMulti = config.get("Golem", "[Attack Dmg] Golem Spawn Block Hardness multiplied by... [1+]", 1).getInt();
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		TargetUtils.betaMsg(this);
		getTexture();

		this.AniID = this.dataWatcher.getWatchableObjectInt(ANI_ID_WATCHER);
		this.AniFrame = (this.AniID != this.PrevAniID) ? 0 : this.AniFrame;


		if (entityToAttack == null) {
			entityToAttack = TargetUtils.findRandomVisablePlayer(this, 20, 4);
		} 
		

		this.AniFrame++;
		if (this.AniID == BUILD && this.AniFrame == 1) {
        	this.playSound(Sounds.GOLEM_BUILD, 5F, 1.0F);
		} else if (this.AniID == BUILD && this.AniFrame > 90) {
			this.AniFrame = 0;
			this.AniID = STAND;
		} else if (this.AniID == THROW && this.AniFrame == 15) {
			throwRock();		
		} else if (this.AniID == THROW && this.AniFrame > 29) {
			this.AniFrame = 0;
			this.AniID = STAND;
		} else if (this.AniID == ROLL && this.AniFrame > 0 && this.AniFrame < 9) {
			if (this.entityToAttack != null){
				this.faceEntity(this.entityToAttack, 360, 0);
				this.moveHelper.setMoveTo(this.entityToAttack.posX, this.posY, this.entityToAttack.posZ, 0.3F);
			}
		} else if (this.AniID == ROLL && this.AniFrame == 9) {
			this.playSound(Sounds.GOLEM_ROLL, 5F, 1.0F);
			count = 0;
			Vec3 look = this.getLookVec();
			float distance = 20F; //distance in front of entity
			double dx = this.posX + (look.xCoord * distance);
			double dy = this.posY; 
			double dz = this.posZ + (look.zCoord * distance);
			this.targetPos = new BlockPos(dx, dy - 1, dz);
		} else if (this.AniID == ROLL && this.AniFrame > 9 && this.AniFrame < 20) {
			this.moveHelper.setMoveTo(this.targetPos.getX(), this.posY, this.targetPos.getZ(), 1);				
		} else if (this.AniID == ROLL && this.AniFrame == 20 && this.count < 2) {
			this.AniFrame = 10;
			this.count++;
		} else if (this.AniID == ROLL && this.AniFrame > 23) {
			this.AniFrame = 0;
			this.AniID = STAND;
		} else if (this.AniID == STOMP && this.AniFrame > 8 && this.AniFrame < 16) {
			stompAttack();
		} else if (this.AniID == STOMP && this.AniFrame > 17) {
			this.AniFrame = 0;
			this.AniID = STAND;
		} else if (this.AniID == DIE && this.AniFrame == 1) {
			this.playSound(Sounds.GOLEM_BUILD, 5F, 1.0F);
			this.playSound(Sounds.GOLEM_LIVING, 5F, 1.0F);
		} else if (this.AniID == DIE && this.AniFrame > 54) {
			for (int x = 0; x < 40; x++){
		         float f = (this.rand.nextFloat() - 0.5F) * 3;
		         float f1 = (this.rand.nextFloat() - 0.5F) * 3;
		         float f2 = (this.rand.nextFloat() - 0.5F) * 3;
				 this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)f, this.posY + 1 + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);				
			}
			this.setDead();
		}
		System.out.println(this.AniID);
		this.PrevAniID = this.AniID;

		if (!this.worldObj.isRemote) {
			this.dataWatcher.updateObject(ANI_ID_WATCHER, AniID);
		}
	}
	
	@Override
	 protected void onDeathUpdate() {
		 this.AniID = DIE;
			if (!this.worldObj.isRemote) {
				this.dataWatcher.updateObject(ANI_ID_WATCHER, AniID);
			}
	 }
		 


	/**
	 * Camera Shake stuff 4 intensity is pretty good shake
	 */
	boolean CamShake = false;
	float CamShakeIntensity;

	public void CamShake(Entity entity, float distance, float Intenstity) {
		if (this.worldObj.isRemote) {
			List<EntityPlayer> players = entity.worldObj.getEntitiesWithinAABB(EntityPlayer.class, entity.getEntityBoundingBox().expand(distance, 4, distance));
			this.CamShake = (this.CamShake == false) ? true : false;
			this.CamShakeIntensity = (this.CamShake) ? Intenstity : -Intenstity;
			for (EntityPlayer player : players) {
				player.setAngles(0, CamShakeIntensity);
			}
		}
	}

	/**
	 * Finds the closest player within 16 blocks to attack, or null if this
	 * Entity isn't interested in attacking (Animals, Spiders at day, peaceful
	 * PigZombies).
	 */
	protected Entity findPlayerToAttack() {
		EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 30.0D);
		return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
	}
	
	
	public void stompAttack(){
		this.playSound("mob.ghast.fireball", 1F, 1.0F);
		// CamShake(this, 10, 40);
		for (float i = 0.5F; i < 3; ++i) {
			double x = ((AniFrame - 6) * Math.cos(Math.toRadians((i * 6) + this.rotationYaw + 90))) + this.posX;
			double z = ((AniFrame - 6) * Math.sin(Math.toRadians((i * 6) + this.rotationYaw + 90))) + this.posZ;

			BlockPos pos = new BlockPos(x, this.posY - 1, z);
			EntityCustomFallingBlock falling = new EntityCustomFallingBlock(this.worldObj, this, x, this.posY - 1, z, 0.4F, (i * 6) + this.rotationYaw + 90, pos, 1);
			if (!this.worldObj.isRemote) {
				this.worldObj.spawnEntityInWorld(falling);
			}
		}
		for (float i = 0.5F; i < 3; ++i) {
			double x = ((AniFrame - 6) * Math.cos(Math.toRadians((i * -6) + this.rotationYaw + 90))) + this.posX;
			double z = ((AniFrame - 6) * Math.sin(Math.toRadians((i * -6) + this.rotationYaw + 90))) + this.posZ;

			BlockPos pos = new BlockPos(x, this.posY - 1, z);
			EntityCustomFallingBlock falling = new EntityCustomFallingBlock(this.worldObj, this, x, this.posY - 1, z, 0.4F, (i * -6) + this.rotationYaw + 90, pos, 1);
			if (!this.worldObj.isRemote) {
				this.worldObj.spawnEntityInWorld(falling);
			}
		}
	}

	protected void throwRock() {
		float distance = entityToAttack.getDistanceToEntity(this);
		if (distance > 30.0F) {
			entityToAttack = null;
		} else if (canEntityBeSeen(entityToAttack)) {
			faceEntity(entityToAttack, 10.0F, 10.0F);
			EntityLivingBase ent = (EntityLivingBase) entityToAttack;
			if (this.AniID == THROW && this.AniFrame == 15) {
				float dmg = this.hardness * golemDmgMulti;
				Entity projectile;
				projectile = new EntityBoulder(worldObj, this, (EntityLivingBase) ent, 1.5F, 0, 2.4F, -1.6F, 0F, 1, 1).setDamage(dmg);
				if (!this.worldObj.isRemote) {
					worldObj.spawnEntityInWorld(projectile);
				}
			}
		}
	}

	public void getTexture() {
		try {
			if (this.textureLoc == null) {
				IBlockState iblockstate = this.worldObj.getBlockState(this.getPosition().down());
				textureBlockID = Block.getStateId(iblockstate.getBlock().getDefaultState());
				this.hardness = iblockstate.getBlock().getBlockHardness(this.worldObj, this.getPosition().down());
				BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
				IBakedModel ibakedmodel = blockrendererdispatcher.getModelFromBlockState(iblockstate, this.worldObj, this.getPosition());
				String string = ibakedmodel.getTexture().getIconName() + ".png";
				String[] parts = string.split(":");
				textureLoc = new ResourceLocation(parts[0] + ":textures/" + parts[1]);
				this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.golemMaxHealthMulti * this.hardness);
				this.heal((float) (this.golemMaxHealthMulti * this.hardness));
			}
		} catch (Exception e) {
			ModularBosses.logger.warn("A Golem Tried To Spaw Without A Texture At Position - " + this.getPosition());
			this.setDead();
		}

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.textureLoc = new ResourceLocation(compound.getString("textureLoc"));
		this.textureBlockID = compound.getInteger("textureBlockID");
		this.hardness = compound.getFloat("hardness");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		getTexture();
		compound.setString("textureLoc", this.textureLoc.toString());
		compound.setInteger("textureBlockID", textureBlockID);
		compound.setFloat("hardness", this.hardness);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		getTexture();
		ByteBufUtils.writeUTF8String(buffer, this.textureLoc.toString());
		ByteBufUtils.writeVarInt(buffer, textureBlockID, 2);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		textureLoc = new ResourceLocation(ByteBufUtils.readUTF8String(additionalData));
		textureBlockID = ByteBufUtils.readVarInt(additionalData, 2);

	}

}
