package com.Splosions.ModularBosses.items;

import java.util.Iterator;
import java.util.List;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;
import com.Splosions.ModularBosses.entity.CustomEntityList;
import com.Splosions.ModularBosses.entity.IEntityVariant;
import com.Splosions.ModularBosses.entity.projectile.EntityCustomEgg;
import com.Splosions.ModularBosses.items.dispenser.BehaviorDispenseCustomMobEgg;

import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * 
 * Spawn eggs for custom mod entities, using {@link CustomEntityList#addMapping(Class, String, Integer...) CustomEntityList.addMapping}
 * to add the entity ID mapping to the custom egg list. Entities that implement {@link IEntityVariant}
 * should use {@link ItemCustomVariantEgg} instead. 
 *
 */
public class ItemCustomEgg extends BaseModItem implements ICustomDispenserBehavior,IItemColor
{
	public ItemCustomEgg() {
		super();
		setHasSubtypes(true);
		setCreativeTab(ModularBosses.tabEggs);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String s = ("" + I18n.translateToLocal("item.mb.spawn_egg.name")).trim();
		String entityName = CustomEntityList.getStringFromID(stack.getItemDamage());
		if (entityName != null) {
			s = s + " " + I18n.translateToLocal("entity." + Reference.MOD_ID + "." + entityName + ".name");
		}
		return s;
	}





	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode)
        {
        	stack.shrink(1);
        }
			Entity projectile = new EntityCustomEgg(worldIn, playerIn, playerIn, 1.0F, 0,0,0,0,0,0,stack.getItemDamage());
			worldIn.spawnEntity(projectile);
			
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}


	/**
	 * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
	 */
	public Entity spawnCreature(World world, int entityID, double x, double y, double z) {
		Entity entity = null;
		Class<? extends Entity> oclass = CustomEntityList.getClassFromID(entityID);
		if (CustomEntityList.entityEggs.containsKey(oclass)) {
			entity = CustomEntityList.createEntity(oclass, world);
			if (entity instanceof EntityLiving) {
				EntityLiving entityliving = (EntityLiving) entity;
				entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
				entityliving.rotationYawHead = entityliving.rotationYaw;
				entityliving.renderYawOffset = entityliving.rotationYaw;
				world.spawnEntity(entity);
				entityliving.playLivingSound();
			} else {
				entity.setPosition(x, y, z);
				world.spawnEntity(entity);
			}
		}
		return entity;
	}


	@Override
	public String[] getVariants() {
		return new String[]{"minecraft:spawn_egg"}; // prevent 'missing location' error
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> itemList){
		Iterator<Class<? extends Entity>> iterator = CustomEntityList.entityEggs.keySet().iterator();
		while (iterator.hasNext()) {
			Class<? extends Entity> oclass = iterator.next();
			List<Integer> colors = CustomEntityList.entityEggs.get(oclass);
			if (colors != null && colors.size() == 2) {
				itemList.add(new ItemStack(this, 1, CustomEntityList.getEntityId(oclass)));
			}
		}
	}

	/**
	 * Register same base texture for each egg subtype
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderers(ItemModelMesher mesher) {
		mesher.register(this, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("minecraft:spawn_egg", "inventory");
			}
		});
	}

	@Override
	public IBehaviorDispenseItem getNewDispenserBehavior() {
		return new BehaviorDispenseCustomMobEgg();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemstack(ItemStack stack, int renderPass) {
		List<Integer> colors = CustomEntityList.entityEggs.get(CustomEntityList.getClassFromID(stack.getItemDamage()));
		return colors != null && colors.size() > 1 ? colors.get((renderPass == 0 ? 0 : 1)) : 16777215;
	}
}