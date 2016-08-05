package com.Splosions.ModularBosses.entity;

import java.util.Random;

import com.Splosions.ModularBosses.entity.projectile.EntityScythe;
import com.Splosions.ModularBosses.util.schematic.Room;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class EntitySandWorm  extends Entity implements IEntityAdditionalSpawnData
{

	
	public Entity[] bodySegments;
    public int entIDs[] = new int[10];

    public EntitySandWorm(World worldIn)
    {
        super(worldIn);
        this.setSize(4.0F, 4.0F);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
        this.noClip = true;
        if (!this.worldObj.isRemote) {
        	bodySegments = new Entity[10];
			for (int x = 0; x < bodySegments.length; x++) {
				bodySegments[x] = (x + 1 == bodySegments.length)? new EntitySandWormTail(worldIn, this.posX, this.posY, this.posZ, this.rotationYaw,this.rotationPitch): new EntitySandWormBody(worldIn, this.posX, this.posY, this.posZ, this.rotationYaw,this.rotationPitch);
				System.out.println("Creating ID " + bodySegments[x].getEntityId() + " on server");
				entIDs[x] = bodySegments[x].getEntityId();
			}
		}
        
    }
    
    



    
    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate(){
        super.onUpdate();
        
        
		if (this.ticksExisted == 1 && !this.worldObj.isRemote) {
			// bodySegments = new Entity[10];
			for (int x = 0; x < bodySegments.length; x++) {
				bodySegments[x].setPosition(this.posX, this.posY, this.posZ);
				this.worldObj.spawnEntityInWorld(bodySegments[x]);
				System.out.println("Spawning ID " + bodySegments[x].getEntityId() + " on server");
				entIDs[x] = bodySegments[x].getEntityId();
			}
		}

        

        
        //this.rotationYaw = 0;
        //this.rotationPitch = 0;  
        //this.rotationYaw++;
        this.rotationPitch++;
        
        if (this.ticksExisted > 60){
        	if (this.worldObj.isRemote && bodySegments == null){
        		bodySegments = new Entity[10];
        		for (int x = 0; x < bodySegments.length; x++) {
        			bodySegments[x] = this.worldObj.getEntityByID(entIDs[x]);
        		}
        	}

            
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
        	PseudoChild(bodySegments[8],spacing,bodySegments[9]);

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






	@Override
	public void writeSpawnData(ByteBuf buffer) {
		for (int x = 0; x < entIDs.length; x++) {
			buffer.writeInt(entIDs[x]);	
			System.out.println("Writing ID " + entIDs[x] + " on server");
		}
		
		
	}






	@Override
	public void readSpawnData(ByteBuf additionalData) {
		for (int x = 0; x < entIDs.length; x++) {
			entIDs[x] = additionalData.readInt();
			System.out.println("Pulling ID " + entIDs[x] + " on client");
		}
		
		
	}



    


    


}