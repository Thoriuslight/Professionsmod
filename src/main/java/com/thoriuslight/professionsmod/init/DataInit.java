package com.thoriuslight.professionsmod.init;

import java.util.EnumMap;

import com.thoriuslight.professionsmod.init.ItemInit.ModItemTier;
import com.thoriuslight.professionsmod.state.properties.Tool;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class DataInit {
	private static EnumMap<Tool, EnumMap<ModItemTier, Item>> map = new EnumMap<>(Tool.class);
	private static EnumMap<ModItemTier, Item> pickaxe = new EnumMap<>(ModItemTier.class);
	private static EnumMap<ModItemTier, Item> shovel = new EnumMap<>(ModItemTier.class);
	private static EnumMap<ModItemTier, Item> axe = new EnumMap<>(ModItemTier.class);
	private static EnumMap<ModItemTier, Item> knife = new EnumMap<>(ModItemTier.class);
	private static EnumMap<ModItemTier, Item> hoe = new EnumMap<>(ModItemTier.class);
	private static EnumMap<ModItemTier, Item> ingot = new EnumMap<>(ModItemTier.class);
	public static void initData() {
		pickaxe.put(ModItemTier.COPPER, ItemInit.COPPER_PICKAXEHEAD.get());
		pickaxe.put(ModItemTier.SILVER, ItemInit.SILVER_PICKAXEHEAD.get());
		pickaxe.put(ModItemTier.GOLD, ItemInit.GOLDEN_PICKAXEHEAD.get());
		shovel.put(ModItemTier.COPPER, ItemInit.COPPER_SHOVELHEAD.get());
		shovel.put(ModItemTier.SILVER, ItemInit.SILVER_SHOVELHEAD.get());
		shovel.put(ModItemTier.GOLD, ItemInit.GOLDEN_SHOVELHEAD.get());
		axe.put(ModItemTier.COPPER, ItemInit.COPPER_AXEHEAD.get());
		axe.put(ModItemTier.SILVER, ItemInit.SILVER_AXEHEAD.get());
		axe.put(ModItemTier.GOLD, ItemInit.GOLDEN_AXEHEAD.get());
		knife.put(ModItemTier.COPPER, ItemInit.COPPER_KNIFEHEAD.get());
		knife.put(ModItemTier.SILVER, ItemInit.SILVER_KNIFEHEAD.get());
		knife.put(ModItemTier.GOLD, ItemInit.GOLDEN_KNIFEHEAD.get());
		hoe.put(ModItemTier.COPPER, ItemInit.COPPER_HOEHEAD.get());
		hoe.put(ModItemTier.SILVER, ItemInit.SILVER_HOEHEAD.get());
		hoe.put(ModItemTier.GOLD, ItemInit.GOLDEN_HOEHEAD.get());
		ingot.put(ModItemTier.COPPER, ItemInit.COPPER_INGOT.get());
		ingot.put(ModItemTier.SILVER, ItemInit.SILVER_INGOT.get());
		ingot.put(ModItemTier.GOLD, Items.GOLD_INGOT);
		map.put(Tool.PICKAXE, pickaxe);
		map.put(Tool.SHOVEL, shovel);
		map.put(Tool.AXE, axe);
		map.put(Tool.KNIFE, knife);
		map.put(Tool.HOE, hoe);
		map.put(Tool.INGOT, ingot);
	}
	public static Item getToolHead(Tool tool, ModItemTier material) {
		return map.get(tool).get(material);
	}
}
