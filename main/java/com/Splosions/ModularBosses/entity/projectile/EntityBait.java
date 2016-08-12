package com.Splosions.ModularBosses.entity.projectile;

import java.util.List;

import com.Splosions.ModularBosses.entity.CustomEntityList;
import com.Splosions.ModularBosses.items.ItemCustomEgg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBait extends EntityThrowable
{

	private int dmg;

	public EntityBait(World world) {
		super(world);
	}

	public EntityBait(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityBait(World world, EntityLivingBase shooter) {
		super(world, shooter);
		
	}
	


	

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		this.motionX = this.motionY = this.motionZ = 0;
	}


}