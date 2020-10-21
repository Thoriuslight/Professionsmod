package com.thoriuslight.professionsmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.thoriuslight.professionsmod.network.ModPacketHandler;
import com.thoriuslight.professionsmod.network.PacketSyncProfCap;
import com.thoriuslight.professionsmod.profession.capabilities.CapabilityProfession;
import com.thoriuslight.professionsmod.profession.capabilities.IProfession.profession;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

public class CommandProfession {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("profession")
				.requires(source -> {return source.hasPermissionLevel(2);})
				.then(Commands.literal("reset")
						.executes(context -> {
							ServerPlayerEntity player = (ServerPlayerEntity) context.getSource().asPlayer();
							context.getSource().sendFeedback(new TranslationTextComponent("commands.profession.reset", player.getDisplayName()), true);
							player.getCapability(CapabilityProfession.PROFESSION, null).ifPresent(iprofession -> {
								iprofession.setProfession(profession.NOTHING);
								iprofession.setSkill(0);
						    	ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayerEntity) player;}), new PacketSyncProfCap(profession.NOTHING, 0));
							});
							return 0;
				}))
		);
	}
}
