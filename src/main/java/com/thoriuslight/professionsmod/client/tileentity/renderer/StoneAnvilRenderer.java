package com.thoriuslight.professionsmod.client.tileentity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.thoriuslight.professionsmod.tileentity.StoneAnvilTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class StoneAnvilRenderer extends TileEntityRenderer<StoneAnvilTileEntity>{

	public StoneAnvilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(StoneAnvilTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		ItemStack item = tileEntityIn.getItem();
		if(!item.isEmpty()) {
			matrixStackIn.push();
			matrixStackIn.scale(1.f,  1.f, 1.f);
			matrixStackIn.translate(0.5D, 1.0D, 0.5D);
			matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.f));
			renderItem(item, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
			matrixStackIn.pop();
		}
	}


	private void renderItem(ItemStack stack, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Minecraft.getInstance().getItemRenderer().renderItem(stack, TransformType.FIXED, combinedLightIn, combinedOverlayIn/*OverlayTexture.NO_OVERLAY*/, matrixStackIn, bufferIn);
	}
}
