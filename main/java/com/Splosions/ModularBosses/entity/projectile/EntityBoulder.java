package com.Splosions.ModularBosses.entity.projectile;

import java.util.Iterator;
import java.util.List;

import com.Splosions.ModularBosses.Sounds;
import com.Splosions.ModularBosses.entity.EntityGolem;
import com.Splosions.ModularBosses.entity.player.MBExtendedPlayer;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBoulder extends EntityMobThrowable implements IEntityAdditionalSpawnData {

	private EntityGolem shooter;
	public ResourceLocation textureLoc;
	public int textureBlockID;

	public EntityBoulder(World world) {
		super(world);
	}

	public EntityBoulder(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityBoulder(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityBoulder(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity, float wobble, float FrontToBack, float YOffset, float SideToSide, float Size1, float Size2) {
		super(world, shooter, target, velocity, wobble, FrontToBack, YOffset, SideToSide, Size1, Size2);

		this.shooter = (EntityGolem) shooter;
		try {
			textureBlockID = this.shooter.textureBlockID;
		} catch (Exception e) {
			this.setDead();
			return;
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		this.collideWithEntities(this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox()));

		if (this.worldObj.isRemote) {
			for (int x = 0; x < 15; x++) {
				this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, Math.random() / 5, Math.random() / 5, Math.random() / 5, textureBlockID);
			}
		}
	}

	private void collideWithEntities(List list) {
		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			Entity entity = (Entity) iterator.next();
			if (entity.hurtResistantTime == 0 && entity instanceof EntityPlayer) {
	            MBExtendedPlayer.get((EntityPlayer) entity).knockdownTime = 60;
				entity.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			}
		}

		if (!list.isEmpty()) {
			for (int x = 0; x < 40; x++) {
				this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, Math.random(), Math.random(), Math.random(), textureBlockID);
			}
			this.playSound(Sounds.BOULDER_HIT, 1F, 1.0F);
			this.setDead();
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		ByteBufUtils.writeVarInt(buffer, textureBlockID, 4);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		textureBlockID = ByteBufUtils.readVarInt(additionalData, 4);
		IBlockState iblockstate = Block.getStateById(textureBlockID);
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel ibakedmodel = blockrendererdispatcher.getModelFromBlockState(iblockstate, this.worldObj, new BlockPos(0,0,0));
		String string = ibakedmodel.getTexture().getIconName() + ".png";
		String[] parts = string.split(":");
		textureLoc = new ResourceLocation(parts[0] + ":textures/" + parts[1]);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		textureBlockID = compound.getInteger("blockID");
		IBlockState iblockstate = Block.getStateById(textureBlockID);
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		IBakedModel ibakedmodel = blockrendererdispatcher.getModelFromBlockState(iblockstate, this.worldObj, new BlockPos(0,0,0));
		String string = ibakedmodel.getTexture().getIconName() + ".png";
		String[] parts = string.split(":");
		textureLoc = new ResourceLocation(parts[0] + ":textures/" + parts[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("blockID", textureBlockID);

	}

	@Override
	protected void onImpact(MovingObjectPosition p_70184_1_) {
		// TODO Auto-generated method stub

	}

}