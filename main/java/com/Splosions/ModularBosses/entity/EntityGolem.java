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
import net.minecraft.util.DamageSource;
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

	public int textureBlockID = -1;

	/* ================== GOLEM CONFIG SETTINGS ===================== */
	public static double golemMaxHealthMulti;
	public static int golemDmgMulti;
	public static int attackCooldown;
	
	  private EntityAIWander entityAIWander = new EntityAIWander(this, 0.25D);

	public EntityGolem(World par1World) {
		super(par1World);
		// sets hitbox size
		this.setSize(2F, 6.5F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;
		this.enablePersistence();
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
		attackCooldown = config.get("Golem", "[Attack Cooldown] Ammount of seconds between attacks... [1+]", 2).getInt() * 20;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		TargetUtils.betaMsg(this);
		getTexture();
		
		if (entityToAttack == null && !this.worldObj.isRemote) {
			entityToAttack = TargetUtils.findRandomVisablePlayer(this, 20, 4);
		} 
		
		if (this.AniID == STAND && entityToAttack != null && !this.worldObj.isRemote) {
			this.moveHelper.setMoveTo(this.entityToAttack.posX, this.entityToAttack.posY, this.entityToAttack.posZ, 0.35F);
		} 
		
		if (this.entityToAttack == null){
			this.tasks.addTask(1, entityAIWander);
		} else {
			this.tasks.removeTask(entityAIWander);
		}
		
		if (!this.worldObj.isRemote && this.AniID == STAND && entityToAttack != null) {
			attackPicker();
		}
		
		
		
		this.AniID = this.dataWatcher.getWatchableObjectInt(ANI_ID_WATCHER);
		this.AniFrame = (this.AniID != this.PrevAniID) ? 0 : this.AniFrame;




		this.AniFrame++;
		if (this.AniID == BUILD && this.AniFrame == 1) {
        	this.playSound(Sounds.GOLEM_BUILD, 4F, 1.0F);
		} else if (this.AniID == BUILD && this.AniFrame > 90) {
			this.AniFrame = 0;
			this.AniID = STAND;
		} else if (this.AniID == THROW && this.AniFrame == 15) {
			if (!this.worldObj.isRemote){throwRock();}		
		} else if (this.AniID == THROW && this.AniFrame > 29) {
			entityToAttack = null;
			this.AniFrame = 0;
			this.AniID = STAND;
		} else if (this.AniID == ROLL && this.AniFrame > 0 && this.AniFrame < 9) {
			if (this.entityToAttack != null){
				this.faceEntity(this.entityToAttack, 360, 0);
				this.moveHelper.setMoveTo(this.entityToAttack.posX, this.posY, this.entityToAttack.posZ, 0.3F);
			}
		} else if (this.AniID == ROLL && this.AniFrame == 9) {
			this.playSound(Sounds.GOLEM_ROLL, 4F, 1.0F);
			count = 0;
			Vec3 look = this.getLookVec();
			float distance = 20F; //distance in front of entity
			double dx = this.posX + (look.xCoord * distance);
			double dy = this.posY; 
			double dz = this.posZ + (look.zCoord * distance);
			this.targetPos = new BlockPos(dx, dy - 1, dz);
		} else if (this.AniID == ROLL && this.AniFrame > 9 && this.AniFrame < 20) {
			List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(1, 0, 1));
			this.kickEntities(list, 3D, 1D, this.hardness * golemDmgMulti);	
			this.moveHelper.setMoveTo(this.targetPos.getX(), this.posY, this.targetPos.getZ(), 1);				
		} else if (this.AniID == ROLL && this.AniFrame == 20 && this.count < 2) {
			this.AniFrame = 10;
			this.count++;
		} else if (this.AniID == ROLL && this.AniFrame > 23) {
			entityToAttack = null;
			this.AniFrame = 0;
			this.AniID = STAND;
		} else if (this.AniID == STOMP && this.AniFrame > 8 && this.AniFrame < 16) {
			stompAttack();
		} else if (this.AniID == STOMP && this.AniFrame > 17) {
			this.AniFrame = 0;
			this.AniID = STAND;
			entityToAttack = null;			
		} else if (this.AniID == DIE && this.AniFrame == 1) {
			this.playSound(Sounds.GOLEM_BUILD, 4F, 1.0F);
			this.playSound(Sounds.GOLEM_LIVING, 4F, 1.0F);
		} else if (this.AniID == DIE && this.AniFrame > 54) {
			for (int x = 0; x < 40; x++){
		         float f = (this.rand.nextFloat() - 0.5F) * 3;
		         float f1 = (this.rand.nextFloat() - 0.5F) * 3;
		         float f2 = (this.rand.nextFloat() - 0.5F) * 3;
				 this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)f, this.posY + 1 + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);				
			}
			this.setDead();
		}
		
		this.PrevAniID = this.AniID;
		if (!this.worldObj.isRemote) {
			this.dataWatcher.updateObject(ANI_ID_WATCHER, AniID);
		}
	}
	
	
	public void attackPicker(){
		if (this.attackCounter <= 0){
			int pick = TargetUtils.getRanNum(0, 10);
			if (pick < 5){
				this.AniID = THROW;
			} else if (pick >= 5 && pick <= 7 ){
				this.AniID = STOMP;
			} else {
				this.AniID = ROLL;
			}
			this.attackCounter = attackCooldown;
			this.dataWatcher.updateObject(ANI_ID_WATCHER, AniID);
		} else {
			this.attackCounter--;
		}
	}
	
	
	
	
	@Override
	 protected void onDeathUpdate() {
		 this.AniID = DIE;
		 entityToAttack = null;
			if (!this.worldObj.isRemote) {
				this.dataWatcher.updateObject(ANI_ID_WATCHER, AniID);
			}
	 }
	
	
	/**
	 * Attacks all entities inside this list, dealing 5 hearts of damage.
	 */
	private void kickEntities(List par1List, double force, double height, float Damage) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity instanceof EntityPlayer) {
				double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
				double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
				double d2 = entity.posX - d0;
				double d3 = entity.posZ - d1;
				double d4 = d2 * d2 + d3 * d3;
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), Damage);
				entity.addVelocity(d2 / d4 * force, height, d3 / d4 * force);
			}

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
		this.playSound("mob.ghast.fireball", 4F, 1.0F);
		 CamShake(this, 15, 40);
		for (float i = 0.5F; i < 3; ++i) {
			double x = ((AniFrame - 6) * Math.cos(Math.toRadians((i * 6) + this.rotationYaw + 90))) + this.posX;
			double z = ((AniFrame - 6) * Math.sin(Math.toRadians((i * 6) + this.rotationYaw + 90))) + this.posZ;

			BlockPos pos = new BlockPos(x, this.posY - 1, z);
			EntityCustomFallingBlock falling = new EntityCustomFallingBlock(this.worldObj, this, x, this.posY - 1, z, 0.4F, (i * 6) + this.rotationYaw + 90, pos, (int) (this.hardness * golemDmgMulti));
			if (!this.worldObj.isRemote) {
				this.worldObj.spawnEntityInWorld(falling);
			}
		}
		for (float i = 0.5F; i < 3; ++i) {
			double x = ((AniFrame - 6) * Math.cos(Math.toRadians((i * -6) + this.rotationYaw + 90))) + this.posX;
			double z = ((AniFrame - 6) * Math.sin(Math.toRadians((i * -6) + this.rotationYaw + 90))) + this.posZ;

			BlockPos pos = new BlockPos(x, this.posY - 1, z);
			EntityCustomFallingBlock falling = new EntityCustomFallingBlock(this.worldObj, this, x, this.posY - 1, z, 0.4F, (i * -6) + this.rotationYaw + 90, pos, (int) (this.hardness * golemDmgMulti));
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
			if (textureBlockID == -1) {
				BlockPos blockPos = new BlockPos(this.posX, this.posY, this.posZ);
				while (!worldObj.getBlockState(blockPos).getBlock().getMaterial().blocksMovement()) {
					blockPos = blockPos.down();
				}
				IBlockState iblockstate = this.worldObj.getBlockState(blockPos);
				textureBlockID = Block.getStateId(iblockstate);
				System.out.println("" + textureBlockID);
				this.hardness = iblockstate.getBlock().getBlockHardness(this.worldObj, blockPos);
				this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.golemMaxHealthMulti * this.hardness);
				this.heal((float) (this.golemMaxHealthMulti * this.hardness));
			}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
			this.textureBlockID = compound.getInteger("textureBlockID");
			this.hardness = compound.getFloat("hardness");
			this.dataWatcher.updateObject(ANI_ID_WATCHER, STAND);
			
			IBlockState iblockstate = Block.getStateById(textureBlockID);
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			IBakedModel ibakedmodel = blockrendererdispatcher.getModelFromBlockState(iblockstate, this.worldObj, new BlockPos(0,0,0));
			String string = ibakedmodel.getTexture().getIconName() + ".png";
			System.out.println(string);
			String[] parts = string.split(":");
			textureLoc = new ResourceLocation(parts[0] + ":textures/" + parts[1]);	
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		getTexture();
		compound.setInteger("textureBlockID", textureBlockID);
		compound.setFloat("hardness", this.hardness);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		getTexture();
		ByteBufUtils.writeVarInt(buffer, textureBlockID, 4);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
			textureBlockID = ByteBufUtils.readVarInt(additionalData, 4);
			IBlockState iblockstate = Block.getStateById(textureBlockID);
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			IBakedModel ibakedmodel = blockrendererdispatcher.getModelFromBlockState(iblockstate, this.worldObj, new BlockPos(0,0,0));
			String string = ibakedmodel.getTexture().getIconName() + ".png";
			String[] parts = string.split(":");
			textureLoc = new ResourceLocation(parts[0] + ":textures/" + parts[1]);
	}

}
