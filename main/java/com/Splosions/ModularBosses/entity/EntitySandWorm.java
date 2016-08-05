package com.Splosions.ModularBosses.entity;

import java.util.Random;

import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.Splosions.ModularBosses.util.schematic.Room;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class EntitySandWorm  extends Entity
{

	
	public Entity[] bodySegments;
    

    public EntitySandWorm(World worldIn)
    {
        super(worldIn);
        this.setSize(4.0F, 4.0F);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
        this.noClip = true;
    }
    
    



    
    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate(){
        super.onUpdate();
        
        this.ignoreFrustumCheck = true;
        if (bodySegments == null && !this.worldObj.isRemote){
        	bodySegments = new Entity[10];
			for (int x = 0; x < bodySegments.length; x++) {
				bodySegments[x] = (x + 1 == bodySegments.length)? new EntitySandWormTail(worldObj, this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch) : new EntitySandWormBody(worldObj, this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
				this.worldObj.spawnEntityInWorld(bodySegments[x]);
				System.out.println(x);
			}
        }
        
        //this.rotationYaw = 0;
        //this.rotationPitch = 0;  

        //this.rotationYaw++;
        this.rotationPitch++;
        
        if (!this.worldObj.isRemote && this.ticksExisted > 10){
        	
        float spacing = 8;
       
           	PseudoChild(this,spacing,bodySegments[0]);
        	PseudoChild(bodySegments[0],spacing,bodySegments[1]);
        	PseudoChild(bodySegments[1],spacing,bodySegments[2]);
        	PseudoChild(bodySegments[2],spacing,bodySegments[3]);
        	PseudoChild(bodySegments[3],spacing,bodySegments[4]);
        	PseudoChild(bodySegments[4],spacing,bodySegments[5]);
        	PseudoChild(bodySegments[5],spacing,bodySegments[6]);
        	PseudoChild(bodySegments[6],spacing,bodySegments[7]);
        	PseudoChild(bodySegments[7],spacing,bodySegments[8]);
        	System.out.println("Head Before = " + bodySegments[9].rotationPitch);
        	PseudoChild(bodySegments[8],spacing,bodySegments[9]);
        	System.out.println("Head After = " + bodySegments[9].rotationPitch);
        	//dead();
        }
        
    }


    
    public void dead(){
    	for (int x = 0; x < bodySegments.length; x++) {
    		bodySegments[x].setDead();
    	}
    	setDead();
    }

    
    
    

	public void PseudoChild(Entity parent, float length, Entity child) {
		
		child.prevPosX = child.posX;
		child.prevPosY = child.posY;
		child.prevPosZ = child.posZ;
		
		child.prevRotationPitch = child.rotationPitch;
		child.prevRotationYaw = child.rotationYaw;
		
		float parentYaw = (parent.rotationYaw + 90) * 0.0174533F;
		float parentPitch = (parent.rotationPitch) * 0.0174533F;
		
		double x = ((length * MathHelper.cos(parentPitch) * MathHelper.cos(parentYaw)) + parent.posX);
		double y = ((length * MathHelper.sin(parentPitch)) + parent.posY);
		double z = (length * MathHelper.cos(parentPitch) * MathHelper.sin(parentYaw)) + parent.posZ;

		float yaw = child.rotationYaw + ((parent.rotationYaw - child.rotationYaw) * 0.1F);
		float pitch = child.rotationPitch + ((parent.rotationPitch - child.rotationPitch) * 0.1F);

		child.setLocationAndAngles(x, y, z, yaw, pitch);
	}




	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub
		
	}






	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}



    


    


}