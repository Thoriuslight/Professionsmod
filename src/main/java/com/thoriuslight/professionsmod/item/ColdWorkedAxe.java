package com.thoriuslight.professionsmod.item;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;
import com.google.common.collect.ImmutableMap.Builder;
import com.thoriuslight.professionsmod.init.ItemInit.ModItemTier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ColdWorkedAxe extends ToolCoreItem{
	protected final float efficiency;
	protected final int hardness;
	private static final Set<Block> effectiveBlocks = Sets.newHashSet(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE);
	protected static final Map<Block, Block> BLOCK_STRIPPING_MAP = (new Builder<Block, Block>()).put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).build();
	
	public ColdWorkedAxe(ModItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder.defaultMaxDamage(tier.getMaxUses()).addToolType(ToolType.AXE, 0));
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
	 * Called when this item is used when targetting a Block
	 */
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		BlockState blockstate = world.getBlockState(blockpos);
		Block block = BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
		if (block != null) {
			PlayerEntity playerentity = context.getPlayer();
			world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!world.isRemote) {
				world.setBlockState(blockpos, block.getDefaultState().with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)), 11);
				if (playerentity != null) {
					context.getItem().damageItem(1, playerentity, (p_220040_1_) -> {
						p_220040_1_.sendBreakAnimation(context.getHand());
					});
				}
			}
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.PASS;
		}
	}
	
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.TALL_PLANTS && material != Material.BAMBOO ? getDestroySpeed2(stack, state) : (this.efficiency + this.getHardness(stack) * 0.2f);
	}
	@SuppressWarnings("static-access")
	public float getDestroySpeed2(ItemStack stack, BlockState state) {
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
