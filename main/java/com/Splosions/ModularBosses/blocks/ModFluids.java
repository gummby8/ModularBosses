package com.Splosions.ModularBosses.blocks;

import com.Splosions.ModularBosses.ModularBosses;
import com.Splosions.ModularBosses.Reference;
import com.Splosions.ModularBosses.blocks.tileentity.TileEntityTempWormBlood;

import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;


public class ModFluids {



	/**
	 * The fluids registered by this mod. Includes fluids that were already registered by another mod.
	 */
	public static final Set<Fluid> FLUIDS = new HashSet<>();

	/**
	 * The fluid blocks from this mod only. Doesn't include blocks for fluids that were already registered by another mod.
	 */
	public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();
	
	public static final Fluid FLUID_WORM_BLOOD = createFluid("fluid_worm_blood", true,
			fluid -> fluid.setLuminosity(10).setDensity(1500).setViscosity(1),
			fluid -> new FluidWormBlood(fluid, new MaterialLiquid(MapColor.ADOBE)));

	public static final Fluid FLUID_TEMP_WORM_BLOOD = createFluid("fluid_temp_worm_blood", true,
			fluid -> fluid.setLuminosity(10).setDensity(1500).setViscosity(1),
			fluid -> new FluidTempWormBlood(fluid, new MaterialLiquid(MapColor.ADOBE)));

	public static final Fluid FLUID_WORM_ACID = createFluid("fluid_worm_acid", true,
			fluid -> fluid.setLuminosity(10).setDensity(1500).setViscosity(1),
			fluid -> new FluidWormAcid(fluid, new MaterialLiquid(MapColor.ADOBE)));

	public static final Fluid FLUID_TEMP_WORM_ACID = createFluid("fluid_temp_worm_acid", true,
			fluid -> fluid.setLuminosity(10).setDensity(1500).setViscosity(1),
			fluid -> new FluidTempWormAcid(fluid, new MaterialLiquid(MapColor.ADOBE)));

	public static final Fluid FLUID_WORM_SALIVA = createFluid("fluid_worm_saliva", true,
			fluid -> fluid.setLuminosity(200).setDensity(1500).setViscosity(1),
			fluid -> new FluidWormSaliva(fluid, new MaterialLiquid(MapColor.ADOBE)));
	
	public static final Fluid GAS_WORM_GAS = createFluid("gas_worm_gas", true,
			fluid -> fluid.setLuminosity(10).setDensity(-10000).setViscosity(100).setGaseous(true),
			fluid -> new GasWormGas(fluid, new MaterialLiquid(MapColor.ADOBE)));
	



	/**
	 * Create a {@link Fluid} and its {@link IFluidBlock}, or use the existing ones if a fluid has already been registered with the same name.
	 *
	 * @param name                 The name of the fluid
	 * @param hasFlowIcon          Does the fluid have a flow icon?
	 * @param fluidPropertyApplier A function that sets the properties of the {@link Fluid}
	 * @param blockFactory         A function that creates the {@link IFluidBlock}
	 * @return The fluid and block
	 */
	private static <T extends Block & IFluidBlock> Fluid createFluid(final String name, final boolean hasFlowIcon, final Consumer<Fluid> fluidPropertyApplier, final Function<Fluid, T> blockFactory) {
		final String texturePrefix = Reference.MOD_ID + ":" + "blocks/fluid_";

		final ResourceLocation still = new ResourceLocation(texturePrefix + name + "_still");
		final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(texturePrefix + name + "_flow") : still;

		Fluid fluid = new Fluid(name, still, flowing);
		final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

		if (useOwnFluid) {
			fluidPropertyApplier.accept(fluid);
			MOD_FLUID_BLOCKS.add(blockFactory.apply(fluid));
		} else {
			fluid = FluidRegistry.getFluid(name);
		}

		FLUIDS.add(fluid);

		return fluid;
	}

	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	public static class RegistrationHandler {

		/**
		 * Register this mod's fluid {@link Block}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
				final Block block = (Block) fluidBlock;
				block.setRegistryName(Reference.MOD_ID, "fluid." + fluidBlock.getFluid().getName());
				block.setUnlocalizedName(Reference.MOD_ID + ":" + fluidBlock.getFluid().getUnlocalizedName());
				block.setCreativeTab(ModularBosses.tabBlocks);
				registry.register(block);
			}
		}

		/**
		 * Register this mod's fluid {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		// Use EventPriority.LOWEST so this is called after the RegistryEvent.Register<Item> handler in ModBlocks where
		// the ItemBlock for ModBlocks.FLUID_TANK is registered.
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
				final Block block = (Block) fluidBlock;
				final ItemBlock itemBlock = new ItemBlock(block);
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName());
				itemBlock.setRegistryName(registryName);
				registry.register(itemBlock);
			}
		}
	}
}