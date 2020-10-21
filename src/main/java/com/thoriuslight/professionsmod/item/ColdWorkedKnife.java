package com.thoriuslight.professionsmod.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.thoriuslight.professionsmod.init.ItemInit.ModItemTier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ColdWorkedKnife extends ToolCoreItem{

	private final float attackSpeed;
	protected final int hardness;
	public ColdWorkedKnife(ModItemTier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
		super(tier, attackDamageIn - 1.0f, attackSpeedIn, properties.defaultMaxDamage(tier.getMaxUses()));
	    this.hardness = tier.getHardness();
		this.attackSpeed = attackSpeedIn;
	}
	
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		if (state.getBlockHardness(worldIn, pos) != 0.0F) {
			int i = 3;
			if(this.getHardness(stack) > (hardness/2)) {
				--i;
	    	}
			stack.damageItem(i, entityLiving, (p_220044_0_) -> {
				p_220044_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			});
		}

		return true;
	}
	
	@Override
	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		return !player.isCreative();
	}
	
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Block block = state.getBlock();
		if (block == Blocks.COBWEB) {
			return 15.0F;
		} else {
			Material material = state.getMaterial();
			return material != Material.PLANTS && material != Material.TALL_PLANTS && material != Material.CORAL && !state.isIn(BlockTags.LEAVES) && material != Material.GOURD ? 1.0F : 1.5F;
		}
	}
	
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		int i = 2;
		if(this.getHardness(stack) > (hardness/2)) {
			--i;
    	}
		stack.damageItem(i, attacker, (p_220045_0_) -> {
			p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		});
		return true;
	}
	

	
	public boolean canHarvestBlock(BlockState blockIn) {
		return blockIn.getBlock() == Blocks.COBWEB;
	}
	
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();
		if (equipmentSlot == EquipmentSlotType.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage + this.getHardness(stack) * (1.d / hardness), AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
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
