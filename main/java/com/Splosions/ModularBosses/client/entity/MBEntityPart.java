package com.Splosions.ModularBosses.client.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class MBEntityPart extends Entity
{
    /** The dragon entity this dragon part belongs to */
    public final MBEntityMultiPart entityObj;
    public final String partName;
    private static final String __OBFID = "CL_00001657";
    
    public double PPosX = 0;
    public double PPosY = 0;
    public double PPosZ = 0;

    public MBEntityPart(MBEntityMultiPart parent, String partName, float base, float sizeHeight)
    {
        super(parent.getWorld());
        this.setSize(base, sizeHeight);
        this.entityObj = parent;
        this.partName = partName;
    }

    protected void entityInit() {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {}

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {}

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return this.entityObj.attackEntityFromPart(this, source, amount);
    }

    /**
     * Returns true if Entity argument is equal to this Entity
     */
    public boolean isEntityEqual(Entity entityIn)
    {
        return this == entityIn || this.entityObj == entityIn;
    }
}