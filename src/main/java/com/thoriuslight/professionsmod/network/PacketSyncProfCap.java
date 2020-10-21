package com.thoriuslight.professionsmod.network;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.thoriuslight.professionsmod.profession.capabilities.CapabilityProfession;
import com.thoriuslight.professionsmod.profession.capabilities.IProfession;
import com.thoriuslight.professionsmod.profession.capabilities.IProfession.profession;
import com.thoriuslight.professionsmod.profession.capabilities.Profession;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.NonNullConsumer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class PacketSyncProfCap {
	private final profession prof;
	private final int skill;
	public PacketSyncProfCap(Profession prof){
		this.prof = prof.getProfession();
		this.skill = prof.getSkill();
	}
	public PacketSyncProfCap(profession prof, int skill){
		this.prof = prof;
		this.skill = skill;
	}
	public static void encode(PacketSyncProfCap msg, PacketBuffer buf) {
		buf.writeInt(msg.prof.ordinal());
		buf.writeInt(msg.skill);
	}
	public static PacketSyncProfCap decode(PacketBuffer buf) {
		profession p = profession.values()[buf.readInt()];
		int s = buf.readInt();
		return new PacketSyncProfCap(p, s);
	}
	public static void handle(PacketSyncProfCap msg, Supplier<NetworkEvent.Context> supplier) {
		Context ctx = supplier.get();
		ServerPlayerEntity player = ctx.getSender();
		if(player != null) {
			ctx.enqueueWork(() -> {
				player.getCapability(CapabilityProfession.PROFESSION, null).ifPresent(new NonNullConsumer<IProfession>() {
	                @Override
	                public void accept(@Nonnull IProfession iProfession) {
	                	iProfession.setProfession(msg.prof);
	                }
	            });
			});
		} else if (ctx.getDirection().getReceptionSide().isClient()){
			ClientPlayerEntity thePlayer = Minecraft.getInstance().player;
			thePlayer.getCapability(CapabilityProfession.PROFESSION, null).ifPresent(new NonNullConsumer<IProfession>() {
                @Override
                public void accept(@Nonnull IProfession iProfession) {
                	if(msg.prof != profession.UNDEFINED) {
                		iProfession.setProfession(msg.prof);
                	}
                	iProfession.setSkill(msg.skill);
                }
            });
		}
	}
}
