package com.thoriuslight.professionsmod.item;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ToolCoreItem extends Item implements IForgeable{
	private final IItemTier tier;
	protected final float attackDamage;
	protected final float attackSpeed;
	
	public ToolCoreItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
		super(properties);
		this.tier = tier;
	    this.attackDamage = attackDamageIn + tier.getAttackDamage();
	    this.attackSpeed = attackSpeedIn;
	}

	protected boolean hasHardness(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("properties");
		return compoundnbt != null && compoundnbt.contains("hardness", 99);
	}
	protected boolean hasWear(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("properties");
		return compoundnbt != null && compoundnbt.contains("wear", 99);
	}
	protected int getHardness(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("properties");
		return compoundnbt != null && compoundnbt.contains("hardness", 99) ? compoundnbt.getInt("hardness") : 0;
	}
	protected int getWear(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("properties");
		return compoundnbt != null && compoundnbt.contains("wear", 99) ? compoundnbt.getInt("wear") : 0;
	}
	protected void setHardness(ItemStack stack, int hardness) {
		stack.getOrCreateChildTag("properties").putInt("hardness", hardness);
	}
	protected void setWear(ItemStack stack, int wear) {
		stack.getOrCreateChildTag("properties").putInt("wear", wear);
	}
	
	public IItemTier getTier() {
		return this.tier;
	}
	public void repair(ItemStack stack, int hardness, int wear) {
		int damage = this.getDamage(stack);
		int i = this.getWear(stack);
		if(damage > i && i < (4*hardness)) {
			this.setWear(stack, ++i);
			damage -= 10-(hardness/2);
			this.setDamage(stack, Math.max(damage, i));
		}
	}
	@Override
	public int getItemEnchantability() {
		return this.tier.getEnchantability();
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return this.tier.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
	}
	
	@Override
	public void hammer(ItemStack stack) {
	}

	@Override
	public int requiredStrikes(ItemStack stack) {
		return 0;
	}

	@Override
	public ItemStack forged(ItemStack stack) {
		return ItemStack.EMPTY;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if (equipmentSlot == EquipmentSlotType.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
		}

		return multimap;
	}

}
