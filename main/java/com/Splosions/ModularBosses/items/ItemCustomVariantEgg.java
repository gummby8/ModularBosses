package com.Splosions.ModularBosses.items;

import java.util.List;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;
import com.Splosions.ModularBosses.entity.CustomEntityList;
import com.Splosions.ModularBosses.entity.IEntityVariant;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * Spawn Eggs for custom entities with subtypes and implementing {@link IEntityVariant};
 * entities with only one variety should use a more vanilla-like style of spawner Item.
 * Unlike the vanilla spawn eggs, the Entity class to be spawned is stored as a field in the
 * Item instance, and each Entity has its own Item; item damage is used as the Entity sub-type. 
 * Uses the vanilla egg icons so that the egg styles always match, regardless of resource pack.
 *
 */
public class ItemCustomVariantEgg extends ItemCustomEgg
{
	/** The class of Entity that will be spawned */
	private final Class<? extends Entity> classToSpawn;

	/** The unlocalized entity name, retrieved as "entity.{entityName}.name" and suffixed with ".n", where 'n' is the subtype index */
	private final String entityName;

	public ItemCustomVariantEgg(Class<? extends Entity> classToSpawn, String entityName) {
		super();
		this.classToSpawn = classToSpawn;
		this.entityName = entityName;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String s = ("" + StatCollector.translateToLocal("item.mb.spawn_egg.name")).trim();
		if (entityName != null) {
			s = s + " " + StatCollector.translateToLocal("entity." + Reference.MOD_ID + "." + entityName + ".name." + stack.getItemDamage());
		}
		return s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		List<Integer> colors = CustomEntityList.entityEggs.get(classToSpawn);
		int colorIndex = stack.getItemDamage() * 2;
		return colors != null && colors.size() > colorIndex + 1 ? colors.get((renderPass == 0 ? colorIndex : colorIndex + 1)) : 16777215;
	}

	@Override
	public Entity spawnCreature(World world, int subtype, double x, double y, double z) {
		Entity entity = null;
		if (CustomEntityList.entityEggs.containsKey(classToSpawn)) {
			entity = CustomEntityList.createEntity(classToSpawn, world);
			if (entity instanceof EntityLiving) {
				EntityLiving entityliving = (EntityLiving) entity;
				entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
				entityliving.rotationYawHead = entityliving.rotationYaw;
				entityliving.renderYawOffset = entityliving.rotationYaw;
				//entityliving.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entityliving)), null);
				if (entity instanceof IEntityVariant) {
					((IEntityVariant) entity).setType(subtype);
				}
				world.spawnEntityInWorld(entity);
				entityliving.playLivingSound();
			}
		}

		return entity;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List itemList) {
		List<Integer> colors = CustomEntityList.entityEggs.get(classToSpawn);
		if (colors == null || colors.isEmpty()) {
			ModularBosses.logger.error("Custom entity egg has an empty color list");
			return;
		} else if (colors.size() % 2 != 0) {
			ModularBosses.logger.error("Custom entity egg has an odd number of colors");
		}
		for (int i = 0; i < (colors.size() / 2); ++i) {
			itemList.add(new ItemStack(item, 1, i));
		}
	}
}