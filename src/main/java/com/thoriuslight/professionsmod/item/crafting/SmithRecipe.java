package com.thoriuslight.professionsmod.item.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class SmithRecipe implements ISmithRecipe{

	public SmithRecipe(ResourceLocation id, ItemStack input, ItemStack output){
		
	}
	
	@Override
	public boolean matches(RecipeWrapper inv, World worldIn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canFit(int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceLocation getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingredient getInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
