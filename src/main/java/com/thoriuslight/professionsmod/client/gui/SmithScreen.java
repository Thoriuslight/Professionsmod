package com.thoriuslight.professionsmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;

public class SmithScreen extends Screen{
	private static final ResourceLocation WINDOW = new ResourceLocation("textures/gui/advancements/window.png");
	private double scrollX;
	private double scrollY;
	private int minX = Integer.MAX_VALUE;
	private int minY = Integer.MAX_VALUE;
	private int maxX = Integer.MIN_VALUE;
	private int maxY = Integer.MIN_VALUE;
	public SmithScreen() {
		super(new TranslationTextComponent("screen.smith.title"));
	}
	@Override
	protected void init() {
		this.scrollX = (double)(117 - (this.maxX + this.minX) / 2);
		this.scrollY = (double)(56 - (this.maxY + this.minY) / 2);
	}
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		      int i = (this.width - 252) / 2;
		      int j = (this.height - 140) / 2;
		      this.renderBackground();
		      this.renderInside(i, j);
			  this.renderWindow(i, j);
	   }
	   
	   private void renderInside(int xC, int yC) {
	         RenderSystem.pushMatrix();
	         RenderSystem.translatef((float)(xC + 9), (float)(yC + 18), 0.0F);
	         //draw background
	         RenderSystem.pushMatrix();
	         RenderSystem.enableDepthTest();
	         RenderSystem.translatef(0.0F, 0.0F, 950.0F);
	         RenderSystem.colorMask(false, false, false, false);
	         fill(4680, 2260, -4680, -2260, -16777216);
	         RenderSystem.colorMask(true, true, true, true);
	         RenderSystem.translatef(0.0F, 0.0F, -950.0F);
	         RenderSystem.depthFunc(518);
	         fill(234, 113, 0, 0, -16777216);
	         RenderSystem.depthFunc(515);
	         ResourceLocation resourcelocation = new ResourceLocation("textures/block/dirt.png");
	         this.minecraft.getTextureManager().bindTexture(resourcelocation);
	         int i = MathHelper.floor(this.scrollX);
	         int j = MathHelper.floor(this.scrollY);
	         int k = i % 16;
	         int l = j % 16;
	         for(int i1 = -1; i1 <= 15; ++i1) {
	             for(int j1 = -1; j1 <= 8; ++j1) {
	                blit(k + 16 * i1, l + 16 * j1, 0.0F, 0.0F, 16, 16, 16, 16);
	             }
	          }
	         RenderSystem.depthFunc(518);
	         RenderSystem.translatef(0.0F, 0.0F, -950.0F);
	         RenderSystem.colorMask(false, false, false, false);
	         fill(4680, 2260, -4680, -2260, -16777216);
	         RenderSystem.colorMask(true, true, true, true);
	         RenderSystem.translatef(0.0F, 0.0F, 950.0F);
	         RenderSystem.depthFunc(515);
	         RenderSystem.popMatrix();
	         
	         RenderSystem.popMatrix();
	         RenderSystem.depthFunc(515);
	         RenderSystem.disableDepthTest();
	   }
	   
	   private void renderWindow(int xC, int yC){
		      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		      RenderSystem.enableBlend();
		      this.minecraft.getTextureManager().bindTexture(WINDOW);
		      this.blit(xC, yC, 0, 0, 252, 140);
		      RenderSystem.disableBlend();
	   }
}
