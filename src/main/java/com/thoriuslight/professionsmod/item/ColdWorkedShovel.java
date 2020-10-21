package com.thoriuslight.professionsmod.item;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.thoriuslight.professionsmod.init.ItemInit.ModItemTier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ColdWorkedShovel extends ToolCoreItem{
	protected final float efficiency;
	protected final int hardness;
	private static final Set<Block> effectiveBlocks = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER);
	/** Map used to lookup shovel right click interactions */
	protected static final Map<Block, BlockState> SHOVEL_LOOKUP = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.getDefaultState()));
	
	public ColdWorkedShovel(ModItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder.defaultMaxDamage(tier.getMaxUses()).addToolType(ToolType.SHOVEL, 0));
	    this.hardness = tier.getHardness();
	    this.efficiency = tier.getEfficiency() - 0.2f * hardness;
	}
	/**
	 * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
	*/
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F) {
	    int i = 2;
	    if(this.getHardness(stack) > (hardness/2)) {
	    	--i;
	    }
	    stack.damageItem(i, entityLiving, (p_220038_0_) -> {
	    		p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
	    	});
		}
		return true;
	}
	
	@Override
	public int getHarvestLevel(ItemStack stack, ToolType tool, PlayerEntity player, BlockState blockState) {
		int i = this.getTier().getHarvestLevel();
  	  	if(this.getHardness(stack) < (hardness/2) && i != 0) {
  	  		--i;
  	  	}
  	  	return i;
	}
	/**
	 * Check whether this Item can harvest the given Block
	 */
	public boolean canHarvestBlock(BlockState blockIn) {
	   Block block = blockIn.getBlock();
	   return block == Blocks.SNOW || block == Blocks.SNOW_BLOCK;
	}
	

	/**
	 * Called when this item is used when targetting a Block
	 */
	public ActionResultType onItemUse(ItemUseContext context) {
	   World world = context.getWorld();
	   BlockPos blockpos = context.getPos();
	   BlockState blockstate = world.getBlockState(blockpos);
	   if (context.getFace() == Direction.DOWN) {
	      return ActionResultType.PASS;
	   } else {
	      PlayerEntity playerentity = context.getPlayer();
	      BlockState blockstate1 = SHOVEL_LOOKUP.get(blockstate.getBlock());
	      BlockState blockstate2 = null;
	      if (blockstate1 != null && world.isAirBlock(blockpos.up())) {
	         world.playSound(playerentity, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
	         blockstate2 = blockstate1;
	      } else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.get(CampfireBlock.LIT)) {
	         world.playEvent((PlayerEntity)null, 1009, blockpos, 0);
	         blockstate2 = blockstate.with(CampfireBlock.LIT, Boolean.valueOf(false));
	      }

	      if (blockstate2 != null) {
	         if (!world.isRemote) {
	            world.setBlockState(blockpos, blockstate2, 11);
	            if (playerentity != null) {
	               context.getItem().damageItem(1, playerentity, (p_220041_1_) -> {
	                  p_220041_1_.sendBreakAnimation(context.getHand());
	               });
	            }
	         }

	         return ActionResultType.SUCCESS;
	      } else {
	         return ActionResultType.PASS;
	      }
	   }
	}
	
	@SuppressWarnings("static-access")
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (getToolTypes(stack).stream().anyMatch(e -> state.isToolEffective(e))) return (this.efficiency + this.getHardness(stack) * 0.2f);
		return this.effectiveBlocks.contains(state.getBlock()) ? (this.efficiency + this.getHardness(stack) * 0.2f) : 1.0F;
	}
	   
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
  	  	int i = 3;
  	  	if(this.getHardness(stack) > (hardness/2)) {
  		  --i;
  	  	}
		stack.damageItem(i, attacker, (p_220039_0_) -> {
			p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		});
		return true;
	}
	
	@Override
	public void hammer(ItemStack stack) {
		int j = this.getHardness(stack);
		repair(stack, this.hardness, 1);
		if(j < this.hardness) {
			++j;
			this.setHardness(stack, j);
		}
	}

	@Override
	public int requiredStrikes(ItemStack stack) {
		int j = this.getHardness(stack);
		int reqRepair = (this.getDamage(stack) - this.getWear(stack))/(10-(j/2));
		return Math.max(this.hardness - j, reqRepair);
	}

	
	@Override
	public boolean dropItem() {
		return false;
	}

}
