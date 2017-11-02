package com.Splosions.ModularBosses.entity;

import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;



public class EntitySandWormTail  extends Entity implements IEntityAdditionalSpawnData {
	
    public int segmentNum;
    public Entity parent;
    
	private static final int YAW_WATCHER = 17;
	private static final int PITCH_WATCHER = 18;
	
	public float yaw;
	public float pitch;

    public EntitySandWormTail(World worldIn)
    {
        super(worldIn);
        this.setSize(10F, 10F);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
    }



    public EntitySandWormTail(World worldIn, double posX, double posY, double posZ, float yaw, float pitch, int segmentNum, Entity parent) {
        super(worldIn);
        this.setSize(15F, 15F);
        this.setPositionAndUpdate(posX, posY, posZ);
        this.yaw = yaw;
        this.pitch = pitch;
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
        this.noClip = true;
        this.segmentNum = segmentNum;
        this.parent = parent;
        
        
	}

	/**
	 * Attacks all entities inside this list, dealing 5 hearts of damage.
	 */
	private void kickEntitiesInList(List par1List, double force, double height, float dmg) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			double d0 = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
			double d1 = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
			double d2 = entity.posX - d0;
			double d3 = entity.posZ - d1;
			double d4 = d2 * d2 + d3 * d3;

			entity.addVelocity(d2 / d4 * force, height, d3 / d4 * force);
		}

	}

	/**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
    	
		List teleList = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(10, 10, 10));
		kickEntitiesInList(teleList, 3, 3, 20);
		
        //super.onUpdate();
    	this.noClip = true;
        this.ignoreFrustumCheck = true;

        if (this.worldObj.isRemote){
        	if (this.parent == null){
        		setDead();
        	} else if (this.parent.isDead) {
        		setDead();
        	}
        }

    }

	@Override
    protected void doBlockCollisions()
    {

    }
    

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(YAW_WATCHER, 0.0F);
		this.dataWatcher.addObject(PITCH_WATCHER, 0.0F);
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
	public void writeSpawnData(ByteBuf buffer) {
			buffer.writeInt(this.segmentNum);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		this.segmentNum = additionalData.readInt();
	}





    


    


}