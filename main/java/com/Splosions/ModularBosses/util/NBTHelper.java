package com.Splosions.ModularBosses.util;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class NBTHelper {
    public static List<TileEntity> readTileEntitiesFromCompound(final NBTTagCompound compound) {
        return readTileEntitiesFromCompound(compound, new ArrayList<TileEntity>());
    }

    public static List<TileEntity> readTileEntitiesFromCompound(final NBTTagCompound compound, final List<TileEntity> tileEntities) {
        final NBTTagList tagList = compound.getTagList("TileEntities", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.tagCount(); i++) {
            final NBTTagCompound tileEntityCompound = tagList.getCompoundTagAt(i);
            final TileEntity tileEntity = readTileEntityFromCompound(tileEntityCompound);
            tileEntities.add(tileEntity);
        }

        return tileEntities;
    }

 
    public static TileEntity readTileEntityFromCompound(final NBTTagCompound tileEntityCompound) {
        // TODO: world should NOT be null...
        return TileEntity.create(null, tileEntityCompound);
    }




}