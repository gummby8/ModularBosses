package com.Splosions.ModularBosses.entity;

import java.util.Random;

import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.Splosions.ModularBosses.util.schematic.Room;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class EntitySandWorm  extends EntityMob
{
    
    
	public Entity[] bodySegments = new Entity[10];
    

    public EntitySandWorm(World worldIn)
    {
        super(worldIn);
        this.setSize(4.0F, 4.0F);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
        this.ignoreFrustumCheck = true;
        this.enablePersistence();
    }


    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        this.ignoreFrustumCheck = true;
        
        if (this.ticksExisted == 1 && !this.worldObj.isRemote){
			for (int x = 0; x < bodySegments.length; x++) {
				float offset = (x == 0)? 10 : (x * 10);
				bodySegments[x] = (x == bodySegments.length)? new EntitySandWormTail(worldObj, this.posX, this.posY, this.posZ - offset, this.rotationYaw, this.rotationPitch) : new EntitySandWormBody(worldObj, this.posX, this.posY, this.posZ - offset, this.rotationYaw, this.rotationPitch);
				this.worldObj.spawnEntityInWorld(bodySegments[x]);
			}
        }
        
        

        
        
        
        
        
    }









    


    


}