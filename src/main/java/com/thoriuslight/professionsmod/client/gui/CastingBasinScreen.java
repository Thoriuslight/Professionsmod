package com.thoriuslight.professionsmod.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thoriuslight.professionsmod.ProfessionsMod;
import com.thoriuslight.professionsmod.init.ItemInit;
import com.thoriuslight.professionsmod.network.ModPacketHandler;
import com.thoriuslight.professionsmod.network.PacketSelectCast;
import com.thoriuslight.professionsmod.state.properties.Tool;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;

public class CastingBasinScreen extends Screen{
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(ProfessionsMod.MODID, "textures/gui/casting_basin.png");
	private int xSize, ySize;
	private final List<ItemStack> Items = Lists.newArrayList();
	private BlockPos pos;
	public CastingBasinScreen(BlockPos pos) {
		super(new TranslationTextComponent("castingbasin.edit"));
		Items.add(new ItemStack(ItemInit.COPPER_PICKAXEHEAD.get()));
		Items.add(new ItemStack(ItemInit.COPPER_SHOVELHEAD.get()));
		Items.add(new ItemStack(ItemInit.COPPER_AXEHEAD.get()));
		Items.add(new ItemStack(ItemInit.COPPER_KNIFEHEAD.get()));
		Items.add(new ItemStack(ItemInit.COPPER_HOEHEAD.get()));
		Items.add(new ItemStack(ItemInit.COPPER_INGOT.get()));
		this.xSize = 176;
		this.ySize = 84;
		this.pos = pos;
	}
	
	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		int i = (this.width - this.xSize) / 2 + 52;
	    int j = (this.height - this.ySize) / 2;
	    
		if (p_mouseClicked_1_ >= (double)i && p_mouseClicked_3_ >= (double)(j + 15) && p_mouseClicked_1_ < (double)(i + 16) && p_mouseClicked_3_ < (double)(j + 33)) {
			if(minecraft.player!=null) {
				ModPacketHandler.INSTANCE.sendToServer(new PacketSelectCast(Tool.PICKAXE, pos));
			}
	        this.minecraft.displayGuiScreen((Screen)null);
            return true;
        }
		if (p_mouseClicked_1_ >= (double)(i+16) && p_mouseClicked_3_ >= (double)(j + 15) && p_mouseClicked_1_ < (double)(i + 32) && p_mouseClicked_3_ < (double)(j + 33)) {
			if(minecraft.player!=null) {
				ModPacketHandler.INSTANCE.sendToServer(new PacketSelectCast(Tool.SHOVEL, pos));
			}
	        this.minecraft.displayGuiScreen((Screen)null);
            return true;
        }
		if (p_mouseClicked_1_ >= (double)(i+32) && p_mouseClicked_3_ >= (double)(j + 15) && p_mouseClicked_1_ < (double)(i + 48) && p_mouseClicked_3_ < (double)(j + 33)) {
			if(minecraft.player!=null) {
				ModPacketHandler.INSTANCE.sendToServer(new PacketSelectCast(Tool.AXE, pos));
			}
	        this.minecraft.displayGuiScreen((Screen)null);
            return true;
        }
		if (p_mouseClicked_1_ >= (double)(i+48) && p_mouseClicked_3_ >= (double)(j + 15) && p_mouseClicked_1_ < (double)(i + 64) && p_mouseClicked_3_ < (double)(j + 33)) {
			if(minecraft.player!=null) {
				ModPacketHandler.INSTANCE.sendToServer(new PacketSelectCast(Tool.KNIFE, pos));
			}
	        this.minecraft.displayGuiScreen((Screen)null);
            return true;
        }
		if (p_mouseClicked_1_ >= (double)i && p_mouseClicked_3_ >= (double)(j + 31) && p_mouseClicked_1_ < (double)(i + 16) && p_mouseClicked_3_ < (double)(j + 49)) {
			if(minecraft.player!=null) {
				ModPacketHandler.INSTANCE.sendToServer(new PacketSelectCast(Tool.HOE, pos));
			}
	        this.minecraft.displayGuiScreen((Screen)null);
            return true;
        }
		if (p_mouseClicked_1_ >= (double)(i+16) && p_mouseClicked_3_ >= (double)(j + 31) && p_mouseClicked_1_ < (double)(i + 32) && p_mouseClicked_3_ < (double)(j + 49)) {
			if(minecraft.player!=null) {
				ModPacketHandler.INSTANCE.sendToServer(new PacketSelectCast(Tool.INGOT, pos));
			}
	        this.minecraft.displayGuiScreen((Screen)null);
            return true;
        }
	    return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
	}

	@Override
	public void render(int mouseX, int mouseY, float p_render_3_) {
		this.renderBackground();
	    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
	      int i = (this.width - this.xSize) / 2;
	      int j = (this.height - this.ySize) / 2;
	    this.blit(i, j, 0, 0, this.xSize, this.ySize);
	    for(int k = 0; k < Items.size(); ++k) {
		    int j2 = j + 15 + (k / 4) * 16;
	    	this.minecraft.getTextureManager().bindTexture(GUI_TEXTURE);
	    	int j1 = 166;
	    	int k1 = i + 52 + (k % 4) * 16;
	    	if (mouseX >= k1 && mouseY >= j2 && mouseX < k1 + 16 && mouseY < j2 + 18) {
	    		j1 += 36;
	    	}
		    this.blit(k1, j2, 0, j1, 16, 18);
	        this.minecraft.getItemRenderer().renderItemAndEffectIntoGUI(Items.get(k), k1, j2);
	    }
		super.render(mouseX, mouseY, p_render_3_);
	}
	

}
