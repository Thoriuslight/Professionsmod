package com.thoriuslight.professionsmod.item;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.thoriuslight.professionsmod.init.ItemInit.ModItemTier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
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

public class ColdWorkedHoe extends ToolCoreItem{
	protected static final Map<Block, BlockState> HOE_LOOKUP = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.FARMLAND.getDefaultState(), Blocks.GRASS_PATH, Blocks.FARMLAND.getDefaultState(), Blocks.DIRT, Blocks.FARMLAND.getDefaultState(), Blocks.COARSE_DIRT, Blocks.DIRT.getDefaultState()));
	protected final int hardness;
	private final float speed;
	protected static final Set<Block> effectiveBlocks = ImmutableSet.of(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX);
	public ColdWorkedHoe(ModItemTier tier, float attackSpeedIn, Properties builder) {
		super(tier, 0.0f, attackSpeedIn, builder.defaultMaxDamage(tier.getMaxUses()).addToolType(ToolType.PICKAXE, tier.getHarvestLevel()));
	    this.hardness = tier.getHardness();
	    this.speed = attackSpeedIn;
	}
	


	   /**
	    * Called when this item is used when targetting a Block
	    */
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
	    BlockPos blockpos = context.getPos();
	    int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
	    if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
	    	if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
	    		BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
	    		if (blockstate != null) {
	    			PlayerEntity playerentity = context.getPlayer();
	    			world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
	    			if (!world.isRemote) {
	    				world.setBlockState(blockpos, blockstate, 11);
	    				if (playerentity != null) {
	    					ItemStack Item = context.getItem();
	    			 	  	int i = 2;
	    			  	  	if(this.getHardness(Item) > (hardness/2)) {
	    			  		  --i;
	    			  	  	}
	    					Item.damageItem(i, playerentity, (p_220043_1_) -> {
	    						p_220043_1_.sendBreakAnimation(context.getHand());
	    					});
	    				}
	    			}
	    			return ActionResultType.SUCCESS;
	    		}
	    	}
	    	return ActionResultType.PASS;
	}
	   
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
  	  	int i = 2;
  	  	if(this.getHardness(stack) > (hardness/2)) {
  		  --i;
  	  	}
		stack.damageItem(i, attacker, (p_220039_0_) -> {
			p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		});
		return true;
	}
	
	   public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
		      Multimap<String, AttributeModifier> multimap = HashMultimap.create();
		      if (equipmentSlot == EquipmentSlotType.MAINHAND) {
		         multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0D, AttributeModifier.Operation.ADDITION));
		         multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.speed, AttributeModifier.Operation.ADDITION));
		      }

		      return multimap;
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
