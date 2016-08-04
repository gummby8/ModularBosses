package com.Splosions.ModularBosses.entity;

import java.util.Random;

import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class EntitySandWormBody  extends EntityMob
{
    
    public boolean TargetLocked;

    public EntitySandWormBody(World worldIn)
    {
        super(worldIn);
        this.setSize(4.0F, 4.0F);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
        this.ignoreFrustumCheck = true;
        this.enablePersistence();
    }
    

    public EntitySandWormBody(World worldIn, double posX, double posY, double posZ, float yaw, float pitch)
    {
        super(worldIn);
        this.setSize(4.0F, 4.0F);
        this.setLocationAndAngles(posX, posY, posZ, yaw, pitch);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
        this.ignoreFrustumCheck = true;
        this.enablePersistence();
    }



	/**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.ignoreFrustumCheck = true;
    }



	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}





    


    


}