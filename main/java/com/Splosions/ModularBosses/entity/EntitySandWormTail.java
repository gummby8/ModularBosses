package com.Splosions.ModularBosses.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class EntitySandWormTail  extends Entity
{
    /** The explosion radius of spawned fireballs. */
    private int explosionStrength = 1;
    private static final String __OBFID = "CL_00001689";
    
    public boolean TargetLocked;

    public EntitySandWormTail(World worldIn)
    {
        super(worldIn);
        this.setSize(4.0F, 4.0F);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
    }



    public EntitySandWormTail(World worldIn, double posX, double posY, double posZ, float yaw, float pitch) {
        super(worldIn);
        this.setSize(4.0F, 4.0F);
        this.setLocationAndAngles(posX, posY, posZ, yaw, pitch);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
        this.noClip = true;
	}



	/**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.ignoreFrustumCheck = true;
        
        if (this.worldObj.isRemote){
        	
        }
        
    }



	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub
		
	}





    


    


}