
package com.Splosions.ModularBosses.items;

import com.Splosions.ModularBosses.Reference;
import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * Can be used for any generic block with metadata subtypes
 * If block implements IBlockVariant, those variant names will be used
 *
 */
public class ItemMetadataBlock extends ItemMultiTexture implements IModItem
{
	/**
	 * Default constructor with no special naming scheme
	 */
	public ItemMetadataBlock(Block block) {
		this(block, new Function<ItemStack, String>() {
			@Override
			public String apply(ItemStack stack) {
				return "";
			}
		});
	}

	/**
	 * @param nameFunction Function to determine unlocalized name
	 */
	public ItemMetadataBlock(Block block, Function<ItemStack, String> nameFunction) {
		super(block, block, nameFunction);
	}

	/**
	 * @param namesByMeta Array of names used to create a lookup-based name function
	 */
	public ItemMetadataBlock(Block block, final String[] namesByMeta) {
		super(block, block, namesByMeta);
	}

	/**
	 * Returns "tile.zss.unlocalized_name" for translation purposes
	 */
	@Override
	public String getUnlocalizedName() {
		return super.getUnlocalizedName().replaceFirst("tile.", "tile.zss.");
	}

	/**
	 * Override ItemMultiTexture's to allow for variants with no custom naming scheme
	 */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String extra = (String) nameFunction.apply(stack);
		return getUnlocalizedName() + (extra == null || extra.length() < 1 ? "" : "." + extra);
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
	 * Default behavior registers a renderer for each variant
	 * If no variants are returned by {@link #getVariants()}, the unlocalized name is used instead.
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