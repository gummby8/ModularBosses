package com.Splosions.ModularBosses.items;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * 
 * Same as {@link BaseModItem}, but with an ItemSword base.
 *
 */
public class BaseModSword extends ItemSword implements IModItem {

	public BaseModSword(ToolMaterial material) {
		super(material);
		setCreativeTab(ModularBosses.tabTools);
	}

	@Override
	public String[] getVariants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerVariants() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRenderers(ItemModelMesher mesher) {
		// TODO Auto-generated method stub
		
	}

}
