package com.Splosions.ModularBosses.items;

import com.Splosions.ModularBosses.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * 
 * Default ItemBlock class implementing IModItem
 *
 */
public class ItemModBlock extends ItemBlock implements IModItem
{
	private final String[] variants;

	/**
	 * At least one variant needs to be registered at some point
	 */
	public ItemModBlock(Block block) {
		super(block);
		this.variants = null;
	}

	/**
	 * Standard ItemBlock constructor with optional variant names
	 */
	public ItemModBlock(Block block, String... variants) {
		super(block);
		this.variants = variants;
		if (variants.length > 1) {
			setMaxDamage(0);
			setHasSubtypes(true);
		}
	}

	/**
	 * Returns "tile.mb.unlocalized_name" for translation purposes
	 */
	@Override
	public String getUnlocalizedName() {
		return super.getUnlocalizedName().replaceFirst("tile.", "tile.mb.");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName();
	}



	/**
	 * Default implementation suggested by {@link IModItem#registerVariants()}
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerVariants() {
		String[] variants = getVariants();
		if (variants != null) {
			ModelBakery.addVariantName(this, variants);
		}
	}

	/**
	 * Register all of this Item's renderers here, including for any subtypes.
	 * Default behavior registers a single inventory-based mesher for each variant
	 * returned by {@link #getVariants() getVariants}.
	 * If no variants are available, "mod_id:" plus the item's unlocalized name is used.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderers(ItemModelMesher mesher) {
		String[] variants = getVariants();
		if (variants == null || variants.length < 1) {
			String name = getUnlocalizedName();
			variants = new String[]{Reference.MOD_ID + ":" + name.substring(name.lastIndexOf(".") + 1)};
		}
		for (int i = 0; i < variants.length; ++i) {
			mesher.register(this, i, new ModelResourceLocation(variants[i], "inventory"));
		}
	}

	@Override
	public String[] getVariants() {
		// TODO Auto-generated method stub
		return null;
	}
}
