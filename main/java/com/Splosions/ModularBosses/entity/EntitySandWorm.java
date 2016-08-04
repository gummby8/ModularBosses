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
    }


    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        super.onUpdate();
     
        this.ignoreFrustumCheck = true;
        if (bodySegments == null && !this.worldObj.isRemote){
        	bodySegments = new Entity[10];
			for (int x = 0; x < bodySegments.length; x++) {
				bodySegments[x] = (x + 1 == bodySegments.length)? new EntitySandWormTail(worldObj, this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch) : new EntitySandWormBody(worldObj, this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
				this.worldObj.spawnEntityInWorld(bodySegments[x]);
			}
        }
        System.out.println("Pitch = " + this.rotationPitch);
        

        
        if (!this.worldObj.isRemote){
        	
        float spacing = 15;
        this.rotationYaw++;
        this.rotationPitch++;
        
           	PseudoChild(this,spacing,bodySegments[0]);
        	PseudoChild(bodySegments[0],spacing,bodySegments[1]);
        	PseudoChild(bodySegments[1],spacing,bodySegments[2]);
        	PseudoChild(bodySegments[2],spacing,bodySegments[3]);
        	PseudoChild(bodySegments[3],spacing,bodySegments[4]);
        	PseudoChild(bodySegments[4],spacing,bodySegments[5]);
        	PseudoChild(bodySegments[5],spacing,bodySegments[6]);
        	PseudoChild(bodySegments[6],spacing,bodySegments[7]);
        	PseudoChild(bodySegments[7],spacing,bodySegments[8]);
        	PseudoChild(bodySegments[8],spacing,bodySegments[9]);
        }

        
        
        
        
        
    }



	public void PseudoChild(Entity Parent, float ParentLength, Entity Child) {
		float parentYaw = (float) Math.toRadians(Parent.rotationYaw);
		float parentPitch = (float) Math.toRadians(Parent.rotationPitch);
		
		double x = ((ParentLength * MathHelper.cos(parentPitch) * MathHelper.cos(parentYaw)) + Parent.posX);
		double y = ((ParentLength * MathHelper.sin(parentPitch)) + Parent.posY);
		double z = (ParentLength * MathHelper.cos(parentPitch) * MathHelper.sin(parentYaw)) + Parent.posZ;

		Child.rotationYaw = Child.rotationYaw + ((Parent.rotationYaw - Child.rotationYaw) * 0.25F);
		Child.rotationPitch = Child.rotationPitch + ((Parent.rotationPitch - Child.rotationPitch) * 0.25F);

		Child.setPositionAndUpdate(x, y, z);
		

	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
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