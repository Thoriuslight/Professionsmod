package com.thoriuslight.professionsmod.block;

import com.thoriuslight.professionsmod.init.BlockInit;
import com.thoriuslight.professionsmod.init.ModTileEntityTypes;
import com.thoriuslight.professionsmod.item.IForgeable;
import com.thoriuslight.professionsmod.tileentity.StoneAnvilTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class StoneAnvilBlock extends Block{
	ResourceLocation copperTag = new ResourceLocation("forge:ores/copper");
	ResourceLocation silverTag = new ResourceLocation("forge:ores/silver");
	public StoneAnvilBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return Block.makeCuboidShape(0, 0, 0, 16, 15, 16);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.STONEANVIL.get().create();
	}
	public boolean hammer(World world, BlockPos pos, PlayerEntity player) {
		if (!world.isRemote) {
            TileEntity tileentity = world.getTileEntity(pos);
            if(tileentity instanceof StoneAnvilTileEntity) {
            	StoneAnvilTileEntity anvil = (StoneAnvilTileEntity)tileentity;
            	if(anvil.isfull()) {
            		anvil.hammer();
           		 	player.getHeldItemMainhand().damageItem(1, player, (p_220039_0_) -> {
           		 	p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
           		 	});
           		 	return true;
            	}
            }
		}
		return false;
	};
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	      ItemStack itemstack = player.getHeldItem(handIn);
	      if (itemstack.isEmpty() && player.getHeldItemMainhand().isEmpty()) {
	    	  TileEntity tileentity = worldIn.getTileEntity(pos);
	    	  if(tileentity instanceof StoneAnvilTileEntity) {
	    		  if(!worldIn.isRemote) {
	    			  if(((StoneAnvilTileEntity)tileentity).isfull()) {
	    				  ((StoneAnvilTileEntity)tileentity).dropItem();
	    			      return ActionResultType.SUCCESS;
	    			  }
	    		  }
	    	  }
	         return ActionResultType.PASS;
	      } else {
	         Item item = itemstack.getItem();
	         if(item instanceof IForgeable) {
	             TileEntity tileentity = worldIn.getTileEntity(pos);
	             if(tileentity instanceof StoneAnvilTileEntity) {
	            	if(!worldIn.isRemote) {
	            		if(!((StoneAnvilTileEntity)tileentity).isfull()) {
	            			((StoneAnvilTileEntity)tileentity).addItem(itemstack.copy());
	            			itemstack.shrink(1);
	            		}
	        	 	}
		         	return ActionResultType.SUCCESS;
	             }
	         }
	         else if(ItemTags.getCollection().getOrCreate(copperTag).contains(item)) {
	             TileEntity tileentity = worldIn.getTileEntity(pos);
	             if(tileentity instanceof StoneAnvilTileEntity) {
	            	if(!worldIn.isRemote) {
	            		if(!((StoneAnvilTileEntity)tileentity).isfull()) {
	            			((StoneAnvilTileEntity)tileentity).addItem(new ItemStack(BlockInit.COPPER_ORE.get(), 1));
	            			itemstack.shrink(1);
	            		}
	        	 	}
		         	return ActionResultType.SUCCESS;
	             }
	         }
	         else if(ItemTags.getCollection().getOrCreate(silverTag).contains(item)) {
	             TileEntity tileentity = worldIn.getTileEntity(pos);
	             if(tileentity instanceof StoneAnvilTileEntity) {
	            	if(!worldIn.isRemote) {
	            		if(!((StoneAnvilTileEntity)tileentity).isfull()) {
	            			((StoneAnvilTileEntity)tileentity).addItem(new ItemStack(BlockInit.SILVER_ORE.get(), 1));
	            			itemstack.shrink(1);
	            		}
	        	 	}
		         	return ActionResultType.SUCCESS;
	             }
	         }
	         return ActionResultType.PASS;
	      }
	}
	
	public void dropItem(World worldIn, StoneAnvilTileEntity tileentity, BlockPos pos, ItemStack itemstack) {
		if (!itemstack.isEmpty()) {
		               worldIn.playEvent(1010, pos, 0);
		               double d0 = (double)(worldIn.rand.nextFloat() * 0.7F) + (double)0.15F;
		               double d1 = (double)(worldIn.rand.nextFloat() * 0.7F) + (double)0.060000002F + 0.6D;
		               double d2 = (double)(worldIn.rand.nextFloat() * 0.7F) + (double)0.15F;
		               ItemStack itemstack1 = itemstack.copy();
		               ItemEntity itementity = new ItemEntity(worldIn, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, itemstack1);
		               itementity.setDefaultPickupDelay();
		               worldIn.addEntity(itementity);
		}      
	}

}
