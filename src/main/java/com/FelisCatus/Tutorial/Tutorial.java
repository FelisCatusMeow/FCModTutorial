package com.FelisCatus.Tutorial;

import com.FelisCatus.Tutorial.List.BlockList;
import com.FelisCatus.Tutorial.List.ItemList;
import com.FelisCatus.Tutorial.world.gen.OreGeneration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
//import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod("tutorial")
@EventBusSubscriber(modid = "tutorial", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Tutorial {

    public static Tutorial instance;
    public static final Logger LOGGER = LogManager.getLogger(Tutorial.class);
    public static String MOD_ID = "tutorial";
    public static final ItemGroup TUTORIAL_GROUP = new Tutorial.TutorialGroup("tutorial_group");

    public Tutorial()
    {
        instance = this;

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);

        ItemList.ITEMS.register(modEventBus);
        BlockList.BLOCKS.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
    }

    @SubscribeEvent
    public static void BlockItems(final RegistryEvent.Register<Item> event)
    {
      final IForgeRegistry<Item> registry = event.getRegistry();

      BlockList.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block ->
      {
        final Item.Properties properties = new Item.Properties().group(TUTORIAL_GROUP);
        final BlockItem blockItem = new BlockItem(block, properties);
        blockItem.setRegistryName(block.getRegistryName());
        registry.register(blockItem);
      });
    }
    private void setup(final FMLCommonSetupEvent event)
    {
      event.enqueueWork(()->
      {
        ItemModelsProperties.registerProperty(ItemList.CHARGED_ITEM.get(),
        new ResourceLocation("tutorial" ,"energy"),(stack, world, entity)->
        {
          LazyOptional<IEnergyStorage> lazyOptional = stack.getCapability(CapabilityEnergy.ENERGY);
    return lazyOptional.map(e -> (float) e.getEnergyStored() / e.getMaxEnergyStored()).orElse(0.0F);
        });
      });
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {

    }

    private void onServerStarting(FMLServerStartingEvent event)
    {
      
    }


    public static class TutorialGroup extends ItemGroup
    {

		public TutorialGroup(String name) {
			super(name);
		}

		@Override
		public ItemStack createIcon() 
    {
			return new ItemStack(Items.IRON_DOOR);
	  }
    }
    
}
