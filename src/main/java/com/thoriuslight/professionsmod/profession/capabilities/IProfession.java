package com.thoriuslight.professionsmod.profession.capabilities;

import net.minecraft.entity.player.PlayerEntity;

public interface IProfession {
	public enum profession{
		NOTHING,
		UNDEFINED,
		SMITH
	}
	public profession getProfession();
	public void setProfession(profession Prof);
	public int getSkill();
	public void addSkill(int x, PlayerEntity player);
	public void setSkill(int x);
}
