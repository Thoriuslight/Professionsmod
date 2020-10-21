package com.thoriuslight.professionsmod.init;

import java.util.function.Supplier;

import com.thoriuslight.professionsmod.ProfessionsMod;
import com.thoriuslight.professionsmod.ProfessionsMod.AlchemyItemGroup;
import com.thoriuslight.professionsmod.ProfessionsMod.BlacksmithItemGroup;
import com.thoriuslight.professionsmod.item.ColdWorkedAxe;
import com.thoriuslight.professionsmod.item.ColdWorkedHoe;
import com.thoriuslight.professionsmod.item.ColdWorkedKnife;
import com.thoriuslight.professionsmod.item.ColdWorkedPickaxe;
import com.thoriuslight.professionsmod.item.ColdWorkedShovel;
import com.thoriuslight.professionsmod.item.GoldOreItem;
import com.thoriuslight.professionsmod.item.HammerItem;
import com.thoriuslight.professionsmod.item.MoltenMetalItem;
import com.thoriuslight.professionsmod.item.Mortar;
import com.thoriuslight.professionsmod.item.Sourdough;

import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ProfessionsMod.MODID);
	//Smithing Tools
	public static final RegistryObject<Item> STONEHAMMER_ITEM = ITEMS.register("stonehammer_item", () -> new HammerItem(new Item.Properties().group(BlacksmithItemGroup.instance)));
	//General Tools
	public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new ColdWorkedPickaxe(ModItemTier.COPPER, 1, -2.8f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", () -> new ColdWorkedShovel(ModItemTier.COPPER, 1.5f, -3.0f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", () -> new ColdWorkedAxe(ModItemTier.COPPER, 6.5f, -3.15f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> COPPER_KNIFE = ITEMS.register("copper_knife", () -> new ColdWorkedKnife(ModItemTier.COPPER, 1.5f, -2.00f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe", () -> new ColdWorkedHoe(ModItemTier.COPPER, -1.50f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	
	public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe", () -> new ColdWorkedPickaxe(ModItemTier.SILVER, 1, -2.8f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel", () -> new ColdWorkedShovel(ModItemTier.SILVER, 1.5f, -3.0f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe", () -> new ColdWorkedAxe(ModItemTier.SILVER, 7.0f, -3.2f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> SILVER_KNIFE = ITEMS.register("silver_knife", () -> new ColdWorkedKnife(ModItemTier.SILVER, 1.5f, -2.00f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe", () -> new ColdWorkedHoe(ModItemTier.SILVER, -2.00f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	
	public static final RegistryObject<Item> GOLDEN_PICKAXE = ITEMS.register("golden_pickaxe", () -> new ColdWorkedPickaxe(ModItemTier.GOLD, 1, -2.8f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> GOLDEN_SHOVEL = ITEMS.register("golden_shovel", () -> new ColdWorkedShovel(ModItemTier.GOLD, 1.5f, -3.0f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> GOLDEN_AXE = ITEMS.register("golden_axe", () -> new ColdWorkedAxe(ModItemTier.GOLD, 6.0f, -3.2f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> GOLDEN_KNIFE = ITEMS.register("golden_knife", () -> new ColdWorkedKnife(ModItemTier.GOLD, 1.5f, -2.00f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> GOLDEN_HOE = ITEMS.register("golden_hoe", () -> new ColdWorkedHoe(ModItemTier.GOLD, -2.50f, new Item.Properties().group(BlacksmithItemGroup.instance)));
	//Copper
	public static final RegistryObject<Item> COPPER_CHUNK = ITEMS.register("copper_chunk", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(16)));
	public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> BUCKETFILLED_COPPER = ITEMS.register("bucketfilled_copper", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> BUCKETFILLED_MOLTENCOPPER = ITEMS.register("bucketfilled_moltencopper", () -> new MoltenMetalItem(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1), ModItemTier.COPPER));
	public static final RegistryObject<Item> COPPER_PICKAXEHEAD = ITEMS.register("copper_pickaxehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> COPPER_SHOVELHEAD = ITEMS.register("copper_shovelhead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> COPPER_AXEHEAD = ITEMS.register("copper_axehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> COPPER_KNIFEHEAD = ITEMS.register("copper_knifehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> COPPER_HOEHEAD = ITEMS.register("copper_hoehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	//Silver
	public static final RegistryObject<Item> SILVER_CHUNK = ITEMS.register("silver_chunk", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(16)));
	public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance)));
	public static final RegistryObject<Item> BUCKETFILLED_SILVER = ITEMS.register("bucketfilled_silver", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> BUCKETFILLED_MOLTENSILVER = ITEMS.register("bucketfilled_moltensilver", () -> new MoltenMetalItem(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1), ModItemTier.SILVER));
	public static final RegistryObject<Item> SILVER_PICKAXEHEAD = ITEMS.register("silver_pickaxehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> SILVER_SHOVELHEAD = ITEMS.register("silver_shovelhead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> SILVER_AXEHEAD = ITEMS.register("silver_axehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> SILVER_KNIFEHEAD = ITEMS.register("silver_knifehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> SILVER_HOEHEAD = ITEMS.register("silver_hoehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	//Gold
	public static final RegistryObject<Item> GOLD_ORE_ITEM = ITEMS.register("gold_ore_item", () -> new GoldOreItem(Blocks.GOLD_ORE, new Item.Properties().maxStackSize(1)));
	public static final RegistryObject<Item> GOLDEN_CHUNK = ITEMS.register("golden_chunk", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(16)));
	public static final RegistryObject<Item> BUCKETFILLED_GOLD = ITEMS.register("bucketfilled_gold", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> BUCKETFILLED_MOLTENGOLD = ITEMS.register("bucketfilled_moltengold", () -> new MoltenMetalItem(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1), ModItemTier.GOLD));
	public static final RegistryObject<Item> GOLDEN_PICKAXEHEAD = ITEMS.register("golden_pickaxehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> GOLDEN_SHOVELHEAD = ITEMS.register("golden_shovelhead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> GOLDEN_AXEHEAD = ITEMS.register("golden_axehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> GOLDEN_KNIFEHEAD = ITEMS.register("golden_knifehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	public static final RegistryObject<Item> GOLDEN_HOEHEAD = ITEMS.register("golden_hoehead", () -> new Item(new Item.Properties().group(BlacksmithItemGroup.instance).maxStackSize(1)));
	//Alchemy
	public static final RegistryObject<Item> MORTARANDPESTLE = ITEMS.register("mortarandpestle", () -> new Mortar(new Item.Properties().group(AlchemyItemGroup.instance).maxStackSize(1).maxDamage(131)));
	public static final RegistryObject<Item> WOODEN_MORTARANDPESTLE = ITEMS.register("wooden_mortarandpestle", () -> new Mortar(new Item.Properties().group(AlchemyItemGroup.instance).maxStackSize(1).maxDamage(59)));
	public static final RegistryObject<Item> FLOUR = ITEMS.register("flour", () -> new Item(new Item.Properties().group(AlchemyItemGroup.instance)));
	public static final RegistryObject<Item> SOURDOUGH = ITEMS.register("sourdough", () -> new Sourdough(new Item.Properties().maxStackSize(1).maxDamage(25).group(AlchemyItemGroup.instance)));
	public static final RegistryObject<Item> DOUGH = ITEMS.register("dough", () -> new Item(new Item.Properties().group(AlchemyItemGroup.instance)));
	
	public enum ModItemTier implements IItemTier {
		COPPER(1, 100, 5.0f, 1.5f, 18, 10, () -> {
		      return Ingredient.fromItems(ItemInit.COPPER_NUGGET.get());
		}),
		SILVER(1, 70, 4.5f, 1.0f, 20, 8, () -> {
		      return Ingredient.fromItems(ItemInit.SILVER_NUGGET.get());
		}),
		GOLD(0, 50, 4.0f, 0.5f, 24, 6, () -> {
		      return Ingredient.fromItems(Items.GOLD_NUGGET);
		});
		   private final int harvestLevel;
		   private final int maxUses;
		   private final float efficiency;
		   private final float attackDamage;
		   private final int enchantability;
		   private final LazyValue<Ingredient> repairMaterial;
		   private final int hardness;

		   private ModItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, int hardness, Supplier<Ingredient> repairMaterialIn) {
		      this.harvestLevel = harvestLevelIn;
		      this.maxUses = maxUsesIn;
		      this.efficiency = efficiencyIn;
		      this.attackDamage = attackDamageIn;
		      this.enchantability = enchantabilityIn;
		      this.repairMaterial = new LazyValue<>(repairMaterialIn);
		      this.hardness = hardness;
		   }

		   public int getMaxUses() {
		      return this.maxUses;
		   }

		   public float getEfficiency() {
		      return this.efficiency;
		   }

		   public float getAttackDamage() {
		      return this.attackDamage;
		   }

		   public int getHarvestLevel() {
		      return this.harvestLevel;
		   }

		   public int getEnchantability() {
		      return this.enchantability;
		   }
		   
		   public int getHardness() {
			   return this.hardness;
			}

		   public Ingredient getRepairMaterial() {
		      return this.repairMaterial.getValue();
		   }
		
	}
	
}
