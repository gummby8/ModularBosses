package com.Splosions.ModularBosses.client;

import java.util.Collection;

import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * Allows automating the {@link ModelBakeEvent} for both Blocks and Items
 *
 */
@SuppressWarnings("deprecation")
public interface ISwapModel {

	/**
	 * Return the default resource locations used to retrieve this object from the model registry
	 */
	@SideOnly(Side.CLIENT)
	Collection<ModelResourceLocation> getDefaultResources();

	/**
	 * Return the class used for the new model
	 * The class must have a constructor that takes a single IBakedModel argument
	 */
	@SideOnly(Side.CLIENT)
	Class<? extends IBakedModel> getNewModel();
}