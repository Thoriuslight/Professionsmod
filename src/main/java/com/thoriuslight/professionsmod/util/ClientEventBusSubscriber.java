package com.thoriuslight.professionsmod.util;

import com.thoriuslight.professionsmod.ProfessionsMod;
import com.thoriuslight.professionsmod.client.gui.WoodenHopperScreen;
import com.thoriuslight.professionsmod.client.gui.screen.inventory.SmithCraftingScreen;
import com.thoriuslight.professionsmod.client.tileentity.renderer.StoneAnvilRenderer;
import com.thoriuslight.professionsmod.init.BlockInit;
import com.thoriuslight.professionsmod.init.KeyBindInit;
import com.thoriuslight.professionsmod.init.ModContainerTypes;
import com.thoriuslight.professionsmod.init.ModTileEntityTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ProfessionsMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModContainerTypes.WOODEN_HOPPER.get(), WoodenHopperScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.SMITH_CRAFTING.get(), SmithCraftingScreen::new);
		ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.STONEANVIL.get(), StoneAnvilRenderer::new);
        RenderType rendertype2 = RenderType.getTranslucent();
    	RenderTypeLookup.setRenderLayer(BlockInit.GLASS_JAR.get(), rendertype2);
    	KeyBindInit.initKeyBinding();
	}
}
