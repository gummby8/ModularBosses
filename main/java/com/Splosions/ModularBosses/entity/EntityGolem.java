package com.Splosions.ModularBosses.entity;



import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.entity.projectile.EntityBoulder;
import com.Splosions.ModularBosses.entity.projectile.EntityChorpSlimeBlob;
import com.Splosions.ModularBosses.util.TargetUtils;

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
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EntityGolem extends EntityMob
{

    /** The Entity this EntityCreature is set to attack. */
    public Entity entityToAttack;
    
	public int attackCounter;
	public int deathTicks;


	public float DeadRot;
	public float hardness;
	public ResourceLocation textureLoc;


	
	public static final int BUILD = 0;
	public static final int STAND = 1;
	public static final int WALK = 2;
	public static final int THROW = 3;
	public static final int SPRINT = 4;
	public static final int COLLAPSE = 5;
	public static final int KICK = 6;
	public static final int DOUBLEFISTSLAM = 7;
	public static final int SQUISH = 8;
	public static final int JUMP = 9;
	public static final int FLAMETHROWER = 10;
	public static final int SLAP = 11;
	
	private static final int ANI_ID_WATCHER = 17;
	
	public int AniID = 0;
	public int PrevAniID = 0;
	public int AniFrame = 0;
	
	/*================== PARAGON CONFIG SETTINGS  =====================*/
	public static double golemMaxHealthMulti;
	/** Golem Damage Multiplier */
	public static int golemDmgMulti;
	/** Paragon Jump Damage */

	
	
	
	public EntityGolem(World par1World) {
		super(par1World);
		//sets hitbox size
		this.setSize(2F, 6.5F);
		this.experienceValue = 10;
		this.isImmuneToFire = false;

		//AI STUFF
		//this.getNavigator().setBreakDoors(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		//this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.21D, false)); //How fast mob moves towards the player
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.21D, true));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.25D));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 0.25D, false));
		this.tasks.addTask(6, new EntityAIWander(this, 0.25D)); //Wander speed
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
	}

	//stuns the mob
	public boolean isMovementBlocked() {
			return false;
	}
	//won't despawn even if the chunk unloads
	protected boolean canDespawn()
	{
		return true;
	}

	public boolean isBurning()
	{
		return false;
	}

	public boolean isEntityInvulnerable()
	{
		return false;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void applyEntityAttributes()
	{
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


	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(ANI_ID_WATCHER, 0);
	}


	public static void postInitConfig(Configuration config) {
		golemMaxHealthMulti = config.get("Golem", "[Max Health] Golem Spawn Block Hardness multiplied by... [1+]", 20).getInt();
		golemDmgMulti = config.get("Golem", "[Attack Dmg] Golem Spawn Block Hardness multiplied by... [1+]", 1).getInt();
	}






	public void onLivingUpdate() {
		//super.onLivingUpdate();
		TargetUtils.betaMsg(this);
		getTexture();

		this.AniID = this.dataWatcher.getWatchableObjectInt(ANI_ID_WATCHER);
		this.AniFrame = (this.AniID != this.PrevAniID)? 0 : this.AniFrame;
		
		this.AniID = THROW;
		
		float distance = 0.0F;


		if (entityToAttack == null) {
			entityToAttack = findPlayerToAttack();

		} else if (entityToAttack.isEntityAlive() && canAttackClass(entityToAttack.getClass())) {
			distance = entityToAttack.getDistanceToEntity(this);
			if (distance > 30.0F) {
				entityToAttack = null;
			} else if (canEntityBeSeen(entityToAttack)) {
				
				faceEntity(entityToAttack, 10.0F, 10.0F);
				attackEntity(entityToAttack, distance);
			}
		} else {
			entityToAttack = null;
		}
		
		
		this.AniFrame++;
		if (this.AniID == BUILD && this.AniFrame > 90){
			this.AniFrame = 0;
		} else
		if (this.AniID == THROW && this.AniFrame > 29){
			this.AniFrame = 0;
		}
		
		this.PrevAniID = this.AniID;
		
		if (!this.worldObj.isRemote) {this.dataWatcher.updateObject(ANI_ID_WATCHER, AniID);}
	}
	
	
    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 30.0D);
        return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
    }


	protected void attackEntity(Entity entity, float distance) {
		EntityLivingBase ent = (EntityLivingBase) entity;
		if (!ent.isPotionActive(Potion.moveSlowdown)) {
			if (this.attackCounter == 40) {
				this.worldObj.playSoundAtEntity(this, Sounds.CHORP_SLIME, 1.0F, 1.0F);
				this.attackCounter = -10;
			}
			if (this.attackCounter == 45) {
				float dmg = this.hardness * golemDmgMulti;
				Entity projectile;
				int difficulty = worldObj.getDifficulty().getDifficultyId();
				projectile = new EntityBoulder(worldObj, this, (EntityLivingBase) entity, 1.5F, 0,0,-2,0,0,0).setDamage(dmg * difficulty);
				if (!this.worldObj.isRemote){
				worldObj.spawnEntityInWorld(projectile);
				}
			}
			if (this.attackCounter >= 60) {
				this.attackCounter = -40;
			}
		}
		//this.attackCounter++;
	}
	
	public void getTexture(){
		try{
		if (this.textureLoc == null){
		IBlockState iblockstate = this.worldObj.getBlockState(this.getPosition().down());
		this.hardness = iblockstate.getBlock().getBlockHardness(this.worldObj, this.getPosition().down());
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        IBakedModel ibakedmodel = blockrendererdispatcher.getModelFromBlockState(iblockstate, this.worldObj, this.getPosition());
        String string = ibakedmodel.getTexture().getIconName() + ".png";
        String[] parts = string.split(":");
        textureLoc = new ResourceLocation(parts[0] + ":textures/" + parts[1]);
        System.out.println(textureLoc);
       	this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.golemMaxHealthMulti * this.hardness);       	
       	this.heal((float) (this.golemMaxHealthMulti * this.hardness));
        }
		} catch (Exception e) {
			ModularBosses.logger.warn("A Golem Tried To Spaw Without A Texture At Position - " + this.getPosition() );
			this.setDead();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.textureLoc = new ResourceLocation(compound.getString("textureLoc"));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		getTexture();
		compound.setString("textureLoc", this.textureLoc.toString());

	}
	
}
