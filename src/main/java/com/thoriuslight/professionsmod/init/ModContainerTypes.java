package com.thoriuslight.professionsmod.init;

import com.thoriuslight.professionsmod.ProfessionsMod;
import com.thoriuslight.professionsmod.inventory.container.WoodenHopperContainer;
import com.thoriuslight.professionsmod.inventory.container.SmithCraftingContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, ProfessionsMod.MODID);
	
	public static final RegistryObject<ContainerType<WoodenHopperContainer>> WOODEN_HOPPER = CONTAINER_TYPES.register("wooden_hopper", () -> IForgeContainerType.create(WoodenHopperContainer::new));
	public static final RegistryObject<ContainerType<SmithCraftingContainer>> SMITH_CRAFTING = CONTAINER_TYPES.register("smith_crafting", () -> IForgeContainerType.create(SmithCraftingContainer::new));
}
