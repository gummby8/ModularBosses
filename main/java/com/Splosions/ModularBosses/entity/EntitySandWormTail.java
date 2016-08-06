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
    public int segmentNum;
    public Entity parent;

    public EntitySandWormTail(World worldIn)
    {
        super(worldIn);
        this.setSize(18.0F, 18.0F);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
    }



    public EntitySandWormTail(World worldIn, double posX, double posY, double posZ, float yaw, float pitch, int segmentNum, Entity parent) {
        super(worldIn);
        this.setSize(18.0F, 18.0F);
        this.setLocationAndAngles(posX, posY, posZ, yaw, pitch);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
        this.noClip = true;
        this.segmentNum = segmentNum;
        this.parent = parent;
	}



	/**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        //super.onUpdate();
        this.ignoreFrustumCheck = true;

        if (!this.worldObj.isRemote){
        	if (this.parent == null){
        		setDead();
        	} else if (this.parent.isDead) {
        		setDead();
        	}
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