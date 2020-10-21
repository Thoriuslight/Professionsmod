package com.thoriuslight.professionsmod.world.gen;

import com.thoriuslight.professionsmod.init.BlockInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModOreGen {
	@SuppressWarnings("rawtypes")
	public static void generateOre() {
		for(Biome biome : ForgeRegistries.BIOMES) {
			if(biome == Biomes.MOUNTAINS) {
				ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(30, 0, 0, 128));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.COPPER_ORE.get().getDefaultState(), 10)).withPlacement(customConfig));
			}
			else {
				ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 0, 0, 128));
				ConfiguredPlacement customConfig_silver = Placement.COUNT_RANGE.configure(new CountRangeConfig(7, 0, 0, 48));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.COPPER_ORE.get().getDefaultState(), 8)).withPlacement(customConfig));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.SILVER_ORE.get().getDefaultState(), 5)).withPlacement(customConfig_silver));

			}
		}
	}
}
