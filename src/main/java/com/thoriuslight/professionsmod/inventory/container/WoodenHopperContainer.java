package com.thoriuslight.professionsmod.inventory.container;

import com.thoriuslight.professionsmod.init.ModContainerTypes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class WoodenHopperContainer extends Container{
	private final IInventory hopperInventory;
	
	public WoodenHopperContainer(int p_i50078_1_, PlayerInventory p_i50078_2_, final PacketBuffer data) {
		this(p_i50078_1_, p_i50078_2_, new Inventory(1));
	}
	
	public WoodenHopperContainer(int p_i50078_1_, PlayerInventory p_i50078_2_) {
		this(p_i50078_1_, p_i50078_2_, new Inventory(1));
	}
	
	public WoodenHopperContainer(final int windowId, final PlayerInventory playerInventory, IInventory inventory) {
		super((ModContainerTypes.WOODEN_HOPPER).get(), windowId);
		this.hopperInventory = inventory;
	      assertInventorySize(inventory, 1);
	      inventory.openInventory(playerInventory.player);
	      this.addSlot(new Slot(inventory, 0, 80 , 20));
	      for(int l = 0; l < 3; ++l) {
	         for(int k = 0; k < 9; ++k) {
	            this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
	         }
	      }

	      for(int i1 = 0; i1 < 9; ++i1) {
	         this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 109));
	      }
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.hopperInventory.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < this.hopperInventory.getSizeInventory()) {
				if (!this.mergeItemStack(itemstack1, this.hopperInventory.getSizeInventory(), this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
	            }
			} else if (!this.mergeItemStack(itemstack1, 0, this.hopperInventory.getSizeInventory(), false)) {
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
	            slot.onSlotChanged();
			}
		}
		return itemstack;
	}

	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		this.hopperInventory.closeInventory(playerIn);
	}
}
