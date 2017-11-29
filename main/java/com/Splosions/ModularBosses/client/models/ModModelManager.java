package com.Splosions.ModularBosses.client.models;


import com.Splosions.ModularBosses.blocks.ModFluids;
import com.Splosions.ModularBosses.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public class ModModelManager {
	public static final ModModelManager INSTANCE = new ModModelManager();

	private static final String FLUID_MODEL_PATH = Reference.RESOURCE_PREFIX + "fluid";

	private ModModelManager() {
	}

	@SubscribeEvent
	public void registerAllModels(ModelRegistryEvent event) {
		INSTANCE.registerFluidModels();

	}

	private void registerFluidModels() {
		ModFluids.MOD_FLUID_BLOCKS.forEach(this::registerFluidModel);
	}

	private void registerFluidModel(IFluidBlock fluidBlock) {
		Item item = Item.getItemFromBlock((Block) fluidBlock);
		ModelBakery.registerItemVariants(item);

		final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(FLUID_MODEL_PATH, fluidBlock.getFluid().getName());

		ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelResourceLocation));

		ModelLoader.setCustomStateMapper((Block) fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
				return modelResourceLocation;
			}
		});
	}
}