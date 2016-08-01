package com.Splosions.ModularBosses.entity;

import java.util.Random;

import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class EntitySandWormBodySegment  extends EntityMob
{
    /** The explosion radius of spawned fireballs. */
    private int explosionStrength = 1;
    private static final String __OBFID = "CL_00001689";
    
    public boolean TargetLocked;

    public EntitySandWormBodySegment(World worldIn)
    {
        super(worldIn);
        this.setSize(4.0F, 4.0F);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.ignoreFrustumCheck = true;
    }



    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        this.ignoreFrustumCheck = true;
        
        
        
        
        
        
        
    }









    


    


}