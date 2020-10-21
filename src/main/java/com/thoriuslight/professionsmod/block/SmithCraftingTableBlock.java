package com.thoriuslight.professionsmod.block;

import com.thoriuslight.professionsmod.inventory.container.SmithContainerProvider;
import com.thoriuslight.professionsmod.inventory.container.SmithCraftingContainer;
import com.thoriuslight.professionsmod.profession.capabilities.CapabilityProfession;
import com.thoriuslight.professionsmod.profession.capabilities.IProfession.profession;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SmithCraftingTableBlock extends Block{
	private static final ITextComponent field_220271_a = new TranslationTextComponent("container.smithcrafting");
	public SmithCraftingTableBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ActionResultType type[] = { ActionResultType.PASS };
		if (worldIn.isRemote) {
			player.getCapability(CapabilityProfession.PROFESSION, null).ifPresent(iProfession -> {
				if(iProfession.getProfession() == profession.SMITH) {
					type[0] = ActionResultType.SUCCESS;
					//player.sendStatusMessage(new StringTextComponent("Skill: "+iProfession.getSkill()), false);
				}
            });
			return type[0];
		} else {
			player.getCapability(CapabilityProfession.PROFESSION, null).ifPresent(iProfession -> {
				if(iProfession.getProfession() == profession.SMITH) {
    				type[0] = ActionResultType.SUCCESS;
    				//player.sendStatusMessage(new StringTextComponent("Skill: "+iProfession.getSkill()), false);
                	//iProfession.addSkill(1, player);
                	NetworkHooks.openGui((ServerPlayerEntity) player, /*state.getContainer(worldIn, pos)*/new SmithContainerProvider(pos), pos);
            		//player.openContainer(state.getContainer(worldIn, pos));
                }
            });
			return type[0];
		}
	}
	
	@Override
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
	      return new SimpleNamedContainerProvider((p_220270_2_, p_220270_3_, p_220270_4_) -> {
	          return new SmithCraftingContainer(p_220270_2_, p_220270_3_, IWorldPosCallable.of(worldIn, pos));
	       }, field_220271_a);
	}
}
