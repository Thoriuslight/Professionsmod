package com.thoriuslight.professionsmod.init;

import com.thoriuslight.professionsmod.ProfessionsMod;
import com.thoriuslight.professionsmod.block.CastingBasinBlock;
import com.thoriuslight.professionsmod.block.GlassJarBlock;
import com.thoriuslight.professionsmod.block.SmithCraftingTableBlock;
import com.thoriuslight.professionsmod.block.StoneAnvilBlock;
import com.thoriuslight.professionsmod.block.WoodenHopperBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ProfessionsMod.MODID);
	//Common Blocks
	public static final RegistryObject<Block> COPPER_ORE = BLOCKS.register("copper_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 3.f).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5f, 3.f).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
	//Smithing Blocks
	public static final RegistryObject<Block> SMITHCRAFTINGTABLE_BLOCK = BLOCKS.register("smithcraftingtable_block", () -> new SmithCraftingTableBlock((Block.Properties.create(Material.ROCK).hardnessAndResistance(3.f, 3.f).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE))));
	public static final RegistryObject<Block> STONEANVIL_BLOCK = BLOCKS.register("stoneanvil_block", () -> new StoneAnvilBlock((Block.Properties.create(Material.ROCK).hardnessAndResistance(3.f, 6.f).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE))));
	public static final RegistryObject<Block> CASTINGBASIN_BLOCK = BLOCKS.register("castingbasin_block", () -> new CastingBasinBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 2.f).sound(SoundType.STONE).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> STONECASTINGBASIN_BLOCK = BLOCKS.register("stonecastingbasin_block", () -> new CastingBasinBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 2.f).sound(SoundType.STONE).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
	//Engineering Blocks
	public static final RegistryObject<Block> WOODENHOPPER_BLOCK = BLOCKS.register("woodenhopper_block", () -> new WoodenHopperBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f, 3.f).sound(SoundType.WOOD).harvestLevel(0).harvestTool(ToolType.AXE)));
	//Alchemy Blocks
	public static final RegistryObject<Block> GLASS_JAR = BLOCKS.register("glass_jar", () -> new GlassJarBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(1.0f, 3.f).sound(SoundType.GLASS)));
	
}
