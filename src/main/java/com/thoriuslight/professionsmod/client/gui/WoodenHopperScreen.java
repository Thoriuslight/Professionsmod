package com.thoriuslight.professionsmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thoriuslight.professionsmod.ProfessionsMod;
import com.thoriuslight.professionsmod.inventory.container.WoodenHopperContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class WoodenHopperScreen extends ContainerScreen<WoodenHopperContainer>{
	
	private static final ResourceLocation HOPPER_GUI_TEXTURE = new ResourceLocation(ProfessionsMod.MODID, "textures/gui/wooden_hopper.png");
	public WoodenHopperScreen(WoodenHopperContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.passEvents = false;
	    this.ySize = 133;
	}
	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		this.renderBackground();
		super.render(p_render_1_, p_render_2_, p_render_3_);
		this.renderHoveredToolTip(p_render_1_, p_render_2_);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
	  	this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	      this.minecraft.getTextureManager().bindTexture(HOPPER_GUI_TEXTURE);
	      int i = (this.width - this.xSize) / 2;
	      int j = (this.height - this.ySize) / 2;
	      this.blit(i, j, 0, 0, this.xSize, this.ySize);
	}

}
