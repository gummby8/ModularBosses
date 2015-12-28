package com.Splosions.ModularBosses.client.entity;


import net.minecraft.util.DamageSource;
import net.minecraft.world.World;


public interface MBEntityMultiPart
{
    World getWorld();

    boolean attackEntityFromPart(MBEntityPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_);
}