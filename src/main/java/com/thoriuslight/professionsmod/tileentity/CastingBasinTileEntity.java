package com.thoriuslight.professionsmod.tileentity;

import com.thoriuslight.professionsmod.init.DataInit;
import com.thoriuslight.professionsmod.init.ItemInit.ModItemTier;

import com.thoriuslight.professionsmod.block.CastingBasinBlock;
import com.thoriuslight.professionsmod.init.ModTileEntityTypes;
import com.thoriuslight.professionsmod.state.properties.Tool;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class CastingBasinTileEntity extends TileEntity{
	ModItemTier liquified_metal;
	int liquidQuantiy;
	Tool tool;
	//boolean filled;
	public CastingBasinTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		liquified_metal = ModItemTier.COPPER;
		liquidQuantiy = 0;
		tool = Tool.NOTHING;
	}

	public CastingBasinTileEntity() {
		this(ModTileEntityTypes.CASTINGBASIN.get());
	}
	
	public boolean addMetal(ModItemTier metal) {
		if(liquidQuantiy > tool.getRequiredIngotAmount() && tool != Tool.NOTHING) {
			liquidQuantiy -= tool.getRequiredIngotAmount();
			this.dropItem(new ItemStack(DataInit.getToolHead(tool, liquified_metal)));
			return false;
		} else if(liquidQuantiy == 0) {
			this.liquified_metal = metal;
			++liquidQuantiy;
		} else if(metal == this.liquified_metal){
			++liquidQuantiy;
			if(liquidQuantiy > tool.getRequiredIngotAmount()  && tool != Tool.NOTHING) {
				liquidQuantiy -= tool.getRequiredIngotAmount();
				this.dropItem(new ItemStack(DataInit.getToolHead(tool, liquified_metal)));
				
			}
		} else {
			return false;
		}
		this.markDirty();
		return true;
	}
	
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	
	public void dropItem(ItemStack item) {
		((CastingBasinBlock)this.getBlockState().getBlock()).dropItem(this.getWorld(), this.pos, item);
		this.liquidQuantiy = 0;
		this.markDirty();
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putString("metal", this.liquified_metal.toString());
		compound.putInt("metalAmount", this.liquidQuantiy);
		compound.putString("tool", this.tool.toString());
		return compound;
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		if(compound.contains("metal")) {
			this.liquified_metal = ModItemTier.valueOf(compound.getString("metal"));
		}
		if(compound.contains("metalAmount")) {
			this.liquidQuantiy = compound.getInt("metalAmount");
		}
		if(compound.contains("tool")) {
			this.tool = Tool.valueOf(compound.getString("tool"));
		}
	}
}
