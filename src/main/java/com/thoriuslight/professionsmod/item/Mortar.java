package com.thoriuslight.professionsmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Mortar extends Item{

	public Mortar(Properties properties) {
		super(properties);
	}
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack stack = new ItemStack(itemStack.getItem());
		stack.setDamage(itemStack.getDamage()+1);
		if(stack.getMaxDamage()-stack.getDamage()<1)
		return ItemStack.EMPTY;
		else
		return stack;
	}
}
