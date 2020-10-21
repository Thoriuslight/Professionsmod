package com.thoriuslight.professionsmod.init;

import com.thoriuslight.professionsmod.ProfessionsMod;
import com.thoriuslight.professionsmod.tileentity.CastingBasinTileEntity;
import com.thoriuslight.professionsmod.tileentity.StoneAnvilTileEntity;
import com.thoriuslight.professionsmod.tileentity.WoodenHopperTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, ProfessionsMod.MODID);
	//Smithing
	public static final RegistryObject<TileEntityType<StoneAnvilTileEntity>> STONEANVIL = TILE_ENTITY_TYPES.register("stoneanvil", () -> TileEntityType.Builder.create(StoneAnvilTileEntity::new, BlockInit.STONEANVIL_BLOCK.get()).build(null));
	public static final RegistryObject<TileEntityType<CastingBasinTileEntity>> CASTINGBASIN = TILE_ENTITY_TYPES.register("castingbasin", () -> TileEntityType.Builder.create(CastingBasinTileEntity::new, BlockInit.CASTINGBASIN_BLOCK.get()).build(null));
	//Engineering
	public static final RegistryObject<TileEntityType<WoodenHopperTileEntity>> WOODEN_HOPPER = TILE_ENTITY_TYPES.register("wooden_hopper", () -> TileEntityType.Builder.create(WoodenHopperTileEntity::new, BlockInit.WOODENHOPPER_BLOCK.get()).build(null));
}
