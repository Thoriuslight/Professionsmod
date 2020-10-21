package com.thoriuslight.professionsmod.item;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.thoriuslight.professionsmod.init.ItemInit.ModItemTier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ColdWorkedPickaxe extends ToolCoreItem{
	protected final float efficiency;
	protected final int hardness;
	protected static final Set<Block> effectiveBlocks = ImmutableSet.of(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX);
	public ColdWorkedPickaxe(ModItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder.defaultMaxDamage(tier.getMaxUses()).addToolType(ToolType.PICKAXE, tier.getHarvestLevel()));
	    this.hardness = tier.getHardness();
	    this.efficiency = tier.getEfficiency() - 0.2f * hardness;
	}
	


	@Override
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
	
	@Override
	public boolean canHarvestBlock(ItemStack stack, BlockState blockIn) {
		int i = this.getTier().getHarvestLevel();
  	  	if(this.getHardness(stack) < (hardness/2) && i != 0) {
  	  		--i;
  	  	}
		if (blockIn.getHarvestTool() == net.minecraftforge.common.ToolType.PICKAXE) {
			return i >= blockIn.getHarvestLevel();
		}
		Material material = blockIn.getMaterial();
		return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? getDestroySpeed2(stack, state) : (this.efficiency + this.getHardness(stack) * 0.2f);
	}

	public float getDestroySpeed2(ItemStack stack, BlockState state) {
		if (getToolTypes(stack).stream().anyMatch(e -> state.isToolEffective(e))) return (this.efficiency + this.getHardness(stack) * 0.2f);
		return ColdWorkedPickaxe.effectiveBlocks.contains(state.getBlock()) ? (this.efficiency + this.getHardness(stack) * 0.2f) : 1.0F;
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
