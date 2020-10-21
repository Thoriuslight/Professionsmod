package com.thoriuslight.professionsmod.item;

import com.thoriuslight.professionsmod.block.StoneAnvilBlock;
import com.thoriuslight.professionsmod.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class HammerItem extends TieredItem{

	public HammerItem(Properties properties) {
		super(ItemTier.STONE, properties.defaultMaxDamage(131));
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockState blockstate = world.getBlockState(context.getPos());
		if(blockstate.getBlock() == BlockInit.STONEANVIL_BLOCK.get()) {
			if(((StoneAnvilBlock)blockstate.getBlock()).hammer(world, context.getPos(), context.getPlayer())) {
			    world.playSound((PlayerEntity)null, context.getPos(), SoundEvents.BLOCK_ANVIL_HIT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
				context.getPlayer().getCooldownTracker().setCooldown(this, 20);
				context.getPlayer().swingArm(context.getHand());
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
}
