package com.Splosions.ModularBosses.entity;

import java.util.Random;

import com.Splosions.ModularBosses.util.TargetUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySkull  extends EntityFlying implements IMob
{
    /** The explosion radius of spawned fireballs. */
    private int explosionStrength = 1;
    private static final String __OBFID = "CL_00001689";
    
    public boolean TargetLocked;
    
	public static int skullMaxHealth;
	public static int skullDmg;
	public static int skullFollowDistance;
	
	public static int skullExpDrop;
	public static String[] skullLoot = new String[]{"100|1|mb:itemNote","1|1|mb:itemNote"};

    public EntitySkull(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
        this.moveHelper = new EntitySkull.GhastMoveHelper();
        this.tasks.addTask(5, new EntitySkull.AIRandomFly());
        this.tasks.addTask(7, new EntitySkull.AILookAround());
        this.tasks.addTask(2, new EntitySkull.AIFireballAttack());
        
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        
    }
    
    protected void applyEntityAttributes()
    {
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

    @SideOnly(Side.CLIENT)
    public boolean func_110182_bF()
    {
        return this.dataWatcher.getWatchableObjectByte(16) != 0;
    }

    public void func_175454_a(boolean p_175454_1_)
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)(p_175454_1_ ? 1 : 0)));
    }

    public int func_175453_cd()
    {
        return this.explosionStrength;
    }
    
    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
    	 player.attackEntityFrom(DamageSource.causeMobDamage(this), skullDmg);
    	 this.TargetLocked = false;
     }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }
        
        
        EntityLivingBase entitylivingbase = this.worldObj.getClosestPlayerToEntity(this, 10.0D);
        

        
        //distance mob can see a player
        double distance = 64.0D;

        
        if (entitylivingbase != null && entitylivingbase.getDistanceSqToEntity(this) < distance * distance && this.canEntityBeSeen(entitylivingbase))
        {
        	
            World world = this.worldObj;
         
            	//System.out.println("TARGET " + entitylivingbase.getName());
            	
    			double d0 = this.posX - entitylivingbase.posX;
    			double d2 = this.posZ - entitylivingbase.posZ;
    			double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
    			float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;

    			this.rotationYaw = f + 180;
    			
    			
    			double e0 = entitylivingbase.posX - this.posX;
    			double e1 = entitylivingbase.posY - this.posY + 1;
    			double e2 = entitylivingbase.posZ - this.posZ;
    			double e3 = e0 * e0 + e1 * e1 + e2 * e2;
                
    			this.motionX += e0 / e3 * 0.2D;
    			this.motionY += e1 / e3 * 0.2D;
    			this.motionZ += e2 / e3 * 0.2D;
    			this.TargetLocked = true;

        }
        
        
        
        
        
        
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {

            return super.attackEntityFrom(source, amount);

    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }



    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.ghast.moan";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.ghast.scream";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.ghast.death";
    }

	@Override
	public void onDeathUpdate() {
		super.onDeathUpdate();
			if (!this.worldObj.isRemote && this.deathTime == 20) {
				TargetUtils.dropExp(this, this.skullExpDrop);
				TargetUtils.dropLoot(this, this.skullLoot);
			}
	}

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.rand.nextInt(2) + this.rand.nextInt(1 + p_70628_2_);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Items.ghast_tear, 1);
        }

        j = this.rand.nextInt(3) + this.rand.nextInt(1 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Items.gunpowder, 1);
        }
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 10.0F;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger("ExplosionPower", this.explosionStrength);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);

        if (tagCompund.hasKey("ExplosionPower", 99))
        {
            this.explosionStrength = tagCompund.getInteger("ExplosionPower");
        }
    }

    public float getEyeHeight()
    {
        return 2.6F;
    }

    class AIFireballAttack extends EntityAIBase
    {
        private EntitySkull mob = EntitySkull.this;
        public int attackCounter;
        private static final String __OBFID = "CL_00002215";
        

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return this.mob.getAttackTarget() != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            this.attackCounter = 0;
        }

        /**
         * Resets the task
         */
        public void resetTask()
        {
            this.mob.func_175454_a(false);
        }

        /**
         * Updates the task
         */
        public void updateTask()
        {
        	
            EntityLivingBase entitylivingbase = this.mob.getAttackTarget();
            //distance mob can see a player
            

            if (entitylivingbase.getDistanceSqToEntity(this.mob) < skullFollowDistance * skullFollowDistance && this.mob.canEntityBeSeen(entitylivingbase))
            {
                World world = this.mob.worldObj;
             
                	
                	
        			double d0 = mob.posX - entitylivingbase.posX;
        			double d2 = mob.posZ - entitylivingbase.posZ;
        			double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        			float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;

        			mob.rotationYaw = f + 180;
        			
        			
        			double e0 = entitylivingbase.posX - mob.posX;
        			double e1 = entitylivingbase.posY - mob.posY + 1;
        			double e2 = entitylivingbase.posZ - mob.posZ;
        			double e3 = e0 * e0 + e1 * e1 + e2 * e2;
                    
                    mob.motionX += e0 / e3 * 0.2D;
                    mob.motionY += e1 / e3 * 0.2D;
                    mob.motionZ += e2 / e3 * 0.2D;
                    mob.TargetLocked = true;

            }

        }
    }

    class AILookAround extends EntityAIBase
    {
        private EntitySkull field_179472_a = EntitySkull.this;
        private static final String __OBFID = "CL_00002217";

        public AILookAround()
        {
            this.setMutexBits(2);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return true;
        }

        /**
         * Updates the task
         */
        public void updateTask()
        {
            if (this.field_179472_a.getAttackTarget() == null)
            {
                this.field_179472_a.renderYawOffset = this.field_179472_a.rotationYaw = -((float)Math.atan2(this.field_179472_a.motionX, this.field_179472_a.motionZ)) * 180.0F / (float)Math.PI;
            }
            else
            {
                EntityLivingBase entitylivingbase = this.field_179472_a.getAttackTarget();
                double d0 = 64.0D;

                if (entitylivingbase.getDistanceSqToEntity(this.field_179472_a) < d0 * d0)
                {
                    double d1 = entitylivingbase.posX - this.field_179472_a.posX;
                    double d2 = entitylivingbase.posZ - this.field_179472_a.posZ;
                    this.field_179472_a.renderYawOffset = this.field_179472_a.rotationYaw = -((float)Math.atan2(d1, d2)) * 180.0F / (float)Math.PI;
                }
            }
        }
    }

    class AIRandomFly extends EntityAIBase
    {
        private EntitySkull field_179454_a = EntitySkull.this;
        private static final String __OBFID = "CL_00002214";

        public AIRandomFly()
        {
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            EntityMoveHelper entitymovehelper = this.field_179454_a.getMoveHelper();

            if (!entitymovehelper.isUpdating())
            {
                return true;
            }
            else
            {
                double d0 = entitymovehelper.func_179917_d() - this.field_179454_a.posX;
                double d1 = entitymovehelper.func_179919_e() - this.field_179454_a.posY;
                double d2 = entitymovehelper.func_179918_f() - this.field_179454_a.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean continueExecuting()
        {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            Random random = this.field_179454_a.getRNG();
            double d0 = this.field_179454_a.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.field_179454_a.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.field_179454_a.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.field_179454_a.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
    }

    class GhastMoveHelper extends EntityMoveHelper
    {
        private EntitySkull field_179927_g = EntitySkull.this;
        private int field_179928_h;
        private static final String __OBFID = "CL_00002216";

        public GhastMoveHelper()
        {
            super(EntitySkull.this);
        }

        public void onUpdateMoveHelper()
        {
            if (this.update)
            {
                double d0 = this.posX - this.field_179927_g.posX;
                double d1 = this.posY - this.field_179927_g.posY;
                double d2 = this.posZ - this.field_179927_g.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.field_179928_h-- <= 0)
                {
                    this.field_179928_h += this.field_179927_g.getRNG().nextInt(5) + 2;
                    d3 = (double)MathHelper.sqrt_double(d3);

                    if (this.func_179926_b(this.posX, this.posY, this.posZ, d3))
                    {
                        this.field_179927_g.motionX += d0 / d3 * 0.1D;
                        this.field_179927_g.motionY += d1 / d3 * 0.1D;
                        this.field_179927_g.motionZ += d2 / d3 * 0.1D;
                    }
                    else
                    {
                        this.update = false;
                    }
                }
            }
        }

        private boolean func_179926_b(double p_179926_1_, double p_179926_3_, double p_179926_5_, double p_179926_7_)
        {
            double d4 = (p_179926_1_ - this.field_179927_g.posX) / p_179926_7_;
            double d5 = (p_179926_3_ - this.field_179927_g.posY) / p_179926_7_;
            double d6 = (p_179926_5_ - this.field_179927_g.posZ) / p_179926_7_;
            AxisAlignedBB axisalignedbb = this.field_179927_g.getEntityBoundingBox();

            for (int i = 1; (double)i < p_179926_7_; ++i)
            {
                axisalignedbb = axisalignedbb.offset(d4, d5, d6);

                if (!this.field_179927_g.worldObj.getCollidingBoundingBoxes(this.field_179927_g, axisalignedbb).isEmpty())
                {
                    return false;
                }
            }

            return true;
        }
    }
}