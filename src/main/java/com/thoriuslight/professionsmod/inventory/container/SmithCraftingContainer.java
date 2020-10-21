package com.thoriuslight.professionsmod.inventory.container;

import javax.naming.directory.ModificationItem;

import com.thoriuslight.professionsmod.init.BlockInit;
import com.thoriuslight.professionsmod.init.ModContainerTypes;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;

public class SmithCraftingContainer extends RecipeBookContainer<CraftingInventory>{
	private final CraftingInventory craftMatrix = new CraftingInventory(this, 3, 3);
	private final CraftResultInventory craftResult = new CraftResultInventory();
	private final IWorldPosCallable canInteractWithCallable;
	private final PlayerEntity player;
	
	public SmithCraftingContainer(int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, IWorldPosCallable.DUMMY);
	}
	
	public SmithCraftingContainer(int windowId, final PlayerInventory playerInventory) {
		this(windowId, playerInventory, IWorldPosCallable.DUMMY);
	}
	
	public SmithCraftingContainer(int windowId, final PlayerInventory playerInventory, IWorldPosCallable worldPosCallable) {
		super(ModContainerTypes.SMITH_CRAFTING.get(), windowId);
		this.canInteractWithCallable = worldPosCallable;
		this.player = playerInventory.player;
	    this.addSlot(new CraftingResultSlot(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 35));
	    
	    for(int i = 0; i < 3; ++i) {
	    	for(int j = 0; j < 3; ++j) {
	    		this.addSlot(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
	    	}
	    }

	    for(int k = 0; k < 3; ++k) {
	          for(int i1 = 0; i1 < 9; ++i1) {
	             this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
	          }
	       }

	       for(int l = 0; l < 9; ++l) {
	          this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
	       }
	}

	@Override
	public void fillStackedContents(RecipeItemHelper itemHelperIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean matches(IRecipe<? super CraftingInventory> recipeIn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getOutputSlot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		   return isWithinUsableDistance(this.canInteractWithCallable, playerIn, BlockInit.SMITHCRAFTINGTABLE_BLOCK.get());
	}

}
