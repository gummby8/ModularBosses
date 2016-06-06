package com.Splosions.ModularBosses.entity;

import com.google.common.collect.Lists;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCustomFallingBlock extends Entity implements IEntityAdditionalSpawnData
{
    private IBlockState fallTile;
    public int fallTime;
    public boolean shouldDropItem = true;
    private boolean field_145808_f;
    private boolean hurtEntities;
    private int fallHurtMax = 40;
    private float fallHurtAmount = 2.0F;
    public NBTTagCompound tileEntityData;
    private static final String __OBFID = "CL_00001668";
    public BlockPos bPos;
	public int damage;

    public EntityCustomFallingBlock(World worldIn)
    {
        super(worldIn);
    }

    public EntityCustomFallingBlock(World worldIn, Entity shooter, double x, double y, double z, double mY, float yaw, BlockPos pos, int dmg)
    {
        super(worldIn);
        this.bPos = pos;
        this.fallTile = this.worldObj.getBlockState(pos);
        this.preventEntitySpawning = true;
        this.setSize(1F, 1F);
        this.setPositionAndRotation(x, y, z, yaw, 0);
        this.motionX = 0.0D;
        this.motionY = mY;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.noClip = true;
        this.damage = dmg;
        
    } 


    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
    
    	

            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;

            this.motionY -= 0.03999999910593033D;
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.9800000190734863D;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= 0.9800000190734863D;
            
            if (this.ticksExisted > 20){this.setDead();}
           
            this.collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()));
    }
    
    
    
    
    
    
    
    /**
     * Pushes all entities inside the list away from the entity.
     */
    private void collideWithEntities(List list)
    {
        double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
        double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
        Iterator iterator = list.iterator();

        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();


            	
                double d2 = entity.posX - d0;
                double d3 = entity.posZ - d1;
                double d4 = d2 * d2 + d3 * d3;
                
                if (entity.hurtResistantTime == 0 && entity instanceof EntityCustomFallingBlock == false && entity instanceof EntityParagon == false){
                entity.addVelocity(d2 / d4 * 0.2D, 1.2D, d3 / d4 * 0.2D);
                entity.attackEntityFrom(DamageSource.fall, damage);
                entity.hurtResistantTime = 10;
                }
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    

    @SideOnly(Side.CLIENT)
    public World getWorldObj()
    {
        return this.worldObj;
    }


    public IBlockState getBlock()
    {
        return this.fallTile;
    }
    
    


	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	  /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound tagCompound){}


    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound tagCompund){}


	@Override
	public void writeSpawnData(ByteBuf buffer) {
		
		
		if (this.bPos != null){
		buffer.writeInt(this.bPos.getX());
		buffer.writeInt(this.bPos.getY());
		buffer.writeInt(this.bPos.getZ());
		
		buffer.writeDouble(this.motionY);
		buffer.writeFloat(this.rotationYaw);
		}
		
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		int x = additionalData.readInt();
		int y = additionalData.readInt();
		int z = additionalData.readInt();
				
		this.bPos = new BlockPos(x,y,z);
		this.fallTile = this.getWorldObj().getBlockState(this.bPos);
		
		this.motionY = additionalData.readDouble();
		this.noClip = true;
		this.rotationYaw = additionalData.readFloat();
	}
}