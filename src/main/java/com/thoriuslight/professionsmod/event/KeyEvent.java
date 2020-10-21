package com.thoriuslight.professionsmod.event;

import javax.annotation.Nonnull;

import com.thoriuslight.professionsmod.ProfessionsMod;
import com.thoriuslight.professionsmod.client.gui.ProfessionsMenuScreen;
import com.thoriuslight.professionsmod.client.gui.SmithScreen;
import com.thoriuslight.professionsmod.init.KeyBindInit;
import com.thoriuslight.professionsmod.profession.capabilities.CapabilityProfession;
import com.thoriuslight.professionsmod.profession.capabilities.IProfession;
import com.thoriuslight.professionsmod.profession.capabilities.IProfession.profession;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.common.util.NonNullConsumer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public @Mod.EventBusSubscriber(modid = ProfessionsMod.MODID, bus = Bus.FORGE, value = Dist.CLIENT)
class KeyEvent {
	@SubscribeEvent
	public static void KeyInputEvent(KeyInputEvent event) {
		if(KeyBindInit.PROMENU.isPressed()) {
			boolean[] hasProfession = { false };
			Minecraft.getInstance().player.getCapability(CapabilityProfession.PROFESSION, null).ifPresent(new NonNullConsumer<IProfession>() {
                @Override
                public void accept(@Nonnull IProfession iProfession) {
                	hasProfession[0] = iProfession.getProfession() != profession.NOTHING;
                }
            });
			if(hasProfession[0]) {
				Minecraft.getInstance().displayGuiScreen(new SmithScreen());
			} 
			else {
				Minecraft.getInstance().displayGuiScreen(new ProfessionsMenuScreen());
			}
		}
	}
}
