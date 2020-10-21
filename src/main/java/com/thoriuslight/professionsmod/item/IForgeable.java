package com.thoriuslight.professionsmod.item;

import net.minecraft.item.ItemStack;

public interface IForgeable {
	public void hammer(ItemStack stack);
	default public int requiredStrikes(ItemStack stack) {
		return 0;
	};
	public ItemStack forged(ItemStack stack);
	default public boolean dropItem() {
		return true;
	}
}
