package com.thoriuslight.professionsmod.item;

import com.thoriuslight.professionsmod.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class SilverOreItem extends BlockItem implements IForgeable{

	public SilverOreItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}

	@Override
	public void hammer(ItemStack stack) {
	}

	@Override
	public int requiredStrikes(ItemStack stack) {
		return 10;
	}

	@Override
	public ItemStack forged(ItemStack stack) {
		return new ItemStack(ItemInit.SILVER_CHUNK.get(), 8);
	}

}
