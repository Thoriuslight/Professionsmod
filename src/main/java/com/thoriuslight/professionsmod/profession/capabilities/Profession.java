package com.thoriuslight.professionsmod.profession.capabilities;

import com.thoriuslight.professionsmod.network.ModPacketHandler;
import com.thoriuslight.professionsmod.network.PacketSyncProfCap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

public class Profession implements IProfession{
	
	private profession Prof = profession.NOTHING;
	private int skillPoints = 0;
	
	@Override
	public profession getProfession() {
		return Prof;
	}

	@Override
	public int getSkill() {
		return skillPoints;
	}

	@Override
	public void setProfession(profession Prof) {
		this.Prof = Prof;
		
	}

	@Override
	public void addSkill(int x, PlayerEntity player) {
		this.skillPoints += x;
    	ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayerEntity) player;}), new PacketSyncProfCap(profession.UNDEFINED, this.skillPoints));
	}

	@Override
	public void setSkill(int x) {
		this.skillPoints = x;
	}

}
