package com.FelisCatus.Tutorial.Util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegistryProperty  {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event)
    {
        
    }
}
