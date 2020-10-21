package com.thoriuslight.professionsmod.tileentity;

import com.thoriuslight.professionsmod.block.StoneAnvilBlock;
import com.thoriuslight.professionsmod.init.ModTileEntityTypes;
import com.thoriuslight.professionsmod.item.IForgeable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.Constants;

public class StoneAnvilTileEntity extends TileEntity{
	private ItemStack item;
	private int hammerStrikes;
	public StoneAnvilTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		item = ItemStack.EMPTY;
		hammerStrikes = 0;
	}
	public StoneAnvilTileEntity() {
		this(ModTileEntityTypes.STONEANVIL.get());
	}
	
	public void addItem(ItemStack item) {
		this.item = item;
		this.hammerStrikes = ((IForgeable)item.getItem()).requiredStrikes(item);
		if(((IForgeable)item.getItem()).dropItem()) {
			--this.hammerStrikes;
		}
		this.markDirty();
	}
	@Override
	public void markDirty() {
		super.markDirty();
		this.world.notifyBlockUpdate(this.pos, this.getBlockState(), this.getBlockState(),
				Constants.BlockFlags.BLOCK_UPDATE);
	}
	public boolean isfull() {
		return !item.isEmpty();
	}
	public boolean canHammer() {
		if(this.isfull()) {
			return (hammerStrikes > 0);
		}
		return false;
	}
	public void hammer() {
		IForgeable itemForged = (IForgeable)item.getItem();
		if(hammerStrikes > 0) {
			--hammerStrikes;
			itemForged.hammer(item);
		} else {
			if(!itemForged.forged(item).isEmpty()) {
				if(itemForged.dropItem()) {
					((StoneAnvilBlock)this.getBlockState().getBlock()).dropItem(this.getWorld(), this, this.pos, itemForged.forged(item));
					this.setItem(ItemStack.EMPTY);
				}else {
					itemForged.hammer(item);
					if(itemForged.forged(item).getItem() instanceof IForgeable) {
						this.addItem(itemForged.forged(item));
					}else {
						this.setItem(ItemStack.EMPTY);
					}
				}
			}
		}
	}
	public void dropItem() {
		((StoneAnvilBlock)this.getBlockState().getBlock()).dropItem(this.getWorld(), this, this.pos, item);
		this.item = ItemStack.EMPTY;
		this.markDirty();
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.put("item", this.item.write(new CompoundNBT()));
		compound.putInt("strikes", this.hammerStrikes);
		return compound;
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		if(compound.contains("item")) {
			this.item = (ItemStack.read(compound.getCompound("item")));
		}
		if(compound.contains("strikes")) {
			this.hammerStrikes = compound.getInt("strikes");
		}
	}
	
	public ItemStack getItem() {
		return item;
	}
	private void setItem(ItemStack stack) {
		this.item = stack;
		this.markDirty();
	}
	//Sync
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);

		return new SUpdateTileEntityPacket(this.getPos(), 1, nbt);
	}
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		this.read(pkt.getNbtCompound());
	}
	@Override
	public CompoundNBT getUpdateTag() {
		return this.write(new CompoundNBT());
	}
	@Override
	public void handleUpdateTag(CompoundNBT tag) {
		this.read(tag);
	}
}
