package com.Splosions.ModularBosses.items;


import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * Interface to improve encapsulation and allow automated registration of variants and renderers
 *
 */
public interface IModItem {

	/**
	 * Returns an array of variant names to be used in {@link #registerVariants()} and possible {@link #registerRenderers(ItemModelMesher) registerRenderer()} 
	 * Typical variant name is "mod_id:" plus the item's unlocalized name, minus any leading prefixes (e.g. 'item.')
	 * @return Return null if there are no variants (e.g. standard generic item)
	 */
	String[] getVariants();

	/**
	 * Register any item variant names here using {@link ModelBakery#addVariantName}
	 * This MUST be called during {@code FMLPreInitializationEvent}
	 * 
	 * Typical implementation taking advantage of {@link #getVariants()}:
	 * 
	 *	String[] variants = getVariants();
	 *	if (variants != null) { // allows for alternate single variants, such as "minecraft:spawn_egg"
	 *		ModelBakery.addVariantName(this, variants);
	 *	}
	 */
	@SideOnly(Side.CLIENT)
	void registerVariants();

	/**
	 * Register all of the Item's renderers here, including for any subtypes.
	 * This MUST be called during {@code FMLInitializationEvent}
	 * 
	 * A default implementation to register an item with modid:itemname would be:
	 *
	 *	String name = getUnlocalizedName();
	 *	name = YourMod.MODID + ":" + name.substring(name.lastIndexOf(".") + 1);
	 *	mesher.register(this, 0, new ModelResourceLocation(name, "inventory"));
	 */
	@SideOnly(Side.CLIENT)
	void registerRenderers(ItemModelMesher mesher);

}