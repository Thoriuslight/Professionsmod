package com.thoriuslight.professionsmod;

import com.thoriuslight.professionsmod.command.CommandProfession;
import com.thoriuslight.professionsmod.init.BlockInit;
import com.thoriuslight.professionsmod.init.DataInit;
import com.thoriuslight.professionsmod.init.ItemInit;
import com.thoriuslight.professionsmod.init.KeyBindInit;
import com.thoriuslight.professionsmod.init.ModContainerTypes;
import com.thoriuslight.professionsmod.init.ModTileEntityTypes;
import com.thoriuslight.professionsmod.item.CopperOreItem;
import com.thoriuslight.professionsmod.item.SilverOreItem;
import com.thoriuslight.professionsmod.network.ModPacketHandler;
import com.thoriuslight.professionsmod.profession.capabilities.CapabilityProfession;
import com.thoriuslight.professionsmod.util.ClientEventBusSubscriber;
import com.thoriuslight.professionsmod.world.gen.ModOreGen;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("professionsmod")
@Mod.EventBusSubscriber(modid = ProfessionsMod.MODID, bus = Bus.MOD)
public class ProfessionsMod{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "professionsmod";
    public static ProfessionsMod instance;

    public ProfessionsMod() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::setup);
    	//modEventBus.addListener(ClientEventBusSubscriber::clientSetup);

    	ItemInit.ITEMS.register(modEventBus);
    	BlockInit.BLOCKS.register(modEventBus);
    	ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
    	ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
    	
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
    	final IForgeRegistry<Item> registry = event.getRegistry();
    	
    	BlockInit.BLOCKS.getEntries().stream().filter(block -> !(block == BlockInit.COPPER_ORE||block == BlockInit.SILVER_ORE||block == BlockInit.WOODENHOPPER_BLOCK||block == BlockInit.GLASS_JAR)).map(RegistryObject::get).forEach(block -> {
    		final Item.Properties properties = new Item.Properties().group(BlacksmithItemGroup.instance);
    		final BlockItem blockItem = new BlockItem(block, properties);
    		blockItem.setRegistryName(block.getRegistryName());
    		registry.register(blockItem);
    	});
    	event.getRegistry().register(new CopperOreItem(BlockInit.COPPER_ORE.get(), new Item.Properties().group(ProfCommonItemGroup.instance).maxStackSize(1)).setRegistryName("copper_ore"));
    	event.getRegistry().register(new SilverOreItem(BlockInit.SILVER_ORE.get(), new Item.Properties().group(ProfCommonItemGroup.instance).maxStackSize(1)).setRegistryName("silver_ore"));
    	event.getRegistry().register(new BlockItem(BlockInit.WOODENHOPPER_BLOCK.get(), new Item.Properties().group(EngineeringItemGroup.instance)).setRegistryName("woodenhopper_block"));
    	event.getRegistry().register(new BlockItem(BlockInit.GLASS_JAR.get(), new Item.Properties().group(AlchemyItemGroup.instance)).setRegistryName("glass_jar"));
    	LOGGER.debug("Registered BlockItems");
    }

    private void setup(final FMLCommonSetupEvent event){
    	ModPacketHandler.registerPackets();
    	DataInit.initData();
    	CapabilityProfession.register();
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    	CommandProfession.register(event.getCommandDispatcher());
    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
    	ModOreGen.generateOre();
    }
    //ItemGroups
    public static class ProfCommonItemGroup extends ItemGroup{
    	public static final ProfCommonItemGroup instance = new ProfCommonItemGroup(ItemGroup.GROUPS.length, "profcommontab");
		private ProfCommonItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockInit.COPPER_ORE.get().asItem());
		}
    	
    }
    public static class BlacksmithItemGroup extends ItemGroup{
    	public static final BlacksmithItemGroup instance = new BlacksmithItemGroup(ItemGroup.GROUPS.length, "blacksmithtab");
		private BlacksmithItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.STONEHAMMER_ITEM.get());
		}
    	
    }
    public static class EngineeringItemGroup extends ItemGroup{
    	public static final EngineeringItemGroup instance = new EngineeringItemGroup(ItemGroup.GROUPS.length, "engineertab");
		private EngineeringItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockInit.WOODENHOPPER_BLOCK.get().asItem());
		}
    	
    }
    public static class AlchemyItemGroup extends ItemGroup{
    	public static final AlchemyItemGroup instance = new AlchemyItemGroup(ItemGroup.GROUPS.length, "alchemytab");
		private AlchemyItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.MORTARANDPESTLE.get().asItem());
		}
    	
    }
    @SubscribeEvent
    public void attachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof PlayerEntity)
        	event.addCapability(new ResourceLocation(MODID, "profession"), new CapabilityProfession());
    }
    
    
}
