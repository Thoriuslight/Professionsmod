package com.thoriuslight.professionsmod.state.properties;

import net.minecraft.util.IStringSerializable;

public enum Tool implements IStringSerializable{
	PICKAXE("pickaxe", 3),
	SHOVEL("shovel", 1),
	AXE("axe", 3),
	KNIFE("knife", 1),
	HOE("hoe", 2),
	INGOT("ingot", 1),
	NOTHING("nothing", 0);
	
	private final String name;
	private final int reqIngot;
	private Tool(String name, int requiredIngots){
		this.name = name;
		this.reqIngot = requiredIngots;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	public int getRequiredIngotAmount() {
		return this.reqIngot;
	}
}
