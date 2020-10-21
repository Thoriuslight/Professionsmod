package com.thoriuslight.professionsmod.client.gui;

import com.thoriuslight.professionsmod.network.ModPacketHandler;
import com.thoriuslight.professionsmod.network.PacketSyncProfCap;
import com.thoriuslight.professionsmod.profession.capabilities.CapabilityProfession;
import com.thoriuslight.professionsmod.profession.capabilities.IProfession.profession;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ProfessionsMenuScreen extends Screen{

	public ProfessionsMenuScreen() {
		super(new TranslationTextComponent("screen.promenu.title"));
	}
	
	@Override
	protected void init() {
		this.addButtons();
	}
	
	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		this.renderBackground();
	    this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 40, 16777215);
		super.render(p_render_1_, p_render_2_, p_render_3_);
	}
	
	private void addButtons(){
		this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 48 + -16, 98, 20, I18n.format("screen.smith.title"), (p_213065_1_) -> {
			Minecraft.getInstance().player.getCapability(CapabilityProfession.PROFESSION, null).ifPresent(iProfession -> {
                	iProfession.setProfession(profession.SMITH);
    				ModPacketHandler.INSTANCE.sendToServer(new PacketSyncProfCap(profession.SMITH, 0));
            });
			this.minecraft.player.sendStatusMessage(new StringTextComponent("You have become a smith!"), false);
	        this.minecraft.displayGuiScreen((Screen)null);
			//this.minecraft.displayGuiScreen(new SmithScreen());
		}));
	}
}
