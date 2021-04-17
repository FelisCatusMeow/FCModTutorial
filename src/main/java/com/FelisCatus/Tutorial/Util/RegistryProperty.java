package com.FelisCatus.Tutorial.Util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


/**************************************
            =====注意！=====
本文件为测试用文件，代码可能不会正常运行!
**************************************/



@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegistryProperty  {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event)
    {
        
    }
}
