package com.FelisCatus.Tutorial;

import com.FelisCatus.Tutorial.List.*;
import com.FelisCatus.Tutorial.Network.ExampleNetwork;
import com.FelisCatus.Tutorial.Client.Gui.Screen.DisplayCaseScreen;
import com.FelisCatus.Tutorial.World.Gen.OreGeneration;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod("tutorial")
@EventBusSubscriber(modid = "tutorial", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Tutorial {

    public static Tutorial instance;
    public static final Logger LOGGER = LogManager.getLogger(Tutorial.class);
    public static String MOD_ID = "tutorial";
    public static final ItemGroup TUTORIAL_GROUP = new Tutorial.TutorialGroup("tutorial_group");

    public Tutorial() {
        instance = this;

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);

        ItemList.ITEMS.register(modEventBus);
        BlockList.BLOCKS.register(modEventBus);
        ContainerList.CONTAINERS.register(modEventBus);
        TileEntityList.TILE_ENTITY_TYPE.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);

    }

    @SubscribeEvent
    public static void BlockItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockList.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(TUTORIAL_GROUP);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }

    //更改为public
    public void setup(final FMLCommonSetupEvent event) {
        ExampleNetwork.list();
    }


    //更改为public
    public void clientSetup(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ContainerList.GUI_CASE_CONTAINER_TYPE.get(), DisplayCaseScreen::new);
        KeyBindingList.register(event);
    }

    // 暂时用不到
    // private void onServerStarting(FMLServerStartingEvent event)
    // {

    // }

    public static class TutorialGroup extends ItemGroup {

        public TutorialGroup(String name) {
            super(name);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.IRON_DOOR);
        }
    }

}
